package dev.aprilvee.xiaoheic.client.gui;

import dev.aprilvee.xiaoheic.main;
import dev.aprilvee.xiaoheic.spell.node.ITreeNode;
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
		//graphics.blit(MENU,(width-200)/2, yOffset, 0, 0,  200,200, 200,200);
		for(int i=0;i<tree.nodes().length;i++){
			ITreeNode[] nodes = tree.nodes();
			ITreeNode node = nodes[i];
			//graphics.blit(node.texture(), (int) node.getPos().x, (int) node.getPos().y,0,0,16,16,16,16);
			graphics.blit(node.texture(), 200, 200,0,0,6000,6000,6000,6000);

		}

		super.render(graphics, mouseX, mouseY, pticks);

	}
	@Override
	public boolean isPauseScreen() {
		return false;
	}
}
