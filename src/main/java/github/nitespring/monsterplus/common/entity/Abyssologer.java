package github.nitespring.monsterplus.common.entity;

import java.util.Random;

import github.nitespring.monsterplus.common.entity.projectiles.PurpleFireball;
import github.nitespring.monsterplus.common.entity.projectiles.SpikeCountdown;
import github.nitespring.monsterplus.config.CommonConfig;
import github.nitespring.monsterplus.core.init.EntityInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;

import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.SpellcasterIllager;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.raid.Raider;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class Abyssologer extends CustomSpellcaster{

	public Abyssologer(EntityType<? extends SpellcasterIllager> p_33724_, Level p_33725_) {
		super(p_33724_, p_33725_);
		this.xpReward = 10;
	}


	@Override
	public boolean canJoinRaid() {
		return super.canJoinRaid();
	}

	@Override
	protected SoundEvent getCastingSoundEvent() {
		
		return SoundEvents.EVOKER_CAST_SPELL;
	}

	@Override
	public SoundEvent getCelebrateSound() {
		
		return SoundEvents.EVOKER_CELEBRATE;
	}
	@Override
	protected void registerGoals() {
	      super.registerGoals();
	      this.goalSelector.addGoal(0, new FloatGoal(this));
	      this.goalSelector.addGoal(1, new Abyssologer.CastingSpellGoal());
	      this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, Mob.class, 8.0F, 0.6D, 1.0D, (p_28879_) -> {
	          return p_28879_ == this.getTarget();
	      }));
	      this.goalSelector.addGoal(4, new Abyssologer.SummonEyeSpellGoal());
	      this.goalSelector.addGoal(4, new Abyssologer.SummonSkeletonSpellGoal());
	      this.goalSelector.addGoal(4, new Abyssologer.BlindnessSpellGoal());
	      this.goalSelector.addGoal(5, new Abyssologer.SpikeSpellGoal());
	      this.goalSelector.addGoal(5, new Abyssologer.FireballSpellGoal());
	      this.goalSelector.addGoal(8, new RandomStrollGoal(this, 0.6D));
	      this.goalSelector.addGoal(9, new LookAtPlayerGoal(this, Player.class, 3.0F, 1.0F));
	      this.goalSelector.addGoal(10, new LookAtPlayerGoal(this, Mob.class, 8.0F));
	      this.targetSelector.addGoal(1, (new HurtByTargetGoal(this, Raider.class)).setAlertOthers());
	      this.targetSelector.addGoal(2, (new NearestAttackableTargetGoal<>(this, Player.class, true)).setUnseenMemoryTicks(300));
	      this.targetSelector.addGoal(3, (new NearestAttackableTargetGoal<>(this, AbstractVillager.class, false)).setUnseenMemoryTicks(300));
	      this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolem.class, false));
	   }

	@Override
	public void applyRaidBuffs(ServerLevel serverLevel, int i, boolean b) {

	}

	public static AttributeSupplier.Builder createAttributes() {
	      return Monster.createMonsterAttributes()
	    		  .add(Attributes.MOVEMENT_SPEED, 0.5D)
	    		  .add(Attributes.MAX_HEALTH, 30.0D)
	              .add(Attributes.FOLLOW_RANGE, 24);
	   }
	 @Override
	 public boolean isAlliedTo(Entity p_32665_) {
	      if (p_32665_ == null) {
	         return false;
	      } else if (p_32665_ == this) {
	         return true;
	      } else if (super.isAlliedTo(p_32665_)) {
	         return true;
	      } else if (p_32665_ instanceof Eye) {
	         return this.isAlliedTo(((Eye)p_32665_).getOwner());
	      }  else if (p_32665_ instanceof SpectralSkeleton) {
		         return this.isAlliedTo(((SpectralSkeleton)p_32665_).getOwner());
	      } else if (p_32665_ instanceof LivingEntity && ((LivingEntity)p_32665_).getType().is(EntityTypeTags.ILLAGER)) {
	         return this.getTeam() == null && p_32665_.getTeam() == null;
	      } else {
	         return false;
	      }
	   }
	 
	 @Override
	 protected SoundEvent getAmbientSound() {
	      return SoundEvents.EVOKER_AMBIENT;
	   }
	 @Override
	   protected SoundEvent getDeathSound() {
	      return SoundEvents.EVOKER_DEATH;
	   }
	 @Override
	   protected SoundEvent getHurtSound(DamageSource p_32654_) {
	      return SoundEvents.EVOKER_HURT;
	   }
	 
	 public static boolean checkAbyssologerSpawnRules(EntityType<? extends Monster> p_219014_, ServerLevelAccessor p_219015_, MobSpawnType p_219016_, BlockPos blockPos, RandomSource p_219018_) {
	      return p_219015_.getDifficulty() != Difficulty.PEACEFUL && isDarkEnoughToSpawn(p_219015_, blockPos, p_219018_) && checkMobSpawnRules(p_219014_, p_219015_, p_219016_, blockPos, p_219018_) 
	    		  && blockPos.getY() <= 40 && CommonConfig.spawn_abyssologer.get();
	   }
	 
	 class CastingSpellGoal extends SpellcasterIllager.SpellcasterCastingSpellGoal {
	      public void tick() {
	         if (Abyssologer.this.getTarget() != null) {
	        	 Abyssologer.this.getLookControl().setLookAt(Abyssologer.this.getTarget(), (float)Abyssologer.this.getMaxHeadYRot(), (float)Abyssologer.this.getMaxHeadXRot());
	         } 

	      }
	   }
	 
	 class SummonEyeSpellGoal extends SpellcasterIllager.SpellcasterUseSpellGoal {
	      private final TargetingConditions eyeCountTargeting = TargetingConditions.forNonCombat().range(16.0D).ignoreLineOfSight().ignoreInvisibilityTesting();

	      public boolean canUse() {
	         if (!super.canUse()) {
	            return false;
	         } else {
	            int i = Abyssologer.this.level().getNearbyEntities(Eye.class, this.eyeCountTargeting, Abyssologer.this, Abyssologer.this.getBoundingBox().inflate(16.0D)).size();
	            return Abyssologer.this.random.nextInt(6) + 1 > i;
	         }
	      }

	      protected int getCastingTime() {
	         return 60;
	      }

	      protected int getCastingInterval() {
	         return 720;
	      }

	      protected void performSpellCasting() {
	         ServerLevel serverlevel = (ServerLevel)Abyssologer.this.level();

	         for(int i = 0; i < 3; ++i) {
	            BlockPos blockpos = Abyssologer.this.blockPosition().offset(-2 + Abyssologer.this.random.nextInt(5), 1, -2 + Abyssologer.this.random.nextInt(5));
	            Eye eye = EntityInit.EYE.get().create(Abyssologer.this.level());
	            eye.moveTo(blockpos, 0.0F, 0.0F);
	            eye.finalizeSpawn(serverlevel, Abyssologer.this.level().getCurrentDifficultyAt(blockpos), MobSpawnType.MOB_SUMMONED, (SpawnGroupData)null);
	            eye.setOwner(Abyssologer.this);
	            //eye.setBoundOrigin(blockpos);
	            eye.setLimitedLife(20 * (30 + Abyssologer.this.random.nextInt(90)));
	            serverlevel.addFreshEntityWithPassengers(eye);
	         }

	      }
	      

	      protected SoundEvent getSpellPrepareSound() {
	         return SoundEvents.EVOKER_PREPARE_SUMMON;
	      }

	      protected SpellcasterIllager.IllagerSpell getSpell() {
	         return SpellcasterIllager.IllagerSpell.SUMMON_VEX;
	      }
	   }
	 
	 
	 class SummonSkeletonSpellGoal extends SpellcasterIllager.SpellcasterUseSpellGoal {
	      private final TargetingConditions eyeCountTargeting = TargetingConditions.forNonCombat().range(16.0D).ignoreLineOfSight().ignoreInvisibilityTesting();

	      public boolean canUse() {
	         if (!super.canUse()) {
	            return false;
	         } else {
	            int i = Abyssologer.this.level().getNearbyEntities(SpectralSkeleton.class, this.eyeCountTargeting, Abyssologer.this, Abyssologer.this.getBoundingBox().inflate(16.0D)).size();
	            return Abyssologer.this.random.nextInt(5) + 1 > i;
	         }
	      }

	      protected int getCastingTime() {
	         return 60;
	      }

	      protected int getCastingInterval() {
	         return 560;
	      }

	      protected void performSpellCasting() {
	         ServerLevel serverlevel = (ServerLevel)Abyssologer.this.level();

	         for(int i = 0; i < 2; ++i) {
	            BlockPos blockpos = Abyssologer.this.blockPosition().offset(-2 + Abyssologer.this.random.nextInt(5), 1, -2 + Abyssologer.this.random.nextInt(5));
	            SpectralSkeleton skeleton = EntityInit.SPECTRAL_SKELETON.get().create(Abyssologer.this.level());
	            skeleton.moveTo(blockpos, 0.0F, 0.0F);
	            skeleton.finalizeSpawn(serverlevel, Abyssologer.this.level().getCurrentDifficultyAt(blockpos), MobSpawnType.MOB_SUMMONED, (SpawnGroupData)null);
	            skeleton.setOwner(Abyssologer.this);
	            //eye.setBoundOrigin(blockpos);
	            skeleton.setLimitedLife(20 * (30 + Abyssologer.this.random.nextInt(90)));
	            serverlevel.addFreshEntityWithPassengers(skeleton);
	         }

	      }
	      

	      protected SoundEvent getSpellPrepareSound() {
	         return SoundEvents.EVOKER_PREPARE_SUMMON;
	      }

	      protected SpellcasterIllager.IllagerSpell getSpell() {
	         return SpellcasterIllager.IllagerSpell.SUMMON_VEX;
	      }
	   }
	 
	 class SpikeSpellGoal extends SpellcasterIllager.SpellcasterUseSpellGoal {


		 @Override
		 public boolean canUse() {
			 if(!Abyssologer.this.isAlive()){
				 return false;
			 }else {
				 return super.canUse();
			 }
		 }

	      protected int getCastingTime() {
	         return 40;
	      }

	      protected int getCastingInterval() {
	         return 60;
	      }

	      protected void performSpellCasting() {
	         LivingEntity livingentity = Abyssologer.this.getTarget();
	         double d0 = Math.min(livingentity.getY(), Abyssologer.this.getY());
	         double d1 = Math.max(livingentity.getY(), Abyssologer.this.getY()) + 1.0D;
	         float f = (float)Mth.atan2(livingentity.getZ() - Abyssologer.this.getZ(), livingentity.getX() - Abyssologer.this.getX());
	         if (Abyssologer.this.distanceToSqr(livingentity) < 16.0D || (Abyssologer.this.distanceToSqr(livingentity) < 30.0D && Abyssologer.this.getRandom().nextBoolean())) {
	            for(int i = 0; i < 5; ++i) {
	               float f1 = f + (float)i * (float)Math.PI * 0.4F;
	               this.createSpellEntity(Abyssologer.this.getX() + (double)Mth.cos(f1) * 1.5D, Abyssologer.this.getZ() + (double)Mth.sin(f1) * 1.5D, d0, d1, f1, 1);
	            }
	            
	            for(int j = 0; j < 10; ++j) {
		               float f1 = f + (float)j * (float)Math.PI * 2.0F / 10.0F + 1.2566371F;
		               this.createSpellEntity(Abyssologer.this.getX() + (double)Mth.cos(f1) * 3.0D, Abyssologer.this.getZ() + (double)Mth.sin(f1) * 3.0D, d0, d1, f1, 2);
		            }

	            for(int k = 0; k < 15; ++k) {
	               float f2 = f + (float)k * (float)Math.PI * 2.0F / 15.0F + 2.2566371F;
	               this.createSpellEntity(Abyssologer.this.getX() + (double)Mth.cos(f2) * 4.5D, Abyssologer.this.getZ() + (double)Mth.sin(f2) * 4.5D, d0, d1, f2, 3);
	            }
	         } else {
	        	 
	        	 int randomPattern = new Random().nextInt(4) +1 ;
	        	 
	       // if(Abyssologer.this.getRandom().nextBoolean()) {
	        	 
	        	 switch(randomPattern) {
	        	 
	        	 case 1:
	        	 
	        	 
	            for(int l = 0; l < 16; ++l) {
	               double d2 = 1.25D * (double)(l + 1);
	               int j = 1 * l;
	               this.createSpellEntity(Abyssologer.this.getX() + (double)Mth.cos(f) * d2  + (new Random().nextFloat() - 0.5), Abyssologer.this.getZ() + (double)Mth.sin(f) * d2 + (new Random().nextFloat() - 0.5), d0, d1, f, j);
	            }
	            break;
	         //}else {
	        	 case 2:
	        	 this.createSpellEntity(Abyssologer.this.getTarget().getX() , Abyssologer.this.getTarget().getZ(), d0, d1, 0, 0);
	        	 
	        	 for(int i = 0; i < 5; ++i) {
		               float f1 = f + (float)i * (float)Math.PI * 0.4F;
		               this.createSpellEntity(Abyssologer.this.getTarget().getX() + (double)Mth.cos(f1) * 1.0D, Abyssologer.this.getTarget().getZ() + (double)Mth.sin(f1) * 1.0D, d0, d1, f1, 1);
		            }
		            
		            for(int j = 0; j < 8; ++j) {
			               float f1 = f + (float)j * (float)Math.PI * 2.0F / 8.0F + 1.2566371F;
			               this.createSpellEntity(Abyssologer.this.getTarget().getX() + (double)Mth.cos(f1) * 1.5D, Abyssologer.this.getTarget().getZ() + (double)Mth.sin(f1) * 1.5D, d0, d1, f1, 2);
			            }
	        	 
	        	 for(int k = 0; k < 25; ++k) {
		               float f2 = f + (float)k * (float)Math.PI * 2.0F / 25.0F + 2.566371F;
		               this.createSpellEntity(Abyssologer.this.getTarget().getX() + (double)Mth.cos(f2) * 5.0D, Abyssologer.this.getTarget().getZ() + (double)Mth.sin(f2) * 5.0D, d0, d1, f2, 3);
		            }
	        	 break;
	        	 
	        	 case 3:
	        		 
                     this.createSpellEntity(Abyssologer.this.getTarget().getX() , Abyssologer.this.getTarget().getZ(), d0, d1, 0, 0);
		        	 
		        	 for(int i = 0; i < 4; ++i) {
			               float f1 = f + (float)i * (float)Math.PI * 2.0F / 4.0F;
			               this.createSpellEntity(Abyssologer.this.getTarget().getX() + (double)Mth.cos(f1) * 0.75D, Abyssologer.this.getTarget().getZ() + (double)Mth.sin(f1) * 0.75D, d0, d1, f1, 1);
			            }
	        		 
	        		 for(int k = 0; k < 20; ++k) {
			               float f2 = f + (float)k * (float)Math.PI * 2.0F / 20.0F + 2.566371F;
			               this.createSpellEntity(Abyssologer.this.getTarget().getX() + (double)Mth.cos(f2) * 4.0D, Abyssologer.this.getTarget().getZ() + (double)Mth.sin(f2) * 4.0D, d0, d1, f2, 3);
			            } 
	            break;
	            
	        	 case 4:
	        		 
		        	 this.createSpellEntity(Abyssologer.this.getTarget().getX() , Abyssologer.this.getTarget().getZ(), d0, d1, 0, 0);
		        	 
		        	 for(int i = 0; i < 4; ++i) {
			               float f1 = f + (float)i * (float)Math.PI * 2.0F / 4.0F;
			               this.createSpellEntity(Abyssologer.this.getTarget().getX() + (double)Mth.cos(f1) * 0.75D, Abyssologer.this.getTarget().getZ() + (double)Mth.sin(f1) * 0.75D, d0, d1, f1, 1);
			            }
			            
			         for(int j = 0; j < 7; ++j) {
				            float f1 = f + (float)j * (float)Math.PI * 2.0F / 7.0F + 1.2566371F;
				            this.createSpellEntity(Abyssologer.this.getTarget().getX() + (double)Mth.cos(f1) * 1.25D, Abyssologer.this.getTarget().getZ() + (double)Mth.sin(f1) * 1.25D, d0, d1, f1, 2);
				        }
	        	 
	        	 
	             }
	         }

	      }

	      private void createSpellEntity(double p_32673_, double p_32674_, double p_32675_, double p_32676_, float p_32677_, int p_32678_) {
	         BlockPos blockpos = new BlockPos(new Vec3i((int)p_32673_, (int)p_32676_, (int)p_32674_));
	         boolean flag = false;
	         double d0 = 0.0D;

	         do {
	            BlockPos blockpos1 = blockpos.below();
	            BlockState blockstate = Abyssologer.this.level().getBlockState(blockpos1);
	            if (blockstate.isFaceSturdy(Abyssologer.this.level(), blockpos1, Direction.UP)) {
	               if (!Abyssologer.this.level().isEmptyBlock(blockpos)) {
	                  BlockState blockstate1 = Abyssologer.this.level().getBlockState(blockpos);
	                  VoxelShape voxelshape = blockstate1.getCollisionShape(Abyssologer.this.level(), blockpos);
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
	        	 //Abyssologer.this.level.addFreshEntity(new CrystalSpikes(Abyssologer.this.level, 2.0f, p_32673_, (double)blockpos.getY() + d0, p_32674_, p_32677_, p_32678_, Abyssologer.this));
	        	 
	        	 Abyssologer.this.level().addFreshEntity(new SpikeCountdown(Abyssologer.this.level(), 4.0f, p_32673_, (double)blockpos.getY() + d0, p_32674_, p_32677_, p_32678_, Abyssologer.this, 25));
	        	 
	        	 
	         }

	      }




		 protected SoundEvent getSpellPrepareSound() {
	         return SoundEvents.EVOKER_PREPARE_ATTACK;
	      }

	      protected SpellcasterIllager.IllagerSpell getSpell() {
	         return SpellcasterIllager.IllagerSpell.FANGS;
	      }
	   }
	 
	 
	 class FireballSpellGoal extends SpellcasterIllager.SpellcasterUseSpellGoal {
	      protected int getCastingTime() {
	         return 20;
	      }

	      protected int getCastingInterval() {
	         return 25;
	      }

		@Override
		protected void performSpellCasting() {
			
			
			
			Vec3 pos = Abyssologer.this.position();
			
			Vec3 posO = new Vec3(pos.x, pos.y + 2.5, pos.z);
			
			Vec3 pos1 = Abyssologer.this.getTarget().position();
			
			Vec3 posT = new Vec3(pos1.x, Abyssologer.this.getTarget().getY(0.5), pos1.z);
			
			double d0 = Math.sqrt((posT.x-posO.x)*(posT.x-posO.x) + (posT.y-posO.y)*(posT.y-posO.y) + (posT.z-posO.z)*(posT.z-posO.z));
			
			Vec3 aim = new Vec3((posT.x-posO.x)/d0,  (posT.y-posO.y)/d0,  (posT.z-posO.z)/d0);
			
			
			
			launchFireball(posO, aim, 4);
			
			
			
		}
		
		 private void launchFireball(Vec3 pos, Vec3 aim, float damage) {
	
			 PurpleFireball fireball = new PurpleFireball(EntityInit.PURPLE_FIREBALL.get(), pos.x(), pos.y(), pos.z(), aim.x()*0.5, aim.y()*0.5, aim.z()*0.5, level(), Abyssologer.this, damage);
			 
			 Abyssologer.this.level().addFreshEntity(fireball);
			 
			 Abyssologer.this.playSound(SoundEvents.PORTAL_TRAVEL, 0.5f, 0.75f);
		}
		
		

		@Override
		protected SoundEvent getSpellPrepareSound() {
			
			return SoundEvents.EVOKER_PREPARE_ATTACK;
		}

		@Override
		protected IllagerSpell getSpell() {
			
			return IllagerSpell.WOLOLO;
		}
	 }
	 
	 class BlindnessSpellGoal extends SpellcasterIllager.SpellcasterUseSpellGoal {
		 

	      public boolean canUse() {
	         if (!super.canUse()) {
	            return false;
	         } else {
	           
	            return CommonConfig.do_abyssologer_darkness.get() && !Abyssologer.this.getTarget().hasEffect(MobEffects.DARKNESS);
	         }
	      }
		 
		 
	      protected int getCastingTime() {
	         return 35;
	      }

	      protected int getCastingInterval() {
	         return 720;
	      }

		@Override
		protected void performSpellCasting() {
			

		      
			Abyssologer.this.getTarget().addEffect(new MobEffectInstance(MobEffects.DARKNESS, 700));
			
			
			
			
			
		}
		
		
		
		
		

		@Override
		protected SoundEvent getSpellPrepareSound() {
			
			return SoundEvents.EVOKER_PREPARE_ATTACK;
		}

		@Override
		protected IllagerSpell getSpell() {
			
			return IllagerSpell.BLINDNESS;
		}
	 }

}
