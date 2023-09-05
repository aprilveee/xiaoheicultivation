package dev.aprilvee.xiaoheic.skilltree;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public interface ITree {
	public ITreeNode[] nodes();
	public Boolean unlocked();
	public int index();
	public String id();
	public ResourceLocation background();
	public ResourceLocation icon();
	public Component name();
	public Component description();
}
