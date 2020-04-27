package model;

import controller.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;


public class MainApp extends Application {
        Stage stage = new Stage();
        Player player;
        MainMenuController mainMenuController = new MainMenuController();
        GameSelectionController gameSelectionController = new GameSelectionController();
        AchievementController achievementController= new AchievementController();
        SlotmachineController slotmashineController = new SlotmachineController();
        StartScreenController startScreenController = new StartScreenController();


        @Override
        public void start(Stage primaryStage) throws Exception{
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("../view/startScreen.fxml"));
                Parent root = loader.load();
                stage.setTitle("Spielautomatensimulator");
                stage.setScene(new Scene(root));
                startScreenController = loader.getController();
                startScreenController.setMainApp(this);
                stage.show();
            } catch (IOException e) {
                showLoadingError("Start");
            }

        }

        public static void main(String[] args) {
            launch(args);
        }

        public void loadGameSelection(){
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("../view/gameSelection.fxml"));
                Parent root = loader.load();
                stage.setTitle("Spielautomatensimulator (Game Selection)");
                stage.setScene(new Scene(root));
                gameSelectionController = loader.getController();
                gameSelectionController.setMainApp(this);
                gameSelectionController.setCoins(player.getCoins());
                stage.show();

            }catch (IOException e) {
                showLoadingError("Game Selection");
            }
        }

    public void loadAchievemensts(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../view/achievements.fxml"));
            Parent root = loader.load();
            stage.setTitle("Spielautomatensimulator (Achievements)");
            stage.setScene(new Scene(root));
            achievementController = loader.getController();
            achievementController.setMainApp(this);
            stage.show();

        }catch (IOException e) {
            showLoadingError("Achievements");
        }
    }
    public void loadSlotmaschine(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../view/slotmaschine.fxml"));
            Parent root = loader.load();
            stage.setTitle("Spielautomatensimulator (Slotmaschine)");
            stage.setScene(new Scene(root));
            slotmashineController = loader.getController();
            slotmashineController.setMainApp(this);
            stage.show();

        }catch (IOException e) {
            showLoadingError("Slot machine");
        }
    }

        public void loadMainMenu() {
            try {
            stage.sizeToScene();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../view/mainMenu.fxml"));
            Parent root = null;
            root = loader.load();
            stage.setTitle("Spielautomatensimulator");
            stage.setScene(new Scene(root));
            mainMenuController = loader.getController();
            mainMenuController.setMainApp(this);
            mainMenuController.setCoins(player.getCoins());
            stage.show();

            } catch (IOException e) {
                showLoadingError("Main Menu");
            }
        }

        public void showLoadingError(String view){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Loading Error");
            alert.setHeaderText("Error:");
            alert.setContentText("Could not load " + view +"!");
            alert.showAndWait();
        }

    public void loadGame() {
        try(ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream("abc")))){
            player = (Player) ois.readObject();
            loadMainMenu();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Player loadGameReturn() {
        try (ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream("abc")))) {
            return (Player) ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setPlayer(Player player){
            this.player= player;
    }

    public void setCoins(){
        mainMenuController.setCoins(player.getCoins());
    }

    public void save() {
            try (ObjectOutputStream dos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(player.getName())))){
                dos.writeObject(player);
                dos.flush();
        } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

    }
}