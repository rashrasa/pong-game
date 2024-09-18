package ca.rashrasa.ponggame;

import javafx.scene.paint.Color;

public class User extends GameElement{
    private final Vector START_POSITION;
    private final double WIDTH = 100.0;
    private final double HEIGHT = 15.0;

    private Vector position;
    private final double MOVE_SPEED = 200; //pixels per second
    private boolean isLeftPressed, isRightPressed, hasControl;

    private Color currentColor;

    public User(){
        this.position = new Vector(200, 480);
        this.START_POSITION = this.position;
        this.currentColor = Color.WHITE;
        this.hasControl = false;
    }

    public void tick(double ms){
        double seconds = ms/1000.0;
        if(isLeftPressed && hasControl){
            this.position = this.position.translateEnd(-MOVE_SPEED*seconds, 0);
        }
        if(isRightPressed && hasControl){
            this.position = this.position.translateEnd(MOVE_SPEED*seconds, 0);
        }
    }

    @Override
    void doCollisionAction(Direction collisionForceDirection) {
        // User and bot are immovable (Do nothing)
    }

    @Override
    public void reset() {
        this.position = START_POSITION;
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
        return new Vector(this.position);
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

    public void disableControls() {
        this.hasControl = false;
    }

    public void enableControls(){
        this.hasControl = true;
    }
}
