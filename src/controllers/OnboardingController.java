package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class OnboardingController extends TransitionUtils implements Initializable{
	@FXML private Button next= new Button();
	@FXML private Line arrowbase = new Line();
	@FXML private Line arrowtop = new Line();
	@FXML private Line arrowbot = new Line();
	@FXML private VBox vb1 = new VBox();
		
	private void setArrowWhite()
	{
		arrowbase.setStroke(Color.WHITE);
		arrowtop.setStroke(Color.WHITE);
		arrowbot.setStroke(Color.WHITE);
	}
	
	private void setArrowBlue()
	{
		arrowbase.setStroke(Color.web("#044dbb"));
		arrowtop.setStroke(Color.web("#044dbb"));
		arrowbot.setStroke(Color.web("#044dbb"));
	}
	
	public void ButtonClicked(MouseEvent e)
	{
		next.setStyle(
				"-fx-background-color: #044dbb;"+
				"-fx-text-fill: #fff;");
		this.setArrowWhite();
	}
	
	public void ButtonReleased(MouseEvent e)
	{
		next.setStyle(
				"-fx-background-color: #fff;"+
				"-fx-text-fill: #044dbb;");
		this.setArrowBlue();
	}
	
	public void ButtonHover(MouseEvent e)
	{
		next.setStyle(
				  "-fx-background-color: #bcdcfa;"+
				  "-fx-text-fill:#044dbb;"
				);
		this.setArrowBlue();
	}
	public void ButtonExit(MouseEvent e)
	{
		next.setStyle("-fx-background-color: #fff");
	}
	
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		 vb1.setOpacity(0);
	     fadeInToScene(vb1);
	     next.setOnAction((e)->
	     {
	    	fadeOutToScene(vb1, "OnboardingScreen2"); 
	     });

	}
	
}
