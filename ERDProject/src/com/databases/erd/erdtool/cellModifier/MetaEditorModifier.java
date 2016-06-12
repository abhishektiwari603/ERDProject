package com.databases.erd.erdtool.cellModifier;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.TableItem;

import com.databases.erd.erdtool.editors.DBMetaInfoEditor;
import com.databases.erd.erdtool.keywordChecker.MySQLKeywordChecker;
import com.databases.erd.erdtool.models.DBMetaInfoModel;




public class MetaEditorModifier implements ICellModifier
{
	TableViewer tableViewer;
	private String message = "Invalid String";
	
	public MetaEditorModifier(TableViewer tableViewer)
	{
		this.tableViewer = tableViewer;
	}
	
	
	public boolean canModify(Object arg0, String arg1) 
	{
		return true;
	}


	public Object getValue(Object model, String property)
	{
		DBMetaInfoModel myModel = (DBMetaInfoModel) model;
		int columnIndex = DBMetaInfoEditor.getTableColumnNames().indexOf(property);
		
		Object result=null;
		
		switch (columnIndex)
		{
			case 2:
				result = myModel.getNew_name();
			break;
	
			default:
				result = "";
		}
		return result;
	}

	
	public void modify(Object element, String property, Object newValue)
	{
		int columnIndex	= DBMetaInfoEditor.getTableColumnNames().indexOf(property);
		
		TableItem item = (TableItem) element;
		DBMetaInfoModel myModel = (DBMetaInfoModel) item.getData();
		String value;
		
		switch (columnIndex)
		{
			case 2 : 
				value = ((String) newValue).trim();
				 if(MySQLKeywordChecker.check_Keyword(value))
				{
					 MessageDialog.openInformation(tableViewer.getControl().getShell(),"Error Message", message );
				}
				 else
				 {
					 myModel.setNew_name(value); 
				 }
				break;
				
			default :
		}

		tableViewer.refresh();
	}

}
