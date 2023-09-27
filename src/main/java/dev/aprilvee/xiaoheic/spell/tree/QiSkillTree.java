package dev.aprilvee.xiaoheic.spell.tree;

import dev.aprilvee.xiaoheic.capability.SpiritCap;
import dev.aprilvee.xiaoheic.capability.SpiritProvider;
import dev.aprilvee.xiaoheic.spell.node.ITreeNode;
import dev.aprilvee.xiaoheic.spell.node.PlaceholderNode;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;

public class QiSkillTree implements ISkillTree{
	@Override
	public void onUnlock(Player player) {
		player.getCapability(SpiritProvider.SPIRITCAP).ifPresent(sp -> {
			sp.skilltrees.add(this);
		});
	}

	@Override
	public ITreeNode[] nodes() {
		return new ITreeNode[]{new PlaceholderNode()};
	}

	@Override
	public boolean unlocked(Player player) {
		SpiritCap sp = player.getCapability(SpiritProvider.SPIRITCAP).orElse(null);
		return sp.state.getIndex() > 0;
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
	public ResourceLocation background() {
		return null;
	}

	@Override
	public ResourceLocation icon() {
		return null;
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
