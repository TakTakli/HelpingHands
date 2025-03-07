package controllers;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class SignupCheckController extends TransitionUtils implements Initializable {
	@FXML private Button signup = new Button();
	@FXML private Button imgood = new Button();
	@FXML private VBox beginvb = new VBox();
	
	@Override
	public void initialize(URL url, ResourceBundle rb)
	{
		beginvb.setOpacity(0);
		fadeInToScene(beginvb);
		signup.setOnAction((e)->
		{
			fadeOutToScene(beginvb, "Registration");
		});
		imgood.setOnAction((e)->
		{
			fadeOutToScene(beginvb, "Home");
		});		
	}
	
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

}
