package dev.aprilvee.xiaoheic.data.datatype;

import dev.aprilvee.xiaoheic.entity.BasicSpell;
import net.minecraft.world.entity.Entity;

public interface IProjectileSpell {
    public void basicProjTick(BasicSpell spell);
    public void entityHit(Entity target, Entity caster, BasicSpell spell);
    public void blockHit(Entity caster, BasicSpell spell);
}
