package com.databases.erd.erdtool.Listeners;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.Bendpoint;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.MouseListener;
import org.eclipse.draw2d.MouseMotionListener;
import org.eclipse.draw2d.RelativeBendpoint;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Color;

import com.databases.erd.erdtool.TableFigure.UMLClassFigure;



 
public class MyListener implements MouseListener,MouseMotionListener
{
  Connection con;
  Point location;
  PointList poli=new PointList();
  Dimension start;
  Graphics graph;
  List<Bendpoint> constraint;
  Point bend;
  int XT,XS,YT,YS;
  
  public MyListener(Connection f) 
  {
    this.con = f;  
    this.poli=con.getPoints();
    this.start=con.getSize();
   
    f.addMouseMotionListener(this);
    f.addMouseListener(this);
   
  }
  
//@Override
  public void mousePressed(MouseEvent me) {
    location = me.getLocation();
    con.setForegroundColor(new Color(null,120,120,250));
    
    me.consume();
  }
   
     //@Override
  public void mouseReleased(MouseEvent me) 
     {
    	    XS=con.getSourceAnchor().getReferencePoint().x;
    	    YS=con.getSourceAnchor().getReferencePoint().y;
    	    XT=con.getTargetAnchor().getReferencePoint().x;
    	    YT=con.getTargetAnchor().getReferencePoint().y;
    	    System.out.println(con.getSourceAnchor().getReferencePoint()); 
    	    System.out.println(con.getTargetAnchor().getReferencePoint()); 
    	   // bend.getLocation();
    	    bend.x=(XS+XT)/2;
    	    bend.y=(YS+YT)/2;
    	    constraint=getRealBendpoint(bend);
    	    if( location==null )
    	        return;
    	      location = null;
    	      me.consume();
  
  }
   //@Override
  public void mouseDoubleClicked(MouseEvent me) 
  {	  
	   this.con.setRoutingConstraint(constraint);
  }

public  List<Bendpoint> getRealBendpoint(Point bendPoint) 
{
	List<Bendpoint> constraint = new ArrayList<Bendpoint>();

	UMLClassFigure umlclass = (UMLClassFigure) con.getSourceAnchor().getOwner();
	
	if (umlclass != null) {
		Rectangle bounds = umlclass.getBounds();
		int width = bounds.width;
		int height = bounds.height;
		System.out.println(width+height);
		if (width == 0) {
			// tableEditPart.getcon().getUpdateManager()
			// .performUpdate();

			bounds = umlclass.getBounds();
			width = bounds.width;
			height = bounds.height;
		}

		RelativeBendpoint point = new RelativeBendpoint();

		int xp = XT;
		int x;

		if (xp == -1) {
			x = bounds.x + bounds.width;
		} else {
			x = bounds.x + (bounds.width * xp / 100);
		}

		point.setRelativeDimensions(new Dimension(width * bendPoint.x
				/ 100 - bounds.x - bounds.width + x, 0), new Dimension(
				width * bendPoint.x / 100 - bounds.x - bounds.width
						+ x, 0));
		point.setWeight(0);
		point.setConnection(this.con);

		constraint.add(point);

		point = new RelativeBendpoint();
		point.setRelativeDimensions(
				new Dimension(width * bendPoint.x / 100 - bounds.x
						- bounds.width + x, height * bendPoint.y / 100),
				new Dimension(width * bendPoint.x / 100 - bounds.x
						- bounds.width + x, height * bendPoint.y / 100));
		point.setWeight(0);
		point.setConnection(this.con);

		constraint.add(point);

		point = new RelativeBendpoint();
		point.setRelativeDimensions(new Dimension(x - bounds.x
				- bounds.width, height * bendPoint.y / 100),
				new Dimension(x - bounds.x - bounds.width, height
						* bendPoint.y / 100));
		point.setWeight(0);
		point.setConnection(this.con);

		constraint.add(point);
	}
	System.out.println(constraint);
return constraint;
	
}

//@Override
public void mouseDragged(MouseEvent me) 
{
	Point newLocation = me.getLocation();
    if( location==null || newLocation == null)
      return;
    // calculate offset wrt last location
    Dimension offset = newLocation.getDifference( location );
    if( offset.width==0 && offset.height==0 )
      return;
    // exchange location
    location = newLocation;
   
    // old Bounds are dirty
    con.getUpdateManager()
      .addDirtyRegion(con.getParent(), con.getBounds()); 
     
    // translate con  
    con.translate( offset.width, offset.height );
     
    // new Bounds are dirty
    con.getUpdateManager()
      .addDirtyRegion( con.getParent(),con.getBounds() );
     
    // new Bounds: set parent constraint
    con.getParent().getLayoutManager()
      .setConstraint(con,con.getBounds());
    //
    me.consume();
}

//@Override
public void mouseEntered(MouseEvent me) {
	// TODO Auto-generated method stub
	
}

//@Override
public void mouseExited(MouseEvent me) {
	// TODO Auto-generated method stub
	
}

//@Override
public void mouseHover(MouseEvent me) {
	// TODO Auto-generated method stub
	
}

//@Override
public void mouseMoved(MouseEvent me) {
	// TODO Auto-generated method stub
	
}

}

  
   
  
