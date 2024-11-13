package net.wickedbog.adventuremod.sound;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.wickedbog.adventuremod.AdventureMod;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, AdventureMod.MOD_ID);

    public static final DeferredHolder<SoundEvent, SoundEvent> TEST_SOUND = SOUND_EVENTS.register("test_sound",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(AdventureMod.MOD_ID, "test_sound")));

    // There is a currently unused method to register fixed range (= non-attenuating) events as well:
    //public static final DeferredHolder<SoundEvent, SoundEvent> MY_FIXED_SOUND = SOUND_EVENTS.register("my_fixed_sound",
            // 16 is the default range of sounds. Be aware that due to OpenAL limitations,
            // values above 16 have no effect and will be capped to 16.
    //        () -> SoundEvent.createFixedRangeEvent(ResourceLocation.fromNamespaceAndPath("examplemod", "my_fixed_sound"), 16)
    //);

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}
