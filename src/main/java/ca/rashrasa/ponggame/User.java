package ca.rashrasa.ponggame;

import javafx.scene.paint.Color;

public class User extends GameElement{
    private final double WIDTH = 100.0;
    private final double HEIGHT = 15.0;

    private double x;
    private final double Y = 480;
    private final double MOVE_SPEED = 200; //pixels per second
    private boolean isLeftPressed, isRightPressed;

    private Color currentColor;

    public User(){
        this.x = 200;
        this.currentColor = Color.WHITE;
    }

    public void tick(double ms){
        double seconds = ms/1000.0;
        if(isLeftPressed){
            this.x = x - MOVE_SPEED*seconds;
        }
        if(isRightPressed){
            this.x = x + MOVE_SPEED*seconds;
        }
    }

    @Override
    void doCollisionAction(Direction collisionForceDirection) {
        // User and bot are immovable (Do nothing)
    }

    public void leftPressed(){
        this.isLeftPressed = true;
    }
    public void rightPressed(){
        this.isRightPressed = true;
    }
    public void leftReleased(){
        this.isLeftPressed = false;
    }
    public void rightReleased() {
        this.isRightPressed = false;
    }

    public Vector getPosition() {
        return new Vector(this.x, this.Y);
    }
    public double getWidth(){
        return this.WIDTH;
    }
    public double getHeight(){
        return this.HEIGHT;
    }
    public Color getCurrentColor(){
        return this.currentColor;
    }
}
