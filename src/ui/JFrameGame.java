package ui;

import javax.swing.*;



import config.FrameConfig;
import config.GameConfig;

public class JFrameGame extends JFrame {

	public JFrameGame(JPanelGame jPanelGame) {
		
		FrameConfig fcfg = GameConfig.getFrameConfig();
		
		this.setTitle(fcfg.getTitle());
		
		this.setSize(fcfg.getWidth(),fcfg.getHeight());
		
		this.setResizable(false);
		
		this.setDefaultCloseOperation(1);
		
		this.setLocationRelativeTo(null);
		
		this.setContentPane(jPanelGame);
		
		this.setVisible(true); 
		
	}
	

}
