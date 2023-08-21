package dev.aprilvee.xiaoheic.cultivation.cultivatemethods;

import dev.aprilvee.xiaoheic.client.gui.AbstractCultivationScreen;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.entity.player.Player;

public interface ICultivateMethod { //do not use like other stuff in datalist, you need to instantiate a new one for each use
	public void startCultivation(Player player);
	public void stopCultivation(Player player);
	public void minigameTick(Player player);
	public void guiTick(Player player, GuiGraphics gui);
	public AbstractCultivationScreen createScreen();
	public void receiveInput(KeyMapping key);
	public void succeed(Player player);
	public void fail(Player player);
	public void win(Player player);
	public void lose(Player player);
	public float getScore();
	public void addScore(float add);
	public ICultivateMethod create();
	public int getIndex();
}
