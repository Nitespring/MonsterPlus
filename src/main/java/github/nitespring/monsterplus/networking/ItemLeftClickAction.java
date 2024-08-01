package github.nitespring.monsterplus.networking;


import java.util.function.Supplier;

import github.nitespring.monsterplus.MonsterPlus;
import github.nitespring.monsterplus.common.item.ILeftClickSpecialActionItem;
import io.netty.buffer.ByteBuf;

import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.network.handling.IPayloadContext;


public record  ItemLeftClickAction() implements CustomPacketPayload {

	public static final CustomPacketPayload.Type<ItemLeftClickAction> TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(MonsterPlus.MODID, "attack"));
	public static final ItemLeftClickAction INSTANCE = new ItemLeftClickAction();

	public static final StreamCodec<ByteBuf,ItemLeftClickAction> STREAM_CODEC = StreamCodec.unit(INSTANCE);


	@Override
	public Type<? extends CustomPacketPayload> type() {
		return TYPE;
	}

	public class ServerPayloadHandler {

		public static void handleData(final ItemLeftClickAction data, final IPayloadContext ctx) {
			ctx.enqueueWork(() -> {
				Player playerIn = ctx.player();
				if (playerIn==null)
					return;
				ItemStack mainHand = playerIn.getMainHandItem();

				if(mainHand.getItem() instanceof ILeftClickSpecialActionItem) {
					if (playerIn.getAttackStrengthScale(0)>=0.9) {
						((ILeftClickSpecialActionItem)mainHand.getItem()).doLeftClickAction(playerIn, mainHand);
					}
				}
			})
			.exceptionally(e -> {
				ctx.disconnect(Component.translatable(MonsterPlus.MODID + ".networking.failed", e.getMessage()));
				return null;
			});
		}
	}

}

