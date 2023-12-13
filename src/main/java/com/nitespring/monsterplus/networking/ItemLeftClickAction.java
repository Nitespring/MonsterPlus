package com.nitespring.monsterplus.networking;


import java.util.function.Supplier;

import com.nitespring.monsterplus.common.item.ILeftClickSpecialActionItem;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.network.CustomPayloadEvent;


public class ItemLeftClickAction {
	        private final int id;
	        private final FriendlyByteBuf buf;
	       
	      

	        public ItemLeftClickAction(FriendlyByteBuf buf) {
	            id = buf.readInt();
	            this.buf=buf;
	         
	        }
	        public ItemLeftClickAction(int id) {
	            this.id = id;
	            this.buf=null;
	     
	        }
	        public void toBytes(FriendlyByteBuf buf) {
	            buf.writeInt(id);  
	           
	        }
	        public void encode(FriendlyByteBuf buffer) {}

	        public void handle(CustomPayloadEvent.Context ctx) {
	        	
	            ctx.enqueueWork(() -> {
	            	Player playerIn = ctx.getSender();
	            	ItemStack mainHand = playerIn.getMainHandItem();
	    			
	    			if(mainHand.getItem() instanceof ILeftClickSpecialActionItem) {
	    				  if (playerIn.getAttackStrengthScale(0)>=0.9) {
	    			((ILeftClickSpecialActionItem)mainHand.getItem()).doLeftClickAction(playerIn, mainHand);
	    				  }
	    			}
	            	
	           
	            });
	        	
	            
	        } 
	      
}

