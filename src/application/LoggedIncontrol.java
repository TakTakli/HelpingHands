package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public  class LoggedIncontrol implements Initializable{
	@FXML
	private Button logout;
	@FXML
	private Label  welcome;
	public void intitalize(URL location,ResourceBundle Resources)
	{
		
		logout.setOnAction(new EventHandler<ActionEvent>()
				{
			@Override
			public void handle(ActionEvent event)
			{
			Utils.changeScene(event,"sample.fxml","Login", null,null);
			}
	    }

);
	}
	public void setUserInfo(String Username)
	{
		welcome.setText("Welcome"+Username+"!");
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
}
	

