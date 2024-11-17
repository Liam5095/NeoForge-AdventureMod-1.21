package net.wickedbog.adventuremod.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.wickedbog.adventuremod.AdventureMod;
import net.wickedbog.adventuremod.block.ModBlocks;
import net.wickedbog.adventuremod.item.ModItems;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, AdventureMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        // Misc.

        basicItem(ModItems.TEST_ITEM.get());

        // Saplings

        saplingItem(ModBlocks.HEARTWOOD_SAPLING);

        // Flowers ect.

        flowerItem(ModBlocks.CELESTIAL_GRASS);
        flowerItem(ModBlocks.LUMINBLOSSOM);

        doubleFlowerItem(ModBlocks.STARLIGHT_GRASS);

        // Foods

        basicItem(ModItems.SKY_BERRIES.get());

        // Dye

        basicItem(ModItems.LUMINBLOSSOM_DYE.get());
    }

    private ItemModelBuilder saplingItem(DeferredBlock<Block> item) {
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.parse("item/generated")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath(AdventureMod.MOD_ID,"block/" + item.getId().getPath()));
    }

    private ItemModelBuilder flowerItem(DeferredBlock<Block> item) {
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.parse("item/generated")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath(AdventureMod.MOD_ID,"block/" + item.getId().getPath()));
    }

    private ItemModelBuilder doubleFlowerItem(DeferredBlock<Block> item) {
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.parse("item/generated")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath(AdventureMod.MOD_ID,"block/" + item.getId().getPath() + "_top"));
    }
}
