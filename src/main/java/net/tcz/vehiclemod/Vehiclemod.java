package net.tcz.vehiclemod;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.tcz.vehiclemod.entity.ModEntities;
import net.tcz.vehiclemod.item.ModItems;
import net.tcz.vehiclemod.net.ModMessages;
import net.tcz.vehiclemod.sound.ModSounds;

public class Vehiclemod implements ModInitializer {
    public static final String MOD_ID = "tcz_vehicle";

    @Override
    public void onInitialize() {
        ModItems.init();
        ModMessages.registerC2SPackets();
        ModSounds.init();
    }
}
