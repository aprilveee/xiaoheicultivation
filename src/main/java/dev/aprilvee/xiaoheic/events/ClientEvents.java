package dev.aprilvee.xiaoheic.events;

import dev.aprilvee.xiaoheic.client.gui.QiBar;
import dev.aprilvee.xiaoheic.client.model.SpriteModel;
import dev.aprilvee.xiaoheic.client.renderer.model.SpriteRenderer;
import dev.aprilvee.xiaoheic.registry.entities;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import dev.aprilvee.xiaoheic.main;

public class ClientEvents {

    @Mod.EventBusSubscriber(modid = main.MODID, value = Dist.CLIENT)
    public static class ClientForgeEvents{

    }

    @Mod.EventBusSubscriber(modid = main.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModBusEvents{
        @SubscribeEvent
        public static void registerOverlays(RegisterGuiOverlaysEvent event){
            event.registerAboveAll("qi_bar", QiBar.OVERLAY);
        }

        @SubscribeEvent
        public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event){
            event.registerEntityRenderer(entities.SPRITE.get(), SpriteRenderer::new);
        }

        @SubscribeEvent
        public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event){
            event.registerLayerDefinition(SpriteModel.LAYER_LOCATION, SpriteModel::createBodyLayer);
        }
    }



}
