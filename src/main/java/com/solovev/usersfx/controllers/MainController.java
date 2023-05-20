package com.solovev.usersfx.controllers;

import com.solovev.usersfx.model.User;
import com.solovev.usersfx.repository.UserRepository;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class MainController {
    @FXML
    public ComboBox<User> ComboBoxOne = new ComboBox<>();

    public MainController() {
        try {
            URL urlToGetUsers = new URL("https://jsonplaceholder.typicode.com/users");
            UserRepository userRep = new UserRepository(urlToGetUsers);
            ComboBoxOne.getItems().setAll(userRep.getUsers());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
