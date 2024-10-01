package github.nitespring.monsterplus.common.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.BodyRotationControl;
import net.minecraft.world.entity.ai.control.LookControl;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.TargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.List;

public class FlyingSkull extends FlyingMob implements Enemy{

	Vec3 moveTargetPoint = Vec3.ZERO;
	   BlockPos anchorPoint = BlockPos.ZERO;
	   FlyingSkull.AttackPhase attackPhase = FlyingSkull.AttackPhase.CIRCLE;
	   @Nullable
	   Mob owner;

	   private boolean hasLimitedLife;
	   private int limitedLifeTicks;

	public FlyingSkull(EntityType<? extends FlyingSkull> p_21368_, Level p_21369_) {
		super(p_21368_, p_21369_);
		this.xpReward = 5;
		this.moveControl = new FlyingSkull.EyeMoveControl(this);
	      this.lookControl = new FlyingSkull.EyeLookControl(this);
	}
	
	//Phantom
	//Bat
	//Ghast
	//Vex
	
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
	
	public static  AttributeSupplier.Builder setCustomAttributes(){
		return Monster.createMonsterAttributes()
				.add(Attributes.MAX_HEALTH, 20.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.25D)
				.add(Attributes.ATTACK_DAMAGE, 5)
				.add(Attributes.ATTACK_SPEED, 1.2D)
				.add(Attributes.ATTACK_KNOCKBACK, 0.2D)
				.add(Attributes.KNOCKBACK_RESISTANCE, 0.0D)
				.add(Attributes.FOLLOW_RANGE, 30);

	  }
	
	 @Override
	public void tick() {
		
		super.tick();
		
		 if (this.hasLimitedLife && --this.limitedLifeTicks <= 0) {
	         this.remove(RemovalReason.DISCARDED);
	         
	      }
		 if(tickCount%6==0) {
			 playSound(SoundEvents.CAMPFIRE_CRACKLE, 0.2f, 0.4f);
		 }
		 this.level().addParticle(ParticleTypes.SOUL_FIRE_FLAME, this.getRandomX(0.6D), this.getRandomY(), this.getRandomZ(0.6D), 0.0, 0.0, 0.0);
	}
	
	
	@Override
	protected void registerGoals() {
		
	    this.goalSelector.addGoal(1, new FlyingSkull.EyeAttackStrategyGoal());
	      this.goalSelector.addGoal(2, new FlyingSkull.EyeSweepAttackGoal());
	      this.goalSelector.addGoal(3, new FlyingSkull.EyeCircleAroundAnchorGoal());
	      this.targetSelector.addGoal(1, new FlyingSkull.EyeAttackPlayerTargetGoal());
	      //this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
	      //this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
	      this.targetSelector.addGoal(2, new FlyingSkull.CopyOwnerTargetGoal(this));
	      this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
	      this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));
	   }
	
	@Override
	protected BodyRotationControl createBodyControl() {
	      return new FlyingSkull.EyeBodyRotationControl(this);
	   }
	
	@Override
	protected boolean shouldDespawnInPeaceful() {
	      return true;
	   }

	  

	@Override
	   protected void customServerAiStep() {
	      super.customServerAiStep();
	   }
	@Override
	   public SpawnGroupData finalizeSpawn(ServerLevelAccessor p_33126_, DifficultyInstance p_33127_, MobSpawnType p_33128_, @Nullable SpawnGroupData p_33129_) {
	      this.anchorPoint = this.blockPosition().above(5);
	     
	      return super.finalizeSpawn(p_33126_, p_33127_, p_33128_, p_33129_);
	   }
	@Override
	   public void readAdditionalSaveData(CompoundTag p_33132_) {
	      super.readAdditionalSaveData(p_33132_);
	      if (p_33132_.contains("AX")) {
	         this.anchorPoint = new BlockPos(p_33132_.getInt("AX"), p_33132_.getInt("AY"), p_33132_.getInt("AZ"));
	      }

	      
	   }
	@Override
	   public void addAdditionalSaveData(CompoundTag p_33141_) {
	      super.addAdditionalSaveData(p_33141_);
	      p_33141_.putInt("AX", this.anchorPoint.getX());
	      p_33141_.putInt("AY", this.anchorPoint.getY());
	      p_33141_.putInt("AZ", this.anchorPoint.getZ());
	     
	   }
	@Override   
	   public SoundSource getSoundSource() {
		      return SoundSource.HOSTILE;
		   }


	   static enum AttackPhase {
		      CIRCLE,
		      SWOOP;
		  }
	   @Override
		   public boolean canAttackType(EntityType<?> p_33111_) {
		      return true;
		   }
	  
		   class EyeBodyRotationControl extends BodyRotationControl {
			      public EyeBodyRotationControl(Mob p_33216_) {
			         super(p_33216_);
			      }

			      public void clientTick() {
			         FlyingSkull.this.yHeadRot = FlyingSkull.this.yBodyRot;
			         FlyingSkull.this.yBodyRot = FlyingSkull.this.getYRot();
			      }
			   }
	   
	   
	class EyeLookControl extends LookControl {
	      public EyeLookControl(Mob p_33235_) {
	         super(p_33235_);
	      }

	      public void tick() {
	      }
	   }

	   class EyeMoveControl extends MoveControl {
	      private float speed = 0.1F;

	      public EyeMoveControl(Mob p_33241_) {
	         super(p_33241_);
	      }

	      public void tick() {
	         if (FlyingSkull.this.horizontalCollision) {
	        	 FlyingSkull.this.setYRot(FlyingSkull.this.getYRot() + 180.0F);
	            this.speed = 0.1F;
	         }

	         double d0 = FlyingSkull.this.moveTargetPoint.x - FlyingSkull.this.getX();
	         double d1 = FlyingSkull.this.moveTargetPoint.y - FlyingSkull.this.getY();
	         double d2 = FlyingSkull.this.moveTargetPoint.z - FlyingSkull.this.getZ();
	         double d3 = Math.sqrt(d0 * d0 + d2 * d2);
	         if (Math.abs(d3) > (double)1.0E-5F) {
	            double d4 = 1.0D - Math.abs(d1 * (double)0.7F) / d3;
	            d0 *= d4;
	            d2 *= d4;
	            d3 = Math.sqrt(d0 * d0 + d2 * d2);
	            double d5 = Math.sqrt(d0 * d0 + d2 * d2 + d1 * d1);
	            float f = FlyingSkull.this.getYRot();
	            float f1 = (float)Mth.atan2(d2, d0);
	            float f2 = Mth.wrapDegrees(FlyingSkull.this.getYRot() + 90.0F);
	            float f3 = Mth.wrapDegrees(f1 * (180F / (float)Math.PI));
	            FlyingSkull.this.setYRot(Mth.approachDegrees(f2, f3, 4.0F) - 90.0F);
	            FlyingSkull.this.yBodyRot = FlyingSkull.this.getYRot();
	            if (Mth.degreesDifferenceAbs(f, FlyingSkull.this.getYRot()) < 3.0F) {
	               this.speed = Mth.approach(this.speed, 1.8F, 0.005F * (1.8F / this.speed));
	            } else {
	               this.speed = Mth.approach(this.speed, 0.2F, 0.025F);
	            }

	            float f4 = (float)(-(Mth.atan2(-d1, d3) * (double)(180F / (float)Math.PI)));
	            FlyingSkull.this.setXRot(f4);
	            float f5 = FlyingSkull.this.getYRot() + 90.0F;
	            double d6 = (double)(this.speed * Mth.cos(f5 * ((float)Math.PI / 180F))) * Math.abs(d0 / d5);
	            double d7 = (double)(this.speed * Mth.sin(f5 * ((float)Math.PI / 180F))) * Math.abs(d2 / d5);
	            double d8 = (double)(this.speed * Mth.sin(f4 * ((float)Math.PI / 180F))) * Math.abs(d1 / d5);
	            Vec3 vec3 = FlyingSkull.this.getDeltaMovement();
	            FlyingSkull.this.setDeltaMovement(vec3.add((new Vec3(d6, d8, d7)).subtract(vec3).scale(0.2D)));
	         }

	      }
	   }
	   
	   class EyeAttackPlayerTargetGoal extends Goal {
		      private final TargetingConditions attackTargeting = TargetingConditions.forCombat().range(64.0D);
		      private int nextScanTick = reducedTickDelay(20);

		      public boolean canUse() {
		         if (this.nextScanTick > 0) {
		            --this.nextScanTick;
		            return false;
		         } else {
		            this.nextScanTick = reducedTickDelay(60);
		            List<Player> list = FlyingSkull.this.level().getNearbyPlayers(this.attackTargeting, FlyingSkull.this, FlyingSkull.this.getBoundingBox().inflate(16.0D, 64.0D, 16.0D));
		            if (!list.isEmpty()) {
		               list.sort(Comparator.<Entity, Double>comparing(Entity::getY).reversed());

		               for(Player player : list) {
		                  if (FlyingSkull.this.canAttack(player, TargetingConditions.DEFAULT)) {
		                	  FlyingSkull.this.setTarget(player);
		                     return true;
		                  }
		               }
		            }

		            return false;
		         }
		      }

		      public boolean canContinueToUse() {
		         LivingEntity livingentity = FlyingSkull.this.getTarget();
		         return livingentity != null ? FlyingSkull.this.canAttack(livingentity, TargetingConditions.DEFAULT) : false;
		      }
		   }

		   class EyeAttackStrategyGoal extends Goal {
		      private int nextSweepTick;

		      public boolean canUse() {
		         LivingEntity livingentity = FlyingSkull.this.getTarget();
		         return livingentity != null ? FlyingSkull.this.canAttack(livingentity, TargetingConditions.DEFAULT) : false;
		      }

		      public void start() {
		         this.nextSweepTick = this.adjustedTickDelay(10);
		         FlyingSkull.this.attackPhase = FlyingSkull.AttackPhase.CIRCLE;
		         this.setAnchorAboveTarget();
		      }

		      public void stop() {
		    	  FlyingSkull.this.anchorPoint = FlyingSkull.this.level().getHeightmapPos(Heightmap.Types.MOTION_BLOCKING, FlyingSkull.this.anchorPoint).above(5 + FlyingSkull.this.random.nextInt(10));
		      }

		      public void tick() {
		         if (FlyingSkull.this.attackPhase == FlyingSkull.AttackPhase.CIRCLE) {
		            --this.nextSweepTick;
		            if (this.nextSweepTick <= 0) {
		            	FlyingSkull.this.attackPhase = FlyingSkull.AttackPhase.SWOOP;
		               this.setAnchorAboveTarget();
		               this.nextSweepTick = this.adjustedTickDelay((8 + FlyingSkull.this.random.nextInt(4)) * 20);
		              
		            }
		         }

		      }
		      
		      private void setAnchorAboveTarget() {
		          FlyingSkull.this.anchorPoint = FlyingSkull.this.getTarget().blockPosition().above(5 + FlyingSkull.this.random.nextInt(20));
		          /*if (FlyingSkull.this.anchorPoint.getY() < FlyingSkull.this.level().getSeaLevel()) {
		        	  FlyingSkull.this.anchorPoint = new BlockPos(FlyingSkull.this.anchorPoint.getX(), FlyingSkull.this.level().getSeaLevel() + 1, FlyingSkull.this.anchorPoint.getZ());
		          }*/

		       }

		       }

	@Override
	public boolean isAlliedTo(Entity pEntity) {
		if(this.getOwner()!=null){
			if(pEntity == this.owner){
				return true;
			}else{
				return this.getOwner().isAlliedTo(pEntity);
			}
		} else {
            return super.isAlliedTo(pEntity);
		}
	}

	class EyeCircleAroundAnchorGoal extends FlyingSkull.EyeMoveTargetGoal {
		          private float angle;
		          private float distance;
		          private float height;
		          private float clockwise;

		          public boolean canUse() {
		             return FlyingSkull.this.getTarget() == null || FlyingSkull.this.attackPhase == FlyingSkull.AttackPhase.CIRCLE;
		          }

		          public void start() {
		             this.distance = 5.0F + FlyingSkull.this.random.nextFloat() * 10.0F;
		             this.height = -4.0F + FlyingSkull.this.random.nextFloat() * 9.0F;
		             this.clockwise = FlyingSkull.this.random.nextBoolean() ? 1.0F : -1.0F;
		             this.selectNext();
		          }

		          public void tick() {
		             if (FlyingSkull.this.random.nextInt(this.adjustedTickDelay(350)) == 0) {
		                this.height = -4.0F + FlyingSkull.this.random.nextFloat() * 9.0F;
		             }

		             if (FlyingSkull.this.random.nextInt(this.adjustedTickDelay(250)) == 0) {
		                ++this.distance;
		                if (this.distance > 15.0F) {
		                   this.distance = 5.0F;
		                   this.clockwise = -this.clockwise;
		                }
		             }

		             if (FlyingSkull.this.random.nextInt(this.adjustedTickDelay(450)) == 0) {
		                this.angle = FlyingSkull.this.random.nextFloat() * 2.0F * (float)Math.PI;
		                this.selectNext();
		             }

		             if (this.touchingTarget()) {
		                this.selectNext();
		             }

		             if (FlyingSkull.this.moveTargetPoint.y < FlyingSkull.this.getY() && !FlyingSkull.this.level().isEmptyBlock(FlyingSkull.this.blockPosition().below(1))) {
		                this.height = Math.max(1.0F, this.height);
		                this.selectNext();
		             }

		             if (FlyingSkull.this.moveTargetPoint.y > FlyingSkull.this.getY() && !FlyingSkull.this.level().isEmptyBlock(FlyingSkull.this.blockPosition().above(1))) {
		                this.height = Math.min(-1.0F, this.height);
		                this.selectNext();
		             }

		          }

		          private void selectNext() {
		             if (BlockPos.ZERO.equals(FlyingSkull.this.anchorPoint)) {
		            	 FlyingSkull.this.anchorPoint = FlyingSkull.this.blockPosition();
		             }

		             this.angle += this.clockwise * 15.0F * ((float)Math.PI / 180F);
		             FlyingSkull.this.moveTargetPoint = Vec3.atLowerCornerOf(FlyingSkull.this.anchorPoint).add((double)(this.distance * Mth.cos(this.angle)), (double)(-4.0F + this.height), (double)(this.distance * Mth.sin(this.angle)));
		          }
		       }
		      
		      
		      
		      abstract class EyeMoveTargetGoal extends Goal {
		          public EyeMoveTargetGoal() {
		             this.setFlags(EnumSet.of(Flag.MOVE));
		          }

		          protected boolean touchingTarget() {
		             return FlyingSkull.this.moveTargetPoint.distanceToSqr(FlyingSkull.this.getX(), FlyingSkull.this.getY(), FlyingSkull.this.getZ()) < 4.0D;
		          }
		       }

		       class EyeSweepAttackGoal extends FlyingSkull.EyeMoveTargetGoal {
		          //private static final int CAT_SEARCH_TICK_DELAY = 20;
		          private boolean isScaredOfCat;
		          private int catSearchTick;

		          public boolean canUse() {
		             return FlyingSkull.this.getTarget() != null && FlyingSkull.this.attackPhase == FlyingSkull.AttackPhase.SWOOP;
		          }

		          public boolean canContinueToUse() {
		             LivingEntity livingentity = FlyingSkull.this.getTarget();
		             if (livingentity == null) {
		                return false;
		             } else if (!livingentity.isAlive()) {
		                return false;
		             } else {
		                if (livingentity instanceof Player) {
		                   Player player = (Player)livingentity;
		                   if (livingentity.isSpectator() || player.isCreative()) {
		                      return false;
		                   }
		                }

		                if (!this.canUse()) {
		                   return false;
		                } else {
		                   if (FlyingSkull.this.tickCount > this.catSearchTick) {
		                      this.catSearchTick = FlyingSkull.this.tickCount + 20;
		                      List<Cat> list = FlyingSkull.this.level().getEntitiesOfClass(Cat.class, FlyingSkull.this.getBoundingBox().inflate(16.0D), EntitySelector.ENTITY_STILL_ALIVE);

		                      for(Cat cat : list) {
		                         cat.hiss();
		                      }

		                      this.isScaredOfCat = !list.isEmpty();
		                   }

		                   return !this.isScaredOfCat;
		                }
		             }
		          }

		          public void start() {
		          }

		          public void stop() {
		        	  FlyingSkull.this.setTarget((LivingEntity)null);
		        	  FlyingSkull.this.attackPhase = FlyingSkull.AttackPhase.CIRCLE;
		          }

		          public void tick() {
		             LivingEntity livingentity = FlyingSkull.this.getTarget();
		             if (livingentity != null) {
		            	 FlyingSkull.this.moveTargetPoint = new Vec3(livingentity.getX(), livingentity.getY(0.5D), livingentity.getZ());
		                if (FlyingSkull.this.getBoundingBox().inflate((double)0.2F).intersects(livingentity.getBoundingBox())) {
		                	FlyingSkull.this.doHurtTarget(livingentity);
		                	FlyingSkull.this.attackPhase = FlyingSkull.AttackPhase.CIRCLE;
		                   if (!FlyingSkull.this.isSilent()) {
		                	   FlyingSkull.this.level().levelEvent(1039, FlyingSkull.this.blockPosition(), 0);
		                   }
		                } else if (FlyingSkull.this.horizontalCollision || FlyingSkull.this.hurtTime > 0) {
		                	FlyingSkull.this.attackPhase = FlyingSkull.AttackPhase.CIRCLE;
		                }

		             }
		          }
         }
		       
		       
		       class CopyOwnerTargetGoal extends TargetGoal {
		    	      private final TargetingConditions copyOwnerTargeting = TargetingConditions.forNonCombat().ignoreLineOfSight().ignoreInvisibilityTesting();

		    	      public CopyOwnerTargetGoal(Mob p_34056_) {
		    	         super(p_34056_, false);
		    	      }

		    	      public boolean canUse() {
		    	         return FlyingSkull.this.owner != null && FlyingSkull.this.owner.getTarget() != null && this.canAttack(FlyingSkull.this.owner.getTarget(), this.copyOwnerTargeting);
		    	      }

		    	      public void start() {
		    	    	  FlyingSkull.this.setTarget(FlyingSkull.this.owner.getTarget());
		    	         super.start();
		    	      }
		    	   }
}
