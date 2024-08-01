package github.nitespring.monsterplus.core.events;

import github.nitespring.monsterplus.MonsterPlus;
import github.nitespring.monsterplus.networking.ItemLeftClickAction;
import github.nitespring.monsterplus.networking.MonsterPlusPacketHandler;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.TickEvent.ClientTickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MonsterPlus.MODID, value = Dist.CLIENT)
public class ClientEvents {
	
	private static boolean isAttackKeyDown =false;
	
	@SubscribeEvent
	 public static void performItemLeftClickAction(ClientTickEvent event) {
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
