package com.solovev.usersfx.controllers;

import com.solovev.usersfx.App;
import com.solovev.usersfx.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 * Controller of the user info dialog scene. Pops up, when info button on the main scene is pressed
 */

public class UserInfo implements ControllerData<User> {
    @FXML
    public TextField textFieldID;
    @FXML
    public TextField textFieldName;
    @FXML
    public TextField textFieldUsername;
    @FXML
    public TextField textFieldEmail;
    @FXML
    public TextField textFieldPhone;
    @FXML
    public TextField textFieldWebsite;

    @Override
    public void initData(User value) { //TODO why it is needed, why not initialize?
        textFieldID.setText("User ID: " + value.getId());
        textFieldName.setText("Name: " + value.getName());
        //Editable fields
        textFieldUsername.setText(value.getUsername());
        textFieldEmail.setText(value.getEmail());
        textFieldPhone.setText(value.getPhone());
        textFieldWebsite.setText(value.getWebsite());
    }

    /**
     * closes window
     *
     * @param actionEvent to trigger close
     */
    @FXML
    public void buttonClose(ActionEvent actionEvent) {
        App.closeWindow(actionEvent);
    }
}
