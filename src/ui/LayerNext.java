package ui;

import java.awt.Graphics;

public class LayerNext extends Layer{

	public LayerNext (int x,int y,int h, int w) {
		super(x, y, h, w);}
	
	private int centerX=20;
	
	public void paint(Graphics g) {
		
		super.createWindows(g); //繪製窗體
		g.drawImage(Img.next,this.x+padding+centerX, this.y+padding,null); //繪製窗口標題
		//遊戲開始才繪製 
		if(this.gameDto.isStart()) {
			this.drawImageAtLower(Img.nextact[this.gameDto.getNext()], g); //繪製下一個方塊
		}
		
	}
}
