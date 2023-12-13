package com.nitespring.monsterplus.common.entity;

import java.util.Random;

import com.nitespring.monsterplus.common.entity.projectiles.SpikeCountdown;
import com.nitespring.monsterplus.config.CommonConfig;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.VoxelShape;

public class CrystalZombie extends Zombie{

	
	public CrystalZombie(EntityType<? extends Zombie> p_34271_, Level p_34272_) {
		super(p_34271_, p_34272_);
	}

	/*@Override
	public boolean canBreatheUnderwater() {
		
		return true;
	}*/
	
	 public static boolean checkCrystalZombieSpawnRules(EntityType<? extends Monster> p_219014_, ServerLevelAccessor p_219015_, MobSpawnType p_219016_, BlockPos blockPos, RandomSource p_219018_) {
	      return p_219015_.getDifficulty() != Difficulty.PEACEFUL && isDarkEnoughToSpawn(p_219015_, blockPos, p_219018_) && checkMobSpawnRules(p_219014_, p_219015_, p_219016_, blockPos, p_219018_) 
	    		  && blockPos.getY() <= 40 && CommonConfig.spawn_crystal_zombie.get();
	   }
	
	@Override
	protected boolean convertsInWater() {
		
		return false;
	}
	
	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(1, new CrystalZombie.RangedCrystalGoal());
		super.registerGoals();
		
		
	}
	
	
	 public static  AttributeSupplier.Builder setCustomAttributes(){
			return Monster.createMonsterAttributes()
					.add(Attributes.MAX_HEALTH, 36.0D)
					.add(Attributes.MOVEMENT_SPEED, 0.05D)
					.add(Attributes.ATTACK_DAMAGE, 5)
					.add(Attributes.ATTACK_SPEED, 1.0D)
					.add(Attributes.ATTACK_KNOCKBACK, 1.5D)
					.add(Attributes.KNOCKBACK_RESISTANCE, 2.0D)
					.add(Attributes.FOLLOW_RANGE, 30);
	
		  }
	 
	 @Override
	protected void populateDefaultEquipmentSlots(RandomSource p_219165_, DifficultyInstance p_219166_) {
		
	}
	 
	 
	 
	 public class RangedCrystalGoal extends Goal {

		 int cooldownTicks;
		 int spikeDelay = 15;
		 
		@Override
		public boolean canUse() {
			
			 LivingEntity livingentity = CrystalZombie.this.getTarget();
			 
	         if (livingentity != null && livingentity.isAlive()&& CrystalZombie.this.getSensing().hasLineOfSight(livingentity)) {
	           
	            return true;
	            
	         } else {
	            return false;
	         }
		}
		
		@Override
		 public boolean canContinueToUse() {
	         LivingEntity livingentity = CrystalZombie.this.getTarget();
	         return livingentity != null && livingentity.isAlive() && cooldownTicks>=-spikeDelay && CrystalZombie.this.isAlive();
	      }
		
		@Override
		public void start() {
			
			cooldownTicks=60;
			
		}
		
		@Override
		public void tick() {
			LivingEntity target = CrystalZombie.this.getTarget();
			cooldownTicks--;
			if(cooldownTicks==0) {
				for(int i = 0; i<=2; i++) {
				Vec3 targetPos = target.position();
				double d0 = Math.min(targetPos.y(), CrystalZombie.this.getY());
		         double d1 = Math.max(targetPos.y(), CrystalZombie.this.getY()) + 1.0D;
		         float f = (float)Mth.atan2(targetPos.z() - CrystalZombie.this.getZ(), targetPos.x() - CrystalZombie.this.getX());
		         
				createSpellEntity(targetPos.x + new Random().nextFloat(), targetPos.z + new Random().nextFloat(), d0, d1, f, 0);
				
				CrystalZombie.this.playSound(SoundEvents.EVOKER_CAST_SPELL);
				CrystalZombie.this.level().addParticle(ParticleTypes.DRAGON_BREATH, CrystalZombie.this.position().x, CrystalZombie.this.position().y + 1.5, CrystalZombie.this.position().z, 0, 0, 0);
				CrystalZombie.this.swing(InteractionHand.MAIN_HAND);
				}
				this.stop();
			}
			
			
			
			
		}
		
		
		
		
		
		
		 private void createSpellEntity(double p_32673_, double p_32674_, double p_32675_, double p_32676_, float p_32677_, int p_32678_) {
	         BlockPos blockpos = new BlockPos((int)p_32673_, (int)p_32676_, (int)p_32674_);
	         boolean flag = false;
	         double d0 = 0.0D;

	         do {
	            BlockPos blockpos1 = blockpos.below();
	            BlockState blockstate = CrystalZombie.this.level().getBlockState(blockpos1);
	            if (blockstate.isFaceSturdy(CrystalZombie.this.level(), blockpos1, Direction.UP)) {
	               if (!CrystalZombie.this.level().isEmptyBlock(blockpos)) {
	                  BlockState blockstate1 = CrystalZombie.this.level().getBlockState(blockpos);
	                  VoxelShape voxelshape = blockstate1.getCollisionShape(CrystalZombie.this.level(), blockpos);
	                  if (!voxelshape.isEmpty()) {
	                     d0 = voxelshape.max(Direction.Axis.Y);
	                  }
	               }

	               flag = true;
	               break;
	            }

	            blockpos = blockpos.below();
	         } while(blockpos.getY() >= Mth.floor(p_32675_) - 1);

	         if (flag) {
	        	 
	        	 CrystalZombie.this.level().addFreshEntity(new SpikeCountdown(CrystalZombie.this.level(), 3.0f, p_32673_, (double)blockpos.getY() + d0, p_32674_, p_32677_, p_32678_, CrystalZombie.this, spikeDelay));
	        	 
	        	 
	         }

	      }
	      
		
		
		
	 
	 }
	 

}
