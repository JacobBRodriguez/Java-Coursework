import java.awt.*;

public class Bear extends Critter {
    private boolean polar;
    private Color color;
    private boolean alternate;

    public Bear(boolean polar) {
        this.setColor(polar);
        this.alternate = false;
    }

    public void setColor(boolean polar) {
        if(polar) {
            this.color = Color.WHITE;
        }
        else
            this.color = Color.BLACK;
    }

    public Color getColor() {
        return this.color;
    }

    private void alternate() {
        this.alternate = !this.alternate;
    }

    public String toString() {
        this.alternate();

        if(alternate) {
            return "(/)";
        }
        else
            return "\\";
    }

    public Action getMove(CritterInfo info) {
        if(info.frontThreat()) { return Action.INFECT; }

        else if(info.getFront() == Neighbor.EMPTY) { return Action.HOP; }

        else
            return Action.LEFT;
    }

}
