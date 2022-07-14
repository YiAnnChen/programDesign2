package application;

public class Square {
	
	private boolean combine;
	private int value;
	
	Square(int v){
		value = v;
	}
	
	public void setCombine(boolean c) {
		combine = c;
	}
	
	public int getValue() {
		return value;
	}
	
	boolean canCombine(Square other) {
		return !combine && other != null && !other.combine && value == other.getValue();
	}
	
	int combineTogether(Square other) {
		if(canCombine(other) == true) {
			value = value*2;
			combine = true;
			return value;
		}else {
			return -1;
		}
	}

}
