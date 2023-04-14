package net.tcz.vehiclemod.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;
import net.tcz.vehiclemod.Vehiclemod;
import net.tcz.vehiclemod.entity.ModEntities;
import net.tcz.vehiclemod.net.ModMessages;

@Environment(EnvType.CLIENT)
public class VehiclemodClient implements ClientModInitializer {
    public static final EntityModelLayer EXAMPLE_CAR_ROOT = new EntityModelLayer(new Identifier(Vehiclemod.MOD_ID, "example_car"), "root");

    public static final EntityModelLayer VEHICLE_ROOT = new EntityModelLayer(new Identifier(Vehiclemod.MOD_ID, "vehicle"), "root");

    @Override
    public void onInitializeClient() {
        ModKeyBindings.init();
        ModEntities.init();
        ModMessages.registerS2CPackets();
    }
}