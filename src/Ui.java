import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;

import acm.graphics.GImage;
import acm.graphics.GLabel;

public class Ui {
	public GImage heart1 = new GImage("assets/pics/heart.png");
	public GImage heart2 = new GImage("assets/pics/heart.png");
	public GImage heart3 = new GImage("assets/pics/heart.png");
	public GLabel gunLabel = new GLabel("Gun: Pistol");
	public void addImages(Game main, int app_width, int app_height){
		main.add(this.heart1);
		main.add(this.heart2);
		main.add(this.heart3);
		this.heart1.setLocation(app_width - heart1.getWidth() * 1, 0);
		this.heart2.setLocation(app_width - heart1.getWidth() * 2, 0);
		this.heart3.setLocation(app_width - heart1.getWidth() * 3, 0);
		try {
			Font font = Font.createFont(Font.TRUETYPE_FONT, new File("assets/fonts/pixelmix.ttf"));
			this.gunLabel.setFont(font.deriveFont(25f));
		} catch (IOException|FontFormatException e) {
			System.out.println("WARNING: NO FONT FILE FOUND");
		}
		this.gunLabel.setColor(Color.BLACK);
		this.gunLabel.setLocation(10, this.gunLabel.getHeight() + 10);
		main.add(gunLabel);
	}
	public void draw(Player player){
		this.setAllInvisible();
		this.gunLabel.setVisible(true);
			if(player.alive){
			if(player.health > 0){
				this.heart1.setVisible(true);
			}
			if(player.health > 1){
				this.heart2.setVisible(true);
			}
			if(player.health > 2){
				this.heart3.setVisible(true);
			}
			if(player.current_gun == 1){this.gunLabel.setLabel("GUN: PISTOL");}
			if(player.current_gun == 2){this.gunLabel.setLabel("GUN: MACHINE GUN");}
		}
	}
	public void setAllInvisible(){
		this.heart1.setVisible(false);
		this.heart2.setVisible(false);
		this.heart3.setVisible(false);
		this.gunLabel.setVisible(false);
	}
}
