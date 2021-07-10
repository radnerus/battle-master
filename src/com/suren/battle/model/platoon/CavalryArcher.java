package com.suren.battle.model.platoon;

import com.suren.battle.model.terrain.Terrain;

import java.util.ArrayList;
import java.util.List;

/**
 * Model for CavalryArcher
 */
public class CavalryArcher extends Platoon {
    private final String name = "CavalryArcher";
    private final int count;

    /**
     * Constructor for CavalryArcher with count
     * @param count count of CavalryArcher
     */
    public CavalryArcher(int count) {
        this.count = count;
    }
    /**
     * Name of the Platoons
     *
     * @return name
     */
    @Override
    public String getPlatoonName() {
        return name;
    }

    /**
     * Platoon count
     *
     * @return count
     */
    @Override
    public int getPlatoonCount() {
        return count;
    }

    /**
     * Platoons again whom the current Platoon has advantage over
     *
     * @return advantage over Platoons
     */
    @Override
    public List<Class<? extends Platoon>> getAdvantageOverPlatoons() {
        return new ArrayList<>() {
            {
                add(Spearmen.class);
                add(HeavyCavalry.class);
            }
        };
    }

    /**
     * Advantage terrain for the current Platoon
     *
     * @return advantage terrain
     */
    @Override
    public List<Terrain> getAdvantageTerrain() {
        return List.of(Terrain.HILL, Terrain.PLAINS);
    }

    /**
     * Disadvantage terrain for the current Platoon
     *
     * @return disadvantage terrain
     */
    @Override
    public List<Terrain> getDisadvantageTerrain() {
        return new ArrayList<>();
    }

    @Override
    public String toString() {
        return "CavalryArcher{" +
                "name='" + name + '\'' +
                ", count=" + count +
                '}';
    }
}
