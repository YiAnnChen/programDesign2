package dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import config.GameConfig;
import dao.Record;
import entity.GameAct;
import util.GameFunction;

public class GameDto {
	
	private boolean start; //遊戲是否 開始
	private boolean shadow; //陰影開關
	private int sleepTime; //線程下落時間間格
	
	private List<Player> record;
	
	private int nowPoint;
	
	private GameAct gameAct;
	
	private boolean[][] gameMap;
	
	private int Next;
	
	private int NextNext;
	
	private int rmLine; //當前消行
	
	private int level;
	
	private HashMap<Integer, String> RANKLIST = GameConfig.getSystemConfig().getRANKLIST();
	
	public GameDto(){
		this.dtoInit();
	}
	
	//遊戲數據初始化
	public void dtoInit() {
		this.gameMap = new boolean[10][18];
		this.nowPoint = 0;
		this.rmLine=0;
		this.level=1;
		this.sleepTime = GameFunction.sleepTime(level);
	}

	public List<Player> getRecord() {
		return record;
	}

	public void setRecord(List<Player> record) {
		this.record = setFillRecord(record);
	}

	private List<Player> setFillRecord(List<Player> players) {
		//如果是空的就創建
		if(players==null) {
			players = new ArrayList<Player>();
		}
		//如果未滿五筆資料就創建到五筆
		while(players.size()<5) {
			players.add(new Player("no data", 0, " "));
		}
		return players;
	}

	public int getNowPoint() {
		return nowPoint;
	}

	public void setNowPoint(int newPoint) {
		this.nowPoint = newPoint;
	}

	public GameAct getGameAct() {
		return gameAct;
	}

	public void setGameAct(GameAct gameAct) {
		this.gameAct = gameAct;
	}

	public boolean[][] getGameMap() {
		return gameMap;
	}

	public void setGameMap(boolean[][] gameMap) {
		this.gameMap = gameMap;
	}

	public int getNext() {
		return Next;
	}

	public void setNext(int next) {
		Next = next;
	}

	public int getNextNext() {
		return NextNext;
	}

	public void setNextNext(int nextNext) {
		NextNext = nextNext;
	}

	public int getRmLine() {
		return rmLine;
	}

	public void setRmLine(int rmLine) {
		this.rmLine = rmLine;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
		this.sleepTime =GameFunction.sleepTime(level); //設定下落線程時間
	}

	public boolean isStart() {
		return start;
	}

	public void setStart(boolean start) {
		this.start = start;
	}

	public boolean isShadow() {
		return shadow;
	}

	public void setShadow(boolean shadow) {
		this.shadow = shadow;
	}

	public int getSleepTime() {
		return sleepTime;
	}

	public HashMap<Integer, String> getRANKLIST() {
		return RANKLIST;
	}

	  
	
	
	
	
	
	
}
