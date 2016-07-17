package com.databases.erd.erdtool.db2xml;

import java.io.File;
import java.io.StringReader;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.w3c.dom.*;

import com.databases.erd.erdtool.NewConnection.getConnect;
import com.databases.erd.erdtool.XmlToDb.xmltodb;
import com.databases.erd.erdtool.dialogs.OpenNewConnectionDialog;
import com.mysql.jdbc.DatabaseMetaData;
import com.mysql.jdbc.ResultSetMetaData;

import org.apache.commons.io.FileUtils;

/**
 * 
 * @author ABHISHEK
 *
 */

public class db2xml
{

    /* static variables used across different methods  */
    static ResultSet rs = null;
    static ResultSet rs1 = null;
    static Statement stmt = null;
    static Connection conn = null;
    static DatabaseMetaData dsmd = null;
    static String tbname = null;
    static DocumentBuilderFactory factory = null;
    static Element results = null;
    static DocumentBuilder builder = null;
    static org.w3c.dom.Document doc = null;
    static String dbname = null;
    static DOMSource source = null;
    static TransformerFactory transformerFactory;
    static Transformer transformer;
    static StreamResult result;
    static Element row;
    static Element contents;
    static Element node;
    static HashMap<Integer, String> hm = new HashMap<Integer, String>();
    static Element foreign_key;
    static Element primary_key;
    static Element connections;
    static int relationid = 0;
    // hash map for tables
    static HashMap<Integer, String> tm = new HashMap<Integer, String>();
    // hash map for normal column
    static HashMap<Integer, String> nc = new HashMap<Integer, String>();

    /* main method and calls connect
       *  create an instance of transformer
       *   */

    public static void main(String db)

    {
        transformerFactory = TransformerFactory.newInstance();
        try
        {
            transformer = transformerFactory.newTransformer();
        }
        catch (TransformerConfigurationException e1)
        {

            e1.printStackTrace();
        }
        /*  Scanner sc=new Scanner(System.in);
          System.out.println("Enter the name of Database");*/
        dbname = db;// sc.nextLine();
        connect();
    }

/* 
 * method to generate an xml document
 *  */
    public static void createdoc(org.w3c.dom.Document docu)
    {
        try
        {

            source = new DOMSource(docu);
            StringWriter writer = new StringWriter();
            StreamResult result = new StreamResult(writer);
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.transform(source, result);
            String s = format(writer.toString());
            // System.out.println(s);
            String path = "xmls\\" + dbname + ".xml";
            File file = new File(path);
            new xmltodb(path);
            /*    FileWriter fileWriter = new FileWriter(file,true);
            BufferedWriter bufferFileWriter  = new BufferedWriter(fileWriter);
            result = new StreamResult(bufferFileWriter);
            transformer.transform(source, result);
            System.out.println("File saved!"); */
            FileUtils.writeStringToFile(file, s);
            String path1 = "xmls\\" + dbname + ".txt";
            File file1 = new File(path1);
            FileUtils.writeStringToFile(file1, s);
        }
        catch (Exception doce)
        {
            doce.printStackTrace();
        }
    }

/* method to make connection and generating the root element diagram */
    public static void connect()
    {
        try
        {
            conn = new getConnect().mysqlDbConnect(OpenNewConnectionDialog.getConnectionModel(), dbname);
            factory = DocumentBuilderFactory.newInstance();
            builder = factory.newDocumentBuilder();
            doc = builder.newDocument();
            results = (Element) doc.createElement("diagram");
            doc.appendChild((Node) results);
            //calling createdict to create dictionary 
            createdict();
            // calling create_contents 
            create_contents();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

/* method to create dictionary */
    public static void createdict() throws SQLException
    {
        int wordidval = 0;
        Element dictionary = (Element) doc.createElement("dictionary");
        ((Node) results).appendChild((Node) dictionary);
        dsmd = (DatabaseMetaData) conn.getMetaData();
        ResultSet tables = dsmd.getTables(null, null, "%", null);
        ArrayList<String> tl = new ArrayList<String>();

        while (tables.next())
        {
            tl.add(tables.getString(3));
        }
        for (String val : tl)
        {
            PreparedStatement cl = conn.prepareStatement("Select * from " + val);
            ResultSet rs = cl.executeQuery();
            ResultSetMetaData dictmd = (ResultSetMetaData) rs.getMetaData();
            int colCount = dictmd.getColumnCount();

            for (int i = 1; i <= colCount; i++)
            {
                String columnName = dictmd.getColumnName(i);
                /* checking whether column name is previously used */
                boolean b = check(columnName);
                if (b == true)
                {
                    Element word = (Element) doc.createElement("word");
                    ((Node) dictionary).appendChild((Node) word);
                    createnode("id", Integer.toString(wordidval), word);
                    hm.put(new Integer(wordidval), columnName);
                    wordidval++;
                    int l = columnName.length();
                    String len = Integer.toString(l);
                    createnode("length", len, word);
                    int scale = dictmd.getScale(i);
                    String temp;
                    if (scale == 0)
                        temp = "null";
                    else
                        temp = Integer.toString(scale);
                    createnode("decimal", temp, word);
                    createnode("args", null, word);
                    createnode("description", null, word);
                    createnode("logical_name", columnName, word);
                    String type = dictmd.getColumnTypeName(i);
                    createnode("type", type, word);
                }
                else
                {
                    continue;
                }
            }
        }
    }

/* 
 * 
 * method to check the column name is previously used or not */
    public static boolean check(String colname)
    {
        boolean b = true;
        for (Map.Entry<Integer, String> entry : hm.entrySet())
        {
            if (entry.getValue().equals(colname))
            {
                b = false;
                break;
            }
        }
        return b;
    }

/* method to find word_id */
    public static int word_id(String colName)
    {
        int i = 0;
        for (Map.Entry<Integer, String> entry : hm.entrySet())
        {
            if (entry.getValue().equals(colName))
            {
                i = entry.getKey();
                break;
            }
        }
        return i;
    }

/* method to generate contents */
    public static void create_contents()
            throws ParserConfigurationException, SQLException
    {
        int normal_column_id = 0;
        contents = (Element) doc.createElement("contents");
        ((Node) results).appendChild((Node) contents);
        dsmd = (DatabaseMetaData) conn.getMetaData();
        ResultSet tb = dsmd.getTables(null, null, "%", null);
        ArrayList<String> al = new ArrayList<String>();
        while (tb.next())
        {
            al.add(tb.getString(3));
        }

        normalcol(al);

        int tablecount = 0;
        // putting tableid and name in a hash map 
        for (String tablename : al)
        {
            tm.put(tablecount, tablename);
            tablecount++;
        }

        for (String val : al)
        {
            System.out.println(val);
            PreparedStatement cl = conn.prepareStatement("Select * from " + val);
            ResultSet rs = cl.executeQuery();

            ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();
            int colCount = rsmd.getColumnCount();

            tbname = rsmd.getTableName(1);
            rs.next();
            row = (Element) doc.createElement("table");

            // row is used as tables 
            ((Node) contents).appendChild((Node) row);

            createnode("id", Integer.toString(table_id(val)), row);

            connections = (Element) doc.createElement("connections");
            ((Node) row).appendChild((Node) connections);

            createnode("logical_name", tbname, row);

            rs1 = (ResultSet) dsmd.getPrimaryKeys(null, null, tbname);
            createnode("primary_key_name", null, row);

            Element columns = (Element) doc.createElement("columns");
            ((Node) row).appendChild((Node) columns);
            String nn = null;

            for (int i = 1; i <= colCount; i++)
            {
                String columnName = rsmd.getColumnName(i);
                String type = rsmd.getColumnTypeName(i);
                boolean p = rsmd.isAutoIncrement(i);
                int q = rsmd.isNullable(i);

                if (q == 1)
                    nn = "false";
                else
                    nn = "true";
                Object pr = rsmd.getPrecision(i);
                node = (Element) doc.createElement("normal_column");
                ((Node) columns).appendChild((Node) node);

                // checking foreign key by calling checkForeignKey
                foreign_key = (Element) doc.createElement("foreign_key");
                String b = checkForeignKeys(conn, val, columnName, Integer.toString(table_id(val)));

                if (b == null)
                {
                    int temp_word_id = word_id(columnName);
                    createnode("word_id", Integer.toString(temp_word_id), node);
                }

                // call word_id method 
                createnode("id", Integer.toString(normal_column_id), node);
                normal_column_id++;

                if (b != null)
                {
                    int temp_word_id = word_id(columnName);
                    createnode("word_id", Integer.toString(temp_word_id), node);
                    createnode("referenced_column", Integer.toString(nkey(b)), node);
                }

                createnode("logical_name", columnName, node);

                createnode("type", type, node);

                createnode("auto_increment", Boolean.toString(p), node);

                ((Node) node).appendChild((Node) foreign_key);

                createnode("not_null", nn, node);

                primary_key = (Element) doc.createElement("primary_key");
                // checking primary key by calling checkPrimaryKey
                boolean check = checkPrimaryKey(val, columnName);
                ((Node) node).appendChild((Node) primary_key);

                Element unique_key = (Element) doc.createElement("unique_key");
                ((Node) node).appendChild((Node) unique_key);
                createnode("precision", pr.toString(), node);

                //checking unique key 
                if (check == true)
                {
                    ((Node) unique_key).appendChild(doc.createTextNode("true"));
                }

                if (check == false)
                {
                    ResultSet rs1 = dsmd.getIndexInfo(dbname, null, tbname, false, true);
                    while (rs1.next())
                    {

                        if (rs1.getString(9).equals(columnName) && rs1.getBoolean(4) == false)
                        {
                            ((Node) unique_key).appendChild(doc.createTextNode("true"));

                        }
                    }
                }
            }
        }
// calling createdoc method 	   
        createdoc(doc);
    }

    // method to return table_id
    public static int table_id(String tableName)
    {
        int i = 0;
        for (Map.Entry<Integer, String> entry : tm.entrySet())
        {
            if (entry.getValue().equals(tableName))
            {
                i = entry.getKey();
                break;
            }
        }
        return i;
    }

/*
 *  method to check the foreign key 
 *  and append relations in connections 
 *  */

    private static String checkForeignKeys(Connection connection, String tableName, String colname, String targetid) throws SQLException
    {

        DatabaseMetaData metaData = (DatabaseMetaData) connection.getMetaData();
        ResultSet foreignKeys = metaData.getImportedKeys(connection.getCatalog(), null, tableName);
        while (foreignKeys.next())
        {
            String fkColumnName = foreignKeys.getString("FKCOLUMN_NAME");
            String pkColumnName = foreignKeys.getString("PKCOLUMN_NAME");
            String pkTableName = foreignKeys.getString("PKTABLE_NAME");
            if (colname.equals(fkColumnName))
            {
                ((Node) foreign_key).appendChild(doc.createTextNode("true"));

                Element relation = (Element) doc.createElement("relation");
                ((Node) connections).appendChild((Node) relation);

                createnode("id", Integer.toString(relationid), relation);
                relationid++;
                createnode("source", Integer.toString(table_id(pkTableName)), relation);
                createnode("target", targetid, relation);
                createnode("child_cardinality", "1..n", relation);
                createnode("parent_cardinality", "1", relation);
                createnode("reference_for_pk", "true", relation);
                createnode("name", null, relation);
                return pkColumnName;
            }

        }
        return null;
    }

/* 
 * method to check primary key 
 * */
    public static boolean checkPrimaryKey(String tableName, String colname) throws SQLException
    {
        ResultSet rs = dsmd.getPrimaryKeys(null, null, tableName);
        if (rs.isBeforeFirst())
        {
            while (rs.next())
            {
                if (rs.getString(4).toString().equals(colname))
                {
                    ((Node) primary_key).appendChild(doc.createTextNode("true"));
                    return true;
                }

            }
        }
        else
        {
            ((Node) primary_key).appendChild(doc.createTextNode("false"));
            return false;
        }
        return false;
    }

/*
 *  method to create leaf node of XML 
 *  */

    public static void createnode(String tagname, String txt, Element source)
    {
        Element node = (Element) doc.createElement(tagname);
        if (txt != null)
            ((Node) node).appendChild(doc.createTextNode(txt));
        ((Node) source).appendChild((Node) node);
    }

    public static void normalcol(ArrayList<String> al)
    {
        int ncount = 0;
        for (String val : al)
        {
            try
            {
                PreparedStatement cl = conn.prepareStatement("Select * from " + val);
                ResultSet rs = cl.executeQuery();
                ResultSetMetaData md = (ResultSetMetaData) rs.getMetaData();

                for (int i = 1; i <= md.getColumnCount(); i++)
                {
                    nc.put(ncount, md.getColumnName(i).toString());
                    ncount++;
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
/*	
	System.out.println("Printing normal comlumn hash map::");
	for(Map.Entry<Integer, String> entry  :nc.entrySet())
 	{   
 		System.out.println("normal column id::"+entry.getKey()+"  normal column name::"+entry.getValue());
 	}
 	*/
    }

    public static int nkey(String colname)
    {
        int i = 0;
        for (Map.Entry<Integer, String> entry : nc.entrySet())
        {
            if (entry.getValue().equals(colname))
            {
                i = entry.getKey();
                break;
            }
        }
        return i;
    }

// Method to format XML

    public static String format(String xml)
    {
        try
        {
            Source xmlInput = new StreamSource(new StringReader(xml));
            StringWriter stringWriter = new StringWriter();
            StreamResult xmlOutput = new StreamResult(stringWriter);
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            transformerFactory.setAttribute("indent-number", 3);
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(xmlInput, xmlOutput);
            return xmlOutput.getWriter().toString();

        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }
}
