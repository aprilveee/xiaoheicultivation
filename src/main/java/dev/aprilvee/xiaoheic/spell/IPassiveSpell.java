package dev.aprilvee.xiaoheic.spell;

import net.minecraft.world.entity.player.Player;

public interface IPassiveSpell extends ISpell { //used for passive and sustain spells, the difference of which is sustain are castable
	public void tick(Player player);
	public void activate(Player player);
	public void deactivate(Player player);
	public boolean active();
	public boolean shouldSustain();
	public boolean toggleable(Player player);
}
