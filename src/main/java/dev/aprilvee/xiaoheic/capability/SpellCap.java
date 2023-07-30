package dev.aprilvee.xiaoheic.capability;

import dev.aprilvee.xiaoheic.data.DataList;
import dev.aprilvee.xiaoheic.data.datatype.SpellType;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.AutoRegisterCapability;

import java.util.Objects;

@AutoRegisterCapability
public class SpellCap {
    public SpellType type;

    public SpellType getType(){
        return type;
    }

    public void setType(SpellType type){
        this.type = type;
    }

    public void copyFrom(SpellCap source){
        this.type = source.type;
    }

    public void saveNBTData(CompoundTag nbt){
        nbt.putString("spelltype", type.id);

    }

    public void loadNBTData(CompoundTag nbt){
        for (int i = 0; i < DataList.spells.length; i++) {
            if(Objects.equals(DataList.spells[i].id, nbt.getString("spelltype"))){
                type = DataList.spells[i];
            }
        }
    }
}
