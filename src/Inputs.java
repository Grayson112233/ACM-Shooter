import java.awt.event.KeyEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class Inputs implements KeyListener {
	public boolean key_up = false;
	public boolean key_down = false;
	public boolean key_left = false;
	public boolean key_right = false;
	public boolean key_space = false;
	public boolean key_w = false;
	public boolean key_a = false;
	public boolean key_s = false;
	public boolean key_d = false;
	public boolean key_e = false;
	public boolean key_r = false;
	public boolean key_r_released = false;
	public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == 38){this.key_up = true;}
        if(e.getKeyCode() == 40){this.key_down = true;}
        if(e.getKeyCode() == 37){this.key_left = true;}
        if(e.getKeyCode() == 39){this.key_right = true;}
        if(e.getKeyCode() == 32){this.key_space = true;}
        if(e.getKeyCode() == 87){this.key_w = true;}
        if(e.getKeyCode() == 65){this.key_a = true;}
        if(e.getKeyCode() == 83){this.key_s = true;}
        if(e.getKeyCode() == 68){this.key_d = true;}
        if(e.getKeyCode() == 82){this.key_r = true;}
        //System.out.println(e.getKeyCode());
    }	
	public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == 38){this.key_up = false;}
        if(e.getKeyCode() == 40){this.key_down = false;}
        if(e.getKeyCode() == 37){this.key_left = false;}
        if(e.getKeyCode() == 39){this.key_right = false;}
        if(e.getKeyCode() == 32){this.key_space = false;}
        if(e.getKeyCode() == 87){this.key_w = false;}
        if(e.getKeyCode() == 65){this.key_a = false;}
        if(e.getKeyCode() == 83){this.key_s = false;}
        if(e.getKeyCode() == 68){this.key_d = false;}
        if(e.getKeyCode() == 82){this.key_r = false;this.key_r_released = true;}
    }
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void update(){
		this.key_r_released = false;
	}
}
