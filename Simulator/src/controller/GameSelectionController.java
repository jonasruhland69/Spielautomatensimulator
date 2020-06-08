package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import model.BlackJack;
import model.MainApp;
import model.Roulette;

import java.io.IOException;
import java.util.HashMap;
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
    private Label coinsLabelBlackJack;

    @FXML
    private ImageView coinPicture1;

    @FXML
    private Label coinsLabelRoulette;

    @FXML
    private ImageView coinPicture11;

    @FXML
    void backClicked(MouseEvent event) {
            mainApp.loadMainMenu();
    }

    @FXML
    void openSlotmaschine(MouseEvent event) {
            mainApp.loadSlotmaschine();
    }

    @FXML
    void openBlackJack(MouseEvent event) {
        if(blackJackSelection.getStyle().contains("-fx-background-color: grey;")){
            if (mainApp.getPlayer().getCoins()>mainApp.getBLACKJACKCOSTS()){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setHeaderText("Are you sure?");
                alert.setTitle("Confirmation");
                alert.setContentText("If you click on OK you are buying this game for " + mainApp.getBLACKJACKCOSTS()+
                " coins.");
                Optional<ButtonType> result= alert.showAndWait();
                if (result.get() == ButtonType.OK){
                    mainApp.getPlayer().setCoins(mainApp.getPlayer().getCoins()-mainApp.getBLACKJACKCOSTS());
                    mainApp.getPlayer().getOwnedGames().put("Black Jack", true);
                    updateOwnedGames();
                }
            }else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Not enough Coins");
                alert.setTitle("Action Failed");
                alert.setContentText("You do not have enough Coins to buy this game!");
                alert.showAndWait();
            }
        } else
            mainApp.loadBlackJack();
    }

    @FXML
    void openRoulette(MouseEvent event) {
        if(rouletteSelection.getStyle().contains("-fx-background-color: grey;")){
            if (mainApp.getPlayer().getCoins()>mainApp.getROULETTECOSTS()){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setHeaderText("Are you sure?");
                alert.setTitle("Confirmation");
                alert.setContentText("If you click on OK you are buying this game for " + mainApp.getROULETTECOSTS()+
                        " coins.");
                Optional<ButtonType> result= alert.showAndWait();
                if (result.get() == ButtonType.OK){
                    mainApp.getPlayer().setCoins(mainApp.getPlayer().getCoins()-mainApp.getROULETTECOSTS());
                    mainApp.getPlayer().getOwnedGames().put("Roulette", true);
                    updateOwnedGames();
                }
            }else {
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

    public void setCoins(int coins){
        coinsLabel.setText(String.valueOf(coins));
    }

    public void updateOwnedGames() {
        if (mainApp.getPlayer().getOwnedGames().get("Black Jack")){
            blackJackSelection.setStyle("-fx-background-color: #ffcc33");
            coinsLabelBlackJack.setVisible(false);
            coinPicture1.setVisible(false);
        }
        if (mainApp.getPlayer().getOwnedGames().get("Roulette")){
            rouletteSelection.setStyle("-fx-background-color: #ffcc33");
            coinsLabelRoulette.setVisible(false);
            coinPicture11.setVisible(false);
        }
    }
}