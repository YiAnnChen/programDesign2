package config;

import java.util.ArrayList;
import java.util.List;

public class FrameConfig {
	//要接收的數據
		private String title ;
		private int width;
		private int height;
		private int padding;
		private int size;
		private List<LayerConfig> layerConfigs;
		private int startButtonX;
		private int startButtonY;
		private int startButtonW;
		private int startButtonH;
		private int settingButtonX;
		private int settingButtonY;
		private int settingButtonW;
		private int settingButtonH;
		
		//配置窗口
		FrameConfig(org.dom4j.Element frame) {
			this.startButtonX=Integer.parseInt(frame.attributeValue("startButtonX"));
			this.startButtonY=Integer.parseInt(frame.attributeValue("startButtonY"));
			this.startButtonW=Integer.parseInt(frame.attributeValue("startButtonW"));
			this.startButtonH=Integer.parseInt(frame.attributeValue("startButtonH"));
			this.settingButtonX=Integer.parseInt(frame.attributeValue("settingButtonX"));
			this.settingButtonY=Integer.parseInt(frame.attributeValue("settingButtonY"));
			this.settingButtonW=Integer.parseInt(frame.attributeValue("settingButtonW"));
			this.settingButtonH=Integer.parseInt(frame.attributeValue("settingButtonH"));
			
			this.title=frame.attributeValue("title");
			this.width=Integer.parseInt(frame.attributeValue("width"));
			this.height=Integer.parseInt(frame.attributeValue("height"));
			this.padding=Integer.parseInt(frame.attributeValue("padding"));
			this.size=Integer.parseInt(frame.attributeValue("size"));
			List<org.dom4j.Element> layers = frame.elements("layer");
			
			layerConfigs = new ArrayList<LayerConfig>(layers.size());
			for (org.dom4j.Element layer : layers) {
				LayerConfig lc= new LayerConfig(layer.attributeValue("className"), 
						Integer.parseInt(layer.attributeValue("x")), 
						Integer.parseInt(layer.attributeValue("y")),
						Integer.parseInt(layer.attributeValue("w")), 
						Integer.parseInt(layer.attributeValue("h")));
			layerConfigs.add(lc);
			}
		}
		
		
		public int getWidth() {
			return width;
		}
		public int getHeight() {
			return height;
		}
		public int getPadding() {
			return padding;
		}
		public int getSize() {
			return size;
		}
		public List<LayerConfig> getLayerConfigs() {
			return layerConfigs;
		}

		public String getTitle() {
			return title;
		}


		public int getStartButtonX() {
			return startButtonX;
		}


		public int getStartButtonY() {
			return startButtonY;
		}


		public int getStartButtonW() {
			return startButtonW;
		}


		public int getStartButtonH() {
			return startButtonH;
		}


		public int getSettingButtonX() {
			return settingButtonX;
		}


		public int getSettingButtonY() {
			return settingButtonY;
		}


		public int getSettingButtonW() {
			return settingButtonW;
		}


		public int getSettingButtonH() {
			return settingButtonH;
		}
		
		
		
}
