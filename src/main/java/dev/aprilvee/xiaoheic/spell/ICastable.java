package dev.aprilvee.xiaoheic.spell;

import dev.aprilvee.xiaoheic.data.datatype.CastType;
import net.minecraft.world.entity.player.Player;

public interface ICastable extends ISpell {
    public void castSpell(Player player);
    public boolean canCast(Player player);
    public int getQiCost();
    public float getPerQiCost();
    public CastType getCasttype();
    public boolean keybindable();
    public boolean isSpell();


}
