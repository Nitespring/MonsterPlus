package github.nitespring.monsterplus.common.item;



import github.nitespring.monsterplus.common.entity.projectiles.BloodySlashEntity;
import github.nitespring.monsterplus.core.init.EntityInit;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Evoker;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class GnawingJaw extends SwordItem implements ILeftClickSpecialActionItem{

	public GnawingJaw(Tier p_43269_, int p_43270_, float p_43271_, Properties p_43272_) {
		super(p_43269_, p_43270_, p_43271_, p_43272_);
		
	}
	
	@Override
	public Rarity getRarity(ItemStack p_41461_) {
		
		return Rarity.EPIC;
		
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
