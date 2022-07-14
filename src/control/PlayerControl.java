package control;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.time.chrono.ThaiBuddhistChronology;

public class PlayerControl extends KeyAdapter{

	private GameControl gameControl;
	
	
	public PlayerControl(GameControl gameControl) {
		this.gameControl = gameControl;
	}


	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()) {
			case KeyEvent.VK_UP:
				this.gameControl.KeyUP();
				break;
				
			case KeyEvent.VK_DOWN:
				this.gameControl.KeyDown();
				break;
				
			case KeyEvent.VK_RIGHT:
				this.gameControl.KeyRight();
				break;
				
			case KeyEvent.VK_LEFT:
				this.gameControl.KeyLeft();
				break;
				
			case KeyEvent.VK_S:
				this.gameControl.KeyPlus();
				break;
				
			case KeyEvent.VK_SPACE:
				this.gameControl.KeyQuickDown();
				break;
			
			case KeyEvent.VK_SHIFT:
				this.gameControl.KeySwitch();
				break;
		}
		
	}

}
