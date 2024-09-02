package ca.rashrasa.ponggame;

public class Puck extends GameElement{
    private Vector position;
    private Vector velocity;
    private double RADIUS = 20.0;

    public Puck(Vector start_pos, Vector start_vel){
        this.position = start_pos;
        this.velocity = start_vel;
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

    public double getRadius() {
        return this.RADIUS;
    }
}
