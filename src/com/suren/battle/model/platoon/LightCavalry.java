package com.suren.battle.model.platoon;

import com.suren.battle.model.terrain.Terrain;

import java.util.ArrayList;
import java.util.List;

/**
 * Model for LightCavalry
 */
public class LightCavalry extends Platoon {
    private final String name = "LightCavalry";
    private final int count;

    /**
     * Constructor for LightCavalry with count
     * @param count # of LightCavalry
     */
    public LightCavalry(int count) {
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
                add(FootArcher.class);
                add(CavalryArcher.class);
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
        return List.of(Terrain.PLAINS);
    }

    /**
     * Disadvantage terrain for the current Platoon
     *
     * @return disadvantage terrain
     */
    @Override
    public List<Terrain> getDisadvantageTerrain() {
        return List.of(Terrain.HILL);
    }

    @Override
    public String toString() {
        return "LightCavalry{" +
                "name='" + name + '\'' +
                ", count=" + count +
                '}';
    }
}
