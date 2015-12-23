import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.List;


public class MainScene extends Scene{
	
	private Snake snake;
	private Food food;
	private Delay snakeMoveDelay;
	long tmp=100;

	MainScene(IGame iGame) {
		super(iGame);
		snake=new Snake(Constants.FIELD_WIDTH/2, Constants.FIELD_HEIGHT/2, Direction.RIGHT);
		replaceFood();
		snakeMoveDelay=new Delay(tmp);
		
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
        if (isGameOver()) {
            iGame.setScene(new GameOverScene(iGame));
            return;
        }
		getPressings();
        if (snakeMoveDelay.updateAndCheck(passedTime)) {      	
            snake.move();
            BodyPart head=snake.head();
            //когда змейка достигает края она "выходит" с другой стороны
            if (head.getX()<1) {
                head.setX(Constants.FIELD_WIDTH);
            }
            if (head.getX()>Constants.FIELD_WIDTH) {
                head.setX(1);
            }
            if (head.getY()<1) {
                head.setY(Constants.FIELD_HEIGHT);
            }
            if (head.getY()>Constants.FIELD_HEIGHT) {
                head.setY(1);
            }
            //проверяет где голова ставит новую еду
            if (head.getX()==food.getX() && head.getY()==food.getY()) {
                List<BodyPart> body = snake.getBody();
                BodyPart lastPart = body.get(body.size() - 1);
                body.add(new BodyPart(lastPart.getX(), lastPart.getY()));
                
                if (isGameOver()) {
                    iGame.setScene(new GameOverScene(iGame));
                } else {
                    replaceFood();
                }                
            }            
        }		
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
	
	//реакция на действия
	private void getPressings(){
		for (KeyEvent event:iGame.getAction().getPressed()){
			switch(event.getKeyCode()){
			case KeyEvent.VK_UP: snake.setDirection(Direction.UP);break;
			case KeyEvent.VK_DOWN: snake.setDirection(Direction.DOWN);break;
			case KeyEvent.VK_LEFT: snake.setDirection(Direction.LEFT);break;
			case KeyEvent.VK_RIGHT: snake.setDirection(Direction.RIGHT);break;
			}
		}
	}
	
    private boolean isGameOver() {
        if (snake.getBody().size()==Constants.FIELD_WIDTH*Constants.FIELD_HEIGHT) {
            return true;
        }
        
        //либо змейка заняла все пространство либо съела себя
        for (BodyPart bodyPart : snake.getBody()) {
            if (bodyPart != snake.head() && snake.head().getX() == bodyPart.getX() && snake.head().getY() == bodyPart.getY()) {
                return true;
            }
        }

        return false;
    }

}
