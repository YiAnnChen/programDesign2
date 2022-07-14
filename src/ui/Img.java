package ui;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

public class Img {

	private static final String GRAPHICS_PATH = "/Users/artchief/eclipse-workspace/Tetris/Graphics/";
	private static final String DEFAULT_PATH = "Default/";
	
	public static Image bg01; //暫時背景圖
	public static Image rank;
	public static Image disk;
	public static Image button;
	public static Image next;
	public static Image nextnext;
	public static Image point;
	public static Image hw;
	public static Image window;
	public static Image act;  //方塊
	public static Image number; //數字
	public static Image rmline; //消行
	public static Image rect; //值槽
	public static Image nextLevel;
	public static Image shadow;
	public static ImageIcon start;
	public static ImageIcon setting;
	
	public static Image[] nextact; //下一個方塊的數組
	public static List<Image> rank_List; //段位清單
	public static List<Image> bg_List; //背景清單
	
	
	
	static {
		setSkin(DEFAULT_PATH); //設定造型路線
	}
	//設定造型方法
	public static void setSkin(String PATH) {
		String skinPath = GRAPHICS_PATH+PATH; //將設定路徑與Graphics結合
		window = new ImageIcon(skinPath+"Window/window.png").getImage();
		bg01 = new ImageIcon(skinPath+"BackGround/1.jpeg").getImage();
		rank = new ImageIcon(skinPath+"String/profile.png").getImage();
		disk = new ImageIcon(skinPath+"String/database.png").getImage();
		next = new ImageIcon(skinPath+"String/next.png").getImage();
		nextnext = new ImageIcon(skinPath+"String/nextXt.png").getImage();
		point = new ImageIcon(skinPath+"String/score.png").getImage();
		hw = new ImageIcon(skinPath+"String/hw.jpeg").getImage();
		act = new ImageIcon(skinPath+"Game/rect.png").getImage();
		number = new ImageIcon(skinPath+"Num/num.png").getImage();
		rmline = new ImageIcon(skinPath+"String/line.png").getImage();
		rect = new ImageIcon(skinPath+"Window/rectt.png").getImage();
		nextLevel = new ImageIcon(skinPath+"String/nextLevel.png").getImage();
		shadow = new ImageIcon(skinPath+"Game/shadow.png").getImage();
		start = new ImageIcon(skinPath+"String/start.png");
		setting = new ImageIcon(skinPath+"String/setButton.png");
		
		nextact = new Image[7];
		for(int i=0;i<nextact.length;i++) {
			nextact[i] = new ImageIcon(skinPath+"Game/"+i+".png").getImage();
		}
		
		//段位圖片數組讀取
		File rankDir = new File(skinPath+"Rank");
		File[] rankFiles  = rankDir.listFiles();
		rank_List = new ArrayList<Image>();
		for (File rankFile : rankFiles) {
			if(!rankFile.isDirectory()) {
				rank_List.add(new ImageIcon(rankFile.getPath()).getImage());
				
			}
			
		}
		
		//背景數組讀取
		File bgDir = new File(skinPath+"BackGround");
		File[] bgFiles  = bgDir.listFiles();
		bg_List = new ArrayList<Image>();
		for (File bgFile : bgFiles) {
			if(!bgFile.isDirectory()) {
				bg_List.add(new ImageIcon(bgFile.getPath()).getImage());
		
			}
		}
		bg_List.remove(0);
		
	}
}
