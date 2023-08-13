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

        if(sp.getCultivation() >= sp.state.limit){
            if(sp.state.hasLimit){
                if(limitBroken(sp, player.getCapability(StatisticsProvider.STATSCAP).orElse(null))){
                    breakLimit(sp, player);
                }else{
                    sp.setCultivation(sp.state.limit);
                }
            }else{
                breakLimit(sp, player);
            }
        }

    }

    public static boolean checkLimit(Player player){ //call whenever a limit condition changes
        SpiritCap sp = player.getCapability(SpiritProvider.SPIRITCAP).orElse(null);
        StatisticsCap stats = player.getCapability(StatisticsProvider.STATSCAP).orElse(null);

        if(sp.getCultivation() >= sp.state.limit && sp.state.hasLimit){
            if(limitBroken(sp, stats)){breakLimit(sp, player);
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
                return false;
            default:
                throw new IllegalStateException("Unexpected state value: " + sp.state.id);
        }
    }
    //call *ONLY* on advancing state
    public static void newStateReward(SpiritCap sp, Player player){
        switch(sp.state.id){ //should add qi and enable mechanics, do qi later
            case "sprite": sp.canCast = true;
            case "attunement": sp.canCultivate = true;
            case "realmshaping":
            default:
                throw new IllegalStateException("Unexpected state value: " + sp.state.id);

        }

    }

    public static void breakLimit(SpiritCap sp, Player player){
        sp.state = DataList.states[sp.state.index+1];
        newStateReward(sp, player);
    }

}
