package ui;

import java.awt.Graphics;
import java.awt.Image;

public class LayerRank extends Layer{

	public LayerRank (int x,int y,int h, int w) {
		super(x, y, h, w);
	}
	
	public void paint(Graphics g) {
		super.createWindows(g);
		g.drawImage(Img.rank,this.x+padding, this.y+padding,null); //繪製窗口標題
		
		int rankIndex =this.gameDto.getLevel()-1;
		if(rankIndex>Img.rank_List.size()-1) {
			rankIndex = Img.rank_List.size()-1;
		}
		
		this.drawImageAtCenter(Img.rank_List.get(rankIndex), g); //畫段位
		
		
	}
}
