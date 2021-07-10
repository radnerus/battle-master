package com.suren.com.suren.battle.simulator.util;

import com.suren.battle.model.outcome.BattleResult;
import com.suren.battle.model.terrain.Terrain;
import com.suren.battle.model.platoon.Platoon;
import com.suren.battle.model.platoon.PlatoonFactory;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * Class to parse strings to objects & object to strings for
 * platoons, terrains & results
 */
public class Parser {
    /**
     * Method to parse formatted platoon string to `List` of Platoons
     * @param platoonString formatted platoon string
     * @return list of platoons
     */
    public static List<Platoon> parsePlatoon(String platoonString) {
        try{
            String[] squads = platoonString.split(";");
            return Arrays.stream(squads).map(squad -> {
                String[] squadDetails = squad.split("#");
                return new PlatoonFactory().createPlatoon(squadDetails[0], Integer.parseInt(squadDetails[1]));
            }).collect(Collectors.toList());
        } catch (Exception e) {
            System.err.println("Error during platoon parsing " + e);
            return null;
        }
    }

    /**
     * Method to parse formatted terrain string to `List` of Terrains
     * @param terrainString formatted terrain string
     * @return list of terrains
     */
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

    /**
     * Method to format list of battle results to a formatted string
     * @param battleResults list of battle results
     * @return formatted outcome string
     */
    public static String getOutcomeString(List<BattleResult> battleResults) {
        if (battleResults == null || battleResults.isEmpty()) {
            return "";
        }

        return battleResults.stream().map(result -> {
            Platoon ourTroop = result.getOurTroop();
            return ourTroop.getPlatoonName() + "#" + ourTroop.getPlatoonCount();
        }).collect(Collectors.joining(";"));
    }

    /**
     * Method to format list of battle strings to formatted string indicating the usage of artillery force
     * @param battleResults list of battle results
     * @return formatted artillery usage string
     */
    public static String getArtilleryUsedString(List<BattleResult> battleResults) {
        if (battleResults == null || battleResults.isEmpty()) {
            return "";
        }

        return battleResults.stream()
                .map(result -> result.isArtilleryUsed() ? "A" : ".")
                .collect(Collectors.joining());
    }
}
