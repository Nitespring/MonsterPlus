package github.nitespring.monsterplus.client.render.entities.mobs.wisp;

import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.HumanoidArmorModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;

import java.util.Arrays;

public class WispModel<T extends Entity> extends HierarchicalModel<T> {
    private final ModelPart[] tentacles = new ModelPart[8];
    private final ModelPart root;

    public WispModel(ModelPart pRoot) {
        this.root = pRoot;
        Arrays.setAll(this.tentacles, p_170995_ -> pRoot.getChild(createTentacleName(p_170995_)));
    }

    private static String createTentacleName(int pIndex) {
        return "tentacle" + pIndex;
    }

    public static LayerDefinition createInnerLayer() {
        return createBodyLayer(new CubeDeformation(-0.48F),CubeDeformation.NONE);
    }
    public static LayerDefinition createOuterLayer() {
        return createBodyLayer(new CubeDeformation(2.02F),new CubeDeformation(0.5F));
    }

    public static LayerDefinition createBodyLayer(CubeDeformation cubedeformation1,CubeDeformation cubedeformation2) {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        int i = -16;
        partdefinition.addOrReplaceChild(
                "body", CubeListBuilder.create().texOffs(0, 0).addBox(-7.0F, -8.0F, -7.0F, 14.0F, 16.0F, 14.0F, cubedeformation1), PartPose.offset(0.0F, 8.0F, 0.0F)
        );
        int j = 8;
        CubeListBuilder cubelistbuilder = CubeListBuilder.create().texOffs(48, 0).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 12.0F, 2.0F, cubedeformation2);

        for (int k = 0; k < 8; k++) {
            double d0 = (double)k * Math.PI * 2.0 / 8.0;
            float f = (float)Math.cos(d0) * 5.0F;
            float f1 = 15.0F;
            float f2 = (float)Math.sin(d0) * 5.0F;
            d0 = (double)k * Math.PI * -2.0 / 8.0 + (Math.PI / 2);
            float f3 = (float)d0;
            partdefinition.addOrReplaceChild(createTentacleName(k), cubelistbuilder, PartPose.offsetAndRotation(f, 15.0F, f2, 0.0F, f3, 0.0F));
        }

        return LayerDefinition.create(meshdefinition, 64, 32);
    }


    @Override
    public void setupAnim(T pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        for (ModelPart modelpart : this.tentacles) {
            modelpart.xRot = pAgeInTicks;
        }
    }

    @Override
    public ModelPart root() {
        return this.root;
    }
}
