package dev.aprilvee.xiaoheic.capabilities;

import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.AutoRegisterCapability;

@AutoRegisterCapability
public class Cultivation {
    private float cultivation;
    private final float MIN_CULTIVATION_VALUE = 0;
    private final float MAX_CULTIVATION_VALUE = Float.MAX_VALUE;

    public float get(){
        return cultivation;
    }

    public void add(float add){
        this.cultivation = Math.min(cultivation + add, MAX_CULTIVATION_VALUE);
    }

    public void sub(float sub){
        this.cultivation = Math.max(cultivation - sub, MIN_CULTIVATION_VALUE);
    }

    public void copyFrom(Cultivation source){
        this.cultivation = source.cultivation;
    }

    public void saveNBTData(CompoundTag nbt){
        nbt.putFloat("cultivation", cultivation);
    }

    public void loadNBTData(CompoundTag nbt){
        cultivation = nbt.getFloat("cultivation");
    }
}
