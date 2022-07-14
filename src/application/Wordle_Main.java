package application;

import javafx.scene.paint.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageTranscoder;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

import javafx.scene.shape.Line;



public class Wordle_Main extends JFrame implements ActionListener{
	public static final Color greenColor=new Color(35,166,57);
	public static final Color yellowColor=new Color(208,161,30);
	public static final Color grayColor=new Color(146,145,143);
	public static final Color systemColor=new Color(250,244,215);
	
	private JFrame gameFrame;
	private WordPanel[] wordPanelArray =new WordPanel[raw];
	private AlphaPanel alphaPanel;
	private UserPanel userPanel;
	private String wordleString;
	private Label title;
	private int count=0;
	
	
	private static final int raw=7;
	
	public Wordle_Main() {
		gameFrame = new JFrame("Wordle Game");
		gameFrame.setSize(546,700);
		gameFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //can turn into next scene!
		gameFrame.setLayout(new GridLayout(raw+2,1));
		gameFrame.setVisible(true);
		gameFrame.setLocationRelativeTo(null);
		title = new Label();
		
	
		title.setBackground(systemColor);
	
		
		title.setText("Wordle");
		title.setAlignment(Label.CENTER);
		title.setFont(new Font("Serif", Font.BOLD,700/(raw+2)-30));
		
    	
		
		gameFrame.add(title);
	
		
		for(int i=1;i<raw;i++) {
			wordPanelArray[i]=new WordPanel();
			gameFrame.add(wordPanelArray[i]);
		};
		
		alphaPanel=new AlphaPanel();		
		gameFrame.add(alphaPanel);
		
		userPanel=new UserPanel();
		userPanel.getOkButton().addActionListener(this);
		gameFrame.add(userPanel);
		gameFrame.revalidate();
		
		wordleString = getWordleString();
		
	}
	

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Wordle_Main wordle= new Wordle_Main();
		
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		String userWord = this.userPanel.getUserInput().getText();
		
		if(getDictionaryList().contains(userWord)) {
			count++;
		}
		
		if(userWord.length()>4) {
			if(getDictionaryList().contains(userWord)) {
				if(isWordleWordEqualTo(userWord.trim().toUpperCase())==true) {
					JOptionPane.showMessageDialog(null,"You win!!","Congrats",JOptionPane.INFORMATION_MESSAGE);
					clearAllpanes();
					clearAlphaPlane();
					//close window
					wordleString = getWordleString();
					return;
				}
			}else {
				JOptionPane.showMessageDialog(null,"The word is not in library. Please change word.","Alert!",JOptionPane.INFORMATION_MESSAGE);
			}
		}
		if(count>=raw-1) {
			JOptionPane.showMessageDialog(null,"You Lost. Better luck next time.","Oops",JOptionPane.INFORMATION_MESSAGE);
			clearAllpanes();
			clearAlphaPlane();
			wordleString = getWordleString();
			return;
		}
		
		
	}
	
	private void clearAlphaPlane() {
		for(int i=0;i<26;i++) {
			alphaPanel.wordColumns[i].setForeground(Color.black);
		}
	}
	
	private void clearAllpanes() {
		for(int i=1;i<=count;i++) {
			wordPanelArray[i].clearWordPanel();
		}
		count=0;
	}
	
	private boolean isWordleWordEqualTo(String userWord) {
		List<String> wordleWordsList = Arrays.asList(wordleString.split(""));
		String[] userWordsArray = userWord.split("");
		List<Boolean> wordMatchList = new ArrayList<>();
		
		for(int i=0;i<5;i++) {
			char character=userWordsArray[i].charAt(0);
			int ascii=(int)character - 'A';
			
			if(wordleWordsList.contains(userWordsArray[i])) {
				if(wordleWordsList.get(i).equals(userWordsArray[i])) {
					getActivePanel().setPanelText(userWordsArray[i], i+1, Color.GREEN);				
					alphaPanel.wordColumns[ascii].setForeground(greenColor);
					
					wordMatchList.add(true);
				}else {
					getActivePanel().setPanelText(userWordsArray[i], i+1, Color.YELLOW);
					if(alphaPanel.wordColumns[ascii].getForeground()!=greenColor) {
						alphaPanel.wordColumns[ascii].setForeground(yellowColor);
					}
					wordMatchList.add(false);
				}
			}else {
				getActivePanel().setPanelText(userWordsArray[i], i+1, Color.GRAY);
				if(alphaPanel.wordColumns[ascii].getForeground()==Color.black) {
					alphaPanel.wordColumns[ascii].setForeground(grayColor);
				}
				wordMatchList.add(false);
			}
		}
		return !wordMatchList.contains(false);
	}

	public WordPanel getActivePanel() {
		return this.wordPanelArray[count];
	}
	
	public String getWordleString() {
		Path path = Paths.get("src/application/dictionary.txt");
		List<String> wordList=new ArrayList<>();
		try {
			wordList= Files.readAllLines(path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Random random = new Random();
		int position = random.nextInt(wordList.size());
		System.out.println("Word for the day : "+wordList.get(position).trim());
		return wordList.get(position).trim().toUpperCase();
		

	}
	
	public List<String> getDictionaryList() {
		Path path =  Paths.get("src/application/dictionary.txt");
		List<String> wordList=new ArrayList<>();
		try {
			wordList= Files.readAllLines(path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String string;
		for(int i=0;i<wordList.size();i++) {
			string=wordList.get(i).trim();
			wordList.set(i, string);
		}
		
		
		return wordList;
		

	}

}
