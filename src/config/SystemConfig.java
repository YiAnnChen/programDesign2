package config;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.dom4j.Element;

public class SystemConfig {
	
	private int min_x;
	private int min_y;
	private int max_x;
	private int max_y;
	private int max_bit; 
	private int max_row;
	private int levelUp;
	
	private  List<Point[]> TYPECONFIG;
	private  List<Boolean> TYPEROTATE; 
	private  HashMap<Integer, Integer> PLUSPOINT;
	private  HashMap<Integer, String> RANKLIST;
	
	public SystemConfig(Element system) {
		this.min_x=Integer.parseInt(system.attributeValue("min_x"));
		this.min_y=Integer.parseInt(system.attributeValue("min_y"));
		this.max_x=Integer.parseInt(system.attributeValue("max_x"));
		this.max_y=Integer.parseInt(system.attributeValue("max_y"));
		
		this.max_bit=Integer.parseInt(system.attributeValue("max_bit"));
		this.max_row=Integer.parseInt(system.attributeValue("max_row"));
		this.levelUp=Integer.parseInt(system.attributeValue("levelUp"));
		
		//獲取所有rect元素
		List<Element> rects = system.elements("rect");
		TYPECONFIG = new ArrayList<Point[]>(rects.size());
		TYPEROTATE = new ArrayList<Boolean>(rects.size());
		for (Element rect : rects) {
		TYPEROTATE.add(Boolean.parseBoolean(rect.attributeValue("rotate")));
		List<Element> pointsL = rect.elements("point");
		Point[] points = new Point[pointsL.size()];
		for(int i=0;i<pointsL.size();i++) {
			int x = Integer.parseInt(pointsL.get(i).attributeValue("x"));
			int y = Integer.parseInt(pointsL.get(i).attributeValue("y"));
			Point point = new Point(x, y);
			points[i] = point;
		}
		TYPECONFIG.add(points);
		}
		List<Element> plusPoint = system.elements("plusPoint");
		PLUSPOINT = new HashMap<Integer,Integer>(plusPoint.size());
		for (Element p : plusPoint) {
			int rm = Integer.parseInt(p.attributeValue("rm"));
			int point = Integer.parseInt(p.attributeValue("point"));
			PLUSPOINT.put(rm, point);
		}
		List<Element> ranklist = system.elements("rankList");
		RANKLIST = new HashMap<Integer,String>(ranklist.size());
		for (Element r : ranklist) {
			int level = Integer.parseInt(r.attributeValue("level"));
			String rank = (r.attributeValue("rank"));
			RANKLIST.put(level, rank); 
		}
	}

	public int getMax_bit() {
		return max_bit;
	}

	public int getMax_row() {
		return max_row;
	}

	public int getLevelUp() {
		return levelUp;
	}

	public int getMin_x() {
		return min_x;
	}

	public int getMin_y() {
		return min_y;
	}

	public int getMax_x() {
		return max_x;
	}

	public int getMax_y() {
		return max_y;
	}

	public  List<Point[]> getTYPECONFIG() {
		return TYPECONFIG;
	}

	public  List<Boolean> getTYPEROTATE() {
		return TYPEROTATE;
	}

	public  HashMap<Integer, Integer> getPLUSPOINT() {
		return PLUSPOINT;
	}

	public HashMap<Integer, String> getRANKLIST() {
		return RANKLIST;
	}
	
	 
	
}
