package com.stonesteve24.luxcapacitor.menus;

import com.stonesteve24.luxcapacitor.lux.LuxAPI;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public class SolarHarvesterScreen
        extends AbstractContainerScreen<SolarHarvesterMenu>
{
    public SolarHarvesterScreen(
            SolarHarvesterMenu menu,
            Inventory inventory,
            Component title)
    {
        super(menu, inventory, title);

        this.imageWidth = 176;
        this.imageHeight = 100;
    }

    @Override
    protected void renderBg(
            GuiGraphics graphics,
            float partialTick,
            int mouseX,
            int mouseY)
    {
        int x = leftPos;
        int y = topPos;

        // Main background
        graphics.fill(
                x, y, x + imageWidth, y + imageHeight,
                0xFF20252B
        );

        // Border
        graphics.fill(x, y, x + imageWidth, y + 2, 0xFF777777);
        graphics.fill(x, y, x + 2, y + imageHeight, 0xFF777777);
        graphics.fill(x + imageWidth - 2, y, x + imageWidth,
                y + imageHeight, 0xFF777777);
        graphics.fill(x, y + imageHeight - 2, x + imageWidth,
                y + imageHeight, 0xFF777777);

        // Lux storage bar
        int barX = x + 10;
        int barY = y + 60;
        int barWidth = 156;
        int barHeight = 10;

        graphics.fill(
                barX, barY, barX + barWidth, barY + barHeight,
                0xFF101010
        );

        int capacity = menu.getLuxCapacity();
        int stored = menu.getStoredLux();

        int filledWidth = capacity > 0
                ? (int) (barWidth * (stored / (float) capacity))
                : 0;

        filledWidth = Math.clamp(filledWidth, 0, barWidth);

        graphics.fill(
                barX, barY,
                barX + filledWidth, barY + barHeight,
                LuxAPI.GetShadeHex(menu.getShadeInt()).getRGB()
        );
    }

    @Override
    protected void renderLabels(
            GuiGraphics graphics,
            int mouseX,
            int mouseY)
    {
        graphics.drawCenteredString(
                font, title, imageWidth / 2, 8, 0xFFFFFF
        );

        graphics.drawString(
                font,
                "Lux Shade: " + LuxAPI.GetShadeString(menu.getShadeInt()),
                10, 25, 0xFFFFFF
        );

        graphics.drawString(
                font,
                "Stored: " + menu.getStoredLux() + " Lux",
                10, 38, 0xFFFFFF
        );

        graphics.drawString(
                font,
                "Capacity: " + menu.getLuxCapacity() + " Lux",
                10, 49, 0xFFCCCCCC
        );
    }

    @Override
    public void render(
            GuiGraphics graphics,
            int mouseX,
            int mouseY,
            float partialTick)
    {
        renderBackground(graphics, mouseX, mouseY, partialTick);
        super.render(graphics, mouseX, mouseY, partialTick);
        renderTooltip(graphics, mouseX, mouseY);
    }

    @Override
    protected void init() {
        super.init();

        int x = leftPos;
        int y = topPos;

        this.addRenderableWidget(
                Button.builder(
                                Component.literal("Flush Lux"),
                                button -> {
                                    if (this.minecraft.gameMode != null) {
                                        this.minecraft.gameMode.handleInventoryButtonClick(
                                                this.menu.containerId,
                                                0
                                        );
                                    }
                                }
                        )
                        .bounds(x+9, y+73, 60, 20)
                        .build()
        );
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        boolean result = super.mouseClicked(mouseX, mouseY, button);

        this.setFocused(null);

        return result;
    }
}