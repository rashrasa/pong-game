package ca.rashrasa.ponggame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GameDisplay extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxml = new FXMLLoader(GameDisplay.class.getResource("main-menu.fxml"));
        Scene scene = new Scene(fxml.load(), 100, 100);
        stage.setTitle("Pong");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setX(400);
        stage.setY(100);
        stage.setWidth(600);
        stage.setHeight(600);
        stage.show();
    }
    public static void main(String [] args){
        launch();
    }
}
