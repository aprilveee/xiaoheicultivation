package dev.aprilvee.xiaoheic.events;

import dev.aprilvee.xiaoheic.client.gui.QiBar;
import dev.aprilvee.xiaoheic.client.model.BasicSpellModel;
import dev.aprilvee.xiaoheic.client.model.SpriteModel;
import dev.aprilvee.xiaoheic.client.render.model.BasicSpellRenderer;
import dev.aprilvee.xiaoheic.client.render.model.SpriteRenderer;
import dev.aprilvee.xiaoheic.network.Messages;
import dev.aprilvee.xiaoheic.network.packet.SpellC2S;
import dev.aprilvee.xiaoheic.registry.entities;
import dev.aprilvee.xiaoheic.spell.SpellList;
import dev.aprilvee.xiaoheic.util.Keybinds;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import dev.aprilvee.xiaoheic.main;

public class ClientEvents {

    @Mod.EventBusSubscriber(modid = main.MODID, value = Dist.CLIENT)
    public static class ClientForgeEvents{



        @SubscribeEvent
        public static void onKeyInput(InputEvent event) {
            if (Keybinds.CAST_SLOT1_KEY.consumeClick()) {
                Messages.sendToServer(new SpellC2S(SpellList.none));
            }
            if (Keybinds.CAST_SLOT2_KEY.consumeClick()) {
                Messages.sendToServer(new SpellC2S(SpellList.fireball));
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
            EntityRenderers.register(entities.BASIC_SPELL.get(), BasicSpellRenderer::new);
        }

        @SubscribeEvent
        public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event){
            event.registerLayerDefinition(SpriteModel.LAYER_LOCATION, SpriteModel::createBodyLayer);
            event.registerLayerDefinition(BasicSpellModel.LAYER_LOCATION, BasicSpellModel::createBodyLayer);

        }

        @SubscribeEvent
        public static void onKeyRegister(RegisterKeyMappingsEvent event) {
            event.register(Keybinds.CAST_SLOT1_KEY);
            event.register(Keybinds.CAST_SLOT2_KEY);
            event.register(Keybinds.CAST_SLOT3_KEY);
            event.register(Keybinds.CAST_SLOT4_KEY);
        }
    }

}
