package net.tcz.vehiclemod.sound;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.tcz.vehiclemod.Vehiclemod;

public class ModSounds {
    public static final Identifier DRIVING_NIVA_ID = new Identifier(Vehiclemod.MOD_ID, "driving_niva");
    public static SoundEvent DRIVING_NIVA = SoundEvent.of(DRIVING_NIVA_ID);

    public static final Identifier STAYING_NIVA_ID = new Identifier(Vehiclemod.MOD_ID, "staying_niva");
    public static SoundEvent STAYING_NIVA = SoundEvent.of(STAYING_NIVA_ID);

    public static final Identifier OPEN_HOOD_ID = new Identifier(Vehiclemod.MOD_ID, "open_hood");
    public static SoundEvent OPEN_HOOD = SoundEvent.of(OPEN_HOOD_ID);

    public static final Identifier CLOSE_HOOD_ID = new Identifier(Vehiclemod.MOD_ID, "close_hood");
    public static SoundEvent CLOSE_HOOD = SoundEvent.of(CLOSE_HOOD_ID);

    public static final Identifier MUSIC_1_ID = new Identifier(Vehiclemod.MOD_ID, "music_1");
    public static SoundEvent MUSIC_1 = SoundEvent.of(MUSIC_1_ID);

    public static void init() {
        Registry.register(Registries.SOUND_EVENT, DRIVING_NIVA_ID, DRIVING_NIVA);
        Registry.register(Registries.SOUND_EVENT, STAYING_NIVA_ID, STAYING_NIVA);
        Registry.register(Registries.SOUND_EVENT, OPEN_HOOD_ID, OPEN_HOOD);
        Registry.register(Registries.SOUND_EVENT, CLOSE_HOOD_ID, CLOSE_HOOD);
        Registry.register(Registries.SOUND_EVENT, MUSIC_1_ID, MUSIC_1);
    }
}