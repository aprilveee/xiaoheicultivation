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
import net.minecraft.world.entity.ai.goal.RunAroundLikeCrazyGoal;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.fluids.FluidType;

public class FireSprite extends PathfinderMob {

    public FireSprite(EntityType<FireSprite> type, Level level) {
        super(type, level);
    }

    public FireSprite(Level level, double x, double y, double z){
        this(entities.FIRESPRITE.get(), level);
        setPos(x,y,z);
    }

    public FireSprite(Level level, BlockPos position){
        this(level, position.getX(),position.getY(),position.getZ());
    }

    @Override
    protected void registerGoals() { //this is its ai!
        this.goalSelector.addGoal(0, new FloatGoal(this)); //floats in water
        this.goalSelector.addGoal(2, new RandomStrollGoal(this, 0.8D)); //float is stroll speed!
    }

    @Override
    public boolean isSensitiveToWater() {
        return true;
    }
    
    @Override
    public boolean canDrownInFluidType(FluidType type){
        if(type == Fluids.LAVA.getFluidType() || type == Fluids.EMPTY.getFluidType()){
            return false;
        }
        return true;
    }

    public static AttributeSupplier.Builder createAttributes(){
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 2).add(Attributes.MOVEMENT_SPEED, 0.25);
    }// these are its misc attributes like speed and health!

    public static boolean canSpawn(EntityType<FireSprite> entityType, LevelAccessor level, MobSpawnType spawnType, BlockPos pos, RandomSource random){
        return Mob.checkMobSpawnRules(entityType, level, spawnType, pos, random);
    }


}