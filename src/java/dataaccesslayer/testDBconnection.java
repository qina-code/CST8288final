/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataaccesslayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author User
 */
public class testDBconnection {public static void main(String[] args){
    Connection connection = DBConnection.getConnection();
    try{
        // Prepare query
        PreparedStatement pstmt = connection.prepareStatement("INSERT INTO user (name, email, password, type, subscribed) "
            + "VALUES (?, ?, ?, ?, ?)");
        pstmt.setString(1, "test");
        pstmt.setString(2, "test@gmail.com");
        pstmt.setString(3, "123456");
                pstmt.setString(4, "customer");

        pstmt.setBoolean(5, true);
        
        // Execute the query
        int rowsAffected = pstmt.executeUpdate();
        System.out.println("Rows affected: " + rowsAffected);
        
        // Close resources
        pstmt.close();
    } catch(SQLException e) {
        // Handle any SQL errors
        e.printStackTrace();
    } finally {
        // Close the connection
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    System.out.println("Connection closed.");
}
}
