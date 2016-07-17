package com.databases.erd.erdtool.views;

import java.util.ArrayList;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.FocusEvent;
import org.eclipse.draw2d.FocusListener;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.swt.graphics.Color;

import com.databases.erd.erdtool.TableFigure.UMLClassFigure;
import com.databases.erd.erdtool.editors.ERDEditor;

public class select {
	
	static ArrayList<UMLClassFigure> fig = new ArrayList<UMLClassFigure>();

	select(ArrayList<UMLClassFigure> abc) {
		abc = ERDEditor.classfig();
		fig = abc;
	}

	public static void SelectAuto(int index) {

		final UMLClassFigure classFigure;
		classFigure = fig.get(index);

		classFigure.addFocusListener(new FocusListener() {

			// @Override
			public void focusGained(FocusEvent fe) {
				classFigure.setBorder(new LineBorder(ColorConstants.green, 2));
				classFigure.setBackgroundColor(new Color(null, 255, 216, 176));
			}

			// @Override
			public void focusLost(FocusEvent fe) {
				classFigure.setBorder(new LineBorder(ColorConstants.black, 1));

			}

		});
	}
}
