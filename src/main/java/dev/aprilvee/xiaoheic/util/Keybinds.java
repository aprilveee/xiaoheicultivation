package dev.aprilvee.xiaoheic.util;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

public class Keybinds {
    public static final String KEY_CATEGORY_XIAOHEI = "key.category.xiaoheic.xiaohei";
    public static final String KEY_MODMENU = "key.xiaoheic.menu";
    public static final String KEY_CASTINGTOGGLE = "key.xiaoheic.togglecast";
    public static final String KEY_CAST_SLOT1 = "key.xiaoheic.castslot1";
    public static final String KEY_CAST_SLOT2 = "key.xiaoheic.castslot2";
    public static final String KEY_CAST_SLOT3 = "key.xiaoheic.castslot3";
    public static final String KEY_CAST_SLOT4 = "key.xiaoheic.castslot4";
    public static final String KEY_CAST_SLOT5 = "key.xiaoheic.castslot5";
    public static final String KEY_CAST_SLOT6 = "key.xiaoheic.castslot6";

    public static final KeyMapping MENU_KEY = new KeyMapping(KEY_MODMENU, KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_LEFT_BRACKET, KEY_CATEGORY_XIAOHEI);
    public static final KeyMapping CULTIVATION_KEY = new KeyMapping("key.xiaoheic.cultivate", KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_C, KEY_CATEGORY_XIAOHEI);
    public static final KeyMapping CASTING_TOGGLE = new KeyMapping(KEY_CASTINGTOGGLE, KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_B, KEY_CATEGORY_XIAOHEI);

    public static final KeyMapping CAST_SLOT1_KEY = new KeyMapping(KEY_CAST_SLOT1, KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_R, KEY_CATEGORY_XIAOHEI);
    public static final KeyMapping CAST_SLOT2_KEY = new KeyMapping(KEY_CAST_SLOT2, KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_T, KEY_CATEGORY_XIAOHEI);
    public static final KeyMapping CAST_SLOT3_KEY = new KeyMapping(KEY_CAST_SLOT3, KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_G, KEY_CATEGORY_XIAOHEI);
    public static final KeyMapping CAST_SLOT4_KEY = new KeyMapping(KEY_CAST_SLOT4, KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_H, KEY_CATEGORY_XIAOHEI);
    public static final KeyMapping CAST_SLOT5_KEY = new KeyMapping(KEY_CAST_SLOT5, KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_Y, KEY_CATEGORY_XIAOHEI);
    public static final KeyMapping CAST_SLOT6_KEY = new KeyMapping(KEY_CAST_SLOT6, KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_V, KEY_CATEGORY_XIAOHEI);
}
