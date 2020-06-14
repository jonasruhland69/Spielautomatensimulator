package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import model.MainApp;

import java.io.File;
import java.io.FileInputStream;
import java.net.MalformedURLException;
import java.util.regex.Pattern;

public class BlackJackController {
    MainApp mainApp;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    private Button hitButton;

    @FXML
    private Button standButton;

    @FXML
    private ImageView card1;

    @FXML
    private ImageView card2;

    @FXML
    private ImageView card3;

    @FXML
    private ImageView card4;

    @FXML
    private ImageView card5;

    @FXML
    private ImageView card6;

    @FXML
    private ImageView card7;

    @FXML
    private ImageView opponentCard1;

    @FXML
    private ImageView opponentCard2;

    @FXML
    private ImageView opponentCard3;

    @FXML
    private ImageView opponentCard4;

    @FXML
    private ImageView opponentCard5;

    @FXML
    private ImageView opponentCard6;

    @FXML
    private ImageView opponentCard7;

    @FXML
    private TextField betField;

    @FXML
    private Button playButton;

    @FXML
    private TextField bankField;

    @FXML
    private Label backToGameSelection;

    @FXML
    private Label winLoseLabel;

    @FXML
    private Label playerCardValue;

    @FXML
    private Label opponentCardValue;

    public void updateBank(){
        bankField.setText(String.valueOf(mainApp.getPlayer().getCoins()));
    }

    @FXML
    void backtoGameSelectionClicked(MouseEvent event) {
        mainApp.loadGameSelection();
    }

    @FXML
    void hitButtonPressed(ActionEvent event) throws MalformedURLException {
        mainApp.getBlackJack().pullCard(mainApp.getBlackJack().getPlayerCards());
        updatePlayerCards();
        updatePlayerCardValue();
        if (Integer.parseInt(playerCardValue.getText())>21){
            gameLost();
        }
    }

    @FXML
    void playButtonPressed(ActionEvent event) throws MalformedURLException {
        if (Pattern.matches("\\d+", getBet().getText()) && Integer.parseInt(getBet().getText()) <= mainApp.getPlayer().getCoins()) {
            mainApp.getBlackJackController().getBet().setStyle("-fx-border-color: green");
            resetCards();
            playerCardValue.setText("0");
            opponentCardValue.setText("0");
            mainApp.getBlackJack().play();
            winLoseLabel.setText("");
            hitButton.setDisable(false);
            standButton.setDisable(false);
            playButton.setDisable(true);
            betField.setEditable(false);
            backToGameSelection.setDisable(true);
            updateOpponentCardValue();
            updatePlayerCardValue();
            updatePlayerCards();
            updateOpponentCards();
        }else
            mainApp.getBlackJackController().getBet().setStyle("-fx-border-color: red");
    }

    private void resetCards() throws MalformedURLException {
        String urlBlackJackCardBack = System.getProperty("user.dir")+ File.separator+"src"+File.separator+"view"+File.separator+
                "Images" + File.separator+"BlackJackCardBack.png";
        ImageView[] playerCardImages = {card1,card2,card3,card4,card5,card6,card7};
        ImageView[] opponentCardImages = {opponentCard1,opponentCard2,opponentCard3,opponentCard4,opponentCard5,opponentCard6,opponentCard7};
        for (int i = 0; i < playerCardImages.length; i++) {
            playerCardImages[i].setImage(new Image(new File(urlBlackJackCardBack).toURI().toURL().toString()));
            opponentCardImages[i].setImage(new Image(new File(urlBlackJackCardBack).toURI().toURL().toString()));
        }
    }

    @FXML
    void standButtonPressed(ActionEvent event) throws MalformedURLException {
        mainApp.getBlackJack().opponentTurn();
    }

    public TextField getBet() {
        return betField;
    }

    public void updatePlayerCards() throws MalformedURLException {
        char[] playerCards = mainApp.getBlackJack().getPlayerCards();
        ImageView[] playerCardImages = {card1,card2,card3,card4,card5,card6,card7};

        for (int i = 0; i < playerCardImages.length; i++) {
            if (playerCards[i]!='\u0000'){
                playerCardImages[i].setImage(new Image(new File(mainApp.getBlackJack().getUrl()).toURI().toURL().toString()+playerCards[i]+".png"));
            }
        }

    }

    public void updateOpponentCards() throws MalformedURLException {
        char[] opponentCards = mainApp.getBlackJack().getOpponentCards();
        ImageView[] opponentCardImages = {opponentCard1,opponentCard2,opponentCard3,opponentCard4,opponentCard5,opponentCard6,opponentCard7};

        for (int i = 0; i < opponentCardImages.length; i++) {
            if (opponentCards[i]!='\u0000'){
                opponentCardImages[i].setImage(new Image(new File(mainApp.getBlackJack().getUrl()).toURI().toURL().toString()+opponentCards[i]+".png"));
            }
        }
    }

    public void updatePlayerCardValue(){
        int value = 0;
        char[] playerCards = mainApp.getBlackJack().getPlayerCards();
        for (int i = 0; i < playerCards.length; i++) {
            if (playerCards[i]>49&&playerCards[i]<58){
                value += Integer.parseInt(String.valueOf(playerCards[i]));
            } else if (playerCards[i]=='J'||playerCards[i]=='Q'||playerCards[i]=='K'||playerCards[i]=='1')
                value +=10;
        }

        for (int i = 0; i < playerCards.length; i++) {
            if (playerCards[i]==65){
                if (value+11>21)
                    value+=1;
                else
                    value+=11;
            }
        }
        playerCardValue.setText(String.valueOf(value));
    }

    public void updateOpponentCardValue(){
        int value = 0;
        char[] opponentCards = mainApp.getBlackJack().getOpponentCards();
        for (int i = 0; i < opponentCards.length; i++) {
            if (opponentCards[i]>49&&opponentCards[i]<58){
                value += Integer.parseInt(String.valueOf(opponentCards[i]));
            } else if (opponentCards[i]=='J'||opponentCards[i]=='Q'||opponentCards[i]=='K'||opponentCards[i]=='1')
                value +=10;
        }

        for (int i = 0; i < opponentCards.length; i++) {
            if (opponentCards[i]==65){
                if (value+11>21)
                    value+=1;
                else
                    value+=11;
            }
        }
        opponentCardValue.setText(String.valueOf(value));
    }

    public void gameLost(){
        winLoseLabel.setText("Lost!");
        mainApp.getPlayer().setCoins(mainApp.getPlayer().getCoins()-Integer.parseInt(betField.getText()));
        playButton.setDisable(false);
        hitButton.setDisable(true);
        standButton.setDisable(true);
        updateBank();
        betField.setEditable(true);
    }

    public void gameWon(){
        winLoseLabel.setText("Won!");
        mainApp.getPlayer().setCoins(mainApp.getPlayer().getCoins() + Integer.parseInt(betField.getText()));
        playButton.setDisable(false);
        hitButton.setDisable(true);
        standButton.setDisable(true);
        updateBank();
        betField.setEditable(true);
    }

    public Label getOpponentCardValue() {
        return opponentCardValue;
    }

    public void calculateWinner() {
        if (Integer.parseInt(opponentCardValue.getText())<Integer.parseInt(playerCardValue.getText())||Integer.parseInt(opponentCardValue.getText())>21){
            gameWon();
        } else if (Integer.parseInt(opponentCardValue.getText())>Integer.parseInt(playerCardValue.getText())){
            gameLost();
        }else {
            winLoseLabel.setText("Draw!");
            playButton.setDisable(false);
            hitButton.setDisable(true);
            standButton.setDisable(true);
            updateBank();
            betField.setEditable(true);
        }
        backToGameSelection.setDisable(false);
    }
}