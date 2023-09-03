package dev.aprilvee.xiaoheic.spell;

import dev.aprilvee.xiaoheic.data.datatype.Element;
import dev.aprilvee.xiaoheic.data.datatype.SType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;

public interface ISpell {
	public Element element();
	public SType type();
	public boolean canUnlock();
	public int getIndex(); //MAKE SURE INDEX IS EQUAL TO THE SPELL'S INDEX IN DATALIST.SPELLS OR YOUR WORLD WILL CRASH FOREVER
	public String getId();
	public Component getName();
	public void saveNBT(CompoundTag nbt);
	public void loadNBT(CompoundTag nbt);
	public ISpell getNew();
}
