
/**
 * Write a description of class HungrySnakeGame here.
 * 
 * @author Jonatan Palomares y William Ruiz
 * @version 1.0
 */
import java.util.List;
import java.util.ArrayList;
import java.util.random.RandomGenerator;


public class HungrySnakeGame {
    private Snake snake;
    public static final int maxX = 200;
    public static final int maxY = 200;
    public static final int maxObstacles = 6;
    private List<Rectangle> obstacles = new ArrayList<>();
    private Circle fruit;

    public HungrySnakeGame() {
        this.placeSnake();
        this.createObstacles();
        this.placeObstacles();
        this.placeFruit();
    }

    public void restart() {
        this.placeSnake();
        this.placeObstacles();
        this.placeFruit();
    }
    
    public String checkState() {
        return "La longitud actual de la serpiente es: " + this.snake.length();
    }

    public void move(char direction) {
        if (this.eatsFruit(direction)) {
            this.snake.grow(direction);
            this.placeFruit();
        } else {
            this.snake.move(direction);
            int xPos = this.snake.head()[1];
            int yPos = this.snake.head()[0];

            if (this.obstacleInPos(xPos, yPos) || !this.snake.isOk()) {
                System.out.println("Has chocado, pero puedes volver a intentarlo!");
                this.restart();
            }
        }
    }

    private boolean eatsFruit(char direction) {
        int snakeX = this.snake.head()[1];
        int snakeY = this.snake.head()[0];
        int fruitX = this.fruit.getXPosition();
        int fruitY = this.fruit.getYPosition();
        switch (direction) {
            case 'n': return (snakeX == fruitX) && (snakeY - 20 == fruitY);
            case 's': return (snakeX == fruitX) && (snakeY + 20 == fruitY);
            case 'w': return (snakeX - 20 == fruitX) && (snakeY == fruitY);
            case 'e': return (snakeX + 20 == fruitX) && (snakeY == fruitY);
            default:
                throw new IllegalArgumentException("direction must be n, s, e or w");
        }
    }

    private void createObstacles() {
        for (int i=0; i < HungrySnakeGame.maxObstacles; i++) {
            Rectangle obstacle = new Rectangle();
            HungrySnakeGame.normalizeRectangle(obstacle, "black");
            this.obstacles.add(obstacle);
        }
    }

    private void placeObstacles() {
        RandomGenerator rng = RandomGenerator.getDefault();
        for (Rectangle obs: obstacles) {
            obs.moveToOrigin();
            obs.makeInvisible();

            boolean objectPlaced = false;
            while (!objectPlaced) {
                int xPos = rng.nextInt(10)*20;
                int yPos = rng.nextInt(10)*20;

                // two consecutive games wont have obstacles in the same positions
                int[] snakeHead = this.snake.head();
                boolean repeatedPos = obstacleInPos(xPos, yPos);
                repeatedPos = repeatedPos || ((snakeHead[1] == xPos) && (snakeHead[0] == yPos));
                if (!repeatedPos) {
                    obs.moveHorizontal(xPos);
                    obs.moveVertical(yPos);
                    if (rng.nextBoolean()) obs.makeVisible();
                    objectPlaced = true;
                }
            }
        }
    }

    private void placeFruit() {
        RandomGenerator rng = RandomGenerator.getDefault();
        boolean fruitPlaced = false;

        while (!fruitPlaced) {
            int xPos = rng.nextInt(10)*20;
            int yPos = rng.nextInt(10)*20;
            boolean repeatedPos = obstacleInPos(xPos, yPos);
            for (Rectangle seg: this.snake.getBody()) {
                repeatedPos = repeatedPos || ((seg.getXPosition() == xPos) && (seg.getYPosition() == yPos));
            }
            if (!repeatedPos) {
                if (this.fruit == null) {
                    Circle fruit = new Circle();
                    HungrySnakeGame.normalizeFruit(fruit);
                    fruit.makeVisible();
                    this.fruit = fruit;
                }
                this.fruit.moveToOrigin();
                this.fruit.moveHorizontal(xPos);
                this.fruit.moveVertical(yPos);
                fruitPlaced = true;
            }
        }
    }

     private void placeSnake() {
        RandomGenerator rng = RandomGenerator.getDefault();
        int xPos = rng.nextInt(10)*20;
        int yPos = rng.nextInt(10)*20;
        if (this.snake != null) this.snake.makeInvisble();
        this.snake = new Snake(xPos, yPos);
        this.snake.makeVisible();
    }

    private boolean obstacleInPos(int xPos, int yPos) {
        boolean repeatedPos = false;
        for (Rectangle obs: this.obstacles) {
            repeatedPos = repeatedPos || (obs.getXPosition() == xPos && obs.getYPosition() == yPos);
        }
        return repeatedPos;
    }

    public static void normalizeRectangle(Rectangle seg, String color) {
        seg.moveToOrigin();
        seg.changeSize(20, 20);
        seg.changeColor(color);
    }

    public static void normalizeFruit(Circle seg) {
        seg.moveToOrigin();
        seg.changeSize(20);
        seg.changeColor("red");
    }
}