/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataaccesslayer;


import java.sql.Connection;

import java.sql.DriverManager;

import java.sql.SQLException;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class DBConnection {
	private static Connection connection = null;

 //       private static final String PROPERTIES_FILE_PATH = "../../../data/database.properties";
          private static final String PROPERTIES_FILE_PATH = "C:\\Users\\User\\Documents\\NetBeansProjects\\FWRP\\src\\data\\database.properties";


	private DBConnection() {
		try {
			//read from properties
		    Properties props = new Properties();
		    
		    try {
		        InputStream in = Files.newInputStream(Paths.get(PROPERTIES_FILE_PATH));
		        props.load(in);
		        in.close();
		    } catch (IOException e) {
                        System.err.println("ERROR: database.properties file not found at path: " + PROPERTIES_FILE_PATH);

		        e.printStackTrace();
		    }

		    String serverUrl = props.getProperty("serverUrl");
		    String userString = props.getProperty("userString");
		    String passwordString = props.getProperty("passwordString");
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				throw new SQLException("MySQL JDBC Driver not found.", e);
			}
			connection = DriverManager.getConnection(serverUrl, userString, passwordString);

		}catch (SQLException e){
		}
	};
	
	public static Connection getConnection() {
				new DBConnection();
                return connection;
        }

        public static void closeConnection() {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
	
}
