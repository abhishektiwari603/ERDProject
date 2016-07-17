package com.databases.erd.erdtool.views;

import java.util.ArrayList;
import java.util.Iterator;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import com.databases.erd.erdtool.TableFigure.UMLClassFigure;
import com.databases.erd.erdtool.XmlToDb.xmltodb;
import com.databases.erd.erdtool.editors.ERDEditor;

/**
 * 
 * @author ABHISHEK
 * @Since Jul 17, 2016
 */
public class PhysicalName extends ViewPart {
	public static final String ID = "com.databases.erd.erdtool.views.PhysicalName";
	SashForm sh;
	ListViewer viewer;
	String tab1;
	int id;
	Label lab = new Label();
	LineBorder bord = new LineBorder(1);

	static ArrayList<UMLClassFigure> fig = new ArrayList<UMLClassFigure>();
	UMLClassFigure classFigure = null;

	public void createPartControl(Composite parent) {
		sh = new SashForm(parent, SWT.HORIZONTAL);

		fig = ERDEditor.classfig();

		Composite comp1 = new Composite(sh, SWT.HORIZONTAL);
		comp1.setLayout(new FillLayout());
		fill1(comp1);

		Composite comp2 = new Composite(sh, SWT.HORIZONTAL);
		comp2.setLayout(new FillLayout());
		viewer = new ListViewer(comp2);
	}

	public void fill1(Composite comp1) {
		final ListViewer viewer = new ListViewer(comp1);
		viewer.setContentProvider(new ArrayContentProvider());
		viewer.setInput(xmltodb.phyname());
		classFigure = fig.get(0);
		getSite().setSelectionProvider(viewer);
		viewer.addDoubleClickListener(new IDoubleClickListener() {
			public void doubleClick(DoubleClickEvent event) {
				try {
					ISelection tab = viewer.getSelection();
					tab1 = tab.toString();
					// System.out.println(tab1);
					String tab11 = tab1.substring(4, tab1.length() - 1);

					id = Integer.parseInt(tab11);
					id = id - 1;
					// System.out.println(tab11);

					classFigure.setBorder(new LineBorder(ColorConstants.black,
							1));
					classFigure = fig.get(id);
					classFigure.setBorder(new LineBorder(
							ColorConstants.darkBlue, 2));

					fill3(id);
				} catch (Exception ex) {
					throw new RuntimeException("not found");
				}
			}
		});
	}

	public void fill3(final int tabid) {
		final ArrayList<Label> colabel = new ArrayList<Label>();
		viewer.setContentProvider(new ArrayContentProvider());
		String table = xmltodb.getTabName(tabid);
		viewer.setInput(xmltodb.getcolumns(table));
		getSite().setSelectionProvider(viewer);
		classFigure = fig.get(tabid);

		viewer.addDoubleClickListener(new IDoubleClickListener() {
			@SuppressWarnings("unchecked")
			public void doubleClick(DoubleClickEvent event) {
				try {
					ISelection tab = viewer.getSelection();
					String colname = tab.toString();
					int le = colname.length();

					lab.setBorder(null);

					String subcolname = colname.substring(1, le - 1);
					int dq = xmltodb.getcolumnindex(xmltodb.getTabName(tabid),
							subcolname);
					Iterator<Label> it;
					it = (Iterator<Label>) classFigure.getMethodsCompartment()
							.getChildren().iterator();

					Color c1 = new Color(null, 255, 128, 0);

					while (it.hasNext())
						colabel.add(it.next());

					System.out.println(colabel);

					int i = (dq * 3) + 2;

					lab = colabel.get(i);
					bord.setColor(c1);
					lab.setBorder(bord);

				} catch (Exception ex) {

				}
			}
		});

	}

	public void setFocus() {
		viewer.getControl().setFocus();

	}
}
