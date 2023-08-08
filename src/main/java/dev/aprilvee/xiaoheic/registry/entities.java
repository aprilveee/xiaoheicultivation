package dev.aprilvee.xiaoheic.registry;

import dev.aprilvee.xiaoheic.entity.*;
import dev.aprilvee.xiaoheic.main;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class entities {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, main.MODID);

    public static final RegistryObject<EntityType<Sprite>> SPRITE = ENTITIES.register("sprite",
            () -> EntityType.Builder.<Sprite>of(Sprite::new, MobCategory.CREATURE)
                    .fireImmune()
                    .sized(0.375f,0.375f) //this affects hitbox size!
                    .build("xiaoheic:sprite")
    );
    public static final RegistryObject<EntityType<MetalSprite>> METALSPRITE = ENTITIES.register("metalsprite",
            () -> EntityType.Builder.<MetalSprite>of(MetalSprite::new, MobCategory.CREATURE)
                    .sized(0.375f,0.375f) //this affects hitbox size!
                    .build("xiaoheic:metalsprite")
    );

    public static final RegistryObject<EntityType<WaterSprite>> WATERSPRITE = ENTITIES.register("watersprite",
            () -> EntityType.Builder.<WaterSprite>of(WaterSprite::new, MobCategory.CREATURE)
                    .sized(0.375f,0.375f) //this affects hitbox size!
                    .build("xiaoheic:watersprite")
    );

    public static final RegistryObject<EntityType<WoodSprite>> WOODSPRITE = ENTITIES.register("woodsprite",
            () -> EntityType.Builder.<WoodSprite>of(WoodSprite::new, MobCategory.CREATURE)
                    .sized(0.375f,0.375f) //this affects hitbox size!
                    .build("xiaoheic:woodsprite")
    );
    public static final RegistryObject<EntityType<FireSprite>> FIRESPRITE = ENTITIES.register("firesprite",
            () -> EntityType.Builder.<FireSprite>of(FireSprite::new, MobCategory.CREATURE)
                    .fireImmune()
                    .sized(0.375f,0.375f) //this affects hitbox size!
                    .build("xiaoheic:firesprite")
    );
    public static final RegistryObject<EntityType<EarthSprite>> EARTHSPRITE = ENTITIES.register("earthsprite",
            () -> EntityType.Builder.<EarthSprite>of(EarthSprite::new, MobCategory.CREATURE)
                    .fireImmune()
                    .sized(0.375f,0.375f) //this affects hitbox size!
                    .build("xiaoheic:earthsprite")
    );


    public static final RegistryObject<EntityType<BasicSpell>> BASIC_SPELL = ENTITIES.register("basicspell", () ->
            EntityType.Builder.<BasicSpell>of(BasicSpell::new, MobCategory.MISC)
                    .fireImmune()
                    .sized(0.35F,0.35F)
                    .build("xiaoheic:basicspell")
    );

}
