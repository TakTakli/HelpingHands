package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class UserProfileController extends TransitionUtils implements Initializable {
	@FXML private HBox roothb = new HBox();      
	@FXML private Button viewhealth_btn= new Button();    
	@FXML private Button logout_btn= new Button();    
	
	@Override
	    public void initialize(URL url, ResourceBundle rb) {
	        roothb.setOpacity(0);
	        fadeInToScene(roothb);
	        viewhealth_btn.setOnAction((e)->{
	        	fadeOutToScene(roothb, "HealthProfile");
	        });
	        logout_btn.setOnAction((e)->{
	        	fadeOutToScene(roothb, "Login");
	        });
	        
	        initializeSidebar(roothb);
	    }
	
    
}
