package github.nitespring.monsterplus.common.item.scrolls;

import github.nitespring.monsterplus.common.entity.projectiles.SpikeCountdown;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.Random;

public class CrystalCircleSpellScroll extends EnchantedScroll{

    public CrystalCircleSpellScroll(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        player.startUsingItem(usedHand);
        return InteractionResultHolder.pass(player.getItemInHand(usedHand));
    }


    @Override
    public void onUseTick(Level level, LivingEntity livingEntity, ItemStack stack, int i) {
        if(i<= getUseDuration(stack, livingEntity)-32){
            livingEntity.stopUsingItem();
        }
    }

    @Override
    public void onStopUsing(ItemStack stack, LivingEntity entity, int count) {
        Player player = (Player) entity;
        if(!player.getCooldowns().isOnCooldown(this)) {
            if(count <= getUseDuration(stack, entity)-24){
                doSpellB(player, stack , 3);
            } else if(count <= getUseDuration(stack, entity)-8){
                doSpellB(player, stack , 2);
            } else{
                doSpellB(player, stack, 1);
            }
        }
    }

    @Override
    public void doSpellA(Player playerIn, ItemStack item) {
        Vec3 pos = playerIn.position();
        Vec3 aim0 = playerIn.getLookAngle().normalize();


        float a = (float) (Math.PI/6);
        float r = 2.5f;
        for(int l = 0; l < 12; ++l) {
            Vec3 posTarget = playerIn.position().add(r*Math.cos(l*a),0,r*Math.sin(l*a));
            double d0 = Math.min(posTarget.y, playerIn.getY());
            double d1 = Math.max(posTarget.y, playerIn.getY()) + 1.0D;
            float f = (float)Mth.atan2(posTarget.z - playerIn.getZ(), posTarget.x - playerIn.getX());
            double d2 = 1.25D * (double)(l + 1);
            int j = 2;
            this.createSpellEntity(playerIn, posTarget.x() + 0.25*(new Random().nextFloat() - 0.5), posTarget.z() + 0.25*(new Random().nextFloat() - 0.5), d0, d1, f, j);
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

        for(int k = 0; k<i; k++) {
            float a = (float) (Math.PI / (6+2*k));
            float r = 2.5f + k * 2.5f;
            for (int l = 0; l < 12+4*k; ++l) {
                Vec3 posTarget = playerIn.position().add(r * Math.cos(l * a), 0, r * Math.sin(l * a));
                double d0 = Math.min(posTarget.y, playerIn.getY());
                double d1 = Math.max(posTarget.y, playerIn.getY()) + 1.0D;
                float f = (float) Mth.atan2(posTarget.z - playerIn.getZ(), posTarget.x - playerIn.getX());
                double d2 = 1.25D * (double) (l + 1);
                int j = 2 + 3 * k;
                this.createSpellEntity(playerIn, posTarget.x() + 0.25 * (new Random().nextFloat() - 0.5), posTarget.z() + 0.25 * (new Random().nextFloat() - 0.5), d0, d1, f, j);
            }
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
