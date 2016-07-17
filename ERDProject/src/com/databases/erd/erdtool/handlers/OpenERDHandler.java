package com.databases.erd.erdtool.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.window.Window;

import com.databases.erd.erdtool.db2xml.db2xml;
import com.databases.erd.erdtool.dialogs.OpenNewConnectionDialog;

public class OpenERDHandler extends AbstractHandler
{

    public static IStructuredSelection strucSelection;

    public OpenERDHandler()
    {
    }

    public Object execute(ExecutionEvent event) throws ExecutionException
    {
        IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
        window.getActivePage().resetPerspective();
        OpenNewConnectionDialog dialog = new OpenNewConnectionDialog(Display.getDefault().getActiveShell());
        dialog.create();
        if (dialog.open() == Window.OK)
        {
            //		DBListView.treeViewer.refresh();
            try
            {
                window.getActivePage().showView("erdtool.views.DBListView");
            }
            catch (PartInitException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            ISelection selection = HandlerUtil.getActiveWorkbenchWindow(event).getActivePage().getSelection();

            if (selection != null & selection instanceof IStructuredSelection)
            {
                strucSelection = (IStructuredSelection) selection;
                //SampleHandler.strucSelection=strucSelection;
                String st = strucSelection.toString();
                int l = st.length();
                String s = st.substring(1, l - 1);
                db2xml.main(s);

            }

        }
        return null;
    }
}
