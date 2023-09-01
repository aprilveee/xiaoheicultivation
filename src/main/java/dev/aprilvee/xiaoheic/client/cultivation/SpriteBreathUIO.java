package dev.aprilvee.xiaoheic.client.cultivation;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec2;

public class SpriteBreathUIO implements IUIObject{
	@Override
	public void render(GuiGraphics gui) {

	}

	@Override
	public void handleCollision(IUIObject collider, IUIObject collided) {

	}

	@Override
	public void tick(int tick) {

	}

	@Override
	public void move(int x, int y) {

	}

	@Override
	public void setPos(int x, int y) {

	}

	@Override
	public IUIObject create() {
		return null;
	}

	@Override
	public Vec2 getPos() {
		return null;
	}

	@Override
	public Vec2 getSize() {
		return null;
	}

	@Override
	public boolean isSolid() {
		return false;
	}

	@Override
	public boolean isInBoundingbox(Vec2 pos) {
		return false;
	}

	@Override
	public ResourceLocation getTex() {
		return null;
	}
}
