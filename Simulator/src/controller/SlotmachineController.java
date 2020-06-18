package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import model.MainApp;

public class SlotmachineController {
    private MainApp mainApp;

    @FXML
    private TextField lastGame;

    @FXML
    private TextField bank;

    @FXML
    private TextField bet;

    @FXML
    private Button spinButton;

    @FXML
    private ImageView slot1;

    @FXML
    private ImageView slot2;

    @FXML
    private ImageView slot3;

    @FXML
    private Label winLoseText;

    @FXML
    private Label backToMainMenu;

    @FXML
    void backtoGameSelection(MouseEvent event) {
        mainApp.loadGameSelection();
    }

    /**
     * Spin Methode in SlotMachine wird ausgefuehrt.
     *
     * @param event Klick auf Button.
     */
    @FXML
    void spin(ActionEvent event) {
        mainApp.getSlotMachine().startGame();
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public void updateBank() {
        bank.setText(String.valueOf(mainApp.getPlayer().getCoins()));
    }

    public TextField getBank() {
        return bank;
    }

    public TextField getBet() {
        return bet;
    }

    public ImageView getSlot1() {
        return slot1;
    }

    public ImageView getSlot2() {
        return slot2;
    }

    public ImageView getSlot3() {
        return slot3;
    }

    public Label getWinLoseText() {
        return winLoseText;
    }

    public TextField getLastGame() {
        return lastGame;
    }
}
