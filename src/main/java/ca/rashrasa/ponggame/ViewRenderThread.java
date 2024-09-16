package ca.rashrasa.ponggame;

import javafx.application.Platform;

public class ViewRenderThread implements Runnable{
    private GameScreenController controller;
    private Game game;

    public ViewRenderThread(Game game, GameScreenController controller){
        this.controller = controller;
        this.game = game;
    }
    @Override
    public void run() {
        double FRAME_RATE = 240.0;
        long startTime = System.nanoTime();
        double renderPeriod = 1000.0/FRAME_RATE;
        long updates = 0;
        while(this.game.isRunning()){
            if(game.hasEnded()){
                break;
            }
            if((System.nanoTime()-startTime)/(renderPeriod*1000000) > updates){
                Platform.runLater(() -> {
                    controller.update();
                });
                updates++;
            }
        }
        this.controller.displayGameEndScreen();
    }
}
