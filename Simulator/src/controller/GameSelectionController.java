package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import model.MainApp;

import java.util.Optional;

public class GameSelectionController {
    private MainApp mainApp;

    @FXML
    private Label backToMainMenu;

    @FXML
    private Label slotmachineSelection;

    @FXML
    private Label rouletteSelection;

    @FXML
    private Label blackJackSelection;

    @FXML
    private Label coinsLabel;

    @FXML
    private ImageView coinPicture;

    @FXML
    private Label coinsLabelRoulett;

    @FXML
    private ImageView coinPicture1;

    @FXML
    private Label coinsLabelSlotMachine;

    @FXML
    private ImageView coinPicture11;

    @FXML
    void backClicked(MouseEvent event) {
        mainApp.loadMainMenu();
    }

    /**
     * Kauft wenn genug Coins im Besitz sind oder laedt Slot machine, je nach dem, ob es bereits in Besitz ist.
     *
     * @param event
     */
    @FXML
    void openSlotmachine(MouseEvent event) {
        //ueberprueft, ob Farbe des Buttons grau ist
        if (slotmachineSelection.getStyle().contains("-fx-background-color: grey;")) {
            //ueberprueft, ob sich Spieler die Kosten leisten kann
            if (mainApp.getPlayer().getCoins() > mainApp.getSLOTMACHINECOSTS()) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setHeaderText("Are you sure?");
                alert.setTitle("Confirmation");
                alert.setContentText("If you click on OK you are buying this game for " + mainApp.getSLOTMACHINECOSTS() +
                        " coins.");
                Optional<ButtonType> result = alert.showAndWait();
                //ueberprueft, ob Spieler beim Alert OK gedrueckt hat
                if (result.get() == ButtonType.OK) {
                    mainApp.getPlayer().setCoins(mainApp.getPlayer().getCoins() - mainApp.getSLOTMACHINECOSTS());
                    mainApp.getPlayer().getOwnedGames().put("Slotmachine", true);
                    updateOwnedGames();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Not enough Coins");
                alert.setTitle("Action Failed");
                alert.setContentText("You do not have enough Coins to buy this game!");
                alert.showAndWait();
            }
        } else
            mainApp.loadSlotmaschine();
    }

    /**
     * Laedt Black Jack.
     *
     * @param event
     */
    @FXML
    void openBlackJack(MouseEvent event) {
        mainApp.loadBlackJack();
    }

    /**
     * Kauft wenn genug Coins im Besitz sind oder laedt Roulette, je nach dem, ob es bereits in Besitz ist.
     *
     * @param event
     */
    @FXML
    void openRoulette(MouseEvent event) {
        //ueberprueft, ob Farbe des Buttons grau ist
        if (rouletteSelection.getStyle().contains("-fx-background-color: grey;")) {
            //ueberprueft, ob sich Spieler die Kosten leisten kann
            if (mainApp.getPlayer().getCoins() > mainApp.getROULETTECOSTS()) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setHeaderText("Are you sure?");
                alert.setTitle("Confirmation");
                alert.setContentText("If you click on OK you are buying this game for " + mainApp.getROULETTECOSTS() +
                        " coins.");
                Optional<ButtonType> result = alert.showAndWait();
                //ueberprueft, ob Spieler beim Alert OK gedrueckt hat
                if (result.get() == ButtonType.OK) {
                    mainApp.getPlayer().setCoins(mainApp.getPlayer().getCoins() - mainApp.getROULETTECOSTS());
                    mainApp.getPlayer().getOwnedGames().put("Roulette", true);
                    updateOwnedGames();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Not enough Coins");
                alert.setTitle("Action Failed");
                alert.setContentText("You do not have enough Coins to buy this game!");
                alert.showAndWait();
            }
        } else
            mainApp.loadRoulette();
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public void setCoins(int coins) {
        coinsLabel.setText(String.valueOf(coins));
    }

    /**
     * Stellt Spiel wenn gekauft ist, als spielbar dar.
     */
    public void updateOwnedGames() {
        //ueberprueft ob Slotmachine in owned Games von Player ist
        if (mainApp.getPlayer().getOwnedGames().get("Slotmachine")) {
            slotmachineSelection.setStyle("-fx-background-color: #ffcc33");
            coinsLabelSlotMachine.setVisible(false);
            coinPicture11.setVisible(false);
        }
        //ueberprueft ob Roulette in owned Games von Player ist
        if (mainApp.getPlayer().getOwnedGames().get("Roulette")) {
            rouletteSelection.setStyle("-fx-background-color: #ffcc33");
            coinsLabelRoulett.setVisible(false);
            coinPicture1.setVisible(false);
        }
    }
}