package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import model.MainApp;

public class SlotmachineController {
    private MainApp mainApp;

    @FXML
    private AnchorPane anchor;

    @FXML
    private Label slotmachineLabel;

    @FXML
    private Label backToGameSelectionLabel;

    @FXML
    void backtoGameSelection(MouseEvent event) {
        mainApp.loadGameSelection();
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
}
