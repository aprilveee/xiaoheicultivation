package dev.aprilvee.xiaoheic.spell;

import dev.aprilvee.xiaoheic.data.datatype.CastType;
import dev.aprilvee.xiaoheic.spell.ISpell;
import net.minecraft.world.entity.player.Player;

public interface ICastable extends ISpell {
    public void castSpell(Player player);
    public CastType getCasttype();
    public boolean keybindable();
    public boolean isSpell();


}
