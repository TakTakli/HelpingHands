package controllers;

import java.io.IOException;
import java.net.URL;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
//import javafx.scene.layout.FlowPane;
//import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import utils.Appointment;
import utils.DatabaseUtil;
import utils.UserSession;

public class CalendarController extends TransitionUtils implements Initializable {
	
	@FXML private StackPane return_btn = new StackPane();
	@FXML private HBox roothb = new HBox();
	
	
	//modify calendar
	@FXML private Label monthyear = new Label();
	@FXML private Button prev_month = new Button();
	@FXML private Button next_month = new Button();
	@FXML private TilePane calendarflow = new TilePane();
	@FXML private VBox appointmentContainer=new VBox();
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
		
		 updateAppointmentList();
		 
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
	        String year = String.valueOf(dateFocus.getYear());
	        String month = String.valueOf(dateFocus.getMonth());
	        monthyear.setText(month + ", " + year);

	        int maxMonthLength = dateFocus.getMonth().maxLength();
	        if (dateFocus.getYear() % 4 != 0 && maxMonthLength == 29) {
	            maxMonthLength = 28;
	        }
	        int dateOffset = ZonedDateTime.of(dateFocus.getYear(), dateFocus.getMonthValue(), 1, 0, 0, 0, 0, dateFocus.getZone()).getDayOfWeek().getValue();

	        for (int i = 0; i < 6; i++) {
	            for (int j = 0; j < 7; j++) {
	                Button date = new Button();
	                date.setStyle(
	                    "-fx-background-color: transparent;" +
	                    "-fx-text-fill: #1d2366;" +
	                    "-fx-font-size: 24;" +
	                    "-fx-font-family: 'Plus Jakarta Sans SemiBold';"
	                );

	                int calcDate = (j + 1) + (7 * i);
	                if (calcDate > dateOffset) {
	                    int currDate = calcDate - dateOffset;
	                    if (currDate <= maxMonthLength) {
	                        date.setText(String.valueOf(currDate));

	                        // Check if the date is today
	                        if (today.getYear() == dateFocus.getYear() && today.getMonth() == dateFocus.getMonth() && today.getDayOfMonth() == currDate) {
	                            date.setStyle(
	                                "-fx-background-color: #044dbb;" +
	                                "-fx-text-fill: #fff;" +
	                                "-fx-font-size: 24;" +
	                                "-fx-font-family: 'Plus Jakarta Sans SemiBold';" +
	                                "-fx-border-radius: 4;" +
	                                "-fx-background-radius: 4;"
	                            );
	                        }

	                        // Check if the date has appointments
	                        List<Appointment> appointments = getAppointments(currDate, dateFocus.getMonthValue(), dateFocus.getYear());
	                        if (!appointments.isEmpty()) {
	                            date.setStyle(
	                            		 "-fx-background-color: #90D5FF;" +
	     	                                    "-fx-text-fill: #044dbb;" +
	     	                                    "-fx-font-size: 24;" +
	     	                                    "-fx-font-family: 'Plus Jakarta Sans SemiBold';" +
	     	                                    "-fx-border-radius: 50%;" +
	     	                                    "-fx-background-radius: 50%;"
	                            );

	                            // Add a tooltip to show the appointments
	                            StringBuilder tooltipText = new StringBuilder("Appointments:\n");
	                            for (Appointment appt : appointments) {
	                                tooltipText.append(appt.getType()).append(" at ").append(appt.getTime()).append("\n");
	                            }
	                            Tooltip tooltip = new Tooltip(tooltipText.toString());
	                            Tooltip.install(date, tooltip);
	                        }

	                        // Add hover effect
	                        date.setOnMouseEntered(event -> {
	                            date.setStyle(
	                            		 "-fx-background-color: lightblue;" +
	     	                                    "-fx-text-fill: #000000;" +
	     	                                    "-fx-font-size: 24;" +
	     	                                    "-fx-font-family: 'Plus Jakarta Sans SemiBold';" +
	     	                                    "-fx-border-radius: 50%;" +
	     	                                    "-fx-background-radius: 50%;"
	                            );
	                        });

	                        date.setOnMouseExited(event -> {
	                            // Restore the original style
	                            if (today.getYear() == dateFocus.getYear() && today.getMonth() == dateFocus.getMonth() && today.getDayOfMonth() == currDate) {
	                                date.setStyle(
	                                    "-fx-background-color: #044dbb;" +
	                                    "-fx-text-fill: #fff;" +
	                                    "-fx-font-size: 24;" +
	                                    "-fx-font-family: 'Plus Jakarta Sans SemiBold';" +
	                                    "-fx-border-radius: 4;" +
	                                    "-fx-background-radius: 4;"
	                                );
	                            } else if (!appointments.isEmpty()) {
	                                date.setStyle(
	                                    "-fx-background-color: #90D5FF;" +
	                                    "-fx-text-fill: #044dbb;" +
	                                    "-fx-font-size: 24;" +
	                                    "-fx-font-family: 'Plus Jakarta Sans SemiBold';" +
	                                    "-fx-border-radius: 50%;" +
	                                    "-fx-background-radius: 50%;"
	                                );
	                            } else {
	                                date.setStyle(
	                                    "-fx-background-color: transparent;" +
	                                    "-fx-text-fill: #1d2366;" +
	                                    "-fx-font-size: 24;" +
	                                    "-fx-font-family: 'Plus Jakarta Sans SemiBold';"
	                                );
	                            }
	                        });

	                        date.setOnAction(event -> {
	                            showAppointmentPopup(currDate, dateFocus.getMonthValue(), dateFocus.getYear());
	                        });
	                    }
	                }
	                calendarflow.getChildren().add(date);
	            }
	        }
	    }

	    private List<Appointment> getAppointments(int day, int month, int year) {
	        int userId = UserSession.getInstance().getUserId();
	        return DatabaseUtil.getAppointments(userId, day, month, year);
	    }

	    private void showAppointmentPopup(int day, int month, int year) {
	        try {
	            FXMLLoader loader = new FXMLLoader(getClass().getResource("CalendarPOPUP.fxml"));
	            Parent root = loader.load();

	          
	            PopupController controller = loader.getController();
	            controller.initPopup(day, month, year, this); 

	            
	            Stage stage = new Stage();
	            stage.setScene(new Scene(root));
	            stage.setTitle("Set Appointment");
	            stage.show();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	    public void updateAppointmentList() {
	        int userId = UserSession.getInstance().getUserId();
	        List<Appointment> appointments = DatabaseUtil.getAppointmentsForUser(userId); // Fetch all appointments for the user

	        appointmentContainer.getChildren().clear(); 

	        if (appointments.isEmpty()) {
	            Label noAppointmentsLabel = new Label("Nothing Due");
	            noAppointmentsLabel.setStyle("-fx-font-size: 24; -fx-font-weight: bold;");
	            appointmentContainer.getChildren().add(noAppointmentsLabel);
	        } else {
	            for (Appointment appt : appointments) {
	                
	                String appointmentText = "Day " + appt.getDay() + ": " + appt.getType() + " at " + appt.getTime();
	                Label appointmentLabel = new Label(appointmentText);
	                appointmentLabel.setStyle("-fx-font-size: 24; -fx-font-weight: bold;");
	                appointmentContainer.getChildren().add(appointmentLabel);
	            }
	        }
	    }
	}
	
