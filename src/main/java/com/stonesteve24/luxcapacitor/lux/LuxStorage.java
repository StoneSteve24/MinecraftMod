package com.stonesteve24.luxcapacitor.lux;

public class LuxStorage
{
    private final int capacity;

    private int stored;
    private LuxShade currentShade;

    /** Construct a Lux Storage component with the specified capacity */
    public LuxStorage(int capacity)
    {
        // Failsafe. Do not initialize with negative storage
        if (capacity < 0) { throw new IllegalArgumentException("Lux storage capacity cannot be a negative value"); }

        this.capacity = capacity;
        currentShade = LuxShade.BLANK;
        stored = 0;
    }

    //Getters

    public int getStored() { return stored; }
    public int getCapacity() { return capacity; }
    public LuxShade getShade() { return currentShade; }

    // Main

    /**
     * Add Lux to the storage component. Specify the shade of Lux to be added and how much should be added
     * @param incomingShade
     * @param amount
     * @return Returns how much Lux was accepted by the component.
     */
    public int addLux(LuxShade incomingShade, int amount)
    {
        if (stored == capacity) { return amount; }
        if (incomingShade == null || amount <= 0) { return amount; }

        // Simple storage cannot mix shades. Reject transfer
        if (stored > 0 && currentShade != incomingShade) { return amount; }

        // Calculate the amount we are allowed to accept into storage
        int accepted = Math.min(amount, capacity - stored);
        currentShade = incomingShade;
        stored += accepted;

        return amount - accepted;
    }

    /**
     * Remove Lux from the storage component. If attempting to remove more Lux than stored, will set stored to 0
     * Stored cannot be negative
     * @param amount
     * @return The amount successfully removed as long.
     */
    public int removeLux(int amount)
    {
        if (amount <= 0) { return 0; }

        int extracted = Math.min(amount, stored);
        stored -= extracted;

        // If emptied, set current shade to blank
        if (stored == 0) { currentShade = LuxShade.BLANK; }
        return extracted;
    }


}
