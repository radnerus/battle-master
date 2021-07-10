package com.suren.battle.model.platoon;

import com.suren.battle.model.terrain.Terrain;

import java.util.ArrayList;
import java.util.List;

/**
 * Model for HeavyCavalry
 */
public class HeavyCavalry extends Platoon {
    private final String name = "HeavyCavalry";
    private final int count;

    /**
     * Constructor for HeavyCavalry with count
     * @param count # of HeavyCavalry
     */
    public HeavyCavalry(int count) {
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
                add(Militia.class);
                add(FootArcher.class);
                add(LightCavalry.class);
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
        return "HeavyCavalry{" +
                "name='" + name + '\'' +
                ", count=" + count +
                '}';
    }
}
