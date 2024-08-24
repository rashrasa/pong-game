package ca.rashrasa.ponggame;

public class Game{
    private volatile boolean running;

    private Puck puck;
    private User user;
    private Bot bot;

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
    }




    public void startGame(int max_score, int bot_difficulty){
        System.out.println("Max Score: "+max_score);
        System.out.println("Bot Difficulty: "+bot_difficulty);
        this.running=true;
        this.run();
    }

    public void run() {
        long lastUpdate=System.currentTimeMillis();
        double fps = 1.0;
        while (running) {
            long currentTime = System.currentTimeMillis();
            if(currentTime>lastUpdate+(1000.0/fps)){
                System.out.println("Game is running.");
                lastUpdate=currentTime;
            }
        }
    }

    public void stop(){
        this.running=false;
    }

    // Puck info
    public Position getPuckPosition(){
        return null;
    }
    public double getPuckRadius(){
        return 0.0;
    }

    // User info
    public Position getUserPosition(){
        return null;
    }
    public double getUserWidth(){
        return 0.0;
    }
    public double getUserHeight(){
        return 0.0;
    }

    // Bot info
    public Position getBotPosition(){
        return null;
    }
    public double getBotWidth(){
        return 0.0;
    }
    public double getBotHeight(){
        return 0.0;
    }


}
