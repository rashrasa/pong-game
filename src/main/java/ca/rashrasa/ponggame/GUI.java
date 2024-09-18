package ca.rashrasa.ponggame;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;

import java.io.IOException;

public class GUI{
    private Thread gameThread;
    private Thread renderThread;

    private Scene rootScene;

    private FXMLLoader mainMenu;
    private FXMLLoader preGameMenu;
    private FXMLLoader gameScreen;

    private Parent mainMenuRoot, preGameMenuRoot, gameScreenRoot;

    private MainMenuController mainMenuController;
    private PreGameMenuController preGameMenuController;
    private GameScreenController gameScreenController;

    public GUI() throws IOException {
        loadMenus();
        this.rootScene = new Scene(mainMenuRoot);
    }
    public Scene getScene(){
        return this.rootScene;
    }

    @FXML
    protected void onStartPress(ActionEvent e, int maxScore, int difficulty) {
        Game game = new Game();
        this.gameThread = new Thread(game);
        gameScreenController.setGame(game);

        this.renderThread = new Thread(new ViewRenderThread(game, this, gameScreenController));
        this.rootScene.setRoot(gameScreenRoot);

        //Keyboard event handlers
        this.rootScene.addEventHandler(KeyEvent.KEY_PRESSED, gameScreenController::playerKeyPressed);
        this.rootScene.addEventHandler(KeyEvent.KEY_RELEASED, gameScreenController::playerKeyReleased);
        this.rootScene.addEventHandler(KeyEvent.KEY_TYPED, gameScreenController::playerKeyTyped);

        game.startGame(maxScore, difficulty);

        this.gameThread.start();
        this.renderThread.start();
    }

    public void onPlayPress(ActionEvent e) {
        this.rootScene.setRoot(preGameMenuRoot);
    }

    public void onOptionsPress(ActionEvent e){
        System.out.println("Pressed: Options button");
    }

    public void onExitPress(ActionEvent e){
        System.exit(0);
    }

    public void restart() throws IOException {
        cleanup();
        this.rootScene.setRoot(mainMenuRoot);
    }

    private void cleanup() {
        this.gameScreenController.reset();
    }

    private void loadMenus() throws IOException {
        this.mainMenu=new FXMLLoader(FXApplication.class.getResource("main-menu.fxml"));
        this.preGameMenu=new FXMLLoader(FXApplication.class.getResource("pre-game-menu.fxml"));
        this.gameScreen=new FXMLLoader(FXApplication.class.getResource("game-screen.fxml"));

        this.mainMenuRoot = this.mainMenu.load();
        this.preGameMenuRoot = this.preGameMenu.load();
        this.gameScreenRoot = this.gameScreen.load();

        this.mainMenuController = this.mainMenu.getController();
        this.preGameMenuController = this.preGameMenu.getController();
        this.gameScreenController = this.gameScreen.getController();

        this.mainMenuController.setGUI(this);
        this.preGameMenuController.setGUI(this);
    }
}
