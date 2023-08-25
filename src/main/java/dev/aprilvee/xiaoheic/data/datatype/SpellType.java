package dev.aprilvee.xiaoheic.data.datatype;

import net.minecraft.client.resources.sounds.Sound;
import net.minecraft.core.particles.SimpleParticleType;

public class SpellType extends SpellSlot{
 //percentage qi cost, so percentage of max qi

    public String firingtype;
    public float projcount;
    public float accuracy;
    public float flyspeed;
    public float gravity;
    public int lifetime;

    public SimpleParticleType particle;
    public Sound castSound;
    public Sound flyingSound;
    public Sound impactSound;
}
