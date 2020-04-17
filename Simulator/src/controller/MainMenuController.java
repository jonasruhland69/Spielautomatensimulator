package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import model.MainApp;

public class MainMenuController {

    private MainApp mainApp;

    @FXML
    private ImageView coinPicture;

    @FXML
    private Button chooseGameButton;

    @FXML
    private Button achievementsButton;

    @FXML
    private Button endGameButton;

    @FXML
    private Label coinsLabel;

    @FXML
    private Button saveButton;

    @FXML
    void clickOnAchievementsButton(ActionEvent event) {
    }

    @FXML
    void clickOnChooseGame(ActionEvent event) {
    }

    @FXML
    void clickOnEndGameButton(ActionEvent event) {
    }

    @FXML
    void clickOnSaveButton(ActionEvent event) {
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
}
