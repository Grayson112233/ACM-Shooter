import java.util.ArrayList;
import java.util.Iterator;

import acm.program.GraphicsProgram;

public class Game extends GraphicsProgram{
	// APPLICATION
	private static final long serialVersionUID = 1L;
	public static final int APPLICATION_WIDTH = 720;
	public static final int APPLICATION_HEIGHT = 480;
	// FRAME RATE / CYCLES
	public static final int FPS = 60;
	public static boolean loop = true;
	// KEYS
	Inputs inputs = new Inputs();
	// PLAYER
	Player player = new Player();
	// ENEMY
	Enemy enemy = new Enemy();
	// UI
	Ui ui = new Ui();
	
	public static void main(String[] args){
		new Game().start(args);
	}
	public void run(){
		addMouseListeners();
		addKeyListeners(inputs);
		addImages();
		setAllInvisible();
		setupPlayer();
		setupEnemy();
		waitForClick();
		// GAME LOOP
		while(loop){
			keyActions();
			updateBullets();
			drawPlayer();
			enemy.update(this, this.player, Game.APPLICATION_WIDTH, Game.APPLICATION_HEIGHT);
			player.update(this);
			checkCollisions();
			ui.draw(this.player);
			pause(1000.0 / Game.FPS); // Set FPS
		}
	}
	
	public void drawPlayer(){
		// TICK FRAMES
		this.player.tickFrames();
		enemy.tickFrames();
		// MAKE CORRECT FRAME VISIBLE
		inputs.update();
		this.player.clamp(Game.APPLICATION_WIDTH, Game.APPLICATION_HEIGHT);
		enemy.clamp(Game.APPLICATION_WIDTH, Game.APPLICATION_HEIGHT);
		player.draw();
		enemy.draw();
		ui.draw(player);
		
	}
	
	public void addImages(){
		this.player.addImages(this);
		this.enemy.addImages(this);
		this.ui.addImages(this, Game.APPLICATION_WIDTH, Game.APPLICATION_HEIGHT);
		this.player.setAllInvisible();
		this.enemy.setAllInvisible();
	}
	public void updateBullets(){
		// PLAYER BULLETS
		this.player.updateBullets(this);
		// ENEMY BULLETS
		this.enemy.updateBullets(this);
	}
	public void keyActions(){
		this.player.keyActions(this, this.inputs);
	}
	public void setupPlayer(){
		this.player.x = APPLICATION_WIDTH / 2 - Player.down1.getWidth() / 2;
		this.player.y = APPLICATION_HEIGHT / 2 - Player.down1.getHeight();
		this.player.bullets = new ArrayList<Bullet>();
	}
	public void setupEnemy(){
		this.enemy.x = 20;
		this.enemy.y = 20;
		this.enemy.bullets = new ArrayList<Bullet>();
	}
	public void checkCollisions(){
		Iterator<Bullet> playerBulletsIter = this.player.bullets.iterator();
		while(playerBulletsIter.hasNext()) {
			Bullet bullet = playerBulletsIter.next();
			if(this.enemy.alive &&
			   bullet.getCenterX() > this.enemy.x && 
			   bullet.getCenterX() < this.enemy.x + Enemy.standing.getWidth() &&
			   bullet.getCenterY() > this.enemy.y && 
			   bullet.getCenterY() < this.enemy.y + Enemy.standing.getHeight()  ){
						this.enemy.hit();
						remove(bullet);
						playerBulletsIter.remove(); //Removes last element from iterator/collection
			}
		}
		Iterator<Bullet> enemyBulletsIter = this.enemy.bullets.iterator();
		while(enemyBulletsIter.hasNext()) {
			Bullet bullet = enemyBulletsIter.next();
			if(this.player.alive &&
			   bullet.getCenterX() > this.player.x && 
			   bullet.getCenterX() < this.player.x + Player.standing.getWidth() &&
			   bullet.getCenterY() > this.player.y && 
			   bullet.getCenterY() < this.player.y + Player.standing.getHeight()){
						this.player.hit();
						remove(bullet);
						enemyBulletsIter.remove();
			}
		}
	}
	public void setAllInvisible(){
		player.setAllInvisible();
		enemy.setAllInvisible();
		ui.setAllInvisible();
	}
}

