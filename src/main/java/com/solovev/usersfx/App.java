package com.solovev.usersfx;

import com.solovev.usersfx.util.Json2PojoGenerator;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("main.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws IOException {

        URL url = new URL("https://jsonplaceholder.typicode.com/users");

        try(
                BufferedInputStream br = new BufferedInputStream(url.openStream());
                FileOutputStream fot = new FileOutputStream("Users.json");
        ){
            fot.write(br.readAllBytes());
            String fileNameUsers = "Users.json";
            //generate classes of user:
            Json2PojoGenerator generatorUsers = new Json2PojoGenerator(fileNameUsers,"src/main/java/");
            String classNameUsers = "Users";
            String packageNameUsers = "com.solovev.model";
            generatorUsers.generate(classNameUsers, packageNameUsers);

        }
    }
}