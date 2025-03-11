package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StoreDAO {
	private static final String URL = "jdbc:mysql://localhost:3306/helpinghands";
    private static final String USER = "root";
    private static final String PASSWORD = "Jelaushekodu!1";

    public boolean saveOrder(int userId, String prodname, int quantity, double total, String address, String phone)
    {
    	String sql = "INSERT INTO user_orders (user_id, product, quantity, total_price, address, phone) "+
                "VALUES (?, ?, ?, ?, ?, ?)";
    	 try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setInt(1, userId);
                pstmt.setString(2, prodname);
                pstmt.setInt(3, quantity);
                pstmt.setDouble(4, total);
                pstmt.setString(5, address);
                pstmt.setString(6, phone);

                int rowsAffected = pstmt.executeUpdate();
                return rowsAffected > 0; // Return true if the profile is saved successfully
            } catch (SQLException e) {
                e.printStackTrace();
                return false; // Return false if an error occurs
            }
    }
}
