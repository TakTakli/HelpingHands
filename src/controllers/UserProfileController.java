package controllers;

import javafx.scene.control.TextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class UserProfileController extends TransitionUtils implements Initializable {
    
    @FXML private HBox roothb = new HBox();
    @FXML private Button viewhealth_btn = new Button();
    @FXML private Button logout_btn = new Button();
    @FXML private TextField usernameField; 
    @FXML private TextField emailField; 
    @FXML private TextField passwordField; 

    // Store logged-in user's data
    private static String username;
    private static String email;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        roothb.setOpacity(0);
        fadeInToScene(roothb);  

       
        if (username != null && email != null) {
            usernameField.setText(username);
            emailField.setText(email);
            passwordField.setText("*********");  // Display password as masked text (if needed)
        }

        // Action for "View Health" button
        viewhealth_btn.setOnAction((e) -> {
            fadeOutToScene(roothb, "HealthProfile");  // Transition to HealthProfile scene
        });

        // Action for "Logout" button
        logout_btn.setOnAction((e) -> {
            confirmLogout();  // Confirm logout before proceeding
        });

        initializeSidebar(roothb);  
    }

    /** Confirm Logout Action */
    private void confirmLogout() {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirm Logout");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to log out?");

        // Show confirmation dialog
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                fadeOutToScene(roothb, "Login");  // Transition to Login scene after confirmation
            }
        });
    }

    // Static method to set user data
    public static void setUserData(String usernameData, String emailData) {
        username = usernameData;
        email = emailData;
    }
}
