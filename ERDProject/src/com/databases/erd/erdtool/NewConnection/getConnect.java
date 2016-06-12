package com.databases.erd.erdtool.NewConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.databases.erd.erdtool.connectionModel.ConnDialogModel;



public class getConnect 
{
public static Connection conn;
public static String URL,JDBC,root;
	public Connection mysqlConnect(ConnDialogModel con)
	{
		try
		{	
				JDBC ="com.mysql.jdbc.Driver"; 
				URL = "jdbc:mysql://"+con.getLocalStr()+":"+con.getPortnoStr()+"/";
				root=con.getUsernameStr();

			System.out.println(URL);
			System.out.println(JDBC);

			Class.forName(JDBC); 		
			conn = DriverManager.getConnection(URL,root,"");
			System.out.println("Connection Established");
		}
		catch(Exception ex)
		{

		}
		return conn;
	}
	public Connection mysqlDbConnect(ConnDialogModel con,String dbname)
	{
		try
		{	
			JDBC ="com.mysql.jdbc.Driver"; 
			URL = "jdbc:mysql://"+con.getLocalStr()+":"+con.getPortnoStr()+"/"+dbname;
			root=con.getUsernameStr();
			System.out.println(URL);
			System.out.println(JDBC);

			Class.forName(JDBC); 		
			conn = DriverManager.getConnection(URL,root,"");
			System.out.println("Connection Established");
		}
		catch(Exception ex)
		{

		}
		return conn;
	}	
	
	
	
}
