import java.awt.Graphics2D;


public abstract class Scene {

	final IGame iGame;
	
	Scene(IGame iGame){
		this.iGame=iGame;
	}
	
	//вызывается, когда сцена должна обновить свое состояние
	//параметр содержит кол-во сек прошедших с последнего обновления
	public abstract void update(long passedTime);
	
	//сообщает сцене, что пора перерисовать свое состояние
	//вызывается сразу после update()
	public abstract void draw(Graphics2D g);
	
}
