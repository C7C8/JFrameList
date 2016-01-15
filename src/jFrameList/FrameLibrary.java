package jFrameList;

import java.io.InputStream;
import java.util.ArrayList;

import nu.xom.*;

public class FrameLibrary
{
	protected ArrayList<Frame> frameList = new ArrayList<Frame>();

	public void parse()
	{
		try
		{
			InputStream file = getClass().getResourceAsStream("/jFrameList/FRAME_List.xml");
			Builder parser = new Builder();
			Document doc = parser.build(file);
			Element root = doc.getRootElement();
			Elements frames = root.getChildElements();
			
			for (int frameNum = 0; frameNum < root.getChildElements().size(); frameNum++)
			{
				Frame newFrame = new Frame();
				Element frame = frames.get(frameNum);
				newFrame.name = frame.getFirstChildElement("name").getValue();
				newFrame.year = Integer.parseInt(frame.getFirstChildElement("year").getValue());
				newFrame.abs = frame.getFirstChildElement("abstract").getValue().replaceAll("\t\t\t", "");
				newFrame.abs = newFrame.abs.replaceFirst("\n", "");
				
				Elements researchers = frame.getFirstChildElement("researchers").getChildElements();
				for (int i = 0; i < researchers.size(); i++)
					newFrame.researchers.add(researchers.get(i).getValue());
				
				Elements foci = frame.getFirstChildElement("focus").getChildElements();
				for (int i = 0; i < foci.size(); i++)
					newFrame.foci.add(foci.get(i).getValue());
				
				frameList.add(newFrame);
			}
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}

	public void clear()
	{
		frameList.clear();
	}

	public int size()
	{
		return frameList.size();
	}
	
	public ArrayList<String> getNames()
	{	
		ArrayList<String> ret = new ArrayList<String>();
		for (int i = 0; i < frameList.size(); i++)
			ret.add(frameList.get(i).name);
		return ret;
	}
	
	public ArrayList<Frame> searchName(String key)
	{
		ArrayList<Frame> ret = new ArrayList<Frame>();
		key = key.toLowerCase(); //Makes searching easier

		try
		{
			for (int i = 0; i < frameList.size(); i++)
			{
				Frame frame = (Frame) frameList.get(i).clone();
				frame.name = frame.name.toLowerCase();
				if (frame.name.contains(key))
					ret.add((Frame) frameList.get(i).clone());
			}
		}
		catch (CloneNotSupportedException ex)
		{
			ex.printStackTrace();
		}

		return ret;
	}

	public ArrayList<Frame> filter(String name, int year, String researcher, String focus)
	{
		ArrayList<Frame> ret = new ArrayList<Frame>();

		try
		{
			for (int i = 0; i < frameList.size(); i++)
			{
				Frame frame = (Frame) frameList.get(i).clone();
				String tempFYear = new String(Integer.toString(frame.year));

				if (!frame.name.toLowerCase().contains(name.toLowerCase()) && !name.isEmpty())
					continue;
				
				if (!tempFYear.contains(Integer.toString(year)) && year > 0)
					continue;
			
				if (!frame.getFoci().toLowerCase().contains(focus.toLowerCase()) && !focus.isEmpty())
					continue;
				
				if (!frame.getResearchers().toLowerCase().contains(researcher.toLowerCase()) && !researcher.isEmpty())
					continue;
				
				ret.add(frame);
			}
		}
		catch (CloneNotSupportedException ex)
		{
			ex.printStackTrace();
		}

		return ret;
	}
}
