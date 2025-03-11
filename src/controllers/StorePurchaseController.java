package controllers;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import application.Product;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import utils.DatabaseConnection;

public class StorePurchaseController extends TransitionUtils implements Initializable{
	@FXML private StackPane return_btn = new StackPane();
	@FXML private HBox roothb = new HBox();
	
	@FXML Label medname = new Label();
	@FXML Label meddose = new Label();
	@FXML Label medamt = new Label();
	@FXML Label medprice = new Label();
	@FXML TextField quantity = new TextField();
	@FXML Label calc_total = new Label();
	
	@FXML ImageView prodimg = new ImageView();

	
	@FXML private Button addcart = new Button();
	
	public void setData(Product p)
	{
		medname.setText(p.getName());
		meddose.setText(p.getDose()+p.getDoseUnit());
		medamt.setText(p.getAmount());
		medprice.setText("Tk. "+String.valueOf(p.getPrice())+"/unit");
		prodimg.setImage(new Image(getClass().getResourceAsStream(p.getImg())));
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
    		String getMedProductID = "SELECT product_id FROM medicine WHERE medname ="+medname;
    		int prodID;
    		
    		Connection con = DatabaseConnection.connect();
    		try {
				PreparedStatement st = con.prepareStatement(getMedProductID);
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
