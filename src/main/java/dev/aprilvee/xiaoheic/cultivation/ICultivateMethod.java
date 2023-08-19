package dev.aprilvee.xiaoheic.cultivation;

import net.minecraft.world.entity.player.Player;

public interface ICultivateMethod { //do not use like other stuff in datalist, you need to instantiate a new one for each use
	public void startCultivation(Player player);
	public void stopCultivation(Player player);
	public void minigameTick(Player player);
	public void succeed(Player player);
	public void fail(Player player);
	public void win(Player player);
	public void lose(Player player);
	public float getScore();
	public void addScore(float add);
	public ICultivateMethod create();
	public int getIndex();
}
