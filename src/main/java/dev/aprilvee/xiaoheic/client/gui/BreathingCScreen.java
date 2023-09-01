package dev.aprilvee.xiaoheic.client.gui;

import dev.aprilvee.xiaoheic.client.cultivation.DebugPlayerUIO;
import dev.aprilvee.xiaoheic.client.cultivation.IUIObject;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;

public class BreathingCScreen extends AbstractCultivationScreen {
	IUIObject testobject;

	public BreathingCScreen() {
		super(Component.literal(""));
	}

	@Override
	public void render(GuiGraphics gui, int mouseX, int mouseY, float pticks) {
		super.renderBackground(gui);

		testobject.tick(minecraft.player.tickCount);
		testobject.render(gui);
		//gui.drawCenteredString(minecraft.font, String.valueOf(minecraft.player.tickCount), minecraft.getWindow().getGuiScaledWidth()/2, minecraft.getWindow().getGuiScaledHeight()/2, 0xFFFFFF);
		super.render(gui, mouseX, mouseY, pticks);
	}

	@Override
	public void init(){
		testobject = new DebugPlayerUIO();
		testobject.setPos(minecraft.getWindow().getGuiScaledWidth()/2, minecraft.getWindow().getGuiScaledHeight()/2);
	}
}