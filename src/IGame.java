import java.awt.Dimension;


public interface IGame {

	void start();
	void pause();
	Dimension getScreenSize();
	//получаем объект, который хранит манипул€ции с управлением
	Action getAction();
	//мен€ет текущую сцену
	void setScene(Scene scene);
	
}
