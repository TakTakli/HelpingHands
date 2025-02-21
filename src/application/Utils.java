package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class Utils {
	public static void changeScene(ActionEvent event,String fxmlFile,String title,String Username,String Email)
	{
		Parent root=null;
		
		if(Username!=null && Email!=null)
		{
			try {
				FXMLLoader loader=new FXMLLoader(Utils.class.getResource(fxmlFile));
				root =loader.load();
				LoggedIncontrol loggedin=loader.getController();
				loggedin.setUserInfo(Username);
				loggedin.setUserInfo(Email);
				
			}catch(IOException e)
			{
				e.printStackTrace();
			}
		}
		else {
		try{
			root=FXMLLoader.load(Utils.class.getResource(fxmlFile));
		}catch(IOException e)
		{
			e.printStackTrace();
			
		}
		Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
		stage.setTitle(title);
		stage.setScene(new Scene(root,600,400));
		stage.show();
		}
		
		
		
		
	}
		public static void signUpUser(ActionEvent event, String Username, String Email,String Password) {
		    Connection connection = null;
		    PreparedStatement psInsert = null;
		    PreparedStatement psCheckUserExists = null;
		    ResultSet resultSet = null;

		    try {
		        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/login", "root", "satadafannum");
		        psCheckUserExists = connection.prepareStatement("SELECT * FROM login.register WHERE Username=? ");
		        psCheckUserExists.setString(1, Username);
		        
		        resultSet = psCheckUserExists.executeQuery();

		        if (resultSet.isBeforeFirst()) {
		            System.out.println("User already exists!");
		            Alert alert = new Alert(Alert.AlertType.ERROR);
		            alert.setContentText("Use a different username");
		            alert.show();
		        } else {
		            psInsert = connection.prepareStatement("INSERT INTO login.register (Username, Email, Password) VALUES (?,?, ?)");
		            psInsert.setString(1, Username);
		            psInsert.setString(2, Email);
		            psInsert.setString(3, Password);
		       
		            psInsert.executeUpdate();
		            changeScene(event,"loggedin.fxml","Welcome!",Username,Email);
		        }}
		        catch (SQLException e) {
		            e.printStackTrace();
		        } finally {
		            if (resultSet != null) {
		                try {
		                    resultSet.close();
		                } catch (SQLException e) {
		                    e.printStackTrace();
		                }
		            }

		            if (psCheckUserExists != null) {
		                try {
		                    psCheckUserExists.close();
		                } catch (SQLException e) {
		                    e.printStackTrace();
		                }
		            }

		            if (psInsert != null) {
		                try {
		                    psInsert.close();
		                } catch (SQLException e) {
		                    e.printStackTrace();
		                }
		            }

		            if (connection != null) {
		                try {
		                    connection.close();
		                } catch (SQLException e) {
		                    e.printStackTrace();
		                }
		            }}}
		            public static void logInUser(ActionEvent event, String username, String password,String email) {
		                Connection connection = null;
		                PreparedStatement preparedStatement = null;
		                ResultSet resultSet = null;

		                try {
		                    connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/login", "root", "satadafannum");
		                    preparedStatement = connection.prepareStatement("SELECT Password FROM login.register WHERE Username = ?");
		                    preparedStatement.setString(1, username);
		                    resultSet = preparedStatement.executeQuery();

		                    if (!resultSet.isBeforeFirst()) {
		                        System.out.println("User not found in the database!");
		                        Alert alert = new Alert(Alert.AlertType.ERROR);
		                        alert.setContentText("Provided credentials are incorrect!");
		                        alert.show();
		                    } else {
		                        while (resultSet.next()) {
		                            String retrievedPassword = resultSet.getString("password");
		                           
		                            if (retrievedPassword.equals(password)) {
		                                changeScene(event, "loggedin.fxml", "Welcome!", username,email);
		                            } else {
		                                System.out.println("Passwords did not match!");
		                                Alert alert = new Alert(Alert.AlertType.ERROR);
		                                alert.setContentText("The provided credentials are incorrect!");
		                                alert.show();
		                            }
		                        }
		                    }
		                } catch (SQLException e) {
		                    e.printStackTrace();
		                }
		            }
					public static void changeScene(ActionEvent event, String fxmlFile, String title, Object username,
							Object email) {
						// TODO Auto-generated method stub
						
					}
                     

		    
		

	

}