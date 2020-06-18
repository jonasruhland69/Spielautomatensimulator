package model;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Random;

public class BlackJack implements Playable{
    private final MainApp mainApp;
    private final ArrayList<Character> cards = new ArrayList<>();
    private char[] playerCards = new char[7];
    private char[] opponentCards = new char[7];
    private final String url = System.getProperty("user.dir") + File.separator + "src" + File.separator + "view" + File.separator +
            "Images" + File.separator + "BlackJackCard";


    public BlackJack(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    /**
     * Kartendeck wird erstellt
     */
    public void setUpCards() {
        cards.clear();
        char[] cardTypes = {'2', '3', '4', '5', '6', '7', '8', '9', '1', 'J', 'Q', 'K', 'A'};
        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 4; j++) {
                cards.add(i * 4 + j, cardTypes[i]);
            }
        }
    }

    /**
     * Zufaellige Karte wird gesucht.
     *
     * @param max Maximaler Index der Karte.
     * @param min Minimaler Index der Karte.
     * @return Zufaellige Karte.
     */
    public char getRandomCard(int max, int min) {
        Random r = new Random();
        int random = r.nextInt((max - min) + 1) + min;
        while (random == ' ')
            random = r.nextInt((max - min) + 1) + min;

        char card = cards.get(random);
        cards.remove(random);
        return card;
    }

    /**
     * Deck wird erstellt, 7 Karten werden gezogen, die ersten 2 werden umgedreht und der Kartenwert wird aktualisiert.
     */
    @Override
    public void startGame(){
        try {
            mainApp.getBlackJackController().updatePlayerCards();
            setUpCards();
            playerCards = new char[7];
            opponentCards = new char[7];
            playerCards[0] = getRandomCard(cards.size() - 1, 0);
            playerCards[1] = getRandomCard(cards.size() - 1, 0);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            mainApp.getBlackJackController().updatePlayerCardValue();
        }

    }

    /**
     * Gegner macht automatisch seine Zuege
     *
     * @throws MalformedURLException
     */
    public void opponentTurn() throws MalformedURLException {
        setUpCards();
        opponentCards[0] = getRandomCard(cards.size() - 1, 0);
        opponentCards[1] = getRandomCard(cards.size() - 1, 0);
        mainApp.getBlackJackController().updateOpponentCards();
        mainApp.getBlackJackController().updateOpponentCardValue();
        while (!checkIfStand()) {
            pullCard(opponentCards);
            mainApp.getBlackJackController().updateOpponentCards();
            mainApp.getBlackJackController().updateOpponentCardValue();
        }
        mainApp.getBlackJackController().calculateWinner();
    }

    public char[] getPlayerCards() {
        return playerCards;
    }

    public char[] getOpponentCards() {
        return opponentCards;
    }

    public String getUrl() {
        return url;
    }

    /**
     * Zufaellige Karte wird aus Deck ausgewaehlt.
     *
     * @param cards
     */
    public void pullCard(char[] cards) {
        for (int i = 0; i < cards.length; i++) {
            if (cards[i] == '\u0000') {
                cards[i] = getRandomCard(this.cards.size() - 1, 0);
                i = cards.length;
            }
        }
    }

    /**
     * Es wird ermittelt, ob der Gegner noch eine Karte ziehen soll.
     *
     * @return Wenn noch Karte gezogen wird false       ansonsten true
     */
    public boolean checkIfStand() {
        Random r = new Random();
        int random = r.nextInt((100 - 1) + 1) + 1;
        if (Integer.parseInt(mainApp.getBlackJackController().getOpponentCardValue().getText()) >= 12) {
            switch (Integer.parseInt(mainApp.getBlackJackController().getOpponentCardValue().getText())) {
                case 12:
                    if (random > 30)
                        return false;
                    break;
                case 13:
                    if (random > 38)
                        return false;
                    break;
                case 14:
                    if (random > 55)
                        return false;
                    break;
                case 15:
                    if (random > 57)
                        return false;
                    break;
                case 16:
                    if (random > 61)
                        return false;
                    break;
                case 17:
                    if (random > 68)
                        return false;
                    break;
                default:
                    return true;
            }
        }
        return false;
    }
}
