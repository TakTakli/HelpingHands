package controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import utils.Medicine;
import utils.MedicineDAO;
import utils.UserSession;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MedTracker1Controller extends TransitionUtils implements Initializable {
    @FXML private VBox medscontainer; // The container for medicines
    @FXML private Label duetxt; // Label to display "Nothing due" or other messages
    @FXML private Label TodayDate; // Label to display today's date
    @FXML private Button medadd_btn; // Add Button to navigate to MedicineAdd view

    private MedicineDAO medicineDAO = new MedicineDAO();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Fetch medicines for the logged-in user
        int userId = UserSession.getInstance().getUserId();
        if (userId != -1) {
            List<Medicine> medicines = medicineDAO.getMedicines(userId);
            displayMedicines(medicines);
        } else {
            duetxt.setText("No user is logged in.");
        }

        // Set today's date
        setTodaysDate();

        // Handle Add Button click
        medadd_btn.setOnAction((e) -> {
            fadeOutToScene(medscontainer, "MedicineAdd");
        });
    }

    /**
     * Displays the list of medicines in a table-like format.
     */
    private void displayMedicines(List<Medicine> medicines) {
        medscontainer.getChildren().clear(); // Clear existing content

        if (medicines.isEmpty()) {
            duetxt.setText("No medicines due today.");
            return;
        }

        // Create a GridPane for the table layout
        GridPane medicineTable = new GridPane();
        medicineTable.setHgap(20); // Horizontal gap between columns
        medicineTable.setVgap(10); // Vertical gap between rows
        medicineTable.setStyle("-fx-padding: 20;");

        // Add column headers
        Label serialHeader = new Label("No.");
        Label nameHeader = new Label("Medicine");
        Label typeHeader = new Label("Type");
        Label dosageHeader = new Label("Dosage");
        Label timeHeader = new Label("Time");
        Label statusHeader = new Label("Status");
        Label actionHeader = new Label("Action");

        // Style the headers
        String headerStyle = "-fx-font-size: 20px; -fx-font-family: 'Arial'; -fx-font-weight: bold;";
        serialHeader.setStyle(headerStyle);
        nameHeader.setStyle(headerStyle);
        typeHeader.setStyle(headerStyle);
        dosageHeader.setStyle(headerStyle);
        timeHeader.setStyle(headerStyle);
        statusHeader.setStyle(headerStyle);
        actionHeader.setStyle(headerStyle);

        // Add headers to the GridPane
        medicineTable.addRow(0, serialHeader, nameHeader, typeHeader, dosageHeader, timeHeader, statusHeader, actionHeader);

        // Add medicines to the GridPane
        for (int i = 0; i < medicines.size(); i++) {
            Medicine medicine = medicines.get(i);
            int rowIndex = i + 1; // Start rows from 1 (row 0 is for headers)

            // Serial number
            Label serialLabel = new Label(rowIndex + ".");
            serialLabel.setStyle("-fx-font-size: 20px; -fx-font-family: 'Arial';");

            // Medicine name
            Label nameLabel = new Label(medicine.getName());
            nameLabel.setStyle("-fx-font-size: 20px; -fx-font-family: 'Arial';");

            // Medicine type
            Label typeLabel = new Label(medicine.getType());
            typeLabel.setStyle("-fx-font-size: 20px; -fx-font-family: 'Arial';");

            // Dosage
            Label dosageLabel = new Label(medicine.getDosageAmount() + " " + medicine.getDosageUnit());
            dosageLabel.setStyle("-fx-font-size: 20px; -fx-font-family: 'Arial';");

            // Time
            Label timeLabel = new Label(medicine.getTime());
            timeLabel.setStyle("-fx-font-size: 20px; -fx-font-family: 'Arial';");

            // Status
            Label statusLabel = new Label(medicine.getStatus());
            statusLabel.setStyle("-fx-font-size: 20px; -fx-font-family: 'Arial';");
            if (medicine.getStatus().equalsIgnoreCase("taken")) {
                statusLabel.setTextFill(Color.GREEN); // Make "Taken" status green
            } else {
                statusLabel.setTextFill(Color.BLACK); // Default color for other statuses
            }

            // CheckBox to mark medicine as taken
            CheckBox takenCheckBox = new CheckBox("Taken");
            takenCheckBox.setSelected(medicine.getStatus().equalsIgnoreCase("taken"));
            takenCheckBox.setStyle("-fx-font-size: 20px; -fx-font-family: 'Arial';");
            takenCheckBox.setOnAction((e) -> {
                boolean isTaken = takenCheckBox.isSelected();
                updateMedicineStatus(medicine.getId(), isTaken ? "taken" : "pending");
                statusLabel.setText(isTaken ? "taken" : "pending");
                statusLabel.setTextFill(isTaken ? Color.GREEN : Color.BLACK); // Update color dynamically
            });

            // Add the medicine details to the GridPane
            medicineTable.addRow(rowIndex, serialLabel, nameLabel, typeLabel, dosageLabel, timeLabel, statusLabel, takenCheckBox);
        }

        // Add the GridPane to the medscontainer
        medscontainer.getChildren().add(medicineTable);
    }

    /**
     * Updates the status of a medicine in the database.
     *
     * @param medicineId The ID of the medicine to update.
     * @param status     The new status (e.g., "taken" or "pending").
     */
    private void updateMedicineStatus(int medicineId, String status) {
        boolean isUpdated = medicineDAO.updateMedicineStatus(medicineId, status);
        if (!isUpdated) {
            System.err.println("Failed to update medicine status.");
        }
    }

    /**
     * Sets today's date in the TodayDate label.
     */
    private void setTodaysDate() {
        java.time.LocalDate today = java.time.LocalDate.now();
        String dayOfWeek = today.getDayOfWeek().toString();
        String day = String.valueOf(today.getDayOfMonth());
        String month = today.getMonth().toString();
        TodayDate.setText(dayOfWeek + ", " + day + " " + month);
    }
}