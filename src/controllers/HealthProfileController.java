package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class HealthProfileController extends TransitionUtils implements Initializable {
	@FXML private TextField fname=new TextField();
	@FXML private TextField lname=new TextField();
	@FXML private TextField phone=new TextField();
	@FXML private TextField mail=new TextField();
	@FXML private TextField otherdis=new TextField();
	@FXML private TextField otherallergy=new TextField();
	@FXML private Button submit = new Button();
	@FXML private VBox rootvb = new VBox(); 
	
	@Override
	public void initialize(URL url, ResourceBundle rb)
	{
		rootvb.setOpacity(0);
		fadeInToScene(rootvb);
		submit.setOnAction((e)->
		{
			fadeOutToScene(rootvb, "SignUpOrNot");
		});
	}
}
