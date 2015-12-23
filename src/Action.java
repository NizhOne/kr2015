import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Collection;


public class Action implements KeyListener{

	private final Collection<KeyEvent> pressed;
	private final Collection<KeyEvent> released;
	
	
	public Action(){
		pressed=new ArrayList<KeyEvent>();
		released=new ArrayList<KeyEvent>();
	}
	
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	//ользователь нажал кнопку, добавляем в коллекцию
	@Override
	public synchronized void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		pressed.add(e);
	}

	//пользовтель отпустил кнопку, добавляем в коллекцию
	@Override
	public synchronized void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		released.add(e);
	}
	
	public synchronized Collection<KeyEvent> getPressed(){
		Collection<KeyEvent> events=new ArrayList<KeyEvent>(pressed);
		pressed.clear();
		return events;
	}
	
	public synchronized Collection<KeyEvent> getReleased(){
		Collection<KeyEvent> events=new ArrayList<KeyEvent>(released);
		released.clear();
		return events;
	}

}
