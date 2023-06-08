package com.nitespring.monsterplus.common.entity;

import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import com.nitespring.monsterplus.config.CommonConfig;
import com.nitespring.monsterplus.core.init.EntityInit;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.AbstractGolem;
import net.minecraft.world.entity.monster.Ghast;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.LargeFireball;
import net.minecraft.world.entity.projectile.SmallFireball;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.AABB;

public class MotherLavaSquid extends LavaSquid{

	int birthTick = 0;
	
	public MotherLavaSquid(EntityType<? extends Monster> p_33002_, Level p_33003_) {
		super(p_33002_, p_33003_);
		
	}
	//Ghast
	
	public static  AttributeSupplier.Builder setCustomAttributes(){
		return Monster.createMonsterAttributes()
				.add(Attributes.MAX_HEALTH, 60.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.25D)
				.add(Attributes.ATTACK_DAMAGE, 5)
				.add(Attributes.ATTACK_SPEED, 1.2D)
				.add(Attributes.ATTACK_KNOCKBACK, 2.0D)
				.add(Attributes.KNOCKBACK_RESISTANCE, 0.5D)
				.add(Attributes.FOLLOW_RANGE, 100);

	  }
	
	public static boolean checkMotherLavaSquidSpawnRules(EntityType<MotherLavaSquid> p_218985_, LevelAccessor p_218986_, MobSpawnType p_218987_, BlockPos p_218988_, RandomSource p_218989_) {
	      return p_218986_.getDifficulty() != Difficulty.PEACEFUL && checkMobSpawnRules(p_218985_, p_218986_, p_218987_, p_218988_, p_218989_) 
	    		  && CommonConfig.spawn_mother_lava_squid.get();
	   }
	
	
	
	@Override	
	protected void registerGoals() {
		  this.goalSelector.addGoal(1, new MotherLavaSquid.MotherLavaSquidAttackGoal(this));
	      this.goalSelector.addGoal(3, new LavaSquid.SquidRandomMovementGoal(this));
	      this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setAlertOthers().setAlertOthers(LavaSquid.class));
	      this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
	      this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractGolem.class, true));
	   }
	
	@Override
	public void aiStep() {
		super.aiStep();
		
		birthTick++;
		
		
		if (birthTick >= 400){
			birthTick = 0;
			 LavaSquid son = new LavaSquid(EntityInit.LAVA_SQUID.get(), this.level());
			son.setPos(this.position()); 
			 this.level().addFreshEntity(son);
			
		}

	};
	
	
	
	
	
	
	static class MotherLavaSquidAttackGoal extends Goal {
	      private final LavaSquid mob;
	      private int attackStep;
	      private int attackTime;
	      private int lastSeen;
	      private Class<?>[] toIgnoreAlert;
	      private int alertOthersTick;

	      public MotherLavaSquidAttackGoal(LavaSquid p_32247_) {
	         this.mob = p_32247_;
	         this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
	      }

	      public boolean canUse() {
	         LivingEntity livingentity = this.mob.getTarget();
	         return livingentity != null && livingentity.isAlive() && this.mob.canAttack(livingentity);
	      }

	      public void start() {
	         this.attackStep = 0;
	         this.alertOthers();
	      }

	      public void stop() {
	         
	         this.lastSeen = 0;
	      }

	      public boolean requiresUpdateEveryTick() {
	         return true;
	      }

	      public void tick() {
	    	  
	         --this.attackTime;
	         LivingEntity livingentity = this.mob.getTarget();
	         if (livingentity != null) {
	            boolean flag = this.mob.getSensing().hasLineOfSight(livingentity);
	            if (flag) {
	               this.lastSeen = 0;
	            } else {
	               ++this.lastSeen;
	            }

	            double d0 = this.mob.distanceToSqr(livingentity);
	            if (d0 < 4.0D) {
	               if (!flag) {
	                  return;
	               }

	               if (this.attackTime <= 0) {
	                  this.attackTime = 20;
	                  this.mob.doHurtTarget(livingentity);
	               }

	               this.mob.getMoveControl().setWantedPosition(livingentity.getX(), livingentity.getY(), livingentity.getZ(), 1.0D);
	            } else if (d0 < this.getFollowDistance() * this.getFollowDistance() && flag) {
	               double d1 = livingentity.getX() - this.mob.getX();
	               double d2 = livingentity.getY(0.5D) - this.mob.getY(0.5D);
	               double d3 = livingentity.getZ() - this.mob.getZ();
	               
	               //
	               
	               if (this.attackTime <= 0) {
	            	   
	            	   Random rand = new Random();
	            	   int rng = rand.nextInt(10) + 1;
	            	   
	            	   if(rng >=8 && this.attackStep==1) {
	            		   //Vec3 aim = this.mob.getLookAngle();
	            		   
	            		   LargeFireball smallfireball = new LargeFireball(this.mob.level(), this.mob, d1/d0, d2/d0, d3/d0, 2);
	                        
	                        smallfireball.setPos(this.mob.getX()+ 3*d1/d0, this.mob.getY(0.5) + 3*d2/d0, this.mob.getZ() + 3*d3/d0);
	                        
	                        this.mob.level().addFreshEntity(smallfireball);
	            		   
	                        this.attackTime = 60;
	            		   
	            	   }else {
	            	   
	                  ++this.attackStep;
	                  if (this.attackStep == 1) {
	                     this.attackTime = 60;
	                    
	                  } else if (this.attackStep <= 13) {
	                     this.attackTime = 6;
	                  } else {
	                     this.attackTime = 100;
	                     this.attackStep = 0;
	                     
	                  }
	            	   }
	                  
	                //  
	                  
	                  if (this.attackStep > 1) {
	                     //double d4 = Math.sqrt(Math.sqrt(d0)) * 0.5D;
	                     if (!this.mob.isSilent()) {
	                        this.mob.level().levelEvent((Player)null, 1018, this.mob.blockPosition(), 0);
	                     }
	                     //Vec3 pos = this.mob.position();
                       //Vec3 aim = this.squid.getLookAngle();
	                     for(int i = 0; i < 1; ++i) {
	                    	 double x = this.mob.getX() + 50.0*Math.sqrt(d2*d2 +  d3*d3)/d0*(Math.cos((this.attackStep-1)*Math.PI/6));
	                    	 double y = this.mob.getY(0.5) + 50.0*Math.sqrt(d1*d1 +  d3*d3)/d0*(Math.sin((this.attackStep-1)*Math.PI/6));
	                    	 double z = this.mob.getZ() + 50.0*Math.sqrt(d2*d2 +  d1*d1)/d0*(Math.cos((this.attackStep-1)*Math.PI/6));
	                    	 
	                    	 double d = Math.sqrt((livingentity.getX()-x)*(livingentity.getX()-x)+(livingentity.getY(0.5)-y)*(livingentity.getY(0.5)-y)+(livingentity.getZ()-z)*(livingentity.getZ()-z));
	                    	 
	                        SmallFireball smallfireball = new SmallFireball(this.mob.level(), this.mob, (livingentity.getX()-x)/d, (livingentity.getY(0.5)-y)/d, (livingentity.getZ()-z)/d);
	                        
	                        smallfireball.setPos(x, y, z);
	                        
	                        this.mob.level().addFreshEntity(smallfireball);
	                     }
	                  }
	                  
	                  
	                  //
	                  
	                  
	                  
	                  
	                  
	               }
	               
	               

	               this.mob.getLookControl().setLookAt(livingentity, 10.0F, 10.0F);
	            } else if (this.lastSeen < 5) {
	               this.mob.getMoveControl().setWantedPosition(livingentity.getX(), livingentity.getY(), livingentity.getZ(), 1.0D);
	            }

	            super.tick();
	         }
	         
	         alertOthersTick++;
	         
	         if (alertOthersTick>=600) {
	        	 this.alertOthers();
	         }
	      }
	      
	      protected void alertOthers() {
	          double d0 = this.getFollowDistance();
	          AABB aabb = AABB.unitCubeFromLowerCorner(this.mob.position()).inflate(d0, 10.0D, d0);
	          List<? extends LavaSquid> list = this.mob.level().getEntitiesOfClass(LavaSquid.class, aabb, EntitySelector.NO_SPECTATORS);
	          Iterator<? extends LavaSquid> iterator = list.iterator();

	          while(true) {
	             Mob mob;
	             while(true) {
	                if (!iterator.hasNext()) {
	                   return;
	                }

	                mob = (Mob)iterator.next();
	                if (this.mob != mob && mob.getTarget() == null ) {
	                    if (this.toIgnoreAlert == null) {
	                       break;
	                    }

	                    boolean flag = false;

	                    for(Class<?> oclass : this.toIgnoreAlert) {
	                       if (mob.getClass() == oclass) {
	                          flag = true;
	                          break;
	                       }
	                    }

	                    if (!flag) {
	                       break;
	                    }
	                 }
	              }

	              this.alertOther(mob, this.mob.getLastHurtByMob());
	           }
	        }

	        protected void alertOther(Mob p_26042_, LivingEntity p_26043_) {
	           p_26042_.setTarget(p_26043_);
	        }
	      private double getFollowDistance() {
	         return this.mob.getAttributeValue(Attributes.FOLLOW_RANGE);
	      }
	   }

}
