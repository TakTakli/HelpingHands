package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class AccountExistingController extends TransitionUtils implements Initializable{
	
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
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		 beginvb.setOpacity(0);
	     fadeInToScene(beginvb);
	     login.setOnAction((e)->
	     {
	    	fadeOutToScene(beginvb, "Login"); 
	     });
	     proceed.setOnAction((e)->
	     {
	    	 fadeOutToScene(beginvb, "HealthProfile"); 
	     });
		
	}
	
}
