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

public class JFrameList
{

	private JFrame frmJframelist;
	private JTextField nameField;
	private JTextField yearField;
	private JTextField FocusField;
	private JTextField ResearcherField;
	private JTextField nameBox;
	private JTextField yearBox;
	private JTextField fociBox;
	private JTextField researcherBox;

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
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize()
	{
		frmJframelist = new JFrame();
		frmJframelist.setTitle("JFrameList");
		frmJframelist.setBounds(100, 100, 633, 389);
		frmJframelist.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmJframelist.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JLabel lblSelectedFrames = new JLabel("Selected FRAMEs");
		frmJframelist.getContentPane().add(lblSelectedFrames, BorderLayout.NORTH);
		
		List list = new List();
		frmJframelist.getContentPane().add(list, BorderLayout.WEST);
		
		JPanel panel = new JPanel();
		frmJframelist.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(290, 2, 41, 15);
		panel.add(lblName);
		
		nameField = new JTextField();
		nameField.setBounds(330, 0, 150, 19);
		panel.add(nameField);
		nameField.setColumns(10);
		
		JLabel lblYear = new JLabel("Year");
		lblYear.setBounds(300, 33, 32, 15);
		panel.add(lblYear);
		
		yearField = new JTextField();
		yearField.setBounds(330, 31, 70, 19);
		panel.add(yearField);
		yearField.setColumns(10);
		
		JLabel lblResearcher = new JLabel("Researcher");
		lblResearcher.setBounds(250, 93, 81, 15);
		panel.add(lblResearcher);
		
		FocusField = new JTextField();
		FocusField.setBounds(330, 59, 150, 19);
		panel.add(FocusField);
		FocusField.setColumns(10);
		
		JLabel lblFocus = new JLabel("Focus");
		lblFocus.setBounds(290, 60, 41, 17);
		panel.add(lblFocus);
		
		ResearcherField = new JTextField();
		ResearcherField.setColumns(10);
		ResearcherField.setBounds(330, 91, 150, 19);
		panel.add(ResearcherField);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		btnSearch.setBounds(330, 128, 117, 25);
		panel.add(btnSearch);
		
		JLabel lblNnameme = new JLabel("Results");
		lblNnameme.setBounds(41, 2, 70, 15);
		panel.add(lblNnameme);
		
		JLabel lblName_1 = new JLabel("Name");
		lblName_1.setBounds(0, 33, 70, 15);
		panel.add(lblName_1);
		
		nameBox = new JTextField();
		nameBox.setEditable(false);
		nameBox.setBounds(68, 31, 176, 19);
		panel.add(nameBox);
		nameBox.setColumns(10);
		
		JLabel lblYear_1 = new JLabel("Year");
		lblYear_1.setBounds(0, 61, 70, 15);
		panel.add(lblYear_1);
		
		yearBox = new JTextField();
		yearBox.setEditable(false);
		yearBox.setBounds(68, 59, 43, 19);
		panel.add(yearBox);
		yearBox.setColumns(10);
		
		JLabel lblFocus_1 = new JLabel("Foci");
		lblFocus_1.setBounds(0, 93, 70, 15);
		panel.add(lblFocus_1);
		
		fociBox = new JTextField();
		fociBox.setEditable(false);
		fociBox.setBounds(68, 91, 114, 19);
		panel.add(fociBox);
		fociBox.setColumns(10);
		
		JLabel lblResearchers = new JLabel("Researchers");
		lblResearchers.setBounds(0, 119, 89, 15);
		panel.add(lblResearchers);
		
		researcherBox = new JTextField();
		researcherBox.setEditable(false);
		researcherBox.setBounds(95, 117, 149, 19);
		panel.add(researcherBox);
		researcherBox.setColumns(10);
	}
}
