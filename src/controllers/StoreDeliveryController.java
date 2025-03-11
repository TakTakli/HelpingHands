package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import application.Equipment;
import application.Product;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import utils.StoreDAO;

public class StoreDeliveryController extends TransitionUtils implements Initializable{
	@FXML private StackPane return_btn = new StackPane();
	@FXML private HBox roothb = new HBox();
	@FXML private Button orderplacebtn = new Button();
	@FXML private TextField address = new TextField();
	@FXML private TextField phoneno = new TextField();

//	private String productname;
//	private double total;
    private StoreDAO storeDAO = new StoreDAO();
	public void setData(Product p, int userid, int quantity, double total)
	{
		orderplacebtn.setOnAction((e)->{
		 boolean isSet = storeDAO.saveOrder(userid, p.getName(), quantity, total, address.getText(), phoneno.getText());
	        if (isSet) {
	            showAlert(Alert.AlertType.INFORMATION, "Success", "Order confirmed!");
	            // Redirect to Store view
	            fadeOutToScene(roothb, "StoreMain");
	        } else {
	            showAlert(Alert.AlertType.ERROR, "Error", "Failed to order. Please try again.");
	        }
		});
	}
	public void setData(Equipment e, int userid, int quantity, double total)
	{
		orderplacebtn.setOnAction((e1)->{
			boolean isSet = storeDAO.saveOrder(userid, e.getName(), quantity, total, address.getText(), phoneno.getText());
			if (isSet) {
				showAlert(Alert.AlertType.INFORMATION, "Success", "Order confirmed!");
				// Redirect to Store view
				fadeOutToScene(roothb, "StoreMain");
			} else {
				showAlert(Alert.AlertType.ERROR, "Error", "Failed to order. Please try again.");
			}
		});
	}
	
    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();}
        
	public void initialize(URL location, ResourceBundle resources) {
		
		roothb.setOpacity(0);
        fadeInToScene(roothb);
        initializeSidebar(roothb);
//        
//        orderplacebtn.setOnAction((e)->{
//        	
//        });
	}        
}
