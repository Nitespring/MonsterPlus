package github.nitespring.monsterplus.common.item;


import github.nitespring.monsterplus.ClientListener;
import github.nitespring.monsterplus.MonsterPlus;
import github.nitespring.monsterplus.client.render.equipment.armour.CrystalArmourModel;
import github.nitespring.monsterplus.client.render.equipment.armour.DarkArmourModel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.PreparableReloadListener;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Consumer;


public class DarkArmour extends ArmorItem{

	public static final ResourceLocation TEXTURE_LOCATION = ResourceLocation.fromNamespaceAndPath(MonsterPlus.MODID, "textures/armour/crystal_layer_1.png");

	public DarkArmour(Holder<ArmorMaterial> material, Type type, Properties properties) {
		super(material, type, properties.stacksTo(1).rarity(Rarity.EPIC).durability(1024));
	}


	@Override
	public boolean isDamageable(ItemStack stack) {
		return true;
	}

	@Override
	public @Nullable ResourceLocation getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, ArmorMaterial.Layer layer, boolean innerModel) {
		return slot == EquipmentSlot.LEGS ? ResourceLocation.fromNamespaceAndPath(MonsterPlus.MODID,"textures/armour/crystal_layer_2.png") : ResourceLocation.fromNamespaceAndPath(MonsterPlus.MODID , "textures/armour/dark_armor.png");
	}



	@Override
	public void initializeClient(Consumer<IClientItemExtensions> consumer) {
		consumer.accept(ArmorRender.INSTANCE);
	}
	

	

	private static final class ArmorRender implements IClientItemExtensions {
		private static final ArmorRender INSTANCE = new ArmorRender();

		
		
		
		
		
		@Override
		public @NotNull HumanoidModel<?> getHumanoidArmorModel(LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel<?> model) {
			EntityModelSet models = Minecraft.getInstance().getEntityModels();
			ModelPart root = models.bakeLayer(slot == EquipmentSlot.LEGS ? ModelLayers.PLAYER_INNER_ARMOR : ClientListener.DARK_ARMOUR_MAIN_LAYER);
			DarkArmourModel<LivingEntity> aModel = new DarkArmourModel<LivingEntity>(root);
			/*if(living instanceof Player player){

				aModel.setupAnim(player,player.swingTime, (float) player.getKnownMovement().length(),living.tickCount,player.yHeadRot,living.getViewXRot(living.tickCount));
			}else{
				aModel.setupAnim(living,living.swingTime, (float) living.getKnownMovement().length(),living.tickCount,living.yHeadRot,living.getViewXRot(living.tickCount));
			}*/
			//Minecraft.getInstance().entity
			float f = 0;
			//if(living.walkAnimation.speed()){
				f = living.walkAnimation.speed(0.1f);
			double d = Mth.lerp(0.1f,living.getKnownMovement().horizontalDistance(),living.getKnownMovement().length());
			aModel.setupAnim(living, (float) d, f,living.tickCount,living.yHeadRot,living.getViewXRot(living.tickCount));
			return aModel;
		}

		
		
		
		
	}
	

}
