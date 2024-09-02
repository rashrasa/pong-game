package ca.rashrasa.ponggame;

import javafx.scene.paint.Color;

public class Puck extends GameElement{
    private Vector position;
    private Vector velocity;
    private double RADIUS = 20.0;
    private Color currentColor;

    public Puck(Vector start_pos, Vector start_vel){
        this.position = start_pos;
        this.velocity = start_vel;
        this.currentColor = Color.WHITE;
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
    void doCollisionAction(Direction collisionForceDirection) {
        this.velocity = this.velocity.changeDirection(collisionForceDirection);
        this.currentColor = Color.rgb(
                (int)(Math.random()*256),
                (int)(Math.random()*256),
                (int)(Math.random()*256));
    }

    public double getRadius() {
        return this.RADIUS;
    }

    public Color getColor() {
        return this.currentColor;
    }
}
