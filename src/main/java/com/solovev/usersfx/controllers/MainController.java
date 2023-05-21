package com.solovev.usersfx.controllers;

import com.solovev.usersfx.model.User;
import com.solovev.usersfx.repository.UserRepository;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;

public class MainController {
    @FXML
    public ComboBox<User> comboBoxUsers = new ComboBox<>();
    public TextArea textAreaSelectedUserInfo;
    public ListView<User> addedUsersListView;

    @FXML
    public void initialize() throws IOException {
        URL urlToGetUsers = new URL("https://jsonplaceholder.typicode.com/users");
        UserRepository userRep = new UserRepository(urlToGetUsers);
        comboBoxUsers.getItems().setAll(userRep.getUsers());
        Callback<ListView<User>, ListCell<User>> factory = lv -> new ListCell<>() {
            @Override
            protected void updateItem(User item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? "" : item.getName());
            }
        };
        comboBoxUsers.setCellFactory(factory);
        comboBoxUsers.setButtonCell(factory.call(null));

        ChangeListener<User> changeInCombo = (list, oldValue, newValue) -> {
            if (newValue != null) {
                textAreaSelectedUserInfo.setText(showUserInfo(newValue));
            } else {
                textAreaSelectedUserInfo.setText("");
            }
        };
        comboBoxUsers.valueProperty().addListener(changeInCombo);
    }

    /**
     * Method creates nice String representation of the User:
     *
     * @param user user to create representation
     * @return string with nice representation
     * "
     */
    private String showUserInfo(User user) {
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

}
