package dev.aprilvee.xiaoheic.data.datatype;

import net.minecraft.client.resources.sounds.Sound;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.network.chat.Component;

public class SpellType {
    public int index;
    public Component name;
    public String id;
    public int qiCost;
    public float pQiCost; //percentage qi cost, so percentage of max qi
    public float flyspeed;
    public float gravity;
    public int lifetime;
    public Element element;
    public boolean offensive;
    public SimpleParticleType particle;
    public Sound castSound;
    public Sound flyingSound;
    public Sound impactSound;
}
