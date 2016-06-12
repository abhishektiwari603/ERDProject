package com.databases.erd.erdtool.XY;

import java.util.ArrayList;

import com.databases.erd.erdtool.XmlToDb.xmltodb;


public class callxy
{
	static int coly=10,coly1=10,coly2=10,coly3=10;
	public static  ArrayList<Integer> call(String tbname) 
		{
		ArrayList <String> tables;
		
		tables=new ArrayList<String>();
		tables=xmltodb.gettables();
		
		ArrayList<Integer> xy=new ArrayList<Integer>();
		ArrayList<Integer> retxy=new ArrayList<Integer>();
		int col=0;
		
			for(int i=0;i<tables.size();i++)
			{
				if(tables.get(i).equalsIgnoreCase(tbname))
				{
					xy=xmltodb.getcount(tbname);
					col=xmltodb.getcolcount(tbname);
				}
			}
			
			if(xy.get(0)>0 && xy.get(1)==0)			//Primary Key Tables placed
			{
				retxy.add(0,500);
				retxy.add(1, coly);
				coly=coly+(col*22)+30+12;
			}
			if(xy.get(1)>0  && xy.get(0)==0)			//Foreign Key Tables placed
			{
				retxy.add(0,10);
				retxy.add(1, coly1);
				coly1+=+(col*22)+30+12;
			}
			if(xy.get(0)>0  && xy.get(1)>0)				//Primary Key & Foreign Key Tables placed
			{
				retxy.add(0,200);
				retxy.add(1,coly2);
				coly2+=+(col*22)+30+12;
			}
			if(xy.get(2)>0)									//Tables with No Key placed
			{
				retxy.add(0,700);
				retxy.add(1, coly3);
				coly3+=+(col*22)+30+12;
			}
			return retxy;
			
		}
	public static void reset()
	{
		coly=coly1=coly2=coly3=0;
	}
}