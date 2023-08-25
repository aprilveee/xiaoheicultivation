package dev.aprilvee.xiaoheic.cultivation.state;

import dev.aprilvee.xiaoheic.capability.SpiritCap;
import dev.aprilvee.xiaoheic.capability.SpiritProvider;
import dev.aprilvee.xiaoheic.data.Datalist;
import dev.aprilvee.xiaoheic.network.Messages;
import dev.aprilvee.xiaoheic.network.packet.MaxQiS2C;
import dev.aprilvee.xiaoheic.spell.ISpell;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

public class SpriteState implements IState {
    public int index = 1;
    public Component name = null;
    public String id = "sprite";
    public int limit = 40;

    @Override
    public boolean limitBroken(Player player) {
        SpiritCap sp = player.getCapability(SpiritProvider.SPIRITCAP).orElse(null);
        return sp.getCultivation() >= limit;
    }

    @Override
    public void stateReached(Player player) {
        SpiritCap sp = player.getCapability(SpiritProvider.SPIRITCAP).orElse(null);
        sp.addMaxQi(50);
        Messages.sendToClient(new MaxQiS2C(sp.getMaxqi()), player.getServer().getPlayerList().getPlayer(player.getUUID()));

    }

    @Override
    public IState advanceState() {
        return Datalist.attunement;
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
        return new ISpell[0];
    }

}
