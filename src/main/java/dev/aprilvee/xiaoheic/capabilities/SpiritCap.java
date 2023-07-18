package dev.aprilvee.xiaoheic.capabilities;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.common.capabilities.AutoRegisterCapability;

@AutoRegisterCapability
public class SpiritCap {
    private int qi;
    private int maxqi;

    private float cultivation;
    private float metalaspect;
    private float wateraspect;
    private float woodaspect;
    private float fireaspect;
    private float earthaspect;

    private float elementLimit = 500;
    private final int MIN_QI_VALUE = 0;
    private final int MAX_QI_VALUE = Integer.MAX_VALUE;

    public int getQi(){
        return qi;
    }
    public int getMaxqi(){return maxqi;}
    public float getCultivation(){return cultivation;}
    public float getMetal(){return metalaspect;}
    public float getWater(){return wateraspect;}
    public float getWood(){return woodaspect;}
    public float getFire(){return fireaspect;}
    public float getEarth(){return earthaspect;}


    public void setQi(int set){
        this.qi = set;
    }
    public void addQi(int add){
        this.qi = Math.min(qi + add, Integer.MAX_VALUE);
    }
    public void subQi(int sub){
        this.qi = Math.max(qi - sub, 0);
    }

    public void setMaxqi(int set){
        this.maxqi = set;
    }
    public void addMaxQi(int add){
        this.maxqi = Math.min(qi + add, Integer.MAX_VALUE);
    }
    public void subMaxQi(int sub){
        this.maxqi = Math.max(qi - sub, 0);
    }

    public void setCultivation(float set){
        this.cultivation = set;
    }
    public void addCultivation(float add){
        this.cultivation = Math.min(cultivation + add, Float.MAX_VALUE);
    }
    public void subCultivation(float sub){
        this.cultivation = Math.max(cultivation - sub, 0);
    }

    public void setMetal(float set){
        this.metalaspect = set;
    }
    public void addMetal(float add){
        this.metalaspect = Math.min(metalaspect + add, elementLimit);
    }
    public void subMetal(float sub){
        this.metalaspect = Math.max(metalaspect - sub, 0);
    }

    public void setWater(float set){
        this.wateraspect = set;
    }
    public void addWater(float add){
        this.wateraspect = Math.min( + add, elementLimit);
    }
    public void subWater(float sub){
        this.wateraspect = Math.max( - sub, 0);
    }

    public void setWood(float set){
        this.woodaspect = set;
    }
    public void addWood(float add){
        this.woodaspect = Math.min( + add, elementLimit);
    }
    public void subWood(float sub){
        this.woodaspect = Math.max( - sub, 0);
    }

    public void setFire(float set){
        this.fireaspect = set;
    }
    public void addFire(float add){
        this.fireaspect = Math.min( + add, elementLimit);
    }
    public void subFire(float sub){
        this.fireaspect = Math.max( - sub, 0);
    }

    public void setEarth(float set){
        this.earthaspect = set;
    }
    public void addEarth(float add){
        this.earthaspect = Math.min( + add, elementLimit);
    }
    public void subEarth(float sub){
        this.earthaspect = Math.max( - sub, 0);
    }


    public void copyFrom(SpiritCap source){
        this.qi = source.qi;
        this.maxqi = source.maxqi;
        this.cultivation = source.cultivation;
        this.metalaspect = source.metalaspect;
        this.wateraspect = source.wateraspect;
        this.woodaspect = source.woodaspect;
        this.fireaspect = source.fireaspect;
        this.earthaspect = source.earthaspect;
    }

    public void saveNBTData(CompoundTag nbt){
        nbt.putInt("qi", qi);
        nbt.putInt("maxqi", maxqi);
        nbt.putFloat("cultivation", cultivation);
        nbt.putFloat("metalaspect", metalaspect);
        nbt.putFloat("wateraspect", wateraspect);
        nbt.putFloat("woodaspect", woodaspect);
        nbt.putFloat("fireaspect", fireaspect);
        nbt.putFloat("earthaspect", earthaspect);

    }

    public void loadNBTData(CompoundTag nbt){
        qi = nbt.getInt("qi");
        maxqi = nbt.getInt("maxqi");
        cultivation = nbt.getFloat("cultivation");
        metalaspect = nbt.getFloat("metalaspect");
        wateraspect = nbt.getFloat("wateraspect");
        woodaspect = nbt.getFloat("woodaspect");
        fireaspect = nbt.getFloat("fireaspect");
        earthaspect = nbt.getFloat("earthaspect");
    }
}
