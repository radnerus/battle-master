package com.suren.com.suren.battle.simulator;

import com.suren.battle.model.Configurations;
import com.suren.battle.model.outcome.BattleOutcome;
import com.suren.battle.model.outcome.BattleResult;
import com.suren.battle.model.outcome.ResultType;
import com.suren.battle.model.terrain.Terrain;
import com.suren.battle.model.troop.SoldierType;
import com.suren.com.suren.battle.simulator.util.Parser;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to simulate battles for the given troops & terrains
 */
public class BattleSimulator {
    private List<SoldierType> ourTroops;
    private List<SoldierType> enemyTroops;
    private List<Terrain> terrains;
    private BattleOutcome battleOutcome;
    private boolean isWinningCombinationFound = false;
    // TODO remove while committing
    private int executionCount = 0;
    private boolean canUserArtillery;

    /**
     * Constructor for Battle simulator
     * @param ourTroops
     * @param enemyTroops
     * @param terrains
     */
    public BattleSimulator(List<SoldierType> ourTroops, List<SoldierType> enemyTroops, List<Terrain> terrains) {
        this.ourTroops = ourTroops;
        this.enemyTroops = enemyTroops;
        this.terrains = terrains;
    }

    public void simulateBattles() {
//        System.out.println("Beginning Simulation");
        startSimulationWithDifferentCombinations(this.ourTroops);
        if (!isWinningCombinationFound) {
            System.out.println("There is no chance of winning");
        } else {
            System.out.println("\n\nWinning Combination");
            System.out.println("-------------------\n");
            for (BattleResult battleResult: battleOutcome.getResults()) {
                SoldierType enemyTroop = battleResult.getEnemyTroop();
                SoldierType ourTroop = battleResult.getOurTroop();
                System.out.println(
                        enemyTroop.getSoldierName() + "-" + enemyTroop.getSoldiersCount() + " X " +
                        ourTroop.getSoldierName() + "-" + ourTroop.getSoldiersCount() + " => "
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

    private @NotNull
    BattleOutcome simulateBatch(List<SoldierType> ourTroops) {
        executionCount++;
        int wins = 0;
        int losses = 0;
        int draws = 0;
        List<BattleResult> results = new ArrayList<>();
        canUserArtillery = Configurations.isArtilleryAvailable();
        for (int i = 0; i < Configurations.getBattleCount(); i++) {
            SoldierType ourTroop = ourTroops.get(i);
            SoldierType enemyTroop = this.enemyTroops.get(i);
            Terrain battleTerrain = this.terrains.get(i);

//            System.out.println(ourTroop.getSoldierName() + "-" + ourTroop.getSoldiersCount() + " X " + enemyTroop.getSoldierName() + "-" + enemyTroop.getSoldiersCount() + " in " + battleTerrain );

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
//            System.out.println(result.getResult());
            results.add(result);
        }

        return new BattleOutcome(results, wins, losses, draws);
    }

    /**
     * @param ourTroop
     * @param enemyTroop
     * @param battleTerrain
     * @return
     */
    private BattleResult simulateBattle(SoldierType ourTroop, SoldierType enemyTroop, Terrain battleTerrain) {
        List<Class<? extends  SoldierType>> ourAdvantages = ourTroop.getAdvantageOverSoldiers();
        List<Class<? extends  SoldierType>> enemyAdvantages = enemyTroop.getAdvantageOverSoldiers();

        List<Terrain> ourAdvantageTerrain = ourTroop.getAdvantageTerrain();
        List<Terrain> ourDisadvantageTerrain = ourTroop.getDisadvantageTerrain();

        List<Terrain> enemyAdvantageTerrain = enemyTroop.getAdvantageTerrain();
        List<Terrain> enemyDisadvantageTerrain = enemyTroop.getDisadvantageTerrain();


        int ourEffectiveTroopCount = ourTroop.getSoldiersCount();
        int enemyEffectiveTroopCount = enemyTroop.getSoldiersCount();

//        System.out.println(ourTroop.getClass());
//        System.out.println(ourAdvantages.contains(enemyTroop.getClass()));
//        System.out.println(enemyTroop.getClass());
//        System.out.println(enemyAdvantages.contains(ourTroop.getClass()));

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

//        System.out.println(ourEffectiveTroopCount  - enemyEffectiveTroopCount);
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

    public void startSimulationWithDifferentCombinations(List<SoldierType> troops){
        getCombinationAndSimulate(troops, 0);
    }

    private BattleOutcome getCombinationAndSimulate(List<SoldierType> troops, int pos){
        if (this.isWinningCombinationFound) {
            return null;
        }
        if(pos >= troops.size() - 1){
            return simulateBatch(troops);
        }

        for(int i = pos; i < troops.size(); i++){

            SoldierType t = troops.get(pos);
            troops.set(pos, troops.get(i));
            troops.set(i, t);

            BattleOutcome outcome = getCombinationAndSimulate(troops, pos+1);

//            System.out.println("++++++++++++++++++++++" + (outcome != null ? outcome.overAllResult() : "") + "+++++++++++++++" + executionCount);

//            System.out.println(outcome);
//            if (outcome.ge)
            if (outcome != null && outcome.overAllResult().equals(ResultType.WIN)) {
//                System.out.println("++++++++++++++++++++++" + executionCount);
//                System.out.println(troops);
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
