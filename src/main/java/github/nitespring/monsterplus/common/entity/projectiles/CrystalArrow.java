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
	
	public CrystalArrow(EntityType<? extends AbstractArrow> e, Level l) {
	      super(e, l);
	      
	   }

	   public CrystalArrow(Level level, LivingEntity e, ItemStack stack, ItemStack stack1) {
	      super(EntityInit.CRYSTAL_ARROW.get(), e,level, stack,stack1);

	   }



	@Override
	protected ItemStack getPickupItem() {
	
		return ItemInit.CRYSTAL_ARROW.get().getDefaultInstance();
	}

	@Override
	protected ItemStack getDefaultPickupItem() {
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
		
		for(int k = 0; k<5; k++) {
		   this.level().addFreshEntity(new CrystalSpikes(this.level(), 5.0f, this.position().x + 2.5f*(new Random().nextFloat() - 0.5), /*blockpos.getY()*/ this.getY() -0.5 /*new Random().nextFloat() - 0.5*/, this.position().z + 2.5f*(new Random().nextFloat() - 0.5), this.yRotO+new Random().nextFloat(), new Random().nextInt(5), (LivingEntity)this.getOwner()));
		}
		
	}

}
