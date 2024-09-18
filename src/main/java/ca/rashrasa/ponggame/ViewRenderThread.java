package ca.rashrasa.ponggame;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewRenderThread implements Runnable{
    private GameScreenController controller;
    private Game game;
    private GUI gui;
    private volatile boolean ended;

    public ViewRenderThread(Game game, GUI gui, GameScreenController controller){
        this.controller = controller;
        this.game = game;
        this.gui = gui;
        this.ended = false;
    }
    @Override
    public void run() {
        double FRAME_RATE = 240.0;
        long startTime = System.nanoTime();
        double renderPeriod = 1000.0/FRAME_RATE;
        long updates = 0;

        while(this.game.isOpen()){
            ActionEvent restartEvent = controller.getRestartEvent();
            if(restartEvent != null){
                try {
                    this.gui.restart();
                    this.game.close();
                    break;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if((System.nanoTime()-startTime)/(renderPeriod*1000000) > updates){
                Platform.runLater(() -> {
                    if(!ended && game.hasEnded()){
                        this.controller.displayGameEndScreen();
                        ended = true;
                    }
                    controller.update();
                });
                updates++;
            }
        }
    }
}
