package dev.aprilvee.xiaoheic.spell;

import dev.aprilvee.xiaoheic.capability.SpiritCap;
import dev.aprilvee.xiaoheic.cultivation.type.IType;
import dev.aprilvee.xiaoheic.data.Element;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;

public interface ISpell {
	public Element element();
	public IType type();
	public boolean canUnlock();
	public int getIndex(); //MAKE SURE INDEX IS EQUAL TO THE SPELL'S INDEX IN DATALIST.SPELLS OR YOUR WORLD WILL CRASH FOREVER
	public String getId();
	public Component getName();
	public Component getDescription();
	public void saveNBT(CompoundTag nbt, SpiritCap sp);
	public void loadNBT(CompoundTag nbt);
	public ISpell getNew();
}
