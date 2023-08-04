package dev.aprilvee.xiaoheic.client.render.model;

import dev.aprilvee.xiaoheic.client.model.SpriteModel;
import dev.aprilvee.xiaoheic.entity.Sprite;
import dev.aprilvee.xiaoheic.main;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class SpriteRenderer extends MobRenderer<Sprite, SpriteModel<Sprite>> {
    public static final ResourceLocation TEXTURE = new ResourceLocation(main.MODID, "textures/entity/sprite.png");
    public SpriteRenderer(EntityRendererProvider.Context context) {
        super(context, new SpriteModel(context.bakeLayer(SpriteModel.LAYER_LOCATION)), 0.0F); //float here is shadow size!

    }

    @Override
    public ResourceLocation getTextureLocation(Sprite p_114482_) {
        return TEXTURE;
    }


}
