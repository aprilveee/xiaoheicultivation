package dev.aprilvee.xiaoheic.entity;

import dev.aprilvee.xiaoheic.registry.entities;
import dev.aprilvee.xiaoheic.data.DataList;
import dev.aprilvee.xiaoheic.data.datatype.SpellType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Position;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;

public class BasicSpell extends Projectile {
    private static EntityDataAccessor<Integer> index = SynchedEntityData.defineId(BasicSpell.class, EntityDataSerializers.INT);
    //public int index = 0;
    public SpellType type = DataList.invalid;
    private int lifetime = 0;

    public BasicSpell(EntityType<BasicSpell> entity, Level level) {
        super(entity, level);
    }

    public BasicSpell(Level level, double x, double y, double z){
        this(entities.BASIC_SPELL.get(), level);
        setPos(x,y,z);
    }

    public BasicSpell(Level level, Position position, int ind){
        this(level, position.x(),position.y(),position.z());
        this.entityData.set(index, ind);

        level.getServer().getPlayerList().getPlayerByName("Dev").sendSystemMessage(Component.literal(String.valueOf(this.entityData.get(index))));

    }



    public SpellType getSpellType() {
        return type;
    }

    public void tick(){
        super.tick();

        if(this.lifetime >= 200){
            this.discard();
        }

        Vec3 v = this.getDeltaMovement();
        double vx = this.getX() + v.x;
        double vy = this.getY() + v.y;
        double vz = this.getZ() + v.z;
        ProjectileUtil.rotateTowardsMovement(this, 0.2F);
        this.setPos(vx,vy,vz);

        this.level().addParticle(DataList.spells[this.entityData.get(index)].particle, vx, vy, vz, v.x, v.y, v.z);

        //hit detection



        ++this.lifetime;
    }

    public void move(MoverType p_36749_, Vec3 p_36750_) {
        super.move(p_36749_, p_36750_);
        }


    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    public static boolean canSpawn(EntityType<BasicSpell> entityType, LevelAccessor level, MobSpawnType spawnType, BlockPos pos, RandomSource random){
        return false;
    }

    @Override
    protected void onHitEntity(EntityHitResult ray) {
        super.onHitEntity(ray);
        this.remove(RemovalReason.DISCARDED);
    }



    protected void defineSynchedData() {
        this.entityData.define(index, 0);
    }
    }