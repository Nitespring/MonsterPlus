package github.nitespring.monsterplus.common.entity.ancienthero;

import github.nitespring.monsterplus.common.entity.projectiles.SkullProjectile;
import github.nitespring.monsterplus.core.init.EntityInit;
import github.nitespring.monsterplus.core.init.ItemInit;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

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
					.add(Attributes.FOLLOW_RANGE, 30)
					.add(Attributes.STEP_HEIGHT, 2.0D)
					.add(Attributes.FALL_DAMAGE_MULTIPLIER, 0.1D)
					.add(Attributes.GRAVITY, 0.4D)
					.add(Attributes.MOVEMENT_EFFICIENCY, 1.0D)
					.add(Attributes.WATER_MOVEMENT_EFFICIENCY, 1.0D);
	
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
						this.level().addFreshEntity(skull);
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
		protected void registerGoals() {
			super.registerGoals();
		     
	}


	@Override
	protected void populateDefaultEquipmentSlots(RandomSource p_218949_, DifficultyInstance p_218950_) {
		
		 this.setItemSlot(EquipmentSlot.MAINHAND, ItemInit.ANCIENT_BROKEN_SWORD.get().getDefaultInstance());
	}
	public void aiStep() {
		super.aiStep();
		//if(this.getTarget()!=null){
			if(tickCount%24==0) {
				shootSkull();
				playSound(SoundEvents.WITHER_SHOOT, 0.2f, 0.4f);
			}
		//}

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


}
