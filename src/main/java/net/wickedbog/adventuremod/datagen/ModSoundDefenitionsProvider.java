package net.wickedbog.adventuremod.datagen;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.common.data.SoundDefinition;
import net.neoforged.neoforge.common.data.SoundDefinitionsProvider;
import net.wickedbog.adventuremod.AdventureMod;
import net.wickedbog.adventuremod.sound.ModSounds;

public class ModSoundDefenitionsProvider extends SoundDefinitionsProvider {
    protected ModSoundDefenitionsProvider(PackOutput output, ExistingFileHelper helper) {
        super(output, AdventureMod.MOD_ID, helper);
    }

    @Override
    public void registerSounds() {
        add(ModSounds.TEST_SOUND, SoundDefinition.definition()
                .with(
                        sound(AdventureMod.MOD_ID + ":test_sound", SoundDefinition.SoundType.SOUND)
                                .volume(0.8f)
                                .pitch(1.2f)
                                .weight(2)
                                .attenuationDistance(8)
                                .stream(true)
                                .preload(true))

                .subtitle("sound.adventuremod.test_sound")
                .replace(true));
    }
}
