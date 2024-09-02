package ca.rashrasa.ponggame;

public class Direction {
    private final Vector direction;

    public Direction(Vector v0){
        double magnitude = v0.magnitude();
        if(magnitude == 0){
            this.direction = new Vector(0,0);
        }
        else{
            this.direction = v0.scalarProduct(1.0/magnitude);
        }
    }
    public Direction (double theta){
        this.direction = new Vector(Math.cos(theta), Math.sin(theta));
    }

    public Vector getVector(){
        return new Vector(this.direction);
    }
}
