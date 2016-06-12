package com.databases.erd.erdtool.editors;

import java.util.ArrayList;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.CoordinateListener;
import org.eclipse.draw2d.FanRouter;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FigureCanvas;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LightweightSystem;
import org.eclipse.draw2d.ManhattanConnectionRouter;
import org.eclipse.draw2d.PolygonDecoration;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;

import com.databases.erd.erdtool.Listeners.FocusListener1;
import com.databases.erd.erdtool.Listeners.MyListener;
import com.databases.erd.erdtool.TableFigure.UMLClassFigure;
import com.databases.erd.erdtool.XY.callxy;
import com.databases.erd.erdtool.XmlToDb.xmltodb;

public class ERDEditor extends EditorPart
{
	public final static String ID="erdtool.editors.ERDEditor";
	static Display d;
	static LightweightSystem lws;
	static Figure contents ;
	static XYLayout contentsLayout;
	static int x;
	static int y;	
	int canw=1100,canh=4500;
	static ArrayList <UMLClassFigure> figure;
	//@Override
	public void createPartControl(Composite parent)
	{
		Composite composite = new Composite(parent, SWT.SCROLL_PAGE);
		composite.setLayout(new FillLayout());

		new xmltodb();

		FigureCanvas canvas = new FigureCanvas(composite);
		canvas.setBackground(new Color(null,255,255,255));
		
		/*Text txt=new Text(canvas, PROP_TITLE);
		txt.setText("Created By: Vishal Gupta in 2014-15");
		txt.setBounds(canw-210, canh-4500, 210, 20);
		txt.setEnabled(true);
		txt.setEditable(false);
		canvas.setData(txt);

		Text txt2=new Text(canvas, PROP_TITLE);
		Image img=new Image(d,UMLClassFigure.class.getResourceAsStream("logo.png"));
		txt2.setBackgroundImage(img);
		txt2.setBounds(canw-250, canh-68, 250, 68);
		txt2.setEnabled(false);
		txt2.setTouchEnabled(false);
		txt.setEditable(false);
		canvas.setData(txt2);*/

		lws = new LightweightSystem(canvas);

		contents = new Figure();
		contentsLayout = new XYLayout();  
		contents.setLayoutManager(contentsLayout);


				Font classFont = new Font(null, "Arial", 12, SWT.BOLD);
				ArrayList <String> tables=new ArrayList<String>();
				ArrayList<Integer> xyval=new ArrayList<Integer>();
				figure=new ArrayList<UMLClassFigure>();
				ArrayList<String> final_tables=new ArrayList<String>();
				ArrayList<String> type=new ArrayList<String>();
				tables=xmltodb.gettables();
				ArrayList <String> column=new ArrayList<String>();
				xmltodb.check_wordList();
				xmltodb.phyname();
				Color c1=new Color(null,0,156,210);
				Color c2=new Color(null,10,156,250);
				Color c3=new Color(null,10,156,200);
				Label la1 = null;
				for(int j=0;j<tables.size();j++)
				{
					Label classLabel1 = new Label(tables.get(j));
					classLabel1.setFont(classFont);
					final UMLClassFigure classFigure= new UMLClassFigure(classLabel1);
					column=xmltodb.getcolumns(tables.get(j));
					type=xmltodb.getType(tables.get(j));
					ArrayList<String> precision=xmltodb.getPrecision(tables.get(j));

					for(int i=0;i<column.size();i++)
					{
						Image pki =new Image(d,UMLClassFigure.class.getResourceAsStream("p.gif"));
						Image fki =new Image(d,UMLClassFigure.class.getResourceAsStream("f.png"));
						String s=xmltodb.getKey(tables.get(j),column.get(i));
						String null_col=xmltodb.check_null(tables.get(j),column.get(i));
						String prec=precision.get(i).toString();
						if(s.equals("PU"))
						{	
							if(null_col.equals("NN"))
							{
								la1=new Label(pki);
								la1.setBackgroundColor(c3);
								classFigure.getMethodsCompartment().add(la1);
								
								la1=new Label("  ");
								classFigure.getMethodsCompartment().add(la1);
								
								la1 = new Label(column.get(i)+" " +type.get(i) +"("+prec+")"+"(UNN)");
								la1.setForegroundColor(new Color(null,255,0,0));
								la1.setBackgroundColor(c1);
								classFigure.getMethodsCompartment().add(la1);		
					    		}
						else
						{
							la1=new Label(pki);
							la1.setBackgroundColor(c3);
							classFigure.getMethodsCompartment().add(la1);
							
							la1=new Label("  ");
							classFigure.getMethodsCompartment().add(la1);
							
							la1 = new Label(column.get(i)+" " +type.get(i)+"("+prec+")");			
							la1.setBackgroundColor(c1);
							classFigure.getMethodsCompartment().add(la1);
						
						}
					}
					else if(s.equals("F"))
					{
						if(null_col.equals("NN"))
						{
							la1=new Label("  ");
							classFigure.getMethodsCompartment().add(la1);
							
							la1=new Label(fki);
							la1.setBackgroundColor(c2);
							classFigure.getMethodsCompartment().add(la1);
							
							la1 = new Label(column.get(i)+" " +type.get(i) +"("+prec+")"+"(NN)");
							la1.setForegroundColor(new Color(null,0,0,255));
							la1.setBackgroundColor(c1);
							classFigure.getMethodsCompartment().add(la1);
						}
						else
						{
							la1=new Label("  ");
							la1.setBackgroundColor(c2);
							classFigure.getMethodsCompartment().add(la1);
							
							la1=new Label(fki);
							la1.setBackgroundColor(c2);
							classFigure.getMethodsCompartment().add(la1);
							
							la1 = new Label(column.get(i)+" " +type.get(i)+"("+prec+")");
							la1.setForegroundColor(new Color(null,0,0,255));
							la1.setBackgroundColor(c1);
							classFigure.getMethodsCompartment().add(la1);
						}
					}
					else if(s.equals("U"))
					{
						if(null_col.equals("NN"))
						{
							la1=new Label("  ");
							classFigure.getMethodsCompartment().add(la1);
							la1=new Label("  ");
							classFigure.getMethodsCompartment().add(la1);
							la1 = new Label(column.get(i)+" " +type.get(i) +"("+prec+")"+"(UNN)");
							la1.setBackgroundColor(c1);
							classFigure.getMethodsCompartment().add(la1);
						}
						
						else
						{
							la1=new Label("  ");
							classFigure.getMethodsCompartment().add(la1);
							la1=new Label("  ");
							classFigure.getMethodsCompartment().add(la1);
							la1 = new Label(column.get(i)+" " +type.get(i) +"("+prec+")"+"(U)");
							la1.setBackgroundColor(c1);
							classFigure.getMethodsCompartment().add(la1);
						}
					}
										
					 else if(s.equals("PFU"))
						{				 
						 if(null_col.equals("NN"))
							{
							 la1=new Label(pki);
							la1.setBackgroundColor(c3);
							classFigure.getMethodsCompartment().add(la1);
							
							la1=new Label(fki);
							la1.setBackgroundColor(c2);
							classFigure.getMethodsCompartment().add(la1);
							
							la1 = new Label(column.get(i)+" " +type.get(i) +"("+prec+")"+"(NN)");
						    la1.setForegroundColor(new Color(null,177,115,0));
							la1.setBackgroundColor(c1);
							classFigure.getMethodsCompartment().add(la1);
							}
						 else
						 {
						    la1=new Label(pki);
							la1.setBackgroundColor(c3);
							classFigure.getMethodsCompartment().add(la1);
							
							la1=new Label(fki);
							la1.setBackgroundColor(c2);
							classFigure.getMethodsCompartment().add(la1);
							
							la1 = new Label(column.get(i)+" " +type.get(i)+"("+prec+")");
						    la1.setForegroundColor(new Color(null,177,115,0));
							la1.setBackgroundColor(c1);
							classFigure.getMethodsCompartment().add(la1);
						 }				
						}
					else
					{
						if(null_col.equals("NN"))
						{
							la1=new Label("  ");
							classFigure.getMethodsCompartment().add(la1);
							
							la1=new Label("  ");
							classFigure.getMethodsCompartment().add(la1);
							
							la1 = new Label(column.get(i)+" " +type.get(i) +"("+prec+")"+"(NN)");
							la1.setBackgroundColor(c1);
							classFigure.getMethodsCompartment().add(la1);
						}
						else
						{
							la1=new Label("  ");
							classFigure.getMethodsCompartment().add(la1);
							la1=new Label("  ");
							classFigure.getMethodsCompartment().add(la1);
							la1 = new Label(column.get(i)+" " +type.get(i)+"("+prec+")" );	
							la1.setBackgroundColor(c1);
							classFigure.getMethodsCompartment().add(la1);				
						}
					}
				}
			
				figure.add(classFigure);
			
				xyval=callxy.call(tables.get(j));
				x=xyval.get(0);
				y=xyval.get(1);
				Rectangle r1=new Rectangle(x,y,-1,-1);
		
				contentsLayout.setConstraint(classFigure,r1);
				
	   			new FocusListener1(classFigure);
			
	   			classFigure.addCoordinateListener(new CoordinateListener(){

	   				//@Override
	   				public void coordinateSystemChanged(IFigure source) {
	   					
	   					source.setOpaque(false);
	   				}	   
	   			   });
				contents.add(classFigure);
			}
				
			/* Creating the connection */
			for(int i=0;i<tables.size();i++)
			{
			    final_tables=xmltodb.check_relation(tables.get(i));
			
				for(int j=0;j<tables.size();j++)
				{
					for(int temp=0;temp<final_tables.size();temp++)
					{
					if(tables.get(j).equals(final_tables.get(temp)))
					{
						PolylineConnection c = new PolylineConnection();
						ChopboxAnchor sourceAnchor = new ChopboxAnchor(figure.get(j));
						ChopboxAnchor targetAnchor = new ChopboxAnchor(figure.get(i));
						c.setSourceAnchor(sourceAnchor);
						c.setTargetAnchor(targetAnchor);
					
						c.setFocusTraversable(true);
					
						FanRouter fan = new FanRouter();
						ManhattanConnectionRouter m = new ManhattanConnectionRouter();

						c.setConnectionRouter(fan);
						fan.route(c);
						fan.setNextRouter(m);
						fan.setSeparation(2);
						fan.route(c);
						
						PolygonDecoration decoration = new PolygonDecoration();
						PointList decorationPointList = new PointList();
						decorationPointList.addPoint(-1, 2);
						decorationPointList.addPoint(-1, 0);
						decorationPointList.addPoint(-1, -2);
						decoration.setTemplate(decorationPointList);
						c.setSourceDecoration(decoration);

						PolygonDecoration decoration1 = new PolygonDecoration();
						PointList decorationPointList1 = new PointList();
						decorationPointList1.addPoint(0,2);
						decorationPointList1.addPoint(-2,0);
						decorationPointList1.addPoint(0,-2);
						decorationPointList1.addPoint(-2,0);
						
						decoration1.setTemplate(decorationPointList1);
						c.setTargetDecoration(decoration1);

						contents.add(c);

					
					new MyListener(c);
					contents.add(c);   
					}
					}
				}
			}
			
			lws.setContents(contents);
			callxy.reset();
		
		 ScrolledComposite sc = new ScrolledComposite(parent, SWT.V_SCROLL |SWT.BORDER);
	//	 composite.setSize(1000,1200);
		 sc.setContent(composite);
		 sc.setExpandHorizontal(true);
		 sc.setExpandVertical(true);
		 sc.setMinSize(composite.computeSize(canw,canh));	
		
		
	}
	//@Override
	public void doSave(IProgressMonitor arg0) {
		// TODO Auto-generated method stub
		
	}

	//@Override
	public void doSaveAs() {
		// TODO Auto-generated method stub
		
	}
	
	public static ArrayList<UMLClassFigure> classfig() 
	{
		return figure;
	}

	//@Override
	public void init(IEditorSite arg0, IEditorInput arg1) throws PartInitException {
		setSite(arg0);
		this.setInput(arg1);
		// TODO Auto-generated method stub
		
	}

	//@Override
	public boolean isDirty() {
		// TODO Auto-generated method stub
		return false;
	}

	//@Override
	public boolean isSaveAsAllowed() {
		// TODO Auto-generated method stub
		return false;
	}

	

	//@Override
	public void setFocus() {
		// TODO Auto-generated method stub
		
	}


}
