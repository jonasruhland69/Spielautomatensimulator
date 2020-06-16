package model;

import javafx.scene.control.Label;

import java.util.*;

public class Roulette {
    MainApp mainApp;
    HashMap<String,RouletteBet> rouletteBets = new HashMap<>();

    public Roulette(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public HashMap<String,RouletteBet> getRouletteBets() {
        return rouletteBets;
    }

    public void spin() throws InterruptedException {
        calculateRouletteBetFields();
        mainApp.getRouletteController().selectionAnimation();
    }

    private void calculateRouletteBetFields() {
        for (String s : rouletteBets.keySet()) {
            rouletteBets.get(s).calculateFields(s,mainApp.getRouletteController().getFields());
        }
    }

    public void calcWinning() {
        HashMap<String,RouletteBet> winningBets = new HashMap<>();
        for (String s : rouletteBets.keySet()) {
            for (Integer s1 : rouletteBets.get(s).getFields()) {
                if (Integer.parseInt(mainApp.getRouletteController().getCurrentField().getText())==(s1))
                    winningBets.put(s,rouletteBets.get(s));
            }
        }
        int win=0;
        if (!winningBets.isEmpty()){
            for (String s : winningBets.keySet()) {
                if ((s.length()==1 || s.length()==2)&& !s.equals("0")){
                    win+=winningBets.get(s).getBet()*36;
                } else if (s.equals("1-12")|| s.equals("13-24")||s.equals("25-36")){
                    win+=winningBets.get(s).getBet()*3;
                } else
                    win+=winningBets.get(s).getBet()*2;
            }
            mainApp.getPlayer().addCoins(win);
            mainApp.getRouletteController().setWinLoseLabel("Win!");
        }else
            mainApp.getRouletteController().setWinLoseLabel("Lose!");
        if (win>0){
            mainApp.getRouletteController().getLastGame().setText("+"+ (win - getTotalBet()));
        }else
            mainApp.getRouletteController().getLastGame().setText(String.valueOf(win-getTotalBet()));
    }

    public int getTotalBet(){
        int totalBet= 0;
        for (RouletteBet value : rouletteBets.values()) {
            totalBet+=value.getBet();
        }
        return totalBet;
    }
}
