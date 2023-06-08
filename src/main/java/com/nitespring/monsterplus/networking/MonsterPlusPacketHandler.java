package com.nitespring.monsterplus.networking;



import com.nitespring.monsterplus.MonsterPlus;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;


public class MonsterPlusPacketHandler {
	public static SimpleChannel INSTANCE;
	private static final String PROTOCOL_VERSION = "1";
	private static int ID = 0;
    private static int nextID() {
        return ID++;
    }

    public static void registerMessages() {
    	//public static final SimpleChannel 
    	INSTANCE = NetworkRegistry.newSimpleChannel(
    		    new ResourceLocation(MonsterPlus.MODID, "main"),
    		    () -> PROTOCOL_VERSION,
    		    PROTOCOL_VERSION::equals,
    		    PROTOCOL_VERSION::equals
    		  
    		);
    	
    	 INSTANCE.messageBuilder(ItemLeftClickAction.class, nextID())
	        .encoder(ItemLeftClickAction::toBytes)
	        .decoder(ItemLeftClickAction::new)
	        .consumerMainThread(ItemLeftClickAction::handle)
	        .add();
    	
    }
    public static void sendToServer(Object packet) {
        INSTANCE.sendToServer(packet);
    }
    public static void sendToClient(Object packet, ServerPlayer player) {
        INSTANCE.sendTo(packet, player.connection.connection, NetworkDirection.PLAY_TO_CLIENT);
    }
    
    

}
