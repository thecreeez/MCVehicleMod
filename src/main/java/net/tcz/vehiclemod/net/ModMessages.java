package net.tcz.vehiclemod.net;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;
import net.tcz.vehiclemod.Vehiclemod;
import net.tcz.vehiclemod.net.packet.ControlC2SPacket;
import net.tcz.vehiclemod.net.packet.InteractVehicleC2SPacket;
import net.tcz.vehiclemod.net.packet.SyncVehicleS2CPacket;

public class ModMessages {
    public static final Identifier CONTROL_VEHICLE = new Identifier(Vehiclemod.MOD_ID, "control_vehicle");
    public static final Identifier SYNC_VEHICLE_PROPERTIES = new Identifier(Vehiclemod.MOD_ID, "sync_vehicle");
    public static final Identifier INTERACT_VEHICLE = new Identifier(Vehiclemod.MOD_ID, "interact_vehicle");

    // Server packets
    public static void registerC2SPackets() {
        ServerPlayNetworking.registerGlobalReceiver(CONTROL_VEHICLE, ControlC2SPacket::receive);
        ServerPlayNetworking.registerGlobalReceiver(INTERACT_VEHICLE, InteractVehicleC2SPacket::receive);
    }

    // Client packets
    public static void registerS2CPackets() {
        ClientPlayNetworking.registerGlobalReceiver(SYNC_VEHICLE_PROPERTIES, SyncVehicleS2CPacket::receive);
    }
}
