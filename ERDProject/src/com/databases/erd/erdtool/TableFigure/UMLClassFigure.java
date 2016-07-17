package com.databases.erd.erdtool.TableFigure;

import java.util.ArrayList;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.swt.graphics.Color;

public class UMLClassFigure extends Figure
{
    public Color classColor = new Color(null, 128, 255, 128);
    private CompartmentFigure attributeFigure = new CompartmentFigure();
    private CompartmentFigure methodFigure = new CompartmentFigure();
    public ArrayList<CompartmentFigure> arrcf = new ArrayList<CompartmentFigure>();
    public ArrayList<Label> name1 = new ArrayList<Label>();

    public UMLClassFigure(Label name)
    {
        name.setForegroundColor(new Color(null, 0, 91, 147));
        ToolbarLayout layout = new ToolbarLayout();
        layout.setStretchMinorAxis(true);
        setLayoutManager(layout);
        setBorder(new LineBorder(ColorConstants.black, 1));
        setBackgroundColor(classColor);
        setOpaque(true);

        add(name);

        //   add(attributeFigure);
        add(methodFigure);
    }

    public CompartmentFigure getAttributesCompartment()
    {
        return attributeFigure;
    }

    public CompartmentFigure getMethodsCompartment()
    {
        return methodFigure;
    }

}