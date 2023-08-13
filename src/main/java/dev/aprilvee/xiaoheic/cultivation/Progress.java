package dev.aprilvee.xiaoheic.cultivation;

import dev.aprilvee.xiaoheic.capability.SpiritCap;
import dev.aprilvee.xiaoheic.capability.SpiritProvider;
import dev.aprilvee.xiaoheic.data.datatype.SpellSlot;
import net.minecraft.world.entity.player.Player;

public class Progress {

    public static void unlockSpell(SpellSlot spell, Player player){
        SpiritCap sp = player.getCapability(SpiritProvider.SPIRITCAP).orElse(null);
        sp.unlockedspells.add(spell);
    }
}
