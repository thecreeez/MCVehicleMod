package net.tcz.vehiclemod.net.packet;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.tcz.vehiclemod.entity.entities.NivaVehicleEntity;
import net.tcz.vehiclemod.entity.entities.VehicleEntity;
import net.tcz.vehiclemod.net.InteractType;

public class InteractVehicleC2SPacket {

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

        InteractType type = buf.readEnumConstant(InteractType.class);

        switch (type) {
            case RADIO -> vehicle.interactRadioServer();
            case TRUNK -> {
                if (vehicle instanceof NivaVehicleEntity niva)
                    niva.setTrunkOpen(!niva.isTrunkOpen());
            }
        }
    }
}
