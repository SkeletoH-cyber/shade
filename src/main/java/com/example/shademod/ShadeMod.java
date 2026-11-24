package com.example.shademod;

import net.fabricmc.api.ModInitializer;

public class ShadeMod implements ModInitializer {
    public static final String MOD_ID = "shademod";

    @Override
    public void onInitialize() {
        ModEntities.register();
        ModItems.register();
        ModItemGroup.register();
    }
}
