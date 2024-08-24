package ca.rashrasa.ponggame;

public class Puck {
    private volatile Vector position;
    private volatile Vector velocity;

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

    }
}
