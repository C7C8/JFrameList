package jFrameList;

import java.util.ArrayList;


public class Frame implements Cloneable
{
	public String name;
	public int year;
	public ArrayList<String> researchers = new ArrayList<String>();
	public ArrayList<String> foci = new ArrayList<String>();
	
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
