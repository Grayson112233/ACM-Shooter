import java.util.Iterator;
import java.util.List;

import acm.graphics.GImage;

public class Player {
	public double x = 0;
	public double y = 0;
	public List<Bullet> bullets;
	public int bullet_cooldown = 30;
	public int bullet_cooldown_timer = 0;
	public static final int PISTOL_COOLDOWN = 30;
	public static final int MACHINEGUN_COOLDOWN = 5;
	/* 
	 * animation # | description    | # of frames
	 *           1 | Walking Down   | 2 
	 *           2 | Walking Up     | 2
	 *           3 | Walking Left   | 2
	 *           4 | Walking Right  | 2
	 *           5 | Standing Still | 1
	 * */
	public static final GImage down1 = new GImage("assets/pics/down1.png");
	public static final GImage down2 = new GImage("assets/pics/down2.png");
	public static final GImage up1 = new GImage("assets/pics/up1.png");
	public static final GImage up2 = new GImage("assets/pics/up2.png");
	public static final GImage right1 = new GImage("assets/pics/right1.png");
	public static final GImage right2 = new GImage("assets/pics/right2.png");
	public static final GImage left1 = new GImage("assets/pics/left1.png");
	public static final GImage left2 = new GImage("assets/pics/left2.png");
	public static final GImage standing = new GImage("assets/pics/standing.png");
	private static final GImage[][] animFrames = new GImage[][]
		{
			{ Player.down1, Player.down2 },
			{ Player.up1, Player.up2 },
			{ Player.left1, Player.left2 },
			{ Player.right1, Player.right2 },
			{ Player.standing }
		};
	
	public int currentFrame = 0;
	public int currentAnimation = 0;
	public int[] animaitons_num_frames = {2, 2, 2, 2, 1};
	public static final int FRAME_TICK_DELAY = 20;
	public int frame_ticker = 1;
	
	public boolean alive = true;
	public int respawn_timer = 0;
	public static final int RESPAWN_TIME = 60 * 3;
	public int health = 3;
	public int current_gun = 1;
	public static final int NUM_OF_GUNS = 2;
	
	public void draw(){
		if(this.alive){
			this.setAllInvisible();
			animFrames[this.currentAnimation][currentFrame].setVisible(true);
			animFrames[this.currentAnimation][currentFrame].setLocation(this.x, this.y);
		}
	}
	
	public void clamp(int width, int height){
		if(this.x < 0){this.x = 0;}
		if(this.y < 0){this.y = 0;}
		if(this.x + Player.standing.getWidth() > width){this.x = width - Player.standing.getWidth();}
		if(this.y + Player.standing.getHeight() > height){this.y = height - Player.standing.getHeight();}
		
	}
	
	public void tickFrames(){
		this.frame_ticker++;
		if(this.frame_ticker == Player.FRAME_TICK_DELAY){
			this.currentFrame++;
			this.frame_ticker = 0;
		}
		if(this.currentFrame >= animFrames[this.currentAnimation].length)
			this.currentFrame = 0;
	}
	public void setAllInvisible(){
		for(GImage[] anim : animFrames)
			for(GImage frame : anim)
				frame.setVisible(false);
	}
	public void addImages(Game main){
		for(GImage[] anim : animFrames)
			for(GImage frame : anim)
				main.add(frame);
	}
	public void keyActions(Game main, Inputs inputs){
		if(this.respawn_timer > 0)
			this.respawn_timer--;
		if(this.alive == false && this.respawn_timer == 0)
			this.alive = true;
		if(this.alive){
			if(this.bullet_cooldown_timer > 0)
				this.bullet_cooldown_timer--;
			if(inputs.keyW){this.y -= 3;this.currentAnimation = 1;}
			if(inputs.keyS){this.y += 3;this.currentAnimation = 0;}
			if(inputs.keyA){this.x -= 3;this.currentAnimation = 2;}
			if(inputs.keyD){this.x += 3;this.currentAnimation = 3;}
			if(inputs.keyRreleased){
				this.current_gun ++;
				if(this.current_gun > Player.NUM_OF_GUNS){this.current_gun = 1;}
				if(this.current_gun == 1){this.bullet_cooldown = Player.PISTOL_COOLDOWN;}
				if(this.current_gun == 2){this.bullet_cooldown = Player.MACHINEGUN_COOLDOWN;}
			}
			if(inputs.keyW == false && inputs.keyA == false && inputs.keyS == false && inputs.keyD == false)
				this.currentAnimation = 4;
			
			if(this.bullet_cooldown_timer == 0 && (inputs.keyRight || inputs.keyLeft || inputs.keyUp || inputs.keyDown)) {
				Direction bulletDir = 
					inputs.keyRight ? Direction.RIGHT : 
					inputs.keyLeft ? Direction.LEFT :
					inputs.keyUp ? Direction.UP : 
					inputs.keyDown ? Direction.DOWN : null;
				
				this.bullets.add(new Bullet(this.x + Player.down1.getWidth() / 2, this.y - 15 + Player.down1.getHeight() / 2, bulletDir));
				main.add(this.bullets.get(this.bullets.size()-1));
				this.bullet_cooldown_timer = this.bullet_cooldown;
			}
		}
	}
	public void updateBullets(Game main){
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
	public double getCenterX(){
		return this.x + Player.standing.getWidth() / 2;
	}
	public double getCenterY(){
		return this.y + Player.standing.getHeight() / 2;
	}
	public void hit(){
		this.health--;
		if(this.health == 0){
			this.alive = false;
			this.respawn_timer = Player.RESPAWN_TIME;
			this.setAllInvisible();
			this.health = 3;
		}
	}
}
