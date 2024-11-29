package net.wickedbog.adventuremod.datagen;

import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.ExplosionCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.wickedbog.adventuremod.block.ModBlocks;
import net.wickedbog.adventuremod.item.ModItems;

import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootSubProvider {
    protected ModBlockLootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }


    @Override
    protected void generate() {
        HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);

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

        // Flowers ect.

        this.add(ModBlocks.CELESTIAL_GRASS.get(), createGrassDrops(ModBlocks.CELESTIAL_GRASS.get()));

        this.dropSelf(ModBlocks.LUMINBLOSSOM.get());
        this.add(ModBlocks.POTTED_LUMINBLOSSOM.get(), createPotFlowerItemTable(ModBlocks.LUMINBLOSSOM));

        this.dropSelf(ModBlocks.ZEPHYR_LILLY.get());
        this.add(ModBlocks.POTTED_ZEPHYR_LILLY.get(), createPotFlowerItemTable(ModBlocks.ZEPHYR_LILLY));

        this.dropSelf(ModBlocks.STARPETAL.get());
        this.add(ModBlocks.POTTED_STARPETAL.get(), createPotFlowerItemTable(ModBlocks.STARPETAL));

        this.add(ModBlocks.STARLIGHT_GRASS.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1f))
                        .add(LootItem.lootTableItem(ModBlocks.STARLIGHT_GRASS.get())
                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.STARLIGHT_GRASS.get())
                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(DoublePlantBlock.HALF, DoubleBlockHalf.LOWER)))
                                .when(ExplosionCondition.survivesExplosion()))));

        // Magical

        this.add(ModBlocks.CRYSTAL_CLUSTER_BLOCK.get(), this.createSilkTouchDispatchTable(
                ModBlocks.CRYSTAL_CLUSTER_BLOCK.get(),
                LootItem.lootTableItem(ModItems.CELESTIAL_SHARD)
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(3.0f)))
                        .apply(ApplyBonusCount.addOreBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))
                        .when(MatchTool.toolMatches(ItemPredicate.Builder.item().of(ItemTags.CLUSTER_MAX_HARVESTABLES)))
                        .otherwise(
                                (LootPoolEntryContainer.Builder<?>)this.applyExplosionDecay(
                                        ModBlocks.CRYSTAL_CLUSTER_BLOCK, LootItem.lootTableItem(ModItems.CELESTIAL_SHARD).apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F)))
                                )
                        )
        ));

        // Magical - Ether Moss

        this.dropSelf(ModBlocks.ETHER_MOSS_BLOCK.get());
        this.dropSelf(ModBlocks.ETHER_MOSS_CARPET.get());

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
