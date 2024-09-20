package ca.rashrasa.ponggame;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.Glow;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class GameScreenController {
    private ActionEvent restartEvent = null;
    private Game game;

    @FXML
    Circle puck, puck_light;

    @FXML
    Rectangle user;

    @FXML
    Rectangle bot;

    @FXML
    Text user_score_label1, bot_score_label1, pause_timer, winning_score_label;

    @FXML
    AnchorPane root, game_root;

    private Text endMessage;
    private Button restartButton;

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
        if(this.game == null){
            return;
        }

        Vector puckPosition = this.game.getPuckPosition();
        Vector playerPosition = this.game.getUserPosition();
        Vector botPosition = this.game.getBotPosition();

        this.puck.setLayoutX(puckPosition.x());
        this.puck.setLayoutY(puckPosition.y());
        this.puck.setFill(this.game.getPuckColor());
        this.puck.setStroke(game.getPuckColor().desaturate());

        this.puck_light.setLayoutX(puckPosition.x());
        this.puck_light.setLayoutY(puckPosition.y());

        Stop[] stops = new Stop[] {
                new Stop(0.0, game.getPuckColor().darker().darker()),
                new Stop(1.0, Color.BLACK)
        };

        this.puck_light.setFill(
                new RadialGradient(
                        0, 0, 0.5, 0.5, 0.5,
                        true,
                        CycleMethod.NO_CYCLE,
                        stops));

        this.user.setLayoutX(playerPosition.x());
        this.user.setLayoutY(playerPosition.y());
        this.user.setFill(this.game.getUserColor());

        this.bot.setLayoutX(botPosition.x());
        this.bot.setLayoutY(botPosition.y());
        this.bot.setFill(this.game.getBotColor());

        this.user_score_label1.setText(String.valueOf(this.game.getUserScore()));
        this.bot_score_label1.setText(String.valueOf(this.game.getBotScore()));

        this.pause_timer.setVisible(!game.hasEnded() && game.getPauseTimer() > 0.0);
        this.pause_timer.setText(
                String.valueOf(
                        Math.round(game.getPauseTimer()*100.0)/100.0
                )
        );

        this.winning_score_label.setText(String.valueOf(this.game.getWinningScore()));
    }

    public void displayGameEndScreen() {
        this.bot.setVisible(false);
        this.user.setVisible(false);
        this.puck.setVisible(false);
        this.pause_timer.setVisible(false);
        String winner = this.game.getUserScore() > this.game.getBotScore() ? "Player" : "Bot";

        //End message
        this.endMessage = new Text(winner + " wins.");
        endMessage.setLayoutX(game_root.getWidth()/2.0 - 45);
        endMessage.setLayoutY(game_root.getHeight()/2.0 - 15);
        endMessage.setFill(Color.WHITE);
        endMessage.setFont(Font.font("Century Gothic"));
        endMessage.setStyle("-fx-font-size: x-large; -fx-font-weight: bold");

        //Restart button
        this.restartButton = new Button("Restart");
        restartButton.setVisible(true);
        restartButton.setLayoutX(game_root.getWidth()/2.0 - 25);
        restartButton.setLayoutY(game_root.getWidth()/2.0 + 15);
        restartButton.setOpacity(0);
        restartButton.addEventHandler(ActionEvent.ACTION, this::onRestartPress);

        FadeTransition restartTransition = new FadeTransition(Duration.seconds(1));
        restartTransition.setNode(restartButton);
        restartTransition.setDelay(Duration.seconds(2.0));
        restartTransition.setFromValue(0.0);
        restartTransition.setToValue(1.0);
        this.game_root.getChildren().addAll(endMessage, restartButton);
        restartTransition.play();
    }

    private void onRestartPress(ActionEvent e){
        this.restartEvent = e;
    }

    public ActionEvent getRestartEvent() {
        return this.restartEvent;
    }

    public void reset(){
        this.restartEvent = null;
        this.game = null;
        this.user_score_label1.setText("0");
        this.bot_score_label1.setText("0");
        this.endMessage.setVisible(false);
        this.restartButton.setVisible(false);
        this.user.setVisible(true);
        this.puck.setVisible(true);
        this.puck.setFill(Color.WHITE);
        this.bot.setVisible(true);
        this.pause_timer.setVisible(true);
    }

}
