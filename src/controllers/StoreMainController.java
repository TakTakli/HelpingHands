package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import application.Equipment;
import application.Product;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import utils.DatabaseConnection;
import utils.ProductListener;

public class StoreMainController extends TransitionUtils implements Initializable{
	
	@FXML private StackPane return_btn = new StackPane();
	@FXML private HBox roothb = new HBox();
	
	@FXML private Button viewcart = new Button();
	@FXML private Button medicine = new Button();
	@FXML private Button equipment = new Button();
	@FXML private Button search_btn = new Button();
	
	@FXML private TextField searchbar = new TextField();
	
	@FXML private FlowPane itemflow = new FlowPane();
	
	private ProductListener<Product> medlistener;
	private ProductListener<Equipment> eqlistener;
	private List<Product> meds = new ArrayList<>();
	private List<Equipment> eq = new ArrayList<>();
	
	
	private void getMedData()
	{
		String sql = "SELECT * FROM medicine";
		try(
			Connection con = DatabaseConnection.connect();
			PreparedStatement statement = con.prepareStatement(sql);	
			)
				{
					ResultSet rs = statement.executeQuery();
					while(rs.next())
					{
						Product product = new Product(rs.getString("medname"), rs.getString("img"), rs.getString("amount"), rs.getString("doseunit"), rs.getInt("dose"), rs.getDouble("pricePerUnit"));
						meds.add(product);
					}
					con.close();
				}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	private void getEqData()
	{
		String sql = "SELECT * FROM equipment";
		try(
			Connection con = DatabaseConnection.connect();
			PreparedStatement statement = con.prepareStatement(sql);	
			)
				{
					ResultSet rs = statement.executeQuery();
					while(rs.next())
					{
						Equipment product = new Equipment(rs.getString("name"), rs.getString("img"), rs.getDouble("price"));
						eq.add(product);
					}
					con.close();
				}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	
	private void drawMedFlow()
	{
		for(int i=0;i<meds.size();i++)
		{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/controllers/Item.fxml"));
			VBox root = null;
			try {
				root = loader.load();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			ItemController itemcontroller = loader.getController();
			itemcontroller.setItemData(meds.get(i), medlistener);
			
			itemflow.getChildren().add(root);
		}
	}
	
	private void drawEqFlow()
	{
		for(int i=0;i<eq.size();i++)
		{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/controllers/EquipmentItem.fxml"));
			VBox root = null;
			try {
				root = loader.load();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			EquipmentController eqcontroller = loader.getController();
			eqcontroller.setItemData(eq.get(i), eqlistener);
			
			itemflow.getChildren().add(root);
		}
	}
	
	
	private void setSelectedMed(Product p)
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/controllers/PurchasePage.fxml"));
		StorePurchaseController spc = loader.getController();
		spc.setData(p);
	}
	
	private void setSelectedEq(Equipment e)
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/controllers/EquipmentPurchase.fxml"));
		EquipmentPurchaseController eqpc = loader.getController();
		eqpc.setData(e);
	}
	
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
		
		medlistener = new ProductListener<Product>()
				{
					@Override
					public void selectedItemListener(Product p)
					{
						setSelectedMed(p);
					}
				};
		eqlistener = new ProductListener<Equipment>()
				{
					@Override
					public void selectedItemListener(Equipment eq)
					{
						setSelectedEq(eq);
					}
				};
		
		getMedData();
		getEqData();
		drawMedFlow();
		medicine.setOnAction((e)->{
			itemflow.getChildren().clear();
			drawMedFlow();
		});
		equipment.setOnAction((e)->{
			itemflow.getChildren().clear();
			drawEqFlow();
		});
//		search_btn.setOnAction((e)->{
//			//db connection
//		});
		
	}
	
	
	
}
