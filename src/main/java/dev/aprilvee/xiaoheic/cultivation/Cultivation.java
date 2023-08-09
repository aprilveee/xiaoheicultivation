package dev.aprilvee.xiaoheic.cultivation;

import dev.aprilvee.xiaoheic.capability.SpiritCap;
import dev.aprilvee.xiaoheic.capability.SpiritProvider;
import dev.aprilvee.xiaoheic.capability.StatisticsProvider;
import dev.aprilvee.xiaoheic.capability.StatisticsCap;
import dev.aprilvee.xiaoheic.data.DataList;
import net.minecraft.world.entity.player.Player;

public class Cultivation {

    public static void addCXP(Player player, int amt){ //cultivation xp
        SpiritCap sp = player.getCapability(SpiritProvider.SPIRITCAP).orElse(null);

        sp.addCultivation(amt);
        checkLimit(player);

        if(sp.getCultivation() > sp.state.limit){
            if(sp.state.hasLimit){
                sp.setCultivation(sp.state.limit);
            }else{
                breakLimit(sp);
            }
        }

    }

    public static boolean checkLimit(Player player){ //call whenever a limit condition changes
        SpiritCap sp = player.getCapability(SpiritProvider.SPIRITCAP).orElse(null);
        StatisticsCap stats = player.getCapability(StatisticsProvider.STATSCAP).orElse(null);

        if(sp.getCultivation() >= sp.state.limit && sp.state.hasLimit){
            if(limitBroken(sp, stats)){breakLimit(sp);
            return true;}
        }
        return false;
    }

    public static boolean limitBroken(SpiritCap sp, StatisticsCap stat){ //holds all limit conditions
        switch(sp.state.id){
            case "sprite": return stat.spriteCount >= 40;
            case "attunement":
                return sp.getMetal() >= 100 || sp.getWater() >= 100 || sp.getWood() >= 100 || sp.getFire() >= 100 || sp.getEarth() >= 100;
            case "realmshaping":
        }

        return false;
    }

    public static void breakLimit(SpiritCap sp){
        sp.state = DataList.states[sp.state.index+1];
    }

}
