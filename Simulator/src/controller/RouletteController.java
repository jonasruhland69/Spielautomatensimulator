package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import model.MainApp;

public class RouletteController {
    MainApp mainApp;

    @FXML
    private Label backToMainMenu;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    void backtoGameSelection(MouseEvent event) {
        mainApp.loadGameSelection();
    }
}
