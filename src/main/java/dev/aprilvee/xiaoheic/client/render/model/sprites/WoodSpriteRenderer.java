package dev.aprilvee.xiaoheic.client.render.model.sprites;

import dev.aprilvee.xiaoheic.client.model.SpriteModel;
import dev.aprilvee.xiaoheic.entity.sprite.WoodSprite;
import dev.aprilvee.xiaoheic.main;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class WoodSpriteRenderer extends MobRenderer<WoodSprite, SpriteModel<WoodSprite>> {
    public static final ResourceLocation TEXTURE = new ResourceLocation(main.MODID, "textures/entity/woodsprite.png");
    public WoodSpriteRenderer(EntityRendererProvider.Context context) {
        super(context, new SpriteModel<>(context.bakeLayer(SpriteModel.LAYER_LOCATION)), 0.0F); //float here is shadow size!

    }

    @Override
    public ResourceLocation getTextureLocation(WoodSprite p_114482_) {
        return TEXTURE;
    }


}
