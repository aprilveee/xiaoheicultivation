package dev.aprilvee.xiaoheic.data.spell;

import dev.aprilvee.xiaoheic.data.datatype.CastType;
import dev.aprilvee.xiaoheic.data.datatype.Element;
import dev.aprilvee.xiaoheic.data.datatype.ICastable;
import dev.aprilvee.xiaoheic.data.datatype.SType;
import net.minecraft.network.chat.Component;

public class EmptySpell implements ICastable {
	@Override
	public void castSpell() {

	}

	@Override
	public CastType getCasttype() {
		return null;
	}

	@Override
	public Element element() {
		return null;
	}

	@Override
	public SType type() {
		return null;
	}

	@Override
	public boolean keybindable() {
		return false;
	}

	@Override
	public boolean isSpell() {
		return false;
	}

	@Override
	public int getIndex() {
		return 0;
	}

	@Override
	public String getId() {
		return "empty";
	}

	@Override
	public Component getName() {
		return null;
	}
}
