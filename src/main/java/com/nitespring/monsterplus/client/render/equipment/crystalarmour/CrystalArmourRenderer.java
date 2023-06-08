package com.nitespring.monsterplus.client.render.equipment.crystalarmour;

import javax.annotation.Nullable;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;

public class CrystalArmourRenderer extends HumanoidArmorLayer<LivingEntity, CrystalArmourModel, CrystalArmourModel>{

	 private final CrystalArmourModel innerModel;
	 private final CrystalArmourModel outerModel;
	
	public CrystalArmourRenderer(RenderLayerParent<LivingEntity, CrystalArmourModel> p_117075_,
			CrystalArmourModel p_117076_, CrystalArmourModel p_117077_,ModelManager p_267238_) {
		super(p_117075_, p_117076_, p_117077_,p_267238_);
		this.innerModel = p_117076_;
		this.outerModel = p_117077_;
	}
	
	//ZombieRenderer
	
	/*
	@Override
	public void render(PoseStack p_117096_, MultiBufferSource p_117097_, int p_117098_, T p_117099_, float p_117100_, float p_117101_, float p_117102_, float p_117103_, float p_117104_, float p_117105_) {
	      this.renderArmorPiece(p_117096_, p_117097_, p_117099_, EquipmentSlot.CHEST, p_117098_, this.getArmorModel(EquipmentSlot.CHEST));
	      this.renderArmorPiece(p_117096_, p_117097_, p_117099_, EquipmentSlot.LEGS, p_117098_, this.getArmorModel(EquipmentSlot.LEGS));
	      this.renderArmorPiece(p_117096_, p_117097_, p_117099_, EquipmentSlot.FEET, p_117098_, this.getArmorModel(EquipmentSlot.FEET));
	      this.renderArmorPiece(p_117096_, p_117097_, p_117099_, EquipmentSlot.HEAD, p_117098_, this.getArmorModel(EquipmentSlot.HEAD));
	   }

	   private void renderArmorPiece(PoseStack p_117119_, MultiBufferSource p_117120_, T p_117121_, EquipmentSlot p_117122_, int p_117123_, A p_117124_) {
	      ItemStack itemstack = p_117121_.getItemBySlot(p_117122_);
	      if (itemstack.getItem() instanceof ArmorItem) {
	         ArmorItem armoritem = (ArmorItem)itemstack.getItem();
	         if (armoritem.getSlot() == p_117122_) {
	            this.getParentModel().copyPropertiesTo(p_117124_);
	            this.setPartVisibility(p_117124_, p_117122_);
	            net.minecraft.client.model.Model model = getArmorModelHook(p_117121_, itemstack, p_117122_, p_117124_);
	            boolean flag = this.usesInnerModel(p_117122_);
	            boolean flag1 = itemstack.hasFoil();
	            if (armoritem instanceof net.minecraft.world.item.DyeableLeatherItem) {
	               int i = ((net.minecraft.world.item.DyeableLeatherItem)armoritem).getColor(itemstack);
	               float f = (float)(i >> 16 & 255) / 255.0F;
	               float f1 = (float)(i >> 8 & 255) / 255.0F;
	               float f2 = (float)(i & 255) / 255.0F;
	               this.renderModelT(p_117119_, p_117120_, p_117123_, flag1, model, f, f1, f2, this.getArmorResource(p_117121_, itemstack, p_117122_, null));
	               this.renderModelT(p_117119_, p_117120_, p_117123_, flag1, model, 1.0F, 1.0F, 1.0F, this.getArmorResource(p_117121_, itemstack, p_117122_, "overlay"));
	            } else {
	               this.renderModelT(p_117119_, p_117120_, p_117123_, flag1, model, 1.0F, 1.0F, 1.0F, this.getArmorResource(p_117121_, itemstack, p_117122_, null));
	            }

	         }
	      }
	   }
	
	   private void renderModelT(PoseStack p_117107_, MultiBufferSource p_117108_, int p_117109_, ArmorItem p_117110_, boolean p_117111_, CrystalArmourModel p_117112_, boolean p_117113_, float p_117114_, float p_117115_, float p_117116_, @Nullable String p_117117_) {
		   renderModelTranslucent(p_117107_, p_117108_, p_117109_, p_117111_, p_117112_, p_117114_, p_117115_, p_117116_, this.getArmorLocation(p_117110_, p_117113_, p_117117_));
	   }
	   private void renderModelTranslucent(PoseStack p_117107_, MultiBufferSource p_117108_, int p_117109_, boolean p_117111_, net.minecraft.client.model.Model p_117112_, float p_117114_, float p_117115_, float p_117116_, ResourceLocation armorResource) {
	      VertexConsumer vertexconsumer = ItemRenderer.getArmorFoilBuffer(p_117108_, RenderType.armorCutoutNoCull(armorResource), false, p_117111_);
	      p_117112_.renderToBuffer(p_117107_, vertexconsumer, p_117109_, OverlayTexture.NO_OVERLAY, p_117114_, p_117115_, p_117116_, 1.0F);
	   }

	   private CrystalArmourModel getArmorModel(EquipmentSlot p_117079_) {
	      return (CrystalArmourModel)(this.usesInnerModel(p_117079_) ? this.innerModel : this.outerModel);
	   }

	   private boolean usesInnerModel(EquipmentSlot p_117129_) {
	      return p_117129_ == EquipmentSlot.LEGS;
	   }
	*/
	

}
