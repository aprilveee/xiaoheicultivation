package dev.aprilvee.xiaoheic.entity;

import dev.aprilvee.xiaoheic.data.Datalist;
import dev.aprilvee.xiaoheic.registry.entities;
import dev.aprilvee.xiaoheic.spell.IProjectileSpell;
import net.minecraft.core.Position;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;

import javax.annotation.Nullable;

public class BasicSpell extends Projectile {
    private static EntityDataAccessor<Integer> index = SynchedEntityData.defineId(BasicSpell.class, EntityDataSerializers.INT);
    public int lifetime = 0;

    public BasicSpell(EntityType<BasicSpell> entity, Level level) {
        super(entity, level);
    }

    public BasicSpell(Level level, double x, double y, double z) {
        this(entities.BASIC_SPELL.get(), level);
        setPos(x, y, z);
    }

    public BasicSpell(Level level, Position position, int ind) {
        this(level, position.x(), position.y(), position.z());
        this.entityData.set(index, ind);
    }

    public void tick() { // DataList.spells[this.entityData.get(index)]
        super.tick();

        //CRASHES IF NONPROJECTILE
        //if(!(Datalist.spells[this.entityData.get(index)] instanceof IProjectileSpell)){this.discard();}
        IProjectileSpell spell = (IProjectileSpell) Datalist.spells[this.entityData.get(index)];
        spell.basicProjTick(this);

        Vec3 pos = this.position();
        Vec3 velocity = this.getDeltaMovement();
        Vec3 newpos = pos.add(velocity);
        Vec3 hitpos = newpos;
        HitResult hitresult = this.level().clip(new ClipContext(pos, newpos, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, this));

        if (hitresult.getType() != HitResult.Type.MISS) {
            hitpos = hitresult.getLocation();
        }

        //hit detection is all in this while statement
        //why, may you ask? well, to that, my answer is
        while (!this.isRemoved()) {
            EntityHitResult entityhitresult = this.findHitEntity(pos, newpos);
            if (entityhitresult != null) {
                hitresult = entityhitresult;
            }

            if (hitresult != null && hitresult.getType() == HitResult.Type.ENTITY) { //teamkill prevention, from vaniller code
                Entity entity = ((EntityHitResult)hitresult).getEntity();
                Entity entity1 = this.getOwner();
                if (entity instanceof Player && entity1 instanceof Player && !((Player)entity1).canHarmPlayer((Player)entity)) {
                    hitresult = null;
                    entityhitresult = null;
                }else if(entity == this.getOwner()){    //self damage prevention, doesn't seem to sync with client?
                    hitresult = null;                   //fix later
                    entityhitresult = null;
                }

            }

            if (hitresult != null && hitresult.getType() != HitResult.Type.MISS) {
                if (net.minecraftforge.event.ForgeEventFactory.onProjectileImpact(this, hitresult))
                    break;
                this.onHit(hitresult);
                //this.hasImpulse = true;
            }


            if (entityhitresult == null) {
                break;
            }

            hitresult = null;
        }

        ProjectileUtil.rotateTowardsMovement(this, 0.2F);
        this.setPos(newpos.x, newpos.y, newpos.z);

        ++this.lifetime;
    }

    @Nullable
    protected EntityHitResult findHitEntity(Vec3 pos, Vec3 newpos) {
        return ProjectileUtil.getEntityHitResult(this.level(), this, pos, newpos, this.getBoundingBox().expandTowards(this.getDeltaMovement()).inflate(2.0D), this::canHitEntity);
    }

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    public Integer getIndex() {
        return this.entityData.get(index);
    }

    @Override
    protected void onHitEntity(EntityHitResult ray) {
        if(ray.getEntity() != this.getOwner()) {
            super.onHitEntity(ray);

            IProjectileSpell spell = (IProjectileSpell) Datalist.spells[this.entityData.get(index)];
            spell.entityHit(ray.getEntity(), this.getOwner(), this);
            this.discard();


        }
    }

    @Override
    protected void onHitBlock(BlockHitResult hit){
        super.onHitBlock(hit);
        IProjectileSpell spell = (IProjectileSpell) Datalist.spells[this.entityData.get(index)];
        spell.blockHit(hit.getBlockPos(), this.getOwner(), this);
        this.discard();

    }


    protected void defineSynchedData() {
        this.entityData.define(index, 0);
    }
    }