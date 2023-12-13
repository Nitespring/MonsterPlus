package github.nitespring.monsterplus.common.item;




import java.awt.MenuComponent;
import java.awt.event.TextEvent;
import java.util.List;
import java.util.function.Consumer;

import javax.swing.text.JTextComponent;

import org.antlr.v4.runtime.misc.NotNull;
import org.jetbrains.annotations.Nullable;

import com.mojang.blaze3d.platform.GlStateManager;

import github.nitespring.monsterplus.ClientListener;
import github.nitespring.monsterplus.MonsterPlus;
import github.nitespring.monsterplus.client.render.equipment.crystalarmour.CrystalArmourModel;
import github.nitespring.monsterplus.client.render.equipment.crystalarmour.CrystalArmourModelNew;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.ChunkRenderTypeSet;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;

public class CrystalArmour extends ArmorItem{

	public static final ResourceLocation TEXTURE_LOCATION = new ResourceLocation(MonsterPlus.MODID, "textures/armour/crystal_layer_1.png");
	
	public CrystalArmour(ArmorMaterial p_40386_, Type p_40387_, Properties p_40388_) {
		super(p_40386_, p_40387_, p_40388_);
	}
	
	
	
	
	
	
	
	@Override
	public @Nullable String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
		return slot == EquipmentSlot.LEGS ? new String(MonsterPlus.MODID + ":textures/armour/crystal_layer_2.png") : new String(MonsterPlus.MODID + ":textures/armour/crystal_layer_1.png");
		//return new String(MonsterPlus.MODID + ":textures/armour/crystal_armour_layer_1.png");
	}
	
	
	@Override
	public void initializeClient(Consumer<IClientItemExtensions> consumer) {
		consumer.accept(ArmorRender.INSTANCE);
	}
	
	@OnlyIn(Dist.CLIENT)
	@Override
	public void appendHoverText(ItemStack p_41421_, Level p_41422_, List<Component> tooltip, TooltipFlag p_41424_) {
		
		String string = "\u00A77\u00A7o+15% Protection from Magic Damage";
		tooltip.add(Component.literal(string));
	}
	
	@Override
	public Rarity getRarity(ItemStack p_41461_) {
		
		return Rarity.EPIC;
	}
	
	
	
	
	
	
	
	//DiscFragmentItem
	
	
	private static final class ArmorRender implements IClientItemExtensions {
		private static final ArmorRender INSTANCE = new ArmorRender();

		
		
		
		
		
		@Override
		public @NotNull HumanoidModel<?> getHumanoidArmorModel(LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel<?> model) {
			EntityModelSet models = Minecraft.getInstance().getEntityModels();
			ModelPart root = models.bakeLayer(slot == EquipmentSlot.LEGS ? ModelLayers.PLAYER_INNER_ARMOR : ClientListener.CRYSTAL_ARMOUR_MAIN_LAYER);
			CrystalArmourModel aModel = new CrystalArmourModel(root);
			return aModel;
		}
		
		
		
		
		
		/*
		@Override
		public @org.jetbrains.annotations.NotNull Model getGenericArmorModel(LivingEntity livingEntity,
				ItemStack itemStack, EquipmentSlot slot, HumanoidModel<?> original) {
			EntityModelSet models = Minecraft.getInstance().getEntityModels();
			ModelPart root = models.bakeLayer(slot == EquipmentSlot.LEGS ? ModelLayers.PLAYER_INNER_ARMOR : ClientListener.CRYSTAL_ARMOUR_MAIN_LAYER);
			return new CrystalArmourModelNew(root);
		}
		*/
		
	}
	

}
