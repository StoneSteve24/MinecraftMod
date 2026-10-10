package com.stonesteve24.luxcapacitor.lux;

import net.minecraft.ChatFormatting;

public enum LuxShade
{
    WHITE(ChatFormatting.WHITE),
    RED(ChatFormatting.RED),
    ORANGE(ChatFormatting.GOLD),
    YELLOW(ChatFormatting.YELLOW),
    GREEN(ChatFormatting.GREEN),
    BLUE(ChatFormatting.BLUE),
    INDIGO(ChatFormatting.DARK_BLUE),
    VIOLET(ChatFormatting.DARK_PURPLE),
    DARK(ChatFormatting.DARK_GRAY),
    BLANK(ChatFormatting.WHITE);

    private final ChatFormatting formatting;

    LuxShade(ChatFormatting formatting) {
        this.formatting = formatting;
    }

    public ChatFormatting getFormatting() {
        return formatting;
    }
}
