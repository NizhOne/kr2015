import java.awt.Graphics2D;


public abstract class Scene {

	final IGame iGame;
	
	Scene(IGame iGame){
		this.iGame=iGame;
	}
	
	//����������, ����� ����� ������ �������� ���� ���������
	//�������� �������� ���-�� ��� ��������� � ���������� ����������
	public abstract void update(long passedTime);
	
	//�������� �����, ��� ���� ������������ ���� ���������
	//���������� ����� ����� update()
	public abstract void draw(Graphics2D g);
	
}
