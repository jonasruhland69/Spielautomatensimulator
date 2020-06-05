package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import model.MainApp;

import java.io.File;
import java.net.MalformedURLException;
import java.util.Random;
import java.util.regex.Pattern;

public class SlotmachineController {
    private MainApp mainApp;
    private String url = System.getProperty("user.dir")+File.separator+"src"+File.separator+"view"+File.separator+
        "Images" + File.separator;
    private Image[] slotPictures = {
            new Image(new File(url+ "$Slotmachine.png").toURI().toURL().toString()),
            new Image(new File(url + "7SlotMachine.png").toURI().toURL().toString()),
            new Image(new File(url + "StarSlotmachine.png").toURI().toURL().toString())
};
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

    public SlotmachineController() throws MalformedURLException {
    }

    private int getRandomIntInRange(int min, int max){
        Random r = new Random();
        return r.nextInt((max - min) + 1) +min;
    }

    @FXML
    void backtoGameSelection(MouseEvent event) {
        mainApp.loadGameSelection();
    }

    @FXML
    void spin(ActionEvent event) {
        int bilanz=0;
        if (Pattern.matches("\\d+", bet.getText()) && Integer.parseInt(bet.getText())<=mainApp.getPlayer().getCoins()){
            bet.setStyle("-fx-border-color: green");
            slot1.setImage(slotPictures[getRandomIntInRange(0,slotPictures.length-1)]);
            slot2.setImage(slotPictures[getRandomIntInRange(0,slotPictures.length-1)]);
            slot3.setImage(slotPictures[getRandomIntInRange(0,slotPictures.length-1)]);
            if (slot1.getImage().equals(slot2.getImage()) && slot3.getImage().equals(slot2.getImage())){
                if (slot1.getImage().equals(slotPictures[0])){
                    bilanz=((Integer.parseInt(bet.getText())*5)-Integer.parseInt(bet.getText()));
                }else if (slot1.getImage().equals(slotPictures[1])){
                    bilanz=((Integer.parseInt(bet.getText())*7)-Integer.parseInt(bet.getText()));
                }else if (slot1.getImage().equals(slotPictures[2])){
                    bilanz=((Integer.parseInt(bet.getText())*3)-Integer.parseInt(bet.getText()));
                }
            }else if (slot1.getImage().equals(slotPictures[2])&&slot2.getImage().equals(slotPictures[1])&&slot3.getImage().equals(slotPictures[0]))
                bilanz=((Integer.parseInt(bet.getText())*10)-Integer.parseInt(bet.getText()));
            else
                bilanz=(-(Integer.parseInt(bet.getText())));
            mainApp.getPlayer().addCoins(bilanz);
            lastGame.setText(String.valueOf(bilanz));
            if (bilanz>0){
                winLoseText.setText("WIN!");
            } else {
                winLoseText.setText("LOSE!");
            }
        }else
            bet.setStyle("-fx-border-color: red");
        updateBank();
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public void updateBank(){
        bank.setText(String.valueOf(mainApp.getPlayer().getCoins()));
    }
}
