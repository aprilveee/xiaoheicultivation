package dev.aprilvee.xiaoheic.registry;

import dev.aprilvee.xiaoheic.main;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;

public class tags {

    public static TagKey<EntityType<?>> sprites = TagKey.create(Registries.ENTITY_TYPE, new ResourceLocation(main.MODID,"sprites"));

    public static final TagKey<Block> solid = BlockTags.create(new ResourceLocation(main.MODID,"solid"));
    public static final TagKey<Block> elemental = BlockTags.create(new ResourceLocation(main.MODID,"hasqi"));

    public static final TagKey<Block> spirit = BlockTags.create(new ResourceLocation(main.MODID,"spirit"));
    public static final TagKey<Block> spiritweak = BlockTags.create(new ResourceLocation(main.MODID,"spiritweak"));
    public static final TagKey<Block> spiritmed = BlockTags.create(new ResourceLocation(main.MODID,"spiritmed"));
    public static final TagKey<Block> spiritstrong = BlockTags.create(new ResourceLocation(main.MODID,"spiritstrong"));
    public static final TagKey<Block> spiritvstrong = BlockTags.create(new ResourceLocation(main.MODID,"spiritvstrong"));

    public static final TagKey<Block> metal = BlockTags.create(new ResourceLocation(main.MODID,"metal"));
    public static final TagKey<Block> metalweak = BlockTags.create(new ResourceLocation(main.MODID,"metalweak"));
    public static final TagKey<Block> metalmed = BlockTags.create(new ResourceLocation(main.MODID,"metalmed"));
    public static final TagKey<Block> metalstrong = BlockTags.create(new ResourceLocation(main.MODID,"metalstrong"));

    public static final TagKey<Block> water = BlockTags.create(new ResourceLocation(main.MODID,"water"));
    public static final TagKey<Block> waterweak = BlockTags.create(new ResourceLocation(main.MODID,"waterweak"));
    public static final TagKey<Block> watermed = BlockTags.create(new ResourceLocation(main.MODID,"watermed"));
    public static final TagKey<Block> waterstrong = BlockTags.create(new ResourceLocation(main.MODID,"waterstrong"));

    public static final TagKey<Block> wood = BlockTags.create(new ResourceLocation(main.MODID,"wood"));
    public static final TagKey<Block> woodweak = BlockTags.create(new ResourceLocation(main.MODID,"woodweak"));
    public static final TagKey<Block> woodmed = BlockTags.create(new ResourceLocation(main.MODID,"woodmed"));
    public static final TagKey<Block> woodstrong = BlockTags.create(new ResourceLocation(main.MODID,"woodstrong"));

    public static final TagKey<Block> fire = BlockTags.create(new ResourceLocation(main.MODID,"fire"));
    public static final TagKey<Block> fireweak = BlockTags.create(new ResourceLocation(main.MODID,"fireweak"));
    public static final TagKey<Block> firemed = BlockTags.create(new ResourceLocation(main.MODID,"firemed"));
    public static final TagKey<Block> firestrong = BlockTags.create(new ResourceLocation(main.MODID,"firestrong"));

    public static final TagKey<Block> earth = BlockTags.create(new ResourceLocation(main.MODID,"earth"));
    public static final TagKey<Block> earthveryweak = BlockTags.create(new ResourceLocation(main.MODID,"earthveryweak"));
    public static final TagKey<Block> earthweak = BlockTags.create(new ResourceLocation(main.MODID,"earthweak"));
    public static final TagKey<Block> earthmed = BlockTags.create(new ResourceLocation(main.MODID,"earthmed"));
    public static final TagKey<Block> earthstrong = BlockTags.create(new ResourceLocation(main.MODID,"earthstrong"));

    public static final TagKey<Fluid> waterfluid = FluidTags.create(new ResourceLocation("minecraft","water"));
    public static final TagKey<Fluid> lava = FluidTags.create(new ResourceLocation("minecraft","lava"));

}
