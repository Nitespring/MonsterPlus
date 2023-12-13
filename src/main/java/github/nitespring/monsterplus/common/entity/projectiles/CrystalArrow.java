package github.nitespring.monsterplus.common.entity.projectiles;

import java.util.Random;

import github.nitespring.monsterplus.core.init.EntityInit;
import github.nitespring.monsterplus.core.init.ItemInit;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity.RemovalReason;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.SpectralArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;

public class CrystalArrow extends AbstractArrow{
	
	public CrystalArrow(EntityType<? extends AbstractArrow> p_37411_, Level p_37412_) {
	      super(p_37411_, p_37412_,null);
	      
	   }

	   public CrystalArrow(Level p_37419_, LivingEntity p_37420_) {
	      super(EntityInit.CRYSTAL_ARROW.get(), p_37420_, p_37419_,null);
	   }

	   public CrystalArrow(Level p_37414_, double p_37415_, double p_37416_, double p_37417_) {
	      super(EntityInit.CRYSTAL_ARROW.get(), p_37415_, p_37416_, p_37417_, p_37414_,null);
	   }

	@Override
	protected ItemStack getPickupItem() {
	
		return ItemInit.CRYSTAL_ARROW.get().getDefaultInstance();
	}
	
	
	
	

	@Override
	protected void onHitEntity(EntityHitResult p_37259_) {
		p_37259_.getEntity().hurt(this.level().damageSources().indirectMagic(this.getOwner(), this), (float) 1.0f);
		this.createCrystals();
		super.onHitEntity(p_37259_);
	}
	
	@Override
	protected void onHitBlock(BlockHitResult p_37258_) {
		this.createCrystals();
		super.onHitBlock(p_37258_);
		this.discard();
	}
	
	private void createCrystals(){
		
		for(int k = 0; k<=2; k++) {
		   this.level().addFreshEntity(new CrystalSpikes(this.level(), 5.0f, this.position().x + new Random().nextFloat() - 0.5, /*blockpos.getY()*/ this.getY() -0.5 /*new Random().nextFloat() - 0.5*/, this.position().z + new Random().nextFloat() -0.5, this.yRotO, 0, (LivingEntity)this.getOwner()));
		}
		
	}

}
