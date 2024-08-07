package github.nitespring.monsterplus.common.item;




import java.util.List;
import java.util.function.Consumer;

import net.minecraft.core.Holder;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import github.nitespring.monsterplus.ClientListener;
import github.nitespring.monsterplus.MonsterPlus;
import github.nitespring.monsterplus.client.render.equipment.armour.CrystalArmourModel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;


public class CrystalArmour extends ArmorItem{

	public static final ResourceLocation TEXTURE_LOCATION = ResourceLocation.fromNamespaceAndPath(MonsterPlus.MODID, "textures/armour/crystal_layer_1.png");
	
	public CrystalArmour(Holder<ArmorMaterial> material, Type type, Properties properties) {
		super(material, type, properties.stacksTo(1).rarity(Rarity.EPIC).durability(512));
	}


	@Override
	public boolean isDamageable(ItemStack stack) {
		return true;
	}

	@Override
	public @Nullable ResourceLocation getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, ArmorMaterial.Layer layer, boolean innerModel) {
		return slot == EquipmentSlot.LEGS ? ResourceLocation.fromNamespaceAndPath(MonsterPlus.MODID,"textures/armour/crystal_layer_2.png") : ResourceLocation.fromNamespaceAndPath(MonsterPlus.MODID , "textures/armour/crystal_layer_1.png");
	}



	@Override
	public void initializeClient(Consumer<IClientItemExtensions> consumer) {
		consumer.accept(ArmorRender.INSTANCE);
	}
	
	@OnlyIn(Dist.CLIENT)
	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag tooltipFlag) {
		
		String string = "\u00A77\u00A7o+15% Protection from Magic Damage";
		tooltip.add(Component.literal(string));
	}
	

	
	
	
	
	
	
	
	//DiscFragmentItem
	
	
	private static final class ArmorRender implements IClientItemExtensions {
		private static final ArmorRender INSTANCE = new ArmorRender();

		
		
		
		
		
		@Override
		public @NotNull HumanoidModel<?> getHumanoidArmorModel(LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel<?> model) {
			EntityModelSet models = Minecraft.getInstance().getEntityModels();
			ModelPart root = models.bakeLayer(slot == EquipmentSlot.LEGS ? ModelLayers.PLAYER_INNER_ARMOR : ClientListener.CRYSTAL_ARMOUR_MAIN_LAYER);
			ResourceLocation resource = slot == EquipmentSlot.LEGS ? ResourceLocation.fromNamespaceAndPath(MonsterPlus.MODID,"textures/armour/crystal_layer_2.png") : ResourceLocation.fromNamespaceAndPath(MonsterPlus.MODID , "textures/armour/crystal_layer_1.png");
			CrystalArmourModel aModel = new CrystalArmourModel(resource, root);
			return aModel;
		}
		
		
		
		
	}
	

}
