package com.solovev.usersfx.controllers;

import com.solovev.usersfx.model.User;
import com.solovev.usersfx.repository.UserRepository;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class MainController {
    @FXML
    public ComboBox<User> comboBoxUsers = new ComboBox<>();

    @FXML
    public void initialize() throws IOException {
            URL urlToGetUsers = new URL("https://jsonplaceholder.typicode.com/users");
            UserRepository userRep = new UserRepository(urlToGetUsers);
            comboBoxUsers.getItems().setAll(userRep.getUsers());
            Callback<ListView<User>, ListCell<User>> factory = lv -> new ListCell<User>() {
                @Override
                protected void updateItem(User item, boolean empty) {
                    super.updateItem(item, empty);
                    setText(empty ? "" : item.getName());
                }
            };
            comboBoxUsers.setCellFactory(factory);
            comboBoxUsers.setButtonCell(factory.call(null));
    }

}
