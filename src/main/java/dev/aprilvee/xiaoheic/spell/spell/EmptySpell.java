package dev.aprilvee.xiaoheic.spell.spell;

import dev.aprilvee.xiaoheic.data.datatype.*;
import dev.aprilvee.xiaoheic.entity.BasicSpell;
import dev.aprilvee.xiaoheic.spell.IProjectileSpell;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;

public class EmptySpell implements IProjectileSpell {

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

	@Override
	public void basicProjTick(BasicSpell spell) {
		spell.discard();
	}

	@Override
	public void entityHit(Entity target, Entity caster, BasicSpell spell) {
		spell.discard();
	}

	@Override
	public void blockHit(BlockPos pos, Entity caster, BasicSpell spell) {
		spell.discard();
	}
}
