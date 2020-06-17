package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.MainApp;
import model.Player;

public class StartScreenController {
    private MainApp mainApp;

    @FXML
    private Button loadButton;

    @FXML
    private TextField nameTextField;

    @FXML
    private Button createPlayer;

    /**
     * Account View wird geladen.
     *
     * @param event Klick auf Button.
     */
    @FXML
    void clickOnLoadButton(ActionEvent event) {
        mainApp.loadAccountView();
    }

    /**
     * Spieler wird erstellt und Main Menue geladen.
     *
     * @param event
     */
    @FXML
    void clickOnCreateButton(ActionEvent event) {
        if (!nameTextField.getText().isEmpty()) {
            mainApp.setPlayer(new Player(50, nameTextField.getText()));
            mainApp.loadMainMenu();
        }
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

}
