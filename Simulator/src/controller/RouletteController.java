package controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import model.MainApp;
import model.RouletteBet;

import java.util.ArrayList;
import java.util.Random;
import java.util.regex.Pattern;

public class RouletteController {
    private MainApp mainApp;
    private Label currentField;
    private final ArrayList<Label> fields = new ArrayList<>();

    @FXML
    private Button spinButton;

    @FXML
    private Label backToGameSelection;

    @FXML
    private Label field0;

    @FXML
    private Label field1;

    @FXML
    private Label field2;

    @FXML
    private Label field3;

    @FXML
    private Label field6;

    @FXML
    private Label field9;

    @FXML
    private Label field12;

    @FXML
    private Label field15;

    @FXML
    private Label field18;

    @FXML
    private Label field21;

    @FXML
    private Label field24;

    @FXML
    private Label field27;

    @FXML
    private Label field30;

    @FXML
    private Label field33;

    @FXML
    private Label field36;

    @FXML
    private Label field4;

    @FXML
    private Label field5;

    @FXML
    private Label field7;

    @FXML
    private Label field8;

    @FXML
    private Label field10;

    @FXML
    private Label field11;

    @FXML
    private Label field13;

    @FXML
    private Label field14;

    @FXML
    private Label field16;

    @FXML
    private Label field17;

    @FXML
    private Label field19;

    @FXML
    private Label field20;

    @FXML
    private Label field22;

    @FXML
    private Label field23;

    @FXML
    private Label field25;

    @FXML
    private Label field26;

    @FXML
    private Label field28;

    @FXML
    private Label field29;

    @FXML
    private Label field31;

    @FXML
    private Label field32;

    @FXML
    private Label field34;

    @FXML
    private Label field35;

    @FXML
    private Label field1to12;

    @FXML
    private Label field13to24;

    @FXML
    private Label field25to36;

    @FXML
    private Label field1to18;

    @FXML
    private Label fieldEven;

    @FXML
    private Label fieldRed;

    @FXML
    private Label fieldBlack;

    @FXML
    private Label fieldOdd;

    @FXML
    private Label field19to36;

    @FXML
    private TextField betField;

    @FXML
    private Button setBetButton;

    @FXML
    private TextField bankTextField;

    @FXML
    private Label winLoseLabel;

    @FXML
    private TextField lastGameTextField;

    @FXML
    void backtoGameSelectionClicked(MouseEvent event) {
        mainApp.loadGameSelection();
    }

    /**
     * Setzt bei richtiger Eingabe Bet Field auf gruen und legt falls noch nicht vorhanden eine Wette auf dieses Feld,
     * wenn noch nicht gemacht.
     *
     * @param event
     */
    @FXML
    void clickOnSetBetButton(ActionEvent event) {
        //ueberprueft auf richtige Eingabe
        if (Pattern.matches("\\d+", betField.getText()) && Integer.parseInt(betField.getText()) <= mainApp.getPlayer().getCoins() && Integer.parseInt(betField.getText()) >= 0) {
            betField.setStyle("-fx-border-color: green");
            int lastBet = 0;
            //Wenn schon eine Wette auf diesem Feld ist, dann setze last Bet auf dieses Feld
            if (mainApp.getRoulette().getRouletteBets().containsKey(currentField.getText()))
                lastBet = mainApp.getRoulette().getRouletteBets().get(currentField.getText()).getBet();
            //Wenn Wette groesser als 0, dann wird eine Wette auf dieses Feld hinzugefuegt
            if (Integer.parseInt(betField.getText()) > 0)
                mainApp.getRoulette().getRouletteBets().put(currentField.getText(), new RouletteBet(Integer.parseInt(betField.getText())));
                //Wenn Wette gleich 0, dann wird Wette auf diesem Feld entfernt
            else if (Integer.parseInt(betField.getText()) == 0) {
                mainApp.getRoulette().getRouletteBets().remove(currentField.getText());
                currentField.setTextFill(Color.web("white"));
            }
            //Wenn auf gewaehltem Feld eine Wette ist, wird Zahl gefaerbt
            if (mainApp.getRoulette().getRouletteBets().containsKey(currentField.getText())) {
                currentField.setTextFill(Color.web("#e3a909"));
            }
            mainApp.getPlayer().addCoins(lastBet - Integer.parseInt(betField.getText()));
            updateBank();
            //Wenn es keine Wetten gibt wird spinButton deaktiviert
                spinButton.setDisable(mainApp.getRoulette().getRouletteBets().isEmpty());
        } else
            betField.setStyle("-fx-border-color: red");
    }

    /**
     * Spin Methode in Roulette wird ausgefuehrt, Coins werden geladen, Bet Field und Spin Button werden deaktiviert.
     *
     * @throws InterruptedException
     */
    @FXML
    void spinButtonClicked() throws InterruptedException {
        mainApp.getRoulette().startGame();
        updateBank();
        betField.setEditable(false);
        spinButton.setDisable(true);
    }

    /**
     * Wenn Feld geklickt, wird Fokus auf betField gesetzt, Einsatz vom Feld wird geladen und Feld wird markiert.
     *
     * @param event Klick auf Feld.
     */
    @FXML
    void fieldClicked(MouseEvent event) {
        betField.requestFocus();
        betField.setEditable(true);
        //Wenn vorher ein Rahmen gefaerbt war, wird dieses weiss gefaerbt
        if (currentField != null) {
            String style = currentField.getStyle();
            style = style.replace("-fx-border-color: #e3a909", "-fx-border-color: white");
            currentField.setStyle(style);
        }
        Label bet = ((Label) event.getSource());
        circleField(bet);
        currentField = bet;
        //Wette des Feldes wird geladen
        if (mainApp.getRoulette().getRouletteBets().containsKey(bet.getText())) {
            betField.setText(String.valueOf(mainApp.getRoulette().getRouletteBets().get(bet.getText()).getBet()));
        } else
            betField.setText("0");
        betField.selectAll();
    }

    /**
     * Feld Rahmen wird gefaerbt.
     *
     * @param field Label des zu faerbenden Feldes.
     */
    public void circleField(Label field) {
        String style = field.getStyle();
        style = style.replace("white;", "#e3a909;");
        field.setStyle(style);
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public TextField getBankTextField() {
        return bankTextField;
    }

    public void updateBank() {
        bankTextField.setText(String.valueOf(mainApp.getPlayer().getCoins()));
    }

    /**
     * Felder werden in fields hinzugefuegt.
     */
    public void initializeFields() {
        fields.add(field1);
        fields.add(field2);
        fields.add(field3);
        fields.add(field6);
        fields.add(field9);
        fields.add(field12);
        fields.add(field15);
        fields.add(field18);
        fields.add(field21);
        fields.add(field24);
        fields.add(field27);
        fields.add(field30);
        fields.add(field33);
        fields.add(field36);
        fields.add(field4);
        fields.add(field5);
        fields.add(field7);
        fields.add(field8);
        fields.add(field10);
        fields.add(field11);
        fields.add(field13);
        fields.add(field14);
        fields.add(field16);
        fields.add(field17);
        fields.add(field19);
        fields.add(field20);
        fields.add(field22);
        fields.add(field23);
        fields.add(field25);
        fields.add(field26);
        fields.add(field28);
        fields.add(field29);
        fields.add(field31);
        fields.add(field32);
        fields.add(field34);
        fields.add(field35);
    }

    /**
     * Farben aller Felderrahmen werden auf weiss gestellt und betField wird zurueckgesetzt
     */
    public void clearFields() {
        mainApp.getRoulette().getRouletteBets().clear();
        for (Label field : fields) {
            field.setTextFill(Color.web("white"));
        }
        field1to12.setTextFill(Color.web("white"));
        field1to18.setTextFill(Color.web("white"));
        fieldOdd.setTextFill(Color.web("white"));
        fieldEven.setTextFill(Color.web("white"));
        field13to24.setTextFill(Color.web("white"));
        field19to36.setTextFill(Color.web("white"));
        field25to36.setTextFill(Color.web("white"));
        fieldRed.setTextFill(Color.web("white"));
        fieldBlack.setTextFill(Color.web("white"));
        betField.setText("");
        betField.deselect();
    }

    public ArrayList<Label> getFields() {
        return fields;
    }


    /**
     * Animation zur Feldauswahl wird ausgefuehrt.
     *
     * @throws InterruptedException
     */
    public void selectionAnimation() throws InterruptedException {
        Timeline timeline = new Timeline(new KeyFrame(
                //Alle 0.5 Sekunden wird zufaellig ein Zahlenfeld ausgewaehlt.
                Duration.millis(500),
                ae -> {
                    String style = currentField.getStyle();
                    style = style.replace("-fx-border-color: #e3a909", "-fx-border-color: white");
                    currentField.setStyle(style);
                    currentField = getRandomField(fields.size() - 1, 0);
                    circleField(currentField);
                }));
        timeline.setCycleCount(10);
        timeline.play();
        timeline.setOnFinished(event -> {
            mainApp.getRoulette().calcWinning();
            clearFields();
        });
    }


    /**
     * Es wird ein random Feld gesucht.
     *
     * @param max Maximale Anzahl von der Random Zahl.
     * @param min Minimale Anzahl von der Random Zahl.
     * @return random Label.
     */
    public Label getRandomField(int max, int min) {
        ArrayList<Label> fields = mainApp.getRouletteController().getFields();
        Random r = new Random();
        return fields.get(r.nextInt((max - min) + 1) + min);
    }

    public Label getCurrentField() {
        return currentField;
    }

    public void setWinLoseLabel(String winLoseLabel) {
        this.winLoseLabel.setText(winLoseLabel);
    }

    public TextField getBetField() {
        return betField;
    }

    public TextField getLastGame() {
        return lastGameTextField;
    }
}
