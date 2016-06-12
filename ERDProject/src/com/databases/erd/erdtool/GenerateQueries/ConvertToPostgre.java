package com.databases.erd.erdtool.GenerateQueries;

public class ConvertToPostgre {
	public static String convertToPostgreType(String type)
    {
   	 if(type.equalsIgnoreCase("INT"))
   		 return "integer";
   	 
   	 if(type.equalsIgnoreCase("VARCHAR"))
   		 return "VARCHAR";
   	 
   	 if(type.equalsIgnoreCase("FLOAT"))
   		 return "FLOAT";
   	 
   	 if(type.equalsIgnoreCase("BIGINT"))
   		 return "bigint";
   	 
   	 if(type.equalsIgnoreCase("CHAR"))
   		 return "char";
   	 
   	 if(type.equals("DATETIME"))
   		 return "date";
   	 
   	 if(type.equalsIgnoreCase("DOUBLE"))
   		 return "real";
   	 
   	 if(type.equalsIgnoreCase("LONGTEXT"))
   		 return "text";
		return null;
   }
}
