package com.suren.com.suren.battle.simulator.util;

import com.suren.battle.model.outcome.BattleResult;
import com.suren.battle.model.terrain.Terrain;
import com.suren.battle.model.troop.SoldierType;
import com.suren.battle.model.troop.SoldierFactory;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 *
 */
public class Parser {
    public static List<SoldierType> parsePlatoon(String platoonString) {
        try{
            String[] squads = platoonString.split(";");
            return Arrays.stream(squads).map(squad -> {
                String[] squadDetails = squad.split("#");
                return new SoldierFactory().createSoldier(squadDetails[0], Integer.parseInt(squadDetails[1]));
            }).collect(Collectors.toList());
        } catch (Exception e) {
            System.err.println("Error during platoon parsing " + e);
            return null;
        }
    }

    public static List<Terrain> parseTerrain(String terrainString) {
        if (terrainString == null || terrainString.isEmpty()) {
            terrainString = "Default;Default;Default;Default;Default";
        }
        try {
            return Arrays.stream(terrainString.split(";"))
                    .map(terrain -> Terrain.valueOf(terrain.toUpperCase(Locale.ROOT)))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            System.err.println("Error during terrain parsing " + e);
            return null;
        }
    }

    public static String getOutcomeString(List<BattleResult> battleResults) {
        if (battleResults == null || battleResults.isEmpty()) {
            return "";
        }

        return battleResults.stream().map(result -> {
            SoldierType ourTroop = result.getOurTroop();
            return ourTroop.getSoldierName() + "#" + ourTroop.getSoldiersCount();
        }).collect(Collectors.joining(";"));
    }

    public static String getArtilleryUsedString(List<BattleResult> battleResults) {
        if (battleResults == null || battleResults.isEmpty()) {
            return "";
        }

        return battleResults.stream()
                .map(result -> result.isArtilleryUsed() ? "A" : ".")
                .collect(Collectors.joining());
    }
}
