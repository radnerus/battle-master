package com.suren.battle.model.outcome;

import com.suren.battle.model.Configurations;

import java.util.List;

/**
 * Model for Battle Combination Outcome
 */
public class BattleOutcome {
    private List<BattleResult> results;
    private int wins;
    private int losses;
    private int draws;

    /**
     * Constructor for battle outcome
     * @param results list of battle results
     * @param wins # of wins
     * @param losses @ of losses
     * @param draws # of draws
     */
    public BattleOutcome(List<BattleResult> results, int wins, int losses, int draws) {
        this.results = results;
        this.wins = wins;
        this.losses = losses;
        this.draws = draws;
    }

    /**
     * Getter for result list
     * @return list of results
     */
    public List<BattleResult> getResults() {
        return results;
    }

    /**
     * Computed and returns the overall result of battle combination
     * @return result of the battle batch combination
     */
    public ResultType getOverAllResult() {
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
