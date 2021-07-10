package com.suren.com.suren.battle.simulator.util;

import com.suren.battle.model.Configurations;
import com.suren.battle.model.terrain.Terrain;
import com.suren.battle.model.platoon.Platoon;

import java.util.List;

/**
 * Util class for battle simulation
 */
public class BattleUtil {
    /**
     * Static method to validate battle inputs
     * @param ourPlatoon list of our input platoon
     * @param enemyPlatoon list of enemy's input platoon
     * @param terrains list of terrain
     * @return true, if all the lists are valid
     */
    public static boolean validateBattleInputs(List<Platoon> ourPlatoon, List<Platoon> enemyPlatoon, List<Terrain> terrains) {
        return validateList(ourPlatoon) && validateList(enemyPlatoon) && validateList(terrains);
    }

    /**
     * Validates list of platoons or terrains
     * @param list list of platoons/terrains
     * @return true, if the list is valid
     */
    public static boolean validateList(List list) {
        return !(list == null || list.size() != Configurations.getBattleCount()) && !list.contains(null);
    }
}
