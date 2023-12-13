package github.nitespring.monsterplus.common.entity.projectiles;

import java.util.UUID;

import javax.annotation.Nullable;

import github.nitespring.monsterplus.core.init.EntityInit;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

public class SpikeCountdown extends Entity{

	@Nullable
	private LivingEntity owner;
	@Nullable
	private UUID ownerUUID;
	
	private int lifeTicks = 40;
	private float damage;
	
	private int warmupDelayTicks;
	private boolean sentSpike = false;

	
	
	public SpikeCountdown(EntityType<?> p_19870_, Level p_19871_) {
		super(p_19870_, p_19871_);
		
	}
	
	public SpikeCountdown( Level worldIn, float damageIn, double posX, double posY, double posZ, float p_36930_, int warmUpDelayTicks, LivingEntity ownerIn, int tickDelayIn) {
	      this(EntityInit.SPIKE_COUNTDOWN.get(), worldIn);
	      this.warmupDelayTicks = warmUpDelayTicks;
	      this.setOwner(ownerIn);
	      this.setYRot(p_36930_ * (180F / (float)Math.PI));
	      this.setPos(posX, posY, posZ);
	      this.damage=damageIn;
	      this.lifeTicks=tickDelayIn;
	   }


	 @Override
		public boolean fireImmune() {
			
			return true;
		}

	@Override
	protected void defineSynchedData() {
		
		
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
	public Packet<ClientGamePacketListener> getAddEntityPacket() {
		
		return new ClientboundAddEntityPacket(this);
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
	
	
	 @Override
	 public void tick() {
	      super.tick();
	      
	            --this.lifeTicks;
	            if (this.lifeTicks <= 0 && !sentSpike) {
	            	
	               this.level().addFreshEntity(new CrystalSpikes(this.level(), this.damage, this.position().x, this.position().y, this.position().z, this.yRotO, warmupDelayTicks, this.getOwner()));
	               sentSpike=true;
	        
	           }
	            
	            if (this.lifeTicks <= -10 -warmupDelayTicks) {
	            	this.remove(RemovalReason.DISCARDED);   
	            }
	            
	            
	            
	          for(int k = 0; k<13; k++ ) { 
	        this.level().addParticle(ParticleTypes.PORTAL, this.getRandomX(1), this.position().y,this.getRandomZ(1), 0, 0.0f, 0);    
	          }
	           
	}
	
}
