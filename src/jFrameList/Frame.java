package jFrameList;

import java.util.ArrayList;


public class Frame implements Cloneable
{
	public String name;
	public int year;
	public ArrayList<String> researchers = new ArrayList<String>();
	public ArrayList<String> foci = new ArrayList<String>();
	
	public String getResearchers()
	{
		String ret = new String();
		for (int i = 0; i < researchers.size(); i++)
			ret = ret.concat(researchers.get(i) + "; ");
		return ret;
	}
	public String getFoci()
	{
		String ret = new String();
		for (int i = 0; i < foci.size(); i++)
			ret = ret.concat(foci.get(i) + ", ");
		return ret;
	}
	
	public Object clone() throws CloneNotSupportedException
	{
		Frame cloned = (Frame) super.clone();
		cloned.name = new String(name);
		cloned.year = year;
		cloned.researchers = new ArrayList<String>(researchers);
		cloned.foci = new ArrayList<String>(foci);
		return cloned;
	}
}
