package dev.aprilvee.xiaoheic.cultivation.type;

import dev.aprilvee.xiaoheic.data.IXiaoheiData;
import dev.aprilvee.xiaoheic.spell.tree.ISkillTree;

public interface IType extends IXiaoheiData {
	public void onPick();
	public boolean unlocked();
	public ISkillTree skilltree();
}
