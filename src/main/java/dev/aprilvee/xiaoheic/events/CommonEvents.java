package dev.aprilvee.xiaoheic.events;

import dev.aprilvee.xiaoheic.capability.*;
import dev.aprilvee.xiaoheic.command.commands;
import dev.aprilvee.xiaoheic.entity.BasicSpell;
import dev.aprilvee.xiaoheic.entity.Sprite;
import dev.aprilvee.xiaoheic.main;
import dev.aprilvee.xiaoheic.network.Messages;
import dev.aprilvee.xiaoheic.network.packet.CultivationS2C;
import dev.aprilvee.xiaoheic.network.packet.MaxQiS2C;
import dev.aprilvee.xiaoheic.network.packet.QiSyncS2C;
import dev.aprilvee.xiaoheic.registry.entities;
import dev.aprilvee.xiaoheic.spell.SpellList;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.ConsoleInput;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;

import java.io.Console;

@Mod.EventBusSubscriber(modid = main.MODID)// bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonEvents {

    @SubscribeEvent
    public static void onAttachCapabilities(AttachCapabilitiesEvent<Entity> event){
        if(event.getObject() instanceof Player){
            if(!event.getObject().getCapability(SpiritProvider.SPIRITCAP).isPresent()) {
                event.addCapability(new ResourceLocation(main.MODID, "spiritvalues"), new SpiritProvider());
            }
        }
        if(event.getObject() instanceof BasicSpell){
            if(!event.getObject().getCapability(SpellProvider.SPELLCAP).isPresent()) {
                event.addCapability(new ResourceLocation(main.MODID, "spelltype"), new SpellProvider());
            }
        }
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

            event.player.getCapability(SpiritProvider.SPIRITCAP).ifPresent(spirit -> {//grab players qi and max qi
                        if(event.player.tickCount % 5 == 0){
                            if (spirit.getQi() < spirit.getMaxqi() && spirit.getMaxqi() > 0) {
                                spirit.addQi((int)( (6+Math.pow(spirit.getMaxqi(),0.65)) * spirit.getQiregen()));

                            }if(spirit.getQi() > spirit.getMaxqi()){
                                spirit.setQi(spirit.getMaxqi());
                            }
                            Messages.sendToClient(new QiSyncS2C(spirit.getQi()), event.player.getServer().getPlayerList().getPlayerByName(event.player.getName().getString()));
                        }});
        }
    }

    @SubscribeEvent
    public static void onProjectileImpact(ProjectileImpactEvent event){
        event.getEntity().remove(Entity.RemovalReason.DISCARDED);
        if(event.getEntity().getType() == entities.BASIC_SPELL.get()){
            event.getEntity().sendSystemMessage(Component.literal("gwa gwa"));
            event.getEntity().remove(Entity.RemovalReason.DISCARDED);
        }
    }

    @SubscribeEvent //give cultivation when rclicking a sprite, then despawn the sprite
    public static void onEntityRightClick(PlayerInteractEvent.EntityInteractSpecific event){
        if(event.getTarget() instanceof Sprite){
            event.getEntity().getCapability(SpiritProvider.SPIRITCAP).ifPresent(spirit -> {
                event.getTarget().remove(Entity.RemovalReason.DISCARDED);
                spirit.addCultivation(1);
                event.getEntity().sendSystemMessage(Component.literal("cultivation: " + spirit.getCultivation()));
            });
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
