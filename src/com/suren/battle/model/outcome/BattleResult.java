package com.suren.battle.model.outcome;

import com.suren.battle.model.terrain.Terrain;
import com.suren.battle.model.troop.SoldierType;

public class BattleResult {
    private SoldierType ourTroop;
    private SoldierType enemyTroop;
    private Terrain terrain;
    private ResultType result;
    private boolean isArtilleryUsed;

    public BattleResult(SoldierType ourTroop, SoldierType enemyTroop, Terrain terrain, ResultType result, boolean isArtilleryUsed) {
        this.ourTroop = ourTroop;
        this.enemyTroop = enemyTroop;
        this.terrain = terrain;
        this.result = result;
        this.isArtilleryUsed = isArtilleryUsed;
    }

    public SoldierType getOurTroop() {
        return ourTroop;
    }

    public void setOurTroop(SoldierType ourTroop) {
        this.ourTroop = ourTroop;
    }

    public SoldierType getEnemyTroop() {
        return enemyTroop;
    }

    public void setEnemyTroop(SoldierType enemyTroop) {
        this.enemyTroop = enemyTroop;
    }

    public Terrain getTerrain() {
        return terrain;
    }

    public void setTerrain(Terrain terrain) {
        this.terrain = terrain;
    }

    public ResultType getResult() {
        return result;
    }

    public void setResult(ResultType result) {
        this.result = result;
    }

    public boolean isArtilleryUsed() {
        return isArtilleryUsed;
    }

    public void setArtilleryUsed(boolean artilleryUsed) {
        isArtilleryUsed = artilleryUsed;
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
