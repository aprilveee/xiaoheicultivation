package dev.aprilvee.xiaoheic.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class BreathingCScreen extends AbstractCultivationScreen {
	//private static final ResourceLocation MENU = new ResourceLocation(main.MODID,"textures/gui/menuplaceholder.png");

	public BreathingCScreen() {
		super(Component.literal(""));
	}

	@Override
	public void render(GuiGraphics gui, int mouseX, int mouseY, float pticks) {
		super.renderBackground(gui);
		//gui.drawCenteredString(minecraft.font, String.valueOf(minecraft.player.tickCount), minecraft.getWindow().getGuiScaledWidth()/2, minecraft.getWindow().getGuiScaledHeight()/2, 0xFFFFFF);
		super.render(gui, mouseX, mouseY, pticks);
	}
}