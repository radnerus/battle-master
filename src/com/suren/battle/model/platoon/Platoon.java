package com.suren.battle.model.platoon;

import com.suren.battle.model.terrain.Terrain;

import java.util.List;

/**
 * Interface for all the platoon types
 */
public abstract class Platoon {
    /**
     * Name of the Platoon
     * @return name
     */
    public abstract String getPlatoonName();

    /**
     * Platoon count
     * @return count
     */
    public abstract int getPlatoonCount();

    /**
     * Platoons again whom the current platoon has advantage over
     * @return advantage over platoons
     */
    public abstract List<Class<? extends Platoon>> getAdvantageOverPlatoons();

    /**
     * Advantage terrain for the current platoon
     * @return advantage terrain
     */
    public abstract List<Terrain> getAdvantageTerrain();

    /**
     * Disadvantage terrain for the current platoon
     * @return disadvantage terrain
     */
    public abstract List<Terrain> getDisadvantageTerrain();
}
