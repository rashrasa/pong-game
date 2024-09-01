package ca.rashrasa.ponggame;

import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class GameScreenController {
    private Game game;
    @FXML
    Circle puck;

    @FXML
    Rectangle user;

    @FXML
    Rectangle bot;

    public void setGame(Game game){
        this.game = game;
    }


    public void playerKeyPressed(KeyEvent keyEvent) {
        KeyCode key = keyEvent.getCode();

        switch (key) {
            case LEFT:
                game.leftPressed();
                break;

            case RIGHT:
                game.rightPressed();
                break;
        }

        System.out.println("User x: " + user.getLayoutX());
        user.setLayoutX(this.game.getUserPosition().x());
    }

    public void playerKeyReleased(KeyEvent keyEvent){
        KeyCode key = keyEvent.getCode();
        switch(key){
            case LEFT:
                game.leftReleased();
                break;

            case RIGHT:
                game.rightReleased();
                break;
        }
        user.setLayoutX(this.game.getUserPosition().x());
    }
}
