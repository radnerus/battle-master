package com.suren.battle.model.platoon;

/**
 * Factory method to construct different types of Platoons
 */
public class PlatoonFactory {
    /**
     * Method to construct different types of Platoons
     * @param platoonName name of the platoon
     * @param count count of soldiers in the platoon
     * @return platoon object
     */
    public Platoon createPlatoon(String platoonName, int count) {
        if (platoonName == null || platoonName.isEmpty()) {
            return null;
        }

        switch (platoonName) {
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
