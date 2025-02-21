package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class loginpagecontrol implements Initializable{
	
 @FXML
 private Button signup;
 @FXML
 private Button Login;
 @FXML
 private TextField Username;
 @FXML
 private TextField Email;
 @FXML
 private TextField Password;
 
 public void intitalize(URL location,ResourceBundle Resources)
	{
		
	 	Login.setOnAction(new EventHandler<ActionEvent>() {
		    @Override
		    public void handle(ActionEvent event) {
		      
		      
		            Utils.signUpUser(event,Username.getText(),Email.getText(), Password.getText());
		        
		}
	 	});

		signup.setOnAction(new EventHandler<ActionEvent>() {
		    @Override
		    public void handle(ActionEvent event) {
		       Utils.changeScene(event, "signup.fxml", "Sign UP!", null, null);
		    }
		});

	}

@Override
public void initialize(URL arg0, ResourceBundle arg1) {
	// TODO Auto-generated method stub
	
}
 

 
}
