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

					NodeList researchers = eFrame.getElementsByTagName("researchers");
					for (int rNum = 0; rNum < researchers.getLength(); rNum++)
					{
						Node nRSC = researchers.item(rNum);
						if (nRSC.getNodeType() == Node.ELEMENT_NODE)
						{
							Element eRSC = (Element) nRSC;
							frame.researchers.add(eRSC.getTextContent());
						}
					}

					NodeList foci = eFrame.getElementsByTagName("focus");
					for (int fNum = 0; fNum < foci.getLength(); fNum++)
					{
						Node nFCS = foci.item(fNum);
						if (nFCS.getNodeType() == Node.ELEMENT_NODE)
						{
							Element eFCS = (Element) nFCS;
							frame.foci.add(eFCS.getTextContent());
						}
					}
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
}
