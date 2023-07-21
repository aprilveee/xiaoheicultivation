package dev.aprilvee.xiaoheic.capability;

import dev.aprilvee.xiaoheic.spell.SpellList;
import dev.aprilvee.xiaoheic.spell.SpellType;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.AutoRegisterCapability;

import java.util.Objects;

@AutoRegisterCapability
public class SpellCap {
    private SpellType type;



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
        nbt.putString("spelltype", type.name);

    }

    public void loadNBTData(CompoundTag nbt){
        for (int i = 0; i < SpellList.spells.length; i++) {
            if(Objects.equals(SpellList.spells[i].name, nbt.getString("spelltype"))){
                type = SpellList.spells[i];
            }
        }
    }
}
