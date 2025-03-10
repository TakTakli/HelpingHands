package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import utils.MedicineDAO;
import utils.UserSession;

public class MedAddController extends TransitionUtils implements Initializable {
    @FXML private StackPane return_btn;
    @FXML private HBox roothb;

    // Choose medicine type
    @FXML private Button bottle;
    @FXML private Button tablet;
    @FXML private Button capsule;
    @FXML private Button syringe;
    @FXML private Button inhaler;
    private String selectedType = ""; // Track the selected medicine type

    // Input details
    @FXML private TextField med_name;
    @FXML private TextField dosetxt;
    @FXML private ChoiceBox<String> DoseBox;
    private String[] doseList = {"mg", "g", "mL", "IU"};

    @FXML private TextField hour;
    @FXML private TextField min;

    @FXML private ChoiceBox<String> AmPmBox;
    private String[] timeList = {"AM", "PM"};

    @FXML private Button addbtn; // DB connection

    private MedicineDAO medicineDAO = new MedicineDAO();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        roothb.setOpacity(0);
        fadeInToScene(roothb);
        initializeSidebar(roothb);

        // Handle return button click
        return_btn.setOnMouseClicked((e) -> {
            fadeOutToScene(roothb, "MedTracker1");
        });

        // Populate choice boxes
        DoseBox.getItems().addAll(doseList);
        AmPmBox.getItems().addAll(timeList);

        // Handle button clicks to track the selected medicine type
        bottle.setOnAction((e) -> selectedType = "Bottle");
        tablet.setOnAction((e) -> selectedType = "Tablet");
        capsule.setOnAction((e) -> selectedType = "Capsule");
        syringe.setOnAction((e) -> selectedType = "Syringe");
        inhaler.setOnAction((e) -> selectedType = "Inhaler");

        // Handle "Add" button click
        addbtn.setOnAction((e) -> saveMedicine());
    }

    /**
     * Saves the medicine details to the database and shows a message box.
     */
    private void saveMedicine() {
        // Get the current user's ID from the session
        int userId = UserSession.getInstance().getUserId();
        if (userId == -1) {
            showAlert(Alert.AlertType.ERROR, "Error", "No user is logged in!");
            return;
        }

        // Validate medicine type
        if (selectedType.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "Please select a medicine type.");
            return;
        }

        // Validate medicine name
        String name = med_name.getText().trim();
        if (name.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "Please enter the medicine name.");
            return;
        }

        // Validate dosage amount
        double dosageAmount;
        try {
            dosageAmount = Double.parseDouble(dosetxt.getText().trim());
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Invalid dosage amount. Please enter a valid number.");
            return;
        }

        // Validate dosage unit
        String dosageUnit = DoseBox.getValue();
        if (dosageUnit == null) {
            showAlert(Alert.AlertType.ERROR, "Error", "Please select a dosage unit.");
            return;
        }

        // Validate time
        String time = hour.getText().trim() + ":" + min.getText().trim() + " " + AmPmBox.getValue();
        if (hour.getText().trim().isEmpty() || min.getText().trim().isEmpty() || AmPmBox.getValue() == null) {
            showAlert(Alert.AlertType.ERROR, "Error", "Please enter a valid time.");
            return;
        }

        // Save medicine details to the database
        boolean isSaved = medicineDAO.saveMedicine(userId, selectedType, name, dosageAmount, dosageUnit, time);
        if (isSaved) {
            showAlert(Alert.AlertType.INFORMATION, "Success", "Medicine saved successfully!");
            // Clear input fields after successful save
            clearFields();
            // Redirect to MedTracker1 view
            fadeOutToScene(roothb, "MedTracker1_OLDVERSION");
        } else {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to save medicine. Please try again.");
        }
    }

    /**
     * Clears all input fields after saving a medicine.
     */
    private void clearFields() {
        med_name.clear();
        dosetxt.clear();
        DoseBox.setValue(null);
        hour.clear();
        min.clear();
        AmPmBox.setValue(null);
        selectedType = ""; // Reset selected type
    }

    /**
     * Displays a message box (alert dialog).
     *
     * @param type    The type of alert (e.g., ERROR, INFORMATION).
     * @param title   The title of the alert.
     * @param message The message to display.
     */
    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}