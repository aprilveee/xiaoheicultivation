package dev.aprilvee.xiaoheic.cultivation;

import dev.aprilvee.xiaoheic.capability.SpiritCap;
import dev.aprilvee.xiaoheic.capability.SpiritProvider;
import dev.aprilvee.xiaoheic.data.Datalist;
import dev.aprilvee.xiaoheic.data.datatype.Element;
import dev.aprilvee.xiaoheic.spell.ISpell;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

import java.util.List;

public class Cultivation {

    public static void addCXP(Player player, float amt){ //add cultivation xp
        SpiritCap sp = player.getCapability(SpiritProvider.SPIRITCAP).orElse(null);
        sp.addCultivation(amt);
        //checkLimit(player);
        //checkSpell(player);
    }

    public static void addAttunement(Player player, int amt, Element element){
        SpiritCap sp = player.getCapability(SpiritProvider.SPIRITCAP).orElse(null);
        switch (element){
            case METAL -> sp.addMetal(amt);
            case WATER -> sp.addWater(amt);
            case WOOD -> sp.addWood(amt);
            case FIRE -> sp.addFire(amt);
            case EARTH -> sp.addEarth(amt);
        }
    }

    public static void checkLimit(Player player){ // call when opening ui and when player checks
        SpiritCap sp = player.getCapability(SpiritProvider.SPIRITCAP).orElse(null);
        if(sp.state.limitBroken(player)){
            advanceState(sp, player);
        }
    }

    @Deprecated //going to be changed to skill trees instead of autounlock
    public static void checkSpell(Player player){ // call every time a spell unlock condition changes
        SpiritCap sp = player.getCapability(SpiritProvider.SPIRITCAP).orElse(null);
        List<ISpell> accspells = sp.accessiblespells.stream().toList();
        for(int i =0;i<accspells.size();i++){
            if(accspells.get(i).canUnlock()){
                sp.unlockedspells.add( Datalist.spells[ accspells.get(i).getIndex() ].getNew() );
            }
        }
    }

    public static void advanceState(SpiritCap sp, Player player){
        sp.state = sp.state.advanceState();
        player.sendSystemMessage(Component.literal(sp.state.getId()));
        sp.state.stateReached(player);
    }

}
