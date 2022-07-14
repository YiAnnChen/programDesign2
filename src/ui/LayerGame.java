package ui;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Window;
import java.time.chrono.ThaiBuddhistChronology;
import java.util.Iterator;

public class LayerGame extends Layer {
	
	private static final int actSize = 32; //方塊尺寸

	public LayerGame (int x,int y,int h, int w) {
		super(x, y, h, w);
	}
	
	public void paint(Graphics g) {
		super.createWindows(g); //繪製遊戲窗口
		//開始才繪製遊戲內容
		if(gameDto.getGameAct()!= null) {
			Point[] actPoints = this.gameDto.getGameAct().getActPoints();  //獲取當前方塊
			
			this.drawShadow(actPoints,g);
			
			this.drawActPoints(actPoints,g); //繪製方塊
			
			this.drawMap(g);
		}
		
		
		
	}
	//繪製陰影
	private void drawShadow(Point[] actPoints, Graphics g) {
		if(this.gameDto.isShadow()) {
			int leftX=9;
			int rightX=0;
			for(Point p:actPoints) {
				leftX = leftX > p.x ? p.x :leftX;
				rightX = rightX < p.x ?p.x : rightX;
			}
			 g.drawImage(Img.shadow, this.x+size+actSize*leftX, this.y+size, actSize*(rightX-leftX+1), this.h-size*2, null); 
		}
		
	}

	//	繪製整個方塊
	private void drawActPoints(Point[] actPoints,Graphics g) {
		//默認設定地圖堆疊方塊索引為0
		int temp =this.gameDto.getGameAct().getTypeCode()+1;
		//若遊戲輸掉則為8
		temp =this.gameDto.isStart()? temp:8;
				
		for(int i =0;i<actPoints.length;i++) {
			drawActPoint(actPoints[i].x, actPoints[i].y,temp, g);
		}
	}
	
	//繪製單個方塊
	private void drawActPoint(int x,int y,int imgIndex,Graphics g) {
		g.drawImage(Img.act,
				this.x+x*actSize+size,
				this.y+y*actSize+size,
				this.x+x*actSize+size+actSize,
				this.y+y*actSize+size+actSize,
				imgIndex*actSize,0,
				imgIndex*actSize+actSize,actSize,null);
	}
	
	//繪製地圖
	public void drawMap(Graphics g) {
		//默認設定地圖堆疊方塊索引為0
		int temp =0;
		//若遊戲輸掉則為8
		temp =this.gameDto.isStart()? temp:8;
		
		boolean[][] gameMap =this.gameDto.getGameMap(); //獲取地圖
		for(int x=0;x<gameMap.length;x++) {
			for(int y=0;y<gameMap[x].length;y++) {
				if(gameMap[x][y]) {
					drawActPoint(x, y,temp,g);
				}
			}
		}
	}
	
	}

