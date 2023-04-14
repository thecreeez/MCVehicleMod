package net.tcz.vehiclemod.entity;

import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.tcz.vehiclemod.Vehiclemod;
import net.tcz.vehiclemod.client.VehiclemodClient;
import net.tcz.vehiclemod.entity.entities.*;
import net.tcz.vehiclemod.entity.models.NivaVehicleModel;

public class ModEntities {
    public static final EntityType<NivaVehicleEntity> NIVA = Registry.register(
            Registries.ENTITY_TYPE,
            new Identifier(Vehiclemod.MOD_ID, "niva"),
            FabricEntityTypeBuilder.create(SpawnGroup.MISC, NivaVehicleEntity::new).dimensions(EntityDimensions.fixed(3f,3f)).build()
    );

    public static void init() {
        EntityRendererRegistry.register(ModEntities.NIVA, VehicleEntityRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(VehiclemodClient.VEHICLE_ROOT, NivaVehicleModel::getTexturedModelData);
    }
}
