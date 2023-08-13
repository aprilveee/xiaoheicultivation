package dev.aprilvee.xiaoheic.cultivation;

import dev.aprilvee.xiaoheic.capability.SpiritCap;
import dev.aprilvee.xiaoheic.capability.SpiritProvider;
import dev.aprilvee.xiaoheic.capability.StatisticsProvider;
import dev.aprilvee.xiaoheic.capability.StatisticsCap;
import dev.aprilvee.xiaoheic.data.DataList;
import dev.aprilvee.xiaoheic.network.Messages;
import dev.aprilvee.xiaoheic.network.packet.MaxQiS2C;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

public class Cultivation {

    public static void cultivate(Player player, BlockPos pos){
        //idk how tihs works yet do tihs later
        //todo: decide on how cultivation score works
        //is it all at once? or upon succeeding in the minigame? and what will the minigame even be?
        //perhaps there's different methods of cultivation one can learn? maybe they have different minigames and scores?
    }

    public static void addCXP(Player player, int amt){ //add cultivation xp
        SpiritCap sp = player.getCapability(SpiritProvider.SPIRITCAP).orElse(null);

        sp.addCultivation(amt);
        player.sendSystemMessage(Component.literal(sp.state.id));

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
        switch (sp.state.id) { //should add qi and enable mechanics, do qi later
            case "sprite" -> { //this is the reward upon entering the state!
                sp.addMaxQi(50);
                Messages.sendToClient(new MaxQiS2C(sp.getMaxqi()), player.getServer().getPlayerList().getPlayer(player.getUUID()));
                player.sendSystemMessage(Component.literal("sprite state entered!"));
            }
            case "attunement" -> sp.canCultivate = true;
            case "realmshaping" -> sp.addElementLimit(150);
            default -> {
            }
        }

    }

    public static void breakLimit(SpiritCap sp, Player player){
        sp.state = DataList.states[sp.state.index+1];
        newStateReward(sp, player);
    }

}
