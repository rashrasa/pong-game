package ca.rashrasa.ponggame;

import javafx.scene.paint.Color;

public class Puck extends GameElement{
    private final Vector START_VELOCITY;
    private Vector position;
    private Vector velocity;
    private final double RADIUS = 20.0;
    private Color currentColor;
    private final Vector START_POSITION;

    public Puck(Vector start_pos, Vector start_vel){
        this.position = start_pos;
        this.velocity = start_vel;
        this.currentColor = Color.WHITE;
        this.START_POSITION = start_pos;
        this.START_VELOCITY = start_vel;
    }

    public Vector getPosition(){
        return new Vector(this.position);
    }

    public Vector getVelocity(){
        return new Vector(this.velocity);
    }

    public void tick(double ms){
        this.position = this.position.add(
                this.velocity.scalarProduct(ms/1000.0)
        );
    }

    @Override
    public void doCollisionAction(Direction collisionForceDirection) {
        this.velocity = this.velocity.changeDirection(collisionForceDirection);
        this.currentColor = Color.rgb(
                (int)(Math.random()*256),
                (int)(Math.random()*256),
                (int)(Math.random()*256));
    }

    @Override
    public Vector getStartPosition() {
        return this.START_POSITION;
    }

    @Override
    public void setPosition(Vector v0) {
        this.position = v0;
    }

    @Override
    public void reset() {
        this.position = START_POSITION;
        this.velocity = START_VELOCITY;
    }

    public double getRadius() {
        return this.RADIUS;
    }

    public Color getColor() {
        return this.currentColor;
    }
}
