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

public class OnboardingController4 extends TransitionUtils implements Initializable{

	@FXML private Button getstarted= new Button();
	@FXML private Line arrowbase = new Line();
	@FXML private Line arrowtop = new Line();
	@FXML private Line arrowbot = new Line();
	@FXML private VBox vb4 = new VBox();
		
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
	
	public void getStartedButtonClick(MouseEvent e)
	{
		getstarted.setStyle(
				  "-fx-text-fill:#044dbb;"+
				  "-fx-background-color: #fff;"+
				  "-fx-border-color: #044dbb;"
				);
		setArrowBlue();
	}
	public void getStartedButtonRelease(MouseEvent e)
	{
		getstarted.setStyle(
				  "-fx-text-fill:#fff;"+
				  "-fx-background-color: #044dbb;"
				);
		setArrowWhite();
	}
	
	public void getStartedButtonHover(MouseEvent e)
	{
		getstarted.setStyle(
				  "-fx-background-color: #bcdcfa;"+
				  "-fx-text-fill:#044dbb;"
				);
		setArrowBlue();
	}
	public void getStartedButtonExit(MouseEvent e)
	{
		getstarted.setStyle(
				"-fx-background-color: #044dbb;"+
						"-fx-text-fill:#fff;"
				);
		setArrowWhite();
	}
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		 vb4.setOpacity(0);
	     fadeInToScene(vb4);
	     getstarted.setOnAction((e)->
	     {
	    	fadeOutToScene(vb4, "AccountExistOrNot"); 
	     });
	}
	
}
