package com.databases.erd.erdtool.RunQueries;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.databases.erd.erdtool.NewConnection.PostgreConnection;

public class RunPostgreQuery
{
    @SuppressWarnings("unused")
    public static void run(String qry)
    {

        Connection connection = null;

        try
        {

            connection = PostgreConnection.getConnection();

            Statement st = connection.createStatement();

            st.executeUpdate(qry);

        }
        catch (SQLException e)
        {

            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            return;

        }

        if (connection != null)
        {
            System.out.println("You made it, take control your database now!");
        }
        else
        {
            System.out.println("Failed to make connection!");
        }

    }

}
