package dev.aprilvee.xiaoheic.registry;

import dev.aprilvee.xiaoheic.entity.Sprite;
import dev.aprilvee.xiaoheic.main;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class entities {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, main.MODID);

    public static final RegistryObject<EntityType<Sprite>> SPRITE = ENTITIES.register("sprite",
            () -> EntityType.Builder.<Sprite>of(Sprite::new, MobCategory.AMBIENT)
                    .fireImmune()
                    .sized(0.375f,0.375f) //this affects hitbox size!
                    .build("xiaoheic:sprite")
    );

}
