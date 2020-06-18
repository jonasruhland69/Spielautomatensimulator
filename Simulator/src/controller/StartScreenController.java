package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import model.MainApp;
import model.Player;

import java.util.ArrayList;

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
        mainApp.readSaveFiles();
        if (!nameTextField.getText().isEmpty() && !checkIfAccountExists()) {
            mainApp.setPlayer(new Player(50, nameTextField.getText()));
            mainApp.loadMainMenu();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning!");
            alert.setHeaderText("Could not create Account.");
            alert.setContentText("Your input is empty or this Account already exists.");
            alert.showAndWait();
        }
    }

    /**
     * Checks if Account exists
     * @return
     */
    private boolean checkIfAccountExists() {
        boolean exists = false;
        String name = nameTextField.getText();
        ArrayList<TreeItem<String>> accounts = mainApp.getAccountViewController().getAccounts();
        for (TreeItem<String> account : accounts) {
            if (name.equals(account.getValue())){
                exists=true;
            }
        }

        return exists;
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

}
