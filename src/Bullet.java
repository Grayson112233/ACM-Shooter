import acm.graphics.GImage;

public class Bullet extends GImage{

	private Direction direction = Direction.UP;
	private static final int SPEED = 10;
	private static final int IMAGE_WIDTH = 10;
	private static final int IMAGE_HEIGHT = 10;
			
	public Bullet(double x, double y, Direction direction) {
		super("assets/pics/bullet.png", x - Bullet.IMAGE_WIDTH / 2, y - Bullet.IMAGE_HEIGHT / 2);
		this.direction = direction;
	}
	public void update(){
		if(this.direction == Direction.UP) {this.setLocation(this.getX(), this.getY() - Bullet.SPEED);}
		if(this.direction == Direction.DOWN) {this.setLocation(this.getX(), this.getY() + Bullet.SPEED);}
		if(this.direction == Direction.LEFT) {this.setLocation(this.getX() - Bullet.SPEED, this.getY());}
		if(this.direction == Direction.RIGHT) {this.setLocation(this.getX() + Bullet.SPEED, this.getY());}
	}
	public double getCenterX(){
		return this.getX() + 5;
	}
	public double getCenterY(){
		return this.getY() + 5;
	}
	
}
