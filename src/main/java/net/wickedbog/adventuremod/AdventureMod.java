package net.wickedbog.adventuremod;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.wickedbog.adventuremod.block.ModBlocks;
import net.wickedbog.adventuremod.item.ModCreativeModeTabs;
import net.wickedbog.adventuremod.item.ModItems;
import net.wickedbog.adventuremod.particle.ModParticles;
import net.wickedbog.adventuremod.sound.ModSounds;
import net.wickedbog.adventuremod.worldgen.biomes.ModTerrablender;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

@Mod(AdventureMod.MOD_ID)
public class AdventureMod {
    public static final String MOD_ID = "adventuremod";
    public static final Logger LOGGER = LogUtils.getLogger();

    public AdventureMod(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::commonSetup);

        NeoForge.EVENT_BUS.register(this);

        ModTerrablender.registerBiomes();

        ModCreativeModeTabs.register(modEventBus);

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);

        ModParticles.register(modEventBus);
        ModSounds.register(modEventBus);

        modEventBus.addListener(this::addCreative);
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBlocks.LUMINBLOSSOM.getId(), ModBlocks.POTTED_LUMINBLOSSOM);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBlocks.ZEPHYR_LILLY.getId(), ModBlocks.POTTED_ZEPHYR_LILLY);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBlocks.STARPETAL.getId(), ModBlocks.POTTED_STARPETAL);
        });
    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        // if(event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
        //     event.accept(ModItems.TEST_ITEM);
        // }

        // if(event.getTabKey() == CreativeModeTabs.COLORED_BLOCKS) {
        //     event.accept(ModBlocks.TEST_BLOCK);
        // }
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @EventBusSubscriber(modid = MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {

        }
    }
}