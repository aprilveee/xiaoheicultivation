package dev.aprilvee.xiaoheic.client.gui;

import dev.aprilvee.xiaoheic.main;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

public class XiaoheiMenu {
    public static final IGuiOverlay OVERLAY = QiBar::renderOverlay;
    private static final ResourceLocation MENU = new ResourceLocation(main.MODID,"textures/gui/cultivationmenu.png");

    public static void renderOverlay(ForgeGui gui, GuiGraphics guiGraphics, float pt, int width, int height){

    }

}
