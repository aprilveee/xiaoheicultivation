package dev.aprilvee.xiaoheic.cultivation.cultivatemethods;

import dev.aprilvee.xiaoheic.cultivation.ICultivateMethod;
import net.minecraft.world.entity.player.Player;

public class EmptyMethod implements ICultivateMethod {
	@Override
	public void startCultivation(Player player) {

	}

	@Override
	public void stopCultivation(Player player) {

	}

	@Override
	public void minigameTick(Player player) {

	}

	@Override
	public void succeed(Player player) {

	}

	@Override
	public void fail(Player player) {

	}

	@Override
	public void win(Player player) {

	}

	@Override
	public void lose(Player player) {

	}

	@Override
	public float getScore() {
		return 0;
	}

	@Override
	public void addScore(float add) {

	}

	@Override
	public ICultivateMethod create() {
		return new EmptyMethod();
	}
}
