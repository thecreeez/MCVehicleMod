package net.tcz.vehiclemod.physic;

import net.minecraft.util.math.Vec3d;
import net.tcz.vehiclemod.entity.entities.VehicleEntity;
import net.tcz.vehiclemod.util.TCZMath;

import java.util.ArrayList;
import java.util.List;

public class VehicleSurface {
    protected VehicleEntity vehicle;
    protected List<Vec3d> points;

    public VehicleSurface(List<Vec3d> points, VehicleEntity entity) {
        this.points = points;
        this.vehicle = entity;
    }

    /**
     * To-do:
     *      Сделать вычисление точек соприкосновения с землей по радиусу колес и транспорту
     * @param vehicle
     * @return
     */
    public static List<Vec3d> getVehiclePoints(VehicleEntity vehicle, double wheelRadius) {
        List<Vec3d> wheelPoints = TCZMath.surface(vehicle);

        return null;
    }
}
