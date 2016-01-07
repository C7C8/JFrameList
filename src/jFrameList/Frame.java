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
		String ret = new String(researchers.get(0));
		for (int i = 1; i < researchers.size() && researchers.size() > 1; i++)
			ret = ret.concat(", " + researchers.get(i));
		return ret;
	}
	public String getFoci()
	{
		String ret = new String(foci.get(0));
		for (int i = 1; i < foci.size(); i++)
			ret.concat(", " + researchers.get(i));
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
