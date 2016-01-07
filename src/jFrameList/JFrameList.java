package jFrameList;

import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.List;
import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class JFrameList
{

	private JFrame frmJframelist;
	private JTextField nameField;
	private JTextField yearField;
	private JTextField focusField;
	private JTextField researcherField;
	private JTextField nameBox;
	private JTextField yearBox;
	private JTextField fociBox;
	private JTextField researcherBox;
	List list; //I probably shouldn't do this.
	
	private FrameLibrary frameList;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					JFrameList window = new JFrameList();
					window.frmJframelist.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public JFrameList()
	{
		frameList = new FrameLibrary();
		frameList.parse("FRAME_List.xml");
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize()
	{
		frmJframelist = new JFrame();
		frmJframelist.setTitle("JFrameList");
		frmJframelist.setBounds(100, 100, 760, 389);
		frmJframelist.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmJframelist.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JLabel lblSelectedFrames = new JLabel("Selected FRAMEs");
		frmJframelist.getContentPane().add(lblSelectedFrames, BorderLayout.NORTH);
		ArrayList<String> fNames = frameList.getNames();
		
		JPanel panel = new JPanel();
		frmJframelist.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(556, 2, 41, 15);
		panel.add(lblName);
		
		nameField = new JTextField();
		nameField.setBounds(596, 0, 150, 19);
		panel.add(nameField);
		nameField.setColumns(10);
		
		JLabel lblYear = new JLabel("Year");
		lblYear.setBounds(556, 33, 32, 15);
		panel.add(lblYear);
		
		yearField = new JTextField();
		yearField.setBounds(596, 31, 70, 19);
		panel.add(yearField);
		yearField.setColumns(10);
		
		JLabel lblResearcher = new JLabel("Researcher");
		lblResearcher.setBounds(510, 93, 81, 15);
		panel.add(lblResearcher);
		
		focusField = new JTextField();
		focusField.setBounds(596, 58, 150, 19);
		panel.add(focusField);
		focusField.setColumns(10);
		
		JLabel lblFocus = new JLabel("Focus");
		lblFocus.setBounds(550, 59, 41, 17);
		panel.add(lblFocus);
		
		researcherField = new JTextField();
		researcherField.setColumns(10);
		researcherField.setBounds(596, 91, 150, 19);
		panel.add(researcherField);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				list.removeAll();
				int year;
				if (yearField.getText().isEmpty())
					year = 0;
				else
					year = Integer.parseInt(yearField.getText());
				ArrayList<Frame> results = frameList.filter(nameField.getText(), 
						year, 
						researcherField.getText(),
						focusField.getText());
				for (int i = 0; i < results.size(); i++)
					list.add(results.get(i).name);
			}
		});
		btnSearch.setBounds(629, 122, 117, 25);
		panel.add(btnSearch);
		
		JLabel lblNnameme = new JLabel("Results");
		lblNnameme.setBounds(334, 2, 70, 15);
		panel.add(lblNnameme);
		
		JLabel lblName_1 = new JLabel("Name");
		lblName_1.setBounds(201, 33, 70, 15);
		panel.add(lblName_1);
		
		nameBox = new JTextField();
		nameBox.setEditable(false);
		nameBox.setBounds(250, 31, 288, 19);
		panel.add(nameBox);
		nameBox.setColumns(10);
		
		JLabel lblYear_1 = new JLabel("Year");
		lblYear_1.setBounds(201, 60, 70, 15);
		panel.add(lblYear_1);
		
		yearBox = new JTextField();
		yearBox.setEditable(false);
		yearBox.setBounds(238, 58, 52, 19);
		panel.add(yearBox);
		yearBox.setColumns(10);
		
		JLabel lblFocus_1 = new JLabel("Foci");
		lblFocus_1.setBounds(201, 93, 70, 15);
		panel.add(lblFocus_1);
		
		fociBox = new JTextField();
		fociBox.setEditable(false);
		fociBox.setBounds(238, 91, 166, 19);
		panel.add(fociBox);
		fociBox.setColumns(10);
		
		JLabel lblResearchers = new JLabel("Researchers");
		lblResearchers.setBounds(201, 122, 89, 15);
		panel.add(lblResearchers);
		
		researcherBox = new JTextField();
		researcherBox.setEditable(false);
		researcherBox.setBounds(290, 120, 248, 19);
		panel.add(researcherBox);
		researcherBox.setColumns(10);
		
		list = new List();
		list.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				Frame selection = frameList.searchName(list.getSelectedItem()).get(0); //Hack? Should work.
				nameBox.setText(selection.name);
				yearBox.setText(Integer.toString(selection.year));
				researcherBox.setText(selection.getResearchers());
				fociBox.setText(selection.getFoci());
			}
		});
		
		list.setBounds(0, 0, 199, 334);
		panel.add(list);
		for (int i = 0; i < fNames.size(); i++)
			list.add(fNames.get(i));
	}
}
