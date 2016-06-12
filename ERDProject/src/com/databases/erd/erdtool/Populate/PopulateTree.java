package com.databases.erd.erdtool.Populate;

import java.sql.Connection;
import java.sql.ResultSet;

import com.databases.erd.erdtool.NewConnection.getConnect;
import com.databases.erd.erdtool.dialogs.OpenNewConnectionDialog;
import com.databases.erd.erdtool.models.DBModel;
import com.mysql.jdbc.Statement;



public class PopulateTree {

	Connection conn;
	public PopulateTree()
	{
		
		conn = new getConnect().mysqlConnect(OpenNewConnectionDialog.aon);
	}
	
	public void populateInitial(DBModel n)
	{
		try
		{    
			String qry="Show databases";
			Statement st=(Statement) conn.createStatement();
		    ResultSet rs=st.executeQuery(qry);
			while(rs.next())
			{ 
				new DBModel(n,rs.getString(1));   
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
    }
}
