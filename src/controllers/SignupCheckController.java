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

public class SignupCheckController implements Initializable {
	@FXML private Button signup = new Button();
	@FXML private Button imgood = new Button();
	@FXML private VBox beginvb = new VBox();
	
	public void goodButtonClicked(MouseEvent e)
	{
		imgood.setStyle(
				"-fx-background-color: #044dbb;"+
				"-fx-text-fill: #fff;");
	}
	
	public void goodButtonReleased(MouseEvent e)
	{
		imgood.setStyle(
				"-fx-background-color: #fff;"+
				"-fx-text-fill: #044dbb;");
	}
	
	public void goodButtonHover(MouseEvent e)
	{
		imgood.setStyle(
				  "-fx-background-color: #bcdcfa;"+
				  "-fx-text-fill:#044dbb;"
				);
	}
	public void goodButtonExit(MouseEvent e)
	{
		imgood.setStyle("-fx-background-color: #fff");
	}
	
	public void signupButtonClick(MouseEvent e)
	{
		signup.setStyle(
				  "-fx-text-fill:#044dbb;"+
				  "-fx-background-color: #fff;"+
				  "-fx-border-color: #044dbb;"
				);
	}
	public void signupButtonRelease(MouseEvent e)
	{
		signup.setStyle(
				  "-fx-text-fill:#fff;"+
				  "-fx-background-color: #044dbb;"
				);
	}
	
	public void signupButtonHover(MouseEvent e)
	{
		signup.setStyle(
				  "-fx-background-color: #bcdcfa;"+
				  "-fx-text-fill:#044dbb;"
				);
	}
	public void signupButtonExit(MouseEvent e)
	{
		signup.setStyle(
				"-fx-background-color: #044dbb;"+
				"-fx-text-fill:#fff;"
				);
	}
	
	private void switchToReg()
	{
		Parent root;
		try 
		{
			root = FXMLLoader.load(getClass().getResource("/controllers/Registration.fxml"));
			Scene scene = new Scene(root);
			Stage stage= (Stage)beginvb.getScene().getWindow();
			stage.setScene(scene);
			stage.show();
			
		} 
		catch (IOException e1) {
			e1.printStackTrace();
		}

	}
	
	private void switchToHome()
	{
		Parent root;
		try 
		{
			root = FXMLLoader.load(getClass().getResource("/controllers/HomeScreen.fxml"));
			Scene scene = new Scene(root);
			Stage stage= (Stage)beginvb.getScene().getWindow();
			stage.setScene(scene);
			stage.show();
			
		} 
		catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	public void fadeOutToReg()
	{
		FadeTransition fade= new FadeTransition();
		fade.setDuration(Duration.millis(200));
		fade.setNode(beginvb);
		fade.setFromValue(1);
		fade.setToValue(0);
		fade.setOnFinished((ActionEvent e)->
		{
			switchToReg();
		});
		fade.play();
	}
	public void fadeOutToHome()
	{
		FadeTransition fade= new FadeTransition();
		fade.setDuration(Duration.millis(200));
		fade.setNode(beginvb);
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
		fade.setNode(beginvb);
		fade.setFromValue(0);
		fade.setToValue(1);
		fade.play();
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb)
	{
		beginvb.setOpacity(0);
		fadeIn();
	}
}
