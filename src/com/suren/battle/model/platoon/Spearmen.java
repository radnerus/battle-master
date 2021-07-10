package com.suren.battle.model.platoon;

import com.suren.battle.model.terrain.Terrain;

import java.util.ArrayList;
import java.util.List;

/**
 * Model for Spearmen Platoon
 */
public class Spearmen extends Platoon {
    private final String name = "Spearmen";
    private final int count;

    /**
     * Constructor for Spearmen with count
     * @param count # of spearmen
     */
    public Spearmen(int count) {
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
                add(LightCavalry.class);
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
        return List.of(Terrain.MUDDY);
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
        return "Spearmen{" +
                "name='" + name + '\'' +
                ", count=" + count +
                '}';
    }
}
