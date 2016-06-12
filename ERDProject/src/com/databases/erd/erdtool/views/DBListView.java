package com.databases.erd.erdtool.views;

import org.eclipse.jface.window.Window;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.part.ViewPart;

import com.databases.erd.erdtool.ContentProviders.DBListContentProvider;
import com.databases.erd.erdtool.GenerateQueries.GeneratePostgreQuery;
import com.databases.erd.erdtool.GenerateQueries.GenerateQuery;
import com.databases.erd.erdtool.LabelProviders.DBListLabelProvider;
import com.databases.erd.erdtool.NewConnection.PostgreConnection;
import com.databases.erd.erdtool.Populate.PopulateTree;
import com.databases.erd.erdtool.XmlToDb.xmltodb;
import com.databases.erd.erdtool.controllers.ShowTables;
import com.databases.erd.erdtool.db2xml.db2xml;
import com.databases.erd.erdtool.dialogs.MigrationDialog;
import com.databases.erd.erdtool.editors.DBMetaInfoEditor;
import com.databases.erd.erdtool.editors.ERDEditor;
import com.databases.erd.erdtool.inputs.DBListEditorInput;
import com.databases.erd.erdtool.inputs.DBMetaInfoEditorInput;
import com.databases.erd.erdtool.inputs.DBMetaInputInfo;
import com.databases.erd.erdtool.models.DBModel;



public class DBListView extends ViewPart
{
	public static TreeViewer treeViewer;
	private Action createERDAction,createXMLAction,openEditorAction,generateSQLServerQuery,migrateToPostgreAction;
	private static ArrayList<DBModel> children=new ArrayList<DBModel>();
	private PopulateTree populateTree=new PopulateTree();
	static String dbname;
	@Override
	public void createPartControl(Composite parent)
	{
		Tree tree = new Tree(parent, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		tree.setHeaderVisible(true);
		treeViewer = new TreeViewer(tree);
		TreeColumn column1 = new TreeColumn(tree, SWT.LEFT);
	    tree.setLinesVisible(false);
	    column1.setAlignment(SWT.LEFT);
	    column1.setWidth(200);
	    
	    treeViewer.setContentProvider(new DBListContentProvider());
		treeViewer.setLabelProvider(new DBListLabelProvider(treeViewer));
		DBModel dbModel= new DBModel(null, "DBList");

		dbModel.setChildren(children);
		DBModel[] nodes = new DBModel[] {dbModel};
		treeViewer.setInput(nodes);
		populateTree.populateInitial(dbModel);
		treeViewer.expandAll();
		makeActions();
		hookContextMenu();
	}
	
	 private void hookContextMenu()
	 {
		 treeViewer.addSelectionChangedListener
		 (
			new ISelectionChangedListener()
			{
				public void selectionChanged(SelectionChangedEvent event) 
				{
					if(event.getSelection() instanceof IStructuredSelection) 
					{
						IStructuredSelection selection = (IStructuredSelection)event.getSelection();            
		                Object o = selection.getFirstElement();  
		                DBListLabelProvider labelProvider = new DBListLabelProvider(treeViewer);
		                dbname = labelProvider.getText(o);
		                new xmltodb(dbname);
		                MenuManager menuMgr = new MenuManager("#POPUPMENU");
		                menuMgr.setRemoveAllWhenShown(true);
		                menuMgr.addMenuListener(new IMenuListener() 
		                {
		                	public void menuAboutToShow(IMenuManager manager) 
		                	{
		                		DBListView.this.fillContextMenu(manager);
		                	}
		                });
		                Menu menu = menuMgr.createContextMenu(treeViewer.getControl());
		                treeViewer.getControl().setMenu(menu);
		                getSite().registerContextMenu(menuMgr, treeViewer);
					}
		        }
			}   
		 );
	}
	   
	   private void fillContextMenu(IMenuManager manager) 
	   {
			manager.add(createERDAction);	
			manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
			manager.add(createXMLAction);
			manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
			manager.add(openEditorAction);
			manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
			manager.add(generateSQLServerQuery);
			manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
			manager.add(migrateToPostgreAction);
			manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
		}
	   
	   @SuppressWarnings("deprecation")
	private void makeActions() 
	   {
			createERDAction = new Action()
			{
				public void run()
				{
					DBListEditorInput editorInput=new DBListEditorInput();
					try
					{
						db2xml.main(dbname);
						PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().openEditor(editorInput, ERDEditor.ID);
					PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView(PhysicalName.ID);
					} 
					catch (Exception e)
					{
						System.out.println(e.getMessage());
					}
				}
			};
			createERDAction.setText("Generate ERD");
			createERDAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_OPEN_MARKER));
			
			createXMLAction = new Action()
			{
				public void run()
				{
				 db2xml.main(dbname);
					
			    	 File fileToOpen = new File("xmls\\"+dbname+".xml");
					 
						if (fileToOpen.exists() && fileToOpen.isFile()) 
						{
						    IFileStore fileStore = EFS.getLocalFileSystem().getStore(fileToOpen.toURI());
						    IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
						 
						    try {
						        IDE.openEditorOnFileStore(page,fileStore);		                
						    } 
						    catch ( PartInitException e ) {
						    }
						} else {
						}
			    	
			    	
					
				}
			};
			
			createXMLAction.setText("Generate XML");
			createXMLAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_TOOL_DELETE_DISABLED));
			
			openEditorAction = new Action() 
			{
				public void run()
				{ 
					    
						db2xml.main(dbname);
						DBMetaInputInfo model = new DBMetaInputInfo(dbname);
					    ShowTables showTables=new ShowTables(dbname);
					    DBMetaInfoEditorInput editorInput = new DBMetaInfoEditorInput(model);
						try
						{
							PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().openEditor(editorInput, DBMetaInfoEditor.ID);
						} 
						catch (PartInitException e)
						{
							System.out.println(e.getMessage());
						}
 				}
			};
			
			openEditorAction.setText("Open Database Description");
	
			openEditorAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_ETOOL_HOME_NAV));
			
			generateSQLServerQuery=new Action()
			{
	             public void run()
	             {
	            	 
	          	 db2xml.main(dbname);
	            	 GenerateQuery.Generate(dbname);

			    	 File fileToOpen = new File("xmls\\"+dbname+"Query.txt");
					 
						if (fileToOpen.exists() && fileToOpen.isFile()) 
						{
						    IFileStore fileStore = EFS.getLocalFileSystem().getStore(fileToOpen.toURI());
						    IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
						 
						    try {
						        IDE.openEditorOnFileStore(page,fileStore);		                
						    } 
						    catch ( PartInitException e ) {
						    }
						} else {
						}
			   }
			
			};
			generateSQLServerQuery.setText("Generate SQL Server Query");
			generateSQLServerQuery.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_TOOL_REDO));
			
			
			
			
			migrateToPostgreAction=new Action()
			{
	             public void run()
	             {
	            	 
	          	     db2xml.main(dbname);
	          	     
	          	     MigrationDialog md=new MigrationDialog(Display.getDefault().getActiveShell());
	          	     md.create();
	          	     if(md.open()== Window.OK)
	          	     {
	          	    	 
	          	    	 PostgreConnection.setConnection(md);
	          	    	 
	          	    	 
	          	     }
	          	     
	               	 GeneratePostgreQuery.Generate(dbname);
	            	 MessageDialog.openInformation(treeViewer.getControl().getShell(),"Migration", "Database Migration Successful.");
			   }
			
			};
			migrateToPostgreAction.setText("Migrate to PostgreSQL");
			migrateToPostgreAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_ELCL_SYNCED_DISABLED));
	   }
	   
	   private void hookDoubleClickAction() 
		{
			treeViewer.addDoubleClickListener(new IDoubleClickListener() 
			{
				public void doubleClick(DoubleClickEvent event) 
				{
					ISelection selection = treeViewer.getSelection();
					Object obj = ((IStructuredSelection)selection).getFirstElement();
				    
					DBListLabelProvider nlp = new DBListLabelProvider(treeViewer);
				    String dbname = nlp.getText(obj);
				    new xmltodb(dbname);
				}
			});
		}
	   
	@Override
	public void setFocus()
	{
		// TODO Auto-generated method stub	
	}
	
}