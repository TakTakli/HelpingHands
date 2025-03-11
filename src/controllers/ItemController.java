package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import application.Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import utils.ProductListener;

public class ItemController extends TransitionUtils implements Initializable{
	@FXML private Label namelabel=new Label();
	@FXML private Label meddose = new Label();
	@FXML private Label medqt = new Label();
	@FXML private Label price = new Label();
	@FXML private Button buybtn = new Button();
	@FXML private ImageView prodimg = new ImageView();
	
	@FXML private VBox itembox = new VBox();
	
	private Product product;
	private ProductListener<Product> clickListener;
	
	public void setItemData(Product p, ProductListener<Product> l)
	{
		//associate passed product and listener to item to pass the data to the second scene later
		product = p;
		clickListener = l;
		
		//set item data to be displayed in flowpane//
		namelabel.setText(product.getName());
		meddose.setText(String.valueOf(product.getDose())+" "+product.getDoseUnit()); //400+mg
		medqt.setText(product.getAmount());
		price.setText("Tk. "+String.valueOf(product.getPrice())+"/unit");
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
