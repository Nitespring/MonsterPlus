package github.nitespring.monsterplus.common.item;

import java.util.List;

import github.nitespring.monsterplus.common.entity.projectiles.CrystalArrow;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.Nullable;

public class CrystalArrowItem extends ArrowItem{

	public CrystalArrowItem(Properties properties) {
		super(properties.rarity(Rarity.RARE));
	}
	


	@Override
	public AbstractArrow createArrow(Level level, ItemStack stack, LivingEntity e, @Nullable ItemStack stack1) {
		return new CrystalArrow(level, e, stack.copyWithCount(1),stack1);
	}



	//BowItem

    @OnlyIn(Dist.CLIENT)
   	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag tooltipFlag) {
   		
   		String string = "\u00A77\u00A7oSummons Crystal Spikes from the ground in the place where it hits";
   		tooltip.add(Component.literal(string));
   	}

       
}
