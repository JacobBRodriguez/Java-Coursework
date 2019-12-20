import java.awt.*;

public class WhiteTiger extends Critter {
    private Color color;
    private short move;
    private boolean infect;

    public WhiteTiger() {
        this.setColor();
        this.setInfect(false);
    }

    public String toString() {
        if(getInfect())
            return "TGR";

        else
            return "tgr";
    }

    private boolean getInfect() {return this.infect;}

    private void setInfect(boolean infect) {
        this.infect = infect;
    }

    public Action getMove(CritterInfo info) {

        this.setMove();

        if(info.frontThreat()) {
            if(!getInfect())
                setInfect(true);
            return Action.INFECT;
        }

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
