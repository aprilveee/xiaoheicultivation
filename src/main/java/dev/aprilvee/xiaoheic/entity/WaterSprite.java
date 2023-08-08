package dev.aprilvee.xiaoheic.entity;

import dev.aprilvee.xiaoheic.registry.entities;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraftforge.fluids.FluidType;

public class WaterSprite extends PathfinderMob {

    public WaterSprite(EntityType<WaterSprite> type, Level level) {
        super(type, level);
    }

    public WaterSprite(Level level, double x, double y, double z){
        this(entities.WATERSPRITE.get(), level);
        setPos(x,y,z);
    }

    public WaterSprite(Level level, BlockPos position){
        this(level, position.getX(),position.getY(),position.getZ());
    }

    @Override
    protected void registerGoals() { //this is its ai!
        this.goalSelector.addGoal(4, new RandomSwimmingGoal(this, 0.6D, 40)); //swimmy
        this.goalSelector.addGoal(5, new RandomStrollGoal(this, 0.4D)); //float is stroll speed!
    }

    @Override
    public boolean canDrownInFluidType(FluidType type){
        return false;
    }

    public static AttributeSupplier.Builder createAttributes(){
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 2).add(Attributes.MOVEMENT_SPEED, 0.25);
    }// these are its misc attributes like speed and health!

    public static boolean canSpawn(EntityType<WaterSprite> entityType, LevelAccessor level, MobSpawnType spawnType, BlockPos pos, RandomSource random){
        return Mob.checkMobSpawnRules(entityType, level, spawnType, pos, random);
    }


}