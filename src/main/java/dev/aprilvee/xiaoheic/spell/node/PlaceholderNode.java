package dev.aprilvee.xiaoheic.spell.node;

import dev.aprilvee.xiaoheic.capability.SpiritCap;
import dev.aprilvee.xiaoheic.capability.SpiritProvider;
import dev.aprilvee.xiaoheic.main;
import dev.aprilvee.xiaoheic.spell.tree.ISkillTree;
import dev.aprilvee.xiaoheic.spell.tree.QiSkillTree;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec2;

public class PlaceholderNode implements ITreeNode {
	@Override
	public void unlock(Player player) {

	}

	@Override
	public void onRemove(Player player) {

	}

	@Override
	public Boolean canUnlock(Player player) {
		SpiritCap sp = player.getCapability(SpiritProvider.SPIRITCAP).orElse(null);
		return sp.state.getIndex() > 0;
	}

	@Override
	public ISkillTree tree() {
		return new QiSkillTree();
	}

	@Override
	public Vec2 getPos() {
		return new Vec2(400,400);
	}

	@Override
	public int index() {
		return 0;
	}

	@Override
	public String id() {
		return null;
	}

	@Override
	public ResourceLocation texture() {
		return new ResourceLocation(main.MODID,"textures/gui/cultivation/nodeplaceholder.png");
	}

	@Override
	public Component name() {
		return null;
	}

	@Override
	public Component description() {
		return null;
	}
}
