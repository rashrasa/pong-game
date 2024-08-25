package ca.rashrasa.ponggame;

import java.util.ArrayList;

public class Game {
    private volatile boolean running;

    private Puck puck;
    private User user;
    private Bot bot;
    public final int TICK_RATE = 60;
    private final ArrayList<GameElement> gameElements;

    /**
     Stores game information and contains its execution.
     Game execution:
     1. Game tick is called for x milliseconds
        - Usually for 1000/(tick rate) milliseconds
     2. All objects that perform actions are ticked by the game the same x milliseconds
     3. Objects that are ticked calculate their new positions, velocities, and any interactions with other objects
     */
    public Game(){
        this.running = false;
        this.gameElements = new ArrayList<>();
    }

    public void startGame(int max_score, int bot_difficulty){
        System.out.println("Max Score: "+max_score);
        System.out.println("Bot Difficulty: "+bot_difficulty);
        this.running=true;
        this.gameElements.add(new Bot());
        this.gameElements.add(new Puck(new Vector(250,250), new Vector(1,-1)));
        this.run();
    }
    public void run() {
        long startTime = System.nanoTime();
        double tickPeriod = 1000.0/TICK_RATE;
        long updates = 0;
        while(running){
            if((System.nanoTime()-startTime)/tickPeriod > updates){
                this.tick(tickPeriod);
                updates++;
            }

        }
    }

    public void tick(double ms){
        for(GameElement e: this.gameElements){
            e.tick(ms);
        }
    }

    public void stop(){
        this.running=false;
    }

    // Puck info
    public Vector getPuckPosition(){
        return null;
    }
    public double getPuckRadius(){
        return 0.0;
    }

    // User info
    public Vector getUserPosition(){
        return null;
    }
    public double getUserWidth(){
        return 0.0;
    }
    public double getUserHeight(){
        return 0.0;
    }

    // Bot info
    public Vector getBotPosition(){
        return null;
    }
    public double getBotWidth(){
        return 0.0;
    }
    public double getBotHeight(){
        return 0.0;
    }


}
