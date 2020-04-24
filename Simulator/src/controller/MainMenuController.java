package controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import model.MainApp;




import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class MainMenuController {
    private MainApp mainApp;

    @FXML
    private Label coinsLabel;

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
    void clickOnSaveButton(ActionEvent event) {

    }

    @FXML
    void close(MouseEvent event) {
        Platform.exit();
    }

    @FXML
    void openAchievements(MouseEvent event) {
            mainApp.loadAchievemensts();
    }

    @FXML
    void openGameSelection(MouseEvent event) {
        mainApp.loadGameSelection();

    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
    public void setCoins(int coins){
        coinsLabel.setText(String.valueOf(coins));
    }

}
