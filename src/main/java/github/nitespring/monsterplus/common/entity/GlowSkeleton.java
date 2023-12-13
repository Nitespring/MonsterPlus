package github.nitespring.monsterplus.common.entity;

import github.nitespring.monsterplus.config.CommonConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.GlowSquid;
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
import net.minecraft.world.level.ServerLevelAccessor;

public class GlowSkeleton extends AbstractSkeleton{

	//GlowSquid
	//Skeleton
	//WitherSkeleton
	//Stray
	
	private static final EntityDataAccessor<Integer> DATA_BRIGHT_TICKS_REMAINING = SynchedEntityData.defineId(GlowSquid.class, EntityDataSerializers.INT);
	
	
	
	public GlowSkeleton(EntityType<? extends GlowSkeleton> p_33570_, Level p_33571_) {
		super(p_33570_, p_33571_);
	}
	
	protected void defineSynchedData() {
	      super.defineSynchedData();
	      this.entityData.define(DATA_BRIGHT_TICKS_REMAINING, 0);
	   }
	
	public void aiStep() {
	      super.aiStep();
	      int i = this.getBrightTicksRemaining();
	      if (i > 0) {
	         this.setBrightTicks(i - 1);
	      }

	      this.level().addParticle(ParticleTypes.GLOW, this.getRandomX(0.6D), this.getRandomY(), this.getRandomZ(0.6D), 0.0, 0.0, 0.0);
	   }

	   public boolean hurt(DamageSource p_147114_, float p_147115_) {
	      boolean flag = super.hurt(p_147114_, p_147115_);
	      if (flag) {
	         this.setBrightTicks(100);
	      }

	      return flag;
	   }

	   private void setBrightTicks(int p_147120_) {
	      this.entityData.set(DATA_BRIGHT_TICKS_REMAINING, p_147120_);
	   }

	   public int getBrightTicksRemaining() {
	      return this.entityData.get(DATA_BRIGHT_TICKS_REMAINING);
	   }

	
	@Override
	protected void populateDefaultEquipmentSlots(RandomSource p_219154_, DifficultyInstance p_219155_) {
		
		
		boolean r = this.getRandom().nextBoolean();
		
		if(r) {
			
			 this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.BOW));
			
		} else {
		
	      this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.STONE_SWORD));
	      
		}
	   }
	
	 public static boolean checkGlowSkeletonSpawnRules(EntityType<? extends Monster> p_219014_, ServerLevelAccessor p_219015_, MobSpawnType p_219016_, BlockPos blockPos, RandomSource p_219018_) {
	      return p_219015_.getDifficulty() != Difficulty.PEACEFUL && isDarkEnoughToSpawn(p_219015_, blockPos, p_219018_) && checkMobSpawnRules(p_219014_, p_219015_, p_219016_, blockPos, p_219018_) 
	    		  && blockPos.getY() <= 40 && CommonConfig.spawn_glow_skeleton.get();
	   }
	 
	
	
	@Override
	protected AbstractArrow getArrow(ItemStack p_33846_, float p_33847_) {
	      AbstractArrow abstractarrow = super.getArrow(p_33846_, p_33847_);
	      if (abstractarrow instanceof Arrow) {
	         ((Arrow)abstractarrow).addEffect(new MobEffectInstance(MobEffects.GLOWING, 600));
	      }

	      return abstractarrow;
	   }
	
	@Override
	public boolean doHurtTarget(Entity p_34169_) {
	      if (!super.doHurtTarget(p_34169_)) {
	         return false;
	      } else {
	         if (p_34169_ instanceof LivingEntity) {
	            ((LivingEntity)p_34169_).addEffect(new MobEffectInstance(MobEffects.GLOWING, 600), this);
	         }

	         return true;
	      }
	   }
	
	 public static  AttributeSupplier.Builder setCustomAttributes(){
			return Monster.createMonsterAttributes()
					.add(Attributes.MAX_HEALTH, 24.0D)
					.add(Attributes.MOVEMENT_SPEED, 0.25D)
					.add(Attributes.ATTACK_DAMAGE, 2)
					.add(Attributes.ATTACK_SPEED, 1.2D)
					.add(Attributes.ATTACK_KNOCKBACK, 1.0D)
					.add(Attributes.KNOCKBACK_RESISTANCE, 0.7D)
					.add(Attributes.FOLLOW_RANGE, 30);
	
		  }
	
	
	
	@Override
	 public boolean canFreeze() {
	      return false;
	   }
	@Override
	   protected SoundEvent getAmbientSound() {
	      return SoundEvents.SKELETON_AMBIENT;
	   }
	@Override
	   protected SoundEvent getHurtSound(DamageSource p_33579_) {
	      return SoundEvents.SKELETON_HURT;
	   }
	@Override
	   protected SoundEvent getDeathSound() {
	      return SoundEvents.SKELETON_DEATH;
	   }
	@Override
	   protected SoundEvent getStepSound() {
	      return SoundEvents.SKELETON_STEP;
	   }

	 
	   
	

}
