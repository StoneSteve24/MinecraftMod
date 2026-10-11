package com.stonesteve24.luxcapacitor.lux;

import java.awt.*;

public final class LuxAPI
{
    private LuxAPI(){};

    public static String GetShadeString(int ordinal)
    {
        return switch (ordinal) {
            case 0 -> "White";
            case 1 -> "Red";
            case 2 -> "Orange";
            case 3 -> "Yellow";
            case 4 -> "Green";
            case 5 -> "Blue";
            case 6 -> "Indigo";
            case 7 -> "Violet";
            case 8 -> "Dark";
            case 9 -> "None";
            default -> "SHADE_STRING_ERROR";
        };
    }

    public static Color GetShadeHex(int ordinal)
    {
        return switch (ordinal) {
            case 0 -> new Color(0xffffff);
            case 1 -> new Color(0xff0000);
            case 2 -> new Color(0xffa500);
            case 3 -> new Color(0xffff00);
            case 4 -> new Color(0x008000);
            case 5 -> new Color(0x0000ff);
            case 6 -> new Color(0x4b00fa);
            case 7 -> new Color(0x7f00ff);
            case 8 -> new Color(0x000000);
            case 9 -> new Color(0xffffff);
            default -> new Color(0xffffff);
        };
    }
}
