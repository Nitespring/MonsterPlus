package github.nitespring.monsterplus.common.item;

import github.nitespring.monsterplus.common.entity.projectiles.SkullProjectile;
import github.nitespring.monsterplus.common.entity.projectiles.SpikeCountdown;
import github.nitespring.monsterplus.core.enums.CustomItemTiers;
import github.nitespring.monsterplus.core.init.EntityInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.Random;

public class SkullStaff extends Item implements ILeftClickSpecialActionItem{

	public SkullStaff(Properties p) {
		super(p.attributes(SwordItem.createAttributes(CustomItemTiers.BONE, 1, -2.8F))
                .durability(128)
                .stacksTo(1)
                .rarity(Rarity.EPIC));
		
	}


	@Override
	public boolean isFoil(ItemStack i) {
		
		return super.isFoil(i);
	}

	@Override
	public void doLeftClickAction(Player playerIn, ItemStack item) {
        item.hurtAndBreak(1, playerIn, EquipmentSlot.MAINHAND);
        shootSkull(playerIn, item);
	}
	
	public void shootSkull(Player playerIn, ItemStack item){
        Level level = playerIn.level();
        Vec3 aim = playerIn.getLookAngle();
        Vec3 pos0 = playerIn.position().add(0.6*aim.x,1.6 + 0.6*aim.y,0.6*aim.z);
        SkullProjectile skull = new SkullProjectile(EntityInit.SPECTRAL_SKULL_PROJECTILE.get(), level);
        skull.setOwner(playerIn);
        skull.setPos(pos0);
        skull.setDeltaMovement(0.15f*aim.x,0.15f*aim.y,0.15f*aim.z);
        skull.setAttackDamage(6.0f);
        level.addFreshEntity(skull);
        level.playSound(null, BlockPos.containing(pos0), SoundEvents.EVOKER_CAST_SPELL, SoundSource.PLAYERS, 1.0f,1.0f);

    }

    @Override
    public boolean onEntitySwing(ItemStack stack, LivingEntity entity, InteractionHand hand) {
        return true;
    }

    @Override
    public boolean canAttackBlock(BlockState p_43291_, Level p_43292_, BlockPos p_43293_, Player p_43294_) {
        return false;
    }
    @Override
    public boolean onLeftClickEntity(ItemStack stack, Player player, Entity entity) {
        return true;
    }

}
