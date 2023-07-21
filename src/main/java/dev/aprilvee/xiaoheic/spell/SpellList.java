package dev.aprilvee.xiaoheic.spell;

public class SpellList {
    public static SpellType fireball;
    public static SpellType[] spells;

    public static void initializeSpells(){
        spells = new SpellType[10];
        fireball = new SpellType();
        fireball.name = "fireball";
        fireball.qiCost = 80;
        fireball.element = "fire";
        fireball.offensive = true;

        spells[0] = fireball;
    }

}
