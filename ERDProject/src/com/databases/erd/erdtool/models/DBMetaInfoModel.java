package com.databases.erd.erdtool.models;

import java.util.ArrayList;

public class DBMetaInfoModel 
{
	private String old_name;
	private String old_description;
	private String new_name;
	private boolean inc;
	private ArrayList<DBMetaColumnInfoModel> fieldInfo;
	
	public void setOld_name(String old_name)
	{
		this.old_name = old_name;
	}
	
	public String getOld_name()
	{
		return old_name;
	}
	
	public void setOld_description(String old_description)
	{
		this.old_description = old_description;
	}
	
	public String getOld_description() 
	{
		return old_description;
	}
	
	public void setNew_name(String new_name) 
	{
		this.new_name = new_name;
	}
	
	public String getNew_name() 
	{
		return new_name;
	}

	public void setInc(boolean inc) 
	{
		this.inc = inc;
	}

	public boolean isInc()
	{
		return inc;
	}

	public ArrayList<DBMetaColumnInfoModel> getFieldInfo() {
		return fieldInfo;
	}

	public void setFieldInfo(ArrayList<DBMetaColumnInfoModel> fieldInfo) {
		this.fieldInfo = fieldInfo;
	}
	
}
