package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class signupcontrol implements Initializable{
	
 @FXML
 private Button Signup;
 @FXML
 private Button login;
 @FXML
 private TextField Username;
 @FXML
 private TextField Email;
 @FXML
 private TextField Password;
 
 public void intitalize(URL location,ResourceBundle Resources)
	{
		
	 	Signup.setOnAction(new EventHandler<ActionEvent>() {
		    @Override
		    public void handle(ActionEvent event) {
		      
		        if (!Username.getText().trim().isEmpty()&&!Email.getText().trim().isEmpty() && !Password.getText().trim().isEmpty()) {
		            Utils.signUpUser(event,Username.getText(),Email.getText(), Password.getText());
		        } else {
		            System.out.println("Please fill in all information");
		            Alert alert = new Alert(Alert.AlertType.ERROR);
		            alert.setContentText("Please fill in all information to sign up!");
		            alert.show();
		        }
		    }
		});

		login.setOnAction(new EventHandler<ActionEvent>() {
		    @Override
		    public void handle(ActionEvent event) {
		       Utils.changeScene(event, "sample.fxml", "Log in!", null, null);
		    }
		});

	}
 
@Override
public void initialize(URL arg0, ResourceBundle arg1) {
	// TODO Auto-generated method stub
	
}
 
}
