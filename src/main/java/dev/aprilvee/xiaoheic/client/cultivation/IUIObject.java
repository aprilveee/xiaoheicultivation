package dev.aprilvee.xiaoheic.client.cultivation;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec2;

public interface IUIObject {
	public void render(GuiGraphics gui);
	public void handleCollision(IUIObject collider, IUIObject collided);
	public void tick(int tick);
	public void move(int x, int y);
	public void setPos(int x, int y);
	public IUIObject create();
	public Vec2 getPos();
	public Vec2 getSize();
	public boolean isSolid();
	public boolean isInBoundingbox(Vec2 pos);
	public ResourceLocation getTex();
	public static boolean iterateThroughCollider(IUIObject collider, IUIObject collided){
		for(int x=0; x<collider.getSize().x;x += 2){ //iterate through x

			for(int y=0;y<collider.getSize().y;y+=2){ //iterate through y
				if(collided.isInBoundingbox(new Vec2(x,y))){
					return true;
				}
			}
		}
		return false;
	}
}
