package dev.aprilvee.xiaoheic.capabilities;

import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.AutoRegisterCapability;

@AutoRegisterCapability
public class MaxQi {
    public int maxqi;
    private final int MIN_MAXQI = 0;
    private final int MAX_MAXQI = Integer.MAX_VALUE;

    public int getMaxQi(){
        return maxqi;
    }

    public void setMaxQi(int set){
        this.maxqi = set;
    }

    public void addMaxQi(int add){
        this.maxqi = Math.min(maxqi + add, MAX_MAXQI);
    }

    public void subMaxQi(int sub){
        this.maxqi = Math.max(maxqi - sub, MIN_MAXQI);
    }

    public void copyFrom(MaxQi source){
        this.maxqi = source.maxqi;
    }

    public void saveNBTData(CompoundTag nbt){
        nbt.putInt("maxqi", maxqi);
    }

    public void loadNBTData(CompoundTag nbt){
        maxqi = nbt.getInt("maxqi");
    }
}
