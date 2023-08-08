package dev.aprilvee.xiaoheic.client;

import dev.aprilvee.xiaoheic.data.DataList;
import dev.aprilvee.xiaoheic.data.datatype.SpellSlot;
import dev.aprilvee.xiaoheic.data.datatype.SpellType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@OnlyIn(Dist.CLIENT)
public class ClientCapData {
    private static int qi;
    private static int maxqi;

    private static float cultivation;
    private static float metalattunement;
    private static float waterattunement;
    private static float woodattunement;
    private static float fireattunement;
    private static float earthattunement;

    private static Set<SpellSlot> unlockedspells = new HashSet<>();
    private static SpellSlot[] selectedspells = {DataList.fireball, DataList.snowshot, DataList.empty, DataList.empty, DataList.empty, DataList.empty};

    public static int getQi(){
        return qi;
    }
    public static int getMaxqi(){return maxqi;}
    public static float getCultivation(){return cultivation;}
    public static float getMetal(){return metalattunement;}
    public static float getWater(){return waterattunement;}
    public static float getWood(){return woodattunement;}
    public static float getFire(){return fireattunement;}
    public static float getEarth(){return earthattunement;}


    public static void setSelectedspell(SpellType type, int index) {
        selectedspells[index] = type;
    }
    public static SpellSlot getSelectedSpell(int index){
        return selectedspells[index];
    }

    public static void setSpells(Collection<SpellType> spells){
        unlockedspells = new HashSet<>(spells);
    }
    public static void addSpell(SpellType spell){ unlockedspells.add(spell);}
    public static boolean hasSpell(SpellType spell){return unlockedspells.contains(spell);}

    public static void setQi(int input){ClientCapData.qi = input;}
    public static void setMaxqi(int set){
        ClientCapData.maxqi = set;
    }
    public static void setCultivation(float set){
        ClientCapData.cultivation = set;
    }
    public static void setMetal(float set){
        ClientCapData.metalattunement = set;
    }
    public static void setWater(float set){
        ClientCapData.waterattunement = set;
    }
    public static void setWood(float set){
        ClientCapData.woodattunement = set;
    }
    public static void setFire(float set){
        ClientCapData.fireattunement = set;
    }
    public static void setEarth(float set){
        ClientCapData.earthattunement = set;
    }



}
