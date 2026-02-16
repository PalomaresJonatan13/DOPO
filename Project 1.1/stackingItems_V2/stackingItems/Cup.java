/**
 * Representa una taza en el simulador de apilamiento
 * Cada taza tiene un color, tamaño y puede tener una tapa asociada, cuando una taza está tapada, ambas se mueven juntas
 * 
 * @author (William Santiago Ruiz, Jonatan Palomares)
 * @version 2.0 (Ciclo 1)
 */
public class Cup {
    private String color;
    private int blockSize;
    private int i;
    private boolean visible;
    private Rectangle rectangle;
    private Lid lid;
    
    public Cup(String color, int blockSize, int index) {
        this.color = color;
        this.blockSize = blockSize;
        this.i = index;
        this.visible = false;
        this.rectangle = new Rectangle();
        this.lid = null;
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
    
    public int getBlockSize() {
        return blockSize;
    }
    
    public int getIndex() {
        return i;
    }
    
    public boolean isVisible() {
        return visible;
    }
    
    public int getHeight() {
        return blockSize;
    }
    
    public int getWidth() {
        return blockSize * 10;
    }
    
    public void setLid(Lid lid) {
        this.lid = lid;
    }
    
    public Lid getLid() {
        return lid;
    }
    
    public boolean hasLid() {
        return lid != null;
    }
    
    public void moveTo(int x, int y) {
        if (rectangle != null) {
            rectangle.moveTo(x, y);
        }
    }
    
    public void setupRectangle(int x, int y, int width) {
        rectangle.changeSize(blockSize * 10, width);
        rectangle.changeColor(color);
        rectangle.moveTo(x, y);
        if (visible) {
            rectangle.makeVisible();
        }
    }
    
    public int getTotalHeight() {
        int height = blockSize;
        if (hasLid()) {
            height += 1;
        }
        return height;
    }
}