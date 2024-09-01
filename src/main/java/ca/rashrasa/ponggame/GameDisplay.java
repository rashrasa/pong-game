package ca.rashrasa.ponggame;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;

public class GameDisplay extends Application {
    private Stage stage;
    private Scene mainScene;
    private FXMLLoader mainMenu=new FXMLLoader(GameDisplay.class.getResource("main-menu.fxml"));
    private FXMLLoader preGameMenu=new FXMLLoader(GameDisplay.class.getResource("pre-game-menu.fxml"));
    private FXMLLoader gameScreen=new FXMLLoader(GameDisplay.class.getResource("game-screen.fxml"));
    private Game game = new Game();
    private Thread gameThread = new Thread(game);

    @FXML TextField max_score_field;
    @FXML Slider bot_difficulty_slider;

    @Override
    public void start(Stage stage) throws IOException {
        this.stage=stage;
        this.mainScene=new Scene(mainMenu.load(),600,600);

        this.stage.setTitle("Pong");
        this.stage.setScene(mainScene);
        this.stage.setResizable(false);
        this.stage.setX(440);
        this.stage.setY(50);
        this.stage.setWidth(600);
        this.stage.setHeight(700);
        this.stage.show();
    }
    public static void main(String [] args){
        launch();
    }

    @FXML
    protected void onStartPress(ActionEvent e) throws IOException {
        AnchorPane root = gameScreen.load();
        GameScreenController controller = gameScreen.getController();
        controller.setGame(this.game);

        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        scene.addEventHandler(KeyEvent.KEY_PRESSED, controller::playerKeyPressed);
        scene.addEventHandler(KeyEvent.KEY_RELEASED, controller::playerKeyReleased);
        stage.setScene(scene);

        game.startGame(Integer.parseInt(max_score_field.getText()), (int)(bot_difficulty_slider.getValue()));
        gameThread.start();
    }

    @FXML
    protected void onPlayPress(ActionEvent e) throws IOException {
        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        stage.setScene(new Scene(preGameMenu.load()));
    }

    @FXML
    protected void onOptionsPress(ActionEvent e){
        System.out.println("Pressed: Options button");
    }

    @FXML
    protected void onExitPress(ActionEvent e){
        System.exit(0);
    }
}
