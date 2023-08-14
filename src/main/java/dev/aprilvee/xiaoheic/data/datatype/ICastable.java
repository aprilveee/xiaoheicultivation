package dev.aprilvee.xiaoheic.data.datatype;

import dev.aprilvee.xiaoheic.data.Datalist;
import dev.aprilvee.xiaoheic.entity.BasicSpell;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;

public interface ICastable { //todo: change to ISpell for all spells and have ICastable for just castable spells instead
    //for offensive spells
    public void castSpell();

    public CastType getCasttype();
    public Element element();
    public SType type();
    public boolean keybindable();
    public boolean isSpell();

    public int getIndex();
    public String getId();
    public Component getName();

}
