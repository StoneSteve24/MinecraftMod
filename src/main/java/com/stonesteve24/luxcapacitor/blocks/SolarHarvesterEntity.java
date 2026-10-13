package com.stonesteve24.luxcapacitor.blocks;

import com.stonesteve24.luxcapacitor.lux.LuxShade;
import com.stonesteve24.luxcapacitor.lux.LuxStorage;
import com.stonesteve24.luxcapacitor.registry.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class SolarHarvesterEntity extends BlockEntity
{
    private final LuxStorage luxStorage = new LuxStorage(10000, 0);
    private boolean canSeeSun = false;
    private int skyCheckTimer = 0;

    public SolarHarvesterEntity(BlockPos pos, BlockState state)
    {
        super(ModBlockEntities.SOLAR_HARVESTER_ENTITY.get(), pos, state);
    }

    public int getStoredLux() { return luxStorage.getStored(); }
    public int getLuxCapacity() { return luxStorage.getCapacity(); }
    public LuxShade getShade() { return luxStorage.getShade(); }
    public int getStorageType() { return luxStorage.getType(); }
    //public LuxStorage getStorageComponent() { return luxStorage; }

    public static void tick(
            Level level,
            BlockPos pos,
            BlockState state,
            SolarHarvesterEntity blockEntity
    ) {
        if (level.isClientSide()) {
            return;
        }
        // Recheck sky visibility every second for performance
        if (++blockEntity.skyCheckTimer >= 20) {
            blockEntity.skyCheckTimer = 0;

            blockEntity.canSeeSun = level.canSeeSky(pos.above())
                    && level.isDay()
                    && !level.isRaining();
        }

        // Generate Lux if sun is available
        if (blockEntity.canSeeSun)
        {
            blockEntity.luxStorage.addLux(LuxShade.WHITE, 50);
        }

        //System.out.println(blockEntity.luxStorage.getStored());
        //System.out.println(blockEntity.luxStorage.getShade().name());
    }

    @Override
    protected void saveAdditional(
            CompoundTag tag,
            HolderLookup.Provider registries
    ) {
        super.saveAdditional(tag, registries);

        tag.putInt("Stored", luxStorage.getStored());
        tag.putString("Shade", luxStorage.getShade().name());
    }

    @Override
    protected void loadAdditional(
            CompoundTag tag,
            HolderLookup.Provider registries
    ) {
        super.loadAdditional(tag, registries);

        luxStorage.setStored(tag.getInt("Stored"));

        String shadeName = tag.getString("Shade");

        try {
            luxStorage.setShade(LuxShade.valueOf(shadeName));
        } catch (IllegalArgumentException e) {
            luxStorage.setShade(LuxShade.BLANK);
        }
    }

    // Button resolvers

    public void flushLux()
    {
        luxStorage.removeLux(luxStorage.getCapacity());
        luxStorage.setShade(LuxShade.BLANK);
    }
}
