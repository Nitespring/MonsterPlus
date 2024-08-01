package github.nitespring.monsterplus.networking;







import github.nitespring.monsterplus.MonsterPlus;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

import net.minecraft.server.level.ServerPlayer;


import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;


public class MonsterPlusPacketHandler {



	public static void onRegisterPayloadHandler(RegisterPayloadHandlersEvent event) {
		PayloadRegistrar registrar = event.registrar(MonsterPlus.MODID)
				.versioned("1.0")
				.optional();
		registrar.playToServer(
				ItemLeftClickAction.TYPE,
				ItemLeftClickAction.STREAM_CODEC,
				ItemLeftClickAction.ServerPayloadHandler::handleData);
	}

	public static <MSG extends CustomPacketPayload> void sendToServer(MSG message) {
		PacketDistributor.sendToServer(message);
	}

	public static <MSG extends CustomPacketPayload> void sendToPlayer(MSG message, ServerPlayer player) {
		PacketDistributor.sendToPlayer(player, message);
	}


}
