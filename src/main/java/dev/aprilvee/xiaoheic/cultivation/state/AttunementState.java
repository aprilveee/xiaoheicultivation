package dev.aprilvee.xiaoheic.cultivation.state;

import dev.aprilvee.xiaoheic.capability.SpiritCap;
import dev.aprilvee.xiaoheic.capability.SpiritProvider;
import dev.aprilvee.xiaoheic.data.Datalist;
import dev.aprilvee.xiaoheic.spell.ISpell;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

import java.util.List;

public class AttunementState implements IState {
    public int index = 2;
    public Component name = null;
    public String id = "attunement";
    public int limit = 100;
    ISpell[] spells = new ISpell[]{Datalist.qiball}; //spells that can be unlocked in this stage
    //upon progressing to this stage, add these to accessible spells

    @Override
    public boolean limitBroken(Player player) {
        SpiritCap sp = player.getCapability(SpiritProvider.SPIRITCAP).orElse(null);
        return sp.getCultivation() >= limit &&
        sp.getMetal() >= 100 | sp.getWater() >= 100 | sp.getWood() >= 100 | sp.getFire() >= 100 | sp.getEarth() >= 100;
    }

    @Override
    public void stateReached(Player player) {
        SpiritCap sp = player.getCapability(SpiritProvider.SPIRITCAP).orElse(null);
        sp.canCultivate = true;
        sp.accessiblespells.addAll(List.of(this.spells));
    }

    @Override
    public IState advanceState() {
        return Datalist.realmshaping;
    }

    @Override
    public int getIndex() {
        return index;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public Component getName() {
        return name;
    }

    @Override
    public int getLimit() {
        return limit;
    }

    @Override
    public ISpell[] getSpells() {
        return spells;
    }

}
