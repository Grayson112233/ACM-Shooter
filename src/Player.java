import java.util.ArrayList;

import acm.graphics.GImage;



public class Player {
	public double x = 0;
	public double y = 0;
	public ArrayList <Bullet> bullets;
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
	public int current_frame = 1;
	public int current_animation = 1;
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
			if(this.current_animation == 1){
				if(this.current_frame == 1){Player.down1.setVisible(true);Player.down1.setLocation(this.x, this.y);}
				if(this.current_frame == 2){Player.down2.setVisible(true);Player.down2.setLocation(this.x, this.y);}
			}
			if(this.current_animation == 2){
				if(this.current_frame == 1){Player.up1.setVisible(true);Player.up1.setLocation(this.x, this.y);}
				if(this.current_frame == 2){Player.up2.setVisible(true);Player.up2.setLocation(this.x, this.y);}
			}
			if(this.current_animation == 3){
				if(this.current_frame == 1){Player.left1.setVisible(true);Player.left1.setLocation(this.x, this.y);}
				if(this.current_frame == 2){Player.left2.setVisible(true);Player.left2.setLocation(this.x, this.y);}
			}
			if(this.current_animation == 4){
				if(this.current_frame == 1){Player.right1.setVisible(true);Player.right1.setLocation(this.x, this.y);}
				if(this.current_frame == 2){Player.right2.setVisible(true);Player.right2.setLocation(this.x, this.y);}
			}
			if(this.current_animation == 5){
				Player.standing.setVisible(true);
				Player.standing.setLocation(this.x, this.y);
			}
		}
	}
	
	public void clamp(int width, int height){
		if(this.x < 0){this.x = 0;}
		if(this.y < 0){this.y = 0;}
		if(this.x + Player.standing.getWidth() > width){this.x = width - Player.standing.getWidth();}
		if(this.y + Player.standing.getHeight() > height){this.y = height - Player.standing.getHeight();}
		
	}
	
	public void tickFrames(){
		this.frame_ticker ++;
		if(this.frame_ticker == Player.FRAME_TICK_DELAY){this.current_frame ++;this.frame_ticker = 0;}
		if(this.current_frame > this.animaitons_num_frames[this.current_animation - 1]){this.current_frame = 1;}
	}
	public void setAllInvisible(){
		Player.up1.setVisible(false);
		Player.up2.setVisible(false);
		Player.right1.setVisible(false);
		Player.right2.setVisible(false);
		Player.left1.setVisible(false);
		Player.left2.setVisible(false);
		Player.down1.setVisible(false);
		Player.down2.setVisible(false);
		Player.standing.setVisible(false);
	}
	public void addImages(Game main){
		main.add(Player.down1);
		main.add(Player.down2);
		main.add(Player.up1);
		main.add(Player.up2);
		main.add(Player.left1);
		main.add(Player.left2);
		main.add(Player.right1);
		main.add(Player.right2);
		main.add(Player.standing);
	}
	public void keyActions(Game main, Inputs inputs){
		if(this.respawn_timer > 0){this.respawn_timer--;}
		if(this.alive == false && this.respawn_timer == 0){this.alive = true;}
		if(this.alive){
			if(this.bullet_cooldown_timer > 0){this.bullet_cooldown_timer--;}
			if(inputs.key_w){this.y -= 3;this.current_animation = 2;}
			if(inputs.key_s){this.y += 3;this.current_animation = 1;}
			if(inputs.key_a){this.x -= 3;this.current_animation = 3;}
			if(inputs.key_d){this.x += 3;this.current_animation = 4;}
			if(inputs.key_r_released){
				this.current_gun ++;
				if(this.current_gun > Player.NUM_OF_GUNS){this.current_gun = 1;}
				if(this.current_gun == 1){this.bullet_cooldown = Player.PISTOL_COOLDOWN;}
				if(this.current_gun == 2){this.bullet_cooldown = Player.MACHINEGUN_COOLDOWN;}
			}
			if(inputs.key_w == false && inputs.key_a == false && inputs.key_s == false && inputs.key_d == false){this.current_animation = 5;}
			if(inputs.key_right && this.bullet_cooldown_timer == 0){
				this.bullets.add(new Bullet(this.x + Player.down1.getWidth() / 2, this.y - 15 + Player.down1.getHeight() / 2, "right"));
				main.add(this.bullets.get(this.bullets.size()-1));
				this.bullet_cooldown_timer = this.bullet_cooldown;
			}
			if(inputs.key_left && this.bullet_cooldown_timer == 0){
				this.bullets.add(new Bullet(this.x + Player.down1.getWidth() / 2, this.y - 15 + Player.down1.getHeight() / 2, "left"));
				main.add(this.bullets.get(this.bullets.size()-1));
				this.bullet_cooldown_timer = this.bullet_cooldown;
			}
			if(inputs.key_up && this.bullet_cooldown_timer == 0){
				this.bullets.add(new Bullet(this.x + Player.down1.getWidth() / 2, this.y - 15 + Player.down1.getHeight() / 2, "up"));
				main.add(this.bullets.get(this.bullets.size()-1));
				this.bullet_cooldown_timer = this.bullet_cooldown;
			}
			if(inputs.key_down && this.bullet_cooldown_timer == 0){
				this.bullets.add(new Bullet(this.x + Player.down1.getWidth() / 2, this.y - 15 + Player.down1.getHeight() / 2, "down"));
				main.add(this.bullets.get(this.bullets.size()-1));
				this.bullet_cooldown_timer = this.bullet_cooldown;
			}
		}
	}
	public void updateBullets(Game main){
		if(this.bullets.size() > 0){
			for(int i = 0; i < this.bullets.size(); i++){
				this.bullets.get(i).update();
				if(this.bullets.get(i).getX() > Game.APPLICATION_WIDTH || this.bullets.get(i).getY() < 0 || this.bullets.get(i).getX() < 0 || this.bullets.get(i).getY() > Game.APPLICATION_HEIGHT){
					main.remove(this.bullets.get(i));
					this.bullets.remove(i);
					i--;
				}
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
