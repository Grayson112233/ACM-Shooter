import acm.graphics.GImage;

public final class Player extends Guy {
	public int bullet_cooldown = 30;
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
	
	public int current_gun = 1;
	
	public static final int NUM_OF_GUNS = 2;
	
	Player() {
		animFrames = new GImage[][]
			{
				{ Player.down1, Player.down2 },
				{ Player.up1, Player.up2 },
				{ Player.left1, Player.left2 },
				{ Player.right1, Player.right2 },
				{ Player.standing }
			};
	}
	
	public void update(Game main) {
		if(this.respawn_timer > 0)
			this.respawn_timer--;
		if(!this.alive && this.respawn_timer == 0)
			this.alive = true;
		if(this.alive) {
			if(this.bullet_cooldown_timer > 0)
				this.bullet_cooldown_timer--;
		}
	}
	
	public void keyActions(Game main, Inputs inputs){
		if(!this.alive) return;
		
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
	public double getCenterX(){
		return this.x + Player.standing.getWidth() / 2;
	}
	public double getCenterY(){
		return this.y + Player.standing.getHeight() / 2;
	}
}
