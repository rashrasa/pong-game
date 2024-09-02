package ca.rashrasa.ponggame;

public class Bot extends GameElement {
    private final double WIDTH = 100.0;
    private final double HEIGHT = 15.0;

    private Vector position;

    public Bot(){
        this.position = new Vector(200,5);
    }

    @Override
    public void tick(double ms) {
        this.position = position.add(new Vector(0,0));
    }

    @Override
    void doCollisionAction(Direction collisionForceDirection) {
        // User and bot are immovable (Do nothing)
    }

    public Vector getPosition() {
        return new Vector(position);
    }

    public double getWidth(){
        return this.WIDTH;
    }
    public double getHeight(){
        return this.HEIGHT;
    }
}
