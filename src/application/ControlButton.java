package application;

import java.io.IOException;
import java.net.URL;

import javax.swing.JFrame;

import control.GameControl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Node;



public class ControlButton {
	
		@FXML
		private Stage stage;
		@FXML
		private Scene scene;
		@FXML
		private Parent root;
		
		@FXML
		private Text chooseGame;
		
		public void switchTo2048(ActionEvent event) throws IOException{
			

			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			   URL location = getClass().getResource("2048.fxml");
			         FXMLLoader fxmlLoader = new FXMLLoader();
			         fxmlLoader.setLocation(location);
			         fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
			         Parent root = fxmlLoader.load();         
			         Controller controller = fxmlLoader.getController();
			         controller.initFace(); 
			   Scene scene = new Scene(root);
			   stage.setScene(scene);
			   stage.show();
			   
		}
		
		public void switchToWordle(ActionEvent event)throws IOException{
			Wordle_Main wordle= new Wordle_Main();
		}
		public void switchToTetris(ActionEvent event)throws IOException{
			GameControl tetris=  new GameControl();

		}
		
	}



