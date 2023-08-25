package dev.aprilvee.xiaoheic.client.render.model;

import com.mojang.blaze3d.vertex.PoseStack;
import dev.aprilvee.xiaoheic.entity.BasicSpell;
import dev.aprilvee.xiaoheic.main;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class BasicSpellRenderer extends EntityRenderer<BasicSpell> {
    private final ResourceLocation texture = new ResourceLocation(main.MODID, "textures/entity/basicspell.png");
    public BasicSpellRenderer(EntityRendererProvider.Context context) {
        //context = context.bakeLayer(new BasicSpellModel(context.bakeLayer(BasicSpellModel.LAYER_LOCATION)));
        super(context);
        //context.bakeLayer(BasicSpellModel.LAYER_LOCATION);
    }

    public void render(BasicSpell spell, float yaw, PoseStack pose, MultiBufferSource buffer, int light){
        //pose.pushPose();
        //this.entityRenderDispatcher.render(spell, 0, 0, 0, 0F, 0, pose, buffer, light);
        //pose.popPose();

    }


    @Override
    public ResourceLocation getTextureLocation(BasicSpell p_114482_) {
        return this.texture;
    }
}
