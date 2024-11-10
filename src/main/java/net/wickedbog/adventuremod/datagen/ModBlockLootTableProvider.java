package net.wickedbog.adventuremod.datagen;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.wickedbog.adventuremod.block.ModBlocks;

import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootSubProvider {
    protected ModBlockLootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }


    @Override
    protected void generate() {
        // Misc.

        dropSelf(ModBlocks.TEST_BLOCK.get());

        // Logs

        this.dropSelf(ModBlocks.HEARTWOOD_LOG.get());
        this.dropSelf(ModBlocks.STRIPPED_HEARTWOOD_LOG.get());

        // Woods

        this.dropSelf(ModBlocks.HEARTWOOD_WOOD.get());
        this.dropSelf(ModBlocks.STRIPPED_HEARTWOOD_WOOD.get());

        // Planks

        this.dropSelf(ModBlocks.HEARTWOOD_PLANKS.get());

        // Leaves

        this.add(ModBlocks.HEARTWOOD_LEAVES.get(), block ->
                createLeavesDrops(block, ModBlocks.HEARTWOOD_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));

        // Saplings

        this.dropSelf(ModBlocks.HEARTWOOD_SAPLING.get());

        // add(ModBlocks.BISMUTH_ORE.get(),
        //         block -> createOreDrop(ModBlocks.BISMUTH_ORE.get(), ModItems.RAW_BISMUTH.get()));
        // add(ModBlocks.BISMUTH_DEEPSLATE_ORE.get(),
        //         block -> createMultipleOreDrops(ModBlocks.BISMUTH_DEEPSLATE_ORE.get(), ModItems.RAW_BISMUTH.get(), 2, 5));
    }

    protected LootTable.Builder createMultipleOreDrops(Block pBlock, Item item, float minDrops, float maxDrops) {
        HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return this.createSilkTouchDispatchTable(pBlock,
                this.applyExplosionDecay(pBlock, LootItem.lootTableItem(item)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(minDrops, maxDrops)))
                        .apply(ApplyBonusCount.addOreBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
       return ModBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}
