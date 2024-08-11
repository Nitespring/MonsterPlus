package github.nitespring.monsterplus.common.item;

import java.util.List;

import github.nitespring.monsterplus.common.entity.projectiles.CrystalClump;
import github.nitespring.monsterplus.core.init.EntityInit;
import github.nitespring.monsterplus.core.init.ItemInit;
import net.minecraft.network.chat.Component;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class CrystalClumpItem extends Item{

	public CrystalClumpItem(Properties properties) {
		super(properties.stacksTo(16).rarity(Rarity.RARE));
	}
	

	
	
	@Override
	public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) {
		
		
		
		if(!worldIn.isClientSide) {
			
	    Vec3 pos = playerIn.position();
		Vec3 aim = playerIn.getLookAngle();	
			
		CrystalClump clump = new CrystalClump(EntityInit.CRYSTAL_CLUMP.get(), worldIn);
		clump.setPos(new Vec3(pos.x + aim.x*0.5f,pos.y + 1.75 + aim.y*0.5f,pos.z + aim.z*0.5f));
		
		//clump.shootFromRotation(playerIn, playerIn.getXRot(), playerIn.getYRot(), 0, 0.8f, 1.0f);
		clump.setDeltaMovement(aim.add(0,0.25f*aim.horizontalDistance(),0).normalize().scale(0.5f));
		clump.accelerationPower=0.5f;
		worldIn.addFreshEntity(clump);
		
		if(!playerIn.isCreative()) {
			playerIn.getItemInHand(handIn).shrink(1);
		}
		playerIn.awardStat(Stats.ITEM_USED.get(this));
		playerIn.getCooldowns().addCooldown(ItemInit.CRYSTAL_CLUMP.get(), 12);;
		
		}
		
		
		return super.use(worldIn, playerIn, handIn);
	}
	
   
	@OnlyIn(Dist.CLIENT)
	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag tooltipFlag) {
		
		String string = "\u00A77\u00A7oSummons Crystal Spikes from the ground when thrown";
		tooltip.add(Component.literal(string));
	}
	


	
	
	
}
