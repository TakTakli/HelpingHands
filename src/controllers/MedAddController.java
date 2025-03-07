package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

public class MedAddController extends TransitionUtils implements Initializable{
	@FXML private StackPane return_btn = new StackPane();
	@FXML private HBox roothb = new HBox();
	
	//choose medicine type//
	@FXML private Button bottle = new Button();
	@FXML private Button tablet = new Button();
	@FXML private Button capsule = new Button();
	@FXML private Button syringe = new Button();
	@FXML private Button inhaler = new Button();
	
	//input details//
	@FXML private TextField med_name = new TextField(); 
	@FXML private TextField dosetxt = new TextField();
	@FXML private ChoiceBox<String> DoseBox = new ChoiceBox<String>();
	private String[] doseList = {"mg", "g", "mL", "IU"};
	
	@FXML private TextField hour = new TextField();
	@FXML private TextField min = new TextField();
	
	@FXML private ChoiceBox<String> AmPmBox = new ChoiceBox<String>();
	private String[] timeList = {"AM", "PM"};
	
	
	@FXML private Button addbtn = new Button(); //db connection
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		roothb.setOpacity(0);
        fadeInToScene(roothb);
        initializeSidebar(roothb);
		return_btn.setOnMouseClicked((e)->{
			fadeOutToScene(roothb, "MedTracker1");
		});
		DoseBox.getItems().addAll(doseList);
		AmPmBox.getItems().addAll(timeList);
	}
}
