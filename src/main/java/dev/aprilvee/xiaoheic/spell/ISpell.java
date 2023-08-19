package dev.aprilvee.xiaoheic.spell;

import dev.aprilvee.xiaoheic.data.datatype.Element;
import dev.aprilvee.xiaoheic.data.datatype.SType;
import net.minecraft.network.chat.Component;

public interface ISpell {
	public Element element();
	public SType type();
	public int getIndex(); //MAKE SURE INDEX IS EQUAL TO THE SPELL'S INDEX IN DATALIST.SPELLS OR YOUR WORLD WILL CRASH FOREVER
	public String getId();
	public Component getName();
	public int getQiCost();
	public float getPerQiCost();
}
