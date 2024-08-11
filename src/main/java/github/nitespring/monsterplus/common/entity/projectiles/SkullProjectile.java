package github.nitespring.monsterplus.common.entity.projectiles;

import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.entity.projectile.ProjectileDeflection;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

public class SkullProjectile extends AbstractHurtingProjectile {

    @Nullable
    LivingEntity target;
    private float attackDamage;
    public SkullProjectile(EntityType<? extends AbstractHurtingProjectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        attackDamage=4.0f;
    }

    @Nullable
    public LivingEntity getTarget() {
        return this.target;
    }

    public float getAttackDamage() {
        return attackDamage;
    }

    public void setAttackDamage(float attackDamage) {
        this.attackDamage = attackDamage;
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
        return super.isPickable();
    }

    @Override
    public boolean canBeCollidedWith() {
        return false;
    }

    @Override
    public boolean canBeHitByProjectile() {
        return false;
    }

    @Override
    protected void onHitBlock(BlockHitResult pResult) {
        playSound(SoundEvents.GENERIC_EXPLODE.value(), 0.2f,1.2f);
        this.discard();
    }

    @Override
    protected boolean canHitEntity(Entity e) {
        if(getOwner()!=null){
            if(e==getOwner()||getOwner().isAlliedTo(e)){
                return false;
            }
        }
        return super.canHitEntity(e);
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        pResult.getEntity().hurt(level().damageSources().indirectMagic(this.getOwner(),this), this.getAttackDamage());
        playSound(SoundEvents.GENERIC_EXPLODE.value(), 0.2f,1.2f);
        this.discard();
    }

    @Override
    public boolean hurt(DamageSource pSource, float pAmount) {
        return false;
    }

    @Override
    protected void onDeflection(@Nullable Entity pEntity, boolean pDeflectedByPlayer) {
        super.onDeflection(pEntity, pDeflectedByPlayer);

    }

    @Override
    public boolean deflect(ProjectileDeflection pDeflection, @Nullable Entity pEntity, @Nullable Entity pOwner, boolean pDeflectedByPlayer) {
        if (this.getOwner() != null && this.getOwner() instanceof LivingEntity owner) {
            this.setTarget(owner);
        }else{
            this.setTarget(null);
        }
        return super.deflect(pDeflection, pEntity, pOwner, pDeflectedByPlayer);
    }
}
