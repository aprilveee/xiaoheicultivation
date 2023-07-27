package dev.aprilvee.xiaoheic.spell;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;

public class SpellList {
    public static SpellType none;
    public static SpellType fireball;
    public static SpellType[] spells;

    public static void initializeSpells(){
        spells = new SpellType[10];
        none = new SpellType();
        none.index = 0;
        none.name = Component.translatable("spell.xiaoheic.none");
        none.iname = "none";
        none.qiCost = 0;
        none.element = "none";
        none.offensive = false;
        none.particle = ParticleTypes.EFFECT;

        fireball = new SpellType();
        fireball.index = 1;
        fireball.name = Component.translatable("spell.xiaoheic.fireball");
        fireball.iname = "fireball";
        fireball.qiCost = 150;
        fireball.element = "fire";
        fireball.offensive = true;
        fireball.particle = ParticleTypes.FLAME;

        spells[0] = none;
        spells[1] = fireball;
    }

}
