package com.databases.erd.erdtool.GenerateQueries;

public class convert {
     public static String convertToSQLServerType(String type)
     {
    	 if(type.equals("INT"))
    		 return "int";
    	 
    	 if(type.equals("VARCHAR"))
    		 return "nvarchar";
    	 
    	 if(type.equals("FLOAT"))
    		 return "float";
    	 
    	 if(type.equals("BIGINT")||type.equals("bigint"))
    		 return "bigint";
    	 
    	 if(type.equals("CHAR"))
    		 return "nchar";
    	 
    	 if(type.equals("DATETIME"))
    		 return "datetime";
    	 
    	 if(type.equals("DOUBLE"))
    		 return "real";
    	 
    	 if(type.equals("LONGTEXT"))
    		 return "text";
		return null;
    	 
    	 
    	 
    	 
     }
}
