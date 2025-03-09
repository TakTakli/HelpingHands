package controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import utils.HealthProfileDAO;
import utils.HealthProfilePreviewDAO;
import utils.UserSession;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class HealthProfilePreviewController extends TransitionUtils implements Initializable {

    @FXML private TextField fname;
    @FXML private TextField lname;
    @FXML private TextField PhoneNumber;
    @FXML private TextField EmailAddress;
    @FXML private DatePicker DateOfBirth;
    @FXML private RadioButton male;
    @FXML private RadioButton female;
    @FXML private CheckBox Diabetes;
    @FXML private CheckBox Hypertension;
    @FXML private CheckBox LowBloodPressure;
    @FXML private CheckBox Arthritis;
    @FXML private CheckBox ChronicBronchitis;
    @FXML private CheckBox FattyLiver;
    @FXML private CheckBox Osteoporosis;
    @FXML private CheckBox ChronicKidneyDisease;
    @FXML private CheckBox Asthma;
    @FXML private CheckBox Cancer;
    @FXML private CheckBox Dementia;
    @FXML private CheckBox Alzheimer;
    @FXML private CheckBox otherdiagnosescheckbox;
    @FXML private TextField OtherDiagnoses;
    @FXML private CheckBox Peanut;
    @FXML private CheckBox Soy;
    @FXML private CheckBox Dairy;
    @FXML private CheckBox Eggs;
    @FXML private CheckBox Wheat;
    @FXML private CheckBox TreeNuts;
    @FXML private CheckBox Shellfish;
    @FXML private CheckBox Beef;
    @FXML private CheckBox Pollen;
    @FXML private CheckBox Mold;
    @FXML private CheckBox otherallergycheck;
    @FXML private TextField OtherAllergy;
    @FXML private TextField fname1;
    @FXML private TextField lname1;
    @FXML private TextField phone1;
    @FXML private TextField mail1;
    @FXML private Button submit; // Save Changes button
    @FXML private Button submit1; // Go Back button
    @FXML private Button edit;
    @FXML private VBox rootvb;

    private HealthProfileDAO healthProfileDAO = new HealthProfileDAO();
    private HealthProfilePreviewDAO healthProfilePreviewDAO = new HealthProfilePreviewDAO(); // Instance of DAO
    private boolean isEditMode = false; // Tracks whether the user is in edit mode

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        rootvb.setOpacity(0);
        fadeInToScene(rootvb);

        // Load the user's health profile data
        loadHealthProfile();

        // Set up button actions
        edit.setOnAction(e -> setFieldsEditable(true));
        submit.setOnAction(e -> saveHealthProfile()); // Save Changes
        submit1.setOnAction(e -> fadeOutToScene(rootvb, "Home")); // Go Back
    }

    /**
     * Loads the user's health profile data from the database.
     */
    private void loadHealthProfile() {
        int userId = UserSession.getInstance().getUserId();
        if (userId == -1) {
            showAlert(Alert.AlertType.ERROR, "Error", "No user is logged in!");
            return;
        }

        // Fetch health profile data
        try (ResultSet rs = healthProfilePreviewDAO.getHealthProfile(userId)) {
            if (rs != null && rs.next()) {
                // Populate personal details
                fname.setText(rs.getString("first_name"));
                lname.setText(rs.getString("last_name"));
                PhoneNumber.setText(rs.getString("phone"));
                EmailAddress.setText(rs.getString("email"));
                DateOfBirth.setValue(LocalDate.parse(rs.getString("date_of_birth")));
                String gender = rs.getString("gender");
                if ("Male".equals(gender)) {
                    male.setSelected(true);
                } else if ("Female".equals(gender)) {
                    female.setSelected(true);
                }

                // Populate emergency contact details
                String[] emergencyContactName = rs.getString("emergency_contact_name").split(" ", 2);
                fname1.setText(emergencyContactName[0]);
                lname1.setText(emergencyContactName.length > 1 ? emergencyContactName[1] : "");
                phone1.setText(rs.getString("emergency_contact_phone"));
                mail1.setText(rs.getString("emergency_contact_email"));

                // Populate other disease and allergy
                OtherDiagnoses.setText(rs.getString("other_disease"));
                OtherAllergy.setText(rs.getString("other_allergy"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Database Error", "Failed to load health profile data.");
        }

        // Fetch and populate diagnoses
        List<Integer> diagnosisIds = healthProfilePreviewDAO.getUserDiagnoses(userId);
        for (int id : diagnosisIds) {
            switch (id) {
                case 1 -> Diabetes.setSelected(true);
                case 2 -> Hypertension.setSelected(true);
                case 3 -> Asthma.setSelected(true);
                case 4 -> LowBloodPressure.setSelected(true);
                case 5 -> Arthritis.setSelected(true);
                case 6 -> ChronicBronchitis.setSelected(true);
                case 7 -> FattyLiver.setSelected(true);
                case 8 -> Osteoporosis.setSelected(true);
                case 9 -> ChronicKidneyDisease.setSelected(true);
                case 10 -> Cancer.setSelected(true);
                case 11 -> Dementia.setSelected(true);
                case 12 -> Alzheimer.setSelected(true);
                case 13 -> otherdiagnosescheckbox.setSelected(true);
            }
        }

        // Fetch and populate allergies
        List<Integer> allergyIds = healthProfilePreviewDAO.getUserAllergies(userId);
        for (int id : allergyIds) {
            switch (id) {
                case 1 -> Peanut.setSelected(true);
                case 2 -> Soy.setSelected(true);
                case 3 -> Dairy.setSelected(true);
                case 4 -> Eggs.setSelected(true);
                case 5 -> Wheat.setSelected(true);
                case 6 -> TreeNuts.setSelected(true);
                case 7 -> Shellfish.setSelected(true);
                case 8 -> Beef.setSelected(true);
                case 9 -> Pollen.setSelected(true);
                case 10 -> Mold.setSelected(true);
                case 11 -> otherallergycheck.setSelected(true);
            }
        }

        // Disable fields for preview mode
        setFieldsEditable(false);
    }

    /**
     * Enables or disables editing of fields.
     *
     * @param editable If true, fields are enabled for editing; if false, fields are disabled.
     */
    private void setFieldsEditable(boolean editable) {
        // Personal Details
        fname.setDisable(!editable);
        lname.setDisable(!editable);
        PhoneNumber.setDisable(!editable);
        EmailAddress.setDisable(!editable);
        DateOfBirth.setDisable(!editable);
        male.setDisable(!editable);
        female.setDisable(!editable);

        // Emergency Contact Details
        fname1.setDisable(!editable);
        lname1.setDisable(!editable);
        phone1.setDisable(!editable);
        mail1.setDisable(!editable);

        // Other Disease and Allergy
        OtherDiagnoses.setDisable(!editable);
        OtherAllergy.setDisable(!editable);

        // Diagnoses CheckBoxes
        Diabetes.setDisable(!editable);
        Hypertension.setDisable(!editable);
        LowBloodPressure.setDisable(!editable);
        Arthritis.setDisable(!editable);
        ChronicBronchitis.setDisable(!editable);
        FattyLiver.setDisable(!editable);
        Osteoporosis.setDisable(!editable);
        ChronicKidneyDisease.setDisable(!editable);
        Asthma.setDisable(!editable);
        Cancer.setDisable(!editable);
        Dementia.setDisable(!editable);
        Alzheimer.setDisable(!editable);
        otherdiagnosescheckbox.setDisable(!editable);

        // Allergies CheckBoxes
        Peanut.setDisable(!editable);
        Soy.setDisable(!editable);
        Dairy.setDisable(!editable);
        Eggs.setDisable(!editable);
        Wheat.setDisable(!editable);
        TreeNuts.setDisable(!editable);
        Shellfish.setDisable(!editable);
        Beef.setDisable(!editable);
        Pollen.setDisable(!editable);
        Mold.setDisable(!editable);
        otherallergycheck.setDisable(!editable);
    }

    /**
     * Saves the updated health profile data to the database.
     */
    private void saveHealthProfile() {
        int userId = UserSession.getInstance().getUserId();
        if (userId == -1) {
            showAlert(Alert.AlertType.ERROR, "Error", "No user is logged in!");
            return;
        }

        // Validate required fields
        if (fname.getText().isEmpty() || lname.getText().isEmpty() || PhoneNumber.getText().isEmpty() ||
            EmailAddress.getText().isEmpty() || DateOfBirth.getValue() == null ||
            (!male.isSelected() && !female.isSelected()) || fname1.getText().isEmpty() ||
            lname1.getText().isEmpty() || phone1.getText().isEmpty() || mail1.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Validation Error", "Please fill in all required fields!");
            return;
        }

        // Save health profile data
        String firstName = fname.getText();
        String lastName = lname.getText();
        String phone = PhoneNumber.getText();
        String email = EmailAddress.getText();
        String dateOfBirth = DateOfBirth.getValue().toString();
        String gender = male.isSelected() ? "Male" : "Female";
        String emergencyContactName = fname1.getText() + " " + lname1.getText();
        String emergencyContactPhone = phone1.getText();
        String emergencyContactEmail = mail1.getText();
        String otherDisease = otherdiagnosescheckbox.isSelected() ? OtherDiagnoses.getText() : "";
        String otherAllergy = otherallergycheck.isSelected() ? OtherAllergy.getText() : "";

        boolean isProfileSaved = healthProfileDAO.saveHealthProfile(userId, firstName, lastName, phone, email, dateOfBirth, gender,
                                          emergencyContactName, emergencyContactPhone, emergencyContactEmail,
                                          otherDisease, otherAllergy);

     // Save diagnoses
        List<Integer> selectedDiagnosisIds = getSelectedDiagnoses();
        boolean areDiagnosesSaved = healthProfileDAO.saveUserDiagnoses(userId, selectedDiagnosisIds);

        // Save allergies
        List<Integer> selectedAllergyIds = getSelectedAllergies();
        boolean areAllergiesSaved = healthProfileDAO.saveUserAllergies(userId, selectedAllergyIds);

        // Check if all data was saved successfully
        if (isProfileSaved && areDiagnosesSaved && areAllergiesSaved) {
            showAlert(Alert.AlertType.INFORMATION, "Success", "Health profile saved successfully!");
            setFieldsEditable(false); // Switch back to preview mode
        } else {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to save health profile. Please try again.");
        }
    }

    /**
     * Gets the selected diagnoses from the checkboxes.
     *
     * @return A list of selected diagnosis IDs.
     */
    private List<Integer> getSelectedDiagnoses() {
        List<Integer> selectedDiagnosisIds = new ArrayList<>();
        if (Diabetes.isSelected()) selectedDiagnosisIds.add(1);
        if (Hypertension.isSelected()) selectedDiagnosisIds.add(2);
        if (Asthma.isSelected()) selectedDiagnosisIds.add(3);
        if (LowBloodPressure.isSelected()) selectedDiagnosisIds.add(4);
        if (Arthritis.isSelected()) selectedDiagnosisIds.add(5);
        if (ChronicBronchitis.isSelected()) selectedDiagnosisIds.add(6);
        if (FattyLiver.isSelected()) selectedDiagnosisIds.add(7);
        if (Osteoporosis.isSelected()) selectedDiagnosisIds.add(8);
        if (ChronicKidneyDisease.isSelected()) selectedDiagnosisIds.add(9);
        if (Cancer.isSelected()) selectedDiagnosisIds.add(10);
        if (Dementia.isSelected()) selectedDiagnosisIds.add(11);
        if (Alzheimer.isSelected()) selectedDiagnosisIds.add(12);
        if (otherdiagnosescheckbox.isSelected()) selectedDiagnosisIds.add(13);
        return selectedDiagnosisIds;
    }

    /**
     * Gets the selected allergies from the checkboxes.
     *
     * @return A list of selected allergy IDs.
     */
    private List<Integer> getSelectedAllergies() {
        List<Integer> selectedAllergyIds = new ArrayList<>();
        if (Peanut.isSelected()) selectedAllergyIds.add(1);
        if (Soy.isSelected()) selectedAllergyIds.add(2);
        if (Dairy.isSelected()) selectedAllergyIds.add(3);
        if (Eggs.isSelected()) selectedAllergyIds.add(4);
        if (Wheat.isSelected()) selectedAllergyIds.add(5);
        if (TreeNuts.isSelected()) selectedAllergyIds.add(6);
        if (Shellfish.isSelected()) selectedAllergyIds.add(7);
        if (Beef.isSelected()) selectedAllergyIds.add(8);
        if (Pollen.isSelected()) selectedAllergyIds.add(9);
        if (Mold.isSelected()) selectedAllergyIds.add(10);
        if (otherallergycheck.isSelected()) selectedAllergyIds.add(11);
        return selectedAllergyIds;
    }

    /**
     * Displays an alert dialog.
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