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
import java.awt.TextArea;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JTextPane;
import javax.swing.UIManager;

public class JFrameList
{

	private JFrame frmJframelist;
	private JTextField nameField;
	private JTextField yearField;
	private JTextField focusField;
	private JTextField researcherField;
	private JTextField yearBox;
	private JTextField fociBox;
	private JTextField researcherBox;
	List list; //I probably shouldn't do this.
	
	private FrameLibrary frameList;
	private TextArea nameBox;
	private JLabel lblSourecLogoContrast;

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
		frameList.parse();
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
		
		nameField = new JTextField();
		nameField.setToolTipText("Name - the name of the study.");
		nameField.setBounds(309, 266, 150, 19);
		panel.add(nameField);
		nameField.setColumns(10);
		
		yearField = new JTextField();
		yearField.setToolTipText("Year - when the study was conducted. 1 for unknown year.");
		yearField.setBounds(309, 230, 70, 19);
		panel.add(yearField);
		yearField.setColumns(10);
		
		focusField = new JTextField();
		focusField.setToolTipText("Focus - what a study focuses on (e.g. Social Identity Theory)");
		focusField.setBounds(208, 266, 89, 19);
		panel.add(focusField);
		focusField.setColumns(10);
		
		researcherField = new JTextField();
		researcherField.setToolTipText("Researcher - who conducted the study.");
		researcherField.setColumns(10);
		researcherField.setBounds(208, 230, 89, 19);
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
		btnSearch.setBounds(208, 297, 117, 25);
		panel.add(btnSearch);
		
		JLabel lblStudyDetails = new JLabel("Study Details");
		lblStudyDetails.setBounds(334, 0, 172, 15);
		panel.add(lblStudyDetails);
		
		yearBox = new JTextField();
		yearBox.setToolTipText("Year - when the selected study was conducted.");
		yearBox.setText("Year");
		yearBox.setEditable(false);
		yearBox.setBounds(524, 47, 52, 19);
		panel.add(yearBox);
		yearBox.setColumns(10);
		
		fociBox = new JTextField();
		fociBox.setToolTipText("Focus - what the selected study focused on (e.g. culture).");
		fociBox.setText("Focus");
		fociBox.setEditable(false);
		fociBox.setBounds(208, 90, 310, 19);
		panel.add(fociBox);
		fociBox.setColumns(10);
		
		researcherBox = new JTextField();
		researcherBox.setToolTipText("Researchers - who conducted the selected study.");
		researcherBox.setText("Researchers");
		researcherBox.setEditable(false);
		researcherBox.setBounds(524, 16, 234, 19);
		panel.add(researcherBox);
		researcherBox.setColumns(10);
		
		list = new List();
		list.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				if (list.getItemCount() == 0)
					return;
				Frame selection = frameList.searchName(list.getSelectedItem()).get(0); //Hack? Should work.
				nameBox.setText(selection.name);
				yearBox.setText(Integer.toString(selection.year));
				researcherBox.setText(selection.getResearchers());
				fociBox.setText(selection.getFoci());
				
				nameBox.setCaretPosition(0);
				researcherBox.setCaretPosition(0);
				fociBox.setCaretPosition(0);
			}
		});
		
		list.setBounds(0, 0, 199, 334);
		panel.add(list);
		
		nameBox = new TextArea();
		nameBox.setText("Name");
		nameBox.setFont(new Font("Dialog", Font.PLAIN, 10));
		nameBox.setEditable(false);
		nameBox.setBounds(208, 16, 310, 70);
		panel.add(nameBox);
		
		JLabel lblResearcher = new JLabel("Researcher");
		lblResearcher.setLabelFor(researcherField);
		lblResearcher.setBounds(208, 214, 89, 15);
		panel.add(lblResearcher);
		
		JLabel lblYear = new JLabel("Year");
		lblYear.setLabelFor(yearField);
		lblYear.setBounds(309, 214, 70, 15);
		panel.add(lblYear);
		
		JLabel lblFocus = new JLabel("Focus");
		lblFocus.setBounds(208, 252, 70, 15);
		panel.add(lblFocus);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(309, 252, 70, 15);
		panel.add(lblName);
		
		lblSourecLogoContrast = new JLabel("");
		lblSourecLogoContrast.setIcon(new ImageIcon(JFrameList.class.getResource("/jFrameList/s_atom.png")));
		lblSourecLogoContrast.setBounds(710, 279, 48, 55);
		panel.add(lblSourecLogoContrast);
		
		JTextPane txtpnSelectAStudy = new JTextPane();
		txtpnSelectAStudy.setBackground(UIManager.getColor("window"));
		txtpnSelectAStudy.setEditable(false);
		txtpnSelectAStudy.setText("Select a study on the left to view more details. Refine your search by entering search terms and clicking \"Search\".");
		txtpnSelectAStudy.setBounds(208, 121, 172, 81);
		panel.add(txtpnSelectAStudy);
		for (int i = 0; i < fNames.size(); i++)
			list.add(fNames.get(i));
	}
}
