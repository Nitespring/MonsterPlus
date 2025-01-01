package github.nitespring.monsterplus.common.item.scrolls;

import github.nitespring.monsterplus.common.entity.projectiles.Flame;
import github.nitespring.monsterplus.core.init.EntityInit;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.SmallFireball;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.Random;

public class SmallFireballSpellScroll extends EnchantedScroll{

    public SmallFireballSpellScroll(Properties properties) {
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

        float a = (float) (Math.PI / 32);
        Vec3 aim = aim0.add(randomFloat(a),randomFloat(a),randomFloat(a));
        Vec3 posO = new Vec3(pos.x + 0.5 * aim0.x, pos.y + 1.4 + 0.5 * aim0.y, pos.z + 0.5 * aim0.z);
        throwFireball(10,posO, aim.scale(0.1f), 5,playerIn);

        playerIn.getCooldowns().addCooldown(this, 16);
        item.consume(1,playerIn);
    }

    @Override
    public void doSpellB(Player playerIn, ItemStack item) {
        Vec3 pos = playerIn.position();
        Vec3 aim0 = playerIn.getLookAngle().normalize();
        for(int i = 0; i<2;i++) {
            float a = (float) (Math.PI / 6);
            Vec3 aim = aim0.add(randomFloat(a),randomFloat(a),randomFloat(a));
            Vec3 posO = new Vec3(pos.x + 0.5 * aim0.x, pos.y + 2.5 + 0.5 * aim0.y, pos.z + 0.5 * aim0.z);
            throwFireball(16,posO, aim.add(0,-0.25f,0).normalize().scale(0.25f), 2,playerIn);
        }
        playerIn.getCooldowns().addCooldown(item.getItem(), 3);
        item.consume(1, playerIn);
    }

    float randomFloat(float peak){
        Random r = new Random();
        float f = r.nextFloat(-1,1)*peak;
        return f;
    }

    private void throwFireball(int flyingTime, Vec3 pos, Vec3 aim, float damage, Player playerIn) {

        SmallFireball fireball = new SmallFireball(EntityType.SMALL_FIREBALL,playerIn.level());
        fireball.setPos(pos.x,pos.y,pos.z);
        fireball.setDeltaMovement(aim.x,aim.y,aim.z);
        fireball.setOwner(playerIn);
        playerIn.level().addFreshEntity(fireball);

        playerIn.playSound(SoundEvents.FIRE_AMBIENT, 0.5f, 0.75f);
    }

}
