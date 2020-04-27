package model;

import java.io.Serializable;
import java.util.HashSet;

public class Player implements Serializable {
    private static final long serialVersionUID = -5595229778182018687L;
    private String name;
    private int coins;
    private HashSet<Game> ownedGames = new HashSet<>();

    public Player(int coins, String name) {
        this.coins = coins;
        ownedGames.add(new SlotMachine());
        this.name = name;
    }

    public void addCoins(int coins){
        this.coins+=coins;
    }

    public int getCoins() {
        return coins;
    }

    public String getName() {
        return name;
    }

    public HashSet<Game> getOwnedGames() {
        return ownedGames;
    }
}
