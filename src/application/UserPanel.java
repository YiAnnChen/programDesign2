package application;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class UserPanel extends JPanel{
		
		private JTextField userInput;
		private JButton okButton;
		
		public UserPanel() {
			this.setLayout(new GridLayout(1,2));
			userInput = new JTextField();
			okButton = new JButton("OK");
			this.add(userInput);
			this.add(okButton);
		}
		
		public JTextField getUserInput() {
			return userInput;
		}
		
		public JButton getOkButton() {
			return okButton;
		}
	

}
