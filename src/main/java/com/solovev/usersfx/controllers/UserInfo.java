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
    private User user;

    @Override
    public void initData(User value) { //TODO why it is needed, why not initialize?
        textFieldID.setText("User ID: " + value.getId());
        textFieldName.setText("Name: " + value.getName());
        //Editable fields
        textFieldUsername.setText(value.getUsername());
        textFieldEmail.setText(value.getEmail());
        textFieldPhone.setText(value.getPhone());
        textFieldWebsite.setText(value.getWebsite());
        this.user = value;
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

    /**
     * Modifies given user based on the fields' values
     *
     * @param actionEvent
     */
    @FXML
    public void buttonSave(ActionEvent actionEvent) {
        User updatedUser = new User(
                user.getId(),
                textFieldName.getText(),
                textFieldUsername.getText(),
                textFieldEmail.getText(),
                user.getAddress(),
                textFieldPhone.getText(),
                textFieldWebsite.getText(),
                user.getCompany()
        );
        if (!user.equals(updatedUser)) {
            user = updatedUser;
        }
        buttonClose(actionEvent);
    }

    public User getUser() {
        return user;
    }
}
