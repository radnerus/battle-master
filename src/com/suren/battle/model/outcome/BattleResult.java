package com.suren.battle.model.outcome;

import com.suren.battle.model.terrain.Terrain;
import com.suren.battle.model.platoon.Platoon;

/**
 * Model for Battle result
 */
public class BattleResult {
    private Platoon ourTroop;
    private Platoon enemyTroop;
    private Terrain terrain;
    private ResultType result;
    private boolean isArtilleryUsed;

    /**
     * Constructor for battle result of a give combination of ours and enemy's platoons
     * @param ourTroop Our troop
     * @param enemyTroop enemy's troop
     * @param terrain battle terrain
     * @param result battle result
     * @param isArtilleryUsed is artillery used in this battle
     */
    public BattleResult(Platoon ourTroop, Platoon enemyTroop, Terrain terrain, ResultType result, boolean isArtilleryUsed) {
        this.ourTroop = ourTroop;
        this.enemyTroop = enemyTroop;
        this.terrain = terrain;
        this.result = result;
        this.isArtilleryUsed = isArtilleryUsed;
    }

    /**
     * Getter for our troops
     * @return our troop
     */
    public Platoon getOurTroop() {
        return ourTroop;
    }

    /**
     * Getter for enemy's troops
     * @return enemy's troop
     */
    public Platoon getEnemyTroop() {
        return enemyTroop;
    }

    /**
     * Getter for battle terrain
     * @return battle terrain
     */
    public Terrain getTerrain() {
        return terrain;
    }

    /**
     * Result of the battle
     * @return result
     */
    public ResultType getResult() {
        return result;
    }

    /**
     * Getter to check if artillery is used for this battle
     * @return true, if artillery used
     */
    public boolean isArtilleryUsed() {
        return isArtilleryUsed;
    }

    @Override
    public String toString() {
        return "BattleResult{" +
                "ourTroop=" + ourTroop +
                ", enemyTroop=" + enemyTroop +
                ", terrain=" + terrain +
                ", result=" + result +
                ", isArtilleryUsed=" + isArtilleryUsed +
                '}';
    }
}
