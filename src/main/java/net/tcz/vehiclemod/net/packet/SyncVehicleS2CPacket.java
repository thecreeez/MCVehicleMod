package net.tcz.vehiclemod.net.packet;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.entity.Entity;
import net.minecraft.network.PacketByteBuf;
import net.tcz.vehiclemod.entity.entities.NivaVehicleEntity;
import net.tcz.vehiclemod.entity.entities.VehicleEntity;

public class SyncVehicleS2CPacket {

    public static void receive(MinecraftClient client, ClientPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender) {
        if (client.world == null)
            return;

        Entity entity = client.world.getEntityById(buf.readInt());

        if (entity == null)
            return;

        if (!(entity instanceof VehicleEntity vehicle))
            return;

        vehicle.wheelYaw = buf.readFloat();
        vehicle.speed = buf.readDouble();

        boolean newRadioPlaying = buf.readBoolean();
        vehicle.setRadioStatus(newRadioPlaying);

        if (vehicle instanceof NivaVehicleEntity niva) {
            niva.setTrunkOpen(buf.readBoolean());
        }
    }
}
