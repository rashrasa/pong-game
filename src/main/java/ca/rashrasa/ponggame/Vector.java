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
        this.x=v0.x();
        this.y=v0.y();
    }


    public double x(){
        return this.x;
    }

    public double y(){
        return this.y;
    }

    public Vector add(Vector v1){
        return new Vector(
                v1.x() + this.x(),
                v1.y() + this.y()
        );
    }
    public Vector scalarProduct(double scalar){
        return new Vector(
                this.x() * scalar,
                this.y() * scalar
        );
    }

    public double magnitude(){
        return Math.sqrt(
                this.x()*this.x() + this.y() * this.y()
        );
    }
}
