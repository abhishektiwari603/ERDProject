package com.databases.erd.erdtool.LabelProviders;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import com.databases.erd.erdtool.models.PropertyModel;



;

public class FieldPropertyLabelProvider extends LabelProvider implements ITableLabelProvider
{

	//@Override
	public Image getColumnImage(Object arg0, int arg1) 
	{
		// TODO Auto-generated method stub
		return null;
	}

	//@Override
	public String getColumnText(Object model, int index) 
	{
		PropertyModel myModel = (PropertyModel) model;
		String text = "";
		
		switch (index)
		{
			case 0:
				text= myModel.getPropertyname();
				break;
		
			case 1:
				text = myModel.getValue();
				break;
				
			default:
				break;
		}
		return text;
	}

}
