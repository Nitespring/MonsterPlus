package github.nitespring.monsterplus.common.item;

import java.util.Random;

import github.nitespring.monsterplus.common.entity.projectiles.SpikeCountdown;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.VoxelShape;

public class Darkstar extends SwordItem implements ILeftClickSpecialActionItem{

	public Darkstar(Tier tier, Properties p) {
		super(tier, p.attributes(SwordItem.createAttributes(Tiers.DIAMOND, 13, -2.8F)).stacksTo(1).rarity(Rarity.EPIC));
		
	}


	@Override
	public boolean isFoil(ItemStack p_41453_) {
		
		return true;
	}

	@Override
	public void doLeftClickAction(Player playerIn, ItemStack item) {
		Vec3 posTarget = playerIn.position().add(playerIn.getLookAngle().scale(15.0));
		 double d0 = Math.min(posTarget.y, playerIn.getY());
         double d1 = Math.max(posTarget.y, playerIn.getY()) + 1.0D;
         float f = (float)Mth.atan2(posTarget.z - playerIn.getZ(), posTarget.x - playerIn.getX());
		for(int l = 0; l < 8; ++l) {
            double d2 = 1.25D * (double)(l + 1);
            int j = 1 * l;
            this.createSpellEntity(playerIn, playerIn.getX() + (double)Mth.cos(f) * d2  + (new Random().nextFloat() - 0.5), playerIn.getZ() + (double)Mth.sin(f) * d2 + (new Random().nextFloat() - 0.5), d0, d1, f, j);
         }
	
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
        	
        	 playerIn.level().addFreshEntity(new SpikeCountdown(playerIn.level(), 4.0f, p_32673_, (double)blockpos.getY() + d0, p_32674_, p_32677_, p_32678_, playerIn, 0));
        	 
        	 
         }

      }
	
	
	
}
