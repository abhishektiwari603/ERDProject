package com.databases.erd.erdtool.LabelProviders;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import com.databases.erd.erdtool.models.DBMetaInfoModel;

public class MetaInfoLabelProvider extends LabelProvider implements ITableLabelProvider
{
    private Image checked;

    public MetaInfoLabelProvider()
    {
        // Create the image
        try
        {
            checked = new Image(null, new FileInputStream("D:/icons/checked.jpg"));
        }
        catch (FileNotFoundException e)
        {
            System.out.println(e.getMessage());
        }
    }

    //@Override
    public Image getColumnImage(Object model, int index)

    {
        DBMetaInfoModel myModel = (DBMetaInfoModel) model;
        Image image = null;
        switch (index)
        {
            case 3:
                if (myModel.isInc())
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
        DBMetaInfoModel myModel = (DBMetaInfoModel) model;
        String text = "";
        switch (index)
        {
            case 0:
                text = myModel.getOld_name();
                break;

            case 1:
                text = myModel.getOld_description();
                break;

            case 2:
                text = myModel.getNew_name();
                break;

            case 3:
                text = "";
                break;

            default:
                text = "Invalid Column Index";
        }
        return text;
    }

}
