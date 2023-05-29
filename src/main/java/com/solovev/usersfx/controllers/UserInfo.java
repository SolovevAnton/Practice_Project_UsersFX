package com.solovev.usersfx.controllers;

import com.solovev.usersfx.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * Controller of the user info dialog scene. Pops up, when info button on the main scene is pressed
 */

public class UserInfo implements ControllerData<User>{
    @FXML
    public TextField textFieldID;
    @FXML
    public TextField textFieldName;
    @FXML
    public TextField textFieldUsername;
    @FXML
    public TextField textFieldEmail;
    @FXML
    public TextArea textAreaAddress;
    @FXML
    public TextField textFieldPhone;
    @FXML
    public TextField textFieldWebsite;
    @FXML
    public TextArea textAreaCompany;

    @Override
    public void initData(User value) {

    }

    @FXML
    public void buttonClose(ActionEvent actionEvent) {
    }
    @FXML
    public void buttonSave(ActionEvent actionEvent) {
    }
}
