package jFrameList;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class FrameLibrary
{
	protected ArrayList<Frame> frameList = new ArrayList<Frame>();

	public ArrayList<String> getNames()
	{
		ArrayList<String> ret = new ArrayList<String>();
		for (int i = 0; i < frameList.size(); i++)
			ret.add(frameList.get(i).name);
		return ret;
	}

	public void parse(String filename)
	{
		try
		{
			File fXMLFile = new File(filename);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXMLFile);
			doc.getDocumentElement().normalize();

			NodeList frames = doc.getElementsByTagName("frame");

			//UGGGGGHHHHH
			for (int frameNum = 0; frameNum < frames.getLength(); frameNum++)
			{
				Node nFrame = frames.item(frameNum);
				if (nFrame.getNodeType() == Node.ELEMENT_NODE)
				{
					Element eFrame = (Element) nFrame;
					Frame frame = new Frame();
					frame.name = eFrame.getElementsByTagName("name").item(0).getTextContent();
					frame.year = Integer.parseInt(eFrame.getElementsByTagName("year").item(0).getTextContent());

					NodeList researchers = eFrame.getElementsByTagName("rsc");
					for (int i = 0; i < researchers.getLength(); i++)
						frame.researchers.add(researchers.item(i).getTextContent());
					
					
					NodeList foci = eFrame.getElementsByTagName("fcs");
					for (int i = 0; i < foci.getLength(); i++)
						frame.foci.add(foci.item(i).getTextContent());
					
					frameList.add(frame);
				}
			}
		}
		catch (FileNotFoundException ex)
		{
			System.out.println("Couldn't find file " + filename + "!");
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}

	public int size()
	{
		return frameList.size();
	}

	public void clear()
	{
		frameList.clear();
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

	public ArrayList<Frame> searchYear(int key)
	{
		ArrayList<Frame> ret = new ArrayList<Frame>();
		try
		{
			for (int i = 0; i < frameList.size(); i++)
			{
				Frame frame = (Frame) frameList.get(i).clone();
				if (key == frame.year)
					ret.add(frame);
			}
		}
		catch (CloneNotSupportedException ex)
		{
			ex.printStackTrace();
		}

		return ret;
	}

	public ArrayList<Frame> searchResearcher(String key)
	{
		ArrayList<Frame> ret = new ArrayList<Frame>();
		key = key.toLowerCase();

		try
		{
			for (int i = 0; i < frameList.size(); i++)
			{
				Frame frame = (Frame) frameList.get(i).clone();
				for (int j = 0; j < frame.researchers.size(); j++)
				{
					String rsc = frame.researchers.get(j).toLowerCase();
					if (rsc.contains(key))
					{
						ret.add(frame);
						break;
					}
				}
			}
		}
		catch (CloneNotSupportedException ex)
		{
			ex.printStackTrace();
		}
		return ret;
	}

	public ArrayList<Frame> searchFocus(String key)
	{
		ArrayList<Frame> ret = new ArrayList<Frame>();
		key = key.toLowerCase();

		try
		{
			for (int i = 0; i < frameList.size(); i++)
			{
				Frame frame = (Frame) frameList.get(i).clone();
				for (int j = 0; j < frame.foci.size(); j++)
				{
					String foc = frame.foci.get(j).toLowerCase();
					if (foc.contains(key))
					{
						ret.add(frame);
						break;
					}
				}
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

				if ((!frame.name.toLowerCase().contains(name.toLowerCase()) && !name.isEmpty()) || (!tempFYear.contains(Integer.toString(year)) && year >= 0))
					continue; //Skip if the name or year doesn't match
				
				boolean invalid = false; //kludge
				for (int j = 0; j < frame.researchers.size() && !researcher.isEmpty(); j++)
				{
					String rsc = frame.researchers.get(j).toLowerCase();
					if (!rsc.contains(researcher.toLowerCase()))
					{
						invalid = true;
						break;
					}
				}
				if (invalid)
					continue;
				
				for (int j = 0; j < frame.foci.size() && !focus.isEmpty(); j++)
				{
					String fcs = frame.foci.get(j).toLowerCase();
					if (!fcs.contains(focus.toLowerCase()))
					{
						invalid = true;
						break;
					}
				}
				
				if (!invalid)
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
