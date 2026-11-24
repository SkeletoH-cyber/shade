package com.example.shademod;

import com.example.shademod.entity.ShadeEntity;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.SpawnLocationTypes;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.Heightmap;

public class ModEntities {
    public static final EntityType<ShadeEntity> SHADE = Registry.register(
            Registries.ENTITY_TYPE,
            Identifier.of(ShadeMod.MOD_ID, "shade"),
            EntityType.Builder.create(ShadeEntity::new, SpawnGroup.MONSTER)
                    .dimensions(0.6f, 1.95f)
                    .maxTrackingRange(8)
                    .build("shade"));

    public static void register() {
        FabricDefaultAttributeRegistry.register(SHADE, ShadeEntity.createShadeAttributes());

        SpawnRestriction.register(SHADE, SpawnLocationTypes.ON_GROUND,
                Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, HostileEntity::canSpawnInDark);

        BiomeModifications.addSpawn(BiomeSelectors.foundInOverworld(),
                SpawnGroup.MONSTER, SHADE, 12, 1, 3);
    }
}
