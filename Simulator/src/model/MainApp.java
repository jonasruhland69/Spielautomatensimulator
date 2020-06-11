package model;

import controller.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TreeItem;
import javafx.stage.Stage;

import java.io.*;
import java.net.MalformedURLException;
import java.util.ArrayList;


public class MainApp extends Application {
        final int BLACKJACKCOSTS = 1000;
        final int ROULETTECOSTS = 1000;
        Stage stage = new Stage();
        Player player;
        MainMenuController mainMenuController = new MainMenuController();
        GameSelectionController gameSelectionController = new GameSelectionController();
        AchievementController achievementController= new AchievementController();
        SlotmachineController slotmashineController = new SlotmachineController();
        StartScreenController startScreenController = new StartScreenController();
        AccountViewController accountViewController = new AccountViewController();
        BlackJackController blackJackController = new BlackJackController();
        RouletteController rouletteController = new RouletteController();
        SlotMachine slotMachine = new SlotMachine(this);
        Roulette roulette = new Roulette();
        BlackJack blackJack = new BlackJack(this);

    public MainApp() throws MalformedURLException {
    }


    @Override
        public void start(Stage primaryStage) throws Exception{
            loadStartScreen();
        }

        public static void main(String[] args) {
            launch(args);
        }

        public void loadStartScreen(){
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("../view/startScreen.fxml"));
                Parent root = loader.load();
                stage.setTitle("Spielautomatensimulator");
                stage.setScene(new Scene(root));
                startScreenController = loader.getController();
                startScreenController.setMainApp(this);
                stage.show();
            }catch (IOException e){
                showLoadingError("startScreen.fxml");
            }
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
                gameSelectionController.updateOwnedGames();
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
            slotmashineController.updateBank();
            stage.show();

        }catch (IOException e) {
            showLoadingError("Slot machine");
        }
    }

    public void loadRoulette() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../view/roulette.fxml"));
            Parent root = loader.load();
            stage.setTitle("Spielautomatensimulator (Roulette)");
            stage.setScene(new Scene(root));
            rouletteController = loader.getController();
            rouletteController.setMainApp(this);
            stage.show();

        } catch (IOException e) {
            showLoadingError("Roulette");
        }
    }

        public void loadBlackJack(){
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("../view/blackJack.fxml"));
                Parent root = loader.load();
                stage.setTitle("Spielautomatensimulator (Black Jack)");
                stage.setScene(new Scene(root));
                blackJackController = loader.getController();
                blackJackController.setMainApp(this);
                blackJackController.updateBank();
                stage.show();

            }catch (IOException e) {
                showLoadingError("Black Jack");
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
            mainMenuController.setPlayerLabel(player.getName());
            stage.show();

            } catch (IOException e) {
                showLoadingError("Main Menu");
            }
        }

    public void loadAccountView(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../view/accountView.fxml"));
            Parent root = loader.load();
            stage.setTitle("Spielautomatensimulator (Accounts)");
            stage.setScene(new Scene(root));
            accountViewController = loader.getController();
            accountViewController.setMainApp(this);
            File file = new File("./");
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                if (files[i].getName().endsWith(".bin")){
                    TreeItem treeItem = new TreeItem(files[i].getName());
                    accountViewController.addAccount(treeItem);
                }
            }
            accountViewController.initializeTreeView();
            stage.show();

        }catch (IOException e) {
            showLoadingError("Account View");
        }
    }

        public void showLoadingError(String view){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Loading Error");
            alert.setHeaderText("Error:");
            alert.setContentText("Could not load " + view +"!");
            alert.showAndWait();
        }

    public void loadGame(String path) {
        try(ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(path)))){
            player = (Player) ois.readObject();
            loadMainMenu();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void save() {
        try (ObjectOutputStream dos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(player.getName() + ".bin")))){
            dos.writeObject(player);
            dos.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void setPlayer(Player player){
            this.player= player;
    }

    public Player getPlayer() {
        return player;
    }

    public int getBLACKJACKCOSTS() {
        return BLACKJACKCOSTS;
    }

    public int getROULETTECOSTS() {
        return ROULETTECOSTS;
    }

    public SlotMachine getSlotMachine() {
        return slotMachine;
    }

    public Roulette getRoulette() {
        return roulette;
    }

    public BlackJack getBlackJack() {
        return blackJack;
    }

    public SlotmachineController getSlotmashineController() {
        return slotmashineController;
    }

    public BlackJackController getBlackJackController() {
        return blackJackController;
    }

    public RouletteController getRouletteController() {
        return rouletteController;
    }
}