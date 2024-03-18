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


	private DBConnection() {
		try {
			//read from properties
		    Properties props = new Properties();
		    
		    try {
		        InputStream in = Files.newInputStream(Paths.get("src/data/database.properties"));
		        props.load(in);
		        in.close();
		    } catch (IOException e) {
		        e.printStackTrace();
		    }

		    String serverUrl = props.getProperty("serverUrl");
		    String userString = props.getProperty("userString");
		    String passwordString = props.getProperty("passwordString");

			connection = DriverManager.getConnection(serverUrl, userString, passwordString);
			  
		}catch (SQLException e){
			e.printStackTrace();
		}
	};
	
	public static Connection getConnection() {
		if(connection == null) {
			new DBConnection();
		}
		return connection;
	}
	

	
}
