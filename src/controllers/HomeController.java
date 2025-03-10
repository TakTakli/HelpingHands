package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import utils.UserSession;

public class HomeController extends TransitionUtils implements Initializable{
	@FXML private Label noAppointment = new Label();
	@FXML private Label nothingDue = new Label();
	@FXML private HBox roothb = new HBox(); 
	@FXML private Button profile_btn = new Button();
	@FXML private Button settings_btn = new Button();
	@FXML private Button caldetails_btn = new Button();
	@FXML private Button medtracker_btn = new Button();
	@FXML private Button mealplan_btn = new Button();
	@FXML private Button shop_btn = new Button();
//	@FXML private Button mapview_btn = new Button();
	@FXML private Button exercise_btn = new Button();
	@FXML private Label Welcome=new Label();
	
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        roothb.setOpacity(0);
        fadeInToScene(roothb);
        
        UserSession userSession = UserSession.getInstance();
        String username = userSession.getUsername();
        if (username!= null) {
            Welcome.setText("Welcome, "+username+"!");
          
        } else {
            Welcome.setText("Welcome!"); 
        }
        
        profile_btn.setOnAction((e)->{
        	fadeOutToScene(roothb, "UserProfile");
        });
        settings_btn.setOnAction((e)->{
        	fadeOutToScene(roothb, "Settings");
        });
        caldetails_btn.setOnAction((e)->{
        	fadeOutToScene(roothb, "Calendar");
        });
        medtracker_btn.setOnAction((e)->{
        	fadeOutToScene(roothb, "MedTracker1");
        });
        mealplan_btn.setOnAction((e)->{
        	fadeOutToScene(roothb, "MealPlanner");
        });
        shop_btn.setOnAction((e)->{
        	fadeOutToScene(roothb, "StoreMain");
        });
//        mapview_btn.setOnAction((e)->{
//        	fadeOutToScene(roothb, "Map");
//        });
        exercise_btn.setOnAction((e)->{
        	fadeOutToScene(roothb, "ExerciseGuide");
        });
        
    }

}
