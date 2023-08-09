package dev.aprilvee.xiaoheic.capability;

import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.AutoRegisterCapability;

@AutoRegisterCapability
public class StatisticsCap {
    public int spriteCount;


    public void copyFrom(StatisticsCap source){
        this.spriteCount = source.spriteCount;
    }

    public void saveNBTData(CompoundTag nbt){
        nbt.putInt("spriteCount", spriteCount);
    }

    public void loadNBTData(CompoundTag nbt){
        spriteCount = nbt.getInt("spriteCount");
    }
}
