import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.JOptionPane;

/**
 * Clase principal del simulador de apilar tazas y tapas
 * Gestiona Tower, las tazas y sus tapas, y proporciona funcionalidades
 * para apilar, ordenar y consultar información.
 * 
 * @author (William Santiago Ruiz, Jonatan Palomares)
 * version 3.0 (Ciclo 1) 
 *
 */

public class Tower {
    private int width; 
    private int height; 
    private boolean visible;
    private boolean ok;
    private boolean active; 
    
    private ArrayList<Cup> cups;
    private ArrayList<String> orderItems;
    private String MARGIN;

    private TowerFrame frame;
    private static final int DEFAULT_MARGIN = 50;
    private static final String[] COLORS = {"red", "blue", "yellow"
    }; 
    
    private int colors = 0;
    
    /**
     * A continuación se crea el constructor de la clase Tower 
     * Se encarga de crear una torre con width y height determinados 
     * @param width 
     * @param maxHeight 
     */
    
    public Tower(int width, int maxHeight) {
        if (width <= 0 || maxHeight <= 0) {
            ok = false;
            showMessage("Error:dimensions < 0 .");
            return;
        }
        this.width = width;
        this.height = maxHeight;
        this.visible = false;
        this.ok = true;
        this.active = true;
        this.MARGIN = String.valueOf(DEFAULT_MARGIN);
        
        this.cups = new ArrayList<Cup>();
        this.orderItems = new ArrayList<String>();
        
        this.frame = new TowerFrame(width, maxHeight, DEFAULT_MARGIN);
    }
    
    /**
     * Se debe hacer +1 taza en Tower 
     * @param blockSize 
     */
    
    public void pushCup (int blockSize) {
        if (!active) {
            showMessage ("Simulator Finished"); 
            return; 
        }
        if (blockSize > width) {
            showMessage("The cup cannot be wider than the tower");
            ok = false;
            return;
        }
        
        int currentHeight = calculateCurrentHeight();
        if (currentHeight + blockSize > height) {
            showMessage("Tower doesn´t have enough space");
            ok = false;
            return;
        }
        
        String color = getNextColor();
        int index = cups.size();
        Cup cup = new Cup(color, blockSize, index);
        
        cups.add(cup);
        orderItems.add("cup-" + index);
        
        positionCup(cup);
        
        if (visible) {
            cup.makeVisible();
        }
        
        ok = true;
    }
    
    private String getNextColor() {
        return "red";
        
    }
    
    private void positionCup(Cup cup) {
    
    }
    private void repositionAllItems() {
    
    }
    
    
    
        
/**
 * public void popCup 
 */




/**
 * Para eliminar una taza
 * @param index hace referencia al índice de la taza
 */

    public void removeCup(int index) {
        if (!active) {
            showMessage("Simulator Finished");
            return;
        }
        
        if (index < 0 || index >= cups.size()) {
            showMessage("invalid index.");
            ok = false;
            return;
        }
        
        Cup cup = cups.get(index);
        cup.makeInvisible();
        cups.remove(index);
        orderItems.remove("cup-" + index);
        
        
        repositionAllItems();
        ok = true;
    }
    
/**
 * Para poner tapas en una taza 
 * @param cupIndex 
 */

    public void pushLid(int cupIndex) {
        if (!active) {
            showMessage("Simulator Finished");
            return;
        }
        
        if (cupIndex < 0 || cupIndex >= cups.size()) {
            showMessage("invalid cup index");
            ok = false;
            return;
        }
        
        Cup cup = cups.get(cupIndex);
        
        if (cup.hasLid()) {
            showMessage("cup has already lid");
            ok = false;
            return;
        }
        
        
        int currentHeight = calculateCurrentHeight();
        if (currentHeight + 1 > height) {
            showMessage("Not enough space to lid");
            ok = false;
            return;
        }
        
    }

    
    
/**
 * Eliminar la tapa de una taza 
 * @param cupIndex 
 */

    public void removeLid(int cupIndex) {
        if (!active) {
            showMessage("Simulator finished");
            return;
        }
        
        if (cupIndex < 0 || cupIndex >= cups.size()) {
            showMessage("invalid index");
            ok = false;
            return;
        }
        
        Cup cup = cups.get(cupIndex);
        
        if (!cup.hasLid()) {
            showMessage("cup has no lid");
            ok = false;
            return;
        }
        
        Lid lid = cup.getLid();
        lid.makeInvisible();
        cup.setLid(null);
        
        orderItems.remove("lid-" + cupIndex);
        
        repositionAllItems();
        ok = true;
    }
    

    
/**
 * Invertir el orden de la torre
 */
    public void reverseTower() {
        if (!active) {
            showMessage("Simulator isn´t finished");
            return;
        }
        
        Collections.reverse(orderItems);
        repositionAllItems();
        ok = true;
    }
    
    private Cup findCupByIndex(int index) {
    for (Cup cup : cups) {
        if (cup.getIndex() == index) {
            return cup;
        }
    }
    return null;
}
    
    private int calculateCurrentHeight() {
    int totalHeight = 0;
    
    for (String item : orderItems) {
        String[] parts = item.split("-");
        String type = parts[0];
        int index = Integer.parseInt(parts[1]);
        
        if (type.equals("cup")) {
            Cup cup = findCupByIndex(index);
            if (cup != null) {
                totalHeight += cup.getBlockSize();
            }
        } else if (type.equals("lid")) {
            totalHeight += 1;
        }
    }
    
    return totalHeight;
}
    /**
 * Muestra un mensaje al usuario si el simulador es visible.
 * @param message el mensaje a mostrar
 */
private void showMessage(String message) {
    if (visible) {
        JOptionPane.showMessageDialog(null, message, 
            "StackingItems Simulator", JOptionPane.INFORMATION_MESSAGE);
    }
}
}

    



        
        

        
        


    

        
        
    
    
    
    
    
    





