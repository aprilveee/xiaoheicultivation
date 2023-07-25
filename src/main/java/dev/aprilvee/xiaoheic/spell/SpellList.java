package dev.aprilvee.xiaoheic.spell;

public class SpellList {
    public static SpellType none;
    public static SpellType fireball;
    public static SpellType[] spells;

    public static void initializeSpells(){
        spells = new SpellType[10];
        none = new SpellType();
        none.index = 0;
        none.name = "none";
        none.qiCost = 0;
        none.element = "none";
        none.offensive = false;

        fireball = new SpellType();
        none.index = 1;
        fireball.name = "fireball";
        fireball.qiCost = 80;
        fireball.element = "fire";
        fireball.offensive = true;

        spells[0] = none;
        spells[1] = fireball;
    }

}
