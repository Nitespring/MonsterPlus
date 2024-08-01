package github.nitespring.monsterplus.common.entity;

import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import github.nitespring.monsterplus.config.CommonConfig;
import github.nitespring.monsterplus.core.init.EntityInit;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.AbstractGolem;
import net.minecraft.world.entity.monster.Blaze;
import net.minecraft.world.entity.monster.Ghast;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.LargeFireball;
import net.minecraft.world.entity.projectile.SmallFireball;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

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
				.add(Attributes.FOLLOW_RANGE, 50);

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


	@Override
	public boolean isAlliedTo(Entity e) {
		if (e == null) {
			return false;
		} else if (e == this) {
			return true;
		} else if (super.isAlliedTo(e)) {
			return true;
		} else if (e instanceof LavaSquid || e instanceof MotherLavaSquid) {
			return true;
		}  else {
			return false;
		}
	}
	
	
	
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
	         LivingEntity target = this.mob.getTarget();
	         if (target != null) {
	            boolean flag = this.mob.getSensing().hasLineOfSight(target);
	            if (flag) {
	               this.lastSeen = 0;
	            } else {
	               ++this.lastSeen;
	            }

	            double d0 = this.mob.distanceToSqr(target);
	            if (d0 < 4.0D) {
	               if (!flag) {
	                  return;
	               }

	               if (this.attackTime <= 0) {
	                  this.attackTime = 20;
	                  this.mob.doHurtTarget(target);
	               }

	               this.mob.getMoveControl().setWantedPosition(target.getX(), target.getY(), target.getZ(), 1.0D);
	            } else if (d0 < this.getFollowDistance() * this.getFollowDistance() && flag) {
	               double d1 = target.getX() - this.mob.getX();
	               double d2 = target.getY(0.5D) - this.mob.getY(0.5D);
	               double d3 = target.getZ() - this.mob.getZ();
	               
	               //
	               
	               if (this.attackTime <= 0) {
	            	   
	            	   Random rand = new Random();
	            	   int rng = rand.nextInt(10) + 1;
	            	   
	            	   if(rng >=8 && this.attackStep==1) {
	            		   //Vec3 aim = this.mob.getLookAngle();
	            		   
	            		   LargeFireball fireball = new LargeFireball(this.mob.level(), this.mob,new Vec3(d1/d0, d2/d0, d3/d0), 2);
						   fireball.setDeltaMovement(d1/d0, d2/d0, d3/d0);
	                        
	                        fireball.setPos(this.mob.getX()+ 3*d1/d0, this.mob.getY(0.5) + 3*d2/d0, this.mob.getZ() + 3*d3/d0);
	                        fireball.setOwner(mob);
	                        this.mob.level().addFreshEntity(fireball);
	            		   
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
	                     Vec3 pos = mob.position().add(0,0.5f+mob.getBbHeight()/2,0);
						 Vec3 pos1 = mob.getTarget().position().add(0,mob.getTarget().getBbHeight()/2,0);
						 Vec3 aim = pos1.add(pos.scale(-1)).normalize();
						 Vec3 pos2= pos.add(aim.scale(3.5));
						 Double a = -Math.PI/6;
	                     for(int i = 0; i <= 1; ++i) {
							Double b = (this.attackStep-1)*a;
							Double d = 3.5;
							Random r = new Random();
							Vec3 aim1 = new Vec3(aim.z,-aim.y,aim.x);
							Vec3 aim2 = new Vec3(aim.x*Math.cos(b)-aim.z*Math.sin(b),
									aim.y,
									aim.z*Math.cos(b)+aim.x*Math.sin(b));
							/*Vec3 pos3=new Vec3(pos2.x+d*(aim.x*Math.cos(b)-aim.z*Math.sin(b)),
									pos2.y+d*aim.y*Math.sin(b),
									pos2.z+d*(aim.z*Math.cos(b)+aim.x*Math.sin(b)));*/
							 double e = Math.sqrt(aim.x*aim.x+aim.z*aim.z);
							 Vec3 pos3=new Vec3(pos2.x+d*(aim1.x*Math.cos(b)-aim.y*Math.sin(b)),
									 pos2.y+d*(aim.y*Math.cos(b)+e*Math.sin(b)),
									 pos2.z+d*(aim1.z*Math.cos(b)-aim.y*Math.sin(b)));
							if(i==1){
								b = (this.attackStep-1)*a+Math.PI;
								pos3=new Vec3(pos2.x+d*(aim1.x*Math.cos(b)-aim.y*Math.sin(b)),
										pos2.y+d*(aim.y*Math.cos(b)+e*Math.sin(b)),
										pos2.z+d*(aim1.z*Math.cos(b)-aim.y*Math.sin(b)));
							}
							 Vec3 pos4 = pos1.add(2.5f*(r.nextFloat()-0.5f),2.5f*(r.nextFloat()-0.5f),2.5f*(r.nextFloat()-0.5f));
							Vec3 aim3 = pos4.add(pos3.scale(-1)).normalize();
	                    	/* double x = this.mob.getX() + 50.0*Math.sqrt(d2*d2 +  d3*d3)/d0*(Math.cos((this.attackStep-1)*Math.PI/6));
	                    	 double y = this.mob.getY(0.5) + 50.0*Math.sqrt(d1*d1 +  d3*d3)/d0*(Math.sin((this.attackStep-1)*Math.PI/6));
	                    	 double z = this.mob.getZ() + 50.0*Math.sqrt(d2*d2 +  d1*d1)/d0*(Math.cos((this.attackStep-1)*Math.PI/6));
							Vec3 vec = new Vec3(x,y,z).normalize();
	                    	 double d = Math.sqrt((target.getX()-x)*(target.getX()-x)+(target.getY(0.5)-y)*(target.getY(0.5)-y)+(target.getZ()-z)*(target.getZ()-z));
							 //double d = mob.distanceTo(target);*/
							Vec3 mov = aim3.scale(0.1f);
	                        SmallFireball smallfireball = new SmallFireball(mob.level(),mob, mov);
							smallfireball.setOwner(mob);
	                        smallfireball.setDeltaMovement(mov);
							smallfireball.setPos(pos3);
	                        
	                        this.mob.level().addFreshEntity(smallfireball);
	                     }
	                  }
	                  
	                  
	                  //
	                  
	                  
	                  
	                  
	                  
	               }
	               
	               

	               this.mob.getLookControl().setLookAt(target, 10.0F, 10.0F);
	            } else if (this.lastSeen < 5) {
	               this.mob.getMoveControl().setWantedPosition(target.getX(), target.getY(), target.getZ(), 1.0D);
	            }

	            super.tick();
	         }
	         
	         alertOthersTick++;
	         
	         if (alertOthersTick>=600) {
	        	 this.alertOthers();
	         }
	      }
	      
	      protected void alertOthers() {
	          //double d0 = this.getFollowDistance();
			  double d0 = 100;
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
