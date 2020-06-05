package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import model.BlackJack;
import model.MainApp;
import model.Roulette;

import java.io.IOException;

public class GameSelectionController {
    private MainApp mainApp;

    @FXML
    private Label backToMainMenu;

    @FXML
    private Label slotmachineSelection;

    @FXML
    private Label rouletteSelection;

    @FXML
    private Label blackJackSelection;

    @FXML
    private Label coinsLabel;

    @FXML
    private ImageView coinPicture;

    @FXML
    private Label coinsLabelBlackJack;

    @FXML
    private ImageView coinPicture1;

    @FXML
    private Label coinsLabelRoulette;

    @FXML
    private ImageView coinPicture11;

    @FXML
    void backClicked(MouseEvent event) {
            mainApp.loadMainMenu();
    }

    @FXML
    void openSlotmaschine(MouseEvent event) {
            mainApp.loadSlotmaschine();
    }

    @FXML
    void openBlackJack(MouseEvent event) {

    }

    @FXML
    void openRoulette(MouseEvent event) {
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public void setCoins(int coins){
        coinsLabel.setText(String.valueOf(coins));
    }
}