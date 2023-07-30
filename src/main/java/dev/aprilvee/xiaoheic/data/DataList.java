package dev.aprilvee.xiaoheic.data;

import dev.aprilvee.xiaoheic.data.datatype.Affinity;
import dev.aprilvee.xiaoheic.data.datatype.Element;
import dev.aprilvee.xiaoheic.data.datatype.SType;
import dev.aprilvee.xiaoheic.data.datatype.SpellType;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;

public class DataList {

    public static SType notype;
    public static SType elemental;
    public static SType instrument;
    public static SType creation;
    public static SType spiritual;
    public static SType mind;
    public static SType spatial;

    public static Element metal;
    public static Element water;
    public static Element wood;
    public static Element fire;
    public static Element earth;
    public static Element neutral;
    public static Element none;

    public static SpellType invalid;
    public static SpellType fireball;

    public static SType[] types;
    public static Affinity[] affinities;
    public static Element[] elements;
    public static SpellType[] spells;

    public static void init(){
        initializeTypes();
        initializeElements();
        initializeSpells();
    }


    public static void initializeTypes(){
        notype = new SType();
        elemental = new SType();
        instrument = new SType();
        creation = new SType();
        spiritual = new SType();
        mind = new SType();
        spatial = new SType();

        notype.index = 0;
        notype.id = "none";
        elemental.index = 1;
        elemental.id = "elemental";
        instrument.index = 2;
        instrument.id = "instrumental";
        creation.index = 3;
        creation.id = "creation";
        spiritual.index = 4;
        spiritual.id = "spiritual";
        mind.index = 5;
        mind.id = "mind";
        spatial.index = 6;
        spatial.id = "spatial";
    }

    public static void initializeElements(){
        none = new Element();   // do not use this element
        none.index = 0;         // for elementally neutral spells, use neutral below
        none.id = "empty";      // this one is to prevent null exceptions

        neutral = new Element();
        neutral.index = 1;
        neutral.id = "none";
        neutral.feeds = none;neutral.destroys = none;neutral.counter = none;

        metal = new Element();
        metal.index = 2;
        metal.id = "metal";
        metal.feeds = water;metal.destroys = wood;metal.counter = fire;

        water = new Element();
        water.index = 3;
        water.id = "water";
        water.feeds = wood;water.destroys = fire;water.counter = earth;

        wood = new Element();
        wood.index = 4;
        wood.id = "wood";
        wood.feeds = fire;wood.destroys = earth;wood.counter = metal;

        fire = new Element();
        fire.index = 5;
        fire.id = "fire";
        fire.feeds = earth;fire.destroys = metal;fire.counter = water;

        earth = new Element();
        earth.index = 6;
        earth.id = "earth";
        earth.feeds = metal;earth.destroys = water; earth.counter = wood;

        elements = new Element[]{none, neutral, metal, water, wood, fire, earth};
    }

    public static void initializeSpells(){
        invalid = new SpellType();
        invalid.index = 0;
        invalid.name = Component.translatable("spell.xiaoheic.none");
        invalid.id = "none";
        invalid.qiCost = 0;
        invalid.element = neutral;
        invalid.offensive = false;
        invalid.particle = ParticleTypes.ELECTRIC_SPARK;

        fireball = new SpellType();
        fireball.index = 1;
        fireball.name = Component.translatable("spell.xiaoheic.fireball");
        fireball.id = "fireball";
        fireball.qiCost = 150;
        fireball.element = fire;
        fireball.offensive = true;
        fireball.particle = ParticleTypes.FLAME;

        spells = new SpellType[]{invalid, fireball};
    }
}
