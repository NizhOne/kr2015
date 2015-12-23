import java.awt.Dimension;


public interface IGame {

	void start();
	void pause();
	Dimension getScreenSize();
	//�������� ������, ������� ������ ����������� � �����������
	Action getAction();
	//������ ������� �����
	void setScene(Scene scene);
	
}
