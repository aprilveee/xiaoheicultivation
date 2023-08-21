package dev.aprilvee.xiaoheic.events;

import dev.aprilvee.xiaoheic.capability.SpiritProvider;
import dev.aprilvee.xiaoheic.client.ClientCapData;
import dev.aprilvee.xiaoheic.client.gui.QiBar;
import dev.aprilvee.xiaoheic.client.gui.XiaoheiMenu;
import dev.aprilvee.xiaoheic.client.model.BasicSpellModel;
import dev.aprilvee.xiaoheic.client.model.SpriteModel;
import dev.aprilvee.xiaoheic.client.render.model.BasicSpellRenderer;
import dev.aprilvee.xiaoheic.client.render.model.sprites.*;
import dev.aprilvee.xiaoheic.network.Messages;
import dev.aprilvee.xiaoheic.network.packet.HandleCastC2S;
import dev.aprilvee.xiaoheic.registry.entities;
import dev.aprilvee.xiaoheic.util.Keybinds;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import dev.aprilvee.xiaoheic.main;

public class ClientEvents {

    @Mod.EventBusSubscriber(modid = main.MODID, value = Dist.CLIENT)
    public static class ClientForgeEvents{


        @SubscribeEvent(priority = EventPriority.HIGHEST)
        public static void spellcastKeyInput(InputEvent event) {

            if (Keybinds.MENU_KEY.consumeClick()) {
                Minecraft.getInstance().setScreen(new XiaoheiMenu());
            }
            if (Keybinds.CAST_SLOT1_KEY.consumeClick()) {
                Messages.sendToServer(new HandleCastC2S(0));
            }
            if (Keybinds.CAST_SLOT2_KEY.consumeClick()) {
                Messages.sendToServer(new HandleCastC2S(1));
            }
            if (Keybinds.CAST_SLOT3_KEY.consumeClick()) {
                Messages.sendToServer(new HandleCastC2S(2));
            }
            if (Keybinds.CAST_SLOT4_KEY.consumeClick()) {
                Messages.sendToServer(new HandleCastC2S(3));
            }
            if (Keybinds.CAST_SLOT5_KEY.consumeClick()) {
                Messages.sendToServer(new HandleCastC2S(4));
            }
            if (Keybinds.CAST_SLOT6_KEY.consumeClick()) {
                Messages.sendToServer(new HandleCastC2S(5));
            }


        }
    }

    @Mod.EventBusSubscriber(modid = main.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModBusEvents{
        @SubscribeEvent
        public static void registerOverlays(RegisterGuiOverlaysEvent event){
            event.registerAboveAll("qi_bar", QiBar.OVERLAY);
        }

        @SubscribeEvent
        public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event){
            EntityRenderers.register(entities.SPRITE.get(), SpriteRenderer::new);
            EntityRenderers.register(entities.METALSPRITE.get(), MetalSpriteRenderer::new);
            EntityRenderers.register(entities.WATERSPRITE.get(), WaterSpriteRenderer::new);
            EntityRenderers.register(entities.WOODSPRITE.get(), WoodSpriteRenderer::new);
            EntityRenderers.register(entities.FIRESPRITE.get(), FireSpriteRenderer::new);
            EntityRenderers.register(entities.EARTHSPRITE.get(), EarthSpriteRenderer::new);
            EntityRenderers.register(entities.BASIC_SPELL.get(), BasicSpellRenderer::new);
        }

        @SubscribeEvent
        public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event){
            event.registerLayerDefinition(SpriteModel.LAYER_LOCATION, SpriteModel::createBodyLayer);
            event.registerLayerDefinition(BasicSpellModel.LAYER_LOCATION, BasicSpellModel::createBodyLayer);

        }

        @SubscribeEvent
        public static void onKeyRegister(RegisterKeyMappingsEvent event) {
            event.register(Keybinds.MENU_KEY);
            event.register(Keybinds.CASTING_TOGGLE);
            event.register(Keybinds.CAST_SLOT1_KEY);
            event.register(Keybinds.CAST_SLOT2_KEY);
            event.register(Keybinds.CAST_SLOT3_KEY);
            event.register(Keybinds.CAST_SLOT4_KEY);
            event.register(Keybinds.CAST_SLOT5_KEY);
            event.register(Keybinds.CAST_SLOT6_KEY);
        }
    }

}
