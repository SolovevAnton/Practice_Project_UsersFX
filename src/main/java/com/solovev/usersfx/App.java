package com.solovev.usersfx;

import com.solovev.usersfx.controllers.ControllerData;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
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
     * @param name of the stage .fxml file
     * @param title of the stage
     * @param data to pass to the stage, or null if nothing
     * @return created stage
     * @param <T>
     * @throws IOException
     */
    public static <T> Stage getStage(String name, String title, T data) throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource(name));

        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setScene(  new Scene(loader.load())  );

        stage.setTitle(title);

        if (data != null) {
            ControllerData<T> controller = loader.getController();
            controller.initData(data);
        }
        return stage;
    }
}