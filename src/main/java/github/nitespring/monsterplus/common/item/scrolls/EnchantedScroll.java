package github.nitespring.monsterplus.common.item.scrolls;

import github.nitespring.monsterplus.common.item.ILeftClickSpecialActionItem;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.ItemAbility;

public abstract class EnchantedScroll extends Item implements ILeftClickSpecialActionItem {
    public EnchantedScroll(Properties properties) {
        super(properties);
    }
    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.BOW;
    }

    @Override
    public int getUseDuration(ItemStack stack, LivingEntity entity) {
        return 10000;
    }

    @Override
    public void doLeftClickAction(Player playerIn, ItemStack item) {
        if(!playerIn.getCooldowns().isOnCooldown(this)) {
            doSpellA(playerIn, item);
        }
    }

    public abstract void doSpellA(Player playerIn, ItemStack item);
    public abstract void doSpellB(Player playerIn, ItemStack item);

    @Override
    public boolean onEntitySwing(ItemStack stack, LivingEntity entity, InteractionHand hand) {
        return true;
    }
    @Override
    public boolean canPerformAction(ItemStack stack, ItemAbility itemAbility) {
        return super.canPerformAction(stack, itemAbility);
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
