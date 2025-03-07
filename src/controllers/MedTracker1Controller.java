package controllers;

import javafx.fxml.Initializable;
import java.net.URL;
import java.time.ZonedDateTime;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

public class MedTracker1Controller extends TransitionUtils implements Initializable{
	@FXML private StackPane return_btn = new StackPane();
	@FXML private HBox roothb = new HBox();
	
//	//mini calendar
	@FXML private Label TodayDate = new Label();
	private String dayofweek, day, month; 
	
//	@FXML private Label sundate = new Label();
//	@FXML private Label mondate = new Label();
//	@FXML private Label tuedate = new Label();
//	@FXML private Label weddate = new Label();
//	@FXML private Label thurdate = new Label();
//	@FXML private Label fridate = new Label();
//	@FXML private Label satdate = new Label();
	
	@FXML private Button viewhistory_btn = new Button();
	@FXML private Button medadd_btn = new Button();
	
	//to be replaced with meds
	@FXML private Label duetxt = new Label();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		roothb.setOpacity(0);
        fadeInToScene(roothb);
        initializeSidebar(roothb);
		return_btn.setOnMouseClicked((e)->{
			fadeOutToScene(roothb, "Home");
		});
		viewhistory_btn.setOnAction((e)->{
			fadeOutToScene(roothb, "MedHistory");
		});
		medadd_btn.setOnAction((e)->{
			fadeOutToScene(roothb, "MedicineAdd");
		});
		
		todaysDate();
		
	}
	
	private void todaysDate()
	{
		ZonedDateTime today = ZonedDateTime.now();
		dayofweek=String.valueOf(today.getDayOfWeek());
		day = String.valueOf(today.getDayOfMonth());
		month = String.valueOf(today.getMonth());
		TodayDate.setText(dayofweek+", "+day+" "+month);
	}
	
}
