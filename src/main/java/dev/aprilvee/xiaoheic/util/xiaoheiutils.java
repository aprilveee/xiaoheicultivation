package dev.aprilvee.xiaoheic.util;

import dev.aprilvee.xiaoheic.data.datatype.Element;
import dev.aprilvee.xiaoheic.registry.tags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.tags.ITagManager;

import java.util.Random;

public class xiaoheiutils {


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
