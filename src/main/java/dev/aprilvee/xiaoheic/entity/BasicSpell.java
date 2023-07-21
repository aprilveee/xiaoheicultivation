package dev.aprilvee.xiaoheic.entity;

import dev.aprilvee.xiaoheic.registry.entities;
import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraftforge.network.NetworkHooks;

public class BasicSpell extends Projectile {

    public BasicSpell(EntityType<BasicSpell> type, Level level) {
        super(type, level);
    }

    public BasicSpell(Level level, double x, double y, double z){
        this(entities.BASIC_SPELL.get(), level);
        setPos(x,y,z);
    }

    public BasicSpell(Level level, BlockPos position){
        this(level, position.getX(),position.getY(),position.getZ());
    }


    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    public static boolean canSpawn(EntityType<BasicSpell> entityType, LevelAccessor level, MobSpawnType spawnType, BlockPos pos, RandomSource random){
        return false;
    }



        @Override
        protected void defineSynchedData() {

        }
    }