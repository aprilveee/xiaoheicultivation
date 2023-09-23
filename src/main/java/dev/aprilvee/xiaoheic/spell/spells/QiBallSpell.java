package dev.aprilvee.xiaoheic.spell.spells;

import dev.aprilvee.xiaoheic.capability.SpiritCap;
import dev.aprilvee.xiaoheic.capability.SpiritProvider;
import dev.aprilvee.xiaoheic.cultivation.type.IType;
import dev.aprilvee.xiaoheic.data.datatype.CastType;
import dev.aprilvee.xiaoheic.data.Element;
import dev.aprilvee.xiaoheic.entity.BasicSpell;
import dev.aprilvee.xiaoheic.spell.ICastable;
import dev.aprilvee.xiaoheic.spell.IProjectileSpell;
import dev.aprilvee.xiaoheic.spell.ISpell;
import dev.aprilvee.xiaoheic.util.xiaoheiutils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
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
	final int cooldown = 20;
	int staleTicks = -100;
	float damageRes = 1;
	float damageX = 1;

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
		spell.level().addParticle(ParticleTypes.ELECTRIC_SPARK, hitpos.x, hitpos.y, hitpos.z, velocity.x, velocity.y, velocity.z);
		//spell.level().addParticle(ParticleTypes.SMALL_FLAME, hitpos.x, hitpos.y, hitpos.z, velocity.x/40 + (rand.nextFloat()-0.5f)/10, velocity.y/40 + (rand.nextFloat()-0.5f)/10, velocity.z/40 + (rand.nextFloat()-0.5f)/10);

	}

	@Override
	public void entityHit(Entity target, Entity caster, BasicSpell spell) {
			target.getCapability(SpiritProvider.SPIRITCAP).ifPresent(sp -> damageRes = sp.getSpellresist());
			if(caster != null){
				caster.getCapability(SpiritProvider.SPIRITCAP).ifPresent(sp -> damageX = sp.getSpelldamage());
			}
		target.hurt(target.damageSources().generic(),2*damageX*damageRes);
	}

	@Override
	public void blockHit(BlockPos pos, Entity caster, BasicSpell spell) {

	}

	@Override
	public void castSpell(Player player) {
		xiaoheiutils.fireProjSpell(player, this, 1.2f, 3);
		this.staleTicks = player.tickCount;
	}

	@Override
	public boolean canCast(Player player) {
		return player.tickCount - this.staleTicks > cooldown;
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
	public boolean canUnlock() {
		return true;
	}

	@Override
	public Element element() {
		return Element.NONE;
	}

	@Override
	public IType type() {
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
	public Component getDescription() {
		return null;
	}

	@Override
	public int getQiCost() {
		return qiCost;
	}

	@Override
	public float getPerQiCost() {
		return perQiCost;
	}

	@Override
	public void saveNBT(CompoundTag nbt, SpiritCap sp) {
		xiaoheiutils.saveCooldown(sp.tickCount,this.staleTicks,index,nbt);
	}

	@Override
	public void loadNBT(CompoundTag nbt) {
		this.staleTicks = -nbt.getInt("spellcd"+index);
	}

	@Override
	public ISpell getNew() {
		return new QiBallSpell();
	}
}
