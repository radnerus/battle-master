package com.suren.com.suren.battle.simulator;

import com.suren.battle.model.Configurations;
import com.suren.battle.model.outcome.BattleOutcome;
import com.suren.battle.model.outcome.BattleResult;
import com.suren.battle.model.outcome.ResultType;
import com.suren.battle.model.terrain.Terrain;
import com.suren.battle.model.platoon.Platoon;
import com.suren.com.suren.battle.simulator.util.Parser;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to simulate battles for the given troops & terrains
 */
public class BattleSimulator {
    private final List<Platoon> ourTroops;
    private final List<Platoon> enemyTroops;
    private final List<Terrain> terrains;
    private BattleOutcome battleOutcome;
    private boolean isWinningCombinationFound = false;
    private boolean canUserArtillery;

    /**
     * Constructor for Battle simulator
     * @param ourTroops List containing our troops
     * @param enemyTroops List containing enemy's troops
     * @param terrains Terrains of battle
     */
    public BattleSimulator(List<Platoon> ourTroops, List<Platoon> enemyTroops, List<Terrain> terrains) {
        this.ourTroops = ourTroops;
        this.enemyTroops = enemyTroops;
        this.terrains = terrains;
    }

    /**
     * Method to simulate all battles
     */
    public void simulateBattles() {
        startSimulationWithDifferentCombinations(this.ourTroops);
        displayBattleResults();
    }

    /**
     * Method to display results of the battle
     */
    private void displayBattleResults() {
        if (!isWinningCombinationFound) {
            System.out.println("There is no chance of winning");
        } else {
            System.out.println("\n\nWinning Combination");
            System.out.println("-------------------\n");
            for (BattleResult battleResult: battleOutcome.getResults()) {
                Platoon enemyTroop = battleResult.getEnemyTroop();
                Platoon ourTroop = battleResult.getOurTroop();
                System.out.println(
                        enemyTroop.getPlatoonName() + "-" + enemyTroop.getPlatoonCount() + " X " +
                        ourTroop.getPlatoonName() + "-" + ourTroop.getPlatoonCount() + " => "
                                + battleResult.getResult()
                );
            }
            System.out.println("\n");
            System.out.println(Parser.getOutcomeString(battleOutcome.getResults()));
            if (Configurations.isArtilleryAvailable()) {
                System.out.println(Parser.getArtilleryUsedString(battleOutcome.getResults()));
            }
        }
    }

    /**
     * Method to simulate a particular batch of results
     * @param ourTroops List of our soilders in the branch
     * @return the battle outcome
     */
    private BattleOutcome simulateBatch(List<Platoon> ourTroops) {
        int wins = 0;
        int losses = 0;
        int draws = 0;
        List<BattleResult> results = new ArrayList<>();
        canUserArtillery = Configurations.isArtilleryAvailable();
        for (int i = 0; i < Configurations.getBattleCount(); i++) {
            Platoon ourTroop = ourTroops.get(i);
            Platoon enemyTroop = this.enemyTroops.get(i);
            Terrain battleTerrain = this.terrains.get(i);

            BattleResult result = simulateBattle(ourTroop, enemyTroop, battleTerrain);
            switch (result.getResult()) {
                case WIN:
                    wins++;
                    break;
                case LOSS:
                    losses++;
                    break;
                case DRAW:
                    draws++;
                    break;
            }

            results.add(result);
        }
        return new BattleOutcome(results, wins, losses, draws);
    }

    /**
     * Method to simulate battle and calculate the result
     * @param ourTroop list of our troops
     * @param enemyTroop list of enemy's troops
     * @param battleTerrain list of terrains
     * @return the result of the battle
     */
    private BattleResult simulateBattle(Platoon ourTroop, Platoon enemyTroop, Terrain battleTerrain) {
        List<Class<? extends Platoon>> ourAdvantages = ourTroop.getAdvantageOverPlatoons();
        List<Class<? extends Platoon>> enemyAdvantages = enemyTroop.getAdvantageOverPlatoons();

        List<Terrain> ourAdvantageTerrain = ourTroop.getAdvantageTerrain();
        List<Terrain> ourDisadvantageTerrain = ourTroop.getDisadvantageTerrain();

        List<Terrain> enemyAdvantageTerrain = enemyTroop.getAdvantageTerrain();
        List<Terrain> enemyDisadvantageTerrain = enemyTroop.getDisadvantageTerrain();


        int ourEffectiveTroopCount = ourTroop.getPlatoonCount();
        int enemyEffectiveTroopCount = enemyTroop.getPlatoonCount();

        if (ourAdvantages.contains(enemyTroop.getClass())) {
            ourEffectiveTroopCount *= 2;
        }

        if (enemyAdvantages.contains(ourTroop.getClass())) {
            enemyEffectiveTroopCount *= 2;
        }

        if (ourAdvantageTerrain.contains(battleTerrain)) {
            ourEffectiveTroopCount *= 2;
        }

        if (ourDisadvantageTerrain.contains(battleTerrain)) {
            ourEffectiveTroopCount *= 0.5;
        }

        if (enemyAdvantageTerrain.contains(battleTerrain)) {
            enemyEffectiveTroopCount *= 2;
        }

        if (enemyDisadvantageTerrain.contains(battleTerrain)) {
            enemyEffectiveTroopCount *= 0.5;
        }

        ResultType result = ResultType.DRAW;
        boolean isWonByArtillery = false;
        if (ourEffectiveTroopCount > enemyEffectiveTroopCount) {
            result = ResultType.WIN;
        } else if (canUserArtillery && ((ourEffectiveTroopCount + 50) > enemyEffectiveTroopCount)) {
            canUserArtillery = false;
            isWonByArtillery = true;
            result = ResultType.WIN;
        } else if (ourEffectiveTroopCount < enemyEffectiveTroopCount) {
            result = ResultType.LOSS;
        }

        return new BattleResult(ourTroop, enemyTroop, battleTerrain, result, isWonByArtillery);
    }

    /**
     * Method that give multiple combinations of our troops
     * @param troops list of our troops
     */
    public void startSimulationWithDifferentCombinations(List<Platoon> troops){
        getCombinationAndSimulate(troops, 0);
    }

    /**
     * Recursive method to get different combinations till we find a winning combination
     * @param troops list of troops
     * @param pos position in the battle
     * @return outcome of the battle
     */
    private BattleOutcome getCombinationAndSimulate(List<Platoon> troops, int pos){
        if (this.isWinningCombinationFound) {
            return null;
        }
        if(pos >= troops.size() - 1){
            return simulateBatch(troops);
        }

        for(int i = pos; i < troops.size(); i++){
            Platoon t = troops.get(pos);
            troops.set(pos, troops.get(i));
            troops.set(i, t);

            BattleOutcome outcome = getCombinationAndSimulate(troops, pos+1);

            if (outcome != null && outcome.getOverAllResult().equals(ResultType.WIN)) {
                this.isWinningCombinationFound = true;
                this.battleOutcome = outcome;
                break;
            }

            t = troops.get(pos);
            troops.set(pos, troops.get(i));
            troops.set(i, t);
        }
        return null;
    }
}
