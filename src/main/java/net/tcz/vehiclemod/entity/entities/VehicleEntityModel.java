package net.tcz.vehiclemod.entity.entities;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class VehicleEntityModel extends EntityModel<VehicleEntity> {
    public final ModelPart driverSit;
    public final ModelPart mainSuspension;

    public final ModelPart backSuspension;
    public final ModelPart wheelLeftBack;
    public final ModelPart wheelRightBack;

    public final ModelPart frontSuspension;
    public final ModelPart wheelLeftFront;
    public final ModelPart wheelRightFront;

    public VehicleEntityModel(ModelPart root) {
        this.driverSit = root.getChild("driver_sit");
        this.mainSuspension = root.getChild("main_suspension");

        this.backSuspension = this.mainSuspension.getChild("back_wheels");
        this.wheelLeftBack = this.backSuspension.getChild("wheel_left_back");
        this.wheelRightBack = this.backSuspension.getChild("wheel_right_back");

        this.frontSuspension = this.mainSuspension.getChild("front_wheels");
        this.wheelLeftFront = this.frontSuspension.getChild("wheel_left_front");
        this.wheelRightFront = this.frontSuspension.getChild("wheel_right_front");
    }
    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData driver_sit = modelPartData.addChild("driver_sit", ModelPartBuilder.create().uv(0, 0).cuboid(-5.0F, -1.625F, -7.0F, 12.0F, 4.0F, 10.0F, new Dilation(0.0F)), ModelTransform.pivot(-1.0F, 11.575F, 4.0F));

        ModelPartData driver_sit_back = driver_sit.addChild("driver_sit_back", ModelPartBuilder.create().uv(0, 15).cuboid(-5.0F, -14.2324F, -10.9914F, 12.0F, 17.0F, 4.0F, new Dilation(0.0F))
                .uv(54, 54).cuboid(-3.0F, -21.2324F, -10.9914F, 8.0F, 6.0F, 4.0F, new Dilation(0.0F))
                .uv(0, 2).cuboid(3.0F, -15.2324F, -8.9914F, 1.0F, 1.0F, 0.0F, new Dilation(0.0F))
                .uv(0, 0).cuboid(-2.0F, -15.2324F, -8.9914F, 1.0F, 1.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 14.0F, -0.0436F, 0.0F, 0.0F));

        ModelPartData main_suspension = modelPartData.addChild("main_suspension", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, 0.0F, -24.0F, 2.0F, 2.0F, 46.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 14.0F, 0.0F, 0.0F, 3.1416F, 0.0F));

        ModelPartData back_wheels = main_suspension.addChild("back_wheels", ModelPartBuilder.create().uv(0, 49).cuboid(-15.0F, 0.0F, -1.0F, 30.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 2.0F, -22.0F));

        ModelPartData wheel_left_back = back_wheels.addChild("wheel_left_back", ModelPartBuilder.create().uv(0, 54).cuboid(-5.075F, -5.0F, -4.0F, 5.0F, 10.0F, 8.0F, new Dilation(0.0F))
                .uv(73, 69).cuboid(-5.7F, 4.0F, -3.0F, 6.0F, 3.0F, 6.0F, new Dilation(0.0F))
                .uv(101, 24).cuboid(-5.725F, -4.0F, -6.75F, 6.0F, 8.0F, 3.0F, new Dilation(0.0F))
                .uv(73, 59).cuboid(-5.7F, -7.1F, -3.0F, 6.0F, 3.0F, 6.0F, new Dilation(0.0F))
                .uv(19, 101).cuboid(-5.725F, -4.0F, 3.75F, 6.0F, 8.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(-14.95F, 1.0F, 0.0F));

        ModelPartData cube_r1 = wheel_left_back.addChild("cube_r1", ModelPartBuilder.create().uv(46, 85).cuboid(-3.0F, -2.25F, -0.75F, 6.0F, 3.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(-2.7F, 6.0F, 3.0F, 0.6981F, 0.0F, 0.0F));

        ModelPartData cube_r2 = wheel_left_back.addChild("cube_r2", ModelPartBuilder.create().uv(69, 88).cuboid(-3.0F, 8.75F, 7.25F, 6.0F, 3.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(-2.7F, -18.5F, 3.0F, -0.6981F, 0.0F, 0.0F));

        ModelPartData cube_r3 = wheel_left_back.addChild("cube_r3", ModelPartBuilder.create().uv(91, 15).cuboid(-3.0F, 8.75F, -12.25F, 6.0F, 3.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(-2.7F, -18.5F, -3.0F, 0.6981F, 0.0F, 0.0F));

        ModelPartData cube_r4 = wheel_left_back.addChild("cube_r4", ModelPartBuilder.create().uv(0, 92).cuboid(-3.0F, -2.25F, -4.25F, 6.0F, 3.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(-2.7F, 6.0F, -3.0F, -0.6981F, 0.0F, 0.0F));

        ModelPartData wheel_right_back = back_wheels.addChild("wheel_right_back", ModelPartBuilder.create().uv(51, 5).cuboid(0.425F, -5.0F, -4.0F, 5.0F, 10.0F, 8.0F, new Dilation(0.0F))
                .uv(72, 18).cuboid(-0.2F, 4.0F, -3.0F, 6.0F, 3.0F, 6.0F, new Dilation(0.0F))
                .uv(65, 97).cuboid(-0.225F, -4.0F, -6.75F, 6.0F, 8.0F, 3.0F, new Dilation(0.0F))
                .uv(54, 65).cuboid(-0.2F, -7.1F, -3.0F, 6.0F, 3.0F, 6.0F, new Dilation(0.0F))
                .uv(46, 94).cuboid(-0.225F, -4.0F, 3.75F, 6.0F, 8.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(14.6F, 1.0F, 0.0F));

        ModelPartData cube_r5 = wheel_right_back.addChild("cube_r5", ModelPartBuilder.create().uv(0, 37).cuboid(-3.0F, -2.25F, -0.75F, 6.0F, 3.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(2.8F, 6.0F, 3.0F, 0.6981F, 0.0F, 0.0F));

        ModelPartData cube_r6 = wheel_right_back.addChild("cube_r6", ModelPartBuilder.create().uv(23, 37).cuboid(-3.0F, 8.75F, 7.25F, 6.0F, 3.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(2.8F, -18.5F, 3.0F, -0.6981F, 0.0F, 0.0F));

        ModelPartData cube_r7 = wheel_right_back.addChild("cube_r7", ModelPartBuilder.create().uv(75, 49).cuboid(-3.0F, 8.75F, -12.25F, 6.0F, 3.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(2.8F, -18.5F, -3.0F, 0.6981F, 0.0F, 0.0F));

        ModelPartData cube_r8 = wheel_right_back.addChild("cube_r8", ModelPartBuilder.create().uv(78, 28).cuboid(-3.0F, -2.25F, -4.25F, 6.0F, 3.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(2.8F, 6.0F, -3.0F, -0.6981F, 0.0F, 0.0F));

        ModelPartData front_wheels = main_suspension.addChild("front_wheels", ModelPartBuilder.create().uv(51, 0).cuboid(-15.0F, 0.0F, -1.0F, 30.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 2.0F, 20.0F));

        ModelPartData wheel_right_front = front_wheels.addChild("wheel_right_front", ModelPartBuilder.create().uv(51, 24).cuboid(0.425F, -5.0F, -4.0F, 5.0F, 10.0F, 8.0F, new Dilation(0.0F))
                .uv(25, 73).cuboid(-0.2F, 4.0F, -3.0F, 6.0F, 3.0F, 6.0F, new Dilation(0.0F))
                .uv(0, 101).cuboid(-0.225F, -4.0F, -6.75F, 6.0F, 8.0F, 3.0F, new Dilation(0.0F))
                .uv(0, 73).cuboid(-0.2F, -7.1F, -3.0F, 6.0F, 3.0F, 6.0F, new Dilation(0.0F))
                .uv(98, 73).cuboid(-0.225F, -4.0F, 3.75F, 6.0F, 8.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(14.6F, 1.0F, 0.0F));

        ModelPartData cube_r9 = wheel_right_front.addChild("cube_r9", ModelPartBuilder.create().uv(78, 37).cuboid(-3.0F, -2.25F, -0.75F, 6.0F, 3.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(2.8F, 6.0F, 3.0F, 0.6981F, 0.0F, 0.0F));

        ModelPartData cube_r10 = wheel_right_front.addChild("cube_r10", ModelPartBuilder.create().uv(75, 79).cuboid(-3.0F, 8.75F, 7.25F, 6.0F, 3.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(2.8F, -18.5F, 3.0F, -0.6981F, 0.0F, 0.0F));

        ModelPartData cube_r11 = wheel_right_front.addChild("cube_r11", ModelPartBuilder.create().uv(0, 83).cuboid(-3.0F, 8.75F, -12.25F, 6.0F, 3.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(2.8F, -18.5F, -3.0F, 0.6981F, 0.0F, 0.0F));

        ModelPartData cube_r12 = wheel_right_front.addChild("cube_r12", ModelPartBuilder.create().uv(23, 83).cuboid(-3.0F, -2.25F, -4.25F, 6.0F, 3.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(2.8F, 6.0F, -3.0F, -0.6981F, 0.0F, 0.0F));

        ModelPartData wheel_left_front = front_wheels.addChild("wheel_left_front", ModelPartBuilder.create().uv(27, 54).cuboid(-5.075F, -5.0F, -4.0F, 5.0F, 10.0F, 8.0F, new Dilation(0.0F))
                .uv(78, 5).cuboid(-5.7F, 4.0F, -3.0F, 6.0F, 3.0F, 6.0F, new Dilation(0.0F))
                .uv(84, 101).cuboid(-5.725F, -4.0F, -6.75F, 6.0F, 8.0F, 3.0F, new Dilation(0.0F))
                .uv(50, 75).cuboid(-5.7F, -7.1F, -3.0F, 6.0F, 3.0F, 6.0F, new Dilation(0.0F))
                .uv(101, 36).cuboid(-5.725F, -4.0F, 3.75F, 6.0F, 8.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(-14.95F, 1.0F, 0.0F));

        ModelPartData cube_r13 = wheel_left_front.addChild("cube_r13", ModelPartBuilder.create().uv(23, 92).cuboid(-3.0F, -2.25F, -0.75F, 6.0F, 3.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(-2.7F, 6.0F, 3.0F, 0.6981F, 0.0F, 0.0F));

        ModelPartData cube_r14 = wheel_left_front.addChild("cube_r14", ModelPartBuilder.create().uv(87, 92).cuboid(-3.0F, 8.75F, 7.25F, 6.0F, 3.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(-2.7F, -18.5F, 3.0F, -0.6981F, 0.0F, 0.0F));

        ModelPartData cube_r15 = wheel_left_front.addChild("cube_r15", ModelPartBuilder.create().uv(93, 53).cuboid(-3.0F, 8.75F, -12.25F, 6.0F, 3.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(-2.7F, -18.5F, -3.0F, 0.6981F, 0.0F, 0.0F));

        ModelPartData cube_r16 = wheel_left_front.addChild("cube_r16", ModelPartBuilder.create().uv(93, 64).cuboid(-3.0F, -2.25F, -4.25F, 6.0F, 3.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(-2.7F, 6.0F, -3.0F, -0.6981F, 0.0F, 0.0F));
        return TexturedModelData.of(modelData, 128, 128);
    }

    public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
        driverSit.render(matrices, vertices, light, overlay, red, green, blue, alpha);
        mainSuspension.render(matrices, vertices, light, overlay, red, green, blue, alpha);
    }

    public void setAngles(VehicleEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {

    }
}
