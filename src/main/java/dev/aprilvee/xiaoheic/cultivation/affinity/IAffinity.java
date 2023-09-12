package dev.aprilvee.xiaoheic.cultivation.affinity;

import dev.aprilvee.xiaoheic.data.datatype.IXiaoheiData;
import dev.aprilvee.xiaoheic.spell.tree.ISkillTree;
import net.minecraft.world.entity.player.Player;

public interface IAffinity extends IXiaoheiData {
	public void onPick(Player player);
	public boolean unlocked(Player player);
	public ISkillTree skilltree();
}
