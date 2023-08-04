package dev.aprilvee.xiaoheic.spell;

import dev.aprilvee.xiaoheic.capability.SpiritCap;
import dev.aprilvee.xiaoheic.capability.SpiritProvider;
import dev.aprilvee.xiaoheic.data.datatype.SpellType;
import dev.aprilvee.xiaoheic.entity.BasicSpell;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;

public class FireSpell {

    public static void BasicSpell(ServerPlayer player, ServerLevel level, SpellType type){
        SpiritCap sp = player.getCapability(SpiritProvider.SPIRITCAP).orElse(null);

        int qicost = sp.getSpellCost(type);
        if(sp.getQi() >= qicost && sp.getMaxqi() >= qicost){

            sp.subQi(qicost);

            for(int i = 0; i < type.projcount; i++){
                BasicSpell spell = new BasicSpell(player.level(), player.getEyePosition(), type.index);

                spell.setOwner(player);
                spell.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, type.flyspeed, type.accuracy);
                level.addFreshEntity(spell);
            }

    }
    }
}
