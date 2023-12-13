package github.nitespring.monsterplus.common.entity.projectiles;

import java.util.Random;

import github.nitespring.monsterplus.common.entity.Abyssologer;
import github.nitespring.monsterplus.core.init.ItemInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.VoxelShape;

public class CrystalClump extends AbstractHurtingProjectile implements ItemSupplier{

	float damage = 8.0f;
	int livingTicks = 0;
	
	
	public CrystalClump(EntityType<? extends AbstractHurtingProjectile> p_36833_, Level p_36834_) {
		super(p_36833_, p_36834_);
		
	}
	
	

	@Override
	public ItemStack getItem() {
		
		return ItemInit.CRYSTAL_CLUMP.get().getDefaultInstance();
		
	}
	
	@Override
	protected boolean shouldBurn() {
		
		return false;
	}
	
	
@Override
protected ParticleOptions getTrailParticle() {
	
	return ParticleTypes.PORTAL;
}
	
	
	
	@Override
	protected void onHitEntity(EntityHitResult p_37259_) {
		super.onHitEntity(p_37259_);
		p_37259_.getEntity().hurt(this.level().damageSources().indirectMagic(this.getOwner(), this), 3.0f);
		this.createCrystals(Math.min(p_37259_.getEntity().getY(), this.getY()-0.5), false);
		this.remove(RemovalReason.DISCARDED); 
	}
	
	@Override
	protected void onHitBlock(BlockHitResult p_37258_) {
		super.onHitBlock(p_37258_);
		this.createCrystals(this.getY()-0.5, true);
		this.remove(RemovalReason.DISCARDED); 
	}
	
	private void createCrystals(double y, boolean flag){
		/*
		for(int k = 0; k<=2; k++) {
			
		         BlockPos blockpos = new BlockPos(this.getX(), y, this.getZ());
		         boolean flag1 = false;
		         double d0 = 0.0D;
                 if(!flag) {
		         do {
		            BlockPos blockpos1 = blockpos.below();
		            BlockState blockstate = this.level.getBlockState(blockpos1);
		            if (blockstate.isFaceSturdy(this.level, blockpos1, Direction.UP)) {
		               if (!this.level.isEmptyBlock(blockpos)) {
		                  BlockState blockstate1 = this.level.getBlockState(blockpos);
		                  VoxelShape voxelshape = blockstate1.getCollisionShape(this.level, blockpos);
		                  if (!voxelshape.isEmpty()) {
		                     d0 = voxelshape.max(Direction.Axis.Y);
		                  }
		               }

		               flag1 = true;
		               break;
		            }

		            blockpos = blockpos.below();
		         } while(blockpos.getY() >= Mth.floor(d0) - 1);
		         
                 }

		         if (flag1 || flag) { */
		for(int k = 0; k<=2; k++) {
		   this.level().addFreshEntity(new CrystalSpikes(this.level(), this.damage, this.position().x + new Random().nextFloat() - 0.5, /*blockpos.getY()*/ this.getY() + new Random().nextFloat() - 0.5, this.position().z + new Random().nextFloat() -0.5, this.yRotO, 0, (LivingEntity)this.getOwner()));
		}
		        	 
		        // }
			
		//}
	}	
	@Override
	public void tick() {
		
		Vec3 vec3 = this.getDeltaMovement();
	      if (this.xRotO == 0.0F && this.yRotO == 0.0F) {
	         double d0 = vec3.horizontalDistance();
	         this.setYRot((float)(Mth.atan2(vec3.x, vec3.z) * (double)(180F / (float)Math.PI)));
	         this.setXRot((float)(Mth.atan2(vec3.y, d0) * (double)(180F / (float)Math.PI)));
	         this.yRotO = this.getYRot();
	         this.xRotO = this.getXRot();
	      }
		livingTicks++;
		this.setDeltaMovement(vec3.x(), vec3.y() - 0.005*livingTicks, vec3.z());
		super.tick();
		
		
	}

}
