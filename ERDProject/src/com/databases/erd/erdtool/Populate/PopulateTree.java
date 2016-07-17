package com.databases.erd.erdtool.Populate;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.databases.erd.erdtool.connectionModel.ConnDialogModel;
import com.databases.erd.erdtool.dialogs.OpenNewConnectionDialog;
import com.databases.erd.erdtool.models.DBModel;
import com.databases.erd.erdtool.utils.ConnectionUtils;
import com.mysql.jdbc.Statement;

public class PopulateTree
{

    Connection conn;

    public PopulateTree()
    {
        ConnDialogModel connModel = OpenNewConnectionDialog.getConnectionModel();
        try
        {
            conn = ConnectionUtils.getMySQLConnection(connModel.getLocalStr(), connModel.getPortnoStr(),
                    connModel.getUsernameStr(), connModel.getPasswordStr());
        }
        catch (ClassNotFoundException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (SQLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void populateInitial(DBModel n)
    {
        try
        {
            String qry = "Show databases";
            Statement st = (Statement) conn.createStatement();
            ResultSet rs = st.executeQuery(qry);
            while (rs.next())
            {
                new DBModel(n, rs.getString(1));
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
