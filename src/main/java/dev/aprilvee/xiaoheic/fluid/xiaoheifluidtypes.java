package dev.aprilvee.xiaoheic.fluid;

import dev.aprilvee.xiaoheic.main;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.joml.Vector3f;

public class xiaoheifluidtypes {
	public static final ResourceLocation water_still = new ResourceLocation("block/water_still");
	public static final ResourceLocation water_flowing = new ResourceLocation("block/water_flow");
	public static final ResourceLocation overlay = new ResourceLocation(main.MODID,"block/overlay");

	public static final DeferredRegister<FluidType> FLUID_TYPES	= DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, main.MODID);

	public static final RegistryObject<FluidType> SPIRIT_WATER_TYPE = register("spirit_water_fluid", FluidType.Properties.create()
			.lightLevel(8).density(20).viscosity(3).canHydrate(false).temperature(0));


	private static RegistryObject<FluidType> register(String name, FluidType.Properties properties){
		return FLUID_TYPES.register(name, () -> new BaseFluidType(water_still, water_flowing, overlay,
				0xa1c7f0d0, new Vector3f(84/255f, 112/255f, 222/255f), properties));
	}

}
