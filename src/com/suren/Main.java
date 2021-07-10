package com.suren;

import com.suren.battle.model.Configurations;
import com.suren.battle.model.terrain.Terrain;
import com.suren.battle.model.troop.SoldierType;
import com.suren.com.suren.battle.simulator.BattleMaster;
import com.suren.com.suren.battle.simulator.BattleSimulator;
import com.suren.com.suren.battle.simulator.util.BattleUtil;
import com.suren.com.suren.battle.simulator.util.Parser;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        BattleMaster battleMaster = new BattleMaster();
        battleMaster.initiate();
    }
}
