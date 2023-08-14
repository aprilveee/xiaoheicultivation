package dev.aprilvee.xiaoheic.data.datatype;

import net.minecraft.network.chat.Component;

public interface IType {
    public int getIndex();
    public String getId();
    public int getColor();
    public Component getName();

}
