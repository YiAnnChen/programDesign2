package service;

import java.awt.Point;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javax.swing.text.StyledEditorKit.BoldAction;

import config.GameConfig;

import java.awt.Desktop.Action;
import java.lang.System.Logger.Level;
import java.time.chrono.ThaiBuddhistChronology;

import control.GameControl;
import dto.GameDto;
import dto.Player;
import entity.GameAct;

public class GameTetris {

	private boolean switchOnce;
	private GameDto gameDto;
	private GameControl gameControl;
	private Random random = new Random();
	private HashMap<Integer, Integer> plusPoint = GameConfig.getSystemConfig().getPLUSPOINT();

	public GameTetris(GameDto gameDto, GameControl gameControl) {
		this.gameDto = gameDto;
		this.gameControl = gameControl;
		
	}
	
	public void test() {
		this.gameDto.setNowPoint(this.gameDto.getNowPoint()+1);
	}
	

	public void KeyUp() {
		this.gameDto.getGameAct().rotate(this.gameDto.getGameMap());
		
	}
	
	public boolean KeyDown() {
		//遊戲開始才執行下落方法
		if(!this.gameDto.isStart()) {
			return false;
		}
		//假如正確向下移動 不執行下面操作
		if(this.gameDto.getGameAct().move(0, 1,this.gameDto.getGameMap())) {
			return true;}
		//獲取地圖
		boolean[][] gameMap = this.gameDto.getGameMap();
		Point[] Act = this.gameDto.getGameAct().getActPoints(); //獲取方塊
		//判斷遊戲是否結束
		
		
		for(int i=0;i<Act.length;i++) {
			gameMap[Act[i].x][Act[i].y] = true;
		}
		
		int plusExp=this.plusExp(gameMap); //判斷是否消行與獲取消行數
		//如果消行增加分數
		if(plusExp>0) {
			this.plusPoint(plusExp);
		}
		switchOnce = false; //開啟交換方塊 
		this.gameDto.getGameAct().initAct(this.gameDto.getNext()); //刷新當前方塊
		this.updateAct(); //產生下下個方塊編號並將下下個設定到下個方塊
		if(isOver(gameMap)) {
			this.gameDto.setStart(false); //將遊戲狀態設為false
			this.gameDto.setShadow(false); //將遊戲陰影關閉 
			 switchOnce = true; //關閉交換方塊
		}
		
		
		return false;
	}
	
	
	//判斷是否需要消行與消行數
	private int plusExp(boolean[][]gameMap) {
		int exp=0;  //初始化消行數
		for(int y=0;y<gameMap[0].length;y++) {
			if(isCanRemoveLine(y,gameMap)) {
				
				this.removeLine(y, gameMap); //地圖數組消行
				exp++;
			}
		}
		return exp;  //回傳消行數 
	}

	//地圖數組消行
	private void removeLine(int rowNumber,boolean[][]gameMap) {
		for(int x=0;x<gameMap.length;x++) {
			for(int y=rowNumber;y>0;y--) {
				gameMap[x][y]=gameMap[x][y-1];
			}
			gameMap[x][0]=false;
		}
		
	}
	
	//加分方法
	private void plusPoint(int exp) {
		 this.gameDto.setNowPoint(this.gameDto.getNowPoint()+plusPoint.get(exp)); //更新分數
		 this.gameDto.setRmLine(this.gameDto.getRmLine()+exp); //更新消行
		 this.gameDto.setLevel(this.gameDto.getNowPoint()/300+1); //更新等級
		 
	}
	
	//判斷是否game over
	private boolean isOver(boolean[][] map) {
		Point[] act = this.gameDto.getGameAct().getActPoints();
		for(int i=0;i<act.length;i++) {
			if(map[act[i].x][act[i].y]) {
				return true;
			}
		}
		return false; 
	}

	//判斷是否能消行
	private boolean isCanRemoveLine(int y , boolean[][]gameMap){
		for(int x=0;x<gameMap.length;x++) {
			if(!gameMap[x][y]) {
				return false;
			}
		}
		return true;  //回傳消行數 
	}
	
	
	public void KeyRight() {
		//遊戲開始才執行下落方法
				if(!this.gameDto.isStart()) {
					return ;}
		this.gameDto.getGameAct().move(1, 0,this.gameDto.getGameMap());
		
	}
	
	public void KeyLeft() {
		//遊戲開始才執行下落方法
				if(!this.gameDto.isStart()) {
					return ;}
		this.gameDto.getGameAct().move(-1, 0,this.gameDto.getGameMap());
		
	}
	
	//更新下下個方塊，並將原本下下個設定到下一個方塊
	private void updateAct() {
		this.gameDto.setNext(this.gameDto.getNextNext()); //設定下一個方塊為下下一個方塊
		
		this.gameDto.setNextNext(random.nextInt(7));
		
	}

	public void KeyPlus() {
		 this.plusPoint(4);
		
	}
	
	public void setRecord(List<Player> players) {
		this.gameDto.setRecord(players);
	}
	//	快速下降方法
	public void KeyQuickDown() {
		while(this.KeyDown());
		
	}
	//交換方塊
	public void KeySwitch() {
	
		int temp = this.gameDto.getGameAct().getTypeCode(); //暫存當前方塊
		//如果是true的話代表本輪交換過 故直接跳出方法
				if(switchOnce || temp==this.gameDto.getNext()) {
					return;
				}
		this.gameDto.getGameAct().initAct(this.gameDto.getNext()); //刷新當前方塊
		this.gameDto.setNext(temp); //設定下一個方塊為當前方塊
		switchOnce =true; //不可再交換方塊
		
		
	}

	//開始遊戲
	public void start() {
	this.gameDto.setGameAct(new GameAct(this,random.nextInt(7)));  //建立當前方塊
	this.gameDto.setNext(random.nextInt(7)); //產生下個方塊編號
	this.gameDto.setNextNext(random.nextInt(7)); //產生下下個方塊編號
	this.gameDto.setShadow(true);//開啟陰影 
	this.gameDto.setStart(true); //將遊戲設定為開始
		
	}
	
}
