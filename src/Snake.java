import java.util.ArrayList;
import java.util.List;


public class Snake {
	private List<BodyPart> body;
	private Direction direction;
	
	public Snake(int x, int y, Direction direction){
		this.direction=direction;
		
		body=new ArrayList<BodyPart>();
		body.add(new BodyPart(x,y));
		body.add(new BodyPart(x-direction.deltaX(), y-direction.deltaY()));
		body.add(new BodyPart(x-direction.deltaX()*2, y-direction.deltaY()*2));
	}
	
	public void move(){
		moveBody();
		moveHead();
	}

	private void moveHead() {
		// TODO Auto-generated method stub
        head().setX(head().getX() + direction.deltaX());
        head().setY(head().getY() + direction.deltaY());		
	}
	
	public BodyPart head(){
		return body.get(0);
	}

	private void moveBody() {
		// TODO Auto-generated method stub
		for(int i=body.size()-1; i>0; i--){
			BodyPart cur=body.get(i);
			BodyPart prev=body.get(i-1);
			cur.setX(prev.getX());
			cur.setY(prev.getY());
		}
	}
	
	public void setDirection (Direction d){
		this.direction=d;
	}
	
	public Direction getDirection(){
		return direction;
	}
	
	public List<BodyPart> getBody(){
		return body;
	}
}
