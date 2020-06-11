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
    private TextField betField;

    @FXML
    private Button playButton;

    @FXML
    private TextField bankField;

    @FXML
    private Label backToGameSelection;

    public void updateBank(){
        bankField.setText(String.valueOf(mainApp.getPlayer().getCoins()));
    }

    @FXML
    void backtoGameSelectionClicked(MouseEvent event) {
        mainApp.loadGameSelection();
    }

    @FXML
    void hitButtonPressed(ActionEvent event) {
        mainApp.getBlackJack().pullCard();
    }

    @FXML
    void playButtonPressed(ActionEvent event) throws MalformedURLException {
        mainApp.getBlackJack().play();
        hitButton.setDisable(false);
        standButton.setDisable(false);
    }

    @FXML
    void standButtonPressed(ActionEvent event) {
    }

    public TextField getBet() {
        return betField;
    }

    public void updateCards() throws MalformedURLException {
        char[] playerCards = mainApp.getBlackJack().getPlayerCards();
        char[] opponentCards = mainApp.getBlackJack().getOpponentCards();
        ImageView[] playerCardImages = {card1,card2,card3,card4,card5};
        ImageView[] opponentCardImages = {opponentCard1,opponentCard2,opponentCard3,opponentCard4,opponentCard5};

        for (int i = 0; i < playerCardImages.length; i++) {
            if (playerCards[i]!='\u0000'){
                playerCardImages[i].setImage(new Image(new File(mainApp.getBlackJack().getUrl()).toURI().toURL().toString()+playerCards[i]+".png"));
            }
        }
    }
}