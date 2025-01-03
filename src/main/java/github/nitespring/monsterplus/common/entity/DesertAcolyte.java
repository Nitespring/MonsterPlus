package github.nitespring.monsterplus.common.entity;

import github.nitespring.monsterplus.common.entity.projectiles.Flame;
import github.nitespring.monsterplus.config.CommonConfig;
import github.nitespring.monsterplus.core.init.EntityInit;
import github.nitespring.monsterplus.core.init.SoundInit;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
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
import net.minecraft.world.entity.projectile.SmallFireball;
import net.minecraft.world.entity.raid.Raider;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.phys.Vec3;

import java.util.Random;

public class DesertAcolyte extends CustomSpellcaster {

	public DesertAcolyte(EntityType<? extends SpellcasterIllager> p_33724_, Level p_33725_) {
		super(p_33724_, p_33725_);
		this.xpReward = 10;
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
	      this.goalSelector.addGoal(1, new DesertAcolyte.CastingSpellGoal());
	      this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, Mob.class, 8.0F, 0.6D, 1.0D, (p_28879_) -> {
	          return p_28879_ == this.getTarget();
	      }));
	      this.goalSelector.addGoal(5, new DesertAcolyte.FlameSpellGoal());
	      this.goalSelector.addGoal(5, new DesertAcolyte.FireballSpellGoal());

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
				.add(Attributes.MAX_HEALTH, 20.0D)
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
	  } else if (p_32665_ instanceof DesertAcolyte ||p_32665_ instanceof DesertAcolyte) {
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

	public static boolean checkDesertAcolyteSpawnRules(EntityType<? extends Monster> p_219014_, ServerLevelAccessor p_219015_, MobSpawnType p_219016_, BlockPos blockPos, RandomSource p_219018_) {
		return p_219015_.getDifficulty() != Difficulty.PEACEFUL && isDarkEnoughToSpawn(p_219015_, blockPos, p_219018_) && checkMobSpawnRules(p_219014_, p_219015_, p_219016_, blockPos, p_219018_)
				&& CommonConfig.spawn_desert_acolyte.get();
	}
	 
	 class CastingSpellGoal extends SpellcasterCastingSpellGoal {
	      public void tick() {
	         if (DesertAcolyte.this.getTarget() != null) {
	        	 DesertAcolyte.this.getLookControl().setLookAt(DesertAcolyte.this.getTarget(), (float) DesertAcolyte.this.getMaxHeadYRot(), (float) DesertAcolyte.this.getMaxHeadXRot());
	         } 

	      }
	   }

	 
	 
	 class FireballSpellGoal extends SpellcasterUseSpellGoal {
	      protected int getCastingTime() {
	         return 50;
	      }

	      protected int getCastingInterval() {
	         return 200;
	      }

		@Override
		protected void performSpellCasting() {
			
			
			
			Vec3 pos = DesertAcolyte.this.position();
			
			Vec3 posO = new Vec3(pos.x, pos.y + 2.5, pos.z);
			
			Vec3 pos1 = DesertAcolyte.this.getTarget().position();
			
			Vec3 posT = new Vec3(pos1.x, DesertAcolyte.this.getTarget().getY(0.5), pos1.z);
			
			double d0 = Math.sqrt((posT.x-posO.x)*(posT.x-posO.x) + (posT.y-posO.y)*(posT.y-posO.y) + (posT.z-posO.z)*(posT.z-posO.z));
			
			Vec3 aim = new Vec3((posT.x-posO.x)/d0,  (posT.y-posO.y)/d0,  (posT.z-posO.z)/d0);
			
			
			
			launchFireball(posO, aim, 4);
			
			
			
		}
		
		 private void launchFireball(Vec3 pos, Vec3 aim, float damage) {

			 SmallFireball fireball = new SmallFireball(EntityType.SMALL_FIREBALL,level());
			 fireball.setPos(pos.x,pos.y,pos.z);
			 fireball.setDeltaMovement(aim.x*0.5f,aim.y*0.5f,aim.z*0.5f);
			 fireball.setOwner(DesertAcolyte.this);
			 DesertAcolyte.this.level().addFreshEntity(fireball);
			 
			 DesertAcolyte.this.playSound(SoundEvents.FIRE_AMBIENT, 0.5f, 0.75f);
		}
		
		

		@Override
		protected SoundEvent getSpellPrepareSound() {
			
			return SoundEvents.FIRECHARGE_USE;
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
			return 160;
		}

		@Override
		protected int getCastWarmupTime() {
			return 20;
		}

		@Override
		public boolean canUse() {
			LivingEntity livingentity = DesertAcolyte.this.getTarget();
			if (livingentity == null || !livingentity.isAlive()||livingentity.distanceTo(DesertAcolyte.this)>=10) {
				return false;
			} else {
				return DesertAcolyte.this.isCastingSpell() ? false : DesertAcolyte.this.tickCount >= this.nextAttackTickCount;
			}
		}

		@Override
		public boolean canContinueToUse() {
			LivingEntity livingentity = DesertAcolyte.this.getTarget();
			return livingentity != null && livingentity.isAlive() && this.attackWarmupDelay > 0;
		}

		@Override
		public void start() {
			this.attackWarmupDelay = this.adjustedTickDelay(this.getTotalCastingTime());
			DesertAcolyte.this.spellCastingTickCount = this.getTotalCastingTime()-2;
			this.nextAttackTickCount = DesertAcolyte.this.tickCount + this.getCastingInterval();
			SoundEvent soundevent = this.getSpellPrepareSound();
			if (soundevent != null) {
				DesertAcolyte.this.playSound(soundevent, 1.0F, 1.0F);
			}

			DesertAcolyte.this.setIsCastingSpell(this.getSpell());
		}


		@Override
		public void tick() {
			this.attackWarmupDelay--;
			if (this.attackWarmupDelay == getCastingTime()) {
				this.performSpellCasting();
				DesertAcolyte.this.playSound(DesertAcolyte.this.getCastingSoundEvent(), 1.0F, 1.0F);
			}
			if (this.attackWarmupDelay <= getCastingTime()) {
				if (attackWarmupDelay%3==0){
					this.performSpellCasting();
				}
			}
		}

		@Override
		protected void performSpellCasting() {

			Vec3 pos = DesertAcolyte.this.position();

			Vec3 posO = new Vec3(pos.x, pos.y + 2.5, pos.z);

			Vec3 pos1 = DesertAcolyte.this.getTarget().position();

			Vec3 posT = new Vec3(pos1.x, DesertAcolyte.this.getTarget().getY(0.5)+0.5f, pos1.z);

			double d0 = Math.sqrt((posT.x-posO.x)*(posT.x-posO.x) + (posT.y-posO.y)*(posT.y-posO.y) + (posT.z-posO.z)*(posT.z-posO.z));
			for(int i = 0; i<3;i++) {

				Vec3 aim = new Vec3((posT.x - posO.x) / d0, (posT.y - posO.y) / d0, (posT.z - posO.z) / d0);
				throwFlame(posO, aim.add(randomFloat(0.1f),randomFloat(0.1f),randomFloat(0.1f)), 1);
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
			fireball.setFlyingTime(15);
			fireball.setOwner(DesertAcolyte.this);
			DesertAcolyte.this.level().addFreshEntity(fireball);

			DesertAcolyte.this.playSound(SoundEvents.FIRE_AMBIENT, 0.5f, 0.75f);
		}



		@Override
		protected SoundEvent getSpellPrepareSound() {

			return SoundEvents.FIRECHARGE_USE;
		}

		@Override
		protected IllagerSpell getSpell() {

			return IllagerSpell.WOLOLO;
		}
	}
}
