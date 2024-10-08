package ca.rashrasa.ponggame;

import javafx.scene.paint.Color;
import java.util.ArrayList;

public class Game implements Runnable{
    private volatile boolean gameOpen, running, roundStarted;

    private Puck puck;
    private User user;
    private Bot bot;
    public final int TICK_RATE = 60;
    private final ArrayList<GameElement> gameElements;
    private double pauseTimer;
    private int botScore, playerScore, maxScore;
    private boolean gameEnded;

    /**
     Stores game information and contains its execution.
     Game execution:
     1. Game tick is called for x milliseconds
        - Usually for 1000/(tick rate) milliseconds
     2. All objects that perform actions are ticked by the game the same x milliseconds
     3. Objects that are ticked calculate their new positions, velocities, and any interactions with other objects
     */
    public Game(){
        this.running = true;
        this.gameOpen = true;
        this.gameElements = new ArrayList<>();
        this.roundStarted = false;
        this.gameEnded = false;
    }

    public void startGame(int max_score, int bot_difficulty){
        System.out.println("Max Score: "+max_score);
        System.out.println("Bot Difficulty: "+bot_difficulty);
        this.user = new User();
        this.bot = new Bot(bot_difficulty);
        this.puck = new Puck(new Vector(250,250), new Vector(0,250));
        this.pauseTimer = 3.0;
        this.gameElements.add(this.bot);
        this.gameElements.add(this.puck);
        this.gameElements.add(this.user);

        this.botScore = 0;
        this.playerScore = 0;
        this.maxScore = max_score;
    }
    public void run() {
        long startTime = System.nanoTime();
        double tickPeriod = 1000.0/TICK_RATE;
        long updates = 0;
        while(this.gameOpen){
            if((System.nanoTime()-startTime)/(tickPeriod*1000000) > updates){
                if(running){
                    this.tick(tickPeriod);
                }
                updates++;
            }

        }
    }

    private void resetRound(){
        this.pauseTimer = 1.0;
        for (GameElement e: this.gameElements){
            e.reset();
        }
    }

    private void tick(double ms){
        if (gameEnded){
            this.stop();
        }
        else if(playerScore >= this.maxScore || botScore >= this.maxScore ){
            this.endGame();
        }

        else if (this.pauseTimer>0){
            this.user.disableControls();
            this.pauseTimer-=ms/1000.0;
            return;
        }
        else if(!this.roundStarted){
            this.roundStarted = true;
            this.user.enableControls();
        }

        Vector puckCenter = getPuckPosition();
        double puckRadius = getPuckRadius();

        Vector puckTop = puckCenter.add(new Vector(0, -puckRadius));
        Vector puckBottom = puckCenter.add(new Vector(0, puckRadius));

        Vector botPositionTopLeft = getBotPosition();
        double botWidth = getBotWidth();
        double botHeight = getBotHeight();
        Vector botCenter = botPositionTopLeft.add(new Vector(botWidth/2.0, botHeight/2.0));

        //Out of bounds detection
        if(puckTop.y() < getTopBoundary()){
            playerWinsRound();
        }
        else if(puckBottom.y()>getBottomBoundary()){
            botWinsRound();
        }

        doCollisionActions();

        // Set bot direction - tracks puck
        if(botCenter.x()<puckCenter.x()){
            this.bot.setMoveDirection(1);
        }
        else if(botCenter.x()>puckCenter.x()){
            this.bot.setMoveDirection(-1);
        }
        else{
            this.bot.setMoveDirection(0);
        }

        // Tick all game elements
        for(GameElement e: this.gameElements){
            e.tick(ms);
        }
    }

    private void doCollisionActions() {
        Vector puckCenter = getPuckPosition();
        double puckRadius = getPuckRadius();
        Vector puckVelocity = getPuckVelocity();

        Vector puckTop = puckCenter.add(new Vector(0, -puckRadius));
        Vector puckBottom = puckCenter.add(new Vector(0, puckRadius));
        Vector puckLeft = puckCenter.add(new Vector(-puckRadius, 0));
        Vector puckRight = puckCenter.add(new Vector(puckRadius, 0));

        Vector userPositionTopLeft = getUserPosition();
        double userWidth = getUserWidth();
        double userHeight = getUserHeight();

        Vector botPositionTopLeft = getBotPosition();
        double botWidth = getBotWidth();
        double botHeight = getBotHeight();

        /*
            New collision changes:
                1. Check y distance from puck bottom to user top
                2. Check x distance from puck right to left user edge
                   if puck on the left of left edge, else check puck left to user right
                3. Distance less than radius means collision
                These cover all potential collisions
        */
        Vector collisionTest=puckCenter;
        double theta = new Direction(puck.getVelocity()).getTheta();
        // Puck -> User
        if(puckCenter.x()<=userPositionTopLeft.x()){
            collisionTest=new Vector(userPositionTopLeft.x(),collisionTest.y());
            theta = 5.0/6.0*Math.PI;
        }
        else if(puckCenter.x()>userPositionTopLeft.x()+userWidth){
            collisionTest=new Vector(userPositionTopLeft.x()+userWidth,collisionTest.y());
            theta = 1.0/6.0*Math.PI;
        }

        if(puckCenter.y()<=userPositionTopLeft.y()){
            collisionTest=new Vector(collisionTest.x(), userPositionTopLeft.y());
            // -PI/6 to -5PI/6
            //@userleft to userleft+width
            theta = (userWidth-(puckCenter.x()-userPositionTopLeft.x())/userWidth)*(-4.0/6.0*Math.PI) - Math.PI/6.0;
        }
        else if(puckCenter.y()>userPositionTopLeft.y()+userWidth){
            collisionTest=new Vector(collisionTest.x(), userPositionTopLeft.y()+userHeight);
            theta = 1.0/2.0*Math.PI;
        }

        if(collisionTest.distanceTo(puckCenter) <= puckRadius){
            puck.doCollisionAction(new Direction(theta));
        }


        //Puck -> Bot
        collisionTest=puckCenter;
        theta = new Direction(puck.getVelocity()).getTheta();

        if(puckCenter.x()<=botPositionTopLeft.x()){
            collisionTest=new Vector(botPositionTopLeft.x(),collisionTest.y());
            theta = 1.0/6.0*Math.PI;
        }
        else if(puckCenter.x()>botPositionTopLeft.x()+botWidth){
            collisionTest=new Vector(botPositionTopLeft.x()+botWidth,collisionTest.y());
            theta = 5.0/6.0*Math.PI;
        }

        if(puckCenter.y()>=botPositionTopLeft.y()+botHeight){
            collisionTest=new Vector(collisionTest.x(), botPositionTopLeft.y()+botHeight);
            theta = -(botWidth-(puckCenter.x()-botPositionTopLeft.x())/botWidth)*(-4.0/6.0*Math.PI) + Math.PI/6.0;
        }
        else if(puckCenter.y()<botPositionTopLeft.y()){
            collisionTest=new Vector(collisionTest.x(), botPositionTopLeft.y()+botHeight);
            theta = -1.0/2.0*Math.PI;
        }

        if(collisionTest.distanceTo(puckCenter) <= puckRadius){
            puck.doCollisionAction(new Direction(theta));
        }
        // Puck -> Left/Right boundaries
        if(puckLeft.x() <= getLeftBoundary()){
            this.puck.doCollisionAction(
                    new Direction(
                            new Vector(
                                    puckVelocity.x()*-1,
                                    puckVelocity.y()
                            )
                    )
            );
        }

        if(puckRight.x() >= getRightBoundary()){
            this.puck.doCollisionAction(
                    new Direction(
                            new Vector(
                                    puckVelocity.x()*-1,
                                    puckVelocity.y()
                            )
                    )
            );
        }
    }

    private void endGame() {
        this.pauseTimer = 5;
        this.resetRound();
        this.gameEnded = true;
    }

    private void playerWinsRound(){
        this.resetRound();
        this.playerScore++;
        this.roundStarted = false;
    }

    private void botWinsRound(){
        this.resetRound();
        this.botScore++;
        this.roundStarted = false;
    }

    public double getLeftBoundary() {
        return 0.0;
    }

    public double getRightBoundary() {
        return 500.0;
    }

    public double getTopBoundary() {
        return 0.0;
    }

    public double getBottomBoundary() {
        return 500.0;
    }

    public void stop(){
        this.running=false;
    }

    // Puck info
    public Vector getPuckPosition(){
        return this.puck.getPosition();
    }
    public Vector getPuckVelocity(){
        return this.puck.getVelocity();
    }
    public double getPuckRadius(){
        return this.puck.getRadius();
    }
    public Color getPuckColor() {
        return this.puck.getColor();
    }

    // User info
    public Vector getUserPosition(){
        return this.user.getPosition();
    }
    public double getUserWidth() {
        return this.user.getWidth();
    }
    public double getUserHeight() {
        return this.user.getHeight();
    }
    public Color getUserColor(){
        return this.user.getCurrentColor();
    }

    // Bot info
    public Vector getBotPosition(){
        return this.bot.getPosition();
    }
    public double getBotWidth() {
        return this.bot.getWidth();
    }
    public double getBotHeight() {
        return this.bot.getHeight();
    }
    public Color getBotColor(){
        return this.bot.getCurrentColor();
    }

    public void leftPressed() {
        this.user.leftPressed();
    }
    public void rightPressed() {
        this.user.rightPressed();
    }
    public void leftReleased() {
        this.user.leftReleased();
    }
    public void rightReleased() {
        this.user.rightReleased();
    }

    public boolean isRoundStarted() {
        return this.roundStarted;
    }

    public boolean isRunning(){
        return this.running;
    }

    public boolean isOpen(){
        return this.gameOpen;
    }

    public boolean hasEnded(){
        return this.gameEnded;
    }

    public int getUserScore() {
        return this.playerScore;
    }

    public int getBotScore() {
        return this.botScore;
    }

    public double getPauseTimer() {
        return this.pauseTimer;
    }

    public int getWinningScore() {
        return this.maxScore;
    }

    public void close() {
        this.gameOpen = false;
    }
}
