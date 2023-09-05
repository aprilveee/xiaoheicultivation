package dev.aprilvee.xiaoheic.skilltree;

import dev.aprilvee.xiaoheic.spell.ISpell;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec2;

public interface ITreeNode {
	public ISpell[] getSpell();
	public Boolean unlocked();
	public Vec2 getPos();
	public int index();
	public String id();
	public ResourceLocation texture();
	public Component name();
	public Component description();
}
