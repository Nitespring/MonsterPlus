package github.nitespring.monsterplus.common.item;



import github.nitespring.monsterplus.common.entity.projectiles.BloodySlashEntity;
import github.nitespring.monsterplus.core.init.EntityInit;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Evoker;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class GnawingJaw extends SwordItem implements ILeftClickSpecialActionItem{

	public GnawingJaw(Tier tier, Properties p) {
		super(tier,  p.attributes(SwordItem.createAttributes(Tiers.DIAMOND, 11, -2.5F)).stacksTo(1).rarity(Rarity.EPIC));
		
	}


	@Override
	public boolean isFoil(ItemStack p_41453_) {
		
		return true;
	}

	@Override
	public void doLeftClickAction(Player playerIn, ItemStack item) {
		Vec3 pos = playerIn.position().add(playerIn.getLookAngle().x()*2.5, 0.4, playerIn.getLookAngle().z()*2.5);
		
		Level levelIn = playerIn.level();
		BloodySlashEntity entity = new BloodySlashEntity(EntityInit.BLOODY_SLASH.get(), 
				levelIn, 
				pos, 
				4.0f,
				(float)Mth.atan2(pos.z - playerIn.getZ(), pos.x - playerIn.getX()));
		entity.setOwner(playerIn);
		levelIn.addFreshEntity(entity);
		
	//Evoker
	}
	
	 
	
	
	
}
