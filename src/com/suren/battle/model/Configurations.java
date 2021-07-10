package com.suren.battle.model;

public class Configurations {
    private static int battleCount;
    private static boolean isArtilleryAvailable;

    public static int getBattleCount() {
        return battleCount;
    }

    public static void setBattleCount(int inBattleCount) {
        battleCount = inBattleCount;
    }

    public static boolean isArtilleryAvailable() {
        return isArtilleryAvailable;
    }

    public static void setArtilleryAvailable(boolean artilleryAvailable) {
        isArtilleryAvailable = artilleryAvailable;
    }
}
