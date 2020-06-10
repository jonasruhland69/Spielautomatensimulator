package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import model.MainApp;

import javax.swing.text.html.ImageView;

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
    private TextField einsatzField;

    @FXML
    private Button playButton;

    @FXML
    private TextField bankField;

    @FXML
    private Label backToGameSelection;

    @FXML
    void backtoGameSelectionClicked(MouseEvent event) {

    }

    @FXML
    void hitButtonPressed(ActionEvent event) {

    }

    @FXML
    void playButtonPressed(ActionEvent event) {

    }

    @FXML
    void standButtonPressed(ActionEvent event) {
        mainApp.loadGameSelection();
    }

}