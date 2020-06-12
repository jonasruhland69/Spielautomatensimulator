package model;

import controller.BlackJackController;

import java.io.File;
import java.net.MalformedURLException;
import java.util.Random;
import java.util.regex.Pattern;

public class BlackJack {
    private MainApp mainApp;
    private char[] cards = new char[52];
    private char[] playerCards = new char[5];
    private char[] opponentCards = new char[5];
    private String url = System.getProperty("user.dir")+ File.separator+"src"+File.separator+"view"+File.separator+
            "Images" + File.separator+"BlackJackCard";


    public void setUpCards(){
        char[] cardTypes= {'2','3','4','5','6','7','8','9','1','J','Q','K','A'};
        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 4; j++) {
                cards[i*4+j]=cardTypes[i];
            }
        }
    }

    public char getRandomCard(int max, int min){
        Random r = new Random();
        int random = r.nextInt((max - min) + 1) +min;
        while (random==' ')
            random = r.nextInt((max - min) + 1) +min;

        char card = cards[random];
        cards[random]=' ';
        return card;
    }

    public void play() throws MalformedURLException {

            setUpCards();
            playerCards= new char[5];
            opponentCards= new char[5];
            playerCards[0] = getRandomCard(cards.length-1,0);
            playerCards[1] = getRandomCard(cards.length-1,0);
            mainApp.getBlackJackController().updatePlayerCards();;
            mainApp.getBlackJackController().updatePlayerCardValue();

    }

    public void opponentTurn() throws MalformedURLException {
        setUpCards();
        opponentCards[0] = getRandomCard(cards.length-1,0);
        opponentCards[1] = getRandomCard(cards.length-1,0);
        mainApp.getBlackJackController().updateOpponentCards();
        mainApp.getBlackJackController().updateOpponentCardValue();
        while (!checkStand()){
            pullCard(opponentCards);
            mainApp.getBlackJackController().updateOpponentCards();
            mainApp.getBlackJackController().updateOpponentCardValue();
        }
        mainApp.getBlackJackController().calculateWinner();
    }

    public BlackJack(MainApp mainApp) {
        this.mainApp = mainApp;
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

    public void pullCard(char[] cards) {
        for (int i = 0; i < cards.length; i++) {
            if (cards[i]=='\u0000'){
                cards[i] = getRandomCard(this.cards.length-1,0);
                i = cards.length;
            }
        }
    }

    public boolean checkStand(){
        Random r = new Random();
        int random = r.nextInt((100 - 1) + 1) +1;
        if (Integer.parseInt(mainApp.getBlackJackController().getOpponentCardValue().getText())>=12) {
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
