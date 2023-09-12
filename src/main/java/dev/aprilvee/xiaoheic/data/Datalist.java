package dev.aprilvee.xiaoheic.data;

import dev.aprilvee.xiaoheic.cultivation.cultivatemethods.EmptyMethod;
import dev.aprilvee.xiaoheic.cultivation.cultivatemethods.ICultivateMethod;
import dev.aprilvee.xiaoheic.cultivation.cultivatemethods.SpriteBreathing;
import dev.aprilvee.xiaoheic.cultivation.state.*;
import dev.aprilvee.xiaoheic.cultivation.type.ElementalType;
import dev.aprilvee.xiaoheic.cultivation.type.IType;
import dev.aprilvee.xiaoheic.cultivation.type.NoType;
import dev.aprilvee.xiaoheic.spell.ISpell;
import dev.aprilvee.xiaoheic.spell.spells.EmptySpell;
import dev.aprilvee.xiaoheic.spell.spells.FireballSpell;
import dev.aprilvee.xiaoheic.spell.spells.QiBallSpell;

public class Datalist {
    public static IType notype = new NoType();
    public static IType elemental = new ElementalType();

    public static ISpell empty = new EmptySpell();
    public static ISpell fireball = new FireballSpell();
    public static ISpell qiball = new QiBallSpell();

    public static ICultivateMethod emptymethod = new EmptyMethod();
    public static ICultivateMethod spritebreathing = new SpriteBreathing();

    public static IState mortal = new MortalState();
    public static IState sprite = new SpriteState();
    public static IState attunement = new AttunementState();
    public static IState realmshaping = new RealmShapingState();

    //ALWAYS INSTANTIATE SPELLS WHEN GIVING THEM TO PLAYERS unless you dont need to, you probably need to
    public static IType[] types = {notype,elemental};
    public static ISpell[] spells = {empty, fireball, qiball};
    public static ICultivateMethod[] cultivationmethods = {emptymethod, spritebreathing};
    public static IState[] states = {mortal, sprite, attunement, realmshaping};

}
