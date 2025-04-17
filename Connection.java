/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bank.management.system;

import java.sql.DriverManager;
import java.sql.SQLException;


public class Connection {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/bankSystem";
        String user = "root"; // Change as needed
        String password = "arun5059"; // Change as needed

        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to MySQL
            java.sql.Connection con = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to MySQL successfully!");
            con.close();
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC Driver not found: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
        }
    }
}
