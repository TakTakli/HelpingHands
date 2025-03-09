package controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import utils.HealthProfileDAO;
import utils.UserSession;
import javax.swing.JOptionPane;

public class HealthProfileController extends TransitionUtils implements Initializable {
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
    @FXML private Button submit;
    @FXML private VBox rootvb;

    private HealthProfileDAO healthProfileDAO = new HealthProfileDAO();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        rootvb.setOpacity(0);
        fadeInToScene(rootvb);

        // Handle submit button action
        submit.setOnAction(e -> saveHealthProfile());
    }

    private void saveHealthProfile() {
        // Get the current user's ID from the session
        int userId = UserSession.getInstance().getUserId();

        if (userId == -1) {
            JOptionPane.showMessageDialog(null, "No user is logged in!");
            return;
        }

        
        if (fname.getText().isEmpty() || lname.getText().isEmpty() || PhoneNumber.getText().isEmpty() ||
            EmailAddress.getText().isEmpty() || DateOfBirth.getValue() == null ||
            (!male.isSelected() && !female.isSelected()) || fname1.getText().isEmpty() ||
            lname1.getText().isEmpty() || phone1.getText().isEmpty() || mail1.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill in all required fields!");
            return;
        }

       
        String firstName = fname.getText();
        String lastName = lname.getText();
        String phone = PhoneNumber.getText();
        String email = EmailAddress.getText();
        String dateOfBirth = DateOfBirth.getValue().toString(); // Convert LocalDate to String
        String gender = male.isSelected() ? "Male" : "Female";

        
        String emergencyContactName = fname1.getText() + " " + lname1.getText();
        String emergencyContactPhone = phone1.getText();
        String emergencyContactEmail = mail1.getText();

       
        String otherDisease = "";
        if (otherdiagnosescheckbox.isSelected() && !OtherDiagnoses.getText().isEmpty()) {
            otherDisease = OtherDiagnoses.getText();
        }

        String otherAllergy = "";
        if (otherallergycheck.isSelected() && !OtherAllergy.getText().isEmpty()) {
            otherAllergy = OtherAllergy.getText();
        }

       
        boolean isProfileSaved = healthProfileDAO.saveHealthProfile(userId, firstName, lastName, phone, email, dateOfBirth, gender,
                                          emergencyContactName, emergencyContactPhone, emergencyContactEmail,
                                          otherDisease, otherAllergy);

        
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

        boolean areDiagnosesSaved = healthProfileDAO.saveUserDiagnoses(userId, selectedDiagnosisIds);

       
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

        boolean areAllergiesSaved = healthProfileDAO.saveUserAllergies(userId, selectedAllergyIds);

        // Check if all data was saved successfully
        if (isProfileSaved && areDiagnosesSaved && areAllergiesSaved) {
            JOptionPane.showMessageDialog(null, "✅ Health profile saved successfully!");
            fadeOutToScene(rootvb, "Home"); // Navigate to Home screen
        } else {
            JOptionPane.showMessageDialog(null, "❌ Failed to save health profile. Please try again.");
        }
    }
}