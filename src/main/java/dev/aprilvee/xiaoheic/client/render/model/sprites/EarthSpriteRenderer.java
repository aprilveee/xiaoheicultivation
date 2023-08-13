package dev.aprilvee.xiaoheic.client.render.model.sprites;

import dev.aprilvee.xiaoheic.client.model.SpriteModel;
import dev.aprilvee.xiaoheic.entity.sprite.EarthSprite;
import dev.aprilvee.xiaoheic.main;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class EarthSpriteRenderer extends MobRenderer<EarthSprite, SpriteModel<EarthSprite>> {
    public static final ResourceLocation TEXTURE = new ResourceLocation(main.MODID, "textures/entity/earthsprite.png");
    public EarthSpriteRenderer(EntityRendererProvider.Context context) {
        super(context, new SpriteModel<>(context.bakeLayer(SpriteModel.LAYER_LOCATION)), 0.0F); //float here is shadow size!

    }

    @Override
    public ResourceLocation getTextureLocation(EarthSprite p_114482_) {
        return TEXTURE;
    }


}
