package com.databases.erd.erdtool.dialogs;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.databases.erd.erdtool.connectionModel.ConnDialogModel;


public class OpenNewConnectionDialog extends TitleAreaDialog{
	
	private Combo dbname1;
	private Text local,portno,username,password;
	private Text urlvalue,driverclassvalue;
	public static ConnDialogModel aon;
	private static String URL;
	private static String DRIVER;
	public OpenNewConnectionDialog(Shell parentShell) 
	{
		super(parentShell);
		
	}
	//@Override
	public void create() {
		super.create();
		
		setTitle("New Connection");
		setMessage("Connect to New Database", IMessageProvider.INFORMATION);
	}

	//@Override
	protected Control createDialogArea(Composite parent) 
	{
		Composite area = (Composite) super.createDialogArea(parent);
		Composite container = new Composite(area, SWT.NONE);
		container.setLayoutData(new GridData(GridData.FILL_BOTH));
		GridLayout layout = new GridLayout(2, true);
		container.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		container.setLayout(layout);
		
		dbname(container);
		servername(container);
		port(container);
		user(container);	
		pwd(container);
		
		defaultlabel(container);
		
	//	container.update();
		
		return area;
	}

	private void dbname(Composite container) {
		Label name = new Label(container, SWT.NONE);
		name.setText("Database *");
		dbname1=new Combo(container, SWT.READ_ONLY);
		dbname1.setItems(new String[]{"MySQL"});
		dbname1.select(0);		
		
	}
	private void servername(Composite container) {
		Label name=new Label(container, SWT.NONE);
		name.setText("Host *");
		local=new Text(container, SWT.BORDER);		
		GridData classdata = new GridData();
		classdata.grabExcessHorizontalSpace = true;
		classdata.horizontalAlignment = GridData.FILL;
		local.setLayoutData(classdata);
		
		
	}
	private void port(Composite container) {
		Label port=new Label(container, SWT.NONE);
		port.setText("Port No. *");
		portno=new Text(container, SWT.BORDER);
		
		GridData classdata = new GridData();
		classdata.grabExcessHorizontalSpace = true;
		classdata.horizontalAlignment = GridData.FILL;
		portno.setLayoutData(classdata);
	/*	portno.addFocusListener(new FocusListener() {
			
			//@Override
			public void focusLost(FocusEvent arg0) {
				if(portno.getText()==null || portno.getText()==""){
					MessageDialog.openError(Display.getDefault().getActiveShell(), "ERROR" ,"Please Input Port No." );
				}	
			}
			
			//@Override
			public void focusGained(FocusEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});*/
	}
	private void user(Composite container) {
		Label name=new Label(container, SWT.NONE);
		name.setText("User Name *");
		username=new Text(container, SWT.BORDER);
		GridData classdata = new GridData();
		classdata.grabExcessHorizontalSpace = true;
		classdata.horizontalAlignment = GridData.FILL;
		username.setLayoutData(classdata);
		/*username.addFocusListener(new FocusListener() {
			
			//@Override
			public void focusLost(FocusEvent arg0) {
				if(username.getText()==null || username.getText()==""){
					MessageDialog.openError(Display.getDefault().getActiveShell(), "ERROR" ,"Please Enter User Name" );
				}	
			}
			
			//@Override
			public void focusGained(FocusEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});*/
	}
	private void pwd(Composite container) {
		Label name=new Label(container, SWT.NONE);
		name.setText("Password *");
		password=new Text(container, SWT.BORDER);
		GridData classdata = new GridData();
		classdata.grabExcessHorizontalSpace = true;
		classdata.horizontalAlignment = GridData.FILL;
		password.setLayoutData(classdata);
		password.addFocusListener(new FocusListener() {
			
			//@Override
			public void focusLost(FocusEvent arg0) {
				if(local.getText()==""||portno.getText()==""||username.getText()=="")
				{
					MessageDialog.openError(Display.getDefault().getActiveShell(), "ERROR" ,"Please Fill all above fields" );
				}
				else if(dbname1.getSelectionIndex()==0)
				{
					GridData urldata = new GridData();
					urldata.grabExcessHorizontalSpace = true;
					urldata.horizontalAlignment = GridData.FILL;
					URL="jdbc:mysql://"+local.getText()+":"+portno.getText()+"/";
					urlvalue.setText(URL);
					
					GridData classdata = new GridData();
					classdata.grabExcessHorizontalSpace = true;
					classdata.horizontalAlignment = GridData.FILL;
					
					DRIVER="com.mysql.jdbc.Driver";
					driverclassvalue.setText(DRIVER);
					
				}
				else if(dbname1.getSelectionIndex()==2)
				{
					GridData urldata = new GridData();
					urldata.grabExcessHorizontalSpace = true;
					urldata.horizontalAlignment = GridData.FILL;
					URL="jdbc:odbc";
					urlvalue.setText(URL);
					
					GridData classdata = new GridData();
					classdata.grabExcessHorizontalSpace = true;
					classdata.horizontalAlignment = GridData.FILL;
					DRIVER="sun.jdbc.odbc.JdbcOdbcDriver";
					driverclassvalue.setText(DRIVER);
				}
				savedata();
			}
			
			//@Override
			public void focusGained(FocusEvent arg0) {
				
				
			}
		});
	}
	

	
	
	private void defaultlabel(Composite container) {
	Label urlname=new Label(container, SWT.NONE);
	urlname.setText("JDBC URL");
	
	urlvalue=new Text(container, SWT.READ_ONLY | SWT.BORDER);
	urlvalue.setText(" ");	
	GridData urldata = new GridData();
	urldata.grabExcessHorizontalSpace = true;
	urldata.horizontalAlignment = GridData.FILL;
	urlvalue.setLayoutData(urldata);
	
	Label driverclassname=new Label(container, SWT.NONE);
	driverclassname.setText("JDBC Driver Class Name");
	driverclassvalue=new Text(container, SWT.READ_ONLY | SWT.BORDER);
	driverclassvalue.setText(" ");
	GridData classdata = new GridData();
	classdata.grabExcessHorizontalSpace = true;
	classdata.horizontalAlignment = GridData.FILL;
	driverclassvalue.setLayoutData(classdata);
	
	}

	private void savedata() {
		aon=new ConnDialogModel();
		aon.setDbnameStr(dbname1.getText());
		aon.setLocalStr(local.getText());
		aon.setPortnoStr(portno.getText());
		aon.setUsernameStr(username.getText());
		aon.setPasswordStr(password.getText());
	}
	public static ConnDialogModel getObj()
	{
		return aon;
	}
	//@Override
	protected void okPressed() {
		super.okPressed();
	}
	//@Override
	protected boolean isResizable() {
		return true;
	}
	//@Override
	protected void createButtonsForButtonBar(Composite parent) 
	{
		createButton(parent, IDialogConstants.OK_ID,  "Connect",  true);

		createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL,  false);
	}
} 
