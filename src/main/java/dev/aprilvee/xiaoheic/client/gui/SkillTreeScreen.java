package dev.aprilvee.xiaoheic.client.gui;

import dev.aprilvee.xiaoheic.main;
import dev.aprilvee.xiaoheic.spell.tree.ISkillTree;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class SkillTreeScreen extends Screen {
	private static final ResourceLocation MENU = new ResourceLocation(main.MODID,"textures/gui/menuplaceholder.png");
	ISkillTree tree;

	public SkillTreeScreen(ISkillTree tree) {
		super(Component.literal(""));
		this.tree = tree;
	}

	@Override
	public void render(GuiGraphics graphics, int mouseX, int mouseY, float pticks) {
		int yOffset = (minecraft.getWindow().getGuiScaledHeight()-200)/2;
		//render the proper menu, then render from the tree variable 
		graphics.blit(MENU,(width-200)/2, yOffset, 0, 0,  200,200, 200,200);
		super.render(graphics, mouseX, mouseY, pticks);

	}
	@Override
	public boolean isPauseScreen() {
		return false;
	}
}
