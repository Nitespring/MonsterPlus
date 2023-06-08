package com.nitespring.monsterplus.core.events;

import com.nitespring.monsterplus.MonsterPlus;
import com.nitespring.monsterplus.networking.ItemLeftClickAction;
import com.nitespring.monsterplus.networking.MonsterPlusPacketHandler;

import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.TickEvent.ClientTickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = MonsterPlus.MODID, value = Dist.CLIENT)
public class ClientEvents {
	
	private static boolean isAttackKeyDown =false;
	
	@SubscribeEvent
	 public static void performItemLeftClickAction(ClientTickEvent event) {
	 Minecraft instance = Minecraft.getInstance();
	if(instance.options.keyAttack.isDown()) {
		 if(isAttackKeyDown==false) {
			 MonsterPlusPacketHandler.INSTANCE.sendToServer(new ItemLeftClickAction(1));
			 isAttackKeyDown=true;
		 }
	 }else {
		 isAttackKeyDown=false; 
	 }
	 
	 }
	 
	 
	
	    
	    
	   
	   
	    
	    
	    
	    
	    
	    
}
