package github.nitespring.monsterplus.common.item.scrolls;

import github.nitespring.monsterplus.common.entity.DesertSorceress;
import github.nitespring.monsterplus.common.entity.projectiles.Flame;
import github.nitespring.monsterplus.core.init.EntityInit;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.common.ItemAbility;

import java.util.Random;

public class FlameSpellScroll extends EnchantedScroll{
    public FlameSpellScroll(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        if(!player.getCooldowns().isOnCooldown(this)) {
            doSpellB(player, player.getItemInHand(usedHand));
            return InteractionResultHolder.success(player.getItemInHand(usedHand));
        }
        return InteractionResultHolder.fail(player.getItemInHand(usedHand));
    }


    @Override
    public void doSpellA(Player playerIn, ItemStack item) {
        Vec3 pos = playerIn.position();
        Vec3 aim0 = playerIn.getLookAngle().normalize();
        for(int i = 0; i<9;i++) {
            float a = (float) (Math.PI / 6);
            Vec3 aim = aim0.add(randomFloat(a),randomFloat(a),randomFloat(a));
            Vec3 posO = new Vec3(pos.x + 0.5 * aim0.x, pos.y + 1.0 + 0.5 * aim0.y, pos.z + 0.5 * aim0.z);
            throwFlame(10,posO, aim.scale(0.1f), 5,playerIn);
        }
        playerIn.getCooldowns().addCooldown(this, 16);
        item.consume(1,playerIn);
        playerIn.level().playSound(null, pos.x, pos.y, pos.z, SoundEvents.FIRECHARGE_USE, SoundSource.PLAYERS, 0.4f, 1.25f);
    }

    @Override
    public void doSpellB(Player playerIn, ItemStack item) {
        Vec3 pos = playerIn.position();
        Vec3 aim0 = playerIn.getLookAngle().normalize();
        for(int i = 0; i<3;i++) {
            float a = (float) (Math.PI / 18);
            Vec3 aim = aim0.add(randomFloat(a),randomFloat(a),randomFloat(a));
            Vec3 posO = new Vec3(pos.x + 0.5 * aim0.x, pos.y + 1.0 + 0.5 * aim0.y, pos.z + 0.5 * aim0.z);
            throwFlame(16,posO, aim.scale(0.25f), 2,playerIn);
        }
        playerIn.getCooldowns().addCooldown(item.getItem(), 4);
        item.consume(1, playerIn);
        playerIn.level().playSound(null, pos.x, pos.y, pos.z, SoundEvents.FIRE_EXTINGUISH, SoundSource.PLAYERS, 0.2f, 1.5f);
    }

    float randomFloat(float peak){
        Random r = new Random();
        float f = r.nextFloat(-1,1)*peak;
        return f;
    }

    private void throwFlame(int flyingTime,Vec3 pos, Vec3 aim, float damage, Player playerIn) {

        Flame fireball = new Flame(EntityInit.FLAME.get(),playerIn.level());
        fireball.setPos(pos.x,pos.y,pos.z);
        fireball.setDeltaMovement(aim.x,aim.y,aim.z);
        fireball.setAttackDamage(damage);
        fireball.setFlyingTime(flyingTime);
        fireball.setOwner(playerIn);
        playerIn.level().addFreshEntity(fireball);

        playerIn.level().playSound(null, pos.x, pos.y, pos.z, SoundEvents.FIRE_AMBIENT, SoundSource.PLAYERS, 0.5f, 0.75f);
    }

}
