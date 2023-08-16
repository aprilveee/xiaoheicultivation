package dev.aprilvee.xiaoheic.data.datatype;

import dev.aprilvee.xiaoheic.data.Datalist;
import dev.aprilvee.xiaoheic.entity.BasicSpell;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;

public interface ICastable extends ISpell { //todo: change to ISpell for all spells and have ICastable for just castable spells instead
    public void castSpell(Player player);
    public CastType getCasttype();
    public boolean keybindable();
    public boolean isSpell();


}
