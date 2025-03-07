package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import org.mindrot.jbcrypt.BCrypt;

import application.Main;

public class RegistrationController extends TransitionUtils implements Initializable {

    @FXML private VBox rootvb;
    @FXML private TextField username;
    @FXML private TextField email;
    @FXML private PasswordField password;
    @FXML private CheckBox showPassword;
    @FXML private TextField passwordVisible;
    @FXML private Button signup=new Button();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        rootvb.setOpacity(0);
        fadeInToScene(rootvb);
        signup.setOnAction((ActionEvent e)->{
        	signUp(e);
        });
    }

    
    @FXML
    public void togglePasswordVisibility() {
        if (showPassword.isSelected()) {
            passwordVisible.setText(password.getText());
            passwordVisible.setVisible(true);
            password.setVisible(false);
        } else {
            password.setText(passwordVisible.getText());
            password.setVisible(true);
            passwordVisible.setVisible(false);
        }
    }

    /** Hash Password with BCrypt */
    private String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

 
    private Connection connectdb() {
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/signup", "root", "satadafannum");
        } catch (SQLException e) {
            e.printStackTrace();
           
        }
		return null;
    }

  
    @FXML
    public void signUp(ActionEvent e) {
        if (username.getText().isEmpty() || email.getText().isEmpty() || password.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "All fields are required!");
            return;
        }

        String hashedPassword = hashPassword(password.getText());

        String sql = "INSERT INTO signup.user (username, email, password) VALUES (?, ?, ?)";

        try (Connection con = connectdb();
             PreparedStatement statement = con.prepareStatement(sql)) {

            statement.setString(1, username.getText());
            statement.setString(2, email.getText());
            statement.setString(3, hashedPassword);
            statement.executeUpdate();

            JOptionPane.showMessageDialog(null, "Successfully created new Account");
            Main.remove_onboarding = 1;
            fadeOutToScene(rootvb, "Home");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    
    @FXML
    public void signupButtonClick(MouseEvent e) {
        signup.setStyle("-fx-text-fill:#044dbb; -fx-background-color: #fff; -fx-border-color: #044dbb;");
    }

    @FXML
    public void signupButtonRelease(MouseEvent e) {
        signup.setStyle("-fx-text-fill:#fff; -fx-background-color: #044dbb;");
    }

    @FXML
    public void signupButtonHover(MouseEvent e) {
        signup.setStyle("-fx-background-color: #bcdcfa; -fx-text-fill:#044dbb;");
    }

    @FXML
    public void signupButtonExit(MouseEvent e) {
        signup.setStyle("-fx-background-color: #044dbb; -fx-text-fill:#fff;");
    }

}
