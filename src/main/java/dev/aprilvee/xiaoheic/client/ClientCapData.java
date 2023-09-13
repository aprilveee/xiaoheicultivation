package dev.aprilvee.xiaoheic.client;

import dev.aprilvee.xiaoheic.data.datatype.SpellSlot;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.HashSet;
import java.util.Set;

@OnlyIn(Dist.CLIENT)
public class ClientCapData {
    public static int qi;
    public static int maxqi;

    public static float cultivation;
    public static float metalattunement;
    public static float waterattunement;
    public static float woodattunement;
    public static float fireattunement;
    public static float earthattunement;

    public static Set<SpellSlot> unlockedspells = new HashSet<>();

    public static int getQi() {
        return qi;
    }

    public static int getMaxqi() {
        return maxqi;
    }

    public static float getCultivation() {
        return cultivation;
    }

    public static float getMetal() {
        return metalattunement;
    }

    public static float getWater() {
        return waterattunement;
    }

    public static float getWood() {
        return woodattunement;
    }

    public static float getFire() {
        return fireattunement;
    }

    public static float getEarth() {
        return earthattunement;
    }

}
