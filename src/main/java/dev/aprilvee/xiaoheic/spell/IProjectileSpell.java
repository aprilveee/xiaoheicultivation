package dev.aprilvee.xiaoheic.spell;

import dev.aprilvee.xiaoheic.entity.BasicSpell;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;

public interface IProjectileSpell extends ICastable {
    public void basicProjTick(BasicSpell spell);
    public void entityHit(Entity target, Entity caster, BasicSpell spell);
    public void blockHit(BlockPos pos, Entity caster, BasicSpell spell);
}
