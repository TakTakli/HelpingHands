package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class HomeController implements Initializable{
	@FXML private Label noAppointment = new Label();
	@FXML private Label nothingDue = new Label();
	@FXML private HBox roothb = new HBox(); 
	
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        roothb.setOpacity(0);
        fadeIn();
    }

    
    
    private void switchToCalendar() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/controllers/Calendar.fxml"));
            Stage stage = (Stage) roothb.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void switchToTracker() {
    	try {
    		Parent root = FXMLLoader.load(getClass().getResource("/controllers/MedTracker.fxml"));
    		Stage stage = (Stage) roothb.getScene().getWindow();
    		stage.setScene(new Scene(root));
    		stage.show();
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }
    private void switchToMeal() {
    	try {
    		Parent root = FXMLLoader.load(getClass().getResource("/controllers/MealPlanner.fxml"));
    		Stage stage = (Stage) roothb.getScene().getWindow();
    		stage.setScene(new Scene(root));
    		stage.show();
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }
    private void switchToStore() {
    	try {
    		Parent root = FXMLLoader.load(getClass().getResource("/controllers/Store.fxml"));
    		Stage stage = (Stage) roothb.getScene().getWindow();
    		stage.setScene(new Scene(root));
    		stage.show();
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }
    private void switchToMap() {
    	try {
    		Parent root = FXMLLoader.load(getClass().getResource("/controllers/Map.fxml"));
    		Stage stage = (Stage) roothb.getScene().getWindow();
    		stage.setScene(new Scene(root));
    		stage.show();
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }
    private void switchToGuide() {
    	try {
    		Parent root = FXMLLoader.load(getClass().getResource("/controllers/ExerciseGuide.fxml"));
    		Stage stage = (Stage) roothb.getScene().getWindow();
    		stage.setScene(new Scene(root));
    		stage.show();
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }
    private void switchToProfile() {
    	try {
    		Parent root = FXMLLoader.load(getClass().getResource("/controllers/UserProfile.fxml"));
    		Stage stage = (Stage) roothb.getScene().getWindow();
    		stage.setScene(new Scene(root));
    		stage.show();
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }
    private void switchToSettings() {
    	try {
    		Parent root = FXMLLoader.load(getClass().getResource("/controllers/Settings.fxml"));
    		Stage stage = (Stage) roothb.getScene().getWindow();
    		stage.setScene(new Scene(root));
    		stage.show();
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }
    
    public void fadeOutToCalendar() {
        FadeTransition fade = new FadeTransition(Duration.millis(200), roothb);
        fade.setFromValue(1);
        fade.setToValue(0);
        fade.setOnFinished(event -> switchToCalendar());
        fade.play();
    }
    public void fadeOutToTracker() {
    	FadeTransition fade = new FadeTransition(Duration.millis(200), roothb);
    	fade.setFromValue(1);
    	fade.setToValue(0);
    	fade.setOnFinished(event -> switchToTracker());
    	fade.play();
    }
    public void fadeOutToMeal() {
    	FadeTransition fade = new FadeTransition(Duration.millis(200), roothb);
    	fade.setFromValue(1);
    	fade.setToValue(0);
    	fade.setOnFinished(event -> switchToMeal());
    	fade.play();
    }
    public void fadeOutToStore() {
    	FadeTransition fade = new FadeTransition(Duration.millis(200), roothb);
    	fade.setFromValue(1);
    	fade.setToValue(0);
    	fade.setOnFinished(event -> switchToStore());
    	fade.play();
    }
    public void fadeOutToMap() {
    	FadeTransition fade = new FadeTransition(Duration.millis(200), roothb);
    	fade.setFromValue(1);
    	fade.setToValue(0);
    	fade.setOnFinished(event -> switchToMap());
    	fade.play();
    }
    public void fadeOutToGuide() {
    	FadeTransition fade = new FadeTransition(Duration.millis(200), roothb);
    	fade.setFromValue(1);
    	fade.setToValue(0);
    	fade.setOnFinished(event -> switchToGuide());
    	fade.play();
    }
    public void fadeOutToProfile() {
    	FadeTransition fade = new FadeTransition(Duration.millis(200), roothb);
    	fade.setFromValue(1);
    	fade.setToValue(0);
    	fade.setOnFinished(event -> switchToProfile());
    	fade.play();
    }
    public void fadeOutToSettings() {
    	FadeTransition fade = new FadeTransition(Duration.millis(200), roothb);
    	fade.setFromValue(1);
    	fade.setToValue(0);
    	fade.setOnFinished(event -> switchToSettings());
    	fade.play();
    }
    
	private void fadeIn() {
	       FadeTransition fade = new FadeTransition(Duration.millis(500), roothb);
	        fade.setFromValue(0);
	        fade.setToValue(1);
	        fade.play();
	}
	
	
}
