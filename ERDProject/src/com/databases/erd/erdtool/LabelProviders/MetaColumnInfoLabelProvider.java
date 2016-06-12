package com.databases.erd.erdtool.LabelProviders;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import com.databases.erd.erdtool.models.DBMetaColumnInfoModel;





public class MetaColumnInfoLabelProvider extends LabelProvider implements ITableLabelProvider
{
	private Image checked;
	private Image unchecked;

	public MetaColumnInfoLabelProvider() {
	    // Create the image
	    try 
	    {
	      checked = new Image(null, new FileInputStream("D:/icons/checked.jpg"));
	      unchecked = new Image(null, new FileInputStream("D:/icons/unchecked.gif"));
	    } 
	    catch (FileNotFoundException e)
	    {
	      // Swallow it
	    }
	  }
	//@Override
	public Image getColumnImage(Object model, int index) {
		DBMetaColumnInfoModel myModel=(DBMetaColumnInfoModel) model;
		Image image=null;
		switch (index)
		{
		case 4:
			if(myModel.isKey() && myModel.getKey_column().equals(myModel.getOldfield_name()))
				image = checked;
			else if(myModel.getKey_column().equals(myModel.getOldfield_name()))
				image = unchecked;
			else
				image =unchecked;
			break;
			
		case 6:
			if(myModel.isInc())
			image = checked;
			break;

		default:
			break;
		}
		return image;
	}

	//@Override
	public String getColumnText(Object model, int index)
	{
		DBMetaColumnInfoModel myModel=(DBMetaColumnInfoModel) model;
		String text="";
		switch (index)
		{
			case 0:
			text = myModel.getOldfield_name();
			break;

			case 1:
			text = myModel.getOldfield_desc();
			break;
			
			case 2:
			text = myModel.getNewcolumn_name();
			break;
			
			case 3:
		    text = myModel.getNew_alias();
			break;
			
			case 4:
			text = "";
			break;
			
			case 5:
				text =myModel.getKeyType();
				break;
			
			case 6:
				text = "";
				break;
			default:
			text="Invalid column index";
		}
		return text;
	}

}
