package com.stonesteve24.luxcapacitor.blocks;

import com.mojang.serialization.MapCodec;
import com.stonesteve24.luxcapacitor.menus.SolarHarvesterMenu;
import com.stonesteve24.luxcapacitor.registry.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

public class SolarHarvester extends BaseEntityBlock
{
    public static final MapCodec<SolarHarvester> CODEC =
            simpleCodec(SolarHarvester::new);

    public SolarHarvester(BlockBehaviour.Properties properties)
    {
        super(properties);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state)
    {
        return new SolarHarvesterEntity(pos, state);
    }

    @Override
    protected RenderShape getRenderShape(BlockState state)
    {
        return RenderShape.MODEL;
    }

    @Override
    protected InteractionResult useWithoutItem(
            BlockState state,
            Level level,
            BlockPos pos,
            Player player,
            BlockHitResult hit)
    {
        if (!level.isClientSide && player instanceof ServerPlayer serverPlayer)
        {
            if (level.getBlockEntity(pos) instanceof SolarHarvesterEntity harvester)
            {
                serverPlayer.openMenu(
                        new SimpleMenuProvider(
                                (id, inventory, p) ->
                                        new SolarHarvesterMenu(id, inventory, harvester),
                                Component.literal("Solar Harvester")
                        ),
                        buffer -> buffer.writeBlockPos(pos)
                );
            }
        }

        return InteractionResult.sidedSuccess(level.isClientSide);
    }
}
