import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class Inputs implements KeyListener {
	public boolean keyUp = false,
	               keyDown = false,
	               keyLeft = false,
	               keyRight = false,
	               keySpace = false,
	    	       keyW = false,
	    	       keyA = false,
	    	       keyS = false,
	    	       keyD = false,
                   keyE = false,
                   keyR = false,
	               keyRreleased = false;
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()) {
			case 38: this.keyUp = true;    break;
			case 40: this.keyDown = true;  break;
			case 37: this.keyLeft = true;  break;
			case 39: this.keyRight = true; break;
			case 32: this.keySpace = true; break;
			case 87: this.keyW = true;     break;
			case 65: this.keyA = true;     break;
			case 83: this.keyS = true;     break;
			case 68: this.keyD = true;     break;
			case 82: this.keyR = true;     break;
		}
        //System.out.println(e.getKeyCode());
    }	
	public void keyReleased(KeyEvent e) {
		switch(e.getKeyCode()) {
			case 38: this.keyUp = false;   break;
			case 40: this.keyDown = false;	break;
			case 37: this.keyLeft = false;	break;
			case 39: this.keyRight = false;break;
			case 32: this.keySpace = false;break;
			case 87: this.keyW = false;    break;
			case 65: this.keyA = false;    break;
			case 83: this.keyS = false;    break;
			case 68: this.keyD = false;    break;
			case 82: this.keyR = false; this.keyRreleased = true; break;
		}
    }
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void update(){
		this.keyRreleased = false;
	}
}
