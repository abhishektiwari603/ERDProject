package com.databases.erd.erdtool.LabelProviders;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

import com.databases.erd.erdtool.models.DBModel;

public class DBListLabelProvider extends LabelProvider
{

    @SuppressWarnings("unused")
    private final TreeViewer viewer;

    public DBListLabelProvider(TreeViewer viewer)
    {
        this.viewer = viewer;
    }

    @Override
    public String getText(Object element)
    {
        DBModel node = (DBModel) element;
        return node.getName();
    }

    @SuppressWarnings("deprecation")
    @Override
    public Image getImage(Object obj)
    {
        String imageKey = ISharedImages.IMG_OBJ_ELEMENT;
        if (obj instanceof DBModel)
            if (((DBModel) obj).getParent() == null)
            {
                imageKey = ISharedImages.IMG_OBJ_ADD;
            }
            else
            {
                imageKey = ISharedImages.IMG_OBJS_TASK_TSK;
            }
        return PlatformUI.getWorkbench().getSharedImages().getImage(imageKey);
    }
}
