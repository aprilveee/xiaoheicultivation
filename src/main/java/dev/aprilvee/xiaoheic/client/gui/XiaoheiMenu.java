package dev.aprilvee.xiaoheic.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import dev.aprilvee.xiaoheic.main;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class XiaoheiMenu extends Screen {
    private static final ResourceLocation MENU = new ResourceLocation(main.MODID,"textures/gui/menuplaceholder.png");

    private static final Minecraft minecraft = Minecraft.getInstance();

    public XiaoheiMenu() {
        super(Component.literal(""));
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float pticks) {
        int yOffset = (minecraft.getWindow().getGuiScaledHeight()-200)/2;
        //RenderSystem.setShaderTexture(0, MENU);
        //RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        graphics.blit(MENU,(width-200)/2, yOffset, 0, 0,  200,200, 200,200);
        super.render(graphics, mouseX, mouseY, pticks);
    }
    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
