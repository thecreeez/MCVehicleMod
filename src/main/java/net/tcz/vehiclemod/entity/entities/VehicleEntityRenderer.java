package net.tcz.vehiclemod.entity.entities;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.math.Vec3d;
import net.tcz.vehiclemod.Vehiclemod;
import net.tcz.vehiclemod.client.VehiclemodClient;
import net.tcz.vehiclemod.entity.models.NivaVehicleModel;

import java.util.ArrayList;

public class VehicleEntityRenderer extends EntityRenderer<VehicleEntity> {

    public NivaVehicleModel model;
    public VehicleEntityRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);

        model = new NivaVehicleModel(ctx.getPart(VehiclemodClient.VEHICLE_ROOT));
    }

    @Override
    public void render(VehicleEntity entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        matrices.push();
        matrices.translate(0,1.85,0);

        float rightYaw = yaw;

        if (Math.abs(rightYaw - entity.getYaw()) > 20)
            rightYaw = entity.serverYaw;

        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(360f - rightYaw));
        matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(180f));

        //matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(45f));

        if (entity instanceof NivaVehicleEntity niva) {

            if (niva.isTrunkOpen())
                niva.trunkAngle = MathHelper.lerpAngleDegrees(0.15f, niva.trunkAngle, 45);
            else
                niva.trunkAngle = MathHelper.lerpAngleDegrees(0.15f, niva.trunkAngle, 0);

            model.trunk.setAngles((float) Math.toRadians(niva.trunkAngle), 0, 0);
        }

        model.steeringWheel.setAngles(0f,0f,(float) Math.toRadians(-entity.wheelYaw * 2));

        model.render(matrices, vertexConsumers.getBuffer(model.getLayer(getTexture(entity))), light, OverlayTexture.DEFAULT_UV, 1f,1f,1f,1f);

        ArrayList<ItemStack> wheels = entity.getWheels();
        ArrayList<Vec3d> vec3ds = entity.getWheelsPos();

        renderWheels(entity, matrices, wheels, vec3ds, light, vertexConsumers);

        matrices.pop();
        super.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
    }

    @Override
    public Identifier getTexture(VehicleEntity entity) {
        return new Identifier(Vehiclemod.MOD_ID, "textures/entity/cars/niva.png");
    }

    private void renderWheels(VehicleEntity car, MatrixStack matrices, ArrayList<ItemStack> wheels, ArrayList<Vec3d> wheelsPos, int light, VertexConsumerProvider vertexConsumers) {
        double wheelRadius = 7f;
        double circumference = wheelRadius * 2 / 16 * Math.PI;
        double distance = new Vec3d(
                car.getX() - car.prevX,
                0d,
                car.getZ() - car.prevZ
        ).length();

        if (distance != 0) {
            if (car.speed > 0)
                car.wheelAngle -= Math.toDegrees(distance / circumference);
            else
                car.wheelAngle += Math.toDegrees(distance / circumference);
        }

        for (int i = 0; i < wheels.size(); i++) {
            matrices.translate(wheelsPos.get(i).getX(), wheelsPos.get(i).getY(), wheelsPos.get(i).getZ());

            if (i % 2 == 1) {
                matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(180f));

                if (i < 2)
                    matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(car.wheelYaw));
                matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(car.wheelAngle));
            } else {
                if (i < 2)
                    matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(car.wheelYaw));
                matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(-car.wheelAngle));
            }

            MinecraftClient.getInstance().getItemRenderer().renderItem(
                    wheels.get(i),
                    ModelTransformation.Mode.FIXED,
                    light,
                    OverlayTexture.DEFAULT_UV,
                    matrices,
                    vertexConsumers,
                    0
            );

            if (i % 2 == 1) {
                matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(-car.wheelAngle));

                if (i < 2)
                    matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(-car.wheelYaw));

                matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(-180f));
            } else {
                matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(car.wheelAngle));

                if (i < 2)
                    matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(-car.wheelYaw));
            }

            matrices.translate(-wheelsPos.get(i).getX(), -wheelsPos.get(i).getY(), -wheelsPos.get(i).getZ());
        }
    }
}
