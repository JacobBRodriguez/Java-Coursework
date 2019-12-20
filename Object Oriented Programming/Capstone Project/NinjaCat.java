import java.awt.*;

public class NinjaCat extends Critter {

    private short step;

     public NinjaCat() {
         this.step = 0;
     }

     public Color getColor() {
         switch(this.step) {
             case 0:
                 this.incrementStep();
                 return Color.CYAN;
             case 1:
                 this.incrementStep();
                 return Color.RED;
             default:
                 incrementStep();
                 return Color.BLACK;
         } //end switch

     }

     public String toString() {
         return "CHONK";
     }

     public Action getMove(CritterInfo info) {

         if(info.frontThreat() == true) {return Action.INFECT;}

         else {
             int min = 1, max = 3, range = max - min + 1;
             int rand = (int)(Math.random() * range) + min;

             switch(rand) {
                 case 1:
                     return Action.HOP;
                 case 2:
                     return Action.RIGHT;
                 default:
                     return Action.LEFT;
             }
         }

     }

     private void incrementStep() {
         this.step++;
         if(this.step == 3) {this.step = 0;}
     }
}
