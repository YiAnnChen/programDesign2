package ui;

import java.awt.Graphics;

public class LayerNextNext extends Layer{
	public LayerNextNext (int x,int y,int h, int w) {
		super(x, y, h, w);}
	
	private int centerX=20;
	
	public void paint(Graphics g) {
		super.createWindows(g);
		g.drawImage(Img.nextnext,this.x+padding+centerX, this.y+padding,null);
		
		//遊戲開始才繪製 
				if(this.gameDto.isStart()) {
					this.drawImageAtLower(Img.nextact[this.gameDto.getNextNext()], g); //繪製下一個方塊
				}
		
	}
}
