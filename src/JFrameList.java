import java.util.ArrayList;

import jFrameList.*;

public class JFrameList
{
	static final String encoding = "UTF-8";
	public static void main(String[] args)
	{
		String filename = new String("FRAME_List.xml");
		FrameLibrary flib = new FrameLibrary();
		flib.parse(filename);
		ArrayList<Frame> temp = flib.searchFocus("Culture");
		for (int i = 0; i < temp.size(); i++)
		{
			System.out.println(temp.get(i).name);
			System.out.println(temp.get(i).year);
			System.out.println("===");
		}
	}
}
