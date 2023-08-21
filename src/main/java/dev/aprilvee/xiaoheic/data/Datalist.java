package dev.aprilvee.xiaoheic.data;

import dev.aprilvee.xiaoheic.cultivation.cultivatemethods.ICultivateMethod;
import dev.aprilvee.xiaoheic.cultivation.cultivatemethods.EmptyMethod;
import dev.aprilvee.xiaoheic.cultivation.cultivatemethods.SpriteBreathing;
import dev.aprilvee.xiaoheic.cultivation.state.*;
import dev.aprilvee.xiaoheic.data.datatype.*;
import dev.aprilvee.xiaoheic.spell.spell.EmptySpell;
import dev.aprilvee.xiaoheic.spell.spell.FireballSpell;
import dev.aprilvee.xiaoheic.spell.ISpell;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;

public class Datalist {
    public static SpellType emptyold;
    public static SpellType fireballold;
    public static SpellType snowshot;

    public static SType notype;
    public static SType elemental;
    public static SType instrument;
    public static SType creation;
    public static SType spiritual;
    public static SType mind;
    public static SType spatial;


    public static ISpell empty = new EmptySpell();
    public static ISpell fireball = new FireballSpell();

    public static ICultivateMethod emptymethod = new EmptyMethod();
    public static ICultivateMethod spritebreathing = new SpriteBreathing();

    public static IState mortal = new MortalState();
    public static IState sprite = new SpriteState();
    public static IState attunement = new AttunementState();
    public static IState realmshaping = new RealmShapingState();

    public static ISpell[] spells = {empty, fireball};
    public static ICultivateMethod[] cultivationmethods = {emptymethod, spritebreathing};
    public static IState[] states = {mortal, sprite, attunement, realmshaping};

    public static SType[] types;
    public static SpellType[] spellsold;

    public static void init(){
        initializeSpells();
        initializeTypes();
    }

    @Deprecated
    public static void initializeSpells(){
        emptyold = new SpellType();
        emptyold.index = 0;
        emptyold.name = Component.translatable("spell.xiaoheic.none");
        emptyold.id = "none";
        emptyold.qiCost = 0;
        emptyold.pQiCost = 0;
        emptyold.isSpell = false;
        emptyold.firingtype = "none";
        emptyold.element = Element.NONE;
        emptyold.offensive = false;
        emptyold.particle = ParticleTypes.EFFECT;

        fireballold = new SpellType();
        fireballold.index = 1;
        fireballold.name = Component.translatable("spell.xiaoheic.fireball");
        fireballold.id = "fireball";
        fireballold.qiCost = 250;
        fireballold.pQiCost = 0.02f;
        fireballold.isSpell = true;
        fireballold.casttype = CastType.activespell;
        fireballold.firingtype = "proj"; ////
        fireballold.projcount = 1;
        fireballold.accuracy = 2;
        fireballold.gravity = 0;
        fireballold.flyspeed = 1.5F;
        fireballold.lifetime = 400; ////
        fireballold.element = Element.FIRE;
        fireballold.offensive = true;
        fireballold.particle = ParticleTypes.FLAME;

        snowshot = new SpellType();
        snowshot.index = 2;
        snowshot.id = "snowshot";
        snowshot.qiCost = 850;
        snowshot.pQiCost = 0.02f;
        snowshot.isSpell = true;
        snowshot.casttype = CastType.activespell;
        snowshot.cooldown = 20;
        snowshot.firingtype = "proj"; ////
        snowshot.projcount = 3;
        snowshot.accuracy = 10;
        snowshot.gravity = 0;
        snowshot.flyspeed = 2;
        snowshot.lifetime = 200; ////
        snowshot.element = Element.WATER;
        snowshot.offensive = true;
        snowshot.particle = ParticleTypes.SNOWFLAKE;

        spellsold = new SpellType[]{emptyold, fireballold, snowshot};
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
        types = new SType[]{notype, elemental, instrument, creation, spiritual, mind, spatial};
    }




        /*public static void initializeElements(){
        none = new Elements();   // do not use this element
        none.index = 0;         // for elementally neutral spells, use neutral below
        none.id = "empty";      // this one is to prevent null exceptions

        neutral = new Elements();
        neutral.index = 1;
        neutral.id = "none";
        neutral.feeds = none;neutral.destroys = none;neutral.counter = none;

        metal = new Elements();
        metal.index = 2;
        metal.id = "metal";
        metal.feeds = water;metal.destroys = wood;metal.counter = fire;

        water = new Elements();
        water.index = 3;
        water.id = "water";
        water.feeds = wood;water.destroys = fire;water.counter = earth;

        wood = new Elements();
        wood.index = 4;
        wood.id = "wood";
        wood.feeds = fire;wood.destroys = earth;wood.counter = metal;

        fire = new Elements();
        fire.index = 5;
        fire.id = "fire";
        fire.feeds = earth;fire.destroys = metal;fire.counter = water;

        earth = new Elements();
        earth.index = 6;
        earth.id = "earth";
        earth.feeds = metal;earth.destroys = water; earth.counter = wood;

        elements = new Elements[]{none, neutral, metal, water, wood, fire, earth};
    }*/
}
