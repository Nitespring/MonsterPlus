package github.nitespring.monsterplus.common.entity;

import github.nitespring.monsterplus.common.entity.projectiles.CurseflameFireball;
import github.nitespring.monsterplus.common.entity.projectiles.SoulflameFireball;
import github.nitespring.monsterplus.config.CommonConfig;
import github.nitespring.monsterplus.core.init.EntityInit;
import github.nitespring.monsterplus.core.init.ParticleInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;

public class Soul extends Wisp{
    public Soul(EntityType<? extends Monster> p_33002_, Level p_33003_) {
        super(p_33002_, p_33003_);
    }
    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new Soul.SoulAttackGoal(this));
        this.goalSelector.addGoal(3, new Wisp.SquidRandomMovementGoal(this));
        this.goalSelector.addGoal(2, new Wisp.SquidFleeGoal());
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setAlertOthers());
        //this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }
    @Override
    public void doParticles() {
        if(tickCount%3==0) {
            double x = this.getRandomX(0.6D);
            double y = this.getRandomY() - 0.2;
            double z = this.getRandomZ(0.6D);
            this.level().addParticle(ParticleInit.SMALL_SOULFLAME.get(), x, y, z, 0.01*(this.getRandom().nextFloat()-0.5), 0, 0.01*(this.getRandom().nextFloat()-0.5));
        }
    }
    public static  AttributeSupplier.Builder setCustomAttributes(){
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 16.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.25D)
                .add(Attributes.ATTACK_DAMAGE, 3)
                .add(Attributes.ATTACK_SPEED, 1.2D)
                .add(Attributes.ATTACK_KNOCKBACK, 0.2D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.0D)
                .add(Attributes.FOLLOW_RANGE, 30)
                .add(Attributes.FALL_DAMAGE_MULTIPLIER, 0);
    }
    public static boolean checkSoulSpawnRules(EntityType<Soul> p_218985_, ServerLevelAccessor p_218986_, MobSpawnType p_218987_, BlockPos p_218988_, RandomSource p_218989_) {
        return checkMobSpawnRules(p_218985_, p_218986_, p_218987_, p_218988_, p_218989_)
                //&& p_218986_.canSeeSky(p_218988_)
                && CommonConfig.spawn_wisp.get();
    }
    protected ParticleOptions getInkParticle() {
        return ParticleInit.SMALL_SOULFLAME.get();
    }

    static class SoulAttackGoal extends Goal {
        private final Wisp squid;
        private int attackStep;
        private int attackTime;
        private int lastSeen;

        public SoulAttackGoal(Wisp p_32247_) {
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

                                SoulflameFireball smallfireball = new SoulflameFireball(EntityInit.SOULFLAME_FIREBALL.get(), this.squid, new Vec3(this.squid.getRandom().triangle(d1, 2.297D * d4), d2, this.squid.getRandom().triangle(d3, 2.297D * d4)),this.squid.level());
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
