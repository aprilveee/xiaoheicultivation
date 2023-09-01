package dev.aprilvee.xiaoheic.client.cultivation;

import dev.aprilvee.xiaoheic.main;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec2;

public class DebugPlayerUIO implements IUIObject {
	private final ResourceLocation texture = new ResourceLocation(main.MODID,"textures/gui/cultivation/flicker.png");
	final int xsize = 16;
	final int ysize = 16;
	final int txsize = 2*xsize;
	final int tysize = 1*ysize;

	private int frameCount = 0;
	private final int framelimit = 1;
	private Vec2 pos = new Vec2(0,0);
	@Override
	public void render(GuiGraphics gui) {
		gui.blit(texture, (int) pos.x - xsize/2, (int) pos.y - ysize/2,
				xsize*frameCount, 0, xsize, ysize, txsize, tysize);
	}

	@Override
	public void handleCollision(IUIObject collider, IUIObject collided) {

	}

	@Override
	public void tick(int tick) {
		if(tick % 20 == 0){
			frameCount++;
			if(frameCount > framelimit){
				frameCount = 0;
			}
		}
	}

	@Override
	public void move(int x, int y) {
		pos = new Vec2(pos.x+x,pos.y+y);
	}

	@Override
	public void setPos(int x, int y) {
		pos = new Vec2(x,y);
	}

	@Override
	public IUIObject create() {
		return new DebugPlayerUIO();
	}

	@Override
	public Vec2 getPos() {
		return this.pos;
	}

	@Override
	public Vec2 getSize() {
		return new Vec2(xsize,ysize);
	}

	@Override
	public boolean isSolid() {
		return true;
	}

	@Override
	public boolean isInBoundingbox(Vec2 pos) {
		return false;
	}

	@Override
	public ResourceLocation getTex() {
		return texture;
	}
}
