package dev.aprilvee.xiaoheic.cultivation;

import dev.aprilvee.xiaoheic.capability.SpiritCap;
import dev.aprilvee.xiaoheic.capability.SpiritProvider;
import dev.aprilvee.xiaoheic.data.Datalist;
import dev.aprilvee.xiaoheic.data.states.SpriteState;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

public class Cultivation {

    public static void cultivate(Player player){
        //idk how tihs works yet do tihs later
        //todo: decide on how cultivation score works
        //is it all at once? or upon succeeding in the minigame? and what will the minigame even be?
        //perhaps there's different methods of cultivation one can learn? maybe they have different minigames and scores?
    }

    public static void addCXP(Player player, int amt){ //add cultivation xp
        SpiritCap sp = player.getCapability(SpiritProvider.SPIRITCAP).orElse(null);
        sp.addCultivation(amt);
        checkLimit(player);
    }

    public static void checkLimit(Player player){ // IMPORTANT **call every time a limit condition changes** IMPORTANT
        SpiritCap sp = player.getCapability(SpiritProvider.SPIRITCAP).orElse(null);
        if(sp.state.limitBroken(player)){
            advanceState(sp, player);
        }
    }

    public static void advanceState(SpiritCap sp, Player player){
        sp.state = sp.state.advanceState();
        player.sendSystemMessage(Component.literal(sp.state.getId()));
        sp.state.stateReached(player);
    }

}
