package ca.rashrasa.ponggame;

public abstract class GameElement {
    abstract void tick(double ms);

    abstract void doCollisionAction(Direction collisionForceDirection);

    abstract Vector getStartPosition();

    abstract void setPosition(Vector v0);

    abstract void reset();
}
