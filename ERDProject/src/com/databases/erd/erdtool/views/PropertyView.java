package com.databases.erd.erdtool.views;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.part.ViewPart;

import com.databases.erd.erdtool.LabelProviders.FieldPropertyLabelProvider;
import com.databases.erd.erdtool.cellModifier.PropertyModifier;
import com.databases.erd.erdtool.models.DBMetaColumnInfoModel;
import com.databases.erd.erdtool.models.PropertyModel;

/**
 * 
 * @author ABHISHEK
 * @Since Jul 17, 2016
 */

public class PropertyView extends ViewPart 
{
	public static final String ID = "dbmigration.views.ERDPropertyView";

	private TableViewer oldFieldViewer;
	private TableViewer newFieldViewer;
	public static String[] columnNames = new String[] { "Property", "Value" };

	@Override
	public void createPartControl(Composite parent) {
		FillLayout layout = new FillLayout(SWT.HORIZONTAL);

		parent.setLayout(layout);

		Group oldFieldGroup = new Group(parent, SWT.NONE);
		layout = new FillLayout();
		layout.marginHeight = 10;
		oldFieldGroup.setLayout(layout);
		oldFieldGroup.setText("Old Field Properties");

		Group newFieldGroup = new Group(parent, SWT.NONE);
		layout = new FillLayout();
		layout.marginHeight = 10;
		newFieldGroup.setLayout(layout);
		newFieldGroup.setText("New Field Properties");

		oldFieldViewer = new TableViewer(oldFieldGroup, SWT.BORDER
				| SWT.FULL_SELECTION);

		Table oldFieldTable = oldFieldViewer.getTable();
		oldFieldTable.setLinesVisible(true);
		oldFieldTable.setHeaderVisible(true);

		TableColumn col1 = new TableColumn(oldFieldTable, SWT.LEFT);
		col1.setWidth(130);
		col1.setText("Property");
		TableColumn col2 = new TableColumn(oldFieldTable, SWT.LEFT);
		col2.setWidth(130);
		col2.setText("Value");

		oldFieldViewer.setContentProvider(new ArrayContentProvider());
		oldFieldViewer.setLabelProvider(new FieldPropertyLabelProvider());

		newFieldViewer = new TableViewer(newFieldGroup, SWT.BORDER
				| SWT.FULL_SELECTION);

		Table newFieldTable = newFieldViewer.getTable();
		newFieldTable.setLinesVisible(true);
		newFieldTable.setHeaderVisible(true);
		TableColumn newType = new TableColumn(newFieldTable, SWT.LEFT);
		newType.setText("Property");
		newType.setWidth(100);

		TableColumn newValue = new TableColumn(newFieldTable, SWT.LEFT);
		newValue.setText("Value");
		newValue.setWidth(100);

		newFieldViewer.setContentProvider(new ArrayContentProvider());
		newFieldViewer.setLabelProvider(new FieldPropertyLabelProvider());
		CellEditor[] cellNewEditor = new CellEditor[2];
		cellNewEditor[1] = new TextCellEditor(newFieldTable);

		newFieldViewer.setCellModifier(new PropertyModifier(newFieldViewer));
		newFieldViewer.setCellEditors(cellNewEditor);
		newFieldViewer.setColumnProperties(columnNames);

	}

	private List<PropertyModel> createprop(ArrayList<DBMetaColumnInfoModel> abc) {
		List<PropertyModel> temp = new ArrayList<PropertyModel>();

		PropertyModel p5 = new PropertyModel();
		p5.setPropertyname("Field Name");
		p5.setValue(abc.get(0).getOldfield_name());

		temp.add(p5);

		PropertyModel p6 = new PropertyModel();
		p6.setPropertyname("Field Description");
		p6.setValue(abc.get(0).getOldfield_desc());
		temp.add(p6);

		PropertyModel p1 = new PropertyModel();
		p1.setPropertyname("Type");
		p1.setValue(abc.get(0).getType());
		temp.add(p1);

		PropertyModel p2 = new PropertyModel();
		p2.setPropertyname("Alias");
		p2.setValue(abc.get(0).getAlias());
		temp.add(p2);

		PropertyModel p3 = new PropertyModel();
		p3.setPropertyname("Length");
		String i = String.valueOf(abc.get(0).getLength());
		p3.setValue(i);
		temp.add(p3);

		PropertyModel p4 = new PropertyModel();
		p4.setPropertyname("Decimal");
		p4.setValue(abc.get(0).getDecimal());
		temp.add(p4);

		return temp;
	}

	@Override
	public void setFocus() {
	}

	/**
	 * 
	 * @param model
	 */
	public void resetInput(DBMetaColumnInfoModel model) {
		ArrayList<DBMetaColumnInfoModel> abc = new ArrayList<DBMetaColumnInfoModel>();
		abc.add(model);

		List<PropertyModel> list = createprop(abc);
		oldFieldViewer.setInput(list);
		newFieldViewer.setInput(list);
	}

	@SuppressWarnings("rawtypes")
	public static java.util.List getColumnNames() {
		return Arrays.asList(columnNames);
	}

}