package bee.insanity.entity.client;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class TheWatcherModel extends EntityModel<TheWatcherRenderstate> {

    public final ModelPart body;
    public final ModelPart head;
    public final ModelPart right_arm;
    public final ModelPart left_arm;
    public final ModelPart right_leg;
    public final ModelPart left_leg;

    protected TheWatcherModel(ModelPart root) {
        super(root);
        this.body = root.getChild("body");
        this.head = root.getChild("head");
        this.right_arm = root.getChild("right_arm");
        this.left_arm = root.getChild("right_arm");
        this.right_leg = root.getChild("right_leg");
        this.left_leg = root.getChild("right_leg");
    }


    public static LayerDefinition createMesh() {
        CubeDeformation scale = CubeDeformation.NONE;
        MeshDefinition mesh = HumanoidModel.createMesh(scale, 0.0F);
        PartDefinition root = mesh.getRoot();
        PartDefinition leftArm = root.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(32, 48).addBox(-1.0F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F, scale), PartPose.offset(5.0F, 2.0F, 0.0F));
        PartDefinition rightArm = root.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(40, 16).addBox(-2.0F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F, scale), PartPose.offset(-5.0F, 2.0F, 0.0F));
        leftArm.addOrReplaceChild("left_sleeve", CubeListBuilder.create().texOffs(48, 48).addBox(-1.0F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F, scale.extend(0.25F)), PartPose.ZERO);
        rightArm.addOrReplaceChild("right_sleeve", CubeListBuilder.create().texOffs(40, 32).addBox(-2.0F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F, scale.extend(0.25F)), PartPose.ZERO);

        PartDefinition leftLeg = root.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(16, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, scale), PartPose.offset(1.9F, 12.0F, 0.0F));
        PartDefinition rightLeg = root.getChild("right_leg");
        leftLeg.addOrReplaceChild("left_pants", CubeListBuilder.create().texOffs(0, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, scale.extend(0.25F)), PartPose.ZERO);
        rightLeg.addOrReplaceChild("right_pants", CubeListBuilder.create().texOffs(0, 32).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, scale.extend(0.25F)), PartPose.ZERO);
        PartDefinition body = root.getChild("body");
        body.addOrReplaceChild("jacket", CubeListBuilder.create().texOffs(16, 32).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, scale.extend(0.25F)), PartPose.ZERO);
        return LayerDefinition.create(mesh, 64, 64);
    }

    @Override
    public void setupAnim(TheWatcherRenderstate state) {
        super.setupAnim(state);
        this.head.xRot = (state.xRot * ((float)Math.PI / 180F));
        this.head.yRot = state.yRot * ((float)Math.PI / 180F);
    }
}
