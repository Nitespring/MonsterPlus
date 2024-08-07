package github.nitespring.monsterplus.common.entity;

import java.util.Comparator;
import java.util.EnumSet;
import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.FlyingMob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.SpawnGroupData;
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
import net.minecraft.world.entity.monster.Vex;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.phys.Vec3;

public class Eye extends FlyingMob implements Enemy{
	
	Vec3 moveTargetPoint = Vec3.ZERO;
	   BlockPos anchorPoint = BlockPos.ZERO;
	   Eye.AttackPhase attackPhase = Eye.AttackPhase.CIRCLE;
	   @Nullable
	   Mob owner;
	   
	   private boolean hasLimitedLife;
	   private int limitedLifeTicks;

	public Eye(EntityType<? extends Eye> p_21368_, Level p_21369_) {
		super(p_21368_, p_21369_);
		this.xpReward = 5;
		this.moveControl = new Eye.EyeMoveControl(this);
	      this.lookControl = new Eye.EyeLookControl(this);
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
				.add(Attributes.MAX_HEALTH, 8.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.25D)
				.add(Attributes.ATTACK_DAMAGE, 3)
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
	}
	
	
	@Override
	protected void registerGoals() {
		
	    this.goalSelector.addGoal(1, new Eye.EyeAttackStrategyGoal());
	      this.goalSelector.addGoal(2, new Eye.EyeSweepAttackGoal());
	      this.goalSelector.addGoal(3, new Eye.EyeCircleAroundAnchorGoal());
	      this.targetSelector.addGoal(1, new Eye.EyeAttackPlayerTargetGoal());
	      //this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
	      //this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
	      this.targetSelector.addGoal(2, new Eye.CopyOwnerTargetGoal(this));
	      this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
	      this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));
	   }
	
	@Override
	protected BodyRotationControl createBodyControl() {
	      return new Eye.EyeBodyRotationControl(this);
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
			         Eye.this.yHeadRot = Eye.this.yBodyRot;
			         Eye.this.yBodyRot = Eye.this.getYRot();
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
	         if (Eye.this.horizontalCollision) {
	        	 Eye.this.setYRot(Eye.this.getYRot() + 180.0F);
	            this.speed = 0.1F;
	         }

	         double d0 = Eye.this.moveTargetPoint.x - Eye.this.getX();
	         double d1 = Eye.this.moveTargetPoint.y - Eye.this.getY();
	         double d2 = Eye.this.moveTargetPoint.z - Eye.this.getZ();
	         double d3 = Math.sqrt(d0 * d0 + d2 * d2);
	         if (Math.abs(d3) > (double)1.0E-5F) {
	            double d4 = 1.0D - Math.abs(d1 * (double)0.7F) / d3;
	            d0 *= d4;
	            d2 *= d4;
	            d3 = Math.sqrt(d0 * d0 + d2 * d2);
	            double d5 = Math.sqrt(d0 * d0 + d2 * d2 + d1 * d1);
	            float f = Eye.this.getYRot();
	            float f1 = (float)Mth.atan2(d2, d0);
	            float f2 = Mth.wrapDegrees(Eye.this.getYRot() + 90.0F);
	            float f3 = Mth.wrapDegrees(f1 * (180F / (float)Math.PI));
	            Eye.this.setYRot(Mth.approachDegrees(f2, f3, 4.0F) - 90.0F);
	            Eye.this.yBodyRot = Eye.this.getYRot();
	            if (Mth.degreesDifferenceAbs(f, Eye.this.getYRot()) < 3.0F) {
	               this.speed = Mth.approach(this.speed, 1.8F, 0.005F * (1.8F / this.speed));
	            } else {
	               this.speed = Mth.approach(this.speed, 0.2F, 0.025F);
	            }

	            float f4 = (float)(-(Mth.atan2(-d1, d3) * (double)(180F / (float)Math.PI)));
	            Eye.this.setXRot(f4);
	            float f5 = Eye.this.getYRot() + 90.0F;
	            double d6 = (double)(this.speed * Mth.cos(f5 * ((float)Math.PI / 180F))) * Math.abs(d0 / d5);
	            double d7 = (double)(this.speed * Mth.sin(f5 * ((float)Math.PI / 180F))) * Math.abs(d2 / d5);
	            double d8 = (double)(this.speed * Mth.sin(f4 * ((float)Math.PI / 180F))) * Math.abs(d1 / d5);
	            Vec3 vec3 = Eye.this.getDeltaMovement();
	            Eye.this.setDeltaMovement(vec3.add((new Vec3(d6, d8, d7)).subtract(vec3).scale(0.2D)));
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
		            List<Player> list = Eye.this.level().getNearbyPlayers(this.attackTargeting, Eye.this, Eye.this.getBoundingBox().inflate(16.0D, 64.0D, 16.0D));
		            if (!list.isEmpty()) {
		               list.sort(Comparator.<Entity, Double>comparing(Entity::getY).reversed());

		               for(Player player : list) {
		                  if (Eye.this.canAttack(player, TargetingConditions.DEFAULT)) {
		                	  Eye.this.setTarget(player);
		                     return true;
		                  }
		               }
		            }

		            return false;
		         }
		      }

		      public boolean canContinueToUse() {
		         LivingEntity livingentity = Eye.this.getTarget();
		         return livingentity != null ? Eye.this.canAttack(livingentity, TargetingConditions.DEFAULT) : false;
		      }
		   }

		   class EyeAttackStrategyGoal extends Goal {
		      private int nextSweepTick;

		      public boolean canUse() {
		         LivingEntity livingentity = Eye.this.getTarget();
		         return livingentity != null ? Eye.this.canAttack(livingentity, TargetingConditions.DEFAULT) : false;
		      }

		      public void start() {
		         this.nextSweepTick = this.adjustedTickDelay(10);
		         Eye.this.attackPhase = Eye.AttackPhase.CIRCLE;
		         this.setAnchorAboveTarget();
		      }

		      public void stop() {
		    	  Eye.this.anchorPoint = Eye.this.level().getHeightmapPos(Heightmap.Types.MOTION_BLOCKING, Eye.this.anchorPoint).above(10 + Eye.this.random.nextInt(20));
		      }

		      public void tick() {
		         if (Eye.this.attackPhase == Eye.AttackPhase.CIRCLE) {
		            --this.nextSweepTick;
		            if (this.nextSweepTick <= 0) {
		            	Eye.this.attackPhase = Eye.AttackPhase.SWOOP;
		               this.setAnchorAboveTarget();
		               this.nextSweepTick = this.adjustedTickDelay((8 + Eye.this.random.nextInt(4)) * 20);
		              
		            }
		         }

		      }
		      
		      private void setAnchorAboveTarget() {
		          Eye.this.anchorPoint = Eye.this.getTarget().blockPosition().above(20 + Eye.this.random.nextInt(20));
		          /*if (Eye.this.anchorPoint.getY() < Eye.this.level().getSeaLevel()) {
		        	  Eye.this.anchorPoint = new BlockPos(Eye.this.anchorPoint.getX(), Eye.this.level().getSeaLevel() + 1, Eye.this.anchorPoint.getZ());
		          }*/

		       }
		      
		      
		      
		     
		       }
		   
		   class EyeCircleAroundAnchorGoal extends Eye.EyeMoveTargetGoal {
		          private float angle;
		          private float distance;
		          private float height;
		          private float clockwise;

		          public boolean canUse() {
		             return Eye.this.getTarget() == null || Eye.this.attackPhase == Eye.AttackPhase.CIRCLE;
		          }

		          public void start() {
		             this.distance = 5.0F + Eye.this.random.nextFloat() * 10.0F;
		             this.height = -4.0F + Eye.this.random.nextFloat() * 9.0F;
		             this.clockwise = Eye.this.random.nextBoolean() ? 1.0F : -1.0F;
		             this.selectNext();
		          }

		          public void tick() {
		             if (Eye.this.random.nextInt(this.adjustedTickDelay(350)) == 0) {
		                this.height = -4.0F + Eye.this.random.nextFloat() * 9.0F;
		             }

		             if (Eye.this.random.nextInt(this.adjustedTickDelay(250)) == 0) {
		                ++this.distance;
		                if (this.distance > 15.0F) {
		                   this.distance = 5.0F;
		                   this.clockwise = -this.clockwise;
		                }
		             }

		             if (Eye.this.random.nextInt(this.adjustedTickDelay(450)) == 0) {
		                this.angle = Eye.this.random.nextFloat() * 2.0F * (float)Math.PI;
		                this.selectNext();
		             }

		             if (this.touchingTarget()) {
		                this.selectNext();
		             }

		             if (Eye.this.moveTargetPoint.y < Eye.this.getY() && !Eye.this.level().isEmptyBlock(Eye.this.blockPosition().below(1))) {
		                this.height = Math.max(1.0F, this.height);
		                this.selectNext();
		             }

		             if (Eye.this.moveTargetPoint.y > Eye.this.getY() && !Eye.this.level().isEmptyBlock(Eye.this.blockPosition().above(1))) {
		                this.height = Math.min(-1.0F, this.height);
		                this.selectNext();
		             }

		          }

		          private void selectNext() {
		             if (BlockPos.ZERO.equals(Eye.this.anchorPoint)) {
		            	 Eye.this.anchorPoint = Eye.this.blockPosition();
		             }

		             this.angle += this.clockwise * 15.0F * ((float)Math.PI / 180F);
		             Eye.this.moveTargetPoint = Vec3.atLowerCornerOf(Eye.this.anchorPoint).add((double)(this.distance * Mth.cos(this.angle)), (double)(-4.0F + this.height), (double)(this.distance * Mth.sin(this.angle)));
		          }
		       }
		      
		      
		      
		      abstract class EyeMoveTargetGoal extends Goal {
		          public EyeMoveTargetGoal() {
		             this.setFlags(EnumSet.of(Goal.Flag.MOVE));
		          }

		          protected boolean touchingTarget() {
		             return Eye.this.moveTargetPoint.distanceToSqr(Eye.this.getX(), Eye.this.getY(), Eye.this.getZ()) < 4.0D;
		          }
		       }

		       class EyeSweepAttackGoal extends Eye.EyeMoveTargetGoal {
		          //private static final int CAT_SEARCH_TICK_DELAY = 20;
		          private boolean isScaredOfCat;
		          private int catSearchTick;

		          public boolean canUse() {
		             return Eye.this.getTarget() != null && Eye.this.attackPhase == Eye.AttackPhase.SWOOP;
		          }

		          public boolean canContinueToUse() {
		             LivingEntity livingentity = Eye.this.getTarget();
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
		                   if (Eye.this.tickCount > this.catSearchTick) {
		                      this.catSearchTick = Eye.this.tickCount + 20;
		                      List<Cat> list = Eye.this.level().getEntitiesOfClass(Cat.class, Eye.this.getBoundingBox().inflate(16.0D), EntitySelector.ENTITY_STILL_ALIVE);

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
		        	  Eye.this.setTarget((LivingEntity)null);
		        	  Eye.this.attackPhase = Eye.AttackPhase.CIRCLE;
		          }

		          public void tick() {
		             LivingEntity livingentity = Eye.this.getTarget();
		             if (livingentity != null) {
		            	 Eye.this.moveTargetPoint = new Vec3(livingentity.getX(), livingentity.getY(0.5D), livingentity.getZ());
		                if (Eye.this.getBoundingBox().inflate((double)0.2F).intersects(livingentity.getBoundingBox())) {
		                	Eye.this.doHurtTarget(livingentity);
		                	Eye.this.attackPhase = Eye.AttackPhase.CIRCLE;
		                   if (!Eye.this.isSilent()) {
		                	   Eye.this.level().levelEvent(1039, Eye.this.blockPosition(), 0);
		                   }
		                } else if (Eye.this.horizontalCollision || Eye.this.hurtTime > 0) {
		                	Eye.this.attackPhase = Eye.AttackPhase.CIRCLE;
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
		    	         return Eye.this.owner != null && Eye.this.owner.getTarget() != null && this.canAttack(Eye.this.owner.getTarget(), this.copyOwnerTargeting);
		    	      }

		    	      public void start() {
		    	    	  Eye.this.setTarget(Eye.this.owner.getTarget());
		    	         super.start();
		    	      }
		    	   }
}
