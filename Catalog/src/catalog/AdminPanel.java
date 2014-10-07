package catalog;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.CardLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdminPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1826219494787982145L;
	private CardLayout cl;
	private JPanel formPanel;

	/**
	 * Create the panel.
	 */
	public AdminPanel() {
		
		JButton btnEnrollStudent = new JButton("Enroll Student");
		btnEnrollStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(formPanel, "Student");
			}
		});
		
		JLabel lblAdministrationActions = new JLabel("Administration actions:");
		
		JButton btnAddNewProfessor = new JButton("Add new Professor");
		btnAddNewProfessor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cl.show(formPanel, "Professor");
			}
		});
		
		JButton btnAddNewRoom = new JButton("Add new Group");
		btnAddNewRoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(formPanel, "Group");
			}
		});
		
		JButton btnAddNewGroup = new JButton("Add new Room");
		btnAddNewGroup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(formPanel,"Room");
			}
		});
		
		JButton btnAddNewCourse = new JButton("Add new Course");
		btnAddNewCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(formPanel, "Course");
			}
		});
		
		JButton btnCreateNewAccount = new JButton("Create new Account");
		btnCreateNewAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(formPanel, "Account");
			}
		});
		
		formPanel = new JPanel();
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btnAddNewGroup, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnAddNewCourse, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnAddNewRoom, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblAdministrationActions)
						.addComponent(btnAddNewProfessor, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnEnrollStudent, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnCreateNewAccount))
					.addGap(18)
					.addComponent(formPanel, GroupLayout.PREFERRED_SIZE, 266, Short.MAX_VALUE)
					.addGap(25))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(formPanel, GroupLayout.PREFERRED_SIZE, 259, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblAdministrationActions)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnEnrollStudent)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnAddNewProfessor)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnAddNewRoom)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnAddNewCourse)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnCreateNewAccount)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnAddNewGroup)))
					.addContainerGap(23, Short.MAX_VALUE))
		);
		formPanel.setLayout(new CardLayout(0, 0));
		cl=(CardLayout)(formPanel.getLayout());
		formPanel.add(new JPanel(),"Default");
		formPanel.add(new StudentEnrollPanel(cl,formPanel),"Student");
		formPanel.add(new ProfessorAddPane(formPanel,cl),"Professor");
		formPanel.add(new GroupAddPanel(cl,formPanel),"Group");
		formPanel.add(new CourseAddPanel(cl,formPanel),"Course");
		formPanel.add(new AcountAddPanel(cl,formPanel),"Account");
		formPanel.add(new RoomAddPanel(cl,formPanel),"Room");
		setLayout(groupLayout);

	}
}
