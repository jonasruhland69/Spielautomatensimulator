package model;

import controller.AchievementController;
import controller.GameSelectionController;
import controller.MainMenuController;
import controller.SlotmaschinController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;


public class MainApp extends Application {
        Stage stage = new Stage();
        Player player = new Player(100);
        MainMenuController mainMenuController = new MainMenuController();
        GameSelectionController gameSelectionController = new GameSelectionController();
        AchievementController achievementController= new AchievementController();
        SlotmaschinController slotmaschinController= new SlotmaschinController();

        @Override
        public void start(Stage primaryStage) throws Exception{
            loadMainMenu();
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
            slotmaschinController = loader.getController();
            slotmaschinController.setMainApp(this);
            stage.show();

        }catch (IOException e) {
            showLoadingError("Slotmaschine");
        }
    }

        public void loadMainMenu() {
            try {
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
    }