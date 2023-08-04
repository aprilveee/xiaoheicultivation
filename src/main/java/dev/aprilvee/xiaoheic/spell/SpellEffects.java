package dev.aprilvee.xiaoheic.spell;

import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

public class SpellEffects {

    public static void fireballEntity(Entity caster, Entity target) {
        if(!target.level().isClientSide){
            target.setSecondsOnFire(3);
            target.hurt(target.damageSources().onFire(),5F);
        }
    }
    public static void fireballBlock(Entity caster, BlockPos pos){

    }

    public static void snowshotE(Entity caster, Entity target) {
        if(target instanceof LivingEntity && !target.level().isClientSide){
            LivingEntity living = (LivingEntity) target;
            living.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN,100, 1));
        }
        target.hurt(target.damageSources().magic(),6F);
    }
    public static void snowshotB(Entity caster, BlockPos pos){

    }
}
