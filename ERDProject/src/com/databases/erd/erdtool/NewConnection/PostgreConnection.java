package com.databases.erd.erdtool.NewConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import com.databases.erd.erdtool.dialogs.MigrationDialog;

public class PostgreConnection {

	private static Connection conn=null;

	public static void setConnection(MigrationDialog d)
	{



		try {

			Class.forName("org.postgresql.Driver");


			Connection con = DriverManager.getConnection(
					"jdbc:postgresql://"+d.getHostName()+":"+d.getPortName()+"/",d.getUserName(),d.getPassword());


			Statement st=con.createStatement();
			String crdb="create database "+d.getDatabaseName();
			st.executeUpdate(crdb);

			conn=DriverManager.getConnection(
					"jdbc:postgresql://"+d.getHostName()+":"+d.getPortName()+"/"+d.getDatabaseName(),d.getUserName(),d.getPassword());



		} catch (Exception e) {

			System.out.println("Where is your PostgreSQL JDBC Driver? Include in your library path!");
			e.printStackTrace();


		}
	}

	public static Connection getConnection()
	{
		return conn;
	}


}
