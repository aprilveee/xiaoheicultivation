package dev.aprilvee.xiaoheic.data;

import dev.aprilvee.xiaoheic.data.datatype.SpellType;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;

public class SpellEffects {

    public void fireballEntity(Player caster, Entity target, SpellType type) {
        target.setSecondsOnFire(3);
        target.hurt(target.damageSources().onFire(),5F);
    }
    public void fireballBlock(Player caster, BlockPos pos, SpellType type){
        caster.sendSystemMessage(Component.literal("haha missed stupid"));
    }
}
