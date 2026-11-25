package com.example.shademod.client;

import com.example.shademod.ShadeMod;
import com.example.shademod.entity.ShadeEntity;
import net.minecraft.client.render.entity.BipedEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.util.Identifier;

public class ShadeRenderer extends BipedEntityRenderer<ShadeEntity, BipedEntityModel<ShadeEntity>> {
    private static final Identifier TEXTURE = Identifier.of(ShadeMod.MOD_ID, "textures/entity/shade.png");

    public ShadeRenderer(EntityRendererFactory.Context context) {
        super(context, new BipedEntityModel<>(context.getPart(EntityModelLayers.ZOMBIE)), 0.5f);
    }

    @Override
    public Identifier getTexture(ShadeEntity entity) {
        return TEXTURE;
    }
}
