package github.nitespring.monsterplus.common.entity.projectiles;

import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.entity.projectile.Fireball;
import net.minecraft.world.entity.projectile.LargeFireball;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

public class SkullProjectile extends AbstractHurtingProjectile {

    @Nullable
    LivingEntity target;
    public SkullProjectile(EntityType<? extends AbstractHurtingProjectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Nullable
    public LivingEntity getTarget() {
        return this.target;
    }


    public void setTarget(LivingEntity mob) {
        this.target = mob;
    }

    @Nullable
    @Override
    protected ParticleOptions getTrailParticle() {
        return ParticleTypes.SOUL_FIRE_FLAME;
    }

    @Override
    public boolean isOnFire() {
        return false;
    }

    @Override
    public void tick() {
        super.tick();
        if(tickCount>=120){
            discard();
        }
        Vec3 vec3 = this.getDeltaMovement();
            double d0 = vec3.horizontalDistance();
            this.setYRot((float)(Mth.atan2(vec3.x, vec3.z) * (double)(180F / (float)Math.PI)));
            this.setXRot((float)(Mth.atan2(vec3.y, d0) * (double)(180F / (float)Math.PI)));
            this.yRotO = this.getYRot();
            this.xRotO = this.getXRot();
        if(this.target!=null){
            Vec3 mov = this.getDeltaMovement();
            Vec3 pos = this.position();
            Vec3 pos1 = this.target.position().add(0,target.getBbHeight()*0.5+0.5f,0);
            Vec3 aim = new Vec3(pos1.x-pos.x,pos1.y-pos.y,pos1.z-pos.z).normalize().scale(mov.length());
            setDeltaMovement(this.getDeltaMovement().lerp(aim,0.075f));
        }else{
            setDeltaMovement(this.getDeltaMovement().add(0,-0.005f,0));
        }
        if(tickCount%6==0) {
            playSound(SoundEvents.CAMPFIRE_CRACKLE, 0.2f, 0.4f);
        }
        this.level().addParticle(ParticleTypes.SOUL_FIRE_FLAME, this.getRandomX(0.6D), this.getRandomY(), this.getRandomZ(0.6D), 0.0, 0.0, 0.0);
    }

    @Override
    public boolean isAttackable() {
        return true;
    }

    @Override
    public boolean isPickable() {
        return true;
    }

    @Override
    public boolean canBeCollidedWith() {
        return true;
    }

    @Override
    public boolean canBeHitByProjectile() {
        return super.canBeHitByProjectile();
    }

    @Override
    protected void onHitBlock(BlockHitResult pResult) {
        this.discard();
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        pResult.getEntity().hurt(level().damageSources().indirectMagic(this.getOwner(),this), 4.0f);
        this.discard();
    }

    @Override
    public boolean hurt(DamageSource pSource, float pAmount) {
        return false;
    }
}
