package net.tcz.vehiclemod.sound.instance;

import net.minecraft.client.sound.AudioStream;
import net.minecraft.client.sound.MovingSoundInstance;
import net.minecraft.client.sound.SoundInstance;
import net.minecraft.client.sound.SoundLoader;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import net.tcz.vehiclemod.entity.entities.VehicleEntity;
import net.tcz.vehiclemod.sound.ModSounds;

import java.util.concurrent.CompletableFuture;

public class NivaSoundMovingInstance extends MovingSoundInstance {
    protected VehicleEntity vehicle;

    protected NivaSoundMovingInstance(SoundEvent soundEvent, SoundCategory soundCategory, Random random) {
        super(soundEvent, soundCategory, random);
    }

    public NivaSoundMovingInstance(VehicleEntity vehicle) {
        super(ModSounds.DRIVING_NIVA, SoundCategory.MASTER, SoundInstance.createRandom());
        this.vehicle = vehicle;
        this.repeat = true;
        this.repeatDelay = 0;
        this.volume = 0.0f;
        this.x = (float)vehicle.getX();
        this.y = (float)vehicle.getY();
        this.z = (float)vehicle.getZ();
    }

    @Override
    public void tick() {
        if (vehicle.isRemoved()) {
            this.setDone();
            return;
        }
        this.volume = 0f;
        if (vehicle.getPassengerList().size() > 0)
            this.volume = 0.5f;

        this.x = (float)vehicle.getX();
        this.y = (float)vehicle.getY();
        this.z = (float)vehicle.getZ();

        double speed = vehicle.speed;

        if (speed != 0) {
            double dispersion = Math.random() / 30;

            this.pitch = (float) Math.abs(speed / vehicle.maxSpeed + dispersion);
        } else {
            this.pitch = 0.5f;
        }
    }

    @Override
    public boolean shouldAlwaysPlay() {
        return true;
    }

    @Override
    public boolean canPlay() {
        return !vehicle.isSilent();
    }

    @Override
    public CompletableFuture<AudioStream> getAudioStream(SoundLoader loader, Identifier id, boolean repeatInstantly) {
        return super.getAudioStream(loader, id, repeatInstantly);
    }
}
