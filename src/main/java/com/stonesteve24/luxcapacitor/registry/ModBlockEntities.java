package com.stonesteve24.luxcapacitor.registry;

import com.stonesteve24.luxcapacitor.LuxCapacitor;
import com.stonesteve24.luxcapacitor.blocks.SolarHarvester;
import com.stonesteve24.luxcapacitor.blocks.SolarHarvesterEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

import static com.stonesteve24.luxcapacitor.LuxCapacitor.LUX_TAB;
import static com.stonesteve24.luxcapacitor.LuxCapacitor.MODID;

public class ModBlockEntities
{
    public static final DeferredRegister<BlockEntityType<?>> MOD_BLOCK_ENTITIES = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE,LuxCapacitor.MODID);
    public static final DeferredRegister.Blocks MOD_BLOCKS = DeferredRegister.createBlocks(MODID);
    public static final DeferredRegister.Items MOD_BLOCK_ITEMS = DeferredRegister.createItems(MODID);

    public static final DeferredBlock<Block> SOLAR_HARVESTER = MOD_BLOCKS.registerBlock("solar_harvester",
            SolarHarvester::new,
            BlockBehaviour.Properties.of()
                    .strength(1.0f, 3.0f)
                    .sound(SoundType.METAL));
    public static final Supplier<BlockEntityType<SolarHarvesterEntity>> SOLAR_HARVESTER_ENTITY = MOD_BLOCK_ENTITIES.register
    (
        "solar_harvester",
        () -> BlockEntityType.Builder.of
            (
                SolarHarvesterEntity::new,
                SOLAR_HARVESTER.get()
            ).build(null)
    );
    public static final DeferredItem<BlockItem> SOLAR_HARVESTER_ITEM = MOD_BLOCK_ITEMS.registerSimpleBlockItem("solar_harvester", SOLAR_HARVESTER);


    // FUNCTIONS
    public static void register(IEventBus modEventBus) {
        MOD_BLOCKS.register(modEventBus);
        MOD_BLOCK_ITEMS.register(modEventBus);
        MOD_BLOCK_ENTITIES.register(modEventBus);

        modEventBus.addListener(ModBlockEntities::addCreative);
    }
    // Adding to the creative tab

    // Add the example block item to the building blocks tab
    private static void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == LUX_TAB.getKey()) {
            event.accept(SOLAR_HARVESTER_ITEM);
        }
    }
}
