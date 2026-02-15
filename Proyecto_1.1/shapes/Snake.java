
/**
 * Write a description of class Snake here.
 * 
 * @author Jonatan Palomares y William Ruiz
 * @version 1.0
 */
import java.util.List;
import java.util.ArrayList;

public class Snake {
    private List<Rectangle> body = new ArrayList<>();
    private Rectangle head;
    private Rectangle tail;
    private boolean validLastMovement = true;
    private boolean isVisible = true;

    /**
     * Constructor for objects of class Snake
     */
    public Snake(int row, int column) {        
        Rectangle headSegment = new Rectangle();
        HungrySnakeGame.normalizeRectangle(headSegment, "blue");
        headSegment.moveHorizontal(column);
        headSegment.moveVertical(row);
        
        this.head = headSegment;
        this.tail = headSegment;
        this.body.add(headSegment);
    }

    public List<Rectangle> getBody() {
        return this.body;
    }
    
    public int[] head() {
        int xPos = this.head.getXPosition();
        int yPos = this.head.getYPosition();
        return new int[]{yPos, xPos};
    }
    
    public int[] tail() {
        int xPos = this.tail.getXPosition();
        int yPos = this.tail.getYPosition();
        return new int[]{yPos, xPos};
    }
    
    public int length() {
        return this.body.size();
    }
    
    public void makeVisible() {
        this.isVisible = true;
        for (Rectangle seg: this.body) {
            seg.makeVisible();
        }
    }
    
    public void makeInvisble() {
        this.isVisible = false;
        for (Rectangle seg: this.body) {
            seg.makeInvisible();
        }
    }

    public boolean isOk() {
        return this.validLastMovement;
    }

    public void move(char direction) {
        int[] nextPos = this.nextMove(direction);
        if (this.validLastMovement) {
            int nextX = nextPos[1];
            int nextY = nextPos[0];
            for (Rectangle seg: body) {
                int currentX = seg.getXPosition();
                int currentY = seg.getYPosition();

                seg.moveHorizontal(nextX - currentX);
                seg.moveVertical(nextY - currentY);

                nextX = currentX;
                nextY = currentY;
            }
        }
    }

    private int[] nextMove(char direction) {
        int yPosSecondSeg = body.get(Math.min(1, this.length() - 1)).getYPosition();
        int xPosSecondSeg = body.get(Math.min(1, this.length() - 1)).getXPosition();
        int[] h = this.head();
        switch (direction) {
            case 'n':
                this.validLastMovement = !(h[0] < 20 || h[0] > yPosSecondSeg);
                return new int[]{h[0] - 20, h[1]};
            case 's':
                this.validLastMovement = !(h[0] + 20 > HungrySnakeGame.maxY || h[0] < yPosSecondSeg);
                return new int[]{h[0] + 20, h[1]};
            case 'w':
                this.validLastMovement = !(h[1] < 20 || h[1] > xPosSecondSeg);
                return new int[]{h[0], h[1] - 20};
            case 'e':
                this.validLastMovement = !(h[1] + 20 > HungrySnakeGame.maxX || h[1] < xPosSecondSeg);
                return new int[]{h[0], h[1] + 20};
            default:
                throw new IllegalArgumentException("direction must be n, s, e or w");
        }
    }

    public void grow(char direction) {
        int[] oldTailPos = this.tail();
        this.move(direction);
        if (this.validLastMovement) {
            Rectangle newTail = new Rectangle();
            HungrySnakeGame.normalizeRectangle(newTail, "blue");
            newTail.moveHorizontal(oldTailPos[1]);
            newTail.moveVertical(oldTailPos[0]);

            if (this.isVisible) newTail.makeVisible();
            body.add(newTail);
            this.tail = newTail;
        }
    }
}