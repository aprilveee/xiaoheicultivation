package dev.aprilvee.xiaoheic.util;

import dev.aprilvee.xiaoheic.capability.SpiritCap;
import dev.aprilvee.xiaoheic.capability.SpiritProvider;
import dev.aprilvee.xiaoheic.data.datatype.Element;
import dev.aprilvee.xiaoheic.entity.BasicSpell;
import dev.aprilvee.xiaoheic.registry.tags;
import dev.aprilvee.xiaoheic.spell.IPassiveSpell;
import dev.aprilvee.xiaoheic.spell.IProjectileSpell;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.tags.ITagManager;

import java.util.Random;

public class xiaoheiutils {

    public static BasicSpell fireProjSpell(Player player, IProjectileSpell type, float speed, float inaccuracy){
        Vec3 spawnpos = new Vec3(player.getEyePosition().x+player.getDeltaMovement().x,
                player.getEyePosition().y+player.getDeltaMovement().y,player.getEyePosition().z+player.getDeltaMovement().z);
        BasicSpell spell = new BasicSpell(player.level(), spawnpos, type.getIndex());

        spell.setOwner(player);
        spell.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, speed, inaccuracy);

        player.level().addFreshEntity(spell);
        return spell;
    }


    public static void tickPassives(Player player){
        SpiritCap sp = player.getCapability(SpiritProvider.SPIRITCAP).orElse(null);
        for(int i = 0;i<sp.passivespells.size();i++){
            IPassiveSpell spell = sp.passivespells.get(i);
            if(spell.shouldSustain()){
                spell.tick(player);
            }else {spell.deactivate(player);}
        }
    }

    public static void saveCooldown(int tickCount, int staleTicks, int index, CompoundTag nbt){
        nbt.putInt("spellcd"+index, tickCount - staleTicks);
    }

    public static BlockPos getValidRandBlockPos(BlockPos pos, LevelAccessor level, Element element, int radius){
        BlockPos checkpos;
        BlockPos belowpos;
        ITagManager<Fluid> tag = ForgeRegistries.FLUIDS.tags();
        Random rand = new Random();
        int counter = 0;
        boolean valid = false;
        do {
            counter++;
            checkpos = pos.offset(rand.nextInt(radius),rand.nextInt(radius),rand.nextInt(radius));
            belowpos = pos.below();

            if(!level.getBlockState(pos).isSuffocating(level, checkpos) &&
                    level.getBlockState(belowpos).isFaceSturdy(level, belowpos, Direction.UP)) {
                switch (element) {
                    case FIRE -> valid = !tag.getTag(tags.waterfluid).contains(level.getFluidState(checkpos).getType());
                    case EARTH -> valid = true;
                    default -> valid = !tag.getTag(tags.lava).contains(level.getFluidState(checkpos).getType());
                }
            }else if (counter > 40){valid = true;}
        }while(!valid);
        return checkpos;
    }

    //boring meth i mean math
    public static int arrayMax(int[] array){
        int max = array[0];
        for(int i = 1; i<array.length;i++){
            max = Math.max(max,array[i]);
        }
        return max;
    }
    public static float arrayMax(float[] array){
        float max = array[0];
        for(int i = 1; i<array.length;i++){
            max = Math.max(max,array[i]);
        }
        return max;
    }

    public static int sumArray(int[] array){
        int sum = 0;
        for (int value : array) {
            sum += value;
        }
        return sum;
    }
    public static float sumArray(float[] array){
        float sum = 0;
        for (float value : array) {
            sum += value;
        }
        return sum;
    }
}
