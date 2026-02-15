
/**
 * Write a description of class Tower here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.List;
import java.util.ArrayList;

public class Tower {
    private int width;
    private int height;
    private List<String> items; // Example: {"c1", "l2", "l1", "c3"}
    private boolean lastOperationOk;
    private Rectangle tower;
    private static int blockSize = 10;

    /**
     * Constructor for objects of class Tower
     */
    public Tower(int width, int maxHeight) {
        this.width = width;
        this.height = maxHeight;
        this.items = new ArrayList<>();

        this.tower = new Rectangle();
        this.tower.changeSize(this.height, this.width);
        this.tower.moveTo(blockSize, blockSize);
        this.tower.changeColor(""); //  ?????
    }
    
    public int height() {
        return this.height;
    }

    /**
     * take only the lids and cups smaller than cup i
     */
    public void pushCup(int i) {
        if (items.contains("c" + i)) {
            this.lastOperationOk = false; // correct?
            // message with JOptionPane
        } else {
            String color = ""; // generate at random from the valid colors
            Cup cup = new Cup(i, color);
            cup.centerX(this.tower.getXPosition(), this.width);

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