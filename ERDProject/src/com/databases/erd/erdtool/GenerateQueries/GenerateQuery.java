package com.databases.erd.erdtool.GenerateQueries;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;

import com.databases.erd.erdtool.XmlToDb.xmltodb;

public class GenerateQuery
{
    public static void Generate(String dbname)
    {
        ArrayList<String> tables = new ArrayList<String>();
        ArrayList<String> columns = new ArrayList<String>();
        ArrayList<String> types = new ArrayList<String>();
        ArrayList<String> precisions = new ArrayList<String>();

        tables = xmltodb.gettables();
        xmltodb.check_wordList();
        String path = "xmls\\" + dbname + "Query.txt";
        File file = new File(path);
        String Allqry = "";
        for (String table : tables)
        {
            columns = xmltodb.getcolumns(table);
            types = xmltodb.getType(table);
            precisions = xmltodb.getPrecision(table);

            String qry = "create table " + table + "(";

            for (int i = 0; i < columns.size(); i++)
            {

                qry = qry + columns.get(i) + " ";

                if (types.get(i).equals("double") || types.get(i).equals("DOUBLE") || types.get(i).equals("datetime") || types.get(i).equals("DATETIME") || types.get(i).equals("INT")
                        || types.get(i).equals("BIGINT"))
                {
                    qry = qry + convert.convertToSQLServerType(types.get(i)) + " ";
                }
                else
                {
                    qry = qry + convert.convertToSQLServerType(types.get(i)) + "(" + precisions.get(i) + ") ";
                }

                String checkNull = xmltodb.check_null(table, columns.get(i));

                if (checkNull.equals("NN"))
                {
                    qry = qry + "not null ";
                }

                String checkKey = xmltodb.getKey(table, columns.get(i));
                if (checkKey.equals("PU"))
                {
                    qry = qry + "primary key ";
                }

                if (checkKey.equals("U"))
                {
                    qry = qry + "unique key ";
                }
                if (i == columns.size() - 1)
                {
                    qry = qry + ");";
                }
                else
                {
                    qry = qry + ",";
                }
            }

            Allqry = Allqry + qry;
            Allqry = Allqry + "\n\n";
        }

        try
        {
            FileUtils.writeStringToFile(file, Allqry);
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
