package com.stonesteve24.luxcapacitor.registry;

import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import static com.stonesteve24.luxcapacitor.LuxCapacitor.MODID;
import static com.stonesteve24.luxcapacitor.LuxCapacitor.LUX_TAB;

public class ModItems {

    public static final DeferredRegister.Items MOD_ITEMS = DeferredRegister.createItems(MODID);

    public static final DeferredItem<Item> PRISM = MOD_ITEMS.registerSimpleItem("simple_prism");
    public static final DeferredItem<Item> RAW_LUMEN = MOD_ITEMS.registerSimpleItem("raw_lumen");

    public static void register(IEventBus modEventBus) {
        MOD_ITEMS.register(modEventBus);
        modEventBus.addListener(ModItems::addCreative);
    }
    // Adding to the creative tab

    // Add the example block item to the building blocks tab
    private static void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == LUX_TAB.getKey()) {
            event.accept(PRISM);
            event.accept(RAW_LUMEN);
        }
    }
}
