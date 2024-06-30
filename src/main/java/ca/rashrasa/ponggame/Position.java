package ca.rashrasa.ponggame;

public class Position {
    private double x;
    private double y;

    /*
    Immutable 2-tuple of double values intended to represent a single point in
    2D space.
     */
    public Position(double x, double y){
        this.x=x;
        this.y=y;
    }

    public double x(){
        return this.x;
    }

    public double y(){
        return this.y;
    }
}
