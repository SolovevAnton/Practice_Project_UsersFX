package com.solovev.usersfx.controllers;

import com.solovev.usersfx.model.User;
import com.solovev.usersfx.repository.UserRepository;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.util.Callback;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class MainController {
    @FXML
    public ComboBox<User> comboBoxUsers = new ComboBox<>();
    @FXML
    public TextArea textAreaSelectedUserInfo;
    @FXML
    public ListView<User> addedUsersListView = new ListView<>();
    private UserRepository savedUsers = new UserRepository();
    private File chosenSaveFile;

    private final Callback<ListView<User>, ListCell<User>> userToOnlyNamesAppearance = lv -> new ListCell<>() {
        @Override
        protected void updateItem(User user, boolean empty) {
            super.updateItem(user, empty);
            setText(empty ? "" : shortUserInfo(user));
        }
    };

    //TODO ? should combobox and other settings be done here?
    @FXML
    public void initialize() throws IOException {
        URL urlToGetUsers = new URL("https://jsonplaceholder.typicode.com/users");
        UserRepository userRep = new UserRepository(urlToGetUsers);
        comboBoxUsers.getItems().setAll(userRep.getUsers());
        comboBoxUsers.setCellFactory(userToOnlyNamesAppearance);
        comboBoxUsers.setButtonCell(userToOnlyNamesAppearance.call(null));
        ChangeListener<User> userChangeListener = (list, oldValue, newValue) -> textAreaSelectedUserInfo.setText(newValue != null ? longUserInfo(newValue) : "");
        comboBoxUsers.valueProperty().addListener(userChangeListener);

        addedUsersListView.setCellFactory(userToOnlyNamesAppearance);
    }


    //TODO ? should these methods be here, or in user class?

    /**
     * Shows id and name of the user
     *
     * @param user to get info about
     * @return user info string representation
     */
    private String shortUserInfo(User user) {
        return String.format("id: %d, Name: %s", user.getId(), user.getName());
    }

    /**
     * Method creates nice String representation of the User:
     *
     * @param user user to create representation
     * @return with long user info representation
     * "
     */
    private String longUserInfo(User user) {
        int lvlCount = 0;
        String userToString = user.toString();
        String filtered = userToString.substring(0, userToString.length() - 1)
                .replaceFirst("User\\{", "")
                .replaceAll(" ", "");
        StringBuilder sb = new StringBuilder();
        for (char c : filtered.toCharArray()) {
            sb.append(c);
            if (c == ',') {
                sb.append("\n").append("\t".repeat(lvlCount));
            }
            if (c == '{') {
                sb.append("\n").append("\t".repeat(++lvlCount));
            }
            if (c == '}') {
                lvlCount--;
            }
        }
        return sb.toString();
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
        addedUsersListView.getItems().remove(selectedUser);
    }

    /**
     * Shows info about selected user
     *
     * @param actionEvent button info gets clicked
     */
    @FXML
    public void buttonShowInfo(ActionEvent actionEvent) {
        User selectedUser = addedUsersListView.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            Alert info = new Alert(Alert.AlertType.INFORMATION);
            info.setTitle("Info");
            info.setHeaderText("Info about selected user: " + shortUserInfo(selectedUser));
            info.setContentText(longUserInfo(selectedUser));
            info.show();
        }
    }

    //TODO refactor
    public void menuButtonOpen(ActionEvent actionEvent) throws IOException {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilterTxt = new FileChooser.ExtensionFilter("TXT","*.txt");
        FileChooser.ExtensionFilter extensionFilterJson = new FileChooser.ExtensionFilter("*.json","*.json");
        FileChooser.ExtensionFilter extensionFilterAll = new FileChooser.ExtensionFilter("all files","*.*");
        fileChooser.getExtensionFilters().add(extensionFilterTxt);
        fileChooser.getExtensionFilters().add(extensionFilterJson);
        fileChooser.getExtensionFilters().add(extensionFilterAll);
        File fileOpened = fileChooser.showOpenDialog(null);
        if(fileOpened !=null){
            comboBoxUsers.getItems().addAll(new UserRepository(fileOpened).getUsers()); //set or add?
        }

    }

    public void menuButtonSave(ActionEvent actionEvent) throws IOException {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilterTxt = new FileChooser.ExtensionFilter("TXT","*.txt");
        FileChooser.ExtensionFilter extensionFilterJson = new FileChooser.ExtensionFilter("*.json","*.json");
        FileChooser.ExtensionFilter extensionFilterAll = new FileChooser.ExtensionFilter("all files","*.*");
        fileChooser.getExtensionFilters().add(extensionFilterTxt);
        fileChooser.getExtensionFilters().add(extensionFilterJson);
        fileChooser.getExtensionFilters().add(extensionFilterAll);

        if(chosenSaveFile != null) {
            chosenSaveFile = fileChooser.showSaveDialog(null);
            comboBoxUsers.getItems().forEach(savedUsers::addUser);
            if (chosenSaveFile != null) {
                savedUsers.save(chosenSaveFile);

            }
        }
    }

    public void menuButtonAbout(ActionEvent actionEvent) {
    }
}
