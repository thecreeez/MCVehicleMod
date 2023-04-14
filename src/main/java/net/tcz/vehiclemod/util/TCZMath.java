package net.tcz.vehiclemod.util;

import net.minecraft.entity.MovementType;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.Vec3d;
import net.tcz.vehiclemod.entity.entities.VehicleEntity;

import java.util.ArrayList;
import java.util.List;

public class TCZMath {

    public static int i = 0;
    public static Vec3d getIntersection(Vec3d v1Start, Vec3d v1End, Vec3d v2Start, Vec3d v2End) {
        double m1 = (v1End.getZ() - v1Start.getZ()) / (v1End.getX() - v1Start.getX());
        double m2 = (v2End.getZ() - v2Start.getZ()) / (v2End.getX() - v2Start.getX());

        if (m1 == m2) {
            return null;
        }

        double b1 = v1Start.getZ() - m1 * v1Start.getX();
        double b2 = v2Start.getZ() - m2 * v2Start.getX();

        double x = (b2 - b1) / (m1 - m2);
        double z = m1 * x + b1;

        return new Vec3d(x, v1Start.getY(), z);
    }

    /*public static Vec3d getMoveVectorCar(ExampleCarEntity car, double distance) {
        Vec3d moveVec = new Vec3d(Math.sin(Math.toRadians(car.getYaw())), 0, Math.cos(Math.toRadians(car.getYaw())));
        distance = distance == 0 ? 0 : 0.0005;

        Vec3d intersectionPos = getIntersection(
                ExampleCarEntity.leftFrontWheelPosOffset,
                ExampleCarEntity.leftFrontWheelPosOffset.add(Math.cos(Math.toRadians(car.wheelYaw)),0, Math.sin(Math.toRadians(car.wheelYaw))),
                ExampleCarEntity.leftBackWheelPosOffset,
                ExampleCarEntity.leftBackWheelPosOffset.add(1,0, 0)
        );

        if (intersectionPos == null)
            return null;

        car.world.addParticle(ParticleTypes.SMOKE,
                car.getX() + intersectionPos.getX(),
                car.getY() + intersectionPos.getY(),
                car.getZ() + intersectionPos.getZ(),
                0f,0f,0f);

        double radius = ExampleCarEntity.leftFrontWheelPosOffset.subtract(intersectionPos).length();

        float angleCircle;
        if (car.wheelYaw < 0) {
            angleCircle = (float) Math.toRadians(Math.toDegrees(distance / radius) - 60);
        } else if (car.wheelYaw > 0) {
            angleCircle = (float) Math.toRadians(Math.toDegrees(-distance / radius) + 60);
        } else {
            angleCircle = (float) Math.toRadians(0);
        }

        // CHECKED - OK!

        Vec3d newPos = new Vec3d(
                intersectionPos.getX() + Math.cos(angleCircle) * radius - ExampleCarEntity.leftFrontWheelPosOffset.getX() * Math.cos(Math.toRadians(car.getYaw())),
                0,
                intersectionPos.getZ() + Math.sin(angleCircle) * radius - ExampleCarEntity.leftFrontWheelPosOffset.getZ() * Math.sin(Math.toRadians(car.getYaw()))
        );

        // NADO UCHEST CENTER SOOQA
        //
        //

        Vec3d centerPos = car.getPos().add(ExampleCarEntity.leftBackWheelPosOffset.add(-1.1,0,0));
        car.world.addParticle(ParticleTypes.CAMPFIRE_COSY_SMOKE,
                centerPos.getX(),
                centerPos.getY(),
                centerPos.getZ(),
                0f,0f,0f);

        System.out.println(newPos);

        float newRotation = (float) (car.getYaw() + Math.toDegrees(angleCircle));
        if (car.wheelYaw > 0) {
            newRotation -= 60 - 10;
        } else if (car.wheelYaw < 0) {
            newRotation += 60 + 10;
        }
        newRotation = newRotation % 360;

        System.out.println(newRotation);
        car.setYaw(newRotation);
        car.setBodyYaw(newRotation);
        car.setHeadYaw(newRotation);
        car.updatePositionAndAngles(car.getX(), car.getY(), car.getZ(), newRotation, 0);

        if (newPos.length() < 100)
            car.move(MovementType.SELF, newPos);
        //car.move(MovementType.SELF, newPos);
        //car.streamSelfAndPassengers().forEach(entity -> {
        //    for (Entity entity2 : car.getPassengerList()) {
        //        car.updatePassengerPosition(entity2, Entity::refreshPositionAfterTeleport);
        //    }
        //});
        //car.updatePositionAndAngles(car.getX(), car.getY(), car.getZ(), (float) (car.getYaw() + newAngle), 0);
        //System.out.println(newAngle);

        //return new Vec3d(Math.cos(newAngle),0,Math.sin(newAngle));

        return null;
    }*/

    public static List<Vec3d> surface(VehicleEntity vehicle) {
        List<Vec3d> vecs = new ArrayList<>();
        for (int i = 0; i < vehicle.getWheelsPos().size(); i++) {
            Vec3d wheelVecR = vehicle.getWheelsPos().get(i).rotateY((float) Math.toRadians(-vehicle.getYaw())).add(vehicle.getX(), vehicle.getY(), vehicle.getZ());
            vecs.add(wheelVecR);
        }

        return vecs;
    }
}