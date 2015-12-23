import java.awt.Font;
import java.awt.Graphics2D;


public class MainScene extends Scene{

	MainScene(IGame iGame) {
		super(iGame);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(long passedTime) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
        g.setFont(new Font("Default", Font.BOLD, 30));
        g.drawString("It Works!", 64, 64);
	}

}
