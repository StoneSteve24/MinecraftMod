package com.stonesteve24.luxcapacitor.blocks;

import com.stonesteve24.luxcapacitor.lux.LuxStorage;
import com.stonesteve24.luxcapacitor.registry.ModBlockEntities;
import com.stonesteve24.luxcapacitor.registry.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class SolarHarvesterEntity extends BlockEntity
{
    private final LuxStorage luxStorage = new LuxStorage(10_000);

    public SolarHarvesterEntity(BlockPos pos, BlockState state)
    {
        super(ModBlockEntities.SOLAR_HARVESTER_ENTITY.get(), pos, state);
    }

    public int getStoredLux() { return luxStorage.getStored(); }

    public int getLuxCapacity() { return luxStorage.getCapacity(); }
}
