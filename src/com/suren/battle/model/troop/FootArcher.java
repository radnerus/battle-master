package com.suren.battle.model.troop;

import com.suren.battle.model.terrain.Terrain;

import java.util.ArrayList;
import java.util.List;

public class FootArcher extends SoldierType {
    private final String name = "FootArcher";
    private final int count;

    public FootArcher(int count) {
        this.count = count;
    }

    /**
     * Name of the Troops
     *
     * @return name
     */
    @Override
    public String getSoldierName() {
        return name;
    }

    /**
     * Troop count
     *
     * @return count
     */
    @Override
    public int getSoldiersCount() {
        return count;
    }

    /**
     * Troops again whom the current troop has advantage over
     *
     * @return advantage over troops
     */
    @Override
    public List<Class<? extends SoldierType>> getAdvantageOverSoldiers() {
        return new ArrayList<>() {
            {
                add(Militia.class);
                add(CavalryArcher.class);
            }
        };
    }

    /**
     * Advantage terrain for the current troop
     *
     * @return advantage terrain
     */
    @Override
    public List<Terrain> getAdvantageTerrain() {
        return List.of(Terrain.HILL, Terrain.MUDDY);
    }

    /**
     * Disadvantage terrain for the current troop
     *
     * @return disadvantage terrain
     */
    @Override
    public List<Terrain> getDisadvantageTerrain() {
        return new ArrayList<>();
    }

    @Override
    public String toString() {
        return "FootArcher{" +
                "name='" + name + '\'' +
                ", count=" + count +
                '}';
    }
}
