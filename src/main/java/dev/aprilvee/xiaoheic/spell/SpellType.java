package dev.aprilvee.xiaoheic.spell;

import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.network.chat.Component;

public class SpellType {
    public int index;
    public Component name;
    public String iname;
    public int qiCost;
    public String element; //dont misspell it!!!
    public boolean offensive;
    public SimpleParticleType particle;
}
