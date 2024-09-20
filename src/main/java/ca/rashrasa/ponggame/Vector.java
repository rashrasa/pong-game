package ca.rashrasa.ponggame;

public class Vector {
    private double x;
    private double y;

    /**
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

    public Vector changeDirection(Direction newDirection){
        Vector direction = newDirection.getVector();
        double magnitude = this.magnitude();
        return new Vector(
                magnitude * direction.x(),
                magnitude * direction.y()
        );
    }

    public Vector translateEnd(double x_dist, double y_dist){
        return new Vector(this.x + x_dist, this.y + y_dist);
    }

    public double magnitude(){
        return Math.sqrt(
                this.x()*this.x() + this.y() * this.y()
        );
    }

    public double distanceTo(Vector v0){
        return Math.sqrt( Math.pow(this.x()-v0.x(),2) + Math.pow(this.y()-v0.y(),2) );
    }
}
