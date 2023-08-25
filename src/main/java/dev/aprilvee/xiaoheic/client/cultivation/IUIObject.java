package dev.aprilvee.xiaoheic.client.cultivation;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec2;

public interface IUIObject {
	public void render(GuiGraphics gui);
	public void tick(int tick);
	public void move(int x, int y);
	public void setPos(int x, int y);
	public IUIObject create();
	public Vec2 getPos();
	public ResourceLocation getTex();
}
