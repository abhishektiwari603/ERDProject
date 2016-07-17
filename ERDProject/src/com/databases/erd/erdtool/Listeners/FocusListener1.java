package com.databases.erd.erdtool.Listeners;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.MouseListener;
import org.eclipse.draw2d.MouseMotionListener;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.swt.graphics.Color;

public class FocusListener1 implements MouseListener, MouseMotionListener
{
    static int click = 0;
    Figure figure;
    Point location;

    /** constructor save reference to figure, then add listeners */
    public FocusListener1(Figure f)
    {
        this.figure = f;
        f.addMouseListener(this);
        f.addMouseMotionListener(this);

    }

//@Override
    public void mousePressed(MouseEvent me)
    {
        location = me.getLocation();
        me.consume();
    }

    //@Override
    public void mouseDragged(MouseEvent me)
    {
        Point newLocation = me.getLocation();
        if (location == null || newLocation == null)
            return;
        // calculate offset wrt last location
        Dimension offset = newLocation.getDifference(location);
        if (offset.width == 0 && offset.height == 0)
            return;
        // exchange location
        location = newLocation;

        // old Bounds are dirty
        figure.getUpdateManager()
                .addDirtyRegion(figure.getParent(), figure.getBounds());

        // translate figure  
        figure.translate(offset.width, offset.height);

        // new Bounds are dirty
        figure.getUpdateManager()
                .addDirtyRegion(figure.getParent(), figure.getBounds());

        // new Bounds: set parent constraint
        figure.getParent().getLayoutManager()
                .setConstraint(figure, figure.getBounds());
        //
        me.consume();
    }

    //@Override
    public void mouseReleased(MouseEvent me)
    {
        if (location == null)
            return;
        location = null;
        me.consume();

    }

    //@Override
    public void mouseEntered(MouseEvent me)
    {
        this.figure.setBackgroundColor(new Color(null, 255, 216, 176));
    }

    //@Override
    public void mouseExited(MouseEvent me)
    {
        this.figure.setBackgroundColor(new Color(null, 128, 255, 128));
    }

    //@Override
    public void mouseHover(MouseEvent me)
    {

    }

    //@Override
    public void mouseMoved(MouseEvent me)
    {
    }

    //@Override
    public void mouseDoubleClicked(MouseEvent me)
    {
        if (click == 0)
        {
            this.figure.setBorder(new LineBorder(ColorConstants.darkBlue, 2));
            click = 1;
        }
        else
        {
            this.figure.setBorder(new LineBorder(ColorConstants.black, 1));
            click = 0;
        }
    }
}
