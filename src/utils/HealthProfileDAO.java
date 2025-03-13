package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class HealthProfileDAO {
	 private static final String URL = "jdbc:mysql://localhost:3306/helpinghands";
	    private static final String USER = "root";
	    private static final String PASSWORD = "Jelaushekodu!1";

    
    public boolean saveHealthProfile(int userId, String firstName, String lastName, String phone, String email,
                                     String dateOfBirth, String gender, String emergencyContactName,
                                     String emergencyContactPhone, String emergencyContactEmail,
                                     String otherDisease, String otherAllergy) {
        String sql = "INSERT INTO health_profile (user_id, first_name, last_name, phone, email, date_of_birth, gender, " +
                     "emergency_contact_name, emergency_contact_phone, emergency_contact_email, other_disease, other_allergy) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, userId);
            pstmt.setString(2, firstName);
            pstmt.setString(3, lastName);
            pstmt.setString(4, phone);
            pstmt.setString(5, email);
            pstmt.setString(6, dateOfBirth);
            pstmt.setString(7, gender);
            pstmt.setString(8, emergencyContactName);
            pstmt.setString(9, emergencyContactPhone);
            pstmt.setString(10, emergencyContactEmail);
            pstmt.setString(11, otherDisease);
            pstmt.setString(12, otherAllergy);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0; 
        } catch (SQLException e) {
            e.printStackTrace();
            return false; 
        }
    }

   
    private boolean isDiagnosisExists(int userId, int diagnosisId) {
        String sql = "SELECT COUNT(*) FROM user_diagnoses WHERE ID = ? AND diagnoses_id = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            pstmt.setInt(2, diagnosisId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next() && rs.getInt(1) > 0) {
                    return true; // Diagnosis exists for the user
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

  
    public boolean saveUserDiagnoses(int userId, List<Integer> diagnosisIds) {
        String sql = "INSERT INTO user_diagnoses (ID, diagnoses_id) VALUES (?, ?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            for (int diagnosisId : diagnosisIds) {
                if (!isDiagnosisExists(userId, diagnosisId)) { 
                    pstmt.setInt(1, userId);
                    pstmt.setInt(2, diagnosisId);
                    pstmt.addBatch();
                }
            }

            int[] rowsAffected = pstmt.executeBatch();
            return rowsAffected.length > 0; 
        } catch (SQLException e) {
            e.printStackTrace();
            return false; 
        }
    }

  
    private boolean isAllergyExists(int userId, int allergyId) {
        String sql = "SELECT COUNT(*) FROM user_allergies WHERE userid = ? AND allergy_id = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            pstmt.setInt(2, allergyId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next() && rs.getInt(1) > 0) {
                    return true; 
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; 
    }

   
    public boolean saveUserAllergies(int userId, List<Integer> allergyIds) {
        String sql = "INSERT INTO user_allergies (userid, allergy_id) VALUES (?, ?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            for (int allergyId : allergyIds) {
                if (!isAllergyExists(userId, allergyId)) { // Check if the allergy already exists
                    pstmt.setInt(1, userId);
                    pstmt.setInt(2, allergyId);
                    pstmt.addBatch();
                }
            }

            int[] rowsAffected = pstmt.executeBatch();
            return rowsAffected.length > 0; 
        } catch (SQLException e) {
            e.printStackTrace();
            return false; 
        }
    }
}