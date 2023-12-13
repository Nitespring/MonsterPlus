package github.nitespring.monsterplus.networking;


import java.util.function.Supplier;

import github.nitespring.monsterplus.common.item.ILeftClickSpecialActionItem;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.network.CustomPayloadEvent;


public class ItemLeftClickAction {
	       
	      

	        public ItemLeftClickAction(FriendlyByteBuf buf) {}
	        public ItemLeftClickAction() {}
	        //public void toBytes(FriendlyByteBuf buf) {}
	        public void encode(FriendlyByteBuf buffer) {}

	        public void handle(CustomPayloadEvent.Context ctx) {
	        	
	            //ctx.enqueueWork(() -> {
	            	Player playerIn = ctx.getSender();
	            	if (playerIn==null)
	            			return;
	            	ItemStack mainHand = playerIn.getMainHandItem();
	    			
	    			if(mainHand.getItem() instanceof ILeftClickSpecialActionItem) {
	    				  if (playerIn.getAttackStrengthScale(0)>=0.9) {
	    			((ILeftClickSpecialActionItem)mainHand.getItem()).doLeftClickAction(playerIn, mainHand);
	    				  }
	    			}
	            	
	           
	            //});
	        	
	            
	        } 
	      
}

