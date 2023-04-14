package net.tcz.vehiclemod.entity.entities;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.passive.AbstractDonkeyEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.tcz.vehiclemod.item.ModItems;
import net.tcz.vehiclemod.net.InteractType;
import net.tcz.vehiclemod.net.ModMessages;
import net.tcz.vehiclemod.sound.ModSounds;

public class NivaVehicleEntity extends StorageVehicleEntity {

    protected boolean isTrunkOpen = false;
    protected float trunkAngle = 0f;

    public NivaVehicleEntity(EntityType<? extends Entity> type, World world) {
        super(type, world);

        for (int i = 0; i < 4; i++) {
            wheels.add(new ItemStack(ModItems.WHEEL_ITEM));
        }

        this.maxSpeed = 0.6d;
        this.acceleration = 0.00625d;
        this.maxPassengers = 4;

        wheelsPos.add(new Vec3d(0.95,1.4,-1.75));
        wheelsPos.add(new Vec3d(-1,1.4,-1.75));
        wheelsPos.add(new Vec3d(0.95,1.4,1.815));
        wheelsPos.add(new Vec3d(-1,1.4,1.815));
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
    }

    public boolean isTrunkOpen() {
        return isTrunkOpen;
    }

    public void setTrunkOpen(boolean isOpen) {
        isTrunkOpen = isOpen;
    }

    @Override
    protected ActionResult interactIn(PlayerEntity player, Vec3d hitPos, Hand hand) {
        if (!this.isLogicalSideForUpdatingMovement()) {
            float playerYaw = -(getYaw() - player.getHeadYaw() % 360);
            float playerPitch = -(getPitch() - player.getPitch());

            if (playerYaw < this.radioAngle.get(0).getLeft())
                return ActionResult.FAIL;

            if (playerYaw > this.radioAngle.get(1).getLeft())
                return ActionResult.FAIL;

            if (playerPitch < this.radioAngle.get(0).getRight())
                return ActionResult.FAIL;

            if (playerPitch > this.radioAngle.get(1).getRight())
                return ActionResult.FAIL;

            interactRadio();
            return ActionResult.SUCCESS;
        }

        return ActionResult.SUCCESS;
    }

    @Override
    protected ActionResult interactOut(PlayerEntity player, Vec3d hitPos, Hand hand) {
        if (player.isSneaking()) {
            super.openInventory(player);

            if (!this.isLogicalSideForUpdatingMovement()) {
                PacketByteBuf buf = PacketByteBufs.create();
                buf.writeEnumConstant(InteractType.TRUNK);
                ClientPlayNetworking.send(ModMessages.INTERACT_VEHICLE, buf);
            }
            this.setTrunkOpen(true);
            player.world.playSound(null, new BlockPos(this.getPos()), ModSounds.OPEN_HOOD, SoundCategory.PLAYERS);
            return ActionResult.SUCCESS;
        }

        return super.interactOut(player, hitPos, hand);
    }

    @Override
    public void onClose(PlayerEntity player) {
        player.world.playSound(null, new BlockPos(this.getPos()), ModSounds.CLOSE_HOOD, SoundCategory.PLAYERS);

        this.setTrunkOpen(false);
        super.onClose(player);
    }

    @Override
    public void onOpen(PlayerEntity player) {
        super.onOpen(player);
    }

    @Override
    protected void sendClientsProperties() {
        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeInt(getId());
        buf.writeFloat((float) Math.floor(wheelYaw * 10) / 10);
        buf.writeDouble(speed);
        buf.writeBoolean(radioPlaying);
        buf.writeBoolean(isTrunkOpen);

        for (ServerPlayerEntity player : ((ServerWorld) getWorld()).getPlayers()) {
            if (player.distanceTo(this) < 100) {
                ServerPlayNetworking.send(player, ModMessages.SYNC_VEHICLE_PROPERTIES, buf);
            }
        }
    }
}
