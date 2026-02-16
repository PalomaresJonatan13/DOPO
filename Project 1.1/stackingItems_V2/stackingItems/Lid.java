/**
 * Representa una tapa en el simulador de apilamiento.
 * Cada tapa tiene un tamaño de bloque, color y puede ser visible o invisible.
 * 
 * @author (William Santiago Ruiz, Jonatan Palomares)
 * @version 2.0 (Ciclo 1)
 */
public class Lid {
    private static final int LID_HEIGHT = 1;
    
    private String color;
    private int blockSize;
    private int i;
    private boolean visible;
    private Rectangle rectangle;
    
    public Lid(String color, int blockSize, int index) {
        this.color = color;
        this.blockSize = blockSize;
        this.i = index;
        this.visible = false;
        this.rectangle = new Rectangle();
    }
    
    public void makeVisible() {
        visible = true;
        if (rectangle != null) {
            rectangle.makeVisible();
        }
    }
    
    public void makeInvisible() {
        visible = false;
        if (rectangle != null) {
            rectangle.makeInvisible();
        }
    }
    
    public String getColor() {
        return color;
    }
}