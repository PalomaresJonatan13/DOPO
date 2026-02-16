/**
 * Representa el marco visual de la torre en el simulador.
 * Tiene rectángulo principal y las marcas de altura.
 * 
 * @author (William Santiago Ruiz, Jonatan Palomares)
 * @version 2.0 (Ciclo 1)
 */
public class TowerFrame {
    private int margin;
    private boolean visible;
    private Rectangle frame;
    private int width;
    private int height;
    
    public TowerFrame(int width, int height, int margin) {
        this.width = width;
        this.height = height;
        this.margin = margin;
        this.visible = false;
        this.frame = new Rectangle();
        setupFrame();
    }
    
    private void setupFrame() {
        int pixelHeight = height * 10;
        int pixelWidth = width * 10;
        
        frame.changeSize(pixelHeight, pixelWidth);
        frame.changeColor("black");
        frame.moveTo(margin, margin);
    }
    
    public void makeVisible() {
        visible = true;
        frame.makeVisible();
        drawHeightMarks();
    }
    
    public void makeInvisible() {
        visible = false;
        frame.makeInvisible();
    }
    
    private void drawHeightMarks() {
        for (int i = 1; i < height; i++) {
            Rectangle mark = new Rectangle();
            mark.changeSize(1, 5);
            mark.changeColor("gray");
            mark.moveTo(margin - 10, margin + (i * 10));
            if (visible) {
                mark.makeVisible();
            }
        }
    }
    
    public int getWidth() {
        return width;
    }
    
    public int getHeight() {
        return height;
    }
    
    public int getMargin() {
        return margin;
    }
    
    public boolean isVisible() {
        return visible;
    }
    
    public int getXPosition() {
        return margin;
    }
    
    public int getYPosition() {
        return margin;
    }
}