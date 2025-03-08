package controllers;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javax.swing.JOptionPane;
import org.mindrot.jbcrypt.BCrypt;

import application.Main;

public class LoginController extends TransitionUtils implements Initializable {

    @FXML private VBox rootvb;
    @FXML private TextField usernameOrEmail;
    @FXML private PasswordField password;
    @FXML private CheckBox showPassword;
    @FXML private TextField passwordVisible;
    @FXML private Button login=new Button();

    private static final String DB_URL = "jdbc:mysql://localhost:3306/signup";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "satadafannum";

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        rootvb.setOpacity(0);
        fadeInToScene(rootvb);
        login.setOnAction((ActionEvent e)->
        {
        	handleLogin(e);
        });
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

    /** Handle Login */
    @FXML
    public void handleLogin(ActionEvent event) {
        String userInput = usernameOrEmail.getText().trim();
        String passInput = password.getText().trim();

        if (userInput.isEmpty() || passInput.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Username/Email and password are required!");
            return;
        }

        if (authenticateUser(userInput, passInput)) {
            JOptionPane.showMessageDialog(null, "✅ Login successful!");
            fadeOutToScene(rootvb, "Home");
            Main.remove_onboarding = 1;
        } else {
            JOptionPane.showMessageDialog(null, "❌ Invalid username/email or password!");
        }
    }

    /** Database Connection */
    private Connection connectdb() {
        try {
            return DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /** Authenticate User */
    private boolean authenticateUser(String userInput, String passInput) {
        String sql = "SELECT password FROM signup.user WHERE username = ? OR email = ?";
        try (Connection con = connectdb();
             PreparedStatement statement = con.prepareStatement(sql)) {

            statement.setString(1, userInput);
            statement.setString(2, userInput);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return BCrypt.checkpw(passInput, rs.getString("password"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /** Button Effects */
    @FXML
    public void loginButtonClick(MouseEvent e) {
        login.setStyle("-fx-text-fill:#044dbb; -fx-background-color: #fff; -fx-border-color: #044dbb;");
    }

    @FXML
    public void loginButtonRelease(MouseEvent e) {
        login.setStyle("-fx-text-fill:#fff; -fx-background-color: #044dbb;");
    }

    @FXML
    public void loginButtonHover(MouseEvent e) {
        login.setStyle("-fx-background-color: #bcdcfa; -fx-text-fill:#044dbb;");
    }

    @FXML
    public void loginButtonExit(MouseEvent e) {
        login.setStyle("-fx-background-color: #044dbb; -fx-text-fill:#fff;");
    }

}
