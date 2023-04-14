package net.tcz.vehiclemod.net.packet;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.tcz.vehiclemod.entity.entities.VehicleEntity;

public class ControlC2SPacket {
    // Only on server
    public static void receive(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler,
                               PacketByteBuf buf, PacketSender responseSender) {

        if (player == null) {
            return;
        }

        if (!player.hasVehicle()) {
            return;
        }

        if (!(player.getVehicle() instanceof VehicleEntity vehicle)) {
            return;
        }

        if (!player.getVehicle().getFirstPassenger().getUuid().equals(player.getUuid())) {
            return;
        }

        int[] axis = buf.readIntArray();

        vehicle.setAxis(axis[0], axis[1]);
    }
}
