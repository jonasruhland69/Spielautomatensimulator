package model;

import java.util.HashSet;

public class Player {
    private int coins;
    private HashSet<Game> ownedGames = new HashSet<>();

    public Player(int coins) {
        this.coins = coins;
        ownedGames.add(new SlotMachine());
    }

    public void addCoins(int coins){
        this.coins+=coins;
    }

    public int getCoins() {
        return coins;
    }
}
