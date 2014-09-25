import java.awt.Image;

import acm.graphics.GImage;

public class Bullet extends GImage{

	private String direction = "up";
	private static final int SPEED = 10;
	private static final int IMAGE_WIDTH = 10;
	private static final int IMAGE_HEIGHT = 10;
			
	public Bullet(double x, double y, String direction) {
		super("assets/pics/bullet.png", x - Bullet.IMAGE_WIDTH / 2, y - Bullet.IMAGE_HEIGHT / 2);
		this.direction = direction;
	}
	public void update(){
		if(this.direction.equals("up")){this.setLocation(this.getX(), this.getY() - this.SPEED);}
		if(this.direction.equals("down")){this.setLocation(this.getX(), this.getY() + this.SPEED);}
		if(this.direction.equals("left")){this.setLocation(this.getX() - this.SPEED, this.getY());}
		if(this.direction.equals("right")){this.setLocation(this.getX() + this.SPEED, this.getY());}
	}
	public double getCenterX(){
		return this.getX() + 5;
	}
	public double getCenterY(){
		return this.getY() + 5;
	}
	
}
