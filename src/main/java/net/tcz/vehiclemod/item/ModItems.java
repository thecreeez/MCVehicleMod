package net.tcz.vehiclemod.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.tcz.vehiclemod.Vehiclemod;

public class ModItems {
    public static final ItemGroup ITEM_GROUP = FabricItemGroup.builder(new Identifier(Vehiclemod.MOD_ID, "main"))
            .icon(() -> new ItemStack(ModItems.WHEEL_ITEM, 1))
            .build();
    public static final Item WHEEL_ITEM = new Item(new FabricItemSettings());
    public static final Item CHAIR = new Item(new FabricItemSettings());

    public static void init() {
        Registry.register(Registries.ITEM, new Identifier(Vehiclemod.MOD_ID, "default_wheel"), WHEEL_ITEM);
        Registry.register(Registries.ITEM, new Identifier(Vehiclemod.MOD_ID, "default_chair"), CHAIR);

        ItemGroupEvents.modifyEntriesEvent(ITEM_GROUP).register(content -> {
            content.add(WHEEL_ITEM);
            content.add(CHAIR);
        });
    }
}
