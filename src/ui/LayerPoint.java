 package ui;

import java.awt.Graphics;

import config.GameConfig;

public class LayerPoint extends Layer{

	private final int levelUp=GameConfig.getSystemConfig().getLevelUp(); //升級條件
	private final int numberX; //畫數字的x座標
	private final int rmLineY; // 消行標題的y座標
	
	public LayerPoint (int x,int y,int h, int w) {
		super(x, y, h, w);
		numberX = this.w-padding-max_bit*numberW;
		rmLineY = padding*2 + Img.point.getHeight(null);
	}
	
	public void paint(Graphics g) {
		super.createWindows(g);
		
		g.drawImage(Img.point,this.x+padding, this.y+padding,null); //繪製窗口標題 分數
		
		this.drawNumber(numberX, padding, this.gameDto.getNowPoint() , max_bit, g); //顯示分數
		
		g.drawImage(Img.rmline,this.x+padding, this.y+rmLineY,null); //繪製窗口標題 消行
		
		this.drawNumber(numberX, rmLineY , this.gameDto.getRmLine() , max_bit, g); //顯示消行
		 
		this.drawRect(rmLineY+padding+Img.rmline.getHeight(null),(double)this.gameDto.getNowPoint()%levelUp/(double)levelUp, g); //繪製值曹
	}
}
