package dev.aprilvee.xiaoheic.capabilities;

import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.AutoRegisterCapability;

@AutoRegisterCapability
public class QiValue {
    private int qi;
    private final int MIN_QI_VALUE = 0;
    private final int MAX_QI_VALUE = Integer.MAX_VALUE;

    public int getQi(){
        return qi;
    }

    public void set(int set){
        this.qi = set;
    }


    public void addQi(int add){
        this.qi = Math.min(qi + add, MAX_QI_VALUE);
    }

    public void subQi(int sub){
        this.qi = Math.max(qi - sub, MIN_QI_VALUE);
    }

    public void copyFrom(QiValue source){
        this.qi = source.qi;
    }

    public void saveNBTData(CompoundTag nbt){
        nbt.putInt("qi", qi);
    }

    public void loadNBTData(CompoundTag nbt){
        qi = nbt.getInt("qi");
    }
}
