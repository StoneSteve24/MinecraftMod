package com.stonesteve24.luxcapacitor.registry;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import static com.stonesteve24.luxcapacitor.LuxCapacitor.LUX_TAB;
import static com.stonesteve24.luxcapacitor.LuxCapacitor.MODID;

public class ModBlocks {

    public static final DeferredRegister.Blocks MOD_BLOCKS = DeferredRegister.createBlocks(MODID);
    public static final DeferredRegister.Items MOD_BLOCK_ITEMS = DeferredRegister.createItems(MODID);

    // Blocks and blockitems to be registered
    public static final DeferredBlock<Block> LUMEN_ORE = MOD_BLOCKS.registerSimpleBlock("lumen_ore",
            BlockBehaviour.Properties.of().strength(3.0f, 3.0f).requiresCorrectToolForDrops().sound(SoundType.STONE));
    public static final DeferredItem<BlockItem> LUMEN_ORE_ITEM = MOD_BLOCK_ITEMS.registerSimpleBlockItem("lumen_ore", LUMEN_ORE);

    // FUNCTIONS
    public static void register(IEventBus modEventBus) {
        MOD_BLOCKS.register(modEventBus);
        MOD_BLOCK_ITEMS.register(modEventBus);

        modEventBus.addListener(ModBlocks::addCreative);
    }
    // Adding to the creative tab

    // Add the example block item to the building blocks tab
    private static void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == LUX_TAB.getKey()) {
            event.accept(LUMEN_ORE_ITEM);
        }
    }

}
