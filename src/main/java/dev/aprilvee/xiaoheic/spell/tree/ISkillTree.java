package dev.aprilvee.xiaoheic.spell.tree;

import dev.aprilvee.xiaoheic.spell.node.ITreeNode;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;

public interface ISkillTree {
	public void onUnlock(Player player);
	public ITreeNode[] nodes();
	public boolean unlocked(Player player);
	public int index();
	public String id();
	public ResourceLocation background();
	public ResourceLocation icon();
	public Component name();
	public Component description();
}
