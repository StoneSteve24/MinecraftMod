package com.stonesteve24.luxcapacitor.menus;

import com.stonesteve24.luxcapacitor.blocks.SolarHarvesterEntity;
import com.stonesteve24.luxcapacitor.registry.ModMenus;
import net.minecraft.core.BlockPos;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.DataSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;

public class SolarHarvesterMenu extends AbstractContainerMenu
{
    private final SolarHarvesterEntity blockEntity;
    private final DataSlot storedLux = DataSlot.standalone();

    public SolarHarvesterMenu(
            int containerId,
            Inventory inventory,
            SolarHarvesterEntity blockEntity)
    {
        super(ModMenus.SOLAR_HARVESTER_MENU.get(), containerId);
        this.blockEntity = blockEntity;
        addDataSlot(storedLux);
        storedLux.set(blockEntity.getStoredLux());
    }

    public SolarHarvesterMenu(
            int containerId,
            Inventory inventory,
            RegistryFriendlyByteBuf data)
    {
        this(
                containerId,
                inventory,
                getBlockEntity(inventory, data)
        );
    }

    private static SolarHarvesterEntity getBlockEntity(
            Inventory inventory,
            RegistryFriendlyByteBuf data)
    {
        BlockPos pos = data.readBlockPos();
        BlockEntity entity = inventory.player.level().getBlockEntity(pos);
        if (entity instanceof SolarHarvesterEntity harvester)
        {
            return harvester;
        }
        throw new IllegalStateException("Solar Harvester block entity not found");
    }

    public int getStoredLux()
    {
        return storedLux.get();
    }

    public int getLuxCapacity()
    {
        return blockEntity.getLuxCapacity();
    }

    @Override
    public ItemStack quickMoveStack(Player player, int i) {
        return null;
    }

    @Override
    public boolean stillValid(Player player)
    {
        return blockEntity.getBlockPos().distToCenterSqr(
                player.position()
        ) <= 64.0;
    }

    @Override
    public void broadcastChanges()
    {
        storedLux.set(blockEntity.getStoredLux());
        super.broadcastChanges();
    }
}
