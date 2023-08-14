package dev.aprilvee.xiaoheic.entity;

import dev.aprilvee.xiaoheic.data.Datalist;
import dev.aprilvee.xiaoheic.data.datatype.IProjectileSpell;
import dev.aprilvee.xiaoheic.registry.entities;
import dev.aprilvee.xiaoheic.spell.SpellEffects;
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

public class BasicSpellOld extends Projectile {
    private static EntityDataAccessor<Integer> index = SynchedEntityData.defineId(BasicSpellOld.class, EntityDataSerializers.INT);
    private int lifetime = 0;

    public BasicSpellOld(EntityType<BasicSpellOld> entity, Level level) {
        super(entity, level);
    }



    public void tick() { // DataList.spells[this.entityData.get(index)]
        super.tick();

        if (this.lifetime >= Datalist.spellsold[this.getIndex()].lifetime) {
            this.discard();
        }

        //CRASHES IF NONPROJECTILE
        IProjectileSpell spell = (IProjectileSpell) Datalist.spells[this.getIndex()];

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
                this.level().addParticle(Datalist.spellsold[this.entityData.get(index)].particle, hitpos.x, hitpos.y, hitpos.z, velocity.x/20, velocity.y/20, velocity.z/20);

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

            /*switch (Datalist.spellsold[this.getIndex()].id) {
                case "fireball" -> {
                        SpellEffects.fireballEntity(this.getOwner(), ray.getEntity());
                        this.discard();
                    }
                    case "snowshot" -> {SpellEffects.snowshotE(this.getOwner(), ray.getEntity());
                        this.discard();
                    }
                    default -> this.discard();
                }*/

        }
    }

    @Override
    protected void onHitBlock(BlockHitResult hit){
        super.onHitBlock(hit);

        switch (Datalist.spellsold[this.getIndex()].id) {
            case "fireball" -> {
                SpellEffects.fireballBlock(this.getOwner(), hit.getBlockPos());
                this.discard();
            }
            case "snowshot" -> SpellEffects.snowshotB(this.getOwner(), hit.getBlockPos());
            default -> this.discard();
        }
    }


    protected void defineSynchedData() {
        this.entityData.define(index, 0);
    }
    }