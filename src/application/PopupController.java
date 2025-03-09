package application;

import java.awt.Button;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class PopupController {
	
	@FXML private ChoiceBox<String> appoint_choice = new ChoiceBox<String>();
	@FXML private ChoiceBox<String> amorpm = new ChoiceBox<String>();
	@FXML private TextField timehour = new TextField();
	@FXML private TextField timemin = new TextField();
	@FXML private Button appoint_set = new Button();
	
	
	
	public void initPopup()
	{
		
	}
}
