package com.nitespring.monsterplus.networking;


import java.util.function.Supplier;

import com.nitespring.monsterplus.common.item.ILeftClickSpecialActionItem;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;


public class ItemLeftClickAction {
	        private final int id;
	       
	       
	      

	        public ItemLeftClickAction(FriendlyByteBuf buf) {
	            id = buf.readInt();
	         
	        }
	        public ItemLeftClickAction(int id) {
	            this.id = id;
	     
	        }
	        public void toBytes(FriendlyByteBuf buf) {
	            buf.writeInt(id);  
	           
	        }
	        public boolean handle(Supplier<NetworkEvent.Context> ctx) {
	            ctx.get().enqueueWork(() -> {
	            	Player playerIn = ctx.get().getSender();
	            	ItemStack mainHand = playerIn.getMainHandItem();
	    			
	    			if(mainHand.getItem() instanceof ILeftClickSpecialActionItem) {
	    				  if (playerIn.getAttackStrengthScale(0)>=0.9) {
	    			((ILeftClickSpecialActionItem)mainHand.getItem()).doLeftClickAction(playerIn, mainHand);
	    				  }
	    			}
	            	
	           
	            });
	            
	            return true;
	        } 
	      
}

