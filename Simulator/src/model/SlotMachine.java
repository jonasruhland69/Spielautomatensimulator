package model;

import javafx.scene.image.Image;

import java.io.File;
import java.net.MalformedURLException;
import java.util.Random;
import java.util.regex.Pattern;

public class SlotMachine  {
    private MainApp mainApp;
    private String url = System.getProperty("user.dir")+ File.separator+"src"+File.separator+"view"+File.separator+
            "Images" + File.separator;
    private Image[] slotPictures = {
            new Image(new File(url+ "$Slotmachine.png").toURI().toURL().toString()),
            new Image(new File(url + "7SlotMachine.png").toURI().toURL().toString()),
            new Image(new File(url + "StarSlotmachine.png").toURI().toURL().toString())
    };

    private int getRandomIntInRange(int min, int max){
        Random r = new Random();
        return r.nextInt((max - min) + 1) +min;
    }

    public void spin(){
        int bilanz=0;
        if (Pattern.matches("\\d+", mainApp.getSlotmashineController().getBet().getText()) && Integer.parseInt(mainApp.getSlotmashineController().getBet().getText())<=mainApp.getPlayer().getCoins()){
            mainApp.getSlotmashineController().getBet().setStyle("-fx-border-color: green");
            mainApp.getSlotmashineController().getSlot1().setImage(slotPictures[getRandomIntInRange(0,slotPictures.length-1)]);
            mainApp.getSlotmashineController().getSlot2().setImage(slotPictures[getRandomIntInRange(0,slotPictures.length-1)]);
            mainApp.getSlotmashineController().getSlot3().setImage(slotPictures[getRandomIntInRange(0,slotPictures.length-1)]);

            if (mainApp.getSlotmashineController().getSlot1().getImage().equals(mainApp.getSlotmashineController().getSlot2().getImage()) && mainApp.getSlotmashineController().getSlot3().getImage().equals(mainApp.getSlotmashineController().getSlot2().getImage())){
                if (mainApp.getSlotmashineController().getSlot1().getImage().equals(slotPictures[0])){
                    bilanz=((Integer.parseInt(mainApp.getSlotmashineController().getBet().getText())*5)-Integer.parseInt(mainApp.getSlotmashineController().getBet().getText()));
                }else if (mainApp.getSlotmashineController().getSlot1().getImage().equals(slotPictures[1])){
                    bilanz=((Integer.parseInt(mainApp.getSlotmashineController().getBet().getText())*7)-Integer.parseInt(mainApp.getSlotmashineController().getBet().getText()));
                }else if (mainApp.getSlotmashineController().getSlot1().getImage().equals(slotPictures[2])){
                    bilanz=((Integer.parseInt(mainApp.getSlotmashineController().getBet().getText())*3)-Integer.parseInt(mainApp.getSlotmashineController().getBet().getText()));
                }
            }else if (mainApp.getSlotmashineController().getSlot1().getImage().equals(slotPictures[2])&&mainApp.getSlotmashineController().getSlot2().getImage().equals(slotPictures[1])&&mainApp.getSlotmashineController().getSlot3().getImage().equals(slotPictures[0]))
                bilanz=((Integer.parseInt(mainApp.getSlotmashineController().getBet().getText())*10)-Integer.parseInt(mainApp.getSlotmashineController().getBet().getText()));
            else
                bilanz=(-(Integer.parseInt(mainApp.getSlotmashineController().getBet().getText())));
            mainApp.getPlayer().addCoins(bilanz);
            mainApp.getSlotmashineController().getLastGame().setText(String.valueOf(bilanz));
            if (bilanz>0){
                mainApp.getSlotmashineController().getWinLoseText().setText("WIN!");
            } else {
                mainApp.getSlotmashineController().getWinLoseText().setText("LOSE!");
            }
        }else
            mainApp.getSlotmashineController().getBet().setStyle("-fx-border-color: red");
        mainApp.getSlotmashineController().updateBank();
    }

    public SlotMachine(MainApp mainApp) throws MalformedURLException {
        this.mainApp = mainApp;
    }
}
