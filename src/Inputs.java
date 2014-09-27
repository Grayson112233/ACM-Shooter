import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class Inputs implements KeyListener {
	public boolean key_up = false,
	               key_down = false,
	               key_left = false,
	               key_right = false,
	               key_space = false,
	    	       key_w = false,
	    	       key_a = false,
	    	       key_s = false,
	    	       key_d = false,
                   key_e = false,
                   key_r = false,
	               key_r_released = false;
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()) {
			case 38: this.key_up = true;    break;
			case 40: this.key_down = true;  break;
			case 37: this.key_left = true;  break;
			case 39: this.key_right = true; break;
			case 32: this.key_space = true; break;
			case 87: this.key_w = true;     break;
			case 65: this.key_a = true;     break;
			case 83: this.key_s = true;     break;
			case 68: this.key_d = true;     break;
			case 82: this.key_r = true;     break;
		}
        //System.out.println(e.getKeyCode());
    }	
	public void keyReleased(KeyEvent e) {
		switch(e.getKeyCode()) {
			case 38: this.key_up = false;   break;
			case 40: this.key_down = false;	break;
			case 37: this.key_left = false;	break;
			case 39: this.key_right = false;break;
			case 32: this.key_space = false;break;
			case 87: this.key_w = false;    break;
			case 65: this.key_a = false;    break;
			case 83: this.key_s = false;    break;
			case 68: this.key_d = false;    break;
			case 82: this.key_r = false; this.key_r_released = true; break;
		}
    }
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void update(){
		this.key_r_released = false;
	}
}
