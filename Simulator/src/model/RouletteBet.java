package model;

import java.util.HashSet;

public class RouletteBet {
    private HashSet<Integer> fields = new HashSet<>();
    private int bet;

    public RouletteBet(int bet) {
        this.bet = bet;
    }

    public void setFields(HashSet<Integer> fields) {
        this.fields = fields;
    }
}
