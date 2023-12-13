package github.nitespring.monsterplus.client.render.entities.mobs.abyssologer;

import net.minecraft.client.model.IllagerModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.world.entity.monster.AbstractIllager;

public class AbyssologerModel<T extends AbstractIllager> extends IllagerModel<T>{

	public AbyssologerModel(ModelPart p_170688_) {
		super(p_170688_);
		this.getHat().visible = true;
	
	}
	
	


}
