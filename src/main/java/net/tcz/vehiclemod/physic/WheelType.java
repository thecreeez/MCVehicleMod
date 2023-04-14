package net.tcz.vehiclemod.physic;

import net.minecraft.item.Item;
import net.tcz.vehiclemod.item.ModItems;

public enum WheelType {
    DEFAULT(ModItems.WHEEL_ITEM, 7d, 0.5f);

    private final Item item;
    private final double radius;
    private final float friction;

    WheelType(Item wheelItem, double radius, float friction) {
        this.radius = radius;
        this.friction = friction;
        this.item = wheelItem;
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

    public double getRadius() {
        return radius;
    }

    public float getFriction() {
        return friction;
    }
}
