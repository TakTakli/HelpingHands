package controllers;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import application.Equipment;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import utils.DatabaseConnection;

public class EquipmentPurchaseController extends TransitionUtils implements Initializable{
	@FXML private StackPane return_btn = new StackPane();
	@FXML private HBox roothb = new HBox();
	
	@FXML Label prodname = new Label();
	@FXML Label prodprice = new Label();
	@FXML ImageView prodimg = new ImageView();
	
	
	@FXML private Button addcart = new Button();

	public void setData(Equipment e)
	{
		prodname.setText(e.getName());
		prodprice.setText("Tk. "+String.valueOf(e.getPrice())+"/unit");
		prodimg.setImage(new Image(getClass().getResourceAsStream(e.getImg())));
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		roothb.setOpacity(0);
        fadeInToScene(roothb);
        initializeSidebar(roothb);
        
    	return_btn.setOnMouseClicked((e)->{
			fadeOutToScene(roothb, "StoreMain");
		});
    	
    	
    	addcart.setOnAction((e)->{
    		String getEqProductID = "SELECT product_id FROM equipment WHERE name ="+prodname;
    		int prodID;
    		
    		Connection con = DatabaseConnection.connect();
    		try {
				PreparedStatement st = con.prepareStatement(getEqProductID);
				ResultSet rs = st.executeQuery();
				rs.next();
				prodID = rs.getInt("product_id");
				
				String sql="";
				
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
    	});
	}
}
