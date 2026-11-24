package com.example.shademod;

import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

public class ModItems {
    /** Выпадает с Тени. Если съесть, даёт ночное зрение на 3 минуты. */
    public static final Item SHADOW_ESSENCE = register("shadow_essence", new Item(new Item.Settings()
            .maxCount(16)
            .rarity(Rarity.UNCOMMON)
            .food(new FoodComponent.Builder()
                    .nutrition(1)
                    .saturationModifier(0.2f)
                    .alwaysEdible()
                    .statusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, 3600, 0), 1.0f)
                    .build())));

    public static final Item SHADE_SPAWN_EGG = register("shade_spawn_egg",
            new SpawnEggItem(ModEntities.SHADE, 0x14101F, 0x8A3FFC, new Item.Settings()));

    private static Item register(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(ShadeMod.MOD_ID, name), item);
    }

    public static void register() {}
}
