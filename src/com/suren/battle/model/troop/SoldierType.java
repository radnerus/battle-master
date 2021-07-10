package com.suren.battle.model.troop;

import com.suren.battle.model.terrain.Terrain;

import java.util.List;

public abstract class SoldierType {
    /**
     * Name of the Troops
     * @return name
     */
    public abstract String getSoldierName();

    /**
     * Troop count
     * @return count
     */
    public abstract int getSoldiersCount();

    /**
     * Troops again whom the current troop has advantage over
     * @return advantage over troops
     */
    public abstract List<Class<? extends SoldierType>> getAdvantageOverSoldiers();

    /**
     * Advantage terrain for the current soldier
     * @return advantage terrain
     */
    public abstract List<Terrain> getAdvantageTerrain();

    /**
     * Disadvantage terrain for the current troop
     * @return disadvantage terrain
     */
    public abstract List<Terrain> getDisadvantageTerrain();
}
