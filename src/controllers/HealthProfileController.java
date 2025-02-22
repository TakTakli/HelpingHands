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
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class HealthProfileController implements Initializable {
	@FXML private TextField fname=new TextField();
	@FXML private TextField lname=new TextField();
	@FXML private TextField phone=new TextField();
	@FXML private TextField mail=new TextField();
	@FXML private TextField otherdis=new TextField();
	@FXML private TextField otherallergy=new TextField();
	@FXML private Button submit = new Button();
	@FXML private VBox rootvb = new VBox(); 
	

	
	void getInputFromField()
	{
	
	}
	
	void getSelectedCheckBox()
	{
		
	}
	
	public void switchToSignupCheck()
	{
		Parent root;
		try 
		{
			root = FXMLLoader.load(getClass().getResource("/controllers/SignupOrNot.fxml"));
			Scene scene = new Scene(root);
			Stage stage= (Stage)rootvb.getScene().getWindow();
			stage.setScene(scene);
			stage.show();
			
		} 
		catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	public void fadeOutToSignup()
	{
		FadeTransition fade= new FadeTransition();
		fade.setDuration(Duration.millis(1000));
		fade.setNode(rootvb);
		fade.setFromValue(1);
		fade.setToValue(0);
		fade.setOnFinished((ActionEvent e)->
		{
			switchToSignupCheck();
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
