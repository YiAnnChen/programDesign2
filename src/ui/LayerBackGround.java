package ui;

import java.awt.Graphics;

public class LayerBackGround extends Layer {
	
		public LayerBackGround (int x,int y,int w, int h) {
			super(x, y, w, h);
		}
		
		public void paint(Graphics g) {
			
		
			int bgIndex =this.gameDto.getLevel()-1;
			if(bgIndex>Img.bg_List.size()-1) {
				bgIndex = Img.bg_List.size()-1;
			}
			g.drawImage(Img.bg_List.get(bgIndex), 0, 0, this.w, this.h, null);
			
}
}
