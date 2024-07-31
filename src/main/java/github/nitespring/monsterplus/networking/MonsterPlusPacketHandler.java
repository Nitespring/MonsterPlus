package github.nitespring.monsterplus.networking;







import github.nitespring.monsterplus.MonsterPlus;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;

import net.minecraftforge.network.ChannelBuilder;

import net.minecraftforge.network.NetworkDirection;

import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.SimpleChannel;


public class MonsterPlusPacketHandler {



	private static final SimpleChannel INSTANCE = ChannelBuilder.named(
					ResourceLocation.fromNamespaceAndPath(MonsterPlus.MODID, "main"))
			.serverAcceptedVersions((status, version) -> true)
			.clientAcceptedVersions((status, version) -> true)
			.networkProtocolVersion(1).optional()
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


}
