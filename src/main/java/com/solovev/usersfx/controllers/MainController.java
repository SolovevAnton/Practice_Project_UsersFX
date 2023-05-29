package com.solovev.usersfx.controllers;

import com.solovev.usersfx.App;
import com.solovev.usersfx.model.User;
import com.solovev.usersfx.repository.UserRepository;
import com.solovev.usersfx.util.UserDecoratorClass;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Controller of the main scene
 */

public class MainController {
    @FXML
    public ComboBox<User> comboBoxUsers = new ComboBox<>();
    @FXML
    public TextArea textAreaSelectedUserInfo;
    @FXML
    public ListView<User> addedUsersListView = new ListView<>();
    public TextArea textAreaLogs;
    private final FileChooser mainChooser = new FileChooser();
    private File chosenSaveFile;

    private final Callback<ListView<User>, ListCell<User>> userToOnlyNamesAppearance = lv -> new ListCell<>() {
        @Override
        protected void updateItem(User user, boolean empty) {
            super.updateItem(user, empty);
            setText(empty ? "" : UserDecoratorClass.shortUserInfo(user));
        }
    };

    @FXML
    public void initialize() throws IOException {
        URL urlToGetUsers = new URL("https://jsonplaceholder.typicode.com/users");
        UserRepository userRep = new UserRepository(urlToGetUsers);
        //combobox initialization
        comboboxInitialization(userRep);

        //fileChooser initialization
        fileChooserInitialization();

        //text fields initialization
        addedUsersListView.setCellFactory(userToOnlyNamesAppearance);
        textAreaLogs.setText(comboBoxUsers.getItems().size() + " Users loaded from: " + urlToGetUsers + "\n");
    }

    /**
     * method to initialize combobox
     *
     * @param repo repository to get users from
     */
    private void comboboxInitialization(UserRepository repo) {
        comboBoxUsers.getItems().setAll(repo.getUsers());
        comboBoxUsers.setCellFactory(userToOnlyNamesAppearance);
        comboBoxUsers.setButtonCell(userToOnlyNamesAppearance.call(null));
        ChangeListener<User> userChangeListener = (list, oldValue, newValue) -> textAreaSelectedUserInfo.setText(newValue != null ? UserDecoratorClass.longUserInfo(newValue) : "");
        comboBoxUsers.valueProperty().addListener(userChangeListener);
    }

    /**
     * Method to initialize fileChooser
     */
    private void fileChooserInitialization() {
        mainChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JSON files", "*.json"),
                new FileChooser.ExtensionFilter("text files", "*.txt"),
                new FileChooser.ExtensionFilter("all files", "*.*")
        );
    }


    /**
     * Adds selected user to the List
     *
     * @param actionEvent buttonSave gets clicked
     */
    @FXML
    public void buttonSave(ActionEvent actionEvent) {
        User selectedUser = comboBoxUsers.getValue();
        if (selectedUser != null) {
            comboBoxUsers.getItems().remove(selectedUser);
            addedUsersListView.getItems().add(selectedUser);
            textAreaLogs.appendText("User " + UserDecoratorClass.shortUserInfo(selectedUser) + " saved locally\n");
        } else {
            textAreaLogs.appendText("Select user to save\n");
        }

    }

    /**
     * Deletes selected user from the saved list
     *
     * @param actionEvent button delete gets clicked
     */
    @FXML
    public void buttonDelete(ActionEvent actionEvent) {
        User selectedUser = addedUsersListView.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            addedUsersListView.getItems().remove(selectedUser);
            textAreaLogs.appendText("User " + UserDecoratorClass.shortUserInfo(selectedUser) + " deleted\n");
        } else {
            textAreaLogs.appendText("Select user to delete\n");
        }

    }

    /**
     * Shows info about selected user
     *
     * @param actionEvent button info gets clicked
     */
    @FXML
    public void buttonShowInfo(ActionEvent actionEvent) throws IOException {
        User selectedUser = addedUsersListView.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            User updatedUser = App.openWindowAndWait("userInfo.fxml","User Info",selectedUser).getUserData();

            if(Up)
        }
    }

    /**
     * menu button to open and read users form JSON files
     *
     * @param actionEvent
     */
    public void menuButtonOpen(ActionEvent actionEvent) throws IOException {
        mainChooser.setTitle("Opening file");
        File fileToOpen = mainChooser.showOpenDialog(null);
        if (fileToOpen != null) {
            mainChooser.setInitialDirectory(fileToOpen.getParentFile());

            UserRepository repo = new UserRepository(fileToOpen);
            comboBoxUsers.getItems().setAll(repo.getUsers());
            textAreaLogs.appendText(comboBoxUsers.getItems().size() + " Users loaded from: " + fileToOpen + "\n");
        }
    }

    /**
     * Adds selected users to local file
     * @param actionEvent
     * @throws IOException
     */

    public void menuButtonSave(ActionEvent actionEvent) throws IOException {
        if (chosenSaveFile != null) {
            UserRepository savingRepo = new UserRepository();
            addedUsersListView.getItems().forEach(savingRepo::addUser);
            savingRepo.save(chosenSaveFile);
            textAreaLogs.appendText(addedUsersListView.getItems().size() + " Users saved to: " + chosenSaveFile + "\n");
        } else {
            menuButtonSaveAs(null);
        }
    }

    /**
     * Menu button to save as
     * @param actionEvent
     * @throws IOException
     */

    public void menuButtonSaveAs(ActionEvent actionEvent) throws IOException {
        File newFileToSave = mainChooser.showSaveDialog(null);
        if (newFileToSave != null) {
            chosenSaveFile = newFileToSave;
            mainChooser.setInitialDirectory(chosenSaveFile.getParentFile());
            menuButtonSave(null);
        }
    }
}
