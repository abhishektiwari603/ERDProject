package com.databases.erd.erdtool.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 
 * @author ABHISHEK
 * @Since Jul 17, 2016
 */
public class ConnectionUtils {
	
	public static Connection getMySQLConnection(String host, String port, String userName, String password)
	          throws SQLException, ClassNotFoundException
	{					
		    String URL = "jdbc:mysql://" + host + "/" + port +"/";
			Class.forName("com.mysql.jdbc.Driver");	
			Connection conn = DriverManager.getConnection(URL, userName, password);
			return conn;			
	}

}
