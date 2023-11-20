package com.example.okienka3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

public class EMMain extends Application {
    private static Stage stage;
    @Override
    public void start(Stage mainStage) throws IOException {
        stage = mainStage;
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("main-menu.fxml")));
        Scene scene = new Scene(root, 1280, 720);
        mainStage.setTitle("Employee Management Program");
        mainStage.setScene(scene);
        mainStage.show();
    }
    public static void main(String[] args) {
        launch();
    }
    public void changeScene(String fxml) throws IOException{
        Parent pane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxml)));
        stage.getScene().setRoot(pane);
    }
}
