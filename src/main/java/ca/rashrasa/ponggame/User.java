package ca.rashrasa.ponggame;

public class User extends GameElement{
    private double x;
    private final double Y = 480;
    private final double MOVE_SPEED = 200; //pixels per second
    private boolean isLeftPressed, isRightPressed;

    public User(){
        this.x = 200;
    }

    public void tick(double ms){
        double seconds = ms/1000.0;
        if(isLeftPressed){
            this.x = x - MOVE_SPEED*seconds;
        }
        if(isRightPressed){
            this.x = x + MOVE_SPEED*seconds;
        }
    }

    public void leftPressed(){
        this.isLeftPressed = true;
    }
    public void rightPressed(){
        this.isRightPressed = true;
    }
    public void leftReleased(){
        this.isLeftPressed = false;
    }
    public void rightReleased() {
        this.isRightPressed = false;
    }
    public double getX(){
        return x;
    }
    public double getY(){
        return Y;
    }
}
