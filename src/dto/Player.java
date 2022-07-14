package dto;

import java.io.Serializable;

public class Player implements Serializable{
	private String name;
	private int point;
	private String rank;
	
	 
	public Player(String name, int point, String rank) {
		this.name = name;
		this.point = point;
		this.rank = rank;
	}


	public Player(String name, int point) {
		this.name = name;
		this.point = point;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getPoint() {
		return point;
	}


	public void setPoint(int point) {
		this.point = point;
	}


	public String getRank() {
		return rank;
	}


	public void setRank(String rank) {
		this.rank = rank;
	}
	
	
	 

}
