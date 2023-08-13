package dev.aprilvee.xiaoheic.events;

import dev.aprilvee.xiaoheic.entity.sprite.*;
import dev.aprilvee.xiaoheic.main;
import dev.aprilvee.xiaoheic.registry.entities;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
@Mod.EventBusSubscriber(modid = main.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModBusEvents {

    @SubscribeEvent
    public static void entityAttributes(EntityAttributeCreationEvent event){
        event.put(entities.SPRITE.get(), Sprite.createAttributes().build());
        event.put(entities.METALSPRITE.get(), MetalSprite.createAttributes().build());
        event.put(entities.WATERSPRITE.get(), WaterSprite.createAttributes().build());
        event.put(entities.WOODSPRITE.get(), WoodSprite.createAttributes().build());
        event.put(entities.FIRESPRITE.get(), FireSprite.createAttributes().build());
        event.put(entities.EARTHSPRITE.get(), EarthSprite.createAttributes().build());
    }

    @SubscribeEvent
    public static void registerSpawnPlacement(SpawnPlacementRegisterEvent event){
        event.register(
                entities.SPRITE.get(),
                SpawnPlacements.Type.NO_RESTRICTIONS,
                Heightmap.Types.WORLD_SURFACE,
                Sprite::canSpawn,
                SpawnPlacementRegisterEvent.Operation.OR);
    }


}
