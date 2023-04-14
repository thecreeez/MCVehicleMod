package net.tcz.vehiclemod.client;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.MinecraftClient;
import net.tcz.vehiclemod.entity.entities.VehicleEntity;
import net.tcz.vehiclemod.net.ModMessages;

public class ModKeyBindings {
    public static int verticalAxis = 0;
    public static int horizontalAxis = 0;

    public static int serverVerticalAxis = 0;
    public static int serverHorizontalAxis = 0;

    public static void init() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player == null) {
                return;
            }

            if (!client.player.hasVehicle()) {
                if (verticalAxis != 0)
                    verticalAxis = 0;

                if (horizontalAxis != 0)
                    horizontalAxis = 0;
                return;
            }

            if (!(client.player.getVehicle() instanceof VehicleEntity car)) {
                return;
            }

            if (!client.player.getVehicle().getFirstPassenger().getUuid().equals(client.player.getUuid())) {
                return;
            }

            boolean forwardKey = MinecraftClient.getInstance().options.forwardKey.isPressed();
            boolean backKey = MinecraftClient.getInstance().options.backKey.isPressed();
            boolean leftKey = MinecraftClient.getInstance().options.leftKey.isPressed();
            boolean rightKey = MinecraftClient.getInstance().options.rightKey.isPressed();

            verticalAxis = 0;

            if (forwardKey) {
                verticalAxis++;
            }

            if (backKey) {
                verticalAxis--;
            }

            horizontalAxis = 0;

            if (leftKey) {
                horizontalAxis--;
            }

            if (rightKey) {
                horizontalAxis++;
            }

            if (serverVerticalAxis != verticalAxis || serverHorizontalAxis != horizontalAxis) {
                int[] axis = new int[2];

                serverVerticalAxis = verticalAxis;
                serverHorizontalAxis = horizontalAxis;

                axis[0] = verticalAxis;
                axis[1] = horizontalAxis;

                ClientPlayNetworking.send(ModMessages.CONTROL_VEHICLE, PacketByteBufs.create().writeIntArray(axis));
            }
        });
    }
}