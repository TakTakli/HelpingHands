package controllers;

import java.io.IOException;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;

public class OnboardingController {
	@FXML private Button next= new Button();
	@FXML private Button getstarted= new Button();
	@FXML private Line arrowbase = new Line();
	@FXML private Line arrowtop = new Line();
	@FXML private Line arrowbot = new Line();
	@FXML private VBox vb1 = new VBox();
	@FXML private VBox vb2 = new VBox();
	@FXML private VBox vb3 = new VBox();
	@FXML private VBox vb4 = new VBox();
	
	private FadeTransition fade=new FadeTransition();
	
	public void buttonClick(MouseEvent e)
	{
		arrowbase.setStroke(Color.WHITE);
		arrowtop.setStroke(Color.WHITE);
		arrowbot.setStroke(Color.WHITE);
	}
	
	public void buttonRelease(MouseEvent e)
	{
		arrowbase.setStroke(Color.web("#044dbb"));
		arrowtop.setStroke(Color.web("#044dbb"));
		arrowbot.setStroke(Color.web("#044dbb"));
	}
	
	public void finalButtonClick(MouseEvent e)
	{
		getstarted.setStyle(
				  "-fx-text-fill:#044dbb;"+
				  "-fx-background-color: #fff;"+
				  "-fx-border-color: #044dbb;"
				);
		arrowbase.setStroke(Color.web("#044dbb"));
		arrowtop.setStroke(Color.web("#044dbb"));
		arrowbot.setStroke(Color.web("#044dbb"));
	}
	public void finalButtonRelease(MouseEvent e)
	{
		getstarted.setStyle(
				  "-fx-text-fill:#fff;"+
				  "-fx-background-color: #044dbb;"
				);
		arrowbase.setStroke(Color.WHITE);
		arrowtop.setStroke(Color.WHITE);
		arrowbot.setStroke(Color.WHITE);
	}
	
	public void fadeout1()
	{
		fade.setDuration(Duration.millis(200));
		fade.setNode(vb1);
		fade.setFromValue(1);
		fade.setToValue(0);
		fade.setOnFinished((ActionEvent e)->
		{
			switchToScene2();
		});
		fade.play();
	}
	public void fadeout2()
	{
		fade.setDuration(Duration.millis(200));
		fade.setNode(vb2);
		fade.setFromValue(1);
		fade.setToValue(0);
		fade.setOnFinished((ActionEvent e)->
		{
			switchToScene3();
		});
		fade.play();
	}
	public void fadeout3()
	{
		fade.setDuration(Duration.millis(200));
		fade.setNode(vb3);
		fade.setFromValue(1);
		fade.setToValue(0);
		fade.setOnFinished((ActionEvent e)->
		{
			switchToScene4();
		});
		fade.play();
	}
	public void fadeout4()
	{
		fade.setDuration(Duration.millis(200));
		fade.setNode(vb4);
		fade.setFromValue(1);
		fade.setToValue(0);
		fade.setOnFinished((ActionEvent e)->
		{
			switchToHealthProfile();
		});
		fade.play();
	}
	
	public void switchToScene2()
	{
		Parent root;
		try 
		{
			root = FXMLLoader.load(getClass().getResource("/controllers/OnboardingScreen2.fxml"));
			Scene scene = new Scene(root);
			Stage stage= (Stage)vb1.getScene().getWindow();
			stage.setScene(scene);
//			stage.setFullScreen(true);
			stage.show();
			
		} 
		catch (IOException e1) {
			e1.printStackTrace();
		}

	}
	public void switchToScene3()
	{
		Parent root;
		try 
		{
			root = FXMLLoader.load(getClass().getResource("/controllers/OnboardingScreen3.fxml"));
			Scene scene = new Scene(root);
			Stage stage= (Stage)vb2.getScene().getWindow();
			stage.setScene(scene);
//			stage.setFullScreen(true);
			stage.show();
			
		} 
		catch (IOException e1) {
			e1.printStackTrace();
		}

	}
	public void switchToScene4()
	{
		Parent root;
		try 
		{
			root = FXMLLoader.load(getClass().getResource("/controllers/OnboardingScreen4.fxml"));
			Scene scene = new Scene(root);
			Stage stage= (Stage)vb3.getScene().getWindow();
			stage.setScene(scene);
//			stage.setFullScreen(true);
			stage.show();
			
		} 
		catch (IOException e1) {
			e1.printStackTrace();
		}

	}
	public void switchToHealthProfile()
	{
		Parent root;
		try 
		{
			root = FXMLLoader.load(getClass().getResource("/controllers/HealthProfile.fxml"));
			Scene scene = new Scene(root);
			Stage stage= (Stage)vb4.getScene().getWindow();
			stage.setScene(scene);
//			stage.setFullScreen(true);
			stage.show();
			
		} 
		catch (IOException e1) {
			e1.printStackTrace();
		}

	}
	
}
