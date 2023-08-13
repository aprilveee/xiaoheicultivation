package dev.aprilvee.xiaoheic.entity.sprite;

import dev.aprilvee.xiaoheic.capability.SpiritCap;
import dev.aprilvee.xiaoheic.cultivation.Cultivation;
import dev.aprilvee.xiaoheic.registry.entities;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;

public class EarthSprite extends PathfinderMob implements ISprite {

    public EarthSprite(EntityType<EarthSprite> type, Level level) {
        super(type, level);
    }

    public EarthSprite(Level level, double x, double y, double z){
        this(entities.EARTHSPRITE.get(), level);
        setPos(x,y,z);
    }

    public EarthSprite(Level level, BlockPos position){
        this(level, position.getX(),position.getY(),position.getZ());
    }

    @Override
    protected void registerGoals() { //this is its ai!
        this.goalSelector.addGoal(2, new RandomStrollGoal(this, 0.4D)); //float is stroll speed!
    }


    public static AttributeSupplier.Builder createAttributes(){
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 8).add(Attributes.MOVEMENT_SPEED, 0.25);
    }// these are its misc attributes like speed and health!

    public static boolean canSpawn(EntityType<EarthSprite> entityType, LevelAccessor level, MobSpawnType spawnType, BlockPos pos, RandomSource random){
        return Mob.checkMobSpawnRules(entityType, level, spawnType, pos, random);
    }


    @Override
    public void absorbSprite(SpiritCap sp, Player player) {
        Cultivation.addCXP(player, 1);
        sp.addEarth(1);
    }
}