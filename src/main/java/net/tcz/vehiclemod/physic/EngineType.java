package net.tcz.vehiclemod.physic;

import net.minecraft.item.Item;
import net.minecraft.item.Items;

public enum EngineType {
    DEFAULT(Items.STICK, 1);

    private final Item item;
    private final int power;


    EngineType(Item item, int power) {
        this.item = item;
        this.power = power;
    }

    public static WheelType getByItem(Item item) {
        WheelType out = null;

        for (WheelType wheelCandidate : WheelType.values()) {
            if (wheelCandidate.getItem().equals(item))
                out = wheelCandidate;
        }

        return out;
    }

    public Item getItem() {
        return item;
    }

    public int getPower() {
        return power;
    }
}
