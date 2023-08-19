package dev.aprilvee.xiaoheic.cultivation.state;

import dev.aprilvee.xiaoheic.capability.SpiritCap;
import dev.aprilvee.xiaoheic.capability.SpiritProvider;
import dev.aprilvee.xiaoheic.data.Datalist;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

public class RealmShapingState implements IState {
    public int index = 3;
    public Component name = null;
    public String id = "realmshaping";
    public int limit = 400;

    @Override
    public boolean limitBroken(Player player) {
        SpiritCap sp = player.getCapability(SpiritProvider.SPIRITCAP).orElse(null);
        return false;
    }

    @Override
    public void stateReached(Player player) {
        SpiritCap sp = player.getCapability(SpiritProvider.SPIRITCAP).orElse(null);
    }

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
        return true;
    }
}
