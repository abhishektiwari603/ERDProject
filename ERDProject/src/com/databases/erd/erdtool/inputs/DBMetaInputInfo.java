package com.databases.erd.erdtool.inputs;

import java.util.ArrayList;
import java.util.List;

import com.databases.erd.erdtool.models.DBMetaColumnInfoModel;
import com.databases.erd.erdtool.models.DBMetaInfoModel;




public class DBMetaInputInfo {

	private String dbname;
	/*
	 * @param dbname
	 */
	public  DBMetaInputInfo(String dbname)
	{
		this.dbname = dbname;
	}
	public List<DBMetaInfoModel> getTableNames()
	{
		List<DBMetaInfoModel> input = new ArrayList<DBMetaInfoModel>();
		//
		//	  	DBTableInfoModel table_desc= ShowTables.getTables(dbname);
		//        ArrayList<String> old_name=table_desc.getOldName();
		//        ArrayList<String> desc=table_desc.getDescription();
		//        for(int i=0;i<old_name.size();i++)
		//        {
		//        	DBMetaInfoModel model1=new DBMetaInfoModel();
		//    		model1.setOld_name(old_name.get(i));
		//    		model1.setOld_description(desc.get(i));
		//    	    model1.setNew_name("");
		//    	    model1.setInc(true);
		//    		input.add(model1);
		//         }


		return input;
	}

	public List<DBMetaColumnInfoModel> createMetaColumnInfo(String tbname)
	{   
		List<DBMetaColumnInfoModel> input=new ArrayList<DBMetaColumnInfoModel>();
//		ArrayList<String> columns=new ArrayList<String>();
//		
//		columns=ShowColumns.getColumns(dbname,tbname);
//		for(String column:columns)
//		{
//			DBMetaColumnInfoModel infoModel=new DBMetaColumnInfoModel();
//			infoModel.setOldfield_name("");
//			infoModel.setOldfield_desc("");
//			infoModel.setNewcolumn_name(column);
//			infoModel.setNew_alias("");
//			infoModel.setKey(true);
//			infoModel.setInc(true);
//			input.add(infoModel);
//		}
		return input;
	}
}
