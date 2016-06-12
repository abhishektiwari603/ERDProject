package com.databases.erd.erdtool.TableFigure;

import org.eclipse.draw2d.AbstractBorder;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.GridLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Insets;


public class CompartmentFigure extends Figure 
{
	  public CompartmentFigure() 
	  {
		GridLayout layout=new GridLayout(3,false);
		setLayoutManager(layout);
	    setBorder(new CompartmentFigureBorder());
	  }
	    
	  public class CompartmentFigureBorder extends AbstractBorder 
	  {
		//RoundedRectangle r=new RoundedRectangle();
	    public Insets getInsets(IFigure figure) 
	    {
	      return new Insets(1,0,0,0);
	    }
	    public void paint(IFigure figure, Graphics graphics, Insets insets) 
	    {
	   graphics.drawLine(getPaintRectangle(figure, insets).getTopLeft(),tempRect.getTopRight());
	      
	    }
	  }
	}