package model;

import java.io.Serializable;
import java.util.HashMap;

public class Player{
    private String name;
    private int coins;
    private final HashMap<String, Boolean> ownedGames = new HashMap<>();

    public Player(int coins, String name) {
        this.coins = coins;
        ownedGames.put("Slotmachine", false);
        ownedGames.put("Black Jack", true);
        ownedGames.put("Roulette", false);
        this.name = name;
    }

    public void addCoins(int coins) {
        this.coins += coins;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashMap<String, Boolean> getOwnedGames() {
        return ownedGames;
    }
}
