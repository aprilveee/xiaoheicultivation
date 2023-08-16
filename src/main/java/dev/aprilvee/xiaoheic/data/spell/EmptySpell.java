package dev.aprilvee.xiaoheic.data.spell;

import dev.aprilvee.xiaoheic.data.datatype.*;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

public class EmptySpell implements ICastable{

	@Override
	public Element element() {
		return null;
	}

	@Override
	public SType type() {
		return null;
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

	@Override
	public int getQiCost() {
		return 0;
	}

	@Override
	public float getPerQiCost() {
		return 0;
	}

	@Override
	public void castSpell(Player player) {

	}

	@Override
	public CastType getCasttype() {
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
}
