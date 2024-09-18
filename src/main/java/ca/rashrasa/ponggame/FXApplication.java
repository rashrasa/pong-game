package ca.rashrasa.ponggame;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class FXApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        GUI gui = new GUI();

        stage.setTitle("Pong");
        stage.setResizable(false);
        stage.setX(440);
        stage.setY(50);
        stage.setWidth(600);
        stage.setHeight(700);
        stage.setScene(gui.getScene());
        stage.show();
    }
    public static void main(String [] args){
        launch();
    }
}
