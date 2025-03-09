package controllers;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javax.swing.JOptionPane;
import org.mindrot.jbcrypt.BCrypt;
import utils.DatabaseConnection;
import utils.UserSession;

public class RegistrationController extends TransitionUtils implements Initializable {

    @FXML private VBox rootvb;
    @FXML private TextField username;
    @FXML private TextField email;
    @FXML private PasswordField password;
    @FXML private CheckBox showPassword;
    @FXML private TextField passwordVisible;
    @FXML private Button signup = new Button();

    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        rootvb.setOpacity(0);
        fadeInToScene(rootvb);
        signup.setOnAction(this::signUp);
    }

    /** Toggle password visibility */
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

    /** Validate email format */
    private boolean isValidEmail(String email) {
        return Pattern.matches(EMAIL_REGEX, email);
    }

    /** Check if username already exists */
    private boolean isUsernameTaken(String username) {
        String sql = "SELECT COUNT(*) FROM signup.user WHERE username = ?";
        try (Connection con = DatabaseConnection.connect();
             PreparedStatement statement = con.prepareStatement(sql)) {
            
            statement.setString(1, username);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next() && rs.getInt(1) > 0) {
                    return true; // Username already exists
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /** Hash Password with BCrypt */
    private String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    /** Handle Signup */
    @FXML
    public void signUp(ActionEvent e) {
        String userInput = username.getText().trim();
        String emailInput = email.getText().trim();
        String passInput = password.getText().trim();

        if (userInput.isEmpty() || emailInput.isEmpty() || passInput.isEmpty()) {
            JOptionPane.showMessageDialog(null, "All fields are required!");
            return;
        }

        if (!isValidEmail(emailInput)) {
            JOptionPane.showMessageDialog(null, "❌ Invalid email format! Please enter a valid email.");
            return;
        }

        if (isUsernameTaken(userInput)) {
            JOptionPane.showMessageDialog(null, "❌ Username already exists! Please choose another.");
            return;
        }

        String hashedPassword = hashPassword(passInput);

        String sql = "INSERT INTO signup.user (username, email, password) VALUES (?, ?, ?)";

        try (Connection con = DatabaseConnection.connect();
             PreparedStatement statement = con.prepareStatement(sql)) {

            statement.setString(1, userInput);
            statement.setString(2, emailInput);
            statement.setString(3, hashedPassword);
            statement.executeUpdate();
      
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int userId = generatedKeys.getInt(1);

                    // Store user details in the session
                    UserSession.getInstance().setUserId(userId);
                    UserSession.getInstance().setUsername(userInput);

                    JOptionPane.showMessageDialog(null, "✅ Successfully created new account!");
                    UserProfileController.setUserData(userInput, emailInput);

                    fadeOutToScene(rootvb, "Home");
                } else {
                    JOptionPane.showMessageDialog(null, "❌ Failed to create account. Please try again.");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /** Button Effects */
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
