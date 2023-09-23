package dev.aprilvee.xiaoheic.events;

import dev.aprilvee.xiaoheic.capability.SpiritCap;
import dev.aprilvee.xiaoheic.capability.SpiritProvider;
import dev.aprilvee.xiaoheic.command.commands;
import dev.aprilvee.xiaoheic.entity.sprite.ISprite;
import dev.aprilvee.xiaoheic.main;
import dev.aprilvee.xiaoheic.network.Messages;
import dev.aprilvee.xiaoheic.network.packet.CultivationS2C;
import dev.aprilvee.xiaoheic.network.packet.MaxQiS2C;
import dev.aprilvee.xiaoheic.network.packet.QiSyncS2C;
import dev.aprilvee.xiaoheic.registry.tags;
import dev.aprilvee.xiaoheic.util.xiaoheiutils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
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
    }
    @SubscribeEvent
    public static void registerComands(RegisterCommandsEvent event){
        commands.register(event.getDispatcher());
    }

    @SubscribeEvent
    public static void onPlayerCloned(PlayerEvent.Clone event) {
        Player oldPlayer = event.getOriginal();
        oldPlayer.revive();
        oldPlayer.getCapability(SpiritProvider.SPIRITCAP).ifPresent(oldsp -> {
            event.getEntity().getCapability(SpiritProvider.SPIRITCAP).ifPresent(sp -> {
                sp.copyFrom(oldsp);
            });});
        event.getOriginal().invalidateCaps();
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if(event.side == LogicalSide.SERVER) {
            event.player.getCapability(SpiritProvider.SPIRITCAP).ifPresent(sp -> {
                sp.currentcultivation.minigameTick(event.player);

                if(event.player.tickCount % 5 == 0){//qi regen
                    if (sp.maxqi > 0) {

                        sp.addQi(sp.getQiRegen());
                        Messages.sendToClient(new QiSyncS2C(sp.getQi()), event.player.getServer().getPlayerList().getPlayerByName(event.player.getName().getString()));
                    }
                }

                xiaoheiutils.tickPassives(event.player);
                sp.tickCount = event.player.tickCount;
            });
        }

    }

    @SubscribeEvent //give cultivation when rclicking a sprite, then despawn the sprite
    public static void onEntityRightClick(PlayerInteractEvent.EntityInteractSpecific event){
        if(event.getTarget().getType().getTags().toList().contains(tags.sprites)){ //if entity is a sprite
            if(event.getSide() == LogicalSide.SERVER){ //so the sync message doesn't crash in state rewards

                SpiritCap sp = event.getEntity().getCapability(SpiritProvider.SPIRITCAP).orElse(null);
                ISprite sprite = (ISprite) event.getTarget(); //WILL crash if entity is not a sprite
                sprite.absorbSprite(sp, event.getEntity());
                event.getTarget().discard();
            }
        }

    }

    @SubscribeEvent
    public static void onPlayerJoinWorld(EntityJoinLevelEvent event) {
        if(!event.getLevel().isClientSide()) {
            if(event.getEntity() instanceof ServerPlayer player) {
                player.getCapability(SpiritProvider.SPIRITCAP).ifPresent(sp -> {
                    Messages.sendToClient(new QiSyncS2C(sp.qi), player);
                    Messages.sendToClient(new MaxQiS2C(sp.maxqi), player);
                    Messages.sendToClient(new CultivationS2C(sp.getCultivation(),sp.getMetal(),sp.getWater(),
                            sp.getWood(),sp.getFire(),sp.getEarth()), player);
                });
            }
        }
    }

}
