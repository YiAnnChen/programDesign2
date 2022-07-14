package application;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class WordPanel extends JPanel{
	final Color greenColor=new Color(35,166,57);
	final Color yellowColor=new Color(208,161,30);
	final Color grayColor=new Color(146,145,143);
	final Color systemColor=new Color(250,244,215);
	
	JLabel[] wordColumns = new JLabel[7];
	
	public WordPanel() {
		this.setLayout(new GridLayout(1,7));
		Border blackBorder= BorderFactory.createLineBorder(new Color(206,200,200), 1);
		
		
		wordColumns[0]=new JLabel();
		wordColumns[0].setHorizontalAlignment(JLabel.CENTER);
		wordColumns[0].setOpaque(true);
			
		wordColumns[0].setBackground(systemColor);
		this.add(wordColumns[0]);
		
		for(int i=1;i<6;i++) {
			wordColumns[i]=new JLabel();
			wordColumns[i].setHorizontalAlignment(JLabel.CENTER);
			wordColumns[i].setOpaque(true);
			wordColumns[i].setBorder(blackBorder);
			wordColumns[i].setBackground(Color.WHITE);
			this.add(wordColumns[i]);
		};
		
		wordColumns[6]=new JLabel();
		wordColumns[6].setHorizontalAlignment(JLabel.CENTER);
		wordColumns[6].setOpaque(true);
			
		wordColumns[6].setBackground(systemColor);
		this.add(wordColumns[6]);
	}
	
	public void clearWordPanel() {
		
		for(int i=1;i<6;i++) {
			wordColumns[i].setText("");
			this.wordColumns[i].setBackground(Color.WHITE);
		}
	}
	public void setPanelText(String charValue, int position, Color color) {
		this.wordColumns[position].setText(charValue);
		this.wordColumns[position].setBackground(color);
	}
	
	
}
