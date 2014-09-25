import java.util.ArrayList;

import acm.graphics.GImage;
import acm.util.RandomGenerator;


public class Enemy {
	public RandomGenerator randGen = new RandomGenerator();
	public double x = 0;
	public double y = 0;
	public boolean alive = true;
	public int respawn_timer = 0;
	public static final int RESPAWN_TIME = 60 * 3;
	/* 
	 * animation # | description    | # of frames
	 *           1 | Walking Down   | 2 
	 *           2 | Walking Up     | 2
	 *           3 | Walking Left   | 2
	 *           4 | Walking Right  | 2
	 *           5 | Standing Still | 1
	 * */
	public static final GImage down1 = new GImage("assets/pics/enemy_down1.png");
	public static final GImage down2 = new GImage("assets/pics/enemy_down2.png");
	public static final GImage up1 = new GImage("assets/pics/enemy_up1.png");
	public static final GImage up2 = new GImage("assets/pics/enemy_up2.png");
	public static final GImage right1 = new GImage("assets/pics/enemy_right1.png");
	public static final GImage right2 = new GImage("assets/pics/enemy_right2.png");
	public static final GImage left1 = new GImage("assets/pics/enemy_left1.png");
	public static final GImage left2 = new GImage("assets/pics/enemy_left2.png");
	public static final GImage standing = new GImage("assets/pics/enemy_standing.png");
	public static final GImage health1 = new GImage("assets/pics/health_1.png");
	public static final GImage health2 = new GImage("assets/pics/health_2.png");
	public static final GImage health3 = new GImage("assets/pics/health_3.png");
	public int current_frame = 1;
	public int current_animation = 1;
	public int[] animaitons_num_frames = {2, 2, 2, 2, 1};
	public static final int FRAME_TICK_DELAY = 20;
	public int frame_timer = 1;
	public int ai_action_timer = 120;
	public ArrayList <Bullet> bullets;
	public static final int BULLET_COOLDOWN = 30;
	public int bullet_cooldown_timer = 0;
	public int health = 3;
	/* AI ACTIONS
	 * 0 - Stand Still
	 * 1 - Walk Up
	 * 2 - Walk Down
	 * 3 - Walk Right
	 * 4 - Walk Left
	*/
	public int ai_current_action = 5;
	public int AI_ACTION_DELAY = 60;

	public void update(Game main, Player player, int app_width, int app_height){
		if(this.respawn_timer > 0){this.respawn_timer--;}
		if(this.respawn_timer == 0 && this.alive == false){
			this.alive = true;
		}
		if(this.alive){
			if(this.bullet_cooldown_timer > 0){this.bullet_cooldown_timer--;}
			if(this.bullet_cooldown_timer == 0 && player.alive){
				if(player.getCenterX() > this.x && player.getCenterX() < this.x + Enemy.standing.getWidth()){
					if(player.getCenterY() > this.y + Enemy.standing.getHeight() / 2){
						this.bullets.add(new Bullet(this.x + Player.down1.getWidth() / 2, this.y - 15 + Player.down1.getHeight() / 2, "down"));
						main.add(this.bullets.get(this.bullets.size()-1));
						this.bullet_cooldown_timer = Enemy.BULLET_COOLDOWN;
					}
					if(player.getCenterY() <= this.y + Enemy.standing.getHeight() / 2){
						this.bullets.add(new Bullet(this.x + Player.down1.getWidth() / 2, this.y - 15 + Player.down1.getHeight() / 2, "up"));
						main.add(this.bullets.get(this.bullets.size()-1));
						this.bullet_cooldown_timer = Enemy.BULLET_COOLDOWN;
					}
				}
				if(player.getCenterY() > this.y && player.getCenterY() < this.y + Enemy.standing.getHeight()){
					if(player.getCenterX() > this.x + Enemy.standing.getWidth() / 2){
						this.bullets.add(new Bullet(this.x + Player.down1.getWidth() / 2, this.y - 15 + Player.down1.getHeight() / 2, "right"));
						main.add(this.bullets.get(this.bullets.size()-1));
						this.bullet_cooldown_timer = Enemy.BULLET_COOLDOWN;
					}
					if(player.getCenterX() <= this.x + Enemy.standing.getHeight() / 2){
						this.bullets.add(new Bullet(this.x + Player.down1.getWidth() / 2, this.y - 15 + Player.down1.getHeight() / 2, "left"));
						main.add(this.bullets.get(this.bullets.size()-1));
						this.bullet_cooldown_timer = Enemy.BULLET_COOLDOWN;
					}
				}
			}

			this.ai_action_timer --;
			if(this.ai_action_timer <= 0){
				this.ai_action_timer = this.AI_ACTION_DELAY;
				this.ai_current_action = randGen.nextInt(1, 5);
				if(this.ai_current_action > 5){this.ai_current_action = 5;}
				if(ai_current_action == 4 && x < 150){this.ai_current_action = 3;}
				if(ai_current_action == 3 && x > app_width - 150){this.ai_current_action = 4;}
				if(ai_current_action == 1 && y < 150){this.ai_current_action = 2;}
				if(ai_current_action == 2 && y > app_height - 150){this.ai_current_action = 1;}
			}
			if(this.ai_current_action == 5){
				this.current_animation = 5;
			}
			if(this.ai_current_action == 1){
				this.y -= 3;
				this.current_animation = 2;
				}
			if(this.ai_current_action == 2){
				this.y += 3;
				this.current_animation = 1;
				}
			if(this.ai_current_action == 4){
				this.x -= 3;
				this.current_animation = 3;
				}
			if(this.ai_current_action == 3){
				this.x += 3;
				this.current_animation = 4;
				}
			if(this.ai_current_action == 5){
				this.current_animation = 5;
			}
			
		}
	}
	
	public void draw(){
		if(this.alive){
		this.setAllInvisible();
			if(this.current_animation == 1){
				if(this.current_frame == 1){Enemy.down1.setVisible(true);Enemy.down1.setLocation(this.x, this.y);}
				if(this.current_frame == 2){Enemy.down2.setVisible(true);Enemy.down2.setLocation(this.x, this.y);}
			}
			if(this.current_animation == 2){
				if(this.current_frame == 1){Enemy.up1.setVisible(true);Enemy.up1.setLocation(this.x, this.y);}
				if(this.current_frame == 2){Enemy.up2.setVisible(true);Enemy.up2.setLocation(this.x, this.y);}
			}
			if(this.current_animation == 3){
				if(this.current_frame == 1){Enemy.left1.setVisible(true);Enemy.left1.setLocation(this.x, this.y);}
				if(this.current_frame == 2){Enemy.left2.setVisible(true);Enemy.left2.setLocation(this.x, this.y);}
			}
			if(this.current_animation == 4){
				if(this.current_frame == 1){Enemy.right1.setVisible(true);Enemy.right1.setLocation(this.x, this.y);}
				if(this.current_frame == 2){Enemy.right2.setVisible(true);Enemy.right2.setLocation(this.x, this.y);}
			}
			if(this.current_animation == 5){
				Enemy.standing.setVisible(true);
				Enemy.standing.setLocation(this.x, this.y);
			}
			if(this.health == 1){
				Enemy.health1.setLocation(this.x, this.y - 15);
				Enemy.health1.setVisible(true);
			}
			if(this.health == 2){
				Enemy.health2.setLocation(this.x, this.y - 15);
				Enemy.health2.setVisible(true);
			}
			if(this.health == 3){
				Enemy.health3.setLocation(this.x, this.y - 15);
				Enemy.health3.setVisible(true);
			}
		}
	}
	
	public void clamp(int width, int height){
		if(this.x < 0){this.x = 0;}
		if(this.y < 0){this.y = 0;}
		if(this.x + Enemy.standing.getWidth() > width){this.x = width - Enemy.standing.getWidth();}
		if(this.y + Enemy.standing.getHeight() > height){this.y = height - Enemy.standing.getHeight();}
	}
	
	public void tickFrames(){
		this.frame_timer ++;
		if(this.frame_timer == Enemy.FRAME_TICK_DELAY){this.current_frame ++;this.frame_timer = 0;}
		if(this.current_frame > this.animaitons_num_frames[this.current_animation - 1]){this.current_frame = 1;}
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
	public void setAllInvisible(){
		Enemy.up1.setVisible(false);
		Enemy.up2.setVisible(false);
		Enemy.right1.setVisible(false);
		Enemy.right2.setVisible(false);
		Enemy.left1.setVisible(false);
		Enemy.left2.setVisible(false);
		Enemy.down1.setVisible(false);
		Enemy.down2.setVisible(false);
		Enemy.standing.setVisible(false);
		Enemy.health1.setVisible(false);
		Enemy.health2.setVisible(false);
		Enemy.health3.setVisible(false);
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
	public void addImages(Game main){
		main.add(Enemy.up1);
		main.add(Enemy.up2);
		main.add(Enemy.down1);
		main.add(Enemy.down2);
		main.add(Enemy.right1);
		main.add(Enemy.right2);
		main.add(Enemy.left1);
		main.add(Enemy.left2);
		main.add(Enemy.standing);
		main.add(Enemy.health1);
		main.add(Enemy.health2);
		main.add(Enemy.health3);
	}
}

