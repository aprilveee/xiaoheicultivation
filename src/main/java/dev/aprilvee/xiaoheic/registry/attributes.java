package dev.aprilvee.xiaoheic.registry;

import dev.aprilvee.xiaoheic.main;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class attributes {
    public static final DeferredRegister<Attribute> ATTRIBUTES = DeferredRegister.create(ForgeRegistries.ATTRIBUTES, main.MODID);

    public static final RegistryObject<Attribute> QI = ATTRIBUTES.register("generic.qi", () -> new RangedAttribute("attribute.name.generic.qi", 0.0D, Double.MIN_VALUE, Double.MAX_VALUE));
}
