package controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import model.MainApp;

public class MainMenuController {
    private MainApp mainApp;

    @FXML
    private Label coinsLabel;

    @FXML
    private Button loadButton;

    @FXML
    private Button saveButton;

    @FXML
    private ImageView coinPicture;

    @FXML
    private Label spielautomatLabel;

    @FXML
    private Label achievementLabel;

    @FXML
    private ImageView raster1Image;

    @FXML
    private ImageView spielautomatImage;

    @FXML
    private Label beendenLabel;

    @FXML
    private ImageView raster2Image;

    @FXML
    private ImageView achievementImage;

    @FXML
    private ImageView beendenImage;

    @FXML
    private Label playerLabel;


    /**
     * Speichert das Spiel.
     *
     * @param event Klick auf Button.
     */
    @FXML
    void clickOnSaveButton(ActionEvent event) {
        mainApp.save();
    }

    /**
     * Schiesst das Spiel.
     *
     * @param event Klick auf Button.
     */
    @FXML
    void close(MouseEvent event) {
        mainApp.save();
        Platform.exit();
    }

    /**
     * Laedt Game Selection.
     *
     * @param event Klick auf Button.
     */
    @FXML
    void openGameSelection(MouseEvent event) {
        mainApp.loadGameSelection();
    }

    /**
     * Laedt Account View.
     *
     * @param event Klick auf Button.
     */
    @FXML
    void clickOnLoadButton(ActionEvent event) {
        mainApp.loadAccountView();
    }

    public void setPlayerLabel(String name) {
        playerLabel.setText(name);
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public void setCoins(int coins) {
        coinsLabel.setText(String.valueOf(coins));
    }

}
