package com.filip.tablehw;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.scene.layout.BorderPane;


public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("primary.fxml"));
        BorderPane border = loader.load();
        scene = new Scene(border);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        App.launch();
    }

}