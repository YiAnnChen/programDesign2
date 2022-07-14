package ui;

import java.awt.Graphics;

public class LayerButton extends Layer {
	
	public LayerButton (int x,int y,int h, int w) {
		super(x, y, h, w);
	}
	
	public void paint(Graphics g) {
		super.createWindows(g);
	}
}
