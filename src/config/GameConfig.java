package config;

import java.util.ArrayList;
import java.util.List;

import javax.lang.model.element.Element;
import javax.swing.text.Document;

import org.dom4j.io.SAXReader;

import ui.JFrameGame;
import ui.Layer;

public class GameConfig {
	
	private static FrameConfig frameConfig;
	private static SystemConfig systemConfig;
	
	static {
		try {
		SAXReader reader = new SAXReader();  //建立讀取器
		org.dom4j.Document doc = reader.read("Data/config.xml"); //讀取xml文件
		org.dom4j.Element game = doc.getRootElement() ; //獲取根元素
		
		frameConfig = new FrameConfig(game.element("frame"));//配置窗口 
		systemConfig = new SystemConfig(game.element("system"));
		
	}catch (Exception e) {
		e.printStackTrace();
	}
		}
	//構造器私有化 
	private GameConfig() {
		
	}
	
	public static FrameConfig getFrameConfig() {
		return frameConfig;
	}
	
	
	public static SystemConfig getSystemConfig() {
		return systemConfig;
	}

	public static void main(String arg[]) throws Exception {
		new GameConfig();
	}
	
}
