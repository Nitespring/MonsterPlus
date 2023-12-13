package github.nitespring.monsterplus.common.entity;

import github.nitespring.monsterplus.config.CommonConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.dimension.DimensionType;

public class OvergrownSkeleton extends AbstractSkeleton{

	public OvergrownSkeleton(EntityType<? extends AbstractSkeleton> p_32133_, Level p_32134_) {
		super(p_32133_, p_32134_);
		
	}

	@Override
	protected SoundEvent getStepSound() {
		
		return SoundEvents.WOOD_STEP;
	}
	
	
	@Override
	protected void populateDefaultEquipmentSlots(RandomSource p_219154_, DifficultyInstance p_219155_) {
		
		
		int r = this.getRandom().nextInt(4)+1;
		
		if(r==1) {
			
			 this.setItemSlot(EquipmentSlot.MAINHAND, ItemStack.EMPTY);
			
		} else if(r==2){
		
	      this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.WOODEN_SWORD));
	      
		} else {
			
			this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.BOW));
			
		}
	   }
	
	 public static boolean checkOvergrownSkeletonSpawnRules(EntityType<? extends Monster> p_219014_, ServerLevelAccessor p_219015_, MobSpawnType p_219016_, BlockPos blockPos, RandomSource p_219018_) {
	      return p_219015_.getDifficulty() != Difficulty.PEACEFUL && isDarkEnoughToSpawn(p_219015_, blockPos, p_219018_) && checkMobSpawnRules(p_219014_, p_219015_, p_219016_, blockPos, p_219018_) 
	    		  && CommonConfig.spawn_overgrown_skeleton.get();
	   }
	 
	 
	 
	
	 
	 
	 @Override
		protected AbstractArrow getArrow(ItemStack p_33846_, float p_33847_) {
		      AbstractArrow abstractarrow = super.getArrow(p_33846_, p_33847_);
		      if (abstractarrow instanceof Arrow) {
		         ((Arrow)abstractarrow).addEffect(new MobEffectInstance(MobEffects.POISON, 200));
		      }

		      return abstractarrow;
		   }
		
		@Override
		public boolean doHurtTarget(Entity p_34169_) {
		      if (!super.doHurtTarget(p_34169_)) {
		         return false;
		      } else {
		         if (p_34169_ instanceof LivingEntity) {
		            ((LivingEntity)p_34169_).addEffect(new MobEffectInstance(MobEffects.POISON, 200), this);
		         }

		         return true;
		      }
		   }
		
		 public static  AttributeSupplier.Builder setCustomAttributes(){
				return Monster.createMonsterAttributes()
						.add(Attributes.MAX_HEALTH, 22.0D)
						.add(Attributes.MOVEMENT_SPEED, 0.25D)
						.add(Attributes.ATTACK_DAMAGE, 1)
						.add(Attributes.ATTACK_SPEED, 1.2D)
						.add(Attributes.ATTACK_KNOCKBACK, 1.0D)
						.add(Attributes.KNOCKBACK_RESISTANCE, 0.3D)
						.add(Attributes.FOLLOW_RANGE, 30);
		
			  }
		
		
		
		@Override
		 public boolean canFreeze() {
		      return false;
		   }
	 
	 
	 
	 @Override
	protected boolean isSunBurnTick() {
		
		return false;
	}
	 
	 
	 
	 

}
