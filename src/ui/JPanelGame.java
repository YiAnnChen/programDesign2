package ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.management.loading.PrivateClassLoader;
import javax.swing.*;


import config.FrameConfig;
import config.GameConfig;
import config.LayerConfig;
import control.GameControl;
import control.PlayerControl;
import dto.GameDto;

public class JPanelGame extends JPanel{
	
	private List<Layer> layers = null;
	private GameControl gameControl;
	private GameDto gameDto;
	private JButton start;
//	private JButton setting;
	
	public JPanelGame(GameDto gameDto,GameControl gameControl) {
		this.gameDto = gameDto; //獲取遊戲數據源
		this.gameControl = gameControl;  //獲取遊戲控制
		this.initLayer();//初始化層
		this.initComponent();//初始化組件
		this.setLayout(null); //設定自由佈局
		this.addKeyListener(new PlayerControl(gameControl)); //新增鍵盤監聽
		
	}
	
	//初始化組件
	private void initComponent() {
		//初始化按鈕
		this.start = new JButton(Img.start);
		this.start.setBounds(GameConfig.getFrameConfig().getStartButtonX(), GameConfig.getFrameConfig().getStartButtonY(),
				GameConfig.getFrameConfig().getStartButtonW(), GameConfig.getFrameConfig().getStartButtonH());  //設定按鈕在面版上的位子
		//安裝事件監聽
		this.start.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//關閉開始按鈕
				startButton(false );
				//開始遊戲
				gameControl.start();
				//返回焦點
				requestFocus();
				
			}
		});
		this.add(start); //將按鈕添加到主面板
		
//		this.setting = new JButton(Img.setting);//初始化按鈕
//		this.setting.setBounds(GameConfig.getFrameConfig().getSettingButtonX(), GameConfig.getFrameConfig().getSettingButtonY(),
//				GameConfig.getFrameConfig().getSettingButtonW(), GameConfig.getFrameConfig().getSettingButtonH());  //設定按鈕在面版上的位子
//		//安裝事件監聽
//				this.setting.addActionListener(new ActionListener() {
//					
//					@Override
//					public void actionPerformed(ActionEvent e) {
//						//返回焦點
//						requestFocus();
//						
//					}
//				});
//		this.add(setting ); //將按鈕添加到主面板
		
	}
	
	//初始化層
	private void initLayer() {
		
		FrameConfig fcfg = GameConfig.getFrameConfig();  //獲得遊戲配置
		
		List<LayerConfig> layerConfigs = fcfg.getLayerConfigs(); //獲得層配置
		
		layers = new ArrayList<Layer>(layerConfigs.size());  //創建層數組
		
		//循環創建組
		for (LayerConfig layer : layerConfigs) {
			try {
				Class<?> c = Class.forName(layer.getClassName());  //獲得類對象
				
				Constructor<?> ctr = c.getConstructor(int.class,int.class,int.class,int.class); //獲得構造值
				
				Layer l= (Layer)ctr.newInstance(layer.getX(),layer.getY(),layer.getW(),layer.getH()); //透過構造函數創建對象
				
				l.setGameDto(this.gameDto); //獲取遊戲數據
				l.setGameControl(this.gameControl); //獲得遊戲控制
				
				layers.add(l); //將對象加入層數組
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void paintComponent(Graphics g) {

		for(int i=0;i<layers.size();layers.get(i++).paint(g)) ;
		//返回焦點
		this.requestFocus();
		
	}

	//開啟或關閉開始按鈕 
	public void startButton(boolean onoff) {
			this.start.setEnabled(onoff);
	}
}
