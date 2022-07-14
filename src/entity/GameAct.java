package entity;

import java.awt.Point;
import java.lang.Boolean;
import java.util.ArrayList;
import java.util.List;

import config.GameConfig;
import service.GameTetris;

public class GameAct {
	
		private static final int min_x =GameConfig.getSystemConfig().getMin_x();
		private static final int min_y =GameConfig.getSystemConfig().getMin_y();
		private static final int max_x =GameConfig.getSystemConfig().getMax_x();
		private static final int max_y =GameConfig.getSystemConfig().getMax_y();
		
		private int typeCode;
		

		private Point[] actPoints;
		private GameTetris gameTetris;
		private static List<Point[]> TYPECONFIG = GameConfig.getSystemConfig().getTYPECONFIG();
		private static List<Boolean> TYPEROTATE = GameConfig.getSystemConfig().getTYPEROTATE();
		
		 
		
		public GameAct(GameTetris gameTetris,int typeCode) {
			this.gameTetris = gameTetris;
			this.initAct(typeCode);
			
		}
		
		//刷新方塊
		public void initAct(int typeCode) {
			//存取方塊編號
			this.typeCode = typeCode;
			//獲得方塊對象
			Point[] points = TYPECONFIG.get(typeCode);
			//建立一個新的方塊到actPoints
			actPoints = new Point[points.length];
			for(int i=0;i<points.length;i++) {
				actPoints[i] = new Point(points[i].x,points[i].y);
			}
		}

		public Point[] getActPoints() {
			return actPoints;
		}
		
		
		
		public int getTypeCode() {
			return typeCode;
		}

		//方塊移動
		//X=X軸偏移量
		//y=y軸偏移量
		public boolean move(int x,int y,boolean[][]gameMap) {
			//將方塊座標加上偏移量
			for(int i=0;i<actPoints.length;i++) {
				int newX = actPoints[i].x+x;
				int newY = actPoints[i].y+y;
				//檢查方塊是否超出地圖
				if(isOverMap(newX,newY,gameMap)) {
					//超出直接跳出方法
					return false;
				}
			}
			//如果都沒有超出就移動
			for(int i=0;i<actPoints.length;i++) {
				actPoints[i].x = actPoints[i].x+x;
				actPoints[i].y = actPoints[i].y+y;
				}
				return true;
			}
		
		//檢查方塊是否超出地圖
		public boolean isOverMap(int x,int y,boolean[][]gameMap) {
			if(x<min_x || x>max_x || y<min_y|| y>max_y || gameMap[x][y]) {
					return true;}	
			return false;	}
		
		//方塊旋轉
		public void rotate(boolean[][]gameMap) {
			//如果為正方形則不給旋轉
			if(!TYPEROTATE.get(this.typeCode)) {
				return;
			}
		
			for(int i=1;i<actPoints.length;i++) {
				int newX = actPoints[0].y+actPoints[0].x-actPoints[i].y;
				int newY = actPoints[0].y-actPoints[0].x+actPoints[i].x;
				//判斷是否超出地圖
				if(isOverMap(newX,newY,gameMap)) {
					return;
				}
			}
			//若所有方塊都沒超出地圖，則旋轉方塊
			for(int i=1;i<actPoints.length;i++) {
				int newX = actPoints[0].y+actPoints[0].x-actPoints[i].y;
				int newY = actPoints[0].y-actPoints[0].x+actPoints[i].x;
				actPoints[i].x = newX;
				actPoints[i].y = newY;
		}
		}
		
		
		
}
