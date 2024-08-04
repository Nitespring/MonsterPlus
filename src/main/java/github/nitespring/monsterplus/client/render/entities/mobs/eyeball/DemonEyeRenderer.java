package github.nitespring.monsterplus.client.render.entities.mobs.eyeball;


import github.nitespring.monsterplus.ClientListener;
import github.nitespring.monsterplus.MonsterPlus;
import github.nitespring.monsterplus.client.render.entities.mobs.abyssologer.EyeModel;
import github.nitespring.monsterplus.client.render.entities.mobs.abyssologer.EyeOverlayLayer;
import github.nitespring.monsterplus.common.entity.DemonEye;
import github.nitespring.monsterplus.common.entity.Eye;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;

public class DemonEyeRenderer<T extends DemonEye> extends MobRenderer<T, DemonEyeModel<T>>{


	public static final ResourceLocation OVERLAY = ResourceLocation.fromNamespaceAndPath(MonsterPlus.MODID, "textures/entities/demon_eye/demon_eye_translucent.png");
	public static final ResourceLocation RED = ResourceLocation.fromNamespaceAndPath(MonsterPlus.MODID, "textures/entities/demon_eye/demon_eye_red.png");
	public static final ResourceLocation PURPLE = ResourceLocation.fromNamespaceAndPath(MonsterPlus.MODID, "textures/entities/demon_eye/demon_eye_purple.png");
	public static final ResourceLocation GREEN = ResourceLocation.fromNamespaceAndPath(MonsterPlus.MODID, "textures/entities/demon_eye/demon_eye_green.png");
	public static final ResourceLocation YELLOW = ResourceLocation.fromNamespaceAndPath(MonsterPlus.MODID, "textures/entities/demon_eye/demon_eye_yellow.png");
	public static final ResourceLocation BLUE = ResourceLocation.fromNamespaceAndPath(MonsterPlus.MODID, "textures/entities/demon_eye/demon_eye_blue.png");
	public static final ResourceLocation TEETH_OPEN = ResourceLocation.fromNamespaceAndPath(MonsterPlus.MODID, "textures/entities/demon_eye/demon_eye_teeth.png");
	public static final ResourceLocation TEETH_CLOSED = ResourceLocation.fromNamespaceAndPath(MonsterPlus.MODID, "textures/entities/demon_eye/demon_eye_teeth_closed.png");
	 public DemonEyeRenderer(Context context) {
	     super(context, new DemonEyeModel<T>(context.bakeLayer(ClientListener.DEMON_EYE_LAYER)), 0.75f);
	     this.addLayer(new DemonEyeOverlayLayer<>(this, context.getModelSet()));
	   }

	public DemonEyeRenderer(Context context, DemonEyeModel<T> e, float f) {
		super(context, e, f);
	}

	
	
	@Override
	public ResourceLocation getTextureLocation(T e) {
		switch(e.getEyeType()) {
			case 1:
				return PURPLE;
			case 2:
				return GREEN;
			case 3:
				return YELLOW;
			case 4:
				return BLUE;
			case 5:
				return (e.tickCount%12==0||e.tickCount%12==1||e.tickCount%12==3||e.tickCount%12==4)?TEETH_CLOSED:TEETH_OPEN;
			default:
				return RED;

		}
	}
	
	@Override
	protected int getBlockLightLevel(T e, BlockPos pos) {
		
		return super.getBlockLightLevel(e,pos);
	}

	
}
