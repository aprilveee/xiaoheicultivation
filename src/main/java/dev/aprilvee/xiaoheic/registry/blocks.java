package dev.aprilvee.xiaoheic.registry;

import dev.aprilvee.xiaoheic.fluid.xiaoheifluids;
import dev.aprilvee.xiaoheic.main;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class blocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, main.MODID);
    public static final RegistryObject<Block> EXAMPLE_BLOCK = BLOCKS.register("example_block", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE)));
    public static final RegistryObject<Block> JADE = BLOCKS.register("jade",()->new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GREEN).requiresCorrectToolForDrops()
            .destroyTime(3)));

    public static final RegistryObject<LiquidBlock> SPIRIT_WATER_BLOCK = BLOCKS.register("spirit_water_block", () ->
            new LiquidBlock(xiaoheifluids.SOURCE_SPIRIT_WATER, BlockBehaviour.Properties.copy(Blocks.WATER)));
}
