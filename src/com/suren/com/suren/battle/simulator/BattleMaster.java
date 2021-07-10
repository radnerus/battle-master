package com.suren.com.suren.battle.simulator;

import com.suren.battle.model.Configurations;
import com.suren.battle.model.terrain.Terrain;
import com.suren.battle.model.troop.SoldierType;
import com.suren.com.suren.battle.simulator.util.BattleUtil;
import com.suren.com.suren.battle.simulator.util.Parser;

import java.util.List;
import java.util.Scanner;

public class BattleMaster {
    public void initiate() {
        Scanner inScanner = new Scanner(System.in);

        System.out.println("\n\nWelcome to Battle Simulator:");
        System.out.println("____________________________");


        System.out.println("\n____________________________");
        System.out.println("Enter number of battles:");
        while (!inScanner.hasNextInt()) {
            System.err.println("Please enter a valid number");
            inScanner.next();
        }
        int noOfBattles = inScanner.nextInt();
        Configurations.setBattleCount(noOfBattles);

        System.out.println("\n____________________________");
        System.out.println("Please enter your platoon:");
        String ourPlatoonString;
        List<SoldierType> ourPlatoon = null;
        while (ourPlatoon == null) {
            ourPlatoonString = inScanner.next();
            ourPlatoon = Parser.parsePlatoon(ourPlatoonString);
            if (!BattleUtil.validateList(ourPlatoon)) {
                ourPlatoon = null;
                System.err.println("Enter valid format for our platoon.");
                System.out.println("Sample: FootArcher#60;Militia#10;LightCavalry#30;HeavyCavalry#1;CavalryArcher#3");
            }
        }

        System.out.println("\n____________________________");
        System.out.println("Please enter enemy platoon:");
        String enemyPlatoonString;
        List<SoldierType> enemyPlatoon = null;
        while (enemyPlatoon == null) {
            enemyPlatoonString = inScanner.next();
            enemyPlatoon = Parser.parsePlatoon(enemyPlatoonString);
            if (!BattleUtil.validateList(enemyPlatoon)) {
                enemyPlatoon = null;
                System.err.println("Enter valid format for enemy platoon.");
                System.out.println("Sample: FootArcher#60;Militia#10;LightCavalry#30;HeavyCavalry#1;CavalryArcher#3");
            }
        }

        System.out.println("\n____________________________");
        System.out.println("Please enter terrains (NA, if Not Applicable):");
        String terrainsString;
        List<Terrain> terrains = null;
        while (terrains == null) {
            terrainsString = inScanner.next();
            if (terrainsString.equals("NA")) terrainsString = "";
            terrains = Parser.parseTerrain(terrainsString);
            if (!BattleUtil.validateList(terrains)) {
                terrains = null;
                System.err.println("Enter valid format for terrain.");
                System.out.println("Sample: Plains;Default;Default;Default;Default");
            }
        }

        System.out.println("\n____________________________");
        System.out.println("Do you want to have artillery backup? (y/n):");
        String isArtilleryAvailableString;
        Boolean isArtilleryAvailable = null;
        while (isArtilleryAvailable == null) {
            isArtilleryAvailableString = inScanner.next();
            if (isArtilleryAvailableString.equals("y")) {
                isArtilleryAvailable = true;
            }
            if (isArtilleryAvailableString.equals("n")) {
                isArtilleryAvailable = false;
            }
        }

        Configurations.setArtilleryAvailable(isArtilleryAvailable);


        boolean isValidBattle = BattleUtil.validateBattleInputs(ourPlatoon, enemyPlatoon, terrains);

        if (isValidBattle) {
            BattleSimulator simulator = new BattleSimulator(ourPlatoon, enemyPlatoon, terrains);
            simulator.simulateBattles();
        } else {
            System.err.println("Our platoon or enemy platoon or terrain is doesn't have the same number of entries as" +
                    " # of battles.");
        }
    }
}
