package dev.aprilvee.xiaoheic.entity.sprite;

import dev.aprilvee.xiaoheic.capability.SpiritCap;
import dev.aprilvee.xiaoheic.cultivation.Cultivation;
import dev.aprilvee.xiaoheic.registry.entities;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;

public class Sprite extends PathfinderMob implements ISprite {

    public Sprite(EntityType<Sprite> type, Level level) {
        super(type, level);
    }

    public Sprite(Level level, double x, double y, double z){
        this(entities.SPRITE.get(), level);
        setPos(x,y,z);
    }

    public Sprite(Level level, BlockPos position){
        this(level, position.getX(),position.getY(),position.getZ());
    }

    @Override
    protected void registerGoals() { //this is its ai!
        this.goalSelector.addGoal(0, new FloatGoal(this)); //floats in water
        this.goalSelector.addGoal(5, new RandomStrollGoal(this, 0.4D)); //float is stroll speed!
    }

    public static AttributeSupplier.Builder createAttributes(){
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 2).add(Attributes.MOVEMENT_SPEED, 0.25);
    }// these are its misc attributes like speed and health!

    public static boolean canSpawn(EntityType<Sprite> entityType, LevelAccessor level, MobSpawnType spawnType, BlockPos pos, RandomSource random){
        return Mob.checkMobSpawnRules(entityType, level, spawnType, pos, random);
    }


    @Override
    public void absorbSprite(SpiritCap sp, Player player) {
        Cultivation.addCXP(player, 1);
    }
}