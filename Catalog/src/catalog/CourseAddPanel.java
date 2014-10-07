package catalog;

import java.awt.CardLayout;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;


import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JButton;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CourseAddPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6363693980774028041L;
	private JTextField name;
	private JSpinner spinner;
	public static final String[] DAYS={"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};
	public static final String[] HOURS={"8","9","10","11","12","13","14","15","16","17","18","19","20"};
	private Catalog catalog;
	/**
	 * Create the panel.
	 */
	public CourseAddPanel(final CardLayout cl,final JPanel formPanel) {
		catalog=Catalog.getInstance();
		JLabel lblName = new JLabel("Name:");
		
		JLabel lblProfessor = new JLabel("Professor:");
		
		JLabel lblRoom = new JLabel("Room:");
		
		JLabel lblDay = new JLabel("Day:");
		
		JLabel lblHour = new JLabel("Hour:");
		
		name = new JTextField();
		name.setColumns(10);
		
		final JComboBox<Professor> professor = new JComboBox<Professor>(new Vector<Professor>(catalog.getProfessors().values()));
		
		final JComboBox<Room> room = new JComboBox<Room>( new Vector<Room>(catalog.getRooms().values()));
		
		final JComboBox<String> day = new JComboBox<String>(DAYS);
		
		final JComboBox<Group> group = new JComboBox<Group>(new Vector<Group>(catalog.getGroups().values()));
		
		spinner = new JSpinner((SpinnerModel) (new SpinnerNumberModel(8, 8, 20, 1)));
		spinner.setCursor(null);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			Set<String> temp=catalog.getCourses().keySet();
			Iterator<String> i=temp.iterator();
			int idCourse=0;
			while(i.hasNext()){
				int k=Integer.parseInt(i.next());
				if(k>idCourse)
					idCourse=k;
			}
			idCourse++;
			int h=(int)spinner.getValue();
			String hour=new String(""+h);
			String id=new String(""+idCourse);
			try {
				catalog.addCourse(id , name.getText(), (String)day.getSelectedItem(), hour , (Group)group.getSelectedItem(), (Professor)professor.getSelectedItem(), (Room) room.getSelectedItem());
				JOptionPane.showMessageDialog(name, "Register successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
				cl.show(formPanel, "Default");
				name.setText("");
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(name, "Input Error!", "ERROR", 0);
				cl.show(formPanel, "Default");
				name.setText("");
			}
			
			}
		});
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				name.setText("");
				cl.show(formPanel, "Default");
			}
		});
		
		JLabel lblGroup = new JLabel("Group:");
		
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
										.addComponent(lblProfessor, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(lblName, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(lblRoom, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(lblDay, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(lblHour, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(spinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(day, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(room, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(professor, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(name)
										.addComponent(group, 0, 121, Short.MAX_VALUE)))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnSubmit)
									.addPreferredGap(ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
									.addComponent(btnCancel))))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblGroup)))
					.addContainerGap(265, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblName)
						.addComponent(name, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblProfessor)
						.addComponent(professor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblRoom)
						.addComponent(room, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDay)
						.addComponent(day, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblHour)
						.addComponent(spinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblGroup)
						.addComponent(group, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSubmit)
						.addComponent(btnCancel))
					.addContainerGap(89, Short.MAX_VALUE))
		);
		setLayout(groupLayout);

	}
}
