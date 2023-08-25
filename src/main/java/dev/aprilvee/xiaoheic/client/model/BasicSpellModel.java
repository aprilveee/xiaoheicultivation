package dev.aprilvee.xiaoheic.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import dev.aprilvee.xiaoheic.main;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class BasicSpellModel<T extends Entity> extends EntityModel<T> {
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(main.MODID, "basicspell"), "main");
	private final ModelPart body;

	public BasicSpellModel(ModelPart root) {
		this.body = root.getChild("body");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -5.0F, -3.0F, 5.0F, 5.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(7, 19).addBox(-1.0F, -4.0F, -4.0F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(10, 26).addBox(-1.0F, -4.0F, 2.0F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(14, 4).addBox(0.0F, -3.0F, 4.0F, 1.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(19, 21).addBox(3.0F, -4.0F, -1.0F, 1.0F, 2.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(12, 19).addBox(-2.0F, -3.0F, 2.0F, 1.0F, 2.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(0, 25).addBox(-1.0F, -3.0F, 4.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(22, 11).addBox(1.0F, -2.0F, 4.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(22, 0).addBox(2.0F, -4.0F, 2.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(0, 18).addBox(-3.0F, -4.0F, -2.0F, 1.0F, 2.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(11, 11).addBox(0.0F, -6.0F, -1.0F, 2.0F, 1.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(0, 10).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 1.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(15, 0).addBox(2.0F, -1.0F, 2.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, 24.0F, 1.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}