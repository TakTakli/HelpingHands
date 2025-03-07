package controllers;

import java.net.URL;
import java.time.ZonedDateTime;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
//import javafx.scene.layout.FlowPane;
//import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;

public class CalendarController extends TransitionUtils implements Initializable {
	
	@FXML private StackPane return_btn = new StackPane();
	@FXML private HBox roothb = new HBox();
	
	
	//modify calendar
	@FXML private Label monthyear = new Label();
	@FXML private Button prev_month = new Button();
	@FXML private Button next_month = new Button();
	@FXML private TilePane calendarflow = new TilePane();
	private String month, year; 
	
    ZonedDateTime dateFocus;
    ZonedDateTime today;
	
	//see appointments
	@FXML private Label duetxt = new Label();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		roothb.setOpacity(0);
        fadeInToScene(roothb);
        initializeSidebar(roothb);
		return_btn.setOnMouseClicked((e)->{
			fadeOutToScene(roothb, "Home");
		});
		
		
		dateFocus=ZonedDateTime.now();
		today=ZonedDateTime.now();
		drawCalendar();
		
		prev_month.setOnAction((ActionEvent e)->{
			prevMonth(e);
		});
		next_month.setOnAction((ActionEvent e)->{
			nextMonth(e);
		});
	}
	
    
    void prevMonth(ActionEvent event) {
        dateFocus = dateFocus.minusMonths(1);
        calendarflow.getChildren().clear();
        drawCalendar();
    }

    void nextMonth(ActionEvent event) {
        dateFocus = dateFocus.plusMonths(1);
        calendarflow.getChildren().clear();
        drawCalendar();
    }
    
    
	private void drawCalendar() {
		
		year = String.valueOf(dateFocus.getYear());
		month = String.valueOf(dateFocus.getMonth());
		monthyear.setText(month+", "+year);
		
		
//        double calendarWidth = calendarflow.getPrefWidth();
//        double calendarHeight = calendarflow.getPrefHeight();
//        double spacingH = calendarflow.getHgap();
//        double spacingV = calendarflow.getVgap();
		
		
		int maxMonthLength = dateFocus.getMonth().maxLength();
        if(dateFocus.getYear() % 4 != 0 && maxMonthLength == 29) //calculate february length
        {
            maxMonthLength = 28;
        }
        int date_offset = ZonedDateTime.of(dateFocus.getYear(), dateFocus.getMonthValue(), 1,0,0,0,0,dateFocus.getZone()).getDayOfWeek().getValue();
        
        
        
        for(int i=0;i<6;i++)
        {
        	for(int j=0;j<7;j++)
        	{
        		Button date = new Button();
        		date.setStyle(
        				"-fx-background-color: transparent;"+
        				"-fx-text-fill: #1d2366;"+
        				"-fx-font-size: 24;"+
        				"-fx-font-family: 'Plus Jakarta Sans SemiBold';"
        				);
//        		double buttonWidth = (calendarWidth/7)-spacingH;
//        		double buttonHeight = (calendarHeight/6)-spacingV;
//        		date.setPrefWidth(20);
//        		date.setPrefHeight(20);
        		
        		
        		int calcDate = (j+1)+(7*i);
        		if(calcDate > date_offset)
        		{
        			int curr_date = calcDate - date_offset;
        			if(curr_date<=maxMonthLength)
        			{
        				date.setText(String.valueOf(curr_date));
//        				calendarflow.getChildren().add(date);
        			}
        			if(today.getYear() == dateFocus.getYear() && today.getMonth() == dateFocus.getMonth() && today.getDayOfMonth() == curr_date)
            		{
                		date.setStyle(
                				"-fx-background-color: #044dbb;"+
                				"-fx-text-fill: #fff;"+
                  				"-fx-font-size: 24;"+
                				"-fx-font-family: 'Plus Jakarta Sans SemiBold';"+
                  				"fx-border-radius: 40;"+
                				"fx-background-radius: 40;"
                				);
            		}
        		}
        		calendarflow.getChildren().add(date);
        	}
        	
        }
	}
	
}
