package github.nitespring.monsterplus.common.entity;

import github.nitespring.monsterplus.MonsterPlus;
import github.nitespring.monsterplus.config.CommonConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
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
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.List;
import java.util.Random;

import static net.minecraft.world.entity.monster.Monster.isDarkEnoughToSpawn;

public class DemonEye extends FlyingMob implements Enemy{

   Vec3 moveTargetPoint = Vec3.ZERO;
   BlockPos anchorPoint = BlockPos.ZERO;
   AttackPhase attackPhase = AttackPhase.CIRCLE;
   @Nullable
   Mob owner;

	private static final EntityDataAccessor<Integer> EYE_TYPE = SynchedEntityData.defineId(DemonEye.class, EntityDataSerializers.INT);
	private static final EntityDataAccessor<Integer> ANIMATIONSTATE = SynchedEntityData.defineId(DemonEye.class, EntityDataSerializers.INT);
	private static final EntityDataAccessor<Float> EYE_SCALE = SynchedEntityData.defineId(DemonEye.class, EntityDataSerializers.FLOAT);
	private static final EntityDataAccessor<Float> SCALE = SynchedEntityData.defineId(DemonEye.class, EntityDataSerializers.FLOAT);
	int animationTick;
	public static final ResourceLocation ATTACK_DAMAGE_MODIFIER_TEETH = ResourceLocation.withDefaultNamespace("fanged_demon_eye_attack_damage_modifier");
	public static final ResourceLocation HEALTH_MODIFIER_SIZE = ResourceLocation.fromNamespaceAndPath(MonsterPlus.MODID,"large_demon_eye_max_health_modifier");
	public static final ResourceLocation SPEED_MODIFIER_SIZE = ResourceLocation.fromNamespaceAndPath(MonsterPlus.MODID,"small_demon_eye_movement_speed_modifier");
	public DemonEye(EntityType<? extends DemonEye> p_21368_, Level p_21369_) {
		super(p_21368_, p_21369_);
		this.xpReward = 7;
		this.moveControl = new EyeMoveControl(this);
	    this.lookControl = new EyeLookControl(this);
	}

	public int getEyeType(){return this.getEntityData().get(EYE_TYPE);}
	public void setEyeType(int i){this.getEntityData().set(EYE_TYPE, i);}

	public int getAnimationState(){return this.getEntityData().get(ANIMATIONSTATE);}
	public void setAnimationState(int i){this.getEntityData().set(ANIMATIONSTATE, i);}
	public float getEyeScale(){return this.getEntityData().get(EYE_SCALE);}
	public void setEyeScale(float f){this.getEntityData().set(EYE_SCALE, f);}
	public float getDimensionScale(){return this.getEntityData().get(SCALE);}
	public void setDimensionScale(float f){this.getEntityData().set(SCALE, f);}


	@Nullable
	public Mob getOwner() {
	      return this.owner;
	   }

	public void setOwner(Mob p_33995_) {
	      this.owner = p_33995_;
	   }
	
	public static  AttributeSupplier.Builder setCustomAttributes(){
		return Monster.createMonsterAttributes()
				.add(Attributes.MAX_HEALTH, 12.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.25D)
				.add(Attributes.ATTACK_DAMAGE, 4)
				.add(Attributes.ATTACK_SPEED, 1.2D)
				.add(Attributes.ATTACK_KNOCKBACK, 0.2D)
				.add(Attributes.KNOCKBACK_RESISTANCE, 0.0D)
				.add(Attributes.FOLLOW_RANGE, 30);

	}


	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder) {
		super.defineSynchedData(builder);
		builder.define(EYE_TYPE, 0);
		builder.define(ANIMATIONSTATE, 0);
		builder.define(EYE_SCALE, 1.0f);
		builder.define(SCALE, 1.0f);
	}

	@Override
	public void readAdditionalSaveData(CompoundTag tag) {
		super.readAdditionalSaveData(tag);
		if (tag.contains("AX")) {
			this.anchorPoint = new BlockPos(tag.getInt("AX"), tag.getInt("AY"), tag.getInt("AZ"));
		}
		this.setEyeType(tag.getInt("EyeType"));
		this.setEyeScale(tag.getFloat("EyeScale"));
		this.setDimensionScale(tag.getFloat("Scale"));

	}

	@Override
	public void addAdditionalSaveData(CompoundTag tag) {
		super.addAdditionalSaveData(tag);
		tag.putInt("AX", this.anchorPoint.getX());
		tag.putInt("AY", this.anchorPoint.getY());
		tag.putInt("AZ", this.anchorPoint.getZ());
		tag.putInt("EyeType", this.getEyeType());
		tag.putFloat("EyeScale", this.getEyeScale());
		tag.putFloat("Scale", this.getDimensionScale());

	}

	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor p_33126_, DifficultyInstance p_33127_, MobSpawnType p_33128_, @Nullable SpawnGroupData p_33129_) {
		this.anchorPoint = this.blockPosition().above(10);
		randomizeEyeColour();
		randomizeEyeBallSize();
		randomizeSize();
		//System.out.println(this.getAttributeValue(Attributes.MAX_HEALTH));
		//System.out.println(this.getAttributeValue(Attributes.ATTACK_DAMAGE));
		return super.finalizeSpawn(p_33126_, p_33127_, p_33128_, p_33129_);
	}
	public void randomizeEyeColour(){
		Random rn = new Random();
		int r = rn.nextInt(24) + 1;
		switch(r){
			case 1,2,3,4,5,6:
				this.setEyeType(1);
				break;
			case 7,8,9,10:
				this.setEyeType(2);
				break;
			case 11,12:
				this.setEyeType(3);
				break;
			case 13:
				this.setEyeType(4);
				break;
			case 14,15,16:
				this.setEyeType(5);
				this.getAttributes().getInstance(Attributes.ATTACK_DAMAGE).addPermanentModifier(
						new AttributeModifier(ATTACK_DAMAGE_MODIFIER_TEETH,2.0f, AttributeModifier.Operation.ADD_VALUE)
				);
				break;
			default:
				this.setEyeType(0);
				break;
		}
	}
	public void randomizeEyeBallSize(){
		Random rn = new Random();
		setEyeScale(0.9f+0.35f*rn.nextFloat());
	}
	public void randomizeSize(){
		Random rn = new Random();
		int r = rn.nextInt(13);
		switch(r){
			case 1,2:
				this.setDimensionScale(0.5f+0.25f*rn.nextFloat());
				this.getAttributes().getInstance(Attributes.MOVEMENT_SPEED).addPermanentModifier(
						new AttributeModifier(SPEED_MODIFIER_SIZE,0.02f, AttributeModifier.Operation.ADD_VALUE)
				);
				break;
			case 3,4,5,6,7:
				this.setDimensionScale(1.0f+0.25f*(2*rn.nextFloat()-1));
				break;
			case 8,9:
				this.setDimensionScale(1.25f+0.25f*rn.nextFloat());
				this.getAttributes().getInstance(Attributes.MAX_HEALTH).addPermanentModifier(
						new AttributeModifier(HEALTH_MODIFIER_SIZE,4.0f, AttributeModifier.Operation.ADD_VALUE)
				);
				break;
			case 10:
				this.setDimensionScale(1.5f+0.5f*rn.nextFloat());
				this.getAttributes().getInstance(Attributes.MAX_HEALTH).addPermanentModifier(
						new AttributeModifier(HEALTH_MODIFIER_SIZE,8.0f, AttributeModifier.Operation.ADD_VALUE)
				);
				break;
			default:
				this.setDimensionScale(1.0f);
				break;
		}
	}
	@Override
	public void tick() {
		super.tick();
		Vec3 vec3 = this.getDeltaMovement();
		if (this.xRotO == 0.0F && this.yRotO == 0.0F) {
			double d0 = vec3.horizontalDistance();
			this.setYRot((float)(Mth.atan2(vec3.x, vec3.z) * (double)(180F / (float)Math.PI)));
			this.setXRot((float)(Mth.atan2(vec3.y, d0) * (double)(180F / (float)Math.PI)));
			this.yRotO = this.getYRot();
			this.xRotO = this.getXRot();
		}


	}


	@Override
	public void aiStep() {
		super.aiStep();
		animationTick++;
		boolean flag = this.isSunSensitive() && this.isSunBurnTick();
		if (flag) {
			this.igniteForSeconds(8.0F);
		}
		if(getEyeType()==5){
			if(getAnimationState()==1){
				if(animationTick>=4){
					setAnimationState(0);
					animationTick=0;
				}
			}else{
				if(animationTick>=8){
					setAnimationState(1);
					animationTick=0;
				}
			}
		}

	}

	@Override
	protected void registerGoals() {

		this.goalSelector.addGoal(1, new EyeAttackStrategyGoal());
		this.goalSelector.addGoal(2, new EyeSweepAttackGoal());
		this.goalSelector.addGoal(3, new EyeCircleAroundAnchorGoal());

		//this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
		//this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
		this.targetSelector.addGoal(2, new CopyOwnerTargetGoal(this));
		registerTargets();
	}
	public void registerTargets(){
		this.targetSelector.addGoal(1, new EyeAttackPlayerTargetGoal());
		this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));
	}
	
	@Override
	protected BodyRotationControl createBodyControl() {
	      return new EyeBodyRotationControl(this);
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
	public SoundSource getSoundSource() {
		return SoundSource.HOSTILE;
	}


	public static boolean checkDemonEyeSpawnRules(EntityType<? extends DemonEye> pType, ServerLevelAccessor pLevel, MobSpawnType pSpawnType, BlockPos pPos, RandomSource pRandom) {
		return pLevel.getDifficulty() != Difficulty.PEACEFUL && CommonConfig.spawn_demon_eye.get()
				&& isDarkEnoughToSpawn(pLevel, pPos, pRandom) && pLevel.canSeeSky(pPos)
				&& checkMobSpawnRules(pType, pLevel, pSpawnType, pPos, pRandom);
	}
	protected boolean isSunSensitive() {
		return true;
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
			DemonEye.this.yHeadRot = DemonEye.this.yBodyRot;
			DemonEye.this.yBodyRot = DemonEye.this.getYRot();
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
			if (DemonEye.this.horizontalCollision) {
				DemonEye.this.setYRot(DemonEye.this.getYRot() + 180.0F);
				this.speed = 0.1F;
			}

			double d0 = DemonEye.this.moveTargetPoint.x - DemonEye.this.getX();
			double d1 = DemonEye.this.moveTargetPoint.y - DemonEye.this.getY();
			double d2 = DemonEye.this.moveTargetPoint.z - DemonEye.this.getZ();
			double d3 = Math.sqrt(d0 * d0 + d2 * d2);
			if (Math.abs(d3) > (double)1.0E-5F) {
				double d4 = 1.0D - Math.abs(d1 * (double)0.7F) / d3;
				d0 *= d4;
				d2 *= d4;
				d3 = Math.sqrt(d0 * d0 + d2 * d2);
				double d5 = Math.sqrt(d0 * d0 + d2 * d2 + d1 * d1);
				float f = DemonEye.this.getYRot();
				float f1 = (float)Mth.atan2(d2, d0);
				float f2 = Mth.wrapDegrees(DemonEye.this.getYRot() + 90.0F);
				float f3 = Mth.wrapDegrees(f1 * (180F / (float)Math.PI));
				DemonEye.this.setYRot(Mth.approachDegrees(f2, f3, 4.0F) - 90.0F);
				DemonEye.this.yBodyRot = DemonEye.this.getYRot();
				if (Mth.degreesDifferenceAbs(f, DemonEye.this.getYRot()) < 3.0F) {
					this.speed = Mth.approach(this.speed, 1.8F, 0.005F * (1.8F / this.speed));
				} else {
					this.speed = Mth.approach(this.speed, 0.2F, 0.025F);
				}

				float f4 = (float)(-(Mth.atan2(-d1, d3) * (double)(180F / (float)Math.PI)));
				DemonEye.this.setXRot(f4);
				float f5 = DemonEye.this.getYRot() + 90.0F;
				double d6 = (double)(this.speed * Mth.cos(f5 * ((float)Math.PI / 180F))) * Math.abs(d0 / d5);
				double d7 = (double)(this.speed * Mth.sin(f5 * ((float)Math.PI / 180F))) * Math.abs(d2 / d5);
				double d8 = (double)(this.speed * Mth.sin(f4 * ((float)Math.PI / 180F))) * Math.abs(d1 / d5);
				Vec3 vec3 = DemonEye.this.getDeltaMovement();
				DemonEye.this.setDeltaMovement(vec3.add((new Vec3(d6, d8, d7)).subtract(vec3).scale(0.2D)));
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
				List<Player> list = DemonEye.this.level().getNearbyPlayers(this.attackTargeting, DemonEye.this, DemonEye.this.getBoundingBox().inflate(16.0D, 64.0D, 16.0D));
				if (!list.isEmpty()) {
					list.sort(Comparator.<Entity, Double>comparing(Entity::getY).reversed());

					for(Player player : list) {
						if (DemonEye.this.canAttack(player, TargetingConditions.DEFAULT)) {
							DemonEye.this.setTarget(player);
							return true;
						}
					}
				}

				return false;
			}
		}

		public boolean canContinueToUse() {
			LivingEntity livingentity = DemonEye.this.getTarget();
			return livingentity != null ? DemonEye.this.canAttack(livingentity, TargetingConditions.DEFAULT) : false;
		}
	}

	class EyeAttackLastHurtByPlayerTargetGoal extends Goal {
		private final TargetingConditions attackTargeting = TargetingConditions.forCombat().range(64.0D);
		private int nextScanTick = reducedTickDelay(20);

		public boolean canUse() {
			if (this.nextScanTick > 0) {
				--this.nextScanTick;
				return false;
			} else {
				this.nextScanTick = reducedTickDelay(60);
				List<Player> list = DemonEye.this.level().getNearbyPlayers(this.attackTargeting, DemonEye.this, DemonEye.this.getBoundingBox().inflate(16.0D, 64.0D, 16.0D));
				if (!list.isEmpty()) {
					list.sort(Comparator.<Entity, Double>comparing(Entity::getY).reversed());

					for(Player player : list) {
						if (DemonEye.this.getLastAttacker()==player&&DemonEye.this.canAttack(player, TargetingConditions.DEFAULT)) {
							DemonEye.this.setTarget(player);
							return true;
						}
					}
				}

				return false;
			}
		}

		public boolean canContinueToUse() {
			LivingEntity livingentity = DemonEye.this.getTarget();
			return livingentity != null ? DemonEye.this.canAttack(livingentity, TargetingConditions.DEFAULT) : false;
		}
	}

	class EyeAttackStrategyGoal extends Goal {
		private int nextSweepTick;

		public boolean canUse() {
			LivingEntity livingentity = DemonEye.this.getTarget();
			return livingentity != null ? DemonEye.this.canAttack(livingentity, TargetingConditions.DEFAULT) : false;
		}

		public void start() {
			this.nextSweepTick = this.adjustedTickDelay(10);
			DemonEye.this.attackPhase = AttackPhase.CIRCLE;
			this.setAnchorAboveTarget();
		}

		public void stop() {
			DemonEye.this.anchorPoint = DemonEye.this.level().getHeightmapPos(Heightmap.Types.MOTION_BLOCKING, DemonEye.this.anchorPoint).above(10 + DemonEye.this.random.nextInt(20));
		}

		public void tick() {
			if (DemonEye.this.attackPhase == AttackPhase.CIRCLE) {
				--this.nextSweepTick;
				if (this.nextSweepTick <= 0) {
					DemonEye.this.attackPhase = AttackPhase.SWOOP;
					this.setAnchorAboveTarget();
					this.nextSweepTick = this.adjustedTickDelay((8 + DemonEye.this.random.nextInt(4)) * 20);

				}
			}

		}

		private void setAnchorAboveTarget() {
			DemonEye.this.anchorPoint = DemonEye.this.getTarget().blockPosition().above(20 + DemonEye.this.random.nextInt(20));
			/*if (DemonEye.this.anchorPoint.getY() < DemonEye.this.level().getSeaLevel()) {
				DemonEye.this.anchorPoint = new BlockPos(DemonEye.this.anchorPoint.getX(), DemonEye.this.level().getSeaLevel() + 1, DemonEye.this.anchorPoint.getZ());
			}*/

		}




	}

	class EyeCircleAroundAnchorGoal extends EyeMoveTargetGoal {
		private float angle;
		private float distance;
		private float height;
		private float clockwise;

		public boolean canUse() {
			return DemonEye.this.getTarget() == null || DemonEye.this.attackPhase == AttackPhase.CIRCLE;
		}

		public void start() {
			this.distance = 5.0F + DemonEye.this.random.nextFloat() * 10.0F;
			this.height = -4.0F + DemonEye.this.random.nextFloat() * 9.0F;
			this.clockwise = DemonEye.this.random.nextBoolean() ? 1.0F : -1.0F;
			this.selectNext();
		}

		public void tick() {
			if (DemonEye.this.random.nextInt(this.adjustedTickDelay(350)) == 0) {
				this.height = -4.0F + DemonEye.this.random.nextFloat() * 9.0F;
			}

			if (DemonEye.this.random.nextInt(this.adjustedTickDelay(250)) == 0) {
				++this.distance;
				if (this.distance > 15.0F) {
					this.distance = 5.0F;
					this.clockwise = -this.clockwise;
				}
			}

			if (DemonEye.this.random.nextInt(this.adjustedTickDelay(450)) == 0) {
				this.angle = DemonEye.this.random.nextFloat() * 2.0F * (float)Math.PI;
				this.selectNext();
			}

			if (this.touchingTarget()) {
				this.selectNext();
			}

			if (DemonEye.this.moveTargetPoint.y < DemonEye.this.getY() && !DemonEye.this.level().isEmptyBlock(DemonEye.this.blockPosition().below(1))) {
				this.height = Math.max(1.0F, this.height);
				this.selectNext();
			}

			if (DemonEye.this.moveTargetPoint.y > DemonEye.this.getY() && !DemonEye.this.level().isEmptyBlock(DemonEye.this.blockPosition().above(1))) {
				this.height = Math.min(-1.0F, this.height);
				this.selectNext();
			}

		}

		private void selectNext() {
			if (BlockPos.ZERO.equals(DemonEye.this.anchorPoint)) {
				DemonEye.this.anchorPoint = DemonEye.this.blockPosition();
			}

			this.angle += this.clockwise * 15.0F * ((float)Math.PI / 180F);
			DemonEye.this.moveTargetPoint = Vec3.atLowerCornerOf(DemonEye.this.anchorPoint).add((double)(this.distance * Mth.cos(this.angle)), (double)(-4.0F + this.height), (double)(this.distance * Mth.sin(this.angle)));
		}
	}



	abstract class EyeMoveTargetGoal extends Goal {
		public EyeMoveTargetGoal() {
			this.setFlags(EnumSet.of(Flag.MOVE));
		}

		protected boolean touchingTarget() {
			return DemonEye.this.moveTargetPoint.distanceToSqr(DemonEye.this.getX(), DemonEye.this.getY(), DemonEye.this.getZ()) < 4.0D;
		}
	}

	class EyeSweepAttackGoal extends EyeMoveTargetGoal {
		//private static final int CAT_SEARCH_TICK_DELAY = 20;
		private boolean isScaredOfCat;
		private int catSearchTick;

		public boolean canUse() {
			return DemonEye.this.getTarget() != null && DemonEye.this.attackPhase == AttackPhase.SWOOP;
		}

		public boolean canContinueToUse() {
			LivingEntity livingentity = DemonEye.this.getTarget();
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
					if (DemonEye.this.tickCount > this.catSearchTick) {
						this.catSearchTick = DemonEye.this.tickCount + 20;
						List<Cat> list = DemonEye.this.level().getEntitiesOfClass(Cat.class, DemonEye.this.getBoundingBox().inflate(16.0D), EntitySelector.ENTITY_STILL_ALIVE);

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
			DemonEye.this.setTarget((LivingEntity)null);
			DemonEye.this.attackPhase = AttackPhase.CIRCLE;
		}

		public void tick() {
			LivingEntity livingentity = DemonEye.this.getTarget();
			if (livingentity != null) {
				DemonEye.this.moveTargetPoint = new Vec3(livingentity.getX(), livingentity.getY(0.5D), livingentity.getZ());
				if (DemonEye.this.getBoundingBox().inflate((double)0.2F).intersects(livingentity.getBoundingBox())) {
					DemonEye.this.doHurtTarget(livingentity);
					DemonEye.this.attackPhase = AttackPhase.CIRCLE;
					if (!DemonEye.this.isSilent()) {
						DemonEye.this.level().levelEvent(1039, DemonEye.this.blockPosition(), 0);
					}
				} else if (DemonEye.this.horizontalCollision || DemonEye.this.hurtTime > 0) {
					DemonEye.this.attackPhase = AttackPhase.CIRCLE;
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
			return DemonEye.this.owner != null && DemonEye.this.owner.getTarget() != null && this.canAttack(DemonEye.this.owner.getTarget(), this.copyOwnerTargeting);
		}

		public void start() {
			DemonEye.this.setTarget(DemonEye.this.owner.getTarget());
			super.start();
		}
	}
	public class HurtByTargetGoal extends TargetGoal {
		private static final TargetingConditions HURT_BY_TARGETING = TargetingConditions.forCombat().ignoreLineOfSight().ignoreInvisibilityTesting();

		private int timestamp;
		private final Class<?>[] toIgnoreDamage;

		public HurtByTargetGoal(DemonEye pMob, Class<?>... pToIgnoreDamage) {
			super(pMob, true);
			this.toIgnoreDamage = pToIgnoreDamage;
			this.setFlags(EnumSet.of(Flag.TARGET));
		}

		@Override
		public boolean canUse() {
			int i = this.mob.getLastHurtByMobTimestamp();
			LivingEntity livingentity = this.mob.getLastHurtByMob();
			if (i != this.timestamp && livingentity != null) {
				if (livingentity.getType() == EntityType.PLAYER && this.mob.level().getGameRules().getBoolean(GameRules.RULE_UNIVERSAL_ANGER)) {
					return false;
				} else {
					for (Class<?> oclass : this.toIgnoreDamage) {
						if (oclass.isAssignableFrom(livingentity.getClass())) {
							return false;
						}
					}

					return this.canAttack(livingentity, HURT_BY_TARGETING);
				}
			} else {
				return false;
			}
		}


		@Override
		public void start() {
			this.mob.setTarget(this.mob.getLastHurtByMob());
			this.targetMob = this.mob.getTarget();
			this.timestamp = this.mob.getLastHurtByMobTimestamp();
			this.unseenMemoryTicks = 300;

			super.start();
		}

	}
}
