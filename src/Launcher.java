import java.awt.Dimension;


public class Launcher {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		IGame iGame=Frame.build(new Dimension(640,480));
		iGame.setScene(new MainScene(iGame));
		iGame.start();
	}

}
