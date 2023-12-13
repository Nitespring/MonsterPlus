package github.nitespring.monsterplus.common.entity;

import github.nitespring.monsterplus.config.CommonConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;

public class SwampZombie extends Zombie{

	public SwampZombie(EntityType<? extends Zombie> p_34271_, Level p_34272_) {
		super(p_34271_, p_34272_);
		
	}

	//Drowned
	
	/*@Override
	public boolean canBreatheUnderwater() {
		
		return true;
	}
	*/
	@Override
	protected boolean convertsInWater() {
		
		return false;
	}
	
	
	@Override
	protected void populateDefaultEquipmentSlots(RandomSource p_219154_, DifficultyInstance p_219155_) {
		
		super.populateDefaultEquipmentSlots(p_219154_, p_219155_);
		
		
		int r = this.getRandom().nextInt(10)+1;
		
		switch(r) {
		
		case 1:
			
			 this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.WOODEN_SWORD));
			 
			 break;
			
		case 2:
		
	         this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.STONE_SWORD));
	         
	         break;
	         
		case 3:
			
	         this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.STONE_SHOVEL)); 
	         
	         break;
	         
		case 4:
			
	         this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.WOODEN_AXE));
	         
	         break;
	         
		case 5:
			
	         this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.WOODEN_SHOVEL));
	         
	         break;
	         
	    default:
	    	
	    	this.setItemSlot(EquipmentSlot.MAINHAND, ItemStack.EMPTY);
	    
	    	break;
	      
		}
	   }
	
	
	@Override
	public boolean doHurtTarget(Entity p_34169_) {
	      if (!super.doHurtTarget(p_34169_)) {
	         return false;
	      } else {
	         if (p_34169_ instanceof LivingEntity) {
	            ((LivingEntity)p_34169_).addEffect(new MobEffectInstance(MobEffects.POISON, 500), this);
	         }

	         return true;
	      }
	   }
	
	 public static  AttributeSupplier.Builder setCustomAttributes(){
			return Monster.createMonsterAttributes()
					.add(Attributes.MAX_HEALTH, 24.0D)
					.add(Attributes.MOVEMENT_SPEED, 0.18D)
					.add(Attributes.ATTACK_DAMAGE, 2)
					.add(Attributes.ATTACK_SPEED, 1.0D)
					.add(Attributes.ATTACK_KNOCKBACK, 1.2D)
					.add(Attributes.KNOCKBACK_RESISTANCE, 0.8D)
					.add(Attributes.FOLLOW_RANGE, 30);
	
		  }
	 
	public static boolean checkSwampZombieSpawnRules(EntityType<SwampZombie> p_218985_, ServerLevelAccessor p_218986_, MobSpawnType p_218987_, BlockPos p_218988_, RandomSource p_218989_) {
		     return checkMonsterSpawnRules(p_218985_, p_218986_, p_218987_, p_218988_, p_218989_) 
		    	&& CommonConfig.spawn_swamp_zombie.get();
		  }
	 
	   //Drowned
	 @Override
	 protected SoundEvent getAmbientSound() {
	      return this.isInWater() ? SoundEvents.DROWNED_AMBIENT_WATER : SoundEvents.DROWNED_AMBIENT;
	   }
	 @Override
	   protected SoundEvent getHurtSound(DamageSource p_32386_) {
	      return this.isInWater() ? SoundEvents.DROWNED_HURT_WATER : SoundEvents.DROWNED_HURT;
	   }
	 @Override
	   protected SoundEvent getDeathSound() {
	      return this.isInWater() ? SoundEvents.DROWNED_DEATH_WATER : SoundEvents.DROWNED_DEATH;
	   }
	 @Override
	   protected SoundEvent getStepSound() {
	      return SoundEvents.DROWNED_STEP;
	   }
	 @Override
	   protected SoundEvent getSwimSound() {
	      return SoundEvents.DROWNED_SWIM;
	   }
	 
	 
	
	
}
