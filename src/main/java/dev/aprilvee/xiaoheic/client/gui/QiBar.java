package dev.aprilvee.xiaoheic.client.gui;

import dev.aprilvee.xiaoheic.capability.SpiritCap;
import dev.aprilvee.xiaoheic.capability.SpiritProvider;
import dev.aprilvee.xiaoheic.client.ClientCapData;
import dev.aprilvee.xiaoheic.main;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

public class QiBar {
    public static final IGuiOverlay OVERLAY = QiBar::renderOverlay;

    private static final ResourceLocation BAR_FULL = new ResourceLocation(main.MODID,"textures/gui/qibarfull.png");
    private static final ResourceLocation BAR_EMPTY = new ResourceLocation(main.MODID,"textures/gui/qibarempty.png");

    private static final Minecraft minecraft = Minecraft.getInstance();


    public static void renderOverlay(ForgeGui gui, GuiGraphics guiGraphics, float pt, int width, int height){

        if(ClientCapData.getMaxqi() == 0) {
            return;
        }
        int qi = ClientCapData.getQi();
        int maxQi = ClientCapData.getMaxqi();
        float barLength;
        barLength = (float)qi/(float) maxQi*147;
        int yOffset = minecraft.getWindow().getGuiScaledHeight() - 64;

        //in order: texture, xoffset, yoffset, texture xoffset, texture yoffset, element xsize, element ysize, texture xsize, texture ysize
        guiGraphics.blit(BAR_EMPTY, width/2-72, yOffset, 0, 0, 147, 13, 147, 13);
        if(qi > 0){
            guiGraphics.blit(BAR_FULL, width/2-72, yOffset, 0, 0,Math.round(barLength), 13, 147, 13);
        }
    }



    /*public static final IGuiOverlay QI_BAR = (((gui, guiGraphics, partialTick, screenWidth, screenHeight) -> {
        int x = screenWidth/2;
        int y = screenHeight;
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F,1.0F,1.0F,1.0F);
        RenderSystem.setShaderTexture(0, BAR_EMPTY);
    }));*/
}
