package com.databases.erd.erdtool.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 
 * @author ABHISHEK
 * @Since Jul 17, 2016
 */
public class ConnectionUtils
{

    /**
     * 
     * @param host
     * @param port
     * @param userName
     * @param password
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static Connection getMySQLConnection(String host, String port, String userName, String password)
            throws SQLException, ClassNotFoundException
    {
        String URL = "jdbc:mysql://" + host + "/" + port + "/";
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection(URL, userName, password);
        return conn;
    }

    /**
     * 
     * @param host
     * @param port
     * @param userName
     * @param password
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static Connection getPostgreConnection(String host, String port, String userName, String password)
            throws SQLException, ClassNotFoundException
    {
        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection(
                "jdbc:postgresql://" + host + ":" + port + "/", userName, password);
        return con;

    }

}
