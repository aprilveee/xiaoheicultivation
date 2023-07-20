package dev.aprilvee.xiaoheic.util;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

public class Keybinds {
    public static final String KEY_CATEGORY_XIAOHEI = "key.category.xiaoheic.xiaohei";
    public static final String KEY_CAST_SLOT1 = "key.xiaoheic.castslot1";
    public static final String KEY_CAST_SLOT2 = "key.xiaoheic.castslot2";
    public static final String KEY_CAST_SLOT3 = "key.xiaoheic.castslot3";
    public static final String KEY_CAST_SLOT4 = "key.xiaoheic.castslot4";

    public static final KeyMapping CAST_SLOT1_KEY = new KeyMapping(KEY_CAST_SLOT1, KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_R, KEY_CATEGORY_XIAOHEI);


}
