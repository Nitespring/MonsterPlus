package github.nitespring.monsterplus.common.entity.ancienthero;

import github.nitespring.monsterplus.common.entity.projectiles.SkullProjectile;
import github.nitespring.monsterplus.config.CommonConfig;
import github.nitespring.monsterplus.core.init.EntityInit;
import github.nitespring.monsterplus.core.init.ItemInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.pathfinder.Path;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;
import java.util.Random;

public class AncientHero extends AbstractSkeleton{


	private static final EntityDataAccessor<Integer> COMBATSTATE = SynchedEntityData.defineId(AncientHero.class, EntityDataSerializers.INT);
	private static final EntityDataAccessor<Integer> ENTITYSTATE = SynchedEntityData.defineId(AncientHero.class, EntityDataSerializers.INT);

	/*public static final ResourceLocation ARMOR_MODIFIER = ResourceLocation.withDefaultNamespace("ancient_hero_armor_modifier");
	public static final ResourceLocation TOUGHNESS_MODIFIER = ResourceLocation.withDefaultNamespace("ancient_hero_toughness_modifier");
	public static final ResourceLocation KNOCKBACK_MODIFIER = ResourceLocation.withDefaultNamespace("ancient_hero_knockback_modifier");*/
	public static final ResourceLocation SPEED_MODIFIER = ResourceLocation.withDefaultNamespace("ancient_hero_speed_modifier");

	public AncientHero(EntityType<? extends AbstractSkeleton> p_32133_, Level p_32134_) {
		super(p_32133_, p_32134_);
		this.xpReward = 30;
	}

	@Override
	public boolean canBeAffected(MobEffectInstance pPotioneffect) {
		return pPotioneffect.is(MobEffects.WITHER) ? false : super.canBeAffected(pPotioneffect);
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return SoundEvents.WITHER_SKELETON_AMBIENT;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource pDamageSource) {
		return SoundEvents.WITHER_SKELETON_HURT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.WITHER_SKELETON_DEATH;
	}

	@Override
	protected SoundEvent getStepSound() {
		return SoundEvents.WITHER_SKELETON_STEP;
	}


	 public static  AttributeSupplier.Builder setCustomAttributes(){
			return Monster.createMonsterAttributes()
					.add(Attributes.MAX_HEALTH, 100.0D)
					.add(Attributes.MOVEMENT_SPEED, 0.27D)
					.add(Attributes.ATTACK_DAMAGE, 8)
					.add(Attributes.ATTACK_SPEED, 1.0D)
					.add(Attributes.ATTACK_KNOCKBACK, 0.7D)
					.add(Attributes.KNOCKBACK_RESISTANCE, 0.6D)
					.add(Attributes.ARMOR, 12.0D)
					.add(Attributes.ARMOR_TOUGHNESS, 4.0D)
					.add(Attributes.FOLLOW_RANGE, 50)
					.add(Attributes.STEP_HEIGHT, 2.0D)
					.add(Attributes.FALL_DAMAGE_MULTIPLIER, 0.1D)
					.add(Attributes.GRAVITY, 0.4D)
					.add(Attributes.MOVEMENT_EFFICIENCY, 1.0D)
					.add(Attributes.WATER_MOVEMENT_EFFICIENCY, 1.0D);
	
		  }
	public static boolean checkAncientHeroSpawnRules(EntityType<? extends Monster> p_219014_, ServerLevelAccessor p_219015_, MobSpawnType p_219016_, BlockPos blockPos, RandomSource p_219018_) {
		return p_219015_.getDifficulty() != Difficulty.PEACEFUL && isDarkEnoughToSpawn(p_219015_, blockPos, p_219018_) && checkMobSpawnRules(p_219014_, p_219015_, p_219016_, blockPos, p_219018_)
				&& blockPos.getY() <= 12 && CommonConfig.spawn_ancient_hero.get();
	}


	@Override
		public void tick() {
			super.tick();
			switch(getEntityState()){
				case 0:
					if(getHealth()<=getMaxHealth()*0.75f){
						setEntityState(1);
						playSound(SoundEvents.ITEM_BREAK);
						this.getAttributes().getInstance(Attributes.ARMOR).setBaseValue(getAttributeBaseValue(Attributes.ARMOR)*0.5f);
						this.getAttributes().getInstance(Attributes.ARMOR_TOUGHNESS).setBaseValue(getAttributeBaseValue(Attributes.ARMOR_TOUGHNESS)*0.5f);
						this.getAttributes().getInstance(Attributes.KNOCKBACK_RESISTANCE).setBaseValue(getAttributeBaseValue(Attributes.KNOCKBACK_RESISTANCE)*0.5f);
						this.getAttributes().getInstance(Attributes.MOVEMENT_SPEED).setBaseValue(getAttributeBaseValue(Attributes.MOVEMENT_SPEED)+0.01f);
						shootSkull();
						if(getTarget()!=null) {
							if (distanceToSqr(getTarget()) <= 8 * 8) {
								setDeltaMovement(getTarget().position().add(position().scale(-1)).scale(-1).add(0, 0.25f, 0).normalize().scale(2.5f));
							}
						}
					}
					break;
				case 1:
					if(getHealth()<=getMaxHealth()*0.5f){
						setEntityState(2);
						playSound(SoundEvents.WITHER_SPAWN);
						this.getAttributes().getInstance(Attributes.MOVEMENT_SPEED).setBaseValue(getAttributeBaseValue(Attributes.MOVEMENT_SPEED)+0.01f);
						this.getAttributes().getInstance(Attributes.ATTACK_SPEED).setBaseValue(getAttributeBaseValue(Attributes.ATTACK_SPEED)+0.25f);
						AncientHeroSkull skull = new AncientHeroSkull(EntityInit.ANCIENT_HERO_SKULL.get(),this.level());
						skull.setPos(this.position().add(0,2.0f,0));
						skull.setOwner(this);
						if(this.getTarget()!=null){
							skull.setTarget(getTarget());
						}
						skull.anchorPoint= BlockPos.containing(this.position().add(0,10,0));
						this.level().addFreshEntity(skull);
						for(int i =0; i<=5; i++){
							shootSkull();
						}
						if(getTarget()!=null) {
							if (distanceToSqr(getTarget()) <= 8 * 8) {
								setDeltaMovement(getTarget().position().add(position().scale(-1)).scale(-1).add(0, 0.25f, 0).normalize().scale(2.5f));
							}
						}
					}
					break;
				case 2:
					if(getHealth()<=getMaxHealth()*0.25f){
						setEntityState(3);
						playSound(SoundEvents.ITEM_BREAK);
						this.getAttributes().getInstance(Attributes.ARMOR).setBaseValue(0);
						this.getAttributes().getInstance(Attributes.ARMOR_TOUGHNESS).setBaseValue(0);
						this.getAttributes().getInstance(Attributes.KNOCKBACK_RESISTANCE).setBaseValue(0);
						this.getAttributes().getInstance(Attributes.MOVEMENT_SPEED).setBaseValue(getAttributeBaseValue(Attributes.MOVEMENT_SPEED)+0.01f);
						shootSkull();
						if(getTarget()!=null) {
							if (distanceToSqr(getTarget()) <= 8 * 8) {
								setDeltaMovement(getTarget().position().add(position().scale(-1)).scale(-1).add(0, 0.25f, 0).normalize().scale(2.5f));
							}
						}
					}
					break;
			}

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
	public boolean isAlliedTo(Entity pEntity) {
		if(pEntity instanceof AncientHeroSkull||pEntity instanceof AncientHero) {
			return true;
		}else if(pEntity instanceof AncientHeroSkull skull){
			if(skull.owner!=null&&this==skull.owner){
				return true;
			}else{
				return skull.isAlliedTo(this);
			}
		} else {
			return super.isAlliedTo(pEntity);
		}
	}
	@Override
		protected void registerGoals() {
			super.registerGoals();
			this.goalSelector.addGoal(1, new AncientHeroAttackGoal(this));
	}

	@Override
	protected float getEquipmentDropChance(EquipmentSlot pSlot) {
		return 0;
	}

	@Override
	protected void populateDefaultEquipmentSlots(RandomSource p_218949_, DifficultyInstance p_218950_) {
		
		 this.setItemSlot(EquipmentSlot.MAINHAND, ItemInit.ANCIENT_BROKEN_SWORD.get().getDefaultInstance());
	}
	public void aiStep() {
		super.aiStep();

		this.level().addParticle(ParticleTypes.SOUL, this.getRandomX(0.6D), this.getRandomY(), this.getRandomZ(0.6D), 0.0, 0.0, 0.0);
	}

	public void shootSkull(){
		SkullProjectile skull = new SkullProjectile(EntityInit.SPECTRAL_SKULL_PROJECTILE.get(),this.level());
		skull.setOwner(this);
		if(this.getTarget()!=null) {
			skull.setTarget(this.getTarget());
		}
		Vec3 aim = getLookAngle();
		Random rn = new Random();
		aim = aim.add(1.5f*(rn.nextFloat()-0.5f),0.75f*rn.nextFloat(),1.5f*(rn.nextFloat()-0.5f));
		skull.setPos(this.position().add(1.5f*aim.x+2.5f*(rn.nextFloat()-0.5f),1.5+0.75f*aim.y,1.5f*aim.z+2.5f*(rn.nextFloat()-0.5f)));
		skull.setDeltaMovement(aim.normalize().scale(0.05f));
		skull.accelerationPower=0.05f;
		skull.setYRot(this.yHeadRot);
		skull.setAttackDamage(4.0f);
		level().addFreshEntity(skull);
	}

	@Override
	public void performRangedAttack(LivingEntity pTarget, float pDistanceFactor) {
		SkullProjectile skull = new SkullProjectile(EntityInit.SPECTRAL_SKULL_PROJECTILE.get(),this.level());
		skull.setOwner(this);
		if(this.getTarget()!=null) {
			skull.setTarget(this.getTarget());
		}
		Vec3 aim = getLookAngle();
		Random rn = new Random();
		aim = aim.add(0.25f*(rn.nextFloat()-0.5f),0.25f*rn.nextFloat(),0.25f*(rn.nextFloat()-0.5f));
		skull.setPos(this.position().add(1.5f*aim.x,1.5+0.75f*aim.y,1.5f*aim.z));
		skull.setDeltaMovement(aim.normalize().scale(0.05f));
		skull.accelerationPower=0.075f;
		skull.setYRot(this.yHeadRot);
		skull.setAttackDamage(4.0f);
		level().addFreshEntity(skull);
	}

	public int getEntityState(){return this.getEntityData().get(ENTITYSTATE);}
	public void setEntityState(int i){this.getEntityData().set(ENTITYSTATE, i);}
	public int getCombatState(){return this.getEntityData().get(COMBATSTATE);}
	public void setCombatState(int i){this.getEntityData().set(COMBATSTATE, i);}
	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder) {
		super.defineSynchedData(builder);
		builder.define(ENTITYSTATE, 0);
		builder.define(COMBATSTATE, 0);
	}

	@Override
	public void readAdditionalSaveData(CompoundTag tag) {
		super.readAdditionalSaveData(tag);
		this.setEntityState(tag.getInt("EntityState"));
		this.setCombatState(tag.getInt("CombatState"));

	}

	@Override
	public void addAdditionalSaveData(CompoundTag tag) {
		super.addAdditionalSaveData(tag);
		tag.putInt("EntityState", this.getEntityState());
		tag.putInt("CombatState", this.getCombatState());

	}

	@Override
	public void reassessWeaponGoal() {}

	public class AncientHeroAttackGoal extends Goal {
		protected final AncientHero mob;
		private final double strafingSpeedModifier = 1.0f;
		private final double runningSpeedModifier = 1.2f;
		private boolean followingTargetEvenIfNotSeen = false;
		private Path path;
		private double pathedTargetX;
		private double pathedTargetY;
		private double pathedTargetZ;
		private int ticksUntilNextPathRecalculation;
		private int ticksUntilNextAttack;
		private final int attackInterval = 20;
		private long lastCanUseCheck;
		private static final long COOLDOWN_BETWEEN_CAN_USE_CHECKS = 20L;
		private int failedPathFindingPenalty = 0;
		private boolean canPenalize = false;


		private int attackIntervalMin = 16;
		private final float rangedAttackRadiusSqr = 20*20;
		private int attackTime = -1;
		private int seeTime;
		private boolean strafingClockwise;
		private boolean strafingBackwards;
		private int strafingTime = -1;
		public int switchCombatStateTick;

		public AncientHeroAttackGoal(AncientHero pMob) {
			this.mob = pMob;
			this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
		}
		public void setMinAttackInterval(int pAttackCooldown) {
			this.attackIntervalMin = pAttackCooldown;
		}
		@Override
		public boolean canUse() {
			long i = this.mob.level().getGameTime();
			if (i - this.lastCanUseCheck < 20L) {
				return false;
			} else {
				this.lastCanUseCheck = i;
				LivingEntity livingentity = this.mob.getTarget();
				if (livingentity == null) {
					return false;
				} else if (!livingentity.isAlive()) {
					return false;
				} else {
					if (canPenalize) {
						if (--this.ticksUntilNextPathRecalculation <= 0) {
							this.path = this.mob.getNavigation().createPath(livingentity, 0);
							this.ticksUntilNextPathRecalculation = 4 + this.mob.getRandom().nextInt(7);
							return this.path != null;
						} else {
							return true;
						}
					}
					this.path = this.mob.getNavigation().createPath(livingentity, 0);
					return this.path != null ? true : this.mob.isWithinMeleeAttackRange(livingentity);
				}
			}
		}

		public double getSpeedModifier(){
			return (mob.getCombatState()==1) ? strafingSpeedModifier : runningSpeedModifier;
		}
		@Override
		public boolean canContinueToUse() {
			LivingEntity livingentity = this.mob.getTarget();
			if (livingentity == null) {
				return false;
			} else if (!livingentity.isAlive()) {
				return false;
			} else if (!this.followingTargetEvenIfNotSeen) {
				return !this.mob.getNavigation().isDone();
			} else {
				return !this.mob.isWithinRestriction(livingentity.blockPosition())
						? false
						: !(livingentity instanceof Player) || !livingentity.isSpectator() && !((Player)livingentity).isCreative();
			}
		}

		@Override
		public void start() {
			if(mob.getCombatState()!=1) {
				this.mob.getNavigation().moveTo(this.path, this.getSpeedModifier());
			}
			this.mob.setAggressive(true);
			this.ticksUntilNextPathRecalculation = 0;
			this.ticksUntilNextAttack = 0;
			this.switchCombatStateTick=0;
			/*if(switchCombatStateTick==0){
				this.switchCombatStateTick = 100;
			}*/

		}

		@Override
		public void stop() {
			LivingEntity livingentity = this.mob.getTarget();
			if (!EntitySelector.NO_CREATIVE_OR_SPECTATOR.test(livingentity)) {
				this.mob.setTarget(null);
			}
			this.mob.setAggressive(false);
			this.mob.getNavigation().stop();
			this.seeTime = 0;
			this.attackTime = -1;
		}

		@Override
		public boolean requiresUpdateEveryTick() {
			return true;
		}

		@Override
		public void tick() {
			doMovement();
			switchCombatStateTick = Math.max(this.switchCombatStateTick - 1, 0);
			if(mob.getEntityState()==2||mob.getEntityState()==3) {
				if(mob.getCombatState()!=0){setCombatState(0);}
			}else{
				if(switchCombatStateTick<=0) {
					Random rn = new Random();
					if (getCombatState() == 1) {
						if (rn.nextInt(13) <= 4 || (rn.nextInt(13) <= 7 && mob.distanceToSqr(mob.getTarget()) <= (4 * 4))) {
							mob.setCombatState(0);
							//this.stop();
							mob.getNavigation().stop();
							ticksUntilNextPathRecalculation = 0;
						}
						switchCombatStateTick = 160;
					} else {
						if (rn.nextInt(13) <= 4 || (rn.nextInt(13) <= 7 && mob.distanceToSqr(mob.getTarget()) >= (6 * 6))) {
							mob.setCombatState(1);
							//this.stop();
							mob.getNavigation().stop();
							mob.shootSkull();
							if(mob.distanceToSqr(mob.getTarget())<=8*8) {
								mob.setDeltaMovement(getTarget().position().add(position().scale(-1)).scale(-1).add(0, 0.25f, 0).normalize().scale(2.5f));
							}
						}
						switchCombatStateTick = 240;
					}
				}
			}
			int i = 16;
			switch(mob.getCombatState()){
				case 1:
					if(mob.tickCount%i==0) {
						shootSkull();
						playSound(SoundEvents.WITHER_SHOOT, 0.2f, 0.4f);
					}
					break;
				default:
					i=48;
					if(mob.getEntityState()==1){
						i=32;
					}
					if(mob.getEntityState()==2){
						i=24;
					}
					if(mob.getEntityState()==3){
						i=16;
					}
					if(mob.tickCount%i==0) {
						shootSkull();
						playSound(SoundEvents.WITHER_SHOOT, 0.2f, 0.4f);
					}
					break;
			}
		}
		protected void checkAndPerformAttack(LivingEntity pTarget) {
			if (this.canPerformAttack(pTarget)) {
				this.resetAttackCooldown();
				this.mob.swing(InteractionHand.MAIN_HAND);
				this.mob.doHurtTarget(pTarget);
			}
		}

		protected void resetAttackCooldown() {
			this.ticksUntilNextAttack = this.adjustedTickDelay(20);
		}

		protected boolean isTimeToAttack() {
			return this.ticksUntilNextAttack <= 0;
		}

		protected boolean canPerformAttack(LivingEntity pEntity) {
			return this.isTimeToAttack() && this.mob.isWithinMeleeAttackRange(pEntity) && this.mob.getSensing().hasLineOfSight(pEntity);
		}

		protected int getTicksUntilNextAttack() {
			return this.ticksUntilNextAttack;
		}

		protected int getAttackInterval() {
			return this.adjustedTickDelay(20);
		}

		public void doMovement(){
			LivingEntity livingentity = this.mob.getTarget();
			if (livingentity != null) {
				boolean flag = this.mob.getSensing().hasLineOfSight(livingentity);
				if (flag) {
					this.seeTime++;
				} else {
					this.seeTime--;
				}
			}
			if(mob.getCombatState()==1) {
				//System.out.println("Performing Strafing");
				if (livingentity != null) {
					double d0 = this.mob.distanceToSqr(livingentity.getX(), livingentity.getY(), livingentity.getZ());
					boolean flag = this.mob.getSensing().hasLineOfSight(livingentity);
					boolean flag1 = this.seeTime > 0;
					if (flag != flag1) {
						this.seeTime = 0;
					}
//
					if (!(d0 > (double) this.rangedAttackRadiusSqr) && this.seeTime >= 20) {
						this.mob.getNavigation().stop();
						this.strafingTime++;
					} else {
						this.mob.getNavigation().moveTo(livingentity, this.getSpeedModifier());
						this.strafingTime = -1;
					}

					if (this.strafingTime >= 20) {
						if ((double) this.mob.getRandom().nextFloat() < 0.3) {
							this.strafingClockwise = !this.strafingClockwise;
						}

						if ((double) this.mob.getRandom().nextFloat() < 0.3) {
							this.strafingBackwards = !this.strafingBackwards;
						}

						this.strafingTime = 0;
					}

					if (this.strafingTime > -1) {
						if (d0 > (double) (this.rangedAttackRadiusSqr * 0.75F)) {
							this.strafingBackwards = false;
						} else if (d0 < (double) (this.rangedAttackRadiusSqr * 0.25F)) {
							this.strafingBackwards = true;
						}

						this.mob.getMoveControl().strafe(this.strafingBackwards ? -0.5F : 0.5F, this.strafingClockwise ? 0.5F : -0.5F);
						if (this.mob.getControlledVehicle() instanceof Mob mob) {
							mob.lookAt(livingentity, 30.0F, 30.0F);
						}

						this.mob.lookAt(livingentity, 30.0F, 30.0F);
					} else {
						this.mob.getLookControl().setLookAt(livingentity, 30.0F, 30.0F);
					}

					if (--this.attackTime <= 0 && this.seeTime >= -60) {
						if (flag) {
							this.mob.performRangedAttack(livingentity,0);
							this.attackTime = this.attackIntervalMin;
							if(mob.distanceToSqr(mob.getTarget())<=2*2) {
								mob.setDeltaMovement(mob.getDeltaMovement().add(getTarget().position().add(position().scale(-1)).scale(-1).add(0, 0.25f, 0).normalize().scale(0.75f)));
							}
						}
					}
				}
			}else{
				//System.out.println("Performing Attacking");
				if (livingentity != null) {
					this.mob.getLookControl().setLookAt(livingentity, 30.0F, 30.0F);
					this.ticksUntilNextPathRecalculation = Math.max(this.ticksUntilNextPathRecalculation - 1, 0);
					if ((this.followingTargetEvenIfNotSeen || this.mob.getSensing().hasLineOfSight(livingentity))
							&& this.ticksUntilNextPathRecalculation <= 0
							&& (
							this.pathedTargetX == 0.0 && this.pathedTargetY == 0.0 && this.pathedTargetZ == 0.0
									|| livingentity.distanceToSqr(this.pathedTargetX, this.pathedTargetY, this.pathedTargetZ) >= 1.0
									|| this.mob.getRandom().nextFloat() < 0.05F
					)) {
						this.pathedTargetX = livingentity.getX();
						this.pathedTargetY = livingentity.getY();
						this.pathedTargetZ = livingentity.getZ();
						this.ticksUntilNextPathRecalculation = 4 + this.mob.getRandom().nextInt(7);
						double d0 = this.mob.distanceToSqr(livingentity);
						if (this.canPenalize) {
							this.ticksUntilNextPathRecalculation += failedPathFindingPenalty;
							if (this.mob.getNavigation().getPath() != null) {
								net.minecraft.world.level.pathfinder.Node finalPathPoint = this.mob.getNavigation().getPath().getEndNode();
								if (finalPathPoint != null && livingentity.distanceToSqr(finalPathPoint.x, finalPathPoint.y, finalPathPoint.z) < 1)
									failedPathFindingPenalty = 0;
								else
									failedPathFindingPenalty += 10;
							} else {
								failedPathFindingPenalty += 10;
							}
						}
						if (d0 > 1024.0) {
							this.ticksUntilNextPathRecalculation += 10;
						} else if (d0 > 256.0) {
							this.ticksUntilNextPathRecalculation += 5;
						}

						if (!this.mob.getNavigation().moveTo(livingentity, this.getSpeedModifier())) {
							this.ticksUntilNextPathRecalculation += 15;
						}

						this.ticksUntilNextPathRecalculation = this.adjustedTickDelay(this.ticksUntilNextPathRecalculation);
					}

					this.ticksUntilNextAttack = Math.max(this.ticksUntilNextAttack - 1, 0);
					this.checkAndPerformAttack(livingentity);
				}
			}
		}


	}


}
