package com.databases.erd.erdtool.dialogs;

import org.eclipse.jface.dialogs.IDialogConstants;
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

/**
 * 
 * @author ABHISHEK
 * @Since Jul 17, 2016
 */
public class MigrationDialog extends TitleAreaDialog
{
    private Combo targetxt;
    private Text databasetxt;
    private Text hosttxt;
    private Text porttxt;
    private Text usertxt;
    private Text passwordtxt;

    private String strTar;
    private String strDatabase;
    private String strHost;
    private String strPort;
    private String strUser;
    private String strPassword;

    public MigrationDialog(Shell parentShell)
    {
        super(parentShell);
    }

    public void create()
    {
        super.create();
        setTitle("Database Migration");
        //setMessage("This is a TitleAreaDialog", IMessageProvider.INFORMATION);
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

        targetDb(container);
        databaseName(container);
        host(container);
        port(container);
        username(container);
        password(container);

        return area;
    }

    private void targetDb(Composite container)
    {
        Label lbtLastName = new Label(container, SWT.NONE);
        lbtLastName.setText(" Target Database Name ");

        targetxt = new Combo(container, SWT.READ_ONLY);
        targetxt.setItems(new String[] { "PostGreSQL" });
        targetxt.select(0);
    }

    private void databaseName(Composite container)
    {
        Label databaseName = new Label(container, SWT.NONE);
        databaseName.setText(" Target Database Name ");

        GridData databaseData = new GridData();
        databaseData.grabExcessHorizontalSpace = true;
        databaseData.horizontalAlignment = GridData.FILL;

        databasetxt = new Text(container, SWT.BORDER);
        databasetxt.setLayoutData(databaseData);
    }

    private void host(Composite container)
    {
        Label host = new Label(container, SWT.NONE);
        host.setText(" Host ");

        GridData hostData = new GridData();
        hostData.grabExcessHorizontalSpace = true;
        hostData.horizontalAlignment = GridData.FILL;

        hosttxt = new Text(container, SWT.BORDER);
        hosttxt.setLayoutData(hostData);
    }

    private void port(Composite container)
    {
        Label port = new Label(container, SWT.NONE);
        port.setText(" Port ");

        GridData portData = new GridData();
        portData.grabExcessHorizontalSpace = true;
        portData.horizontalAlignment = GridData.FILL;

        porttxt = new Text(container, SWT.BORDER);
        porttxt.setLayoutData(portData);
    }

    private void username(Composite container)
    {
        Label user = new Label(container, SWT.NONE);
        user.setText(" Username ");

        GridData userData = new GridData();
        userData.grabExcessHorizontalSpace = true;
        userData.horizontalAlignment = GridData.FILL;

        usertxt = new Text(container, SWT.BORDER);
        usertxt.setLayoutData(userData);
    }

    private void password(Composite container)
    {
        Label password = new Label(container, SWT.NONE);
        password.setText(" Password ");

        GridData passwordData = new GridData();
        passwordData.grabExcessHorizontalSpace = true;
        passwordData.horizontalAlignment = GridData.FILL;

        passwordtxt = new Text(container, SWT.BORDER);
        passwordtxt.setLayoutData(passwordData);
        passwordtxt.addFocusListener(new FocusListener()
        {

            //@Override
            public void focusLost(FocusEvent arg0)
            {
                if (databasetxt.getText() == "" || usertxt.getText() == "" || porttxt.getText() == "" || hosttxt.getText() == "" || passwordtxt.getText() == "")
                {
                    MessageDialog.openError(Display.getDefault().getActiveShell(), "ERROR", "Please Fill all above fields");
                }
            }

            //@Override
            public void focusGained(FocusEvent arg0)
            {
                // TODO Auto-generated method stub				
            }
        });
    }

    //@Override
    protected boolean isResizable()
    {
        return true;
    }

    //@Override
    protected void createButtonsForButtonBar(Composite parent)
    {
        createButton(parent, IDialogConstants.OK_ID, "OK", true);

        createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);
    }

    private void saveInput()
    {
        this.strTar = targetxt.getText();
        this.strDatabase = databasetxt.getText();
        this.strHost = hosttxt.getText();
        this.strPort = porttxt.getText();
        this.strUser = usertxt.getText();
        this.strPassword = passwordtxt.getText();
    }

    //@Override
    protected void okPressed()
    {
        saveInput();
        super.okPressed();
    }

    public String getDatabaseName()
    {
        return strDatabase;
    }

    public String getHostName()
    {
        return strHost;
    }

    public String getPortName()
    {
        return strPort;
    }

    public String getUserName()
    {
        return strUser;
    }

    public String getStrTar()
    {
        return strTar;
    }

    public String getPassword()
    {
        return strPassword;
    }
}
