package com.stonesteve24.luxcapacitor.blocks;

import com.mojang.serialization.MapCodec;
import com.stonesteve24.luxcapacitor.menus.BasicLuxStorageMenu;
import com.stonesteve24.luxcapacitor.registry.ModBlockEntities;
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
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class BasicLuxStorage extends BaseEntityBlock
{
    public static final MapCodec<BasicLuxStorage> CODEC =
            simpleCodec(BasicLuxStorage::new);

    public BasicLuxStorage(BlockBehaviour.Properties properties)
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
        return new BasicLuxStorageEntity(pos, state);
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
            if (level.getBlockEntity(pos) instanceof BasicLuxStorageEntity storage)
            {
                serverPlayer.openMenu(
                        new SimpleMenuProvider(
                                (id, inventory, p) ->
                                        new BasicLuxStorageMenu(id, inventory, storage),
                                Component.literal("Basic Lux Storage")
                        ),
                        buffer -> buffer.writeBlockPos(pos)
                );
            }
        }

        return InteractionResult.sidedSuccess(level.isClientSide);
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(
            Level level,
            BlockState state,
            BlockEntityType<T> type
    ) {
        return level.isClientSide()
                ? null
                : createTickerHelper(
                type,
                ModBlockEntities.BASIC_LUX_STORAGE_ENTITY.get(),
                BasicLuxStorageEntity::tick
        );
    }
}