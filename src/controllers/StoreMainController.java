package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

public class StoreMainController extends TransitionUtils implements Initializable{
	
	@FXML private StackPane return_btn = new StackPane();
	@FXML private HBox roothb = new HBox();
	
	@FXML private Button viewcart = new Button();
	@FXML private Button medicine = new Button();
	@FXML private Button equipment = new Button();
	@FXML private Button search_btn = new Button();
	
	@FXML private TextField searchbar = new TextField();
	
	@FXML private FlowPane medflow = new FlowPane();
	@FXML private FlowPane eqflow = new FlowPane();
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		roothb.setOpacity(0);
        fadeInToScene(roothb);
        initializeSidebar(roothb);
		return_btn.setOnMouseClicked((e)->{
			fadeOutToScene(roothb, "Home");
		});
		viewcart.setOnAction((e)->{
			fadeOutToScene(roothb, "StoreCart");
		});
		medicine.setOnAction((e)->{
			eqflow.setVisible(false);
			medflow.setVisible(true);
		});
		equipment.setOnAction((e)->{
			medflow.setVisible(false);
			eqflow.setVisible(true);
		});
		search_btn.setOnAction((e)->{
			//db connection
		});
		
	}
	
	
	
}
