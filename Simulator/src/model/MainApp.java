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

public class MainApp extends Application {
    final int SLOTMACHINECOSTS = 500;
    final int ROULETTECOSTS = 1000;
    Stage stage = new Stage();
    Player player;
    MainMenuController mainMenuController = new MainMenuController();
    GameSelectionController gameSelectionController = new GameSelectionController();
    SlotmachineController slotmashineController = new SlotmachineController();
    StartScreenController startScreenController = new StartScreenController();
    AccountViewController accountViewController = new AccountViewController();
    BlackJackController blackJackController = new BlackJackController();
    RouletteController rouletteController = new RouletteController();
    SlotMachine slotMachine = new SlotMachine(this);
    Roulette roulette = new Roulette(this);
    BlackJack blackJack = new BlackJack(this);

    public MainApp() throws MalformedURLException {
    }

    /**
     * Launcht das Programm.
     *
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Ruft loadStartScreen() auf.
     *
     * @param primaryStage Stage des gesamten Programms
     */
    @Override
    public void start(Stage primaryStage) {
        loadStartScreen();
    }

    /**
     * Laedt den Start Screen und konfiguriert Controller.
     */
    public void loadStartScreen() {
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
            showLoadingError("startScreen.fxml");
        }
    }

    /**
     * Laedt die Game Selection, konfiguriert Controller, laedt die Coins des Spielers und laedt die gekauften Spiele.
     */
    public void loadGameSelection() {
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

        } catch (IOException e) {
            showLoadingError("Game Selection");
        }
    }

    /**
     * Laedt die Slot Machine, konfiguriert Controller und laedt die Coins des Spielers.
     */
    public void loadSlotmaschine() {
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
            stage.setMaximized(true);
            stage.setFullScreen(true);

        } catch (IOException e) {
            showLoadingError("Slot machine");
        }
    }

    /**
     * Laedt Roulette, konfiguriert Controller, laedt die Coins des Spielers und initialisiert die Felder.
     */

    public void loadRoulette() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../view/roulette.fxml"));
            Parent root = loader.load();
            stage.setTitle("Spielautomatensimulator (Roulette)");
            stage.setScene(new Scene(root));
            rouletteController = loader.getController();
            rouletteController.setMainApp(this);
            rouletteController.getBankTextField().setEditable(false);
            rouletteController.updateBank();
            rouletteController.initializeFields();
            stage.show();
            stage.setMaximized(true);
            rouletteController.getBetField().setEditable(false);

        } catch (IOException e) {
            showLoadingError("Roulette");
        }
    }

    /**
     * Laedt den Black Jack, konfiguriert Controller und laedt die Coins des Spielers.
     */
    public void loadBlackJack() {
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
            stage.setMaximized(true);

        } catch (IOException e) {
            showLoadingError("Black Jack");
        }
    }

    /**
     * Laedt das Main Menue, konfiguriert Controller und laedt die Coins des Spielers und den Spielernamen.
     */

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

    /**
     * Laedt die Account View, konfiguriert den Controller und laedt alle Save Dateien.
     */

    public void loadAccountView() {
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
                if (files[i].getName().endsWith(".bin")) {
                    accountViewController.addAccount(new TreeItem(files[i].getName()));
                }
            }
            accountViewController.initializeTreeView();
            stage.show();

        } catch (IOException e) {
            showLoadingError("Account View");
        }
    }

    /**
     * Zur Ausgabe von Error Alert Boxes.
     *
     * @param view Der Name der View.
     */

    public void showLoadingError(String view) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Loading Error");
        alert.setHeaderText("Error:");
        alert.setContentText("Could not load " + view + "!");
        alert.showAndWait();
    }

    /**
     * Laedt eine Save Datei.
     *
     * @param path Pfad der Save Datei.
     */

    public void loadGame(String path) {
        try (ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(path)))) {
            player = (Player) ois.readObject();
            loadMainMenu();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Speichert das Spiel mit dem Namen des Spielers.bin
     */

    public void save() {
        try (ObjectOutputStream dos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(player.getName() + ".bin")))) {
            dos.writeObject(player);
            dos.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getSLOTMACHINECOSTS() {
        return SLOTMACHINECOSTS;
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

    public AccountViewController getAccountViewController() {
        return accountViewController;
    }
}