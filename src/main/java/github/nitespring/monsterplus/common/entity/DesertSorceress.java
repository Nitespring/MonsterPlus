package github.nitespring.monsterplus.common.entity;

import github.nitespring.monsterplus.common.entity.projectiles.Flame;
import github.nitespring.monsterplus.common.entity.projectiles.SpikeCountdown;
import github.nitespring.monsterplus.config.CommonConfig;
import github.nitespring.monsterplus.core.init.EntityInit;
import github.nitespring.monsterplus.core.init.SoundInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.SpellcasterIllager;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.LargeFireball;
import net.minecraft.world.entity.projectile.SmallFireball;
import net.minecraft.world.entity.raid.Raider;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class DesertSorceress extends SpellcasterIllager{

	public DesertSorceress(EntityType<? extends SpellcasterIllager> p_33724_, Level p_33725_) {
		super(p_33724_, p_33725_);
		this.xpReward = 15;
	}

	@Nullable
	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficultyInstance, MobSpawnType spawnType, @Nullable SpawnGroupData groupData) {
		return super.finalizeSpawn(level, difficultyInstance, spawnType, groupData);
	}

	@Override
	public boolean canJoinRaid() {
		return super.canJoinRaid();
	}

	@Override
	protected SoundEvent getCastingSoundEvent() {

		return SoundInit.DESERT_SORCERESS_CAST.get();
	}

	@Override
	public SoundEvent getCelebrateSound() {

		return SoundInit.DESERT_SORCERESS_CELEBRATE.get();
	}
	@Override
	protected void registerGoals() {
	      super.registerGoals();
	      this.goalSelector.addGoal(0, new FloatGoal(this));
	      this.goalSelector.addGoal(1, new DesertSorceress.CastingSpellGoal());
	      this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, Mob.class, 8.0F, 0.6D, 1.0D, (p_28879_) -> {
	          return p_28879_ == this.getTarget();
	      }));
	      this.goalSelector.addGoal(5, new DesertSorceress.FlameSpellGoal());
	      this.goalSelector.addGoal(5, new DesertSorceress.FireballSpellGoal());
		this.goalSelector.addGoal(5, new DesertSorceress.FireballBarrageSpellGoal());

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
	public boolean fireImmune() {
		return true;
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
	  } else if (p_32665_ instanceof DesertSorceress||p_32665_ instanceof DesertAcolyte) {
		  return this.getTeam() == null && p_32665_.getTeam() == null;
	  } else if (p_32665_ instanceof LivingEntity && ((LivingEntity)p_32665_).getType().is(EntityTypeTags.ILLAGER)) {
		 return this.getTeam() == null && p_32665_.getTeam() == null;
	  } else {
		 return false;
	  }
	}
	 
	@Override
	protected SoundEvent getAmbientSound() {return SoundInit.DESERT_SORCERESS_AMBIENT.get();}
	@Override
	protected SoundEvent getDeathSound() {
	  return SoundInit.DESERT_SORCERESS_DEATH.get();
	}
	@Override
	protected SoundEvent getHurtSound(DamageSource p_32654_) {return SoundInit.DESERT_SORCERESS_HURT.get();}
	 
	 public static boolean checkDesertSorceressSpawnRules(EntityType<? extends Monster> p_219014_, ServerLevelAccessor p_219015_, MobSpawnType p_219016_, BlockPos blockPos, RandomSource p_219018_) {
	      return p_219015_.getDifficulty() != Difficulty.PEACEFUL && isDarkEnoughToSpawn(p_219015_, blockPos, p_219018_) && checkMobSpawnRules(p_219014_, p_219015_, p_219016_, blockPos, p_219018_) 
	    		  && CommonConfig.spawn_desert_sorceress.get();
	   }
	 
	 class CastingSpellGoal extends SpellcasterCastingSpellGoal {
	      public void tick() {
	         if (DesertSorceress.this.getTarget() != null) {
	        	 DesertSorceress.this.getLookControl().setLookAt(DesertSorceress.this.getTarget(), (float) DesertSorceress.this.getMaxHeadYRot(), (float) DesertSorceress.this.getMaxHeadXRot());
	         } 

	      }
	   }
	 

	 
	 class FlameWallSpellGoal extends SpellcasterUseSpellGoal {


		 @Override
		 public boolean canUse() {
			 if(!DesertSorceress.this.isAlive()){
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
	         LivingEntity livingentity = DesertSorceress.this.getTarget();
	         double d0 = Math.min(livingentity.getY(), DesertSorceress.this.getY());
	         double d1 = Math.max(livingentity.getY(), DesertSorceress.this.getY()) + 1.0D;
	         float f = (float)Mth.atan2(livingentity.getZ() - DesertSorceress.this.getZ(), livingentity.getX() - DesertSorceress.this.getX());
	         if (DesertSorceress.this.distanceToSqr(livingentity) < 16.0D || (DesertSorceress.this.distanceToSqr(livingentity) < 30.0D && DesertSorceress.this.getRandom().nextBoolean())) {
	            for(int i = 0; i < 5; ++i) {
	               float f1 = f + (float)i * (float)Math.PI * 0.4F;
	               this.createSpellEntity(DesertSorceress.this.getX() + (double)Mth.cos(f1) * 1.5D, DesertSorceress.this.getZ() + (double)Mth.sin(f1) * 1.5D, d0, d1, f1, 1);
	            }
	            
	            for(int j = 0; j < 10; ++j) {
		               float f1 = f + (float)j * (float)Math.PI * 2.0F / 10.0F + 1.2566371F;
		               this.createSpellEntity(DesertSorceress.this.getX() + (double)Mth.cos(f1) * 3.0D, DesertSorceress.this.getZ() + (double)Mth.sin(f1) * 3.0D, d0, d1, f1, 2);
		            }

	            for(int k = 0; k < 15; ++k) {
	               float f2 = f + (float)k * (float)Math.PI * 2.0F / 15.0F + 2.2566371F;
	               this.createSpellEntity(DesertSorceress.this.getX() + (double)Mth.cos(f2) * 4.5D, DesertSorceress.this.getZ() + (double)Mth.sin(f2) * 4.5D, d0, d1, f2, 3);
	            }
	         } else {
	        	 
	        	 int randomPattern = new Random().nextInt(4) +1 ;
	        	 
	       // if(Abyssologer.this.getRandom().nextBoolean()) {
	        	 
	        	 switch(randomPattern) {
	        	 
	        	 case 1:
	        	 
	        	 
	            for(int l = 0; l < 16; ++l) {
	               double d2 = 1.25D * (double)(l + 1);
	               int j = 1 * l;
	               this.createSpellEntity(DesertSorceress.this.getX() + (double)Mth.cos(f) * d2  + (new Random().nextFloat() - 0.5), DesertSorceress.this.getZ() + (double)Mth.sin(f) * d2 + (new Random().nextFloat() - 0.5), d0, d1, f, j);
	            }
	            break;
	         //}else {
	        	 case 2:
	        	 this.createSpellEntity(DesertSorceress.this.getTarget().getX() , DesertSorceress.this.getTarget().getZ(), d0, d1, 0, 0);
	        	 
	        	 for(int i = 0; i < 5; ++i) {
		               float f1 = f + (float)i * (float)Math.PI * 0.4F;
		               this.createSpellEntity(DesertSorceress.this.getTarget().getX() + (double)Mth.cos(f1) * 1.0D, DesertSorceress.this.getTarget().getZ() + (double)Mth.sin(f1) * 1.0D, d0, d1, f1, 1);
		            }
		            
		            for(int j = 0; j < 8; ++j) {
			               float f1 = f + (float)j * (float)Math.PI * 2.0F / 8.0F + 1.2566371F;
			               this.createSpellEntity(DesertSorceress.this.getTarget().getX() + (double)Mth.cos(f1) * 1.5D, DesertSorceress.this.getTarget().getZ() + (double)Mth.sin(f1) * 1.5D, d0, d1, f1, 2);
			            }
	        	 
	        	 for(int k = 0; k < 25; ++k) {
		               float f2 = f + (float)k * (float)Math.PI * 2.0F / 25.0F + 2.566371F;
		               this.createSpellEntity(DesertSorceress.this.getTarget().getX() + (double)Mth.cos(f2) * 5.0D, DesertSorceress.this.getTarget().getZ() + (double)Mth.sin(f2) * 5.0D, d0, d1, f2, 3);
		            }
	        	 break;
	        	 
	        	 case 3:
	        		 
                     this.createSpellEntity(DesertSorceress.this.getTarget().getX() , DesertSorceress.this.getTarget().getZ(), d0, d1, 0, 0);
		        	 
		        	 for(int i = 0; i < 4; ++i) {
			               float f1 = f + (float)i * (float)Math.PI * 2.0F / 4.0F;
			               this.createSpellEntity(DesertSorceress.this.getTarget().getX() + (double)Mth.cos(f1) * 0.75D, DesertSorceress.this.getTarget().getZ() + (double)Mth.sin(f1) * 0.75D, d0, d1, f1, 1);
			            }
	        		 
	        		 for(int k = 0; k < 20; ++k) {
			               float f2 = f + (float)k * (float)Math.PI * 2.0F / 20.0F + 2.566371F;
			               this.createSpellEntity(DesertSorceress.this.getTarget().getX() + (double)Mth.cos(f2) * 4.0D, DesertSorceress.this.getTarget().getZ() + (double)Mth.sin(f2) * 4.0D, d0, d1, f2, 3);
			            } 
	            break;
	            
	        	 case 4:
	        		 
		        	 this.createSpellEntity(DesertSorceress.this.getTarget().getX() , DesertSorceress.this.getTarget().getZ(), d0, d1, 0, 0);
		        	 
		        	 for(int i = 0; i < 4; ++i) {
			               float f1 = f + (float)i * (float)Math.PI * 2.0F / 4.0F;
			               this.createSpellEntity(DesertSorceress.this.getTarget().getX() + (double)Mth.cos(f1) * 0.75D, DesertSorceress.this.getTarget().getZ() + (double)Mth.sin(f1) * 0.75D, d0, d1, f1, 1);
			            }
			            
			         for(int j = 0; j < 7; ++j) {
				            float f1 = f + (float)j * (float)Math.PI * 2.0F / 7.0F + 1.2566371F;
				            this.createSpellEntity(DesertSorceress.this.getTarget().getX() + (double)Mth.cos(f1) * 1.25D, DesertSorceress.this.getTarget().getZ() + (double)Mth.sin(f1) * 1.25D, d0, d1, f1, 2);
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
	            BlockState blockstate = DesertSorceress.this.level().getBlockState(blockpos1);
	            if (blockstate.isFaceSturdy(DesertSorceress.this.level(), blockpos1, Direction.UP)) {
	               if (!DesertSorceress.this.level().isEmptyBlock(blockpos)) {
	                  BlockState blockstate1 = DesertSorceress.this.level().getBlockState(blockpos);
	                  VoxelShape voxelshape = blockstate1.getCollisionShape(DesertSorceress.this.level(), blockpos);
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
	        	 
	        	 DesertSorceress.this.level().addFreshEntity(new SpikeCountdown(DesertSorceress.this.level(), 4.0f, p_32673_, (double)blockpos.getY() + d0, p_32674_, p_32677_, p_32678_, DesertSorceress.this, 25));
	        	 
	        	 
	         }

	      }

		 protected SoundEvent getSpellPrepareSound() {
	         return SoundEvents.EVOKER_PREPARE_ATTACK;
	      }

	      protected IllagerSpell getSpell() {
	         return IllagerSpell.FANGS;
	      }
	   }
	 
	 
	 class FireballSpellGoal extends SpellcasterUseSpellGoal {
	      protected int getCastingTime() {
	         return 50;
	      }

	      protected int getCastingInterval() {
	         return 120;
	      }

		@Override
		protected void performSpellCasting() {
			
			
			
			Vec3 pos = DesertSorceress.this.position();
			
			Vec3 posO = new Vec3(pos.x, pos.y + 2.5, pos.z);
			
			Vec3 pos1 = DesertSorceress.this.getTarget().position();
			
			Vec3 posT = new Vec3(pos1.x, DesertSorceress.this.getTarget().getY(0.5), pos1.z);
			
			double d0 = Math.sqrt((posT.x-posO.x)*(posT.x-posO.x) + (posT.y-posO.y)*(posT.y-posO.y) + (posT.z-posO.z)*(posT.z-posO.z));
			
			Vec3 aim = new Vec3((posT.x-posO.x)/d0,  (posT.y-posO.y)/d0,  (posT.z-posO.z)/d0);
			
			
			
			launchFireball(posO, aim, 4);
			
			
			
		}
		
		 private void launchFireball(Vec3 pos, Vec3 aim, float damage) {
	
			 LargeFireball fireball = new LargeFireball(EntityType.FIREBALL,level());
			 fireball.setPos(pos.x,pos.y,pos.z);
			 fireball.setDeltaMovement(aim.x*0.5f,aim.y*0.5f,aim.z*0.5f);
			 fireball.setOwner(DesertSorceress.this);
			 DesertSorceress.this.level().addFreshEntity(fireball);
			 
			 DesertSorceress.this.playSound(SoundEvents.FIRE_AMBIENT, 0.5f, 0.75f);
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
	class FlameSpellGoal extends SpellcasterUseSpellGoal {
		protected int getCastingTime() {
			return 35;
		}
		protected int getTotalCastingTime() {
			return getCastWarmupTime()+getCastingTime();
		}

		protected int getCastingInterval() {
			return 80;
		}

		@Override
		protected int getCastWarmupTime() {
			return 20;
		}

		@Override
		public boolean canUse() {
			LivingEntity livingentity = DesertSorceress.this.getTarget();
			if (livingentity == null || !livingentity.isAlive()||livingentity.distanceTo(DesertSorceress.this)>=10) {
				return false;
			} else {
				return DesertSorceress.this.isCastingSpell() ? false : DesertSorceress.this.tickCount >= this.nextAttackTickCount;
			}
		}

		@Override
		public boolean canContinueToUse() {
			LivingEntity livingentity = DesertSorceress.this.getTarget();
			return livingentity != null && livingentity.isAlive() && this.attackWarmupDelay > 0;
		}

		@Override
		public void start() {
			this.attackWarmupDelay = this.adjustedTickDelay(this.getTotalCastingTime());
			DesertSorceress.this.spellCastingTickCount = this.getTotalCastingTime()-2;
			this.nextAttackTickCount = DesertSorceress.this.tickCount + this.getCastingInterval();
			SoundEvent soundevent = this.getSpellPrepareSound();
			if (soundevent != null) {
				DesertSorceress.this.playSound(soundevent, 1.0F, 1.0F);
			}

			DesertSorceress.this.setIsCastingSpell(this.getSpell());
		}


		@Override
		public void tick() {
			this.attackWarmupDelay--;
			if (this.attackWarmupDelay == getCastingTime()) {
				this.performSpellCasting();
				DesertSorceress.this.playSound(DesertSorceress.this.getCastingSoundEvent(), 1.0F, 1.0F);
			}
			if (this.attackWarmupDelay <= getCastingTime()) {
				if (attackWarmupDelay%3==0){
					this.performSpellCasting();
				}
			}
		}

		@Override
		protected void performSpellCasting() {

			Vec3 pos = DesertSorceress.this.position();

			Vec3 posO = new Vec3(pos.x, pos.y + 2.5, pos.z);

			Vec3 pos1 = DesertSorceress.this.getTarget().position();

			Vec3 posT = new Vec3(pos1.x, DesertSorceress.this.getTarget().getY(0.5)+0.5f, pos1.z);

			double d0 = Math.sqrt((posT.x-posO.x)*(posT.x-posO.x) + (posT.y-posO.y)*(posT.y-posO.y) + (posT.z-posO.z)*(posT.z-posO.z));
			for(int i = 0; i<3;i++) {

				Vec3 aim = new Vec3((posT.x - posO.x) / d0, (posT.y - posO.y) / d0, (posT.z - posO.z) / d0);
				throwFlame(posO, aim.add(randomFloat(0.1f),randomFloat(0.1f),randomFloat(0.1f)), 2);
			}

		}
		float randomFloat(float peak){
			Random r = new Random();
			float f = r.nextFloat(-1,1)*peak;
			return f;
		}

		private void throwFlame(Vec3 pos, Vec3 aim, float damage) {

			Flame fireball = new Flame(EntityInit.FLAME.get(),level());
			fireball.setPos(pos.x,pos.y,pos.z);
			fireball.setDeltaMovement(aim.x*0.1f,aim.y*0.1f+0.0015f,aim.z*0.1f);
			fireball.setAttackDamage(damage);
			fireball.setFlyingTime(18);
			fireball.setOwner(DesertSorceress.this);
			DesertSorceress.this.level().addFreshEntity(fireball);

			DesertSorceress.this.playSound(SoundEvents.FIRE_AMBIENT, 0.5f, 0.75f);
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
	class FireballBarrageSpellGoal extends SpellcasterUseSpellGoal {
		protected int getCastingTime() {
			return 20;
		}
		protected int getTotalCastingTime() {
			return getCastWarmupTime()+getCastingTime();
		}

		protected int getCastingInterval() {
			return 120;
		}

		@Override
		protected int getCastWarmupTime() {
			return 25;
		}
		@Override
		public boolean canContinueToUse() {
			LivingEntity livingentity = DesertSorceress.this.getTarget();
			return livingentity != null && livingentity.isAlive() && this.attackWarmupDelay > 0;
		}

		@Override
		public void start() {
			this.attackWarmupDelay = this.adjustedTickDelay(this.getTotalCastingTime());
			DesertSorceress.this.spellCastingTickCount = this.getTotalCastingTime()-2;
			this.nextAttackTickCount = DesertSorceress.this.tickCount + this.getCastingInterval();
			SoundEvent soundevent = this.getSpellPrepareSound();
			if (soundevent != null) {
				DesertSorceress.this.playSound(soundevent, 1.0F, 1.0F);
			}

			DesertSorceress.this.setIsCastingSpell(this.getSpell());
		}


		@Override
		public void tick() {
			this.attackWarmupDelay--;
			if (this.attackWarmupDelay == getCastingTime()) {
				this.performSpellCasting();
				DesertSorceress.this.playSound(DesertSorceress.this.getCastingSoundEvent(), 1.0F, 1.0F);
			}
			if (this.attackWarmupDelay <= getCastingTime()) {
				if (attackWarmupDelay%3==0){
					this.performSpellCasting();
				}
			}
		}

		@Override
		protected void performSpellCasting() {

			Vec3 pos = DesertSorceress.this.position();

			Vec3 posO = new Vec3(pos.x, pos.y + 2.5, pos.z);

			Vec3 pos1 = DesertSorceress.this.getTarget().position();

			Vec3 posT = new Vec3(pos1.x, DesertSorceress.this.getTarget().getY(0.5)+0.5f, pos1.z);

			double d0 = Math.sqrt((posT.x-posO.x)*(posT.x-posO.x) + (posT.y-posO.y)*(posT.y-posO.y) + (posT.z-posO.z)*(posT.z-posO.z));
			//for(int i = 0; i<3;i++) {

				Vec3 aim = new Vec3((posT.x - posO.x) / d0, (posT.y - posO.y) / d0, (posT.z - posO.z) / d0);
				throwFireball(posO, aim.add(randomFloat(0.15f),randomFloat(0.15f),randomFloat(0.15f)), 2);
			//}

		}
		float randomFloat(float peak){
			Random r = new Random();
			float f = r.nextFloat(-1,1)*peak;
			return f;
		}

		private void throwFireball(Vec3 pos, Vec3 aim, float damage) {

			SmallFireball fireball = new SmallFireball(EntityType.SMALL_FIREBALL,level());
			fireball.setPos(pos.x,pos.y,pos.z);
			fireball.setDeltaMovement(aim.x*0.1f,aim.y*0.1f,aim.z*0.1f);
			fireball.setOwner(DesertSorceress.this);
			DesertSorceress.this.level().addFreshEntity(fireball);

			DesertSorceress.this.playSound(SoundEvents.FIRE_AMBIENT, 0.5f, 0.75f);
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


}
