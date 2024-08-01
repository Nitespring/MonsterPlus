package github.nitespring.monsterplus.common.entity.projectiles;

import javax.annotation.Nullable;

import github.nitespring.monsterplus.core.init.ItemInit;
import net.minecraft.client.ParticleStatus;
import net.minecraft.commands.arguments.ParticleArgument;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

public class PurpleFireball extends AbstractHurtingProjectile implements ItemSupplier{
	
	float BaseDamage;
	
	
	public PurpleFireball(EntityType<? extends AbstractHurtingProjectile> p_36833_, Level p_36834_) {
	      super(p_36833_, p_36834_);
	   }

	public PurpleFireball(EntityType<? extends AbstractHurtingProjectile> p_36817_, double p_36818_, double p_36819_,
			double p_36820_, double p_36821_, double p_36822_, double p_36823_, Level p_36824_, @Nullable LivingEntity ownerIn, float damageIn) {
		super(p_36817_, p_36818_, p_36819_, p_36820_ , p_36824_);
		this.setDeltaMovement(p_36821_, p_36822_, p_36823_);
		this.BaseDamage = damageIn;
		this.setOwner(ownerIn);
	}

	@Override
	protected boolean shouldBurn() {
		
		return false;
	}
	
	
	@Override
	public void tick() {
		super.tick();
		this.playSound(SoundEvents.PORTAL_AMBIENT);
	}
	
	@Override
	protected void onHitEntity(EntityHitResult result) {
	
		Entity entity = result.getEntity();
		
		
		if(this.getOwner()!=null) {
		
		   if(!this.getOwner().isAlliedTo(entity)) {
			
			   this.doHurtTarget(entity);
			
		   }  	
		
		} else {
			
			this.doHurtTarget(entity);
			
		}
	}
	
	
	protected void doHurtTarget(Entity target) {
		
		target.hurt(this.level().damageSources().indirectMagic(this.getOwner(), target), BaseDamage);
		target.setRemainingFireTicks(this.getRemainingFireTicks() + 80);

		this.remove(RemovalReason.DISCARDED);
		
	}
	
	 @Override
		public boolean fireImmune() {
			
			return true;
		}
	
	
	
	@Override
	protected void onHitBlock(BlockHitResult p_37258_) {
		
		super.onHitBlock(p_37258_);
		
		this.remove(RemovalReason.DISCARDED);
		
		
	}
	
	@Override
	protected ParticleOptions getTrailParticle() {
		
		return ParticleTypes.PORTAL;
	}

	@Override
	public ItemStack getItem() {
		
		return ItemInit.PURPLE_FIREBALL.get().getDefaultInstance();
	}
	
	
	@Override
	protected boolean canHitEntity(Entity entity) {
		if(entity instanceof CrystalSpikes) {
			
			return false;
			
		}else {
		if(this.getOwner()!=null) {
		
		    return (!this.getOwner().isAlliedTo(entity));
		
		} else {
			
			return super.canHitEntity(entity);
		}
			
		}	
		
	}
	
	@Override
	protected void onHit(HitResult p_37260_) {
		
		super.onHit(p_37260_);
		
		this.playSound(SoundEvents.DRAGON_FIREBALL_EXPLODE);
		
	}
	
	
	

}
