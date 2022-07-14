package dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.sound.midi.VoiceStatus;

import dto.Player;

public class Record {
	//讀檔
	public List<Player> loaddata(){
		ObjectInputStream ois = null;
		List<Player> players = null;
		try {
			ois = new ObjectInputStream(new FileInputStream("data/record.dat"));
			players =new ArrayList<Player>();
			players = (List<Player>)ois.readObject();
			ois.close();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return players;
		
	}
	//存檔
	public void savedata(Player pla) {
		//先讀取
		List<Player> players = this.loaddata();
		//將本局玩家紀錄儲存
		players.add(pla);
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(new FileOutputStream("data/record.dat"));
			oos.writeObject(players);
			oos.close();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//	public static void main(String[] args) {
//		Record record = new Record();
//		List<Player> players = new ArrayList<Player>();
//		players.add(new Player("天選之人", 6666, "gm"));
//		record.savedata(players);
//		System.out.println("保存完畢");
//	}

}
