import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.WindowConstants;


public class Frame{
	public static IGame build(Dimension screenSize){
		final Game game=new Game(screenSize);
		
		JFrame frame=new JFrame("Snake the game");
		frame.setFocusable(false);
		frame.setResizable(false);
		frame.setLayout(new BorderLayout());
		frame.add(game);
		frame.pack();				  //!!!!!
		frame.setVisible(true);
		game.createBufferStrategy(2); //!!!!!
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //?
		frame.setLocationRelativeTo(null);
		
		frame.addWindowListener(new WindowAdapter(){		//!!!!
			@Override
			public void windowClosing(WindowEvent e){
				game.pause();
			}
		});
		
		frame.requestFocus();
		game.requestFocus();
		return game;	
	}
}
