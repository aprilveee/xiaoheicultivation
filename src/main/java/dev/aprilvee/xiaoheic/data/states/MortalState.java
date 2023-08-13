package dev.aprilvee.xiaoheic.data.states;

import dev.aprilvee.xiaoheic.data.Datalist;
import dev.aprilvee.xiaoheic.data.datatype.IState;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

public class MortalState implements IState {
    public int index = 0;
    public Component name = null;
    public String id = "mortal";
    public int limit = 1;

    @Override
    public boolean limitBroken(Player player) {
        return true;
    }

    @Override
    public void stateReached(Player player) {
        player.sendSystemMessage(Component.literal("mortal state reached?"));
    }

    @Override
    public IState advanceState() {
        return Datalist.sprite;
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
