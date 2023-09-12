package dev.aprilvee.xiaoheic.spell.node;

import dev.aprilvee.xiaoheic.spell.tree.ISkillTree;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec2;

public interface ITreeNode {
	public void onUnlock(Player player);
	public void onRemove(Player player);
	public Boolean unlocked(Player player);
	public ISkillTree tree();
	public Vec2 getPos();
	public int index();
	public String id();
	public ResourceLocation texture();
	public Component name();
	public Component description();
}
