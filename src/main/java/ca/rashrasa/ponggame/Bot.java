package ca.rashrasa.ponggame;

import javafx.scene.paint.Color;

public class Bot extends GameElement {
    private final Vector START_POSITION;
    private final double WIDTH = 100.0;
    private final double HEIGHT = 15.0;
    private final double MOVE_SPEED;

    private int moveDirection = 0;
    private Color currentColor;

    private Vector position;

    public Bot(int difficulty){
        this.position = new Vector(200,5);
        this.currentColor = Color.WHITE;
        this.START_POSITION = new Vector(200,5);
        this.MOVE_SPEED = 150.0 * (difficulty/4.0);
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

    @Override
    public Vector getStartPosition() {
        return this.START_POSITION;
    }

    @Override
    public void setPosition(Vector v0) {
        this.position = new Vector(v0.x(), this.position.y());
    }

    @Override
    public void reset() {
        this.position = START_POSITION;
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
