package com.databases.erd.erdtool.perspectives;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

/**
 *  This class is meant to serve as an example for how various contributions 
 *  are made to a perspective. Note that some of the extension point id's are
 *  referred to as API constants while others are hardcoded and may be subject 
 *  to change. 
 */
public class ERD implements IPerspectiveFactory
{

    @SuppressWarnings("unused")
    private IPageLayout factory;

    public ERD()
    {
        super();
    }

    public void createInitialLayout(IPageLayout factory)
    {
        this.factory = factory;

    }

}
