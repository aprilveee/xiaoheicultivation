package dev.aprilvee.xiaoheic.registry;

import dev.aprilvee.xiaoheic.main;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class items {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, main.MODID);

    public static final RegistryObject<Item> QICRYSTAL = ITEMS.register("qicrystal", () -> new Item(new Item.Properties()));

    public static final RegistryObject<ForgeSpawnEggItem> SPRITE_SPAWN_EGG = ITEMS.register("sprite_spawn_egg", () -> new ForgeSpawnEggItem(entities.SPRITE, 0xa0c4c7, 0xaaeff6, new Item.Properties()));

    public static final RegistryObject<Item> JADEBLOCK = ITEMS.register("jade_block", () -> new BlockItem(blocks.JADE.get(), new Item.Properties()));



    public static final RegistryObject<Item> EXAMPLE_BLOCK_ITEM = ITEMS.register("example_block", () -> new BlockItem(blocks.EXAMPLE_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<Item> EXAMPLE_ITEM = ITEMS.register("example_item", () -> new Item(new Item.Properties().food(new FoodProperties.Builder()
            .alwaysEat().nutrition(1).saturationMod(2f).build())));
    public static final RegistryObject<Item> TABICON = ITEMS.register("tabicon", () -> new Item(new Item.Properties()));


}
