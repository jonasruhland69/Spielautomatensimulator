package model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;

public class Player implements Serializable {
    private static final long serialVersionUID = -5595229778182018687L;
    private String name;
    private int coins;
    private HashMap<String,Boolean> ownedGames = new HashMap<>();

    public Player(int coins, String name) {
        this.coins = coins;
        ownedGames.put("Slotmachine",true);
        ownedGames.put("Black Jack",false);
        ownedGames.put("Roulette",false);
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

    public HashMap<String,Boolean> getOwnedGames() {
        return ownedGames;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }
}
