package com.solovev.usersfx;

import com.solovev.usersfx.controllers.ControllerData;
import javafx.application.Application;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("main.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Users saving UI");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    /**
     * Method to set up additional stage
     *
     * @param name  of the stage .fxml file
     * @param title of the stage
     * @param data  to pass to the stage, or null if nothing
     * @param <T>
     * @return created stage
     * @throws IOException
     */
    public static <T> Stage getStage(String name, String title, T data) throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource(name));

        Stage stage = new Stage(StageStyle.DECORATED);

        stage.setScene(new Scene(loader.load()));

        stage.setTitle(title);

        if (data != null) {
            ControllerData<T> controller = loader.getController();
            controller.initData(data);
        }
        return stage;
    }

    /**
     * Method to open stage without freezing the main one
     *
     * @param name  of the stage .fxml file
     * @param title of the stage
     * @param data  to pass to the stage, or null if nothing
     * @return created stage
     * @throws IOException
     */
    public static <T> Stage openWindow(String name, String title, T data) throws IOException {
        Stage stage = getStage(name, title, data);
        stage.show();
        return stage;
    }

    /**
     * Method to open stage freezing the main one
     *
     * @param name  of the stage .fxml file
     * @param title of the stage
     * @param data  to pass to the stage, or null if nothing
     * @return created stage
     * @throws IOException
     */
    public static <T> Stage openWindowAndWait(String name, String title, T data) throws IOException {
        Stage stage = getStage(name, title, data);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
        return stage;
    }

    /**
     * Method to close window
     *
     * @param event what triggers window closing
     */
    public static void closeWindow(Event event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    /**
     * Creates and shows alert without header
     *
     * @param title     title of the alert
     * @param content   content of the alert
     * @param alertType type
     */
    public static void showAlertWithoutHeaderText(String title, String content, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

}