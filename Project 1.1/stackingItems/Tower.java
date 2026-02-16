
/**
 * Write a description of class Tower here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

public class Tower {
    private int width;
    private int height;
    private boolean visible;
    private boolean ok;
    private boolean active;
    private String[] orderOfItems;
    private TowerFrame frame;
    private HashMap<Integer, Cup> cups = new Hashmap<>();;
    private int MARGIN;
    private int BLOCKSIZE;

    /**
     * Constructor for objects of class Tower
     */
    public Tower(int width, int maxHeight) {
        this.width = width;
        this.height = maxHeight;

        this.frame = new TowerFrame(width, maxHeight, BLOCKSIZE, MARGIN);
    }

    // for now, assuming the tower has only cups, no lids
    public void pushCup(int i) {
        if (this.active && i > 0) {
            int lastI = 0;
            int lastHeight = 0;
            int yPosition = 0;
            int yPos = -1;
            for (int k = this.orderOfItems.length - 1; k >= 0; k--) {
                String item = this.orderOfItems[k];
                boolean isCup = item.charAt(0) == "C";
                int currentI = (int) item.charAt(1);

                // the cup already exists
                if (isCup && (currentI == i)) {
                    this.ok = false;
                    return;
                }

                // is this fine with everything else?
                if ((currentI > i) && (yPos < 0)) {
                    yPos = yPosition; yPosition = 1;
                }
                if (currentI > lastI) {
                    yPosition = Math.max(currentI, yPosition + 1);
                } else {
                    yPosition += currentI;
                }
            }
        }
    }
    
    public popCup() {
        // f
    }
    
    /**
     * maybe make pop until that cup, and save the item names. then push all cups and lids
     * above the cup removed, in order.
     */
    public removeCup() {
        // f
    }
    
    public pushLid () {
        // f
    }
    
    public popLid () {
        // f
    }
    
    public removeLid () {
        // f
    }
    
    public void () {
        // f
    }
    
    public void () {
        // f
    }
    
    public void () {
        // f
    }
    
    public void () {
        // f
    }
    
    public void () {
        // f
    }
    
}