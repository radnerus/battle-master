package com.suren.battle.model.troop;

public class SoldierFactory {
    public SoldierType createSoldier(String soldierName, int count) {
        if (soldierName == null || soldierName.isEmpty()) {
            return null;
        }

        switch (soldierName) {
            case "Militia":
                return new Militia(count);
            case "Spearmen":
                return new Spearmen(count);
            case "LightCavalry":
                return new LightCavalry(count);
            case "HeavyCavalry":
                return new HeavyCavalry(count);
            case "CavalryArcher":
                return new CavalryArcher(count);
            case "FootArcher":
                return new FootArcher(count);
        }
        return null;
    }
}
