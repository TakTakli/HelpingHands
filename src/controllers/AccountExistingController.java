package controllers;

import java.io.IOException;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class AccountExistingController {
	
	@FXML private Button login = new Button();
	@FXML private Button proceed = new Button();
	@FXML private VBox beginvb = new VBox();
	
	public void proceedButtonClicked(MouseEvent e)
	{
		proceed.setStyle(
				"-fx-background-color: #044dbb;"+
				"-fx-text-fill: #fff;");
	}
	
	public void proceedButtonReleased(MouseEvent e)
	{
		proceed.setStyle(
				"-fx-background-color: #fff;"+
				"-fx-text-fill: #044dbb;");
	}
	
	public void proceedButtonHover(MouseEvent e)
	{
		proceed.setStyle(
				  "-fx-background-color: #bcdcfa;"+
				  "-fx-text-fill:#044dbb;"
				);
	}
	public void proceedButtonExit(MouseEvent e)
	{
		proceed.setStyle("-fx-background-color: #fff");
	}
	
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
	
	private void switchToHealthProfile()
	{
		Parent root;
		try 
		{
			root = FXMLLoader.load(getClass().getResource("/controllers/HealthProfile.fxml"));
			Scene scene = new Scene(root);
			Stage stage= (Stage)beginvb.getScene().getWindow();
			stage.setScene(scene);
			stage.show();
			
		} 
		catch (IOException e1) {
			e1.printStackTrace();
		}

	}
	private void switchToLoginScreen()
	{
		Parent root;
		try 
		{
			root = FXMLLoader.load(getClass().getResource("/controllers/Login.fxml"));
			Scene scene = new Scene(root);
			Stage stage= (Stage)beginvb.getScene().getWindow();
			stage.setScene(scene);
			stage.show();
			
		} 
		catch (IOException e1) {
			e1.printStackTrace();
		}
		
	}
	
	public void fadeOutToHealthProfile()
	{
		FadeTransition fade= new FadeTransition();
		fade.setDuration(Duration.millis(200));
		fade.setNode(beginvb);
		fade.setFromValue(1);
		fade.setToValue(0);
		fade.setOnFinished((ActionEvent e)->
		{
			switchToHealthProfile();
		});
		fade.play();
	}
	public void fadeOutToLoginScreen()
	{
		FadeTransition fade= new FadeTransition();
		fade.setDuration(Duration.millis(200));
		fade.setNode(beginvb);
		fade.setFromValue(1);
		fade.setToValue(0);
		fade.setOnFinished((ActionEvent e)->
		{
			switchToLoginScreen();
		});
		fade.play();
	}
	
}
