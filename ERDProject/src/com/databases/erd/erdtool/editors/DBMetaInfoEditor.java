package com.databases.erd.erdtool.editors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.widgets.Label;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.CheckboxCellEditor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.EditorPart;

import com.databases.erd.erdtool.LabelProviders.MetaColumnInfoLabelProvider;
import com.databases.erd.erdtool.LabelProviders.MetaInfoLabelProvider;
import com.databases.erd.erdtool.cellModifier.MetaEditorModifier;
import com.databases.erd.erdtool.cellModifier.MetaFieldEditorModifier;
import com.databases.erd.erdtool.controllers.ShowTables;
import com.databases.erd.erdtool.inputs.DBMetaInfoEditorInput;
import com.databases.erd.erdtool.inputs.DBMetaInputInfo;
import com.databases.erd.erdtool.models.DBMetaColumnInfoModel;
import com.databases.erd.erdtool.models.DBMetaInfoModel;
import com.databases.erd.erdtool.views.PropertyView;

@SuppressWarnings("unused")
public class DBMetaInfoEditor extends EditorPart implements IEditorPart
{
    public static final String ID = "erdtool.editors.MigrateEditor";
    private DBMetaInputInfo model;
    public DBMetaColumnInfoModel prop;
    DBMetaInfoModel dbmi;
    ArrayList<DBMetaColumnInfoModel> infoList2;
    public static String[] columnNames = new String[] { "Old Name", "Description", "New Name", "Inc" };
    public static String[] fieldNames = new String[] { "Old Field Name", "Old Field Description",
            "New Column Name", "New Aliases", "Key", "Inc" };

    //@Override
    public void createPartControl(final Composite parent)
    {
        parent.setBackground(new Color(null, 255, 255, 255));

        FillLayout layout = new FillLayout(SWT.VERTICAL);
        layout.spacing = 25;
        layout.marginHeight = 10;
        parent.setLayout(layout);

        Group firstGroup = new Group(parent, SWT.NONE);
        layout = new FillLayout();
        layout.marginHeight = 10;
        firstGroup.setLayout(layout);
        firstGroup.setText("Tables");

        Group secondGroup = new Group(parent, SWT.NONE);
        layout = new FillLayout();
        layout.marginHeight = 10;
        secondGroup.setLayout(layout);
        secondGroup.setText("Fields");

        final TableViewer tableViewer = new TableViewer(firstGroup, SWT.FULL_SELECTION | SWT.BORDER);
        final TableViewer fieldViewer = new TableViewer(secondGroup, SWT.FULL_SELECTION | SWT.BORDER);

        Table table = tableViewer.getTable();
        table.setLinesVisible(true);
        table.setHeaderVisible(true);

        TableColumn column1 = new TableColumn(table, SWT.LEFT);
        column1.setText("Old Name");
        column1.setWidth(250);

        TableColumn column2 = new TableColumn(table, SWT.LEFT);
        column2.setText("Description");
        column2.setWidth(250);

        TableColumn column3 = new TableColumn(table, SWT.LEFT);
        column3.setText("New Name");
        column3.setWidth(250);

        TableColumn column4 = new TableColumn(table, SWT.NONE);
        column4.setText("Inc");
        column4.setWidth(250);

        final ArrayList<DBMetaInfoModel> infoList1 = ShowTables.getTables();

        tableViewer.setContentProvider(new ArrayContentProvider());
        tableViewer.setLabelProvider(new MetaInfoLabelProvider());
        tableViewer.setInput(infoList1);
        CellEditor[] cellEditor = new CellEditor[4];
        cellEditor[2] = new TextCellEditor(table);

        tableViewer.setCellModifier(new MetaEditorModifier(tableViewer));
        tableViewer.setCellEditors(cellEditor);
        tableViewer.setColumnProperties(columnNames);

        getSite().setSelectionProvider(tableViewer);

        Table table2 = fieldViewer.getTable();
        table2.setLinesVisible(true);
        table2.setHeaderVisible(true);

        TableColumn column11 = new TableColumn(table2, SWT.LEFT);
        column11.setText("Old Field Name");
        column11.setWidth(163);

        TableColumn column12 = new TableColumn(table2, SWT.LEFT);
        column12.setText("Old Field Description");
        column12.setWidth(163);

        TableColumn column13 = new TableColumn(table2, SWT.LEFT);
        column13.setText("New Cloumn Name");
        column13.setWidth(163);

        TableColumn column14 = new TableColumn(table2, SWT.LEFT);
        column14.setText("New Aliases");
        column14.setWidth(163);

        TableColumn column15 = new TableColumn(table2, SWT.LEFT);
        column15.setText("Key");
        column15.setWidth(163);

        TableColumn column17 = new TableColumn(table2, SWT.LEFT);
        column17.setText("Key Type");
        column17.setWidth(100);

        TableColumn column16 = new TableColumn(table2, SWT.LEFT);
        column16.setText("Inc");
        column16.setWidth(100);

        tableViewer.getTable().addListener(SWT.Selection, new Listener()
        {
            public void handleEvent(Event e)
            {
                IStructuredSelection strucSelection = (IStructuredSelection) tableViewer.getSelection();
                dbmi = (DBMetaInfoModel) strucSelection.getFirstElement();
                fieldViewer.setInput(dbmi.getFieldInfo());

            }
        });

        fieldViewer.setContentProvider(new ArrayContentProvider());
        fieldViewer.setLabelProvider(new MetaColumnInfoLabelProvider());
        CellEditor[] cellFieldEditor = new CellEditor[6];
        cellFieldEditor[2] = new TextCellEditor(table2);
        cellFieldEditor[3] = new TextCellEditor(table2);
        cellFieldEditor[4] = new CheckboxCellEditor(table2);

        fieldViewer.setCellModifier(new MetaFieldEditorModifier(fieldViewer));
        fieldViewer.setCellEditors(cellFieldEditor);
        fieldViewer.setColumnProperties(fieldNames);

        fieldViewer.getTable().addListener(SWT.Selection, new Listener()
        {
            //@Override
            public void handleEvent(Event arg0)
            {
                IStructuredSelection strucSelection = (IStructuredSelection) fieldViewer.getSelection();
                prop = (DBMetaColumnInfoModel) strucSelection.getFirstElement();

                try
                {
                    IViewPart view = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(PropertyView.ID);
                    if (view == null)
                        view = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView(PropertyView.ID);

                    PropertyView propertyView = (PropertyView) view;
                    propertyView.resetInput(prop);
                }
                catch (PartInitException e1)
                {
                    e1.printStackTrace();
                }
            }
        });
    }

    //@Override
    public void init(IEditorSite site, IEditorInput editorInput) throws PartInitException
    {
        setSite(site);
        this.setInput(editorInput);

        model = ((DBMetaInfoEditorInput) editorInput).getInputModel();
    }

    //@Override
    public boolean isDirty()
    {
        return false;
    }

    //@Override
    public boolean isSaveAsAllowed()
    {
        return false;
    }

    //@Override
    public void doSave(IProgressMonitor arg0)
    {
    }

    //@Override
    public void doSaveAs()
    {
    }

    //@Override
    public void setFocus()
    {
    }

    @SuppressWarnings("rawtypes")
    public static java.util.List getTableColumnNames()
    {
        return Arrays.asList(columnNames);
    }

    @SuppressWarnings("rawtypes")
    public static java.util.List getFieldColumnNames()
    {
        return Arrays.asList(fieldNames);
    }

}
