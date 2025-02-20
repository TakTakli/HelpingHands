package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class Onboarding1_Controller {
	@FXML private Button next= new Button();
	@FXML private Line arrowbase = new Line();
	@FXML private Line arrowtop = new Line();
	@FXML private Line arrowbot = new Line();
	
	public void buttonclick(MouseEvent e)
	{
		arrowbase.setStroke(Color.WHITE);
		arrowtop.setStroke(Color.WHITE);
		arrowbot.setStroke(Color.WHITE);
	}
	
	public void buttonrelease(MouseEvent e)
	{
		arrowbase.setStroke(Color.web("#044dbb"));
		arrowtop.setStroke(Color.web("#044dbb"));
		arrowbot.setStroke(Color.web("#044dbb"));
	}
}
