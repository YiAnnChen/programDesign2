package control;

import javax.swing.JFrame;

import OtherJFrame.JFrameSavePoint;
import dao.Record;
import dto.GameDto;
import dto.Player;
import service.GameTetris;
import ui.JFrameGame;
import ui.JPanelGame;

public class GameControl  {

	private JPanelGame jPanelGame;
	
	private GameDto gameDto;
	
	private GameTetris gameTetris;
	
	private Thread GameThread;

	private JFrameSavePoint jFrameSavePoint;
	
	private Record record;
	

	public GameControl() {
		
		this.gameDto = new GameDto();  //創建遊戲數據源
		
		this.jPanelGame = new JPanelGame(gameDto,this); //創建遊戲面板
		
		this.gameTetris = new GameTetris(gameDto,this); //創建遊戲邏輯
		
		this.record = new Record();
		this.gameTetris.setRecord(record.loaddata());
		
		jFrameSavePoint = new JFrameSavePoint(this); //初始化遊戲保存分數窗
		JFrameGame jf = new JFrameGame(jPanelGame); //創建遊戲窗口
	}
	


	
	public void KeyDown() {
		this.gameTetris.KeyDown();
		this.jPanelGame.repaint();
	}
	
	public void KeyRight() {
		this.gameTetris.KeyRight();
		this.jPanelGame.repaint();
	}
	
	public void KeyLeft() {
		this.gameTetris.KeyLeft();
		this.jPanelGame.repaint();
	}


	public void KeyUP() {
		this.gameTetris.KeyUp();
		this.jPanelGame.repaint();
	}




	public void KeyPlus() {
		this.gameTetris.KeyPlus();
		this.jPanelGame.repaint();
		
	}




	public void KeyQuickDown() {
		this.gameTetris.KeyQuickDown();
		this.jPanelGame.repaint();
		
	}




	public void KeySwitch() {
		this.gameTetris.KeySwitch();
		this.jPanelGame.repaint();
		
	}

	//下落線程
	private class GameThread extends Thread{
		@Override
		public void run() {
			//遊戲開始才執行下落線程
			while(gameDto.isStart()) {
				try {
					Thread.sleep(gameDto.getSleepTime()); //方塊下落間隔時間
					gameTetris.KeyDown(); //方塊下落
					jPanelGame.repaint(); //刷新畫面
					} catch ( Exception e) {
					e.printStackTrace();
				}
			}
			//執行gameover後要做的事
			afterLose();
		}

	}
	private void afterLose() {
		//暫存等級
		int nowLevel = this.gameDto.getLevel();
		//檢查等級是否超過9等
		nowLevel=nowLevel>9 ?9 :nowLevel;
		//打開開始按鈕
		this.jPanelGame.startButton(true); ;
		//顯示保存分數窗口
		this.jFrameSavePoint.showSavePoint(this.gameDto.getNowPoint(), this.gameDto.getRANKLIST().get(nowLevel));
		
	}


	public void start() {
		//數據初始化
		 this.gameDto.dtoInit(); 
		//開始遊戲
		this.gameTetris.start();
		//創立下落線程
		this.GameThread = new GameThread();
		//啟動下落線程
		this.GameThread.start();
		//刷新畫面
		this.jPanelGame.repaint();
		
		
	}




	public void savePoint(String name) {
		//暫存等級
		int nowLevel = this.gameDto.getLevel();
		//檢查等級是否超過9等
		nowLevel=nowLevel>9 ?9 :nowLevel;
		//建立新的PLAYER
		Player pla = new Player(name, this.gameDto.getNowPoint(), this.gameDto.getRANKLIST().get(nowLevel)); 
		//將新的player保存至紀錄
		this.record.savedata(pla);
		//重新讀取新紀錄
		this.gameTetris.setRecord(this.record.loaddata()); 
		//刷新畫面
		this.jPanelGame.repaint();
			
		
	}


	

	
}
