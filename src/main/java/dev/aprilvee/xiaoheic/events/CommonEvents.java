package dev.aprilvee.xiaoheic.events;

import dev.aprilvee.xiaoheic.capabilities.*;
import dev.aprilvee.xiaoheic.entity.Sprite;
import dev.aprilvee.xiaoheic.main;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
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
            if(!event.getObject().getCapability(QiProvider.QI).isPresent()){
                event.addCapability(new ResourceLocation(main.MODID, "qivalue"), new QiProvider());
            }
            if(!event.getObject().getCapability(MaxQiProvider.MAX_QI).isPresent()){
                event.addCapability(new ResourceLocation(main.MODID, "maxqi"), new MaxQiProvider());
            }
            if(!event.getObject().getCapability(CultivationProvider.CULTIVATION).isPresent()){
                event.addCapability(new ResourceLocation(main.MODID, "cultivation"), new CultivationProvider());
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerCloned(PlayerEvent.Clone event){
        if(event.isWasDeath()) {
            event.getOriginal().getCapability(QiProvider.QI).ifPresent(oldStore -> {
                event.getOriginal().getCapability(QiProvider.QI).ifPresent(newStore -> {
                    newStore.copyFrom(oldStore);
                });
            });
            event.getOriginal().getCapability(MaxQiProvider.MAX_QI).ifPresent(oldStore -> {
                event.getOriginal().getCapability(MaxQiProvider.MAX_QI).ifPresent(newStore -> {
                    newStore.copyFrom(oldStore);
                });
            });
            event.getOriginal().getCapability(CultivationProvider.CULTIVATION).ifPresent(oldStore -> {
                event.getOriginal().getCapability(CultivationProvider.CULTIVATION).ifPresent(newStore -> {
                    newStore.copyFrom(oldStore);
                });
            });
        }
    }

    /*@SubscribeEvent
    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
        event.register(QiValue.class);
        event.register(MaxQi.class);
    }*/

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if(event.side == LogicalSide.SERVER) {

            event.player.getCapability(QiProvider.QI).ifPresent(qi ->
                    event.player.getCapability(MaxQiProvider.MAX_QI).ifPresent(maxQi -> {//grab players max qi

                        if(event.player.tickCount % 10 == 0){
                            if (qi.getQi() < maxQi.getMaxQi()) {
                                qi.addQi(1);
                                event.player.sendSystemMessage(Component.literal("Qi: " + qi.getQi())
                                        .withStyle(ChatFormatting.AQUA));
                            }else if(qi.getQi() > maxQi.getMaxQi()){
                            qi.set(maxQi.getMaxQi());
                            }
            }}));
        }
    }

    @SubscribeEvent //give cultivation when rclicking a sprite, then despawn the sprite
    public static void onEntityRightClick(PlayerInteractEvent.EntityInteractSpecific event){
        if(event.getTarget() instanceof Sprite){
            event.getEntity().getCapability(CultivationProvider.CULTIVATION).ifPresent(cultivation -> {
                cultivation.add(1);
                event.getEntity().sendSystemMessage(Component.literal("cultivation: " + cultivation.get()));
                event.getTarget().remove(Entity.RemovalReason.DISCARDED);
            });
        }
    }

}
