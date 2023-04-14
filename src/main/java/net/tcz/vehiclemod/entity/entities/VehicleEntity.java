package net.tcz.vehiclemod.entity.entities;

import com.ibm.icu.impl.number.parse.InfinityMatcher;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.ParticlesMode;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.sound.SoundInstance;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.particle.ParticleType;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Pair;
import net.minecraft.util.math.*;
import net.minecraft.world.World;
import net.tcz.vehiclemod.net.InteractType;
import net.tcz.vehiclemod.net.ModMessages;
import net.tcz.vehiclemod.sound.ModSounds;
import net.tcz.vehiclemod.sound.instance.NivaSoundMovingInstance;
import net.tcz.vehiclemod.sound.instance.RadioSoundMovingInstance;
import net.tcz.vehiclemod.util.TCZMath;

import java.util.ArrayList;
import java.util.List;

public class VehicleEntity extends Entity {

    /**
     * CONTROLS
     */
    @Environment(EnvType.SERVER)
    protected int verticalAxis;

    @Environment(EnvType.SERVER)
    protected int horizontalAxis;

    /**
     * SLIGHT SYSTEM
     */
    protected double serverX;
    protected double serverY;
    protected double serverZ;

    protected float serverYaw;
    protected float serverPitch;

    protected int ticksFromUpdate = 0;

    /**
     * MOVING HEAD SHIT
     */

    // Шобы на эту дельту передвигать головы игорьков
    protected float deltaYaw;
    protected float deltaPitch;

    /**
     * VEHICLE PROPERTIES
     */

    // speed
    protected double acceleration;
    public double speed;
    public double maxSpeed;

    // wheels
    public float wheelYaw;
    public float maxWheelYaw;

    // wheel rotation
    public float wheelAngle;

    protected int maxPassengers;

    /**
     * SOUNDS
     */
    public boolean radioPlaying;
    protected SoundInstance engineInstance;
    protected RadioSoundMovingInstance radioInstance;

    protected ArrayList<Pair<Float, Float>> radioAngle;



    protected ArrayList<Vec3d> wheelsPos = new ArrayList<>();
    protected ArrayList<ItemStack> wheels = new ArrayList<>();

    public VehicleEntity(EntityType<? extends Entity> type, World world) {
        super(type, world);

        this.speed = 0;
        this.maxSpeed = 0.5d;
        this.acceleration = 0.0125;

        this.noClip = false;

        this.wheelYaw = 0;
        this.maxWheelYaw = 20;

        this.radioAngle = new ArrayList<>();
        this.radioPlaying = false;

        this.stepHeight = 1.5f;

        this.radioAngle.add(new Pair<>(33.1402f, 27.1494f));
        this.radioAngle.add(new Pair<>(52.4903f, 41.2495f));

        setBoundingBox(Box.of(new Vec3d(0,0,0), 3f,3f,3f));
    }

    @Override
    protected void addPassenger(Entity passenger) {
        super.addPassenger(passenger);
    }

    @Override
    protected void removePassenger(Entity passenger) {
        super.removePassenger(passenger);
    }

    @Override
    protected void initDataTracker() {

    }

    @Override
    protected void readCustomDataFromNbt(NbtCompound nbt) {

    }

    @Override
    protected void writeCustomDataToNbt(NbtCompound nbt) {

    }

    public ArrayList<ItemStack> getWheels() {
        return wheels;
    }

    public ArrayList<Vec3d> getWheelsPos() {
        return wheelsPos;
    }



    @Override
    public ActionResult interactAt(PlayerEntity player, Vec3d hitPos, Hand hand) {
        if (getPassengerList().contains(player)) {
            return interactIn(player,hitPos,hand);
        } else {
            return interactOut(player, hitPos, hand);
        }
    }

    protected ActionResult interactIn(PlayerEntity player, Vec3d hitPos, Hand hand) {
        return ActionResult.SUCCESS;
    }

    protected ActionResult interactOut(PlayerEntity player, Vec3d hitPos, Hand hand) {
        return ActionResult.SUCCESS;
    }

    public void interactRadio() {
        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeEnumConstant(InteractType.RADIO);

        ClientPlayNetworking.send(ModMessages.INTERACT_VEHICLE, buf);
    }

    public void interactRadioServer() {
        radioPlaying = !radioPlaying;
    }

    public void setRadioStatus(boolean status) {
        radioPlaying = status;
    }

    @Override
    public void updatePassengerPosition(Entity passenger) {
        if (!this.hasPassenger(passenger)) {
            return;
        }

        int index = getPassengerList().indexOf(passenger);

        switch (index) {
            case 0 -> passenger.setPosition(new Vec3d(this.getX(), this.getY(), this.getZ()).add(new Vec3d(0.6,0.65,0.3).rotateY((float) -Math.toRadians(getYaw()))));
            case 1 -> passenger.setPosition(new Vec3d(this.getX(), this.getY(), this.getZ()).add(new Vec3d(-0.7,0.65,0.3).rotateY((float) -Math.toRadians(getYaw()))));
        }

        //passenger.setYaw(passenger.getYaw() + deltaYaw);
        passenger.setBodyYaw(getYaw());
        //passenger.setHeadYaw(passenger.getYaw() + deltaYaw);

        deltaYaw = 0;
    }

    @Override
    public void onSpawnPacket(EntitySpawnS2CPacket packet) {
        super.onSpawnPacket(packet);
    }

    @Override
    public void tick() {
        super.tick();
        this.updatePositionAndRotation();
        this.tickControl();

        if (this.isLogicalSideForUpdatingMovement()) {
            if (!(this.getFirstPassenger() instanceof PlayerEntity)) {
                this.verticalAxis = 0;
                this.horizontalAxis = 0;
            }
        } else {
            if (!this.isAlive()) {
                return;
            }

            TCZMath.surface(this);

            /**
             * car.getX() + intersectionPos.getX(),
             *                 car.getY() + intersectionPos.getY(),
             *                 car.getZ() + intersectionPos.getZ(),
             *                 0f,0f,0f);
             */

            if (engineInstance == null) {
                engineInstance = new NivaSoundMovingInstance(this);
                MinecraftClient.getInstance().getSoundManager().play(engineInstance);
            }

            if (!MinecraftClient.getInstance().getSoundManager().isPlaying(engineInstance)) {
                MinecraftClient.getInstance().getSoundManager().play(engineInstance);
            }


            if (radioInstance == null || radioInstance.isDone()) {
                radioInstance = new RadioSoundMovingInstance(this);
            }

            if (!MinecraftClient.getInstance().getSoundManager().isPlaying(radioInstance) && radioPlaying) {
                MinecraftClient.getInstance().getSoundManager().play(radioInstance);
            }
        }
    }

    private void updatePositionAndRotation() {
        if (this.isLogicalSideForUpdatingMovement()) {
            this.ticksFromUpdate = 0;
            this.updateTrackedPosition(this.getX(), this.getY(), this.getZ());
        }
        if (this.ticksFromUpdate <= 0) {
            return;
        }

        double g = MathHelper.wrapDegrees(this.serverYaw - (double)this.getYaw());

        deltaYaw = (float)g / (float) this.ticksFromUpdate;
        this.setYaw(this.getYaw() + (float)g / (float)this.ticksFromUpdate);
        this.setPitch(this.getPitch() + (float)(this.serverPitch - (double)this.getPitch()) / (float)this.ticksFromUpdate);

        double newX = this.getX() + (serverX - this.getX()) / (double)this.ticksFromUpdate;
        double newY = this.getY() + (serverY - this.getY()) / (double)this.ticksFromUpdate;
        double newZ = this.getZ() + (serverZ - this.getZ()) / (double)this.ticksFromUpdate;
        --this.ticksFromUpdate;

        this.setPosition(newX, newY, newZ);
        this.setRotation(this.getYaw(), this.getPitch());
    }

    @Override
    public void updateTrackedPositionAndAngles(double x, double y, double z, float yaw, float pitch, int interpolationSteps, boolean interpolate) {
        this.serverX = x;
        this.serverY = y;
        this.serverZ = z;

        this.serverYaw = yaw;
        this.serverPitch = pitch;

        this.ticksFromUpdate = 5;
    }

    public void tickControl() {
        if (isLogicalSideForUpdatingMovement()) {
            if (verticalAxis == 1) {
                speed += acceleration;
            } else if (verticalAxis == -1) {
                speed -= acceleration / 2;
            } else {
                if (speed != 0) {
                    speed = MathHelper.lerp(0.03, speed, 0);

                    if (Math.abs(speed) < 0.04) {
                        speed = 0;
                    }
                }
            }

            if (horizontalAxis == 1) {
                wheelYaw = (float) MathHelper.lerp(0.25, wheelYaw, maxWheelYaw);
            } else if (horizontalAxis == -1) {
                wheelYaw = (float) MathHelper.lerp(0.25, wheelYaw, -maxWheelYaw);
            } else {
                wheelYaw = (float) MathHelper.lerp(0.25, wheelYaw, 0);
            }

            if (Math.abs(speed) > maxSpeed) {
                if (speed > 0)
                    speed = maxSpeed;
                else
                    speed = -maxSpeed;
            }

            Vec3d moveVec = new Vec3d(0, -0.4d,0);

            if (speed != 0) {
                float carRotation = (float) ((wheelYaw / 3.1415) * speed);

                deltaPitch = 0;

                moveVec = moveVec.add(
                        -speed * Math.sin(Math.toRadians(getBodyYaw())),
                        0,
                        speed * Math.cos(Math.toRadians(getBodyYaw()))
                );

                setRotation(getYaw() + carRotation, getPitch());
            }

            move(MovementType.SELF, moveVec);
            sendClientsProperties();
        }

    }

    protected void sendClientsProperties() {
        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeInt(getId());
        buf.writeFloat((float) Math.floor(wheelYaw * 10) / 10);
        buf.writeDouble(speed);
        buf.writeBoolean(radioPlaying);

        for (ServerPlayerEntity player : ((ServerWorld) getWorld()).getPlayers()) {
            if (player.distanceTo(this) < 100) {
                ServerPlayNetworking.send(player, ModMessages.SYNC_VEHICLE_PROPERTIES, buf);
            }
        }
    }

    @Override
    public boolean isCollidable() {
        return true;
    }

    @Override
    protected boolean canAddPassenger(Entity passenger) {
        return this.getPassengerList().size() < maxPassengers;
    }

    @Override
    public boolean canHit() {
        return true;
    }

    public void setAxis(int verticalAxis, int horizontalAxis) {
        if (verticalAxis >= 1) {
            this.verticalAxis = 1;
        } else if (verticalAxis <= -1) {
            this.verticalAxis = -1;
        } else {
            this.verticalAxis = verticalAxis;
        }

        if (horizontalAxis >= 1) {
            this.horizontalAxis = 1;
        } else if (horizontalAxis <= -1) {
            this.horizontalAxis = -1;
        } else {
            this.horizontalAxis = horizontalAxis;
        }
    }

    // МОЖНО ЮЗАТЬ ДЛЯ ПОДНЯТИЯ НАВЕРХ =))


    @Override
    protected void onBlockCollision(BlockState state) {

    }

    // МОЖНО ЮЗАТЬ ДЛЯ ПОЛУЧЕНИЯ ВЫСОТЫ БЛОКА
    @Override
    public BlockPos getSteppingPos() {
        return super.getSteppingPos();
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {}
}
