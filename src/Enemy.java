import acm.graphics.GImage;
import acm.util.RandomGenerator;


public class Enemy extends Guy {
	public RandomGenerator randGen = new RandomGenerator();
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
	
	Enemy() {
		animFrames = new GImage[][]
			{
				{ Enemy.down1, Enemy.down2 },
				{ Enemy.up1, Enemy.up2 },
				{ Enemy.left1, Enemy.left2 },
				{ Enemy.right1, Enemy.right2 },
				{ Enemy.standing },
				{ Enemy.health1, Enemy.health2, Enemy.health3 }
			};
	}
	public static final int FRAME_TICK_DELAY = 20;
	public int frame_timer = 1;
	public int ai_action_timer = 120;

	public static final int BULLET_COOLDOWN = 30;
	public int bullet_cooldown_timer = 0;
	/* AI ACTIONS
	 * 0 - Stand Still
	 * 1 - Walk Up
	 * 2 - Walk Down
	 * 3 - Walk Right
	 * 4 - Walk Left
	*/
	public int ai_current_action = 5;
	public int AI_ACTION_DELAY = 60;

	public void draw() {
		super.draw();
		
		if(this.alive){
			animFrames[5][this.health - 1].setLocation(this.x, this.y);
			animFrames[5][this.health - 1].setVisible(true);
		}
	}
	
	public void update(Game main, Player player, int app_width, int app_height){
		if(this.respawn_timer > 0){this.respawn_timer--;}
		if(this.respawn_timer == 0 && this.alive == false){
			this.alive = true;
		}
		if(this.alive){
			if(this.bullet_cooldown_timer > 0) 
				this.bullet_cooldown_timer--;
			if(this.bullet_cooldown_timer == 0 && player.alive){
				if(player.getCenterX() > this.x && player.getCenterX() < this.x + Enemy.standing.getWidth()){
					if(player.getCenterY() > this.y + Enemy.standing.getHeight() / 2){
						this.bullets.add(new Bullet(this.x + Player.down1.getWidth() / 2, this.y - 15 + Player.down1.getHeight() / 2, Direction.DOWN));
						main.add(this.bullets.get(this.bullets.size()-1));
						this.bullet_cooldown_timer = Enemy.BULLET_COOLDOWN;
					}
					if(player.getCenterY() <= this.y + Enemy.standing.getHeight() / 2){
						this.bullets.add(new Bullet(this.x + Player.down1.getWidth() / 2, this.y - 15 + Player.down1.getHeight() / 2, Direction.UP));
						main.add(this.bullets.get(this.bullets.size()-1));
						this.bullet_cooldown_timer = Enemy.BULLET_COOLDOWN;
					}
				}
				if(player.getCenterY() > this.y && player.getCenterY() < this.y + Enemy.standing.getHeight()){
					if(player.getCenterX() > this.x + Enemy.standing.getWidth() / 2){
						this.bullets.add(new Bullet(this.x + Player.down1.getWidth() / 2, this.y - 15 + Player.down1.getHeight() / 2, Direction.RIGHT));
						main.add(this.bullets.get(this.bullets.size()-1));
						this.bullet_cooldown_timer = Enemy.BULLET_COOLDOWN;
					}
					if(player.getCenterX() <= this.x + Enemy.standing.getHeight() / 2){
						this.bullets.add(new Bullet(this.x + Player.down1.getWidth() / 2, this.y - 15 + Player.down1.getHeight() / 2, Direction.LEFT));
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
				this.currentAnimation = 5;
				this.currentFrame = 0;
			}
			if(this.ai_current_action == 1){
				this.y -= 3;
				this.currentAnimation = 1;
				}
			if(this.ai_current_action == 2){
				this.y += 3;
				this.currentAnimation = 0;
				}
			if(this.ai_current_action == 4){
				this.x -= 3;
				this.currentAnimation = 2;
				}
			if(this.ai_current_action == 3){
				this.x += 3;
				this.currentAnimation = 3;
				}
			if(this.ai_current_action == 5){
				this.currentAnimation = 4;
			}
			
		}
	}
}

