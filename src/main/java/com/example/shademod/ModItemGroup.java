package com.example.shademod;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroup {
    public static final ItemGroup SHADE_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(ShadeMod.MOD_ID, "shade_group"),
            FabricItemGroup.builder()
                    .icon(() -> new ItemStack(ModItems.SHADOW_ESSENCE))
                    .displayName(Text.translatable("itemGroup.shademod.shade_group"))
                    .entries((context, entries) -> {
                        entries.add(ModItems.SHADOW_ESSENCE);
                        entries.add(ModItems.SHADE_SPAWN_EGG);
                    })
                    .build());

    public static void register() {}
}
