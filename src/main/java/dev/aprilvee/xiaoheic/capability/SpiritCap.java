package dev.aprilvee.xiaoheic.capability;

import dev.aprilvee.xiaoheic.cultivation.cultivatemethods.EmptyMethod;
import dev.aprilvee.xiaoheic.cultivation.cultivatemethods.ICultivateMethod;
import dev.aprilvee.xiaoheic.cultivation.state.IState;
import dev.aprilvee.xiaoheic.data.Datalist;
import dev.aprilvee.xiaoheic.data.datatype.SType;
import dev.aprilvee.xiaoheic.spell.ICastable;
import dev.aprilvee.xiaoheic.spell.IPassiveSpell;
import dev.aprilvee.xiaoheic.spell.ISpell;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.AutoRegisterCapability;

import java.util.*;

@AutoRegisterCapability
public class SpiritCap {
    private int qi;
    private int maxqi;
    public int primalqi;
    public int maxprimalqi;

    private float maxqiX = 1; //these 5 are multipliers, be careful with them! strong!
    private float qiregen = 1;
    private float spelldamage = 1;
    private float spellresist = 1;
    private float spellcost = 1;

    private String affinity = "none";
    private String affinity2 = "none";
    private SType type = Datalist.notype;
    public IState state = Datalist.mortal;
    public ICultivateMethod currentcultivation = new EmptyMethod();
    public Set<ICultivateMethod> cultivationmethods = new HashSet<>();

    public Set<ISpell> unlockedspells = new HashSet<>();
    public Set<ISpell> accessiblespells = new HashSet<>();
    public List<IPassiveSpell> passivespells = new ArrayList<>();
    public ICastable[] selectedspells = {(ICastable) Datalist.fireball.getNew(), (ICastable) Datalist.qiball.getNew(), (ICastable) Datalist.empty, (ICastable) Datalist.empty, (ICastable) Datalist.empty, (ICastable) Datalist.empty};

    private float cultivation;
    private int elementlimit = 100;
    private int metalattunement;
    private int waterattunement;
    private int woodattunement;
    private int fireattunement;
    private int earthattunement;

    //unlocked mechanics section
    public boolean canCultivate = false;
    public boolean hasSpiritRealm = false;

    public int getQi(){
        return qi;
    }
    public int getMaxqi(){return maxqi;}
    public float getMaxqiX(){return maxqiX;}
    public float getQiregen(){return qiregen;}
    public float getSpelldamage(){return spelldamage;}
    public float getSpellresist(){return spellresist;}
    public float getSpellcost(){return spellcost;}

    public String getAffinty(){return affinity;}
    public String getAffinty2(){return affinity2;}
    public SType getType(){return type;}
    public float getCultivation(){return cultivation;}
    public int getMetal(){return metalattunement;}
    public int getWater(){return waterattunement;}
    public int getWood(){return woodattunement;}
    public int getFire(){return fireattunement;}
    public int getEarth(){return earthattunement;}
    public int getElementlimit(){return elementlimit;}
    public ISpell getSpellslot(int index){return selectedspells[index];}

    public int getQiRegen() {
        return( (int)((this.maxqi/160 + (Math.pow(this.maxqi,0.48)) )* this.qiregen) - 5 );
    }
    public int getSpellCost(ICastable spell){
        return (int) ((spell.getQiCost() + spell.getPerQiCost() * this.maxqi) * this.spellcost);
    }

    public void setQi(int set){
        this.qi = set;
    } //SYNC WITH CLIENT AFTER USING!!!!
    public void addQi(int add){this.qi = Math.min(qi + add, this.maxqi);} //SYNC WITH CLIENT AFTER USING!!!!
    public void subQi(int sub){
        this.qi = Math.max(qi - sub, 0);
    }

    public void setMaxQi(int set){ //SYNC WITH CLIENT AFTER USING!!!!
        this.maxqi = set;
    }
    public void addMaxQi(int add){ //SYNC WITH CLIENT AFTER USING!!!!!
        this.maxqi = Math.min(qi + add, Integer.MAX_VALUE);
    }
    public void subMaxQi(int sub){
        this.maxqi = Math.max(qi - sub, 0);
    } //SYNC WITH CLIENT AFTER USING!!!!

    public void setMaxqiX(float set){this.maxqiX = set;}
    public void setQiregen(float set){this.qiregen = set;}
    public void setSpelldamage(float set){this.spelldamage = set;}
    public void setSpellresist(float set){this.spellresist = set;}
    public void setSpellcost(float set){this.spellcost = set;}

    public void setAffinity(String affinity){
        this.affinity = affinity;
    }
    public void setAffinity2(String affinity){
        this.affinity2 = affinity2;
    }

    public void setType(SType type){
        this.type = type;
    }
    public boolean isType(SType type){return this.type == type;}

    public void setSelectedspell(ICastable type, int index) {
        this.selectedspells[index] = type;
    }

    public void setSpells(Collection<ISpell> spells){
        this.unlockedspells = new HashSet<>(spells);
    }
    public void addSpell(ISpell spell){ this.unlockedspells.add(spell);}
    public boolean hasSpell(ISpell spell){return this.unlockedspells.contains(spell);}

    public void setCultivation(float set){
        this.cultivation = set;
    }
    public void addCultivation(float add){
        this.cultivation = Math.min(cultivation + add, Integer.MAX_VALUE);
    }
    public void subCultivation(float sub){
        this.cultivation = Math.max(cultivation - sub, 0);
    }

    public void setMetal(int set){
        this.metalattunement = set;
    }
    public void addMetal(int add){
        this.metalattunement = Math.min(metalattunement + add, elementlimit);
    }
    public void subMetal(int sub){
        this.metalattunement = Math.max(metalattunement - sub, 0);
    }

    public void setWater(int set){
        this.waterattunement = set;
    }
    public void addWater(int add){
        this.waterattunement = Math.min(waterattunement + add, elementlimit);
    }
    public void subWater(int sub){
        this.waterattunement = Math.max(waterattunement - sub, 0);
    }

    public void setWood(int set){
        this.woodattunement = set;
    }
    public void addWood(int add){
        this.woodattunement = Math.min(woodattunement + add, elementlimit);
    }
    public void subWood(int sub){
        this.woodattunement = Math.max(woodattunement - sub, 0);
    }

    public void setFire(int set){
        this.fireattunement = set;
    }
    public void addFire(int add){
        this.fireattunement = Math.min(fireattunement + add, elementlimit);
    }
    public void subFire(int sub){
        this.fireattunement = Math.max(fireattunement - sub, 0);
    }

    public void setEarth(int set){
        this.earthattunement = set;
    }
    public void addEarth(int add){
        this.earthattunement = Math.min(earthattunement + add, elementlimit);
    }
    public void subEarth(int sub){
        this.earthattunement = Math.max(earthattunement - sub, 0);
    }

    public void setElementlimit(int set){
        this.elementlimit = set;
    }
    public void addElementLimit(int add){
        this.elementlimit = Math.min(elementlimit + add, 1000);
    }
    public void subElementLimit(int sub){
        this.elementlimit = Math.max(elementlimit - sub, 0);
    }


    public void copyFrom(SpiritCap source){
        this.qi = source.qi;
        this.maxqi = source.maxqi;
        this.primalqi = source.primalqi;
        this.maxprimalqi = source.maxprimalqi;

        this.maxqiX = source.maxqiX;
        this.qiregen = source.qiregen;
        this.spelldamage = source.spelldamage;
        this.spellresist = source.spellresist;
        this.spellcost = source.spellcost;

        this.selectedspells = source.selectedspells;
        this.unlockedspells = source.unlockedspells;
        this.cultivationmethods = source.cultivationmethods;

        this.canCultivate = source.canCultivate;
        this.hasSpiritRealm = source.hasSpiritRealm;

        this.cultivation = source.cultivation;
        this.metalattunement = source.metalattunement;
        this.waterattunement = source.waterattunement;
        this.woodattunement = source.woodattunement;
        this.fireattunement = source.fireattunement;
        this.earthattunement = source.earthattunement;
        this.elementlimit = source.elementlimit;

        this.affinity = source.affinity;
        this.affinity2 = source.affinity2;
        this.type = source.type;
        this.state = source.state;
    }
    public void saveNBTData(CompoundTag nbt){
        nbt.putInt("qi", qi);
        nbt.putInt("maxqi", maxqi);

        nbt.putString("affinity", affinity);
        nbt.putString("affinity2", affinity2);
        nbt.putInt("type", type.index);
        nbt.putInt("state", state.getIndex());

        nbt.putBoolean("cancultivate",canCultivate);
        nbt.putBoolean("hasspiritrealm",hasSpiritRealm);

        nbt.putFloat("maxqiX", maxqiX);
        nbt.putFloat("qiregen", qiregen);
        nbt.putFloat("spelldamage", spelldamage);
        nbt.putFloat("spellresist", spellresist);
        nbt.putFloat("spellcost", spellcost);

        nbt.putFloat("cultivation", cultivation);
        nbt.putInt("metalattunement", metalattunement);
        nbt.putInt("waterattunement", waterattunement);
        nbt.putInt("woodattunement", woodattunement);
        nbt.putInt("fireattunement", fireattunement);
        nbt.putInt("earthattunement", earthattunement);
        nbt.putInt("elementlimit", elementlimit);

        nbt.putInt("unlockedspellsize", unlockedspells.size());
        nbt.putInt("accessiblespellsize", accessiblespells.size());
        nbt.putInt("cultivationmethodsize", cultivationmethods.size());

        for(int i = 0; i<selectedspells.length;i++){
            nbt.putInt("selectedspell" + i,selectedspells[i].getIndex());
        }

        List<ISpell> spells = unlockedspells.stream().toList();
        for(int i = 0; i<spells.size();i++){
            nbt.putInt("unlockedspell" + i,spells.get(i).getIndex());
        }
        List<ISpell> accspells = accessiblespells.stream().toList();
        for(int i = 0; i<accspells.size();i++){
            nbt.putInt("accessiblespell" + i,accspells.get(i).getIndex());
        }
        List<ICultivateMethod> cmethods = cultivationmethods.stream().toList();
        for(int i = 0; i<cmethods.size();i++){
            nbt.putInt("cultivationmethod" + i,cmethods.get(i).getIndex());
        }
    }

    public void loadNBTData(CompoundTag nbt){
        qi = nbt.getInt("qi");
        maxqi = nbt.getInt("maxqi");

        affinity = nbt.getString("affinity");
        affinity2 = nbt.getString("affinity2");
        type = Datalist.types[nbt.getInt("type")];
        state = Datalist.states[nbt.getInt("state")];

        canCultivate = nbt.getBoolean("cancultivate");
        hasSpiritRealm = nbt.getBoolean("hasspiritrealm");

        maxqiX = nbt.getFloat("maxqiX");
        qiregen = nbt.getFloat("qiregen");
        spelldamage = nbt.getFloat("spelldamage");
        spellresist = nbt.getFloat("spellresist");
        spellcost = nbt.getFloat("spellcost");

        cultivation = nbt.getFloat("cultivation");
        metalattunement = nbt.getInt("metalattunement");
        waterattunement = nbt.getInt("waterattunement");
        woodattunement = nbt.getInt("woodattunement");
        fireattunement = nbt.getInt("fireattunement");
        earthattunement = nbt.getInt("earthattunement");
        elementlimit = nbt.getInt("elementlimit");

        for(int i = 0; i< nbt.getInt("unlockedspellsize") ;i++){
            unlockedspells.add( Datalist.spells[nbt.getInt("unlockedspell" + i)].getNew());
            //load misc data on spell
        }

        List<ISpell> unlspells = unlockedspells.stream().toList();
        for(int i = 0; i<6;i++){
            for(int e=0;e<unlockedspells.size();e++){
                if(unlspells.get(e).getIndex() == nbt.getInt("selectedspell"+i)){ //syncing selected and unlocked spells
                    selectedspells[i] = (ICastable) unlspells.get(e);
                }
            }
        }

        for(int i = 0; i< nbt.getInt("accessiblespellsize") ;i++){
            accessiblespells.add( Datalist.spells[nbt.getInt("accessiblespell" + i)] );
        }

        for(int i = 0; i< nbt.getInt("cultivationmethodsize") ;i++){
            cultivationmethods.add( Datalist.cultivationmethods[nbt.getInt("cultivationmethod" + i)]);
        }

    }
}
