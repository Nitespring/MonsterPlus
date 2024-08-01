package github.nitespring.monsterplus.core.events;

import github.nitespring.monsterplus.MonsterPlus;
import github.nitespring.monsterplus.networking.ItemLeftClickAction;
import github.nitespring.monsterplus.networking.MonsterPlusPacketHandler;
import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;


@OnlyIn(Dist.CLIENT)
@EventBusSubscriber(modid = MonsterPlus.MODID, value = Dist.CLIENT)
public class ClientEvents {
	
	private static boolean isAttackKeyDown =false;
	
	@SubscribeEvent
	 public static void performItemLeftClickAction(ClientTickEvent.Pre event) {
	 Minecraft instance = Minecraft.getInstance();
	if(instance.options.keyAttack.isDown()) {
		 if(isAttackKeyDown==false) {
			 MonsterPlusPacketHandler.sendToServer(new ItemLeftClickAction());
			 isAttackKeyDown=true;
		 }
	 }else {
		 isAttackKeyDown=false; 
	 }
	 
	 }
	 
	 
	
	    
	    
	   
	   
	    
	    
	    
	    
	    
	    
}
