package com.nitespring.monsterplus.common.entity;

import javax.annotation.Nullable;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.Entity.RemovalReason;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.TargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class SpectralSkeleton extends AbstractSkeleton{

	 @Nullable
	   Mob owner;
	 
	 private boolean hasLimitedLife;
	   private int limitedLifeTicks;
	
	
	
	public SpectralSkeleton(EntityType<? extends AbstractSkeleton> p_32133_, Level p_32134_) {
		super(p_32133_, p_32134_);
		
	}
	
	@Nullable
	   public Mob getOwner() {
	      return this.owner;
	   }
	
	 
	 public void setOwner(Mob p_33995_) {
	      this.owner = p_33995_;
	   }

	   public void setLimitedLife(int p_33988_) {
	      this.hasLimitedLife = true;
	      this.limitedLifeTicks = p_33988_;
	   }

	@Override
	protected SoundEvent getStepSound() {
		
		return SoundEvents.SKELETON_STEP;
	}
	
	 public static  AttributeSupplier.Builder setCustomAttributes(){
			return Monster.createMonsterAttributes()
					.add(Attributes.MAX_HEALTH, 12.0D)
					.add(Attributes.MOVEMENT_SPEED, 0.25D)
					.add(Attributes.ATTACK_DAMAGE, 3)
					.add(Attributes.ATTACK_SPEED, 1.0D)
					.add(Attributes.ATTACK_KNOCKBACK, 0.7D)
					.add(Attributes.KNOCKBACK_RESISTANCE, 0.0D)
					.add(Attributes.FOLLOW_RANGE, 30);
	
		  }
	 
	 @Override
		public void tick() {
			
			super.tick();
			
			 if (this.hasLimitedLife && --this.limitedLifeTicks <= 0) {
		         this.remove(RemovalReason.DISCARDED);
		         
		      }
		}
	 @Override
	protected boolean isSunBurnTick() {
		
		return false;
	}
	 @Override
		protected void registerGoals() {
			super.registerGoals();
		  
		      this.targetSelector.addGoal(2, new SpectralSkeleton.CopyOwnerTargetGoal(this));
		     
		   }
	 
	 
	 @Override
	protected void populateDefaultEquipmentSlots(RandomSource p_218949_, DifficultyInstance p_218950_) {
		
		 this.setItemSlot(EquipmentSlot.MAINHAND, ItemStack.EMPTY);
	}
	 
	 
     class CopyOwnerTargetGoal extends TargetGoal {
  	      private final TargetingConditions copyOwnerTargeting = TargetingConditions.forNonCombat().ignoreLineOfSight().ignoreInvisibilityTesting();

  	      public CopyOwnerTargetGoal(PathfinderMob p_34056_) {
  	         super(p_34056_, false);
  	      }

  	      public boolean canUse() {
  	         return SpectralSkeleton.this.owner != null && SpectralSkeleton.this.owner.getTarget() != null && this.canAttack(SpectralSkeleton.this.owner.getTarget(), this.copyOwnerTargeting);
  	      }

  	      public void start() {
  	    	SpectralSkeleton.this.setTarget(SpectralSkeleton.this.owner.getTarget());
  	         super.start();
  	      }
  	   }

}
