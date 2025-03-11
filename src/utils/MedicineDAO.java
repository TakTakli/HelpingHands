package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MedicineDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/signup";
    private static final String USER = "root";
    private static final String PASSWORD = "satadafannum";

 
    public boolean updateMedicineStatus(int medicineId, String status) {
        String sql = "UPDATE medicines SET status = ? WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, status);
            pstmt.setInt(2, medicineId);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error updating medicine status: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    public boolean saveMedicine(int userId, String type, String name, double dosageAmount, String dosageUnit, String time) {
        String sql = "INSERT INTO medicines (medicine_id, type, name, dosage_amount, dosage_unit, time, status) VALUES (?, ?, ?, ?, ?, ?, 'pending')";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            pstmt.setString(2, type);
            pstmt.setString(3, name);
            pstmt.setDouble(4, dosageAmount);
            pstmt.setString(5, dosageUnit);
            pstmt.setString(6, time);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0; // Return true if the medicine is saved successfully
        } catch (SQLException e) {
            System.err.println("Error saving medicine: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
   
    public List<Medicine> getPendingMedicines(int userId) {
        List<Medicine> pendingMedicines = new ArrayList<>();
        String sql = "SELECT * FROM medicines WHERE medicine_id = ? AND status = 'pending'";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Medicine medicine = new Medicine(
                        rs.getInt("id"),
                        rs.getString("type"),
                        rs.getString("name"),
                        rs.getDouble("dosage_amount"),
                        rs.getString("dosage_unit"),
                        rs.getString("time"),
                        rs.getString("status")
                );
                pendingMedicines.add(medicine);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching pending medicines: " + e.getMessage());
            e.printStackTrace();
        }
        return pendingMedicines;
    }

    
    
    public List<Medicine> getMedicines(int userId) {
        List<Medicine> medicines = new ArrayList<>();
        String sql = "SELECT * FROM medicines WHERE medicine_id = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Medicine medicine = new Medicine(
                        rs.getInt("id"),
                        rs.getString("type"),
                        rs.getString("name"),
                        rs.getDouble("dosage_amount"),
                        rs.getString("dosage_unit"),
                        rs.getString("time"),
                        rs.getString("status")
                );
                medicines.add(medicine);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching medicines: " + e.getMessage());
            e.printStackTrace();
        }
        return medicines;
    }
}
