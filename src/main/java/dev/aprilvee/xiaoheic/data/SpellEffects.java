package dev.aprilvee.xiaoheic.data;

import dev.aprilvee.xiaoheic.data.datatype.SpellType;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;

public class SpellEffects {

    public static void fireballEntity(Entity caster, Entity target) {
        target.setSecondsOnFire(3);
        target.hurt(target.damageSources().onFire(),5F);
    }
    public static void fireballBlock(Entity caster, BlockPos pos){
        if(caster != null){
            caster.sendSystemMessage(Component.literal("haha missed stupid"));
        }
    }
}
