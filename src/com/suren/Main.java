package com.suren;

import com.suren.com.suren.battle.simulator.BattleMaster;

/**
 * Main method for execution
 */
public class Main {
    /**
     * Main method to initialize Battle Master
     * @param args CLI input arguments
     */
    public static void main(String[] args) {
        BattleMaster battleMaster = new BattleMaster();
        // Initiates CLI input collection
        battleMaster.initiate();
    }
}
