package com.databases.erd.erdtool.XmlToDb;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.*;
import javax.xml.parsers.*;
/***
 * 
 * @author trainee3
 *
 */
public class xmltodb
{
	static DocumentBuilderFactory docBuilderFactory;
	static DocumentBuilder docBuilder;
	static Document doc;
	static ArrayList<String> foreign_key=new ArrayList<String>();
	static ArrayList<String> unique_key=new ArrayList<String>();
	static HashMap<Integer, String> tm = new HashMap<Integer, String>(); 
	private static  String path=null;

public xmltodb(String pa)
{
	path=pa;
}
	
	
public xmltodb() {
	if(path==null)
	{
	}
	
}


public static ArrayList<String> getcolumns(String s) 
{
	ArrayList<String> tab=new ArrayList<String>();
	ArrayList<String> tab1=new ArrayList<String>();

	try
	{
		docBuilderFactory = DocumentBuilderFactory.newInstance();
		docBuilder = docBuilderFactory.newDocumentBuilder();
		doc = docBuilder.parse (new File(path));
		doc.getDocumentElement().normalize();
		NodeList tableList = doc.getElementsByTagName("table");
	    NodeList column = doc.getElementsByTagName("columns");
		for (int temp = 0; temp < tableList.getLength(); temp++) 
		{
		    	Node n1=tableList.item(temp);
		    	if(n1.getNodeType()== Node.ELEMENT_NODE)
		    	{
		    		Element element1 = (Element) n1;
		    		String str= element1.getElementsByTagName("logical_name").item(0).getTextContent();
		    		if(str.equals(s))
		    		{	
		    			
		    			Node n2=column.item(temp);
		    			int c =n2.getChildNodes().getLength();
		    			c=(c-1)/2;
		    			for(int temp1=0;temp1<c;temp1++)
		    			{
		    				if(n2.getNodeType()==Node.ELEMENT_NODE)
		    				{
		    					Element element2=(Element) n2;
		    					tab.add(element2.getElementsByTagName("word_id").item(temp1).getTextContent());
		    						
		    				}
		    			}
		    		}
		    		else
		    		{
		    			continue;
		    		}
		    	}
		    }
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	for(String val:tab)
	{
		
		tab1.add(word_id(new Integer(val)));
		
	}
	

return tab1;	
}

public static void check_wordList()
{
	try
	{
		
		docBuilderFactory = DocumentBuilderFactory.newInstance();
	    
		docBuilder = docBuilderFactory.newDocumentBuilder();
		doc = docBuilder.parse (new File(path));
		doc.getDocumentElement().normalize();
		NodeList dict=doc.getElementsByTagName("word");
		for(int i=0;i<dict.getLength();i++)
		{  
			Node word=dict.item(i);
			Element w=(Element)word;
			if(word.getNodeType()==Node.ELEMENT_NODE)
			{
				String wordidval=w.getElementsByTagName("id").item(0).getTextContent();
				String columnName=w.getElementsByTagName("logical_name").item(0).getTextContent();
				tm.put(new Integer(wordidval),columnName);
					 
			}
		}
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
		
}
public static ArrayList<String> getType(String s)
{
	ArrayList<String> column_type=new ArrayList<String>();
	try {

				docBuilderFactory = DocumentBuilderFactory.newInstance();
				docBuilder = docBuilderFactory.newDocumentBuilder();
				doc = docBuilder.parse (new File(path));
				doc.getDocumentElement().normalize();
				NodeList tableList = doc.getElementsByTagName("table");
			    NodeList column1 = doc.getElementsByTagName("columns");
			    for (int temp = 0; temp < tableList.getLength(); temp++) 
			    {
			    	Node n1=tableList.item(temp);
			    	if(n1.getNodeType()== Node.ELEMENT_NODE)
			    	{
			    		Element element1 = (Element) n1;
			    		String str= element1.getElementsByTagName("logical_name").item(0).getTextContent();
			    		if(str.equals(s))
			    		{	
			    		//	column_type.add(element1.getElementsByTagName("logical_name").item(0).getTextContent());
			    			Node n2=column1.item(temp);
			    			int c1 =n2.getChildNodes().getLength();
			    			c1=(c1-1)/2;
			    			for(int i=0;i<c1;i++)
						    {
						    	
						    	if(n1.getNodeType()==Node.ELEMENT_NODE)
				    			{
				    				Element element=(Element) n1;
				    				column_type.add(element.getElementsByTagName("type").item(i).getTextContent());
				  
				    			}
						    	
						    }
			    		}
			    		else
			    		{
			    			continue;
			    		}
			    	}
			    }
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	//System.out.println(column_type);
	return column_type;
}

public static ArrayList<String> getPrecision(String s)
{
	ArrayList<String> precision=new ArrayList<String>();
	try {

				docBuilderFactory = DocumentBuilderFactory.newInstance();
				docBuilder = docBuilderFactory.newDocumentBuilder();
				doc = docBuilder.parse (new File(path));
				doc.getDocumentElement().normalize();
				NodeList tableList = doc.getElementsByTagName("table");
			    NodeList column = doc.getElementsByTagName("columns");
			   
			    for (int temp = 0; temp < tableList.getLength(); temp++) 
			    {
			    	Node n1=tableList.item(temp);
			    	if(n1.getNodeType()== Node.ELEMENT_NODE)
			    	{
			    		Element element1 = (Element) n1;
			    		String str= element1.getElementsByTagName("logical_name").item(0).getTextContent();
			    		if(str.equals(s))
			    		{	
			    			
			    			Node n2=column.item(temp);
			    			int c =n2.getChildNodes().getLength();
			    			c=(c-1)/2;
			    			
			    			for(int temp1=0;temp1<c;temp1++)
			    			{
			    				if(n2.getNodeType()==Node.ELEMENT_NODE)
			    				{
			    					Element element2=(Element) n2;
			    					precision.add(element2.getElementsByTagName("precision").item(temp1).getTextContent());
			    					
			    				}
			    			}
			    		}
			    		else
			    		{
			    			continue;
			    		}
			    	}
			    }
			   
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	return precision;	
}

public static ArrayList<String> gettables() 
{ 
	

	ArrayList<String> tables=new ArrayList<String>();
	try 
	{
				docBuilderFactory = DocumentBuilderFactory.newInstance();
				docBuilder = docBuilderFactory.newDocumentBuilder();
				doc = docBuilder.parse (new File(path));
				doc.getDocumentElement().normalize();
				NodeList tableList = doc.getElementsByTagName("table");
				for (int temp = 0; temp < tableList.getLength(); temp++) 
				{
					Node nNode = tableList.item(temp);
			 		if (nNode.getNodeType() == Node.ELEMENT_NODE) {
			 
						Element eElement = (Element) nNode;
						tables.add(eElement.getElementsByTagName("logical_name").item(0).getTextContent());
					}
				}
	} 
	catch (Exception err) 
	{
			System.out.println(" " + err.getMessage ());
	}
	return tables;
}
public static String getKey(String table_name,String colname)
{
	String a="";
	ArrayList<String> primary_key=new ArrayList<String>();
	ArrayList<String> foreign_key=new ArrayList<String>();
    ArrayList<String> unique_key=new ArrayList<String>();
	try
	{
		docBuilderFactory = DocumentBuilderFactory.newInstance();
		docBuilder = docBuilderFactory.newDocumentBuilder();
		doc = docBuilder.parse (new File(path));
		doc.getDocumentElement().normalize();
		NodeList tableList = doc.getElementsByTagName("table");
	    NodeList column = doc.getElementsByTagName("columns");
	    for (int temp = 0; temp < tableList.getLength(); temp++) 
	    {
	    	Node n1=tableList.item(temp);
	    	if(n1.getNodeType()== Node.ELEMENT_NODE)
	    	{
	    		Element element1 = (Element) n1;
	    		String str= element1.getElementsByTagName("logical_name").item(0).getTextContent();
	    	
	    		if(str.equals(table_name))
	    		{	
	    			
	    			Node n2=column.item(temp);
	    			int c =n2.getChildNodes().getLength();
	    			c=(c-1)/2;
	    			for(int temp1=0;temp1<c;temp1++)
	    			{
	    				if(n2.getNodeType()==Node.ELEMENT_NODE)
	    				{
	    					Element element2=(Element) n2;
	    					
	    					String s=element2.getElementsByTagName("primary_key").item(temp1).getTextContent();
	    				
	    					if(s.equals("true"))
	    					{
	    						primary_key.add(element2.getElementsByTagName("logical_name").item(temp1).getTextContent());
	    					}
	    					else
	    					{
	    						continue;
	    					}
	    				}
	    			}
	    			for(int temp1=0;temp1<c;temp1++)
	    			{
	    				if(n2.getNodeType()==Node.ELEMENT_NODE)
	    				{
	    					Element element2=(Element) n2;
	    					String s=element2.getElementsByTagName("foreign_key").item(temp1).getTextContent();
	    					if(s.equals("true"))
	    					{
	    						foreign_key.add(element2.getElementsByTagName("logical_name").item(temp1).getTextContent());
	    					}
	    					else
	    					{
	    						continue;
	    					}
	    				}
	    			}
	    			for(int temp1=0;temp1<c;temp1++)
	    			{
	    				if(n2.getNodeType()==Node.ELEMENT_NODE)
	    				{
	    					Element element2=(Element) n2;
	    					String s=element2.getElementsByTagName("unique_key").item(temp1).getTextContent();
	    					if(s.equals("true"))
	    					{
	    						unique_key.add(element2.getElementsByTagName("logical_name").item(temp1).getTextContent());
	    					}
	    					else
	    					{
	    						continue;
	    					}
	    				}
	    			}
	    		}
	    		else
	    		{
	    			continue;
	    		}
	    	}
	    }
}
	catch(Exception e)
	{
		e.printStackTrace();
		
	}
	
	for(String var:primary_key)
	{
		if(var.equals(colname))
		{
			a=a+"P";
		}
	}
	for(String var:foreign_key)
	{
		if(var.equals(colname))
		{
			a=a+"F";
		}
	}
	for(String var:unique_key)
	{
		if(var.equals(colname))
		{
			a=a+"U";
		}
	}
	
	
	return a;
}
 public static String check_null(String table_name,String colname)
 {
	 ArrayList<String> null_type=new ArrayList<String>();
	 String ar="";
		try {

					docBuilderFactory = DocumentBuilderFactory.newInstance();
					docBuilder = docBuilderFactory.newDocumentBuilder();
					doc = docBuilder.parse (new File(path));
					doc.getDocumentElement().normalize();
					NodeList tableList = doc.getElementsByTagName("table");
				    NodeList column = doc.getElementsByTagName("columns");
				  
				    for (int temp = 0; temp < tableList.getLength(); temp++) 
				    {
				    	Node n1=tableList.item(temp);
				    	if(n1.getNodeType()== Node.ELEMENT_NODE)
				    	{
				    		Element element1 = (Element) n1;
				    		String str= element1.getElementsByTagName("logical_name").item(0).getTextContent();
				    		if(str.equals(table_name))
				    		{	
				    			
				    			Node n2=column.item(temp);
				    			int c =n2.getChildNodes().getLength();
				    			c=(c-1)/2;
				    			for(int temp1=0;temp1<c;temp1++)
				    			{
				    				if(n2.getNodeType()==Node.ELEMENT_NODE)
				    				{
				    					Element element2=(Element) n2;
				    					
				    					String s=element2.getElementsByTagName("not_null").item(temp1).getTextContent();
				    				
				    					if(s.equals("true"))
				    					{
				    						null_type.add(element2.getElementsByTagName("logical_name").item(temp1).getTextContent());
				    					}
				    					else
				    					{
				    						continue;
				    					}
				    				}
				    			}
				    		}
				    		else
				    		{
				    			continue;
				    		}
				    	}
				    }
				   
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
		for(String var:null_type)
		{
			if(var.equals(colname))
			{
				ar=ar+"NN";
			}
		}
		return ar;	
 }
public static ArrayList<String> check_relation(String table)
{
	ArrayList<String> null_type=new ArrayList<String>();
	ArrayList<String> tables=new ArrayList<String>();
	ArrayList<String> final_tables=new ArrayList<String>();
		try {

					docBuilderFactory = DocumentBuilderFactory.newInstance();
					docBuilder = docBuilderFactory.newDocumentBuilder();
					doc = docBuilder.parse (new File(path));
					doc.getDocumentElement().normalize();
					NodeList tableList = doc.getElementsByTagName("table");
				    NodeList relation = doc.getElementsByTagName("connections");
				  
				    for (int temp = 0; temp < tableList.getLength(); temp++) 
				    {
				    	Node n1=tableList.item(temp);
				    	if(n1.getNodeType()== Node.ELEMENT_NODE)
				    	{
				    		Element element1 = (Element) n1;
				    		String str= element1.getElementsByTagName("logical_name").item(0).getTextContent();
				    		tables.add(str);
				    		
				    		if(str.equals(table))
				    		{	
				    			
				    			Node n2=relation.item(temp);
				    			int c =n2.getChildNodes().getLength();
				    			c=(c-1)/2;	
				    		       for(int temp1=0;temp1<c;temp1++){
				    			
				    				if(n2.getNodeType()==Node.ELEMENT_NODE)
				    				{
				    					Element element2=(Element) n2;
				    					
				    					
				    					null_type.add(element2.getElementsByTagName("source").item(temp1).getTextContent());
				    		
				    				}
				    			}
				    		}
				    		else
				    		{
				    			continue;
				    		}
				    	}
				    }
				   
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		for(int j=0;j<null_type.size();j++)
		{	String index=null_type.get(j);
			
			final_tables.add(tables.get(Integer.parseInt(index)));
		}
		
		return final_tables;
	 }

public static String word_id(int key)
{
	String i="";
	for(Map.Entry<Integer, String> entry  :tm.entrySet())
	{   
		if(entry.getKey()==key)
		{
		       i=i+entry.getValue();
			   break;
		}
	}
	return i;
}
public static ArrayList<String> get_X_Y(String table)
{
	ArrayList<String> tables=new ArrayList<String>();
	try 
	{
				docBuilderFactory = DocumentBuilderFactory.newInstance();
				docBuilder = docBuilderFactory.newDocumentBuilder();
				doc = docBuilder.parse (new File(path));
				doc.getDocumentElement().normalize();
				NodeList tableList = doc.getElementsByTagName("table");
				
				for (int temp = 0; temp < tableList.getLength(); temp++) 
				{
					Node n1 = tableList.item(temp);
					if(n1.getNodeType()== Node.ELEMENT_NODE)
			    	{
						Element element1 = (Element) n1;
			    		String str= element1.getElementsByTagName("logical_name").item(0).getTextContent();
			    		if(str.equals(table))
			    		{	
			    			if (n1.getNodeType() == Node.ELEMENT_NODE) 
			    			{
			    				
			    				tables.add(element1.getElementsByTagName("x").item(0).getTextContent());
			    				tables.add(element1.getElementsByTagName("y").item(0).getTextContent());
			    			}
			    		}
					}
				}
	} 
	catch (Exception err) 
	{
			System.out.println(" " + err.getMessage ());
	}

	return tables;
}


public static ArrayList<Integer> getcount(String tbname) 
{
	ArrayList<Integer> count=new ArrayList<Integer>();
	int p=0,f=0;
	try
	{
		docBuilderFactory = DocumentBuilderFactory.newInstance();
		docBuilder = docBuilderFactory.newDocumentBuilder();
		doc = docBuilder.parse (new File(path));
		doc.getDocumentElement().normalize();
		NodeList tableList = doc.getElementsByTagName("table");
	    NodeList column = doc.getElementsByTagName("columns");
	    for (int temp = 0; temp < tableList.getLength(); temp++) 
	    {
	    	Node n1=tableList.item(temp);
	    	if(n1.getNodeType()== Node.ELEMENT_NODE)
	    	{
	    		Element element1 = (Element) n1;
	    		String str= element1.getElementsByTagName("logical_name").item(0).getTextContent();
	    	
	    		if(str.equals(tbname))
	    		{	    			
	    			Node n2=column.item(temp);
	    			int c =n2.getChildNodes().getLength();
	    			c=(c-1)/2;
	    			for(int temp1=0;temp1<c;temp1++)
	    			{
	    				if(n2.getNodeType()==Node.ELEMENT_NODE)
	    				{
	    					Element element2=(Element) n2;
	    					
	    					String s=element2.getElementsByTagName("primary_key").item(temp1).getTextContent();
	    				
	    					if(s.equals("true"))
	    					{

	    						p++;
	    					}
	    					else
	    					{
	    						continue;
	    					}
	    				}
	    			}
	    			for(int temp1=0;temp1<c;temp1++)
	    			{
	    				if(n2.getNodeType()==Node.ELEMENT_NODE)
	    				{
	    					Element element2=(Element) n2;
	    					String s=element2.getElementsByTagName("foreign_key").item(temp1).getTextContent();
	    					if(s.equals("true"))
	    					{
	    						f++;
	    					}
	    					else
	    					{
	    						continue;
	    					}
	    				}
	    			}
	    		}
	    		else
	    		{
	    			continue;
	    		}
	    	}
	    }
}
	catch(Exception e)
	{
		e.printStackTrace();
		
	}
	
	count.add(0,p);
	count.add(1,f);
	if(p==0 && f==0)
		count.add(2,1);
	else
		count.add(2,0);
	return count;
}
public static int getcolcount(String tbname) 
{
	int c = 0;
	try
	{
		docBuilderFactory = DocumentBuilderFactory.newInstance();
		docBuilder = docBuilderFactory.newDocumentBuilder();
		doc = docBuilder.parse (new File(path));
		doc.getDocumentElement().normalize();
		NodeList tableList = doc.getElementsByTagName("table");
	    NodeList column = doc.getElementsByTagName("columns");
		for (int temp = 0; temp < tableList.getLength(); temp++) 
		{
		    	Node n1=tableList.item(temp);
		    	if(n1.getNodeType()== Node.ELEMENT_NODE)
		    	{
		    		Element element1 = (Element) n1;
		    		String str= element1.getElementsByTagName("logical_name").item(0).getTextContent();
		    		if(str.equals(tbname))
		    		{	
		    			
		    			Node n2=column.item(temp);
		    			c =n2.getChildNodes().getLength();
		    			c=(c-1)/2;
		    		}
		    	}
		}
	}
		catch(Exception ex){
		}	
	
		return c;
	
}
public static int tbcount()
{
	int cnt = 0;
	boolean pri,fore;
	try
	{
		docBuilderFactory = DocumentBuilderFactory.newInstance();
		docBuilder = docBuilderFactory.newDocumentBuilder();
		doc = docBuilder.parse (new File(path));
		doc.getDocumentElement().normalize();
		NodeList tableList = doc.getElementsByTagName("table");
	    NodeList column = doc.getElementsByTagName("columns");
		for (int temp = 0; temp < tableList.getLength(); temp++) 
		{
			
			Node n1=tableList.item(temp);
	    	if(n1.getNodeType()== Node.ELEMENT_NODE)
	    	{
	    		pri=fore=false;
	    			Node n2=column.item(temp);
	    			int c =n2.getChildNodes().getLength();
	    			c=(c-1)/2;
	    			for(int temp1=0;temp1<c;temp1++)
	    			{
	    				if(n2.getNodeType()==Node.ELEMENT_NODE)
	    				{
	    					Element element2=(Element) n2;
	    					
	    					String s=element2.getElementsByTagName("primary_key").item(temp1).getTextContent();
	    				
	    					if(s.equals("true"))
	    					{
	    						pri=true;
	    					}
	    				}
	    				if(n2.getNodeType()==Node.ELEMENT_NODE)
		    				{
		    				Element element2=(Element) n2;
		    				
	    					String s1=element2.getElementsByTagName("foreign_key").item(temp1).getTextContent();
	    					if(s1.equals("true"))
	    					{
	    						fore=true;
	    						
	    					}
	    				}
	    			if(pri==true&&fore==true)
	    			{cnt++;
	    			break;}
	    			}	
	    	}	
		}
}
	catch(Exception e)
	{
		e.printStackTrace();
		
	}
	//System.out.println(cnt);
	return cnt;
	
}
public static ArrayList<String> phyname()
{
	ArrayList<String> phyn=new ArrayList<String>();
	/*final String[] numNames = {
		       " one",		       " two",		       " three",		       " four",		       " five",
		       " six",		       " seven",		       " eight",		       " nine",		       " ten",
		       " eleven",		       " twelve",		       " thirteen",		       " fourteen",		       " fifteen",
		       " sixteen",		       " seventeen",		       " eighteen",		       " nineteen",		       "twenty"
		   };*/
	try 
	{
				docBuilderFactory = DocumentBuilderFactory.newInstance();
				docBuilder = docBuilderFactory.newDocumentBuilder();
				doc = docBuilder.parse (new File(path));
				doc.getDocumentElement().normalize();
				NodeList tableList = doc.getElementsByTagName("table");
				for (int temp = 1; temp <=tableList.getLength(); temp++) 
				{
					phyn.add("TAB"+temp);
			 		
				}
	} 
	catch (Exception err) 
	{
			System.out.println(" " + err.getMessage ());
	}
	//System.out.println(phyn);
	return phyn;
}




public static ArrayList<Integer> tabid()
{
	ArrayList<Integer> sour= new ArrayList<Integer>();
	ArrayList<Integer> targ= new ArrayList<Integer>();
	try
	{
		docBuilderFactory = DocumentBuilderFactory.newInstance();
		docBuilder = docBuilderFactory.newDocumentBuilder();
		doc = docBuilder.parse (new File(path));
		doc.getDocumentElement().normalize();
		NodeList tableList = doc.getElementsByTagName("table");
	    NodeList conn = doc.getElementsByTagName("connections");
		for (int temp = 0; temp < tableList.getLength(); temp++) 
		{
			Node n1=tableList.item(temp);
	    	if(n1.getNodeType()== Node.ELEMENT_NODE)
	    	{
	    			Node n2=conn.item(temp);
	    	    			int c =n2.getChildNodes().getLength();
	    	    			c=(c-1)/2;
	    	    			for(int temp1=0;temp1<c;temp1++)
	    	    			{
	    	    				if(n2.getNodeType()==Node.ELEMENT_NODE)
	    	    				{
	    	    					Element element2=(Element) n2;
	    					
	    					String target=element2.getElementsByTagName("target").item(temp1).getTextContent();
	    					String source=element2.getElementsByTagName("source").item(temp1).getTextContent();
	    					sour.add(Integer.parseInt(source));
	    					targ.add(Integer.parseInt(target));
	    	    				}
	    	    			}
	    	}
	    	else
	    		continue;
		}
	}
	
catch(Exception e)
  		{
    			e.printStackTrace();
   		}
//System.out.println(sour);
//System.out.println(targ);
return null;
	
}

public static String getTabName(int i)
{
	ArrayList<String> tables=new ArrayList<String>();
	tables=gettables();
	String table_name = null;
	for(int j=0;j<tables.size();j++)
	{
		if(j==i)
		{
			table_name=tables.get(j);
		}
	}
	return table_name;
}
public static int getcolumnindex(String tbname,String col) 
{
	int index=0;
	try
	{
		docBuilderFactory = DocumentBuilderFactory.newInstance();
		docBuilder = docBuilderFactory.newDocumentBuilder();
		doc = docBuilder.parse (new File(path));
		doc.getDocumentElement().normalize();
		NodeList tableList = doc.getElementsByTagName("table");
	    NodeList column = doc.getElementsByTagName("columns");
		for (int temp = 0; temp < tableList.getLength(); temp++) 
		{
		    	Node n1=tableList.item(temp);
		    	if(n1.getNodeType()== Node.ELEMENT_NODE)
		    	{
		    		Element element1 = (Element) n1;
		    		String str= element1.getElementsByTagName("logical_name").item(0).getTextContent();
		    		if(str.equals(tbname))
		    		{	
		    			Node n2=column.item(temp);
		    			int c =n2.getChildNodes().getLength();
		    			c=(c-1)/2;
		    			
		    			for(int temp1=0;temp1<c;temp1++)
		    			{
		    				if(n2.getNodeType()==Node.ELEMENT_NODE)
		    				{
			    				Element element2=(Element) n2;
			    				int coln=new Integer(element2.getElementsByTagName("word_id").item(temp1).getTextContent());
			    			
			    				String coln1=word_id(coln);
			    				
			    				
			    				if(coln1.equalsIgnoreCase(col))
		    					{
			    					index=temp1;
			    					break;
		    					}	
			    				else
			    				{
			    					continue;
			    				}

		    				}
		    				else
		    				{
		    					continue;
		    				}
		    			}
		    			}
		    		}
		    		else
		    		{
		    			continue;
		    		}
		    	}
		    
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	

return index;	
}

}
