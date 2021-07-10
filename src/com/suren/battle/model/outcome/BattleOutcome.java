package com.suren.battle.model.outcome;

import com.suren.battle.model.Configurations;

import java.util.List;

public class BattleOutcome {
    private List<BattleResult> results;
    private int wins;
    private int losses;
    private int draws;

    public BattleOutcome(List<BattleResult> results) {
        this.results = results;
    }

    public BattleOutcome(List<BattleResult> results, int wins, int losses, int draws) {
        this.results = results;
        this.wins = wins;
        this.losses = losses;
        this.draws = draws;
    }

    public List<BattleResult> getResults() {
        return results;
    }

    public void setResults(List<BattleResult> results) {
        this.results = results;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public int getDraws() {
        return draws;
    }

    public void setDraws(int draws) {
        this.draws = draws;
    }

    public ResultType overAllResult() {
        return (wins > (Configurations.getBattleCount() / 2)) ? ResultType.WIN : (wins == losses) ?
                ResultType.DRAW : ResultType.LOSS;
    }

    @Override
    public String toString() {
        return "BattleOutcome{" +
                "result" + results +
                "wins=" + wins +
                ", losses=" + losses +
                ", draws=" + draws +
                '}';
    }
}
