package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

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
	@FXML private Button mapview_btn = new Button();
	@FXML private Button exercise_btn = new Button();
	
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        roothb.setOpacity(0);
        fadeInToScene(roothb);
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
        mapview_btn.setOnAction((e)->{
        	fadeOutToScene(roothb, "Map");
        });
        exercise_btn.setOnAction((e)->{
        	fadeOutToScene(roothb, "ExerciseGuide");
        });
        
    }

}
