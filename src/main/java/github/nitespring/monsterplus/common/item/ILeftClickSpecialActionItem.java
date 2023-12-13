package github.nitespring.monsterplus.common.item;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public interface ILeftClickSpecialActionItem {
	
	
	public abstract void doLeftClickAction(Player playerIn, ItemStack item);

}
