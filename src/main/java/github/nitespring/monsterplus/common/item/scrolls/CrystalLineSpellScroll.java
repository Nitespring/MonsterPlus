package github.nitespring.monsterplus.common.item.scrolls;

import github.nitespring.monsterplus.common.entity.projectiles.SpikeCountdown;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.LargeFireball;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.Random;

public class CrystalLineSpellScroll extends EnchantedScroll{

    public CrystalLineSpellScroll(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        player.startUsingItem(usedHand);
        return InteractionResultHolder.pass(player.getItemInHand(usedHand));
    }

    @Override
    public void onStopUsing(ItemStack stack, LivingEntity entity, int count) {
        Player player = (Player) entity;
        if(!player.getCooldowns().isOnCooldown(this)) {
            if(count <= getUseDuration(stack, entity)-54){
                doSpellB(player, stack , 13);
            } else{
                doSpellB(player, stack, 4+(getUseDuration(stack, entity)-count)/6);
            }
        }
    }

    @Override
    public void doSpellA(Player playerIn, ItemStack item) {
        Vec3 pos = playerIn.position();
        Vec3 aim0 = playerIn.getLookAngle().normalize();

        Vec3 posTarget = playerIn.position().add(playerIn.getLookAngle().scale(11.0));
        double d0 = Math.min(posTarget.y, playerIn.getY());
        double d1 = Math.max(posTarget.y, playerIn.getY()) + 1.0D;
        float f = (float)Mth.atan2(posTarget.z - playerIn.getZ(), posTarget.x - playerIn.getX());
        for(int l = 0; l < 5; ++l) {
            double d2 = 1.25D * (double)(l + 1);
            int j = 3 * l;
            this.createSpellEntity(playerIn, playerIn.getX() + (double)Mth.cos(f) * d2  + (new Random().nextFloat() - 0.5), playerIn.getZ() + (double)Mth.sin(f) * d2 + (new Random().nextFloat() - 0.5), d0, d1, f, j);
        }


        playerIn.getCooldowns().addCooldown(this, 16);
        item.consume(1,playerIn);
        playerIn.level().playSound(null, pos.x, pos.y, pos.z, SoundEvents.FIRECHARGE_USE, SoundSource.PLAYERS, 0.25f, 0.25f);
    }

    @Override
    public void doSpellB(Player playerIn, ItemStack item) {
        doSpellB(playerIn, item, 5);
    }


    public void doSpellB(Player playerIn, ItemStack item, int i) {
        Vec3 pos = playerIn.position();
        Vec3 aim0 = playerIn.getLookAngle().normalize();

        Vec3 posTarget = playerIn.position().add(playerIn.getLookAngle().scale(3+i*2));
        double d0 = Math.min(posTarget.y, playerIn.getY());
        double d1 = Math.max(posTarget.y, playerIn.getY()) + 1.0D;
        float f = (float)Mth.atan2(posTarget.z - playerIn.getZ(), posTarget.x - playerIn.getX());
        for(int l = 0; l < i; ++l) {
            double d2 = 1.25D * (double)(l + 1);
            int j = 1 * l;
            this.createSpellEntity(playerIn, playerIn.getX() + (double)Mth.cos(f) * d2  + (new Random().nextFloat() - 0.5), playerIn.getZ() + (double)Mth.sin(f) * d2 + (new Random().nextFloat() - 0.5), d0, d1, f, j);
        }


        playerIn.getCooldowns().addCooldown(this, 24);
        item.consume(1,playerIn);
        playerIn.level().playSound(null, pos.x, pos.y, pos.z, SoundEvents.FIRECHARGE_USE, SoundSource.PLAYERS, 0.25f, 0.25f);
    }

    float randomFloat(float peak){
        Random r = new Random();
        float f = r.nextFloat(-1,1)*peak;
        return f;
    }
    private void createSpellEntity(Player playerIn, double p_32673_, double p_32674_, double p_32675_, double p_32676_, float p_32677_, int p_32678_) {
        BlockPos blockpos = new BlockPos((int)p_32673_, (int)p_32676_, (int)p_32674_);
        boolean flag = false;
        double d0 = 0.0D;

        do {
            BlockPos blockpos1 = blockpos.below();
            BlockState blockstate = playerIn.level().getBlockState(blockpos1);
            if (blockstate.isFaceSturdy(playerIn.level(), blockpos1, Direction.UP)) {
                if (!playerIn.level().isEmptyBlock(blockpos)) {
                    BlockState blockstate1 = playerIn.level().getBlockState(blockpos);
                    VoxelShape voxelshape = blockstate1.getCollisionShape(playerIn.level(), blockpos);
                    if (!voxelshape.isEmpty()) {
                        d0 = voxelshape.max(Direction.Axis.Y);
                    }
                }

                flag = true;
                break;
            }

            blockpos = blockpos.below();
        } while(blockpos.getY() >= Mth.floor(p_32675_) - 1);

        if (flag) {

            playerIn.level().addFreshEntity(new SpikeCountdown(playerIn.level(), 7.0f, p_32673_, (double)blockpos.getY() + d0, p_32674_, p_32677_, p_32678_, playerIn, 0));


        }

    }

}
