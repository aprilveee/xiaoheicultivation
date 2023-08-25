package dev.aprilvee.xiaoheic.spell.spells;

import dev.aprilvee.xiaoheic.data.datatype.CastType;
import dev.aprilvee.xiaoheic.data.datatype.Element;
import dev.aprilvee.xiaoheic.data.datatype.SType;
import dev.aprilvee.xiaoheic.entity.BasicSpell;
import dev.aprilvee.xiaoheic.spell.ICastable;
import dev.aprilvee.xiaoheic.spell.IProjectileSpell;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

public class QiBallSpell implements IProjectileSpell, ICastable {
	public int index = 2;
	public Component name = null;
	public String id = "qiball";
	public int qiCost = 100;
	public float perQiCost = 0.01f;

	@Override
	public void basicProjTick(BasicSpell spell) {
		if(spell.lifetime > 400){
			spell.discard();
		}
		Vec3 pos = spell.position();
		Vec3 velocity = spell.getDeltaMovement();
		Vec3 newpos = pos.add(velocity);
		Vec3 hitpos = newpos;
		//Random rand = new Random();
		spell.level().addParticle(ParticleTypes.AMBIENT_ENTITY_EFFECT, hitpos.x, hitpos.y, hitpos.z, velocity.x, velocity.y, velocity.z);
		//spell.level().addParticle(ParticleTypes.SMALL_FLAME, hitpos.x, hitpos.y, hitpos.z, velocity.x/40 + (rand.nextFloat()-0.5f)/10, velocity.y/40 + (rand.nextFloat()-0.5f)/10, velocity.z/40 + (rand.nextFloat()-0.5f)/10);

	}

	@Override
	public void entityHit(Entity target, Entity caster, BasicSpell spell) {
		target.hurt(target.damageSources().generic(),2F);
	}

	@Override
	public void blockHit(BlockPos pos, Entity caster, BasicSpell spell) {

	}

	@Override
	public void castSpell(Player player) {
		Vec3 spawnpos = new Vec3(player.getEyePosition().x+player.getDeltaMovement().x,
				player.getEyePosition().y+player.getDeltaMovement().y,player.getEyePosition().z+player.getDeltaMovement().z);
		BasicSpell spell = new BasicSpell(player.level(), spawnpos, this.getIndex());
		spell.setOwner(player);
		spell.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5f, 4);
		player.level().addFreshEntity(spell);
	}

	@Override
	public CastType getCasttype() {
		return null;
	}

	@Override
	public boolean keybindable() {
		return false;
	}

	@Override
	public boolean isSpell() {
		return false;
	}

	@Override
	public boolean unlock() {
		return true;
	}

	@Override
	public Element element() {
		return Element.NONE;
	}

	@Override
	public SType type() {
		return null;
	}

	@Override
	public int getIndex() {
		return index;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public Component getName() {
		return name;
	}

	@Override
	public int getQiCost() {
		return qiCost;
	}

	@Override
	public float getPerQiCost() {
		return perQiCost;
	}
}
