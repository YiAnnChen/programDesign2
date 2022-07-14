package ui;

import java.awt.Graphics;

public class LayerTime extends Layer {

	public LayerTime (int x,int y,int h, int w) {
		super(x, y, h, w);
	}
	
	public void paint(Graphics g) {
		super.createWindows(g);
		g.drawImage(Img.hw,this.x+size, this.y+size,this.w-size*2,this.h-size*2,null);
	}
}
