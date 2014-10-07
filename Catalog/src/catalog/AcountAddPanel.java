package catalog;

import java.awt.CardLayout;

import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JRadioButton;



import java.util.Vector;

import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class AcountAddPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9061549483702480924L;
	private JLabel lblStudentSsid,lblTeacherSsid;
	private JComboBox<String> comboBox,comboBox_1;
	private Catalog catalog;
	private int level;
	private JTextField username;
	private JPasswordField password;
	private JPasswordField repassword;
	/**
	 * Create the panel.
	 * 
	 */
	public AcountAddPanel(final CardLayout cl,final JPanel formPanel) {
		catalog=Catalog.getInstance();
		JLabel lblUsername = new JLabel("Username:");
		
		JLabel lblPassword = new JLabel("Password:");
		
		JLabel lblRetypePassword = new JLabel("Re-type Password:");
		
		JRadioButton stud = new JRadioButton("Student");
		stud.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				lblStudentSsid.setVisible(true);
				
				comboBox.setVisible(true);
			
				lblTeacherSsid.setVisible(false);
				
				comboBox_1.setVisible(false);
				level=3;
			}
		});
		
		JLabel lblAccess = new JLabel("Access:");
		
		JRadioButton prof = new JRadioButton("Teacher");
		prof.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(arg0.getStateChange()==ItemEvent.SELECTED){
					lblStudentSsid.setVisible(false);
					
					comboBox.setVisible(false);
				
					lblTeacherSsid.setVisible(true);
					
					comboBox_1.setVisible(true);
					level=1;
				}
			}
		});
		
		JRadioButton admin = new JRadioButton("Admin");
		admin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblStudentSsid.setVisible(false);
				
				comboBox.setVisible(false);
			
				lblTeacherSsid.setVisible(false);
				
				comboBox_1.setVisible(false);
				level=0;
				
			}
		});
		
		admin.setSelected(true);
		ButtonGroup group=new ButtonGroup();
		group.add(stud);
		group.add(prof);
		group.add(admin);
		
		lblStudentSsid = new JLabel("Student SSID:");
		lblStudentSsid.setVisible(false);
		comboBox = new JComboBox<String>(new Vector<String>(catalog.getStudents().keySet()));
		comboBox.setVisible(false);
		lblTeacherSsid = new JLabel("Teacher SSID:");
		lblTeacherSsid.setVisible(false);
		comboBox_1 = new JComboBox<String>(new Vector<String>(catalog.getProfessors().keySet()));
		comboBox_1.setVisible(false);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String pass=new String(password.getPassword());
				String repass=new String(repassword.getPassword());
				if(pass.equals(repass)){
					try {
						String owner="0";
						if(level==3)
							owner=(String)comboBox.getSelectedItem();
						if(level==1)
							owner=(String)comboBox_1.getSelectedItem();
						catalog.addUser(username.getText(),pass, level,owner);
						username.setText("");
						password.setText("");
						repassword.setText("");
						cl.show(formPanel, "Default");
						JOptionPane.showMessageDialog(comboBox, "Account Created", "Success", 2);
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(comboBox, "Invalid Registration", "ERROR", JOptionPane.ERROR_MESSAGE);
					}
				}
				else
					JOptionPane.showMessageDialog(comboBox, "Password mismatch", "ERROR", 0);
				
			}
		});
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				username.setText("");
				password.setText("");
				repassword.setText("");
				cl.show(formPanel, "Default");
			}
		});
		
		username = new JTextField();
		username.setColumns(10);
		
		password = new JPasswordField();
		
		repassword = new JPasswordField();
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
									.addComponent(lblRetypePassword, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(lblPassword, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(lblUsername, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblAccess)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(admin)
										.addComponent(stud)
										.addComponent(prof))))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(repassword, Alignment.LEADING)
								.addComponent(password, Alignment.LEADING)
								.addComponent(username, Alignment.LEADING)))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblTeacherSsid)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnSubmit)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnCancel))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblStudentSsid)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(250, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblUsername)
						.addComponent(username, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPassword)
						.addComponent(password, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblRetypePassword)
						.addComponent(repassword, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAccess)
						.addComponent(stud))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(admin)
					.addGap(0)
					.addComponent(prof)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTeacherSsid)
						.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblStudentSsid)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSubmit)
						.addComponent(btnCancel))
					.addContainerGap(69, Short.MAX_VALUE))
		);
		setLayout(groupLayout);

	}
}
