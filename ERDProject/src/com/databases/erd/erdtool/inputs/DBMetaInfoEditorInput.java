/**
 * 
 */
package com.databases.erd.erdtool.inputs;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

/**
 * @author trainee1
 *
 */
public class DBMetaInfoEditorInput implements IEditorInput 
{
	/**
	 * 
	 */
	private DBMetaInputInfo inputModel;

	/**
	 * @param model
	 */
	public DBMetaInfoEditorInput(DBMetaInputInfo model) 
	{
		this.inputModel = model;
	}

	public DBMetaInputInfo getInputModel() 
	{
		return inputModel;
	}
	/* (non-Javadoc)
	 * @see org.eclipse.ui.IEditorInput#exists()
	 */
	//@Override
	public boolean exists() 
	{
		// TODO Auto-generated method stub
		return true;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IEditorInput#getImageDescriptor()
	 */
	//@Override
	public ImageDescriptor getImageDescriptor() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IEditorInput#getName()
	 */
	//@Override
	public String getName() 
	{
		return "DB Migration";
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IEditorInput#getPersistable()
	 */
	//@Override
	public IPersistableElement getPersistable() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IEditorInput#getToolTipText()
	 */
	//@Override
	public String getToolTipText() 
	{
		return "DB Editor";
	}

	/* (non-Javadoc)
	 * @see org.eclipse.core.runtime.IAdaptable#getAdapter(java.lang.Class)
	 */
	@SuppressWarnings("rawtypes")
	//@Override
	public Object getAdapter(Class arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
