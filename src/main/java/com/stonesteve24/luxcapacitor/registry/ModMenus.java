package com.stonesteve24.luxcapacitor.registry;

import com.stonesteve24.luxcapacitor.LuxCapacitor;
import com.stonesteve24.luxcapacitor.menus.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModMenus
{
    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(Registries.MENU, LuxCapacitor.MODID);

    public static final Supplier<MenuType<SolarHarvesterMenu>>
            SOLAR_HARVESTER_MENU = MENUS.register(
            "solar_harvester",
            () -> IMenuTypeExtension.create(SolarHarvesterMenu::new)
    );

    public static void register(IEventBus modEventBus)
    {
        MENUS.register(modEventBus);
    }
}
