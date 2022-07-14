package ui;

import java.awt.Graphics;
import java.awt.Image;
import java.util.List;

import dto.Player;

public class LayerDisk extends Layer{
	
	private static int startY; //值槽起始y
	private static int rect_H_disk=Img.rect.getHeight(null)+6; //值槽高度
	private static int spacing; //值槽間距
	
	public LayerDisk (int x,int y,int h, int w) {
		super(x, y, h, w);
		spacing = (this.h-padding*2-Img.disk.getHeight(null)-rect_H_disk*5)/5;  //計算間距
		startY =   padding+Img.disk.getHeight(null)+spacing; //計算起始y
	}

	public void paint(Graphics g) {
		super.createWindows(g); //繪製窗體
		this.showData(Img.disk,this.gameDto.getRecord(),g); //顯示數據
	
	}

	private void showData(Image title,List<Player> players,Graphics g) {
		g.drawImage(title,this.x+padding, this.y+padding,null); //繪製窗口標題
		int nowPoint = this.gameDto.getNowPoint(); //獲取現在分數 
		//循環繪製玩家紀錄
		for(int i=0;i<max_row;i++) {
			Player pla=players.get(i);//獲取一筆玩家紀錄
			int plaPoint =pla.getPoint(); //獲取玩家分數
			double percent = (double)nowPoint/plaPoint; //計算現在分數與紀錄分數的比率
			percent =percent>1 ? 1 : percent; //如果破紀錄則比率設為1
			this.drawRectData(startY+i*(spacing+rect_H_disk) , pla.getName(), plaPoint , pla.getRank(),percent,max_bit, g);//繪製紀錄
		}
		
	}
}
