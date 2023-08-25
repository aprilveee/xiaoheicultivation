package dev.aprilvee.xiaoheic.cultivation;

import dev.aprilvee.xiaoheic.capability.SpiritCap;
import dev.aprilvee.xiaoheic.capability.SpiritProvider;
import dev.aprilvee.xiaoheic.data.Datalist;
import dev.aprilvee.xiaoheic.spell.ISpell;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

import java.util.List;

public class Cultivation {

    public static void cultivate(Player player){
        //idk how tihs works yet do tihs later
        //is it all at once? or upon succeeding in the minigame? and what will the minigame even be?
        //perhaps there's different methods of cultivation one can learn? maybe they have different minigames and scores?
    }

    public static void addCXP(Player player, float amt){ //add cultivation xp
        SpiritCap sp = player.getCapability(SpiritProvider.SPIRITCAP).orElse(null);
        sp.addCultivation(amt);
        checkLimit(player);
        checkSpell(player);
    }

    public static void checkLimit(Player player){ // IMPORTANT **call every time a limit condition changes** IMPORTANT
        SpiritCap sp = player.getCapability(SpiritProvider.SPIRITCAP).orElse(null);
        if(sp.state.limitBroken(player)){
            advanceState(sp, player);
        }
    }

    public static void checkSpell(Player player){ // call every time a spell unlock condition changes
        SpiritCap sp = player.getCapability(SpiritProvider.SPIRITCAP).orElse(null);
        List<ISpell> accspells = sp.accessiblespells.stream().toList();
        for(int i =0;i<accspells.size();i++){
            if(accspells.get(i).unlock()){
                sp.unlockedspells.add( Datalist.spells[ accspells.get(i).getIndex() ] );
            }
        }
    }

    public static void advanceState(SpiritCap sp, Player player){
        sp.state = sp.state.advanceState();
        player.sendSystemMessage(Component.literal(sp.state.getId()));
        sp.state.stateReached(player);
    }

}
