package com.nitespring.monsterplus.networking;





import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

import com.nitespring.monsterplus.MonsterPlus;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.Channel;
import net.minecraftforge.network.ChannelBuilder;
import net.minecraftforge.network.ChannelListManager;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.SimpleChannel;


public class MonsterPlusPacketHandler {
	
	
	
	 private static final SimpleChannel INSTANCE = ChannelBuilder.named(
	            new ResourceLocation(MonsterPlus.MODID, "main"))
	            .serverAcceptedVersions((status, version) -> true)
	            .clientAcceptedVersions((status, version) -> true)
	            .networkProtocolVersion(1)
	            .simpleChannel();
	
	
	 public static void register() {
	        INSTANCE.messageBuilder(ItemLeftClickAction.class, NetworkDirection.PLAY_TO_SERVER)
	                .encoder(ItemLeftClickAction::encode)
	                .decoder(ItemLeftClickAction::new)
	                .consumerMainThread(ItemLeftClickAction::handle)
	                .add();
	    }

	    public static void sendToServer(Object msg) {
	        INSTANCE.send(msg, PacketDistributor.SERVER.noArg());
	    }

	    public static void sendToPlayer(Object msg, ServerPlayer player) {
	        INSTANCE.send(msg, PacketDistributor.PLAYER.with(player));
	    }

	    public static void sendToAllClients(Object msg) {
	        INSTANCE.send(msg, PacketDistributor.ALL.noArg());
	    }
	
	
	
	
	
	
	
	
	/*
	public static SimpleChannel INSTANCE;
	private static final String PROTOCOL_VERSION = "1";
	private static int ID = 0;
    private static int nextID() {
        return ID++;
    }
    public static void registerMessages() {
    	//public static final SimpleChannel 
    	INSTANCE = ChannelBuilder.named(
    		    new ResourceLocation(MonsterPlus.MODID, "main"))
    			.networkProtocolVersion(Integer.parseInt(PROTOCOL_VERSION))
    			.simpleChannel()
	    		.messageBuilder(ItemLeftClickAction.class, nextID())
		        .encoder(ItemLeftClickAction::toBytes)
		        .decoder(ItemLeftClickAction::new)
		        .consumerMainThread(ItemLeftClickAction::handle)
		        .add();    	
    }
    public static void sendToServer(Object packet) {
        INSTANCE.sendToServer(packet);
    }
    public static void sendToClient(Object packet, ServerPlayer player) {
        INSTANCE.sendTo(packet, player.connection.getConnection(), NetworkDirection.PLAY_TO_CLIENT);
    }
    public static int getVersion() {
        return INSTANCE.getProtocolVersion();
    }
    */
}
