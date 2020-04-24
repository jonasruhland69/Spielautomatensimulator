package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import model.MainApp;

public class AchievementController {
    private MainApp mainApp;

    @FXML
    private AnchorPane achievementanchor;

    @FXML
    private Label achieventLabel;
    @FXML
    private Label backLabel;

    @FXML
    void backToMainMenu(MouseEvent event) {
        mainApp.loadMainMenu();

    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp=mainApp;
    }
}
