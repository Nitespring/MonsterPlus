package github.nitespring.monsterplus.common.entity;

import github.nitespring.monsterplus.config.CommonConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.SmallFireball;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;

public class Wisp extends Monster{

	   public float xBodyRot;
	   public float xBodyRotO;
	   public float zBodyRot;
	   public float zBodyRotO;
	   public float tentacleMovement;
	   public float oldTentacleMovement;
	   public float tentacleAngle;
	   public float oldTentacleAngle;
	   private float speed;
	   private float tentacleSpeed;
	   private float rotateSpeed;
	   public float tx;
	   public float ty;
	   public float tz;

	//Squid
	   //Blaze

	public Wisp(EntityType<? extends Monster> p_33002_, Level p_33003_) {
		super(p_33002_, p_33003_);
		this.random.setSeed((long)this.getId());
	    this.tentacleSpeed = 1.0F / (this.random.nextFloat() + 1.0F) * 0.2F;
	}
	
	
	public static  AttributeSupplier.Builder setCustomAttributes(){
		return Monster.createMonsterAttributes()
				.add(Attributes.MAX_HEALTH, 12.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.25D)
				.add(Attributes.ATTACK_DAMAGE, 2)
				.add(Attributes.ATTACK_SPEED, 1.2D)
				.add(Attributes.ATTACK_KNOCKBACK, 0.2D)
				.add(Attributes.KNOCKBACK_RESISTANCE, 0.0D)
				.add(Attributes.FOLLOW_RANGE, 30)
				.add(Attributes.FALL_DAMAGE_MULTIPLIER, 0);

	  }
	
	 @Override
	    public SpawnGroupData finalizeSpawn(ServerLevelAccessor server, DifficultyInstance difficulty,
	    		MobSpawnType type, SpawnGroupData group) {
	    	
	    	   Vec3 pos = this.position();
	    	   Vec3 deltaPos = new Vec3(0, 1.5, 0);
	    	   this.setPos(pos.add(deltaPos));
	    	   
	    	return super.finalizeSpawn(server, difficulty, type, group);
	    }
	 
	 
     @Override
  public boolean checkSpawnObstruction(LevelReader p_21433_) {

  	return p_21433_.isUnobstructed(this);
  }
     
	@Override	
	protected void registerGoals() {
		  //this.goalSelector.addGoal(1, new Wisp.LavaSquidAttackGoal(this));
	      this.goalSelector.addGoal(3, new Wisp.SquidRandomMovementGoal(this));
	      this.goalSelector.addGoal(2, new Wisp.SquidFleeGoal());
	      this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setAlertOthers());
	      //this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
	   }


	public static boolean checkWispSpawnRules(EntityType<Wisp> p_218985_, ServerLevelAccessor p_218986_, MobSpawnType p_218987_, BlockPos p_218988_, RandomSource p_218989_) {
	      return checkMobSpawnRules(p_218985_, p_218986_, p_218987_, p_218988_, p_218989_) 
	    		  && CommonConfig.spawn_wisp.get();
	   }
       @Override
	   protected SoundEvent getAmbientSound() {
	      return SoundEvents.SQUID_AMBIENT;
	   }
       @Override
	   protected SoundEvent getHurtSound(DamageSource p_29980_) {
	      return SoundEvents.SQUID_HURT;
	   }
       @Override
	   protected SoundEvent getDeathSound() {
	      return SoundEvents.SQUID_DEATH;
	   }
       @Override
	   protected MovementEmission getMovementEmission() {
		      return MovementEmission.EVENTS;
		   }
       
       @Override
    public boolean fireImmune() {
    	
    	return true;
    }
       
       
       @Override
		   public void aiStep() {
		      super.aiStep();
		      this.xBodyRotO = this.xBodyRot;
		      this.zBodyRotO = this.zBodyRot;
		      this.oldTentacleMovement = this.tentacleMovement;
		      this.oldTentacleAngle = this.tentacleAngle;
		      this.tentacleMovement += this.tentacleSpeed;
		      if ((double)this.tentacleMovement > (Math.PI * 2D)) {
		         if (this.level().isClientSide) {
		            this.tentacleMovement = ((float)Math.PI * 2F);
		         } else {
		            this.tentacleMovement -= ((float)Math.PI * 2F);
		            if (this.random.nextInt(10) == 0) {
		               this.tentacleSpeed = 1.0F / (this.random.nextFloat() + 1.0F) * 0.2F;
		            }

		            this.level().broadcastEntityEvent(this, (byte)19);
		         }
		      }

		      //if (this.isInWaterOrBubble()) {
		         if (this.tentacleMovement < (float)Math.PI) {
		            float f = this.tentacleMovement / (float)Math.PI;
		            this.tentacleAngle = Mth.sin(f * f * (float)Math.PI) * (float)Math.PI * 0.25F;
		            if ((double)f > 0.75D) {
		               this.speed = 1.0F;
		               this.rotateSpeed = 1.0F;
		            } else {
		               this.rotateSpeed *= 0.8F;
		            }
		         } else {
		            this.tentacleAngle = 0.0F;
		            this.speed *= 0.9F;
		            this.rotateSpeed *= 0.99F;
		         }

		         if (!this.level().isClientSide) {
		            this.setDeltaMovement((double)(this.tx * this.speed), (double)(this.ty * this.speed), (double)(this.tz * this.speed));
		         }

		         Vec3 vec3 = this.getDeltaMovement();
		         double d0 = vec3.horizontalDistance();
		         this.yBodyRot += (-((float)Mth.atan2(vec3.x, vec3.z)) * (180F / (float)Math.PI) - this.yBodyRot) * 0.1F;
		         this.setYRot(this.yBodyRot);
		         this.zBodyRot += (float)Math.PI * this.rotateSpeed * 1.5F;
		         this.xBodyRot += (-((float)Mth.atan2(d0, vec3.y)) * (180F / (float)Math.PI) - this.xBodyRot) * 0.1F;
		      /*} else {
		         this.tentacleAngle = Mth.abs(Mth.sin(this.tentacleMovement)) * (float)Math.PI * 0.25F;
		         if (!this.level.isClientSide) {
		            double d1 = this.getDeltaMovement().y;
		            if (this.hasEffect(MobEffects.LEVITATION)) {
		               d1 = 0.05D * (double)(this.getEffect(MobEffects.LEVITATION).getAmplifier() + 1);
		            } else if (!this.isNoGravity()) {
		               d1 -= 0.08D;
		            }

		            this.setDeltaMovement(0.0D, d1 * (double)0.98F, 0.0D);
		         }

		         this.xBodyRot += (-90.0F - this.xBodyRot) * 0.02F;
		      }*/

		   }
       
		   @SuppressWarnings("unused")
		private Vec3 rotateVector(Vec3 p_29986_) {
			      Vec3 vec3 = p_29986_.xRot(this.xBodyRotO * ((float)Math.PI / 180F));
			      return vec3.yRot(-this.yBodyRotO * ((float)Math.PI / 180F));
			   }
	
		  
		   @Override
			   public void travel(Vec3 p_29984_) {
			      this.move(MoverType.SELF, this.getDeltaMovement());
			   }
		   @Override
			   public void handleEntityEvent(byte p_29957_) {
			      if (p_29957_ == 19) {
			         this.tentacleMovement = 0.0F;
			      } else {
			         super.handleEntityEvent(p_29957_);
			      }

			   }
	
			   public void setMovementVector(float p_29959_, float p_29960_, float p_29961_) {
			      this.tx = p_29959_;
			      this.ty = p_29960_;
			      this.tz = p_29961_;
			   }

			   public boolean hasMovementVector() {
			      return this.tx != 0.0F || this.ty != 0.0F || this.tz != 0.0F;
			   }

	@Override
	public boolean isAlliedTo(Entity e) {
		if (e == null) {
			return false;
		} else if (e == this) {
			return true;
		} else if (super.isAlliedTo(e)) {
			return true;
		} else if (e instanceof Wisp || e instanceof MotherLavaSquid) {
			return true;
		}  else {
			return false;
		}
	}

	class SquidRandomMovementGoal extends Goal {
	      private final Wisp squid;

	      public SquidRandomMovementGoal(Wisp p_30004_) {
	         this.squid = p_30004_;
	      }

	      public boolean canUse() {
	         return true;
	      }

	      public void tick() {
	         int i = this.squid.getNoActionTime();
	         if (i > 100) {
	            this.squid.setMovementVector(0.0F, 0.0F, 0.0F);
	         } else if (this.squid.getRandom().nextInt(reducedTickDelay(50)) == 0 || !this.squid.hasMovementVector()) {
	            float f = this.squid.getRandom().nextFloat() * ((float)Math.PI * 2F);
	            float f1 = Mth.cos(f) * 0.2F;
	            float f2 = -0.1F + this.squid.getRandom().nextFloat() * 0.2F;
	            float f3 = Mth.sin(f) * 0.2F;
	            this.squid.setMovementVector(f1, f2, f3);
	         }

	      }
	   }
	 class SquidFleeGoal extends Goal {
	     
		//private static final float SQUID_FLEE_SPEED = 3.0F;
	      //private static final float SQUID_FLEE_MIN_DISTANCE = 5.0F;
	      //private static final float SQUID_FLEE_MAX_DISTANCE = 10.0F;
	      private int fleeTicks;

	      public boolean canUse() {
	         LivingEntity livingentity = Wisp.this.getLastHurtByMob();
	         //double d0 = (new Vec3(LavaSquidEntity.this.getX() - livingentity.getX(), LavaSquidEntity.this.getY() - livingentity.getY(), LavaSquidEntity.this.getZ() - livingentity.getZ())).length();
	         if (livingentity != null /*&& d0 <= 10*/) {
	            return Wisp.this.distanceToSqr(livingentity) < 100.0D;
	         } else {
	            return false;
	         }
	      }

	      public void start() {
	         this.fleeTicks = 0;
	      }

	      public boolean requiresUpdateEveryTick() {
	         return true;
	      }

	      public void tick() {
	         ++this.fleeTicks;
	         LivingEntity livingentity = Wisp.this.getLastHurtByMob();
	         if (livingentity != null) {
	            Vec3 vec3 = new Vec3(Wisp.this.getX() - livingentity.getX(), Wisp.this.getY() - livingentity.getY(), Wisp.this.getZ() - livingentity.getZ());
	            BlockState blockstate = Wisp.this.level().getBlockState(new BlockPos((int)(Wisp.this.getX() + vec3.x), (int)(Wisp.this.getY() + vec3.y), (int)(Wisp.this.getZ() + vec3.z)));
	            //FluidState fluidstate = LavaSquidEntity.this.level.getFluidState(new BlockPos(LavaSquidEntity.this.getX() + vec3.x, LavaSquidEntity.this.getY() + vec3.y, LavaSquidEntity.this.getZ() + vec3.z));
	            if (blockstate.isAir()) {
	               double d0 = vec3.length();
	               if (d0 > 0.0D) {
	                  vec3.normalize();
	                  double d1 = 3.0D;
	                  if (d0 > 5.0D) {
	                     d1 -= (d0 - 5.0D) / 5.0D;
	                  }

	                  if (d1 > 0.0D) {
	                     vec3 = vec3.scale(d1);
	                  }
	               }


	               Wisp.this.setMovementVector((float)vec3.x / 20.0F, (float)vec3.y / 20.0F, (float)vec3.z / 20.0F);
	            }

	            if (this.fleeTicks % 10 == 5) {
	            	Wisp.this.level().addParticle(ParticleTypes.SMALL_FLAME, Wisp.this.getX(), Wisp.this.getY(), Wisp.this.getZ(), 0.0D, 0.0D, 0.0D);
	            }

	         }
	      }
	   }
	 static class LavaSquidAttackGoal extends Goal {
	      private final Wisp squid;
	      private int attackStep;
	      private int attackTime;
	      private int lastSeen;

	      public LavaSquidAttackGoal(Wisp p_32247_) {
	         this.squid = p_32247_;
	         this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
	      }

	      public boolean canUse() {
	         LivingEntity livingentity = this.squid.getTarget();
	         return livingentity != null && livingentity.isAlive() && this.squid.canAttack(livingentity);
	      }

	      public void start() {
	         this.attackStep = 0;
	      }

	      public void stop() {
	         //this.blaze.setCharged(false);
	         this.lastSeen = 0;
	      }

	      public boolean requiresUpdateEveryTick() {
	         return true;
	      }

	      public void tick() {
	         --this.attackTime;
	         LivingEntity livingentity = this.squid.getTarget();
	         if (livingentity != null) {
	            boolean flag = this.squid.getSensing().hasLineOfSight(livingentity);
	            if (flag) {
	               this.lastSeen = 0;
	            } else {
	               ++this.lastSeen;
	            }

	            double d0 = this.squid.distanceToSqr(livingentity);
	            if (d0 < 4.0D) {
	               if (!flag) {
	                  return;
	               }

	               if (this.attackTime <= 0) {
	                  this.attackTime = 20;
	                  this.squid.doHurtTarget(livingentity);
	               }

	               this.squid.getMoveControl().setWantedPosition(livingentity.getX(), livingentity.getY(), livingentity.getZ(), 1.0D);
	            } else if (d0 < this.getFollowDistance() * this.getFollowDistance() && flag) {
	               double d1 = livingentity.getX() - this.squid.getX();
	               double d2 = livingentity.getY(0.5D) - this.squid.getY(0.5D);
	               double d3 = livingentity.getZ() - this.squid.getZ();
	               
	               //
	               
	               if (this.attackTime <= 0) {
	                  ++this.attackStep;
	                  if (this.attackStep == 1) {
	                     this.attackTime = 60;
	                     //this.blaze.setCharged(true);
	                  } else if (this.attackStep <= this.squid.getRandom().nextInt(4)+1) {
	                     this.attackTime = 6;
	                  } else {
	                     this.attackTime = 100;
	                     this.attackStep = 0;
	                     //this.blaze.setCharged(false);
	                  }

	                  
	                //  
	                  
	                  if (this.attackStep > 1) {
	                     double d4 = Math.sqrt(Math.sqrt(d0)) * 0.5D;
	                     if (!this.squid.isSilent()) {
	                        this.squid.level().levelEvent((Player)null, 1018, this.squid.blockPosition(), 0);
	                     }
                         //Vec3 aim = this.squid.getLookAngle();
	                     for(int i = 0; i < 1; ++i) {
	                    	 
	                        SmallFireball smallfireball = new SmallFireball(this.squid.level(), this.squid, new Vec3(this.squid.getRandom().triangle(d1, 2.297D * d4), d2, this.squid.getRandom().triangle(d3, 2.297D * d4)));
	                        double rn = (this.squid.getRandom().nextInt(10)+1)/6;
	                        smallfireball.setPos(this.squid.getX()+Math.cos(Math.PI*2*rn), this.squid.getY(0.5D) + 0.5D + 2.5*Math.sin(Math.PI*2*rn), this.squid.getZ()+Math.sin(Math.PI*2*rn));
	                        smallfireball.setOwner(squid);
							this.squid.level().addFreshEntity(smallfireball);
	                     }
	                  }
	                  
	                  //
	               }
	               
	               

	               this.squid.getLookControl().setLookAt(livingentity, 10.0F, 10.0F);
	            } else if (this.lastSeen < 5) {
	               this.squid.getMoveControl().setWantedPosition(livingentity.getX(), livingentity.getY(), livingentity.getZ(), 1.0D);
	            }

	            super.tick();
	         }
	      }

	      private double getFollowDistance() {
	         return this.squid.getAttributeValue(Attributes.FOLLOW_RANGE);
	      }
	   }
}
