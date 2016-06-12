package com.databases.erd.erdtool.cellModifier;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.TableItem;

import com.databases.erd.erdtool.keywordChecker.MySQLKeywordChecker;
import com.databases.erd.erdtool.models.PropertyModel;
import com.databases.erd.erdtool.views.PropertyView;



public class PropertyModifier implements ICellModifier
{
	TableViewer tableViewer;
	private String message = "Invalid String";

	public PropertyModifier(TableViewer tableViewer)
	{
		this.tableViewer = tableViewer;
	}

	
	public boolean canModify(Object arg0, String arg1)
	{
		return true;
	}

	
	public Object getValue(Object model, String property)
	{
		PropertyModel myModel = (PropertyModel) model;
		int columnIndex = PropertyView.getColumnNames().indexOf(property);

		Object result=null;

		switch (columnIndex)
		{
		case 1:
			result = myModel.getValue();
			break;

		default:
			result = "";
		}
		return result;
	}

	
	public void modify(Object element, String property, Object newValue)
	{
		TableItem item = (TableItem) element;
		PropertyModel myModel = (PropertyModel) item.getData();

		int columnIndex = PropertyView.getColumnNames().indexOf(property);
		String value;

		switch (columnIndex)
		{
		case 1 : 
			value = ((String) newValue).trim();
			if(item.getText().equals("Type"))
			{
				myModel.setValue(value);
			}
			
			else if(item.getText().equals("Length")||item.getText().equals("Decimal"))
			{
				    @SuppressWarnings("unused")
					int num;
				
				    if(value==null) 
				        break; // if you press cancel it will exit
				    try {
				        num=Integer.parseInt(value);
				        myModel.setValue(value);
				        break;
				    } catch(NumberFormatException ex)
				    {
				    	MessageDialog.openInformation(tableViewer.getControl().getShell(),"Error Message","Enter Numeric Value");
				    }
				
			}
			

			else if(MySQLKeywordChecker.check_Keyword(value))
			{
				MessageDialog.openInformation(tableViewer.getControl().getShell(),"Error Message", message );
			}
			else
			{
				myModel.setValue(value);
			}
			break;

		default :
		}

		tableViewer.refresh();

	}
}
