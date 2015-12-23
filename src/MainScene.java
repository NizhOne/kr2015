import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;


public class MainScene extends Scene{
	
	private Snake snake;
	private Food food;

	MainScene(IGame iGame) {
		super(iGame);
		snake=new Snake(Constants.FIELD_WIDTH/2, Constants.FIELD_HEIGHT/2, Direction.RIGHT);
		replaceFood();
	}

	private void replaceFood() {
		// TODO Auto-generated method stub
        int x=1+(int)(Math.random()*Constants.FIELD_WIDTH);
        int y=1+(int)(Math.random()*Constants.FIELD_HEIGHT);
        while (!isCellEmpty(x, y)) {
            if (x<Constants.FIELD_WIDTH) {
                x++;
            } else {
                if (y<Constants.FIELD_HEIGHT) {
                    x = 1;
                    y++;
                } else {
                    x = 1;
                    y = 1;
                }
            }
        }
        food=new Food(x, y);
	}
//проверяет пуста ли клетка
	private boolean isCellEmpty(int x, int y) {
        for (BodyPart bodyPart : snake.getBody()) {
            if (bodyPart.getX() == x && bodyPart.getY() == y) {
                return false;
            }
        }
        return true;
	}

	@Override
	public void update(long passedTime) {
		// TODO Auto-generated method stub
		// nothing to do
	}

	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
        g.setColor(Color.black);
        g.fillRect(0, 0, iGame.getScreenSize().width, iGame.getScreenSize().height);
        
        drawSnake(g);
        drawFood(g);
	}

	private void drawFood(Graphics2D g) {
		g.setColor(Color.gray);
		g.fillRect(food.getX()*Constants.SNAKE_HEIGHT-Constants.SNAKE_HEIGHT, iGame.getScreenSize().height - (food.getY()*Constants.SNAKE_HEIGHT), Constants.SNAKE_HEIGHT, Constants.SNAKE_HEIGHT);
	}

	private void drawSnake(Graphics2D g) {
		g.setColor(Color.GREEN);
		
        for (BodyPart bodyPart : snake.getBody()) {
            g.fillRect(
                    bodyPart.getX()*Constants.SNAKE_HEIGHT,
                    iGame.getScreenSize().height - (bodyPart.getY()*Constants.SNAKE_HEIGHT), Constants.SNAKE_HEIGHT, Constants.SNAKE_HEIGHT);
        }
	}

}
