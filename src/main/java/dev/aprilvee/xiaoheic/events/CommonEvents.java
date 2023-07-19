package dev.aprilvee.xiaoheic.events;

import dev.aprilvee.xiaoheic.capabilities.*;
import dev.aprilvee.xiaoheic.entity.Sprite;
import dev.aprilvee.xiaoheic.main;
import dev.aprilvee.xiaoheic.network.Messages;
import dev.aprilvee.xiaoheic.network.packet.TemplateC2SPacket;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = main.MODID)// bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonEvents {

    @SubscribeEvent
    public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event){
        if(event.getObject() instanceof Player){
            if(!event.getObject().getCapability(SpiritProvider.SPIRITCAP).isPresent()){
                event.addCapability(new ResourceLocation(main.MODID, "spiritvalues"), new SpiritProvider());
            }/*
            if(!event.getObject().getCapability(MaxQiProvider.MAX_QI).isPresent()){
                event.addCapability(new ResourceLocation(main.MODID, "maxqi"), new MaxQiProvider());
            }
            if(!event.getObject().getCapability(CultivationProvider.CULTIVATION).isPresent()){
                event.addCapability(new ResourceLocation(main.MODID, "cultivation"), new CultivationProvider());
            }*/
        }
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

                        if(event.player.tickCount % 10 == 0){
                            if (spirit.getQi() < spirit.getMaxqi()) {
                                spirit.addQi(1);
                                event.player.sendSystemMessage(Component.literal("Qi: " + spirit.getQi())
                                        .withStyle(ChatFormatting.AQUA));
                            }else if(spirit.getQi() > spirit.getMaxqi()){
                            spirit.setQi(spirit.getMaxqi());
                            }
            }});
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


}
