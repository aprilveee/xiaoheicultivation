package dev.aprilvee.xiaoheic.data.datatype;

import net.minecraft.network.chat.Component;

public class SpellSlot {
    public int index;
    public Component name;
    public String id;
    public int qiCost;
    public float pQiCost;
    public int sustaintick;

    public Element element;
    public SType type;

    public boolean isSpell;
    public CastType casttype;
    public boolean offensive;

}
