package net.wickedbog.adventuremod.datagen;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.JsonElement;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.data.models.blockstates.BlockStateGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.AdvancementProvider;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.wickedbog.adventuremod.AdventureMod;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;
@EventBusSubscriber(modid = AdventureMod.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class DataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        generator.addProvider(event.includeClient(), new ModParticleDescriptionProvider(packOutput, existingFileHelper));

        generator.addProvider(event.includeServer(), new LootTableProvider(packOutput, Collections.emptySet(),
                List.of(new LootTableProvider.SubProviderEntry(ModBlockLootTableProvider::new, LootContextParamSets.BLOCK)), lookupProvider));
        generator.addProvider(event.includeServer(), new ModRecipeProvider(packOutput, lookupProvider));

        BlockTagsProvider blockTagsProvider = new ModBlockTagProvider(packOutput, lookupProvider, existingFileHelper);
        generator.addProvider(event.includeServer(), blockTagsProvider);
        generator.addProvider(event.includeServer(), new ModItemTagProvider(packOutput, lookupProvider, blockTagsProvider.contentsGetter(), existingFileHelper));

        generator.addProvider(event.includeClient(), new ModItemModelProvider(packOutput, existingFileHelper));

        Map<ResourceLocation, Supplier<JsonElement>> supplierMap = Maps.newHashMap();
        BiConsumer<ResourceLocation, Supplier<JsonElement>> supplierBiConsumer = (resourceLocation, supplier) -> {
            Supplier<JsonElement> jsonElementSupplier = supplierMap.put(resourceLocation, supplier);
            if (jsonElementSupplier != null) {
                throw new IllegalStateException("Duplicate model definition for " + resourceLocation);
            }
        };

        Map<Block, BlockStateGenerator> stateGeneratorMap = Maps.newHashMap();
        Consumer<BlockStateGenerator> stateGeneratorConsumer = blockStateGenerator -> {
            Block block = blockStateGenerator.getBlock();
            BlockStateGenerator stateGenerator = stateGeneratorMap.put(block, blockStateGenerator);
            if (stateGenerator != null) {
                throw new IllegalStateException("Duplicate blockstate definition for " + block);
            }
        };
        generator.addProvider(event.includeClient(), new ModBlockStateProvider(packOutput, existingFileHelper, stateGeneratorConsumer, supplierBiConsumer));

        generator.addProvider(event.includeClient(), new ModWorldGenProvider(packOutput, lookupProvider));
        generator.addProvider(event.includeClient(), new AdvancementProvider(packOutput, lookupProvider, existingFileHelper, List.of(new ModAdvancementProvider())));
        generator.addProvider(event.includeClient(), new ModSoundDefenitionsProvider(packOutput, existingFileHelper));
    }
}
