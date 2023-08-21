package dev.aprilvee.xiaoheic.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class BreathingCScreen extends AbstractCultivationScreen {
	//private static final ResourceLocation MENU = new ResourceLocation(main.MODID,"textures/gui/menuplaceholder.png");

	public BreathingCScreen() {
		super(Component.literal(""));
	}

	@Override
	public void render(GuiGraphics gui, int mouseX, int mouseY, float pticks) {
		super.renderBackground(gui);
		super.render(gui, mouseX, mouseY, pticks);
	}
}