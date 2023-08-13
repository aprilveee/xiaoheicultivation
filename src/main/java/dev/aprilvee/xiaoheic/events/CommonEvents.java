package dev.aprilvee.xiaoheic.events;

import dev.aprilvee.xiaoheic.capability.*;
import dev.aprilvee.xiaoheic.command.commands;
import dev.aprilvee.xiaoheic.cultivation.Cultivation;
import dev.aprilvee.xiaoheic.data.Datalist;
import dev.aprilvee.xiaoheic.data.datatype.SpellType;
import dev.aprilvee.xiaoheic.entity.BasicSpell;
import dev.aprilvee.xiaoheic.main;
import dev.aprilvee.xiaoheic.network.Messages;
import dev.aprilvee.xiaoheic.network.packet.CultivationS2C;
import dev.aprilvee.xiaoheic.network.packet.MaxQiS2C;
import dev.aprilvee.xiaoheic.network.packet.QiSyncS2C;
import dev.aprilvee.xiaoheic.registry.tags;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = main.MODID)
public class CommonEvents {

    @SubscribeEvent
    public static void onAttachCapabilities(AttachCapabilitiesEvent<Entity> event){
        if(event.getObject() instanceof Player){
            if(!event.getObject().getCapability(SpiritProvider.SPIRITCAP).isPresent()) {
                event.addCapability(new ResourceLocation(main.MODID, "spiritvalues"), new SpiritProvider());
            }
        }
        /*if(event.getObject() instanceof BasicSpell){
            if(!event.getObject().getCapability(SpellProvider.SPELLCAP).isPresent()) {
                event.addCapability(new ResourceLocation(main.MODID, "spelltype"), new SpellProvider());
            }
        }*/
    }
    @SubscribeEvent
    public static void registerComands(RegisterCommandsEvent event){
        commands.register(event.getDispatcher());
    }

    @SubscribeEvent
    public static void onPlayerCloned(PlayerEvent.Clone event){
        if(event.isWasDeath()) {
            event.getOriginal().getCapability(SpiritProvider.SPIRITCAP).ifPresent(oldStore -> {
                event.getOriginal().getCapability(SpiritProvider.SPIRITCAP).ifPresent(newStore -> {
                    newStore.copyFrom(oldStore);
                });
            });
        }
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if(event.side == LogicalSide.SERVER) {

            event.player.getCapability(SpiritProvider.SPIRITCAP).ifPresent(sp -> {//grab players qi and max qi
                        if(event.player.tickCount % 5 == 0){
                            if (sp.getQi() < sp.getMaxqi() && sp.getMaxqi() > 0) {

                                sp.addQi(sp.getQiRegen());

                            }if(sp.getQi() > sp.getMaxqi()){
                                sp.setQi(sp.getMaxqi());
                            }
                            Messages.sendToClient(new QiSyncS2C(sp.getQi()), event.player.getServer().getPlayerList().getPlayerByName(event.player.getName().getString()));
                        }});
        }
    }

    @SubscribeEvent
    public static void onProjectileImpact(ProjectileImpactEvent event){
        if(event.getProjectile() instanceof BasicSpell){
            BasicSpell spell = (BasicSpell) event.getProjectile();
            SpellType type = Datalist.spells[spell.getIndex()];

            /*if(event.getRayTraceResult().getType() == HitResult.Type.ENTITY){
                switch (type.id){
                    case "fireball":
                        SpellEffects.fireballEntity(spell.getOwner(), event.getRayTraceResult());
                        break;
                    default: break;

            }


            }*/


            //proj.remove(Entity.RemovalReason.DISCARDED);
        }
    }

    @SubscribeEvent //give cultivation when rclicking a sprite, then despawn the sprite
    public static void onEntityRightClick(PlayerInteractEvent.EntityInteractSpecific event){
        if(event.getTarget().getType().getTags().toList().contains(tags.sprites)){ //if entity is a sprite
            if(event.getSide() == LogicalSide.SERVER){ //so the sync message doesn't crash in state rewards

                SpiritCap sp = event.getEntity().getCapability(SpiritProvider.SPIRITCAP).orElse(null);
                switch(event.getTarget().getType().toString()){ //see which sprite type
                    case "entity.xiaoheic.sprite": event.getTarget().discard(); break;
                    case "entity.xiaoheic.metalsprite": sp.addMetal(1); break;
                    case "entity.xiaoheic.watersprite": sp.addWater(1); break;
                    case "entity.xiaoheic.woodsprite": sp.addWood(1); break;
                    case "entity.xiaoheic.firesprite": sp.addFire(1); event.getEntity().sendSystemMessage(Component.literal("fire:" + sp.getFire()));break;
                    case "entity.xiaoheic.earthsprite": sp.addEarth(1); break;
                }
                Cultivation.addCXP(event.getEntity(), 1);
                event.getTarget().discard();
            }
        }

    }

    @SubscribeEvent
    public static void onPlayerJoinWorld(EntityJoinLevelEvent event) {
        if(!event.getLevel().isClientSide()) {
            if(event.getEntity() instanceof ServerPlayer player) {
                player.getCapability(SpiritProvider.SPIRITCAP).ifPresent(spirit -> {
                    Messages.sendToClient(new QiSyncS2C(spirit.getQi()), player);
                    Messages.sendToClient(new MaxQiS2C(spirit.getMaxqi()), player);
                    Messages.sendToClient(new CultivationS2C(spirit.getCultivation(),spirit.getMetal(),spirit.getWater(),
                            spirit.getWood(),spirit.getFire(),spirit.getEarth()), player);
                });
            }
        }
    }

}
