import java.awt.Dimension;


public class Launcher {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int screenWidth=Constants.FIELD_HEIGHT*Constants.SNAKE_HEIGHT;
		int screenHeight=Constants.FIELD_WIDTH*Constants.SNAKE_HEIGHT;
		Dimension screenSize=new Dimension(screenWidth, screenHeight);
		
		IGame iGame=Frame.build(screenSize);
		iGame.setScene(new MainScene(iGame));
		iGame.start();
	}

}
