package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

public class CalendarController extends TransitionUtils implements Initializable {
	
	@FXML private StackPane return_btn = new StackPane();
	@FXML private HBox roothb = new HBox();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		roothb.setOpacity(0);
        fadeInToScene(roothb);
		return_btn.setOnMouseClicked((e)->{
			fadeOutToScene(roothb, "Home");
		});
	}
	
}
