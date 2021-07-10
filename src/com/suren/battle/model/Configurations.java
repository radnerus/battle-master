package com.suren.battle.model;

/**
 * General configuration for the battle master
 */
public class Configurations {
    private static int battleCount;
    private static boolean isArtilleryAvailable;

    /**
     * getter for battle count
     * @return battle count
     */
    public static int getBattleCount() {
        return battleCount;
    }

    /**
     * setter for battle count
     * @param inBattleCount battle count
     */
    public static void setBattleCount(int inBattleCount) {
        battleCount = inBattleCount;
    }

    /**
     * getter for artillery availability
     * @return artillery availability
     */
    public static boolean isArtilleryAvailable() {
        return isArtilleryAvailable;
    }

    /**
     * setter for artillery availability
     * @param artilleryAvailable artillery availability
     */
    public static void setArtilleryAvailable(boolean artilleryAvailable) {
        isArtilleryAvailable = artilleryAvailable;
    }
}
