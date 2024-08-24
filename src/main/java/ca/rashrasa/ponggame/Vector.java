package ca.rashrasa.ponggame;

public class Vector {
    private double x;
    private double y;

    /***
    Immutable 2-tuple of double values intended to represent a single point in
    2D space.
     */
    public Vector(double x, double y){
        this.x=x;
        this.y=y;
    }

    public Vector (Vector v0){
        this.x= v0.x();
        this.y=v0.y();
    }


    public double x(){
        return this.x;
    }

    public double y(){
        return this.y;
    }
}
