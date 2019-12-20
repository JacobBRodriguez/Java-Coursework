import java.awt.*;
import java.lang.Math;

public class Tiger extends Critter {

    private Color color;
    private short move;

    public Tiger() {
        this.setColor();

    }

    public String toString() {
        return "TGR";
    }

    public Action getMove(CritterInfo info) {

        this.setMove();

        if(info.frontThreat()) { return Action.INFECT; }

        else if(info.getFront() == Neighbor.WALL || info.getRight() == Neighbor.WALL) { return Action.LEFT; }

        else if(info.getFront() == Neighbor.SAME)
            return Action.RIGHT;
        else
            return Action.HOP;
    }

    public Color getColor() {return this.color;}

    private void setMove() {
        if(this.move == 3) {
            this.setColor();
            this.move = 1;
        }
        else
            this.move++;
    }

    private void setColor() {
        int min = 1, max = 3, range = max - min + 1;
        int rand = (int)(Math.random() * range) + min;

        switch(rand) {
            case 1:
                this.color = Color.RED;
                break;
            case 2:
                this.color = Color.GREEN;
                break;
            default:
                this.color = Color.BLUE;
        }
    }
}
