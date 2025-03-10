package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class ExerciseController extends TransitionUtils implements Initializable{
	@FXML private StackPane return_btn = new StackPane();
	@FXML private HBox roothb = new HBox();
	
	@FXML private VBox walktab = new VBox();
	@FXML private VBox balancetab = new VBox();
	@FXML private VBox legtab = new VBox();
	@FXML private VBox backtab = new VBox();
	@FXML private VBox stretchtab = new VBox();
	
	@FXML private Button walkbtn = new Button();
	@FXML private Button balancebtn = new Button();
	@FXML private Button legbtn = new Button();
	@FXML private Button backbtn = new Button();
	@FXML private Button stretchbtn = new Button();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		roothb.setOpacity(0);
        fadeInToScene(roothb);
        initializeSidebar(roothb);
		return_btn.setOnMouseClicked((e)->{
			fadeOutToScene(roothb, "Home");
		});
		
		
		walkbtn.setOnAction((e)->{
			walktab.setVisible(true);
			balancetab.setVisible(false);
			legtab.setVisible(false);
			backtab.setVisible(false);
			stretchtab.setVisible(false);
		});
		balancebtn.setOnAction((e)->{
			walktab.setVisible(false);
			balancetab.setVisible(true);
			legtab.setVisible(false);
			backtab.setVisible(false);
			stretchtab.setVisible(false);
		});
		legbtn.setOnAction((e)->{
			walktab.setVisible(false);
			balancetab.setVisible(false);
			legtab.setVisible(true);
			backtab.setVisible(false);
			stretchtab.setVisible(false);
		});
		backbtn.setOnAction((e)->{
			walktab.setVisible(false);
			balancetab.setVisible(false);
			legtab.setVisible(false);
			backtab.setVisible(true);
			stretchtab.setVisible(false);
		});
		stretchbtn.setOnAction((e)->{
			walktab.setVisible(false);
			balancetab.setVisible(false);
			legtab.setVisible(false);
			backtab.setVisible(false);
			stretchtab.setVisible(true);
		});
		
	}

}
