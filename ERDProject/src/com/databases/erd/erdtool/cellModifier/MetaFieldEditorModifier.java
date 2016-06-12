package com.databases.erd.erdtool.cellModifier;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.TableItem;

import com.databases.erd.erdtool.editors.DBMetaInfoEditor;
import com.databases.erd.erdtool.keywordChecker.MySQLKeywordChecker;
import com.databases.erd.erdtool.models.DBMetaColumnInfoModel;




public class MetaFieldEditorModifier implements ICellModifier
{
	TableViewer fieldViewer;
	private String message = "Invalid String";
	
	public MetaFieldEditorModifier(TableViewer fieldViewer)
	{
		this.fieldViewer = fieldViewer;
	}


	public boolean canModify(Object arg0, String arg1) 
	{
		return true;
	}


	public Object getValue(Object model, String property)
	{
		DBMetaColumnInfoModel myModel = (DBMetaColumnInfoModel) model;
		int columnIndex = DBMetaInfoEditor.getFieldColumnNames().indexOf(property);
		
		Object result=null;
		switch (columnIndex)
		{
			case 2:
				result = myModel.getNewcolumn_name();
			break;

			case 3:
				result = myModel.getNew_alias();
			break;
			
			case 4:
				result = myModel.isKey();
			break;
			default:
				result = "";
		}
		return result;
	}

	
	public void modify(Object element, String property, Object newValue) 
	{
		int columnIndex	= DBMetaInfoEditor.getFieldColumnNames().indexOf(property);
		
		TableItem item = (TableItem) element;
		DBMetaColumnInfoModel task = (DBMetaColumnInfoModel) item.getData();
		String value;
		
		switch (columnIndex)
		{
			case 2 : 
				value = ((String) newValue).trim();
				 if(MySQLKeywordChecker.check_Keyword(value))
				 {
					 MessageDialog.openInformation(fieldViewer.getControl().getShell(),"Error Message", message );
				 }
				 else
				 {
					 task.setNewcolumn_name(value); 
				 }
				break;
				
			case 3:
				value = ((String) newValue).trim();
				if(MySQLKeywordChecker.check_Keyword(value))
				 {
					 MessageDialog.openInformation(fieldViewer.getControl().getShell(),"Error Message", message );
				 }
				 else
				 {
					 task.setNew_alias(value);
				 }
				break;
				
			case 4 :
				task.setKey(((Boolean) newValue).booleanValue());
				break;
				
			default :
		}
		
		fieldViewer.refresh();
	}
}
