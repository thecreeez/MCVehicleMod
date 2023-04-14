package net.tcz.vehiclemod.sound.instance;

import net.minecraft.client.sound.MovingSoundInstance;
import net.minecraft.client.sound.SoundInstance;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.random.Random;
import net.tcz.vehiclemod.entity.entities.VehicleEntity;
import net.tcz.vehiclemod.sound.ModSounds;

public class RadioSoundMovingInstance extends MovingSoundInstance {
    protected VehicleEntity vehicle;

    protected RadioSoundMovingInstance(SoundEvent soundEvent, SoundCategory soundCategory, Random random) {
        super(soundEvent, soundCategory, random);
    }

    public RadioSoundMovingInstance(VehicleEntity vehicle) {
        super(ModSounds.MUSIC_1, SoundCategory.MASTER, SoundInstance.createRandom());
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
        this.volume = 0.5f;

        this.x = (float)vehicle.getX();
        this.y = (float)vehicle.getY();
        this.z = (float)vehicle.getZ();

        if (!vehicle.radioPlaying) {
            this.setDone();
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

    public boolean isDone() {
        return super.isDone();
    }
}
