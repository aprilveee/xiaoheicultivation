package dev.aprilvee.xiaoheic.client.gui;

import dev.aprilvee.xiaoheic.main;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public abstract class AbstractCultivationScreen extends Screen {
	private static final ResourceLocation SCREEN = new ResourceLocation(main.MODID,"textures/gui/cultivationbackground.png");
	private static final int x = 230;
	private static final int y = 178;

	protected AbstractCultivationScreen(Component pTitle) {
		super(pTitle);
	}

	@Override
	public void renderBackground(GuiGraphics gui){
		int xOffset = (minecraft.getWindow().getGuiScaledWidth()-x)/2;
		int yOffset = (minecraft.getWindow().getGuiScaledHeight()-y)/2;
		gui.blit(SCREEN,xOffset, yOffset, 0, 0,  x,y, 256,256);
	}

	@Override
	public boolean isPauseScreen() {
		return false;
	}
}
