package github.nitespring.monsterplus.common.entity;

import github.nitespring.monsterplus.core.init.ItemInit;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.target.TargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.WitherSkeleton;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;

public class AncientHero extends AbstractSkeleton{







	public AncientHero(EntityType<? extends AbstractSkeleton> p_32133_, Level p_32134_) {
		super(p_32133_, p_32134_);
		
	}

	@Override
	public boolean canBeAffected(MobEffectInstance pPotioneffect) {
		return pPotioneffect.is(MobEffects.WITHER) ? false : super.canBeAffected(pPotioneffect);
	}



	@Override
	protected SoundEvent getStepSound() {
		
		return SoundEvents.SKELETON_STEP;
	}
	
	 public static  AttributeSupplier.Builder setCustomAttributes(){
			return Monster.createMonsterAttributes()
					.add(Attributes.MAX_HEALTH, 100.0D)
					.add(Attributes.MOVEMENT_SPEED, 0.27D)
					.add(Attributes.ATTACK_DAMAGE, 8)
					.add(Attributes.ATTACK_SPEED, 1.0D)
					.add(Attributes.ATTACK_KNOCKBACK, 0.7D)
					.add(Attributes.KNOCKBACK_RESISTANCE, 1.0D)
					.add(Attributes.ARMOR, 12.0D)
					.add(Attributes.ARMOR_TOUGHNESS, 4.0D)
					.add(Attributes.FOLLOW_RANGE, 30)
					.add(Attributes.STEP_HEIGHT, 2.0D)
					.add(Attributes.FALL_DAMAGE_MULTIPLIER, 0.1D)
					.add(Attributes.GRAVITY, 0.4D)
					.add(Attributes.MOVEMENT_EFFICIENCY, 1.0D)
					.add(Attributes.WATER_MOVEMENT_EFFICIENCY, 1.0D);
	
		  }
	 
	 @Override
		public void tick() {
			
			super.tick();

		}
	 @Override
	protected boolean isSunBurnTick() {
		return false;
	}

	@Override
	public boolean fireImmune() {
		return true;
	}

	@Override
		protected void registerGoals() {
			super.registerGoals();
		     
		   }
	 

	@Override
	protected void populateDefaultEquipmentSlots(RandomSource p_218949_, DifficultyInstance p_218950_) {
		
		 this.setItemSlot(EquipmentSlot.MAINHAND, ItemInit.ANCIENT_BROKEN_SWORD.get().getDefaultInstance());
	}
	public void aiStep() {
		super.aiStep();


		this.level().addParticle(ParticleTypes.SOUL, this.getRandomX(0.6D), this.getRandomY(), this.getRandomZ(0.6D), 0.0, 0.0, 0.0);
	}
	 


}
