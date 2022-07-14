package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.time.chrono.ThaiBuddhistChronology;

import javax.swing.ImageIcon;


import config.FrameConfig;
import config.GameConfig;
import config.SystemConfig;
import control.GameControl;
import dto.GameDto;

abstract public class Layer {
	
	
	private int numberIndex; //畫數字用的索引
	private static  int windowH=Img.window.getHeight(null); //window高度
	private static  int windowW=Img.window.getWidth(null); //window寬度
	protected static final int size = GameConfig.getFrameConfig().getSize();
	protected static final int padding = GameConfig.getFrameConfig().getPadding();
	protected static final int numberW = Img.number.getWidth(null)/10; //數字切片寬度
	private static final int numberH = Img.number.getHeight(null); //數字切片高度
	private static final int rect_W = Img.rect.getWidth(null); //值槽寬度
	private static final int rect_H = Img.rect.getHeight(null); //值槽高度
	protected final int max_bit = GameConfig.getSystemConfig().getMax_bit(); //數字最大位數
	protected final int max_row = GameConfig.getSystemConfig().getMax_row(); //紀錄最大數
	
	protected int x;
	protected int y;
	protected int h;
	protected int w;
	protected GameDto gameDto;
	protected GameControl gameControl;
	
	public Layer(int x,int y,int w,int h) {
		this.x=x;
		this.y=y;
		this.h=h;
		this.w=w;
		
	}
	
	public void createWindows(Graphics g) {
		g.drawImage(Img.window, x, y, x+size, y+size, 0, 0, size, size, null); //左上
		g.drawImage(Img.window, x+size, y, x+w-size, y+size, size, 0, windowW-size, size, null) ;//中上
		g.drawImage(Img.window, x+w-size, y, x+w, y+size, windowW-size, 0, windowW, size, null) ;//右上
		g.drawImage(Img.window, x, y+size, x+size, h+y-size, 0, size, size, windowH-size, null) ;//左中
		g.drawImage(Img.window, x+size, y+size, x+w-size, y+h-size, size, size, windowW-size, windowH-size, null) ; //中
		g.drawImage(Img.window, x+w-size, y+size, x+w, y+h-size, windowW-size, size, windowW, windowH-size, null) ;//右中
		g.drawImage(Img.window, x, y+h-size, x+size, y+h, 0, windowH-size, size, windowH, null) ;//左下
		g.drawImage(Img.window, x+size, y+h-size, x+w-size, y+h, size, windowH-size, windowW-size, windowH, null) ;//中下
		g.drawImage(Img.window, x+w-size, y+h-size, x+w, y+h, windowW-size, windowH-size, windowW, windowH, null) ; //右下
	}
	
	//畫數字
	protected void drawNumber(int x,int y,int num,int maxBit,Graphics g) {
		
		String numStr = Integer.toString(num); //將要畫出來的數字轉成字串符
		//循環畫數字
		for(int i=0;i<maxBit;i++) {
			if(maxBit-i <= numStr.length()) {
				int bit =numStr.charAt(numberIndex)-'0';
				g.drawImage(Img.number, x+this.x+i*numberW, this.y+y, x+ this.x+numberW+i*numberW,
						y + this.y+numberH, numberW*bit, 0, numberW*bit+numberW, numberH, null);
				numberIndex++;
				}
			
		}
		numberIndex =0; //將畫數字的索引設為零
	}
	
	//畫圖在窗口中間
	protected  void drawImageAtCenter(Image image,Graphics g) {
		//獲取圖片長寬
		int imgW=image.getWidth(null);
		int imgH=image.getHeight(null);
		//計算x,y置中位置
		int x =this.x+(this.w-imgW)/2;
		int y =this.y+(this.h-imgH)/2;
		
		g.drawImage(image, x, y, null);
	}
	
	
	//畫圖在窗口中下方
	protected  void drawImageAtLower(Image image,Graphics g) {
		//獲取圖片長寬
		int imgW=image.getWidth(null);
		int imgH=image.getHeight(null);
		//計算x,y置中位置
		int x =this.x+(this.w-imgW)/2;
		int y =this.y+(this.h-imgH)/2+padding;
		
		g.drawImage(image, x, y, null);
	}
	
	//繪製值槽
	protected void drawRect(int y,Double percent,Graphics g) { 
		//初始化直槽 x y
		int rectX = this.x+padding;
		int rectY = this.y+y;
		//繪製黑色外框值曹
		g.setColor(Color.BLACK);
		g.fillRect(rectX, rectY, this.w-padding*2, rect_H+6 );
		//繪製padding
		g.setColor(Color.WHITE);
		g.fillRect(rectX+1, rectY+1, this.w-padding*2-2, rect_H+4 ); 
		//繪製內部
		g.setColor(Color.BLACK);
		g.fillRect(rectX+2, rectY+2, this.w-padding*2-4, rect_H+2 );
		
		//繪製值槽
		int w = (int)(percent*(this .w-padding*2-4)) ;
		int colorIndex=(int)(percent*rect_W );
		g.drawImage(Img.rect, rectX+2, rectY+2, rectX+2+w,rectY+rect_H,
				colorIndex,0,colorIndex+1,rect_H,null);
		
		g.drawImage(Img.nextLevel, rectX+3, rectY+3,  null);
	}
	
	
	//繪製值槽加文字
		protected void drawRectData(int y,String name,int point,String rank,Double percent,int maxBit,Graphics g) { 
			//初始化直槽 x y
			int rectX = this.x+padding;
			int rectY = this.y+y;
			//繪製黑色外框值曹
			g.setColor(Color.BLACK);
			g.fillRect(rectX, rectY, this.w-padding*2, rect_H+6 );
			//繪製padding
			g.setColor(Color.WHITE);
			g.fillRect(rectX+1, rectY+1, this.w-padding*2-2, rect_H+4 ); 
			//繪製內部
			g.setColor(Color.BLACK);
			g.fillRect(rectX+2, rectY+2, this.w-padding*2-4, rect_H+2 );
			
			//繪製值槽
			int w = (int)(percent*(this .w-padding*2-4)) ;
			int colorIndex=(int)(percent*rect_W)-1; 
			g.drawImage(Img.rect, rectX+2, rectY+2, rectX+2+w,rectY+rect_H,
					colorIndex,0,colorIndex+1,rect_H,null);
			
			//顯示玩家名稱
			g.setColor(Color.WHITE);
			g.setFont(new Font("標楷體",0,20));
			g.drawString(name, rectX+6, rectY+23);
			
			//顯示段位
			g.drawString(rank, this.w/2+padding, rectY+23);
			
			//顯示分數
			String strPoint =Integer.toString(point);
			for(int i=0;i<maxBit;i++) {
				if(maxBit-i <= strPoint.length()) {
					g.drawString(strPoint, this.w-padding*3+numberIndex*10, rectY+23);
					}
				else {numberIndex++;}
				
			}
			numberIndex=0;	
		}
	
	public void setGameDto(GameDto gameDto) {
		this.gameDto = gameDto;
	}
	

	public void setGameControl(GameControl gameControl) {
		this.gameControl = gameControl;
	}

	abstract protected void paint(Graphics g);
}
