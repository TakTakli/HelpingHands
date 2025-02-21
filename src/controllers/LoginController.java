package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class LoginController implements Initializable {
	@FXML private Button login = new Button();
	@FXML private VBox rootvb = new VBox();
	
	public void loginButtonClick(MouseEvent e)
	{
		login.setStyle(
				  "-fx-text-fill:#044dbb;"+
				  "-fx-background-color: #fff;"+
				  "-fx-border-color: #044dbb;"
				);
	}
	public void loginButtonRelease(MouseEvent e)
	{
		login.setStyle(
				  "-fx-text-fill:#fff;"+
				  "-fx-background-color: #044dbb;"
				);
	}
	
	public void loginButtonHover(MouseEvent e)
	{
		login.setStyle(
				  "-fx-background-color: #bcdcfa;"+
				  "-fx-text-fill:#044dbb;"
				);
	}
	public void loginButtonExit(MouseEvent e)
	{
		login.setStyle(
				"-fx-background-color: #044dbb;"+
				"-fx-text-fill:#fff;"
				);
	}
	
	private void switchToHome()
	{
		Parent root;
		try 
		{
			root = FXMLLoader.load(getClass().getResource("/controllers/HomeScreen.fxml"));
			Scene scene = new Scene(root);
			Stage stage= (Stage)rootvb.getScene().getWindow();
			stage.setScene(scene);
			stage.show();
			
		} 
		catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	public void fadeOutToHome()
	{
		FadeTransition fade= new FadeTransition();
		fade.setDuration(Duration.millis(200));
		fade.setNode(rootvb);
		fade.setFromValue(1);
		fade.setToValue(0);
		fade.setOnFinished((ActionEvent e)->
		{
			switchToHome();
		});
		fade.play();
	}
	
	public void fadeIn()
	{
		FadeTransition fade= new FadeTransition();
		fade.setDuration(Duration.millis(500));
		fade.setNode(rootvb);
		fade.setFromValue(0);
		fade.setToValue(1);
		fade.play();
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb)
	{
		rootvb.setOpacity(0);
		fadeIn();
	}
}
