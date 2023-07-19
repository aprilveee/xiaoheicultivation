package dev.aprilvee.xiaoheic.client.gui;

import dev.aprilvee.xiaoheic.capabilities.SpiritCap;
import dev.aprilvee.xiaoheic.capabilities.SpiritProvider;
import dev.aprilvee.xiaoheic.main;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

public class QiBar { //idk how to anything!!!! this will be done approximately later!
    public static final IGuiOverlay OVERLAY = QiBar::renderOverlay;

    private static final ResourceLocation BAR_FULL = new ResourceLocation(main.MODID,"textures/gui/qibarfull");
    private static final ResourceLocation BAR_EMPTY = new ResourceLocation(main.MODID,"textures/gui/qibarempty  ");

    private static final Minecraft minecraft = Minecraft.getInstance();

    public static void renderOverlay(ForgeGui gui, GuiGraphics guiGraphics, float pt, int width, int height){

        SpiritCap spiritvalues = minecraft.player.getCapability(SpiritProvider.SPIRITCAP).orElse(null);
        int qi = spiritvalues.getQi();
        int maxQi = spiritvalues.getMaxqi();

    }



    /*public static final IGuiOverlay QI_BAR = (((gui, guiGraphics, partialTick, screenWidth, screenHeight) -> {
        int x = screenWidth/2;
        int y = screenHeight;
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F,1.0F,1.0F,1.0F);
        RenderSystem.setShaderTexture(0, BAR_EMPTY);
    }));*/
}
