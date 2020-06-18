package model;

import java.util.HashMap;

public class Roulette implements Playable{
    private MainApp mainApp;
    private HashMap<String, RouletteBet> rouletteBets = new HashMap<>();

    public Roulette(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public HashMap<String, RouletteBet> getRouletteBets() {
        return rouletteBets;
    }

    /**
     * Einsaetze werden eingelesen und Auswahlanimation wird angezeigt.
     *
     */
    @Override
    public void startGame(){
        try {
            calculateRouletteBetFields();
            mainApp.getRouletteController().selectionAnimation();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gewinnfelder von Einsaetzen werden herausgefunden.
     */
    private void calculateRouletteBetFields() {
        for (String s : rouletteBets.keySet()) {
            rouletteBets.get(s).calculateFields(s, mainApp.getRouletteController().getFields());
        }
    }

    /**
     * Einsaetze, die das Gewinnfeld miteinbeziehen werden ermittelt und  Einsaetze werden ausgewertet.
     */
    public void calcWinning() {
        HashMap<String, RouletteBet> winningBets = new HashMap<>();
        for (String s : rouletteBets.keySet()) {
            for (Integer s1 : rouletteBets.get(s).getFields()) {
                //Wenn ein Feld des Einsatzes gleich dem Gewinnerfeld, dann ist dieser Einsatz ein Gewinneeinsatz
                if (Integer.parseInt(mainApp.getRouletteController().getCurrentField().getText()) == (s1))
                    winningBets.put(s, rouletteBets.get(s));
            }
        }
        int win = 0;
        //Wenn Gewinneinsatz nicht leer, dann werden Einsatzspezifische Multiplikatoren zur Gewinnermittlung benutzt.
        if (!winningBets.isEmpty()) {
            for (String s : winningBets.keySet()) {
                if ((s.length() == 1 || s.length() == 2) && !s.equals("0")) {
                    win += winningBets.get(s).getBet() * 36;
                } else if (s.equals("1-12") || s.equals("13-24") || s.equals("25-36")) {
                    win += winningBets.get(s).getBet() * 3;
                } else
                    win += winningBets.get(s).getBet() * 2;
            }
            mainApp.getPlayer().addCoins(win);
            mainApp.getRouletteController().setWinLoseLabel("Win!");
        } else
            mainApp.getRouletteController().setWinLoseLabel("Lose!");
        if (win > 0) {
            mainApp.getRouletteController().getLastGame().setText("+" + (win - getTotalBet()));
        } else
            mainApp.getRouletteController().getLastGame().setText(String.valueOf(win - getTotalBet()));
    }

    /**
     * Alle Einsaetze werden zusammengezaehlt.
     *
     * @return Gesamter Einsatz.
     */
    public int getTotalBet() {
        int totalBet = 0;
        for (RouletteBet value : rouletteBets.values()) {
            totalBet += value.getBet();
        }
        return totalBet;
    }
}
