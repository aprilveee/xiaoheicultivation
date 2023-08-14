package dev.aprilvee.xiaoheic.data.state;

import dev.aprilvee.xiaoheic.capability.SpiritCap;
import dev.aprilvee.xiaoheic.capability.SpiritProvider;
import dev.aprilvee.xiaoheic.data.Datalist;
import dev.aprilvee.xiaoheic.data.datatype.IState;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

public class AttunementState implements IState {
    public int index = 2;
    public Component name = null;
    public String id = "attunement";
    public int limit = 100;

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
    public boolean isFinalstate() {
        return false;
    }
}
