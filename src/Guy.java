import java.util.Iterator;
import java.util.List;

import acm.graphics.GImage;


public abstract class Guy {
	public double x = 0;
	public double y = 0;
	
	public int currentFrame = 0;
	public int currentAnimation = 0;
	
	GImage[][] animFrames;
	
	public static final int FRAME_TICK_DELAY = 20;
	public int frame_ticker = 0;
	
	public boolean alive = true;
	public int health = 3;
	
	public int respawn_timer = 0;
	public static final int RESPAWN_TIME = 60 * 3;

	public List<Bullet> bullets;
	public int bullet_cooldown_timer = 0;
	
	public void draw() {
		if(this.alive){
			this.setAllInvisible();
			animFrames[currentAnimation][currentFrame].setVisible(true);
			animFrames[currentAnimation][currentFrame].setLocation(this.x, this.y);
		}
	}

	public void setAllInvisible() {
		for(GImage[] anim : animFrames)
			for(GImage frame : anim)
				frame.setVisible(false);
	}

	public void addImages(Game main) {
		for(GImage[] anim : animFrames)
			for(GImage frame : anim)
				main.add(frame);
	}

	public void updateBullets(Game main) {
		Iterator<Bullet> bulletIter = this.bullets.iterator();
		while(bulletIter.hasNext()) {
			Bullet bullet = bulletIter.next();
			bullet.update();
			if(bullet.getX() > Game.APPLICATION_WIDTH || 
			   bullet.getY() < 0 || 
			   bullet.getX() < 0 || 
			   bullet.getY() > Game.APPLICATION_HEIGHT){
				main.remove(bullet);
				bulletIter.remove();
			}
		}
	}

	public void hit() {
		if( --this.health <= 0){
			this.alive = false;
			this.respawn_timer = Guy.RESPAWN_TIME;
			this.setAllInvisible();
			this.health = 3;
		}
	}

	public void clamp(int width, int height) {
		if(this.x < 0){this.x = 0;}
		if(this.y < 0){this.y = 0;}
		if(this.x + Player.standing.getWidth() > width){this.x = width - Player.standing.getWidth();}
		if(this.y + Player.standing.getHeight() > height){this.y = height - Player.standing.getHeight();}
		
	}

	public void tickFrames() {
		this.frame_ticker++;
		if(this.frame_ticker == Guy.FRAME_TICK_DELAY){
			this.currentFrame++;
			this.frame_ticker = 0;
		}
		if(this.currentFrame >= animFrames[this.currentAnimation].length)
			this.currentFrame = 0;
	}
}
