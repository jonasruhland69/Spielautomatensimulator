package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import model.MainApp;

import java.util.ArrayList;

public class AccountViewController {
    private MainApp mainApp;
    private ArrayList<TreeItem<String>> accounts = new ArrayList<>();

    @FXML
    private TreeView<String> accountsTreeView = new TreeView<>();

    @FXML
    private Label backToMainMenu;

    @FXML
    void backToMainMenu(MouseEvent event) {
        this.mainApp.loadStartScreen();
    }

    public void addAccount(TreeItem<String> accountName){
        accounts.add(accountName);
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public void initializeTreeView() {
        TreeItem<String> root = new TreeItem<>("Accounts:");
        root.setExpanded(true);
        accountsTreeView.setRoot(root);
        for (TreeItem account : accounts) {
            accountsTreeView.getRoot().getChildren().add(account);
        }
    }

    public void treeItemClicked(MouseEvent mouseEvent) {

        mainApp.loadGame(accountsTreeView.getSelectionModel().getSelectedItem().getValue());
    }
}
