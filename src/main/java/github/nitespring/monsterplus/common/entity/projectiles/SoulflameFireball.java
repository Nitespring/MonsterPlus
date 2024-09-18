package github.nitespring.monsterplus.common.entity.projectiles;

import github.nitespring.monsterplus.core.init.ItemInit;
import github.nitespring.monsterplus.core.init.ParticleInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class SoulflameFireball extends AbstractHurtingProjectile implements ItemSupplier{

    private float damage = 5.0f;
    public SoulflameFireball(EntityType<? extends AbstractHurtingProjectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public SoulflameFireball(EntityType<? extends AbstractHurtingProjectile> pEntityType, double pX, double pY, double pZ, Level pLevel) {
        super(pEntityType, pX, pY, pZ, pLevel);
    }

    public SoulflameFireball(EntityType<? extends AbstractHurtingProjectile> pEntityType, double pX, double pY, double pZ, Vec3 pMovement, Level pLevel) {
        super(pEntityType, pX, pY, pZ, pMovement, pLevel);
    }

    public SoulflameFireball(EntityType<? extends AbstractHurtingProjectile> pEntityType, LivingEntity pOwner, Vec3 pMovement, Level pLevel) {
        super(pEntityType, pOwner, pMovement, pLevel);
    }

    @Override
    public ItemStack getItem() {
        return ItemInit.SOULFLAME_POWDER.get().getDefaultInstance();
    }

    @Override
    protected void onHitBlock(BlockHitResult pResult) {
        BlockPos pos = pResult.getBlockPos();
        Level level = this.level();
        BlockState block = level.getBlockState(pos);
        if(!block.is(BlockTags.LOGS)&&!block.is(BlockTags.LEAVES)){
            super.onHitBlock(pResult);
            this.discard();
        }

    }
    @Override
    public void tick() {
        super.tick();
        if(tickCount%24==0) {
            this.playSound(SoundEvents.FIRE_AMBIENT);
        }
        Vec3 mov = this.getDeltaMovement();
        Random rng = new Random();
        double ox = mov.x;
        double oy = mov.y;
        double oz = mov.z;
        if(!this.isInWater()) {
            for (int i = 0; i < 2; i++) {
                this.level().addAlwaysVisibleParticle(ParticleTypes.SOUL_FIRE_FLAME,
                        this.getX() - ox * (double) i / 4.0 + 0.5 * (rng.nextFloat() - 0.5),
                        this.getY() + 0.1 - oy * (double) i / 4.0 + 0.5 * (rng.nextFloat() - 0.5),
                        this.getZ() - oz * (double) i / 4.0 + 0.5 * (rng.nextFloat() - 0.5),
                        -0.05 * ox + 0.1 * (rng.nextFloat() - 0.5), -0.05 * oy + 0.1 * (rng.nextFloat() - 0.5), -0.05 * oz + 0.1 * (rng.nextFloat() - 0.5));
            }
        }else{
            this.discard();
            this.playSound(SoundEvents.FIRE_EXTINGUISH);
        }
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        super.onHitEntity(pResult);
        Entity target = pResult.getEntity();
        if(this.getOwner() instanceof LivingEntity owner){
            if(!owner.isAlliedTo(target)){
                target.hurt(level().damageSources().indirectMagic(getOwner(),this), getDamage());
                target.igniteForSeconds(6);
                this.discard();
            }

        }else{
            target.hurt(level().damageSources().indirectMagic(this,null), getDamage());
            target.igniteForSeconds(6);
            this.discard();
        }


    }

    public void setDamage(float f){this.damage= f;}
    public float getDamage(){return this.damage;}


    @Nullable
    @Override
    protected ParticleOptions getTrailParticle() {
        return null;
    }

    @Override
    public boolean displayFireAnimation() {
        return false;
    }


}
