import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.concurrent.atomic.AtomicBoolean;


public class Game extends Canvas implements IGame, Runnable{
	
	private Thread gameT;
	private AtomicBoolean isRunning;
	
	private Action action;
	private Scene scene;
	
	public Game(Dimension screenSize){
		isRunning=new AtomicBoolean(false);
		setSize(screenSize);
		initAction();
		initFocusListener();
	}
	
	//��������� �� ������� ������, ������������ ������ ����
	private void initFocusListener() {
		// TODO Auto-generated method stub
		addFocusListener(new FocusListener(){
			public void focusGained(FocusEvent e){
				start();
			}
			
			public void focusLost(FocusEvent e){
				pause();
			}		
		});
	}
	
	//������������� ��������
	private void initAction() {
		// TODO Auto-generated method stub
		action=new Action();
		addKeyListener(action);				//??????
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		long prevIterTime=System.nanoTime();
		
		while(isRunning.get()){
			if(scene==null){
				continue;
			}
			
			long now=System.nanoTime();
			long passedTime=now-prevIterTime;
			prevIterTime=now;
			
			Graphics2D g=(Graphics2D)getBufferStrategy().getDrawGraphics();			//!!!!!!
			scene.update(passedTime);
			scene.draw(g);
			//���������� �������� �� ������
			getBufferStrategy().show();									//!!!!!!!!
			
		}
	}
	
	@Override
	public void start() {
		// TODO Auto-generated method stub
		if (isRunning.compareAndSet(false, true)){			//!!!!!
			gameT=new Thread(this);
			gameT.start();
		}
	}
	
	@Override
	public void pause() {
		// TODO Auto-generated method stub
		if(isRunning.compareAndSet(true, false)){			//!!!!!
			try {
				//����� join ������� ���������� ������, ��� �������� �� ������
				//���������� ����� �������, ���� ��������� ����� ������������� � ����
				gameT.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				throw new RuntimeException(e);
			}
		}
	}
	
	@Override
	public Dimension getScreenSize() {
		// TODO Auto-generated method stub
		return getSize();
	}
	
	@Override
	public Action getAction() {
		// TODO Auto-generated method stub
		return action;
	}
	
	@Override
	public void setScene(Scene scene) {
		// TODO Auto-generated method stub
		this.scene=scene;
	}

}
