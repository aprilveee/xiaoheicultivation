package dev.aprilvee.xiaoheic.entity.sprite;

import dev.aprilvee.xiaoheic.capability.SpiritCap;
import net.minecraft.world.entity.player.Player;

public interface ISprite {
    public void absorbSprite(SpiritCap sp, Player player);
}
