package dev.aprilvee.xiaoheic.cultivation.state;

import dev.aprilvee.xiaoheic.spell.ISpell;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

public interface IState {
    public boolean limitBroken(Player player);
    public void stateReached(Player player);
    public IState advanceState();
    public int getIndex();
    public String getId();
    public int getLimit();
    public ISpell[] getSpells();
    public Component getName();

}
