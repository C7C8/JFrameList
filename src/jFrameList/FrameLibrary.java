package jFrameList;

import java.awt.List;
import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

class FrameLibrary
{
	protected List frameList = new List();
	
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
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
