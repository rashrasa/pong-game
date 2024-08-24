package ca.rashrasa.ponggame;

import java.util.ArrayList;

public class Game {
    private volatile boolean running;

    private Puck puck;
    private User user;
    private Bot bot;
    public final int TICK_RATE = 60;
    private final ArrayList<? extends GameElement> gameElements;

    /**
    Stores game information and contains its execution.
     */
    public Game(){
        this.running = false;
        this.gameElements = new ArrayList<>();
    }

    public void startGame(int max_score, int bot_difficulty){
        System.out.println("Max Score: "+max_score);
        System.out.println("Bot Difficulty: "+bot_difficulty);
        this.running=true;
        this.gameElements.add(new Bot())
        this.gameElements.add(new Puck(new Vector(250,250), new Vector(1,-1)));
        this.run();
    }
    public void run() {
        while(running){
            this.tick(1000.0/TICK_RATE);
        }
    }

    @Override
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
