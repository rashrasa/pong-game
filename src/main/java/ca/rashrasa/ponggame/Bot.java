package ca.rashrasa.ponggame;

public class Bot extends GameElement {
    private Vector position;

    public Bot(){
        this.position = new Vector(200,5);
    }

    @Override
    public void tick(double ms) {
        this.position = position.add(new Vector(-1,0));
    }

    public Vector getPosition() {
        return new Vector(position);
    }
}
