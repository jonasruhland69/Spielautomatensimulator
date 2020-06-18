package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import model.MainApp;

import java.util.ArrayList;

public class AccountViewController {
    private ArrayList<TreeItem<String>> accounts = new ArrayList<>();
    private MainApp mainApp;

    @FXML
    private TreeView<String> accountsTreeView = new TreeView<>();

    @FXML
    private Label backToMainMenu;

    @FXML
    void backToMainMenu(MouseEvent event) {
        this.mainApp.loadStartScreen();
    }

    /**
     * Fuegt einen Account zur ArrayList accounts hinzu.
     *
     * @param accountName Name vom Account.
     */
    public void addAccount(TreeItem<String> accountName) {
        accounts.add(accountName);
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    /**
     * Fuegt Tree Items zu Tree View hinzu.
     */
    public void initializeTreeView() {
        TreeItem<String> root = new TreeItem<>("Accounts:");
        root.getChildren().addAll(accounts);
        root.setExpanded(true);
        accountsTreeView.setRoot(root);
    }

    /**
     * Laedt bei Klick auf ein Tree Item den Account.
     *
     * @param mouseEvent Klick auf Button.
     */
    public void treeItemClicked(MouseEvent mouseEvent) {
        if (accountsTreeView.getSelectionModel().getSelectedItem() != null)
            mainApp.loadGame(accountsTreeView.getSelectionModel().getSelectedItem().getValue()+".bin");
    }

    public ArrayList<TreeItem<String>> getAccounts() {
        return accounts;
    }
}
