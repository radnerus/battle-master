package com.suren.com.suren.battle.simulator.util;

import com.suren.battle.model.Configurations;
import com.suren.battle.model.terrain.Terrain;
import com.suren.battle.model.troop.SoldierType;

import java.util.List;

public class BattleUtil {
    public static boolean validateBattleInputs(List<SoldierType> ourPlatoon, List<SoldierType> enemyPlatoon, List<Terrain> terrains) {
        return validateList(ourPlatoon) && validateList(enemyPlatoon) && validateList(terrains);
    }
    public static boolean validateList(List list) {
        return !(list == null || list.size() != Configurations.getBattleCount()) && !list.contains(null);
    }
}
