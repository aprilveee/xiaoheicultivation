package dev.aprilvee.xiaoheic.cultivation.type;

import dev.aprilvee.xiaoheic.spell.tree.ISkillTree;
import net.minecraft.network.chat.Component;

public class NoType implements IType {
	@Override
	public void onPick() {

	}

	@Override
	public boolean unlocked() {
		return false;
	}

	@Override
	public ISkillTree skilltree() {
		return null;
	}

	@Override
	public int getIndex() {
		return 0;
	}

	@Override
	public String getId() {
		return null;
	}

	@Override
	public Component getName() {
		return null;
	}
}
