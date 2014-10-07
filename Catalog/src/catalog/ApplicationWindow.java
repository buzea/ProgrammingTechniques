package catalog;

import java.awt.EventQueue;



import javax.swing.JFrame;

import java.awt.Toolkit;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.Font;



import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import java.util.*;


public class ApplicationWindow {

	private JFrame frmSchoolApplication;
	private Catalog catalog;
;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ApplicationWindow window = new ApplicationWindow();
					window.frmSchoolApplication.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ApplicationWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSchoolApplication = new JFrame();
		frmSchoolApplication.setTitle("School Application");
		frmSchoolApplication.setIconImage(Toolkit.getDefaultToolkit().getImage(ApplicationWindow.class.getResource("/resource/png_open_book_by_lagrimadejarjayes-d5rnz2z.png")));
		frmSchoolApplication.setBounds(100, 100, 425, 327);
		frmSchoolApplication.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSchoolApplication.setResizable(false);
		catalog=Catalog.getInstance();
		
		
		JButton btnAdministration = new JButton("Administration");
		btnAdministration.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminFrame aFr=new AdminFrame();
				aFr.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				aFr.setVisible(true);
			}
		});
		
		JLabel lblLogin = new JLabel("Login:");
		lblLogin.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JButton btnProfessor = new JButton("Professor");
		btnProfessor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProfessorWindow prof=new ProfessorWindow();
				prof.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				prof.setVisible(true);
			}
		});
		
		JButton btnNewButton = new JButton("Student");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StudentFrame fr=new StudentFrame();
				fr.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				fr.setVisible(true);
			}
		});
		
		JSeparator separator = new JSeparator();
		
		JLabel lblGeneralActions = new JLabel("General Actions:");
		lblGeneralActions.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JButton btnViewSchedule = new JButton("View Schedule");
		btnViewSchedule.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ScheduleFrame schedule=new ScheduleFrame(false);
				schedule.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				schedule.setVisible(true);
			}
		});
		
		JTextArea txtrContactInformation = new JTextArea();
		txtrContactInformation.setBackground(SystemColor.menu);
		txtrContactInformation.setText("Contact Information:\r\nEmail: buzea.vlad@gmail.com");
		txtrContactInformation.setEditable(false);
		
		JButton btnViewSchoolRooms = new JButton("View School Rooms");
		btnViewSchoolRooms.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ScheduleFrame schedule=new ScheduleFrame(true);
				schedule.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				schedule.setVisible(true);
			}
		});
		
		JButton btnViewProfessors = new JButton("View Professors");
		btnViewProfessors.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String[] header={"SSID","Name","Email","Phone","Departament"};
				TableFrame tb=new TableFrame(getProfessorsTable(),header);
				tb.setVisible(true);
				tb.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				
			}
		});
		
		GroupLayout groupLayout = new GroupLayout(frmSchoolApplication.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
									.addComponent(lblLogin, Alignment.LEADING)
									.addComponent(btnAdministration, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(btnProfessor, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(btnNewButton, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE))
								.addComponent(separator, GroupLayout.PREFERRED_SIZE, 532, GroupLayout.PREFERRED_SIZE))
							.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(11)
									.addComponent(lblGeneralActions))
								.addComponent(btnViewSchoolRooms, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnViewProfessors, GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
								.addComponent(btnViewSchedule, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
							.addComponent(txtrContactInformation, GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE)
							.addGap(57))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblLogin)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnAdministration)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnProfessor)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnNewButton)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblGeneralActions)
							.addGap(5)
							.addComponent(btnViewSchedule)
							.addGap(7)
							.addComponent(btnViewSchoolRooms)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnViewProfessors))
						.addComponent(txtrContactInformation, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(16, Short.MAX_VALUE))
		);
		frmSchoolApplication.getContentPane().setLayout(groupLayout);
		
		JMenuBar menuBar = new JMenuBar();
		frmSchoolApplication.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(1);
			}
		});
		mnFile.add(mntmExit);
	}
	
	private Object[][] getProfessorsTable(){
		//"SSID","Name","Email","Phone","Departament"
		Map<String,Professor> prof=catalog.getProfessors();
		Object[][] o=new Object[prof.size()][5];
		int i=0;
		for(Professor temp:prof.values()){
			o[i][0]=temp.getSSID();
			o[i][1]=(temp.getFirstName()+" "+temp.getLastName());
			o[i][2]=temp.getEmail();
			o[i][3]=temp.getPhone();
			o[i][4]=temp.getDepartament();
			i++;
		}
		
		return o;
	}
}
