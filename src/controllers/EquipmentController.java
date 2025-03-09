package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import application.Equipment;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import utils.ProductListener;

public class EquipmentController implements Initializable {
	
	@FXML private Label eqnamelabel=new Label();
	@FXML private Label eqprice=new Label();
	
	@FXML private Button buybtn = new Button();
	@FXML private ImageView prodimg = new ImageView();
	
	private Equipment product;
	private ProductListener<Equipment> clickListener;
	
	public void setItemData(Equipment p, ProductListener<Equipment> l)
	{
		//associate passed product and listener to item to pass the data to the second scene later
		product = p;
		clickListener = l;
		
		//set item data to be displayed in flowpane//
		eqnamelabel.setText(product.getName());
		eqprice.setText("Tk. "+String.valueOf(product.getPrice()));
		prodimg.setImage(new Image(getClass().getResourceAsStream(product.getImg()))); 
		
	}
	
	private void buyBtnOnClick(ActionEvent e)
	{
		clickListener.selectedItemListener(product);
	}
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		buybtn.setOnAction((e)->{
			buyBtnOnClick(e);
		});
	}

}
