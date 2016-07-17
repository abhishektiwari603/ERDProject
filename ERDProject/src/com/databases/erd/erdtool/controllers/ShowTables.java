package com.databases.erd.erdtool.controllers;

import java.util.ArrayList;

import com.databases.erd.erdtool.XmlToDb.xmltodb;
import com.databases.erd.erdtool.models.DBMetaColumnInfoModel;
import com.databases.erd.erdtool.models.DBMetaInfoModel;

/**
 * 
 * @author ABHISHEK
 * @Since Jul 17, 2016
 */
public class ShowTables
{

    public static ArrayList<DBMetaInfoModel> getTables()
    {

        ArrayList<DBMetaInfoModel> input = new ArrayList<DBMetaInfoModel>();

        ArrayList<String> tables = xmltodb.gettables();
        xmltodb.check_wordList();

        for (String table : tables)
        {
            DBMetaInfoModel model1 = new DBMetaInfoModel();
            ArrayList<DBMetaColumnInfoModel> field_info = new ArrayList<DBMetaColumnInfoModel>();
            model1.setOld_name(table);
            model1.setOld_description("");
            model1.setNew_name(table);

            ArrayList<String> columns = xmltodb.getcolumns(table);

            ArrayList<String> type = xmltodb.getType(table);
            ArrayList<String> precision = xmltodb.getPrecision(table);
            for (int i = 0; i < columns.size(); i++)
            {
                DBMetaColumnInfoModel dbcim = new DBMetaColumnInfoModel();
                dbcim.setOldfield_name(columns.get(i));
                dbcim.setOldfield_desc("");
                dbcim.setNewcolumn_name(columns.get(i));
                dbcim.setNew_alias("");

                String key = xmltodb.getKey(table, columns.get(i));
                if (key.equals(""))
                {
                    dbcim.setKey(false);
                    dbcim.setKey_column("");
                    dbcim.setKeyType("");
                }
                else
                {
                    dbcim.setKey(true);
                    dbcim.setKey_column(columns.get(i));
                    dbcim.setKeyType(key);
                }

                dbcim.setType(type.get(i));
                dbcim.setLength(Integer.parseInt(precision.get(i)));
                dbcim.setAlias("");
                dbcim.setDecimal("");

                field_info.add(dbcim);

            }
            model1.setFieldInfo(field_info);
            input.add(model1);
        }
        return input;
    }
}
