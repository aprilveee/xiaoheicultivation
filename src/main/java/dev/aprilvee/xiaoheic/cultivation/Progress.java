package dev.aprilvee.xiaoheic.cultivation;

import dev.aprilvee.xiaoheic.capability.SpiritCap;
import dev.aprilvee.xiaoheic.capability.SpiritProvider;
import dev.aprilvee.xiaoheic.spell.ISpell;
import dev.aprilvee.xiaoheic.data.datatype.SType;
import net.minecraft.world.entity.player.Player;

@Deprecated
public class Progress {
    public static void unlockSpell(ISpell spell, Player player){
        SpiritCap sp = player.getCapability(SpiritProvider.SPIRITCAP).orElse(null);
        sp.unlockedspells.add(spell);
    }

    public static void chooseType(SType type, Player player){
        SpiritCap sp = player.getCapability(SpiritProvider.SPIRITCAP).orElse(null);
        sp.setType(type);
        Cultivation.checkLimit(player);
    }
}
