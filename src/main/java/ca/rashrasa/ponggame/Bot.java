package ca.rashrasa.ponggame;

import javafx.scene.paint.Color;

public class Bot extends GameElement {
    private final double WIDTH = 100.0;
    private final double HEIGHT = 15.0;
    private final double MOVE_SPEED = 200.0;

    private int moveDirection = 0;
    private Color currentColor;

    private Vector position;

    public Bot(){
        this.position = new Vector(200,5);
        this.currentColor = Color.WHITE;
    }

    @Override
    public void tick(double ms) {
        double seconds = ms/1000.0;
        this.position = this.position.add(new Vector(moveDirection*MOVE_SPEED*seconds, 0));
    }

    @Override
    void doCollisionAction(Direction collisionForceDirection) {
        // User and bot are immovable (Do nothing)
    }

    public Vector getPosition() {
        return new Vector(position);
    }

    public void setMoveDirection(int d){
        this.moveDirection = d;
    }

    public double getWidth(){
        return this.WIDTH;
    }
    public double getHeight(){
        return this.HEIGHT;
    }

    public Color getCurrentColor() {
        return this.currentColor;
    }
}
