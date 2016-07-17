package com.databases.erd.erdtool.inputs;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

public class DBListEditorInput implements IEditorInput
{
    //@Override
    public boolean exists()
    {
        // TODO Auto-generated method stub
        return true;
    }

    //@Override
    public ImageDescriptor getImageDescriptor()
    {
        // TODO Auto-generated method stub
        return null;
    }

    //@Override
    public String getName()
    {
        return "ERD Tool";
    }

    //@Override
    public IPersistableElement getPersistable()
    {
        // TODO Auto-generated method stub
        return null;
    }

    //@Override
    public String getToolTipText()
    {
        return "ERD Editor";
    }

    @SuppressWarnings("rawtypes")
    //@Override
    public Object getAdapter(Class arg0)
    {
        // TODO Auto-generated method stub
        return null;
    }
}
