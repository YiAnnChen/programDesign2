package application;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;


public class AlphaPanel extends JPanel{
	
	
	JLabel[] wordColumns = new JLabel[26];
	public AlphaPanel() {
		this.setBackground(Wordle_Main.systemColor);
		this.setLayout(new GridLayout(1,26));
			
		for(int i=0;i<26;i++) {
				wordColumns[i]=new JLabel();
				wordColumns[i].setHorizontalAlignment(JLabel.CENTER);
				wordColumns[i].setOpaque(true);
				wordColumns[i].setBackground(Wordle_Main.systemColor);
				wordColumns[i].setText(String.valueOf((char)('A'+i)));
				wordColumns[i].setForeground(Color.BLACK);
				wordColumns[i].setFont(new Font(wordColumns[i].getText(),Font.BOLD,15));
				this.add(wordColumns[i]);
		};
			
			
		
		}
}
