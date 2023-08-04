package dev.aprilvee.xiaoheic.spell;

import dev.aprilvee.xiaoheic.data.datatype.SpellType;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public class SpellEffects {

    public static void fireballEntity(Entity caster, Entity target) {
        target.setSecondsOnFire(3);
        target.hurt(target.damageSources().onFire(),5F);
    }
    public static void fireballBlock(Entity caster, BlockPos pos){

    }

    public static void snowshotE(Entity caster, Entity target) {
        target.hurt(target.damageSources().magic(),6F);
        if(target instanceof LivingEntity){
            LivingEntity living = (LivingEntity) target;
            living.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN,100, 1));
        }
    }
    public static void snowshotB(Entity caster, BlockPos pos){

    }
}
