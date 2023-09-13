package dev.aprilvee.xiaoheic.fluid;

import dev.aprilvee.xiaoheic.main;
import dev.aprilvee.xiaoheic.registry.blocks;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class xiaoheifluids {
	public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, main.MODID);

	public static final RegistryObject<FlowingFluid> SOURCE_SPIRIT_WATER = FLUIDS.register("spirit_water_fluid", () -> new
			ForgeFlowingFluid.Source(xiaoheifluids.SPIRIT_WATER_PROPERTIES));
	public static final RegistryObject<FlowingFluid> FLOWING_SPIRIT_WATER = FLUIDS.register("flowing_spirit_water", () ->
			new ForgeFlowingFluid.Flowing(xiaoheifluids.SPIRIT_WATER_PROPERTIES));

	public static final ForgeFlowingFluid.Properties SPIRIT_WATER_PROPERTIES = new ForgeFlowingFluid.Properties(
			xiaoheifluidtypes.SPIRIT_WATER_TYPE, SOURCE_SPIRIT_WATER, FLOWING_SPIRIT_WATER).slopeFindDistance(2).levelDecreasePerBlock(2)
			.block(blocks.SPIRIT_WATER_BLOCK);
}
