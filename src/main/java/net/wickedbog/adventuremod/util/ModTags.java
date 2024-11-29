package net.wickedbog.adventuremod.util;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.wickedbog.adventuremod.AdventureMod;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> HEARTWOOD = createTag("heartwood");
        public static final TagKey<Block> HEARTWOOD_EXTRAS = createTag("heartwood_extras");
        public static final TagKey<Block> HEARTWOOD_COMPLETE = createTag("heartwood_complete");

        private static TagKey<Block> createTag(String name) {
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(AdventureMod.MOD_ID, name));
        }
    }

    public static class Items {
        //  public static final TagKey<Item> TRANSFORMABLE_ITEMS = createTag("transformable_items");

        private static TagKey<Item> createTag(String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(AdventureMod.MOD_ID, name));
        }
    }
}
