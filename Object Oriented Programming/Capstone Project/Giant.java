import java.awt.*;

public class Giant extends Critter {
    private short move;

    public Giant() {
        this.move = 0;
    }

    public Color getColor() {return Color.GRAY;}

    @Override
    public String toString() {

        short currentMove = this.getMoveCount();

        if(currentMove >= 0 && currentMove < 6) { return "fee"; }

        else if(currentMove >= 6 && currentMove < 12) {return "fie";}

        else if(currentMove >= 12 && currentMove < 18) {return "foe";}

        else
            return "fum";
    }

    public Action getMove(CritterInfo info) {

        if(info.frontThreat()) {
            this.setMoveCount();
            return Action.INFECT;}

        else if(info.getFront() == Neighbor.EMPTY) {
            this.setMoveCount();
            return Action.HOP;}

        else {
           this.setMoveCount();
           return Action.RIGHT; }

    }

    private void setMoveCount() {
        this.move++;
        if(this.move == 24) {this.move = 0;}
    }

    private short getMoveCount() {
        return this.move;
    }
}
