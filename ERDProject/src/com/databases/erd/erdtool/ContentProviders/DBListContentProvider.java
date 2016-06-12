package com.databases.erd.erdtool.ContentProviders;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import com.databases.erd.erdtool.models.*;

public class DBListContentProvider implements ITreeContentProvider 
{

	////@Override
	public Object[] getChildren(Object element)
	{
		return ((DBModel)element).getChildren().toArray();
	}

	//@Override
	public Object getParent(Object element) 
	{
		return ((DBModel)element).getParent();
	}

	//@Override
	public boolean hasChildren(Object element) 
	{
		return ((DBModel)element).hasChildren();
	}

	//@Override	
	public Object[] getElements(Object inputElement) 
	{
		return (Object[]) inputElement;
	}

	//@Override
	public void dispose() 
	{
		// unused
	}

	//@Override
	public void inputChanged(Viewer viewer, Object oldInput,Object newInput) 
	{
		// unused
	}

}

