package ca.rashrasa.ponggame;

import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class GameScreenController {
    private Game game;
    @FXML
    Circle puck;

    @FXML
    Rectangle user;

    @FXML
    Rectangle bot;

    @FXML
    Text user_score_label1, bot_score_label1, pause_timer;

    @FXML
    AnchorPane root, game_root;

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
    }

    public void playerKeyTyped(KeyEvent keyEvent) {
    }

    public void update(){
        Vector puckPosition = this.game.getPuckPosition();
        Vector playerPosition = this.game.getUserPosition();
        Vector botPosition = this.game.getBotPosition();

        this.puck.setLayoutX(puckPosition.x());
        this.puck.setLayoutY(puckPosition.y());
        this.puck.setFill(this.game.getPuckColor());


        this.user.setLayoutX(playerPosition.x());
        this.user.setLayoutY(playerPosition.y());
        this.user.setFill(this.game.getUserColor());

        this.bot.setLayoutX(botPosition.x());
        this.bot.setLayoutY(botPosition.y());
        this.bot.setFill(this.game.getBotColor());

        this.user_score_label1.setText(String.valueOf(this.game.getUserScore()));
        this.bot_score_label1.setText(String.valueOf(this.game.getBotScore()));

        this.pause_timer.setVisible(game.getPauseTimer() > 0.0);
        this.pause_timer.setText(
                String.valueOf(
                        Math.round(game.getPauseTimer()*100.0)/100.0
                )
        );

    }

    public void displayGameEndScreen() {
        this.game_root.getChildren().removeAll();
        String winner = this.game.getUserScore() > this.game.getBotScore() ? "Player" : "User";
        Text endMessage = new Text(winner + " wins!");
        endMessage.setLayoutX(game_root.getWidth()/2.0);
        endMessage.setLayoutY(game_root.getHeight()/2.0);
        this.game_root.getChildren().add(endMessage);
        game_root.getId();
    }
}
