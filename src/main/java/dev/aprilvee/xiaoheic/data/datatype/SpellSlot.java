package dev.aprilvee.xiaoheic.data.datatype;

import dev.aprilvee.xiaoheic.data.Datalist;
import net.minecraft.network.chat.Component;

public abstract class SpellSlot {
    public int index;
    public Component name;
    public String id;
    public int qiCost;
    public float pQiCost;
    public int cooldown;
    public int chargetimer;
    public int sustaintick;

    public Element element = Element.NONE;
    public SType type = Datalist.notype;

    public boolean isSpell;
    public boolean holdable = false;
    public CastType casttype;
    public boolean offensive;

}
