package github.nitespring.monsterplus.common.entity.projectiles;

import github.nitespring.monsterplus.core.init.EntityInit;
import github.nitespring.monsterplus.core.util.CustomBlockTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerEntity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.Random;
import java.util.UUID;

public class SorceressFireSpawner extends Entity{

	@Nullable
	private LivingEntity owner;
	@Nullable
	private UUID ownerUUID;
	private int lifeTicks = 22;
	private boolean clientSideAttackStarted;
	private int warmupDelayTicks;
	private boolean sentFlameEvent;
	private float damage;

	public int rotationModifier;

	public SorceressFireSpawner(EntityType<? extends SorceressFireSpawner> p_19870_, Level p_19871_) {
		super(p_19870_, p_19871_);

	}

	public SorceressFireSpawner(Level worldIn, float damageIn, double posX, double posY, double posZ, float p_36930_, int warmUpDelayTicks, LivingEntity ownerIn) {
	      this(EntityInit.FLAME_SUMMONER.get(), worldIn);
	      this.warmupDelayTicks = warmUpDelayTicks;
	      this.setOwner(ownerIn);
	      this.setYRot(p_36930_ * (180F / (float)Math.PI));
	      this.setPos(posX, posY, posZ);
	      this.damage=damageIn;
	   }


	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder) {
	
		
	}

	@Override
	protected void readAdditionalSaveData(CompoundTag p_20052_) {
		this.warmupDelayTicks = p_20052_.getInt("Warmup");
	      if (p_20052_.hasUUID("Owner")) {
	         this.ownerUUID = p_20052_.getUUID("Owner");
	      }
		
	}

	@Override
	protected void addAdditionalSaveData(CompoundTag p_20139_) {
		p_20139_.putInt("Warmup", this.warmupDelayTicks);
	      if (this.ownerUUID != null) {
	    	  p_20139_.putUUID("Owner", this.ownerUUID);
	      }
		
	}

	@Override
	public Packet<ClientGamePacketListener> getAddEntityPacket(ServerEntity e) {
		
		return new ClientboundAddEntityPacket(this,e);
	}
	
	
	
	public void setOwner(@Nullable LivingEntity p_36939_) {
	    this.owner = p_36939_;
	    this.ownerUUID = p_36939_ == null ? null : p_36939_.getUUID();
	}

	@Nullable
	public LivingEntity getOwner() {
	   if (this.owner == null && this.ownerUUID != null && this.level() instanceof ServerLevel) {
	      Entity entity = ((ServerLevel)this.level()).getEntity(this.ownerUUID);
	      if (entity instanceof LivingEntity) {
	         this.owner = (LivingEntity)entity;
	      }
	   }

	   return this.owner;
	}

	 public float getAnimationProgress(float p_36937_) {
	      if (!this.clientSideAttackStarted) {
	         return 0.0F;
	      } else {
	         int i = this.lifeTicks - 2;
	         return i <= 0 ? 1.0F : 1.0F - ((float)i - p_36937_) / 20.0F;
	      }
	   }
	 
	 @Override
	public void onAddedToLevel() {
		super.onAddedToLevel();
	}
	 
	 @Override
	public boolean fireImmune() {
		
		return true;
	}

	 
	 @Override
	 public void tick() {
	      super.tick();
	      if (this.level().isClientSide) {
	         if (this.clientSideAttackStarted) {
	            --this.lifeTicks;
	            if (this.lifeTicks == 14) {
	               for(int i = 0; i < 12; ++i) {
	                  double d0 = this.getX() + (this.random.nextDouble() * 2.0D - 1.0D) * (double)this.getBbWidth() * 0.5D;
	                  double d1 = this.getY() + 0.05D + this.random.nextDouble();
	                  double d2 = this.getZ() + (this.random.nextDouble() * 2.0D - 1.0D) * (double)this.getBbWidth() * 0.5D;
	                  double d3 = (this.random.nextDouble() * 2.0D - 1.0D) * 0.3D;
	                  double d4 = 0.3D + this.random.nextDouble() * 0.3D;
	                  double d5 = (this.random.nextDouble() * 2.0D - 1.0D) * 0.3D;
	                  this.level().addParticle(ParticleTypes.CRIMSON_SPORE, d0, d1 + 1.0D, d2, d3, d4, d5);
	               }
	            }
	         }
	      } else if (--this.warmupDelayTicks < 0) {
	         if (this.warmupDelayTicks == -4) {
	            /*for(LivingEntity livingentity : this.level().getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(0.2D, 0.0D, 0.2D))) {
	               this.dealDamageTo(livingentity);
	            }*/
				 Vec3 pos = this.position();
				 Level world = this.level();
				 int xSpread = Math.toIntExact((long) (this.getBoundingBox().getXsize() * 1.0));
				 int zSpread = Math.toIntExact((long) (this.getBoundingBox().getZsize() * 1.0));
				 int ySpread = Math.toIntExact((long) (this.getBoundingBox().getYsize() * 1.0));
				 int x0 = this.blockPosition().getX();
				 int y0 = this.blockPosition().getY();
				 int z0 = this.blockPosition().getZ();
				 for(int i = 0; i<=24; i++) {
					 for (int j = 0; j <= zSpread; j++) {
						 for (int k = -ySpread; k <= ySpread; k++) {
							 double a = Math.PI / 12;
							 double d = j;
							 int xVar = (int) (d * Math.sin(i * a));
							 int yVar = k;
							 int zVar = (int) (d * Math.cos(i * a));
							 ;
							 int x = x0 + xVar;
							 int z = z0 + zVar;
							 int y = y0 + yVar;

							 BlockPos blockPos = new BlockPos(x, y, z);
							 if (level().getBlockState(blockPos).is(CustomBlockTags.FLAME_BREAKABLE)) {
								 level().destroyBlock(blockPos, true, this.getOwner());
								 level().gameEvent(this, GameEvent.BLOCK_DESTROY, blockPos);
							 }
						 }
					 }
				 }
	         }

	         if (!this.sentFlameEvent) {
	            this.level().broadcastEntityEvent(this, (byte)4);
	            this.sentFlameEvent = true;
	         }

	         if (--this.lifeTicks < 0) {
	            this.discard();
	         }
	      }

	   }
	 
	  /*private void dealDamageTo(LivingEntity e) {
	      LivingEntity livingentity = this.getOwner();

	      if (e.isAlive() && !e.isInvulnerable() && e != livingentity) {
	         if (livingentity == null) {
	            e.hurt(this.level().damageSources().magic(), 6.0F);
				e.knockback(0.1f,0.01,0.01);
				 Vec3 mov =e.getDeltaMovement();
				 e.setDeltaMovement(mov.x,Math.max(mov.y+0.5,-Math.min(mov.y,0)+0.5),mov.z);
	         } else {
				if (livingentity.isAlliedTo(e)) {
	               return;
	            }
				e.knockback(0.1f,0.01,0.01);
				 Vec3 mov =e.getDeltaMovement();
				 e.setDeltaMovement(mov.x,Math.max(mov.y+0.5,-Math.min(mov.y,0)+0.5),mov.z);
	            e.hurt(this.level().damageSources().indirectMagic(this, livingentity), damage);
	         }

	      }else if(livingentity==e&&e instanceof Player){
			  e.knockback(0.1f,0,0);
			  Vec3 mov =e.getDeltaMovement();
			  e.setDeltaMovement(mov.x,Math.max(mov.y+0.5,-Math.min(mov.y,0)+0.5),mov.z);
		  }
	   }*/
       @Override
	   public void handleEntityEvent(byte p_36935_) {
	      super.handleEntityEvent(p_36935_);
	      if (p_36935_ == 4) {
	         this.clientSideAttackStarted = true;
	         if (!this.isSilent()) {
	            this.level().playLocalSound(this.getX(), this.getY(), this.getZ(), SoundEvents.FIRECHARGE_USE, this.getSoundSource(), 1.0F, this.random.nextFloat() * 0.2F + 0.85F, false);
	         }
	      }

	   }
	

}
