package catalog;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JTable;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.*;
import java.awt.Font;


public class StudentPanel extends JPanel implements Observer{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4134405002981871546L;
	private Catalog catalog;
	private String ids;
	private JTable table;
	private JTable table_1;
	private LoginPanel login;
	private JScrollPane scrollPane,scrollPane_1;
	private JLabel welcome;

	/**
	 * Create the panel.
	 */
	public StudentPanel(LoginPanel login) {
		catalog=Catalog.getInstance();
		this.login=login;
		JLabel lblMyGrades = new JLabel("My Grades:");
		catalog.addObserver(this);
		scrollPane = new JScrollPane();
		
		JLabel lblMyCourses = new JLabel("My Courses:");
		
		scrollPane_1 = new JScrollPane();
		
		JButton btnLoadTables = new JButton("Load Tables");
		btnLoadTables.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadTables();
			}
		});
		
		welcome = new JLabel("New label");
		welcome.setVisible(false);
		welcome.setFont(new Font("Tahoma", Font.BOLD, 12));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblMyGrades)
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE))
							.addGap(8)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 429, Short.MAX_VALUE)
								.addComponent(lblMyCourses)))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnLoadTables)
							.addGap(18)
							.addComponent(welcome)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(15)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnLoadTables)
						.addComponent(welcome))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblMyGrades)
						.addComponent(lblMyCourses))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 213, GroupLayout.PREFERRED_SIZE)
						.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 213, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		
		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		setLayout(groupLayout);

	}
	private void loadTables(){
		ids=login.getOwnerId();
		String[] gradeHeader={"Course","Grade","Date"};
		List<Grade> grades=catalog.getGrades();
		Iterator<Grade> i=grades.iterator();
		int count=0;
		Grade temp;
		while(i.hasNext()){
			temp=i.next();
			if(temp.getStudent().getSSID().equals(ids))
				count++;
		
		}
		Object[][] o=new Object[count][3];
		
		i=grades.iterator();
		count=0;
		while(i.hasNext()){
			temp=i.next();
			if(temp.getStudent().getSSID().equals(ids)){
				o[count][0]=temp.getCourse().getName();
				o[count][1]=temp.getMark();
				o[count][2]=temp.gatFormatedDate();
				count++;
			}
				
		
		}
		
		table=new JTable(o,gradeHeader);
		table.setEnabled(false);
		scrollPane.setViewportView(table);
		
		
		/////////////////////////////////Courses Table
		String[] courseHeader={"Name","Professor","Room","Day","Hour","Group"};
		Map<String,Course> courses=catalog.getCourses();
		Object[][] o1=new Object[courses.size()][6];
		count=0;
		for(Course tem:courses.values())
			if(tem.getGroup().getIdg().equals(catalog.getStudents().get(ids).getGroup().getIdg()))
		{
			o1[count][0]= tem.getName();
			o1[count][1]= (tem.getProfessor().getFirstName()+" "+tem.getProfessor().getLastName());
			o1[count][2]= tem.getRoom().getIdRoom();
			o1[count][3]= tem.getDay();
			o1[count][4]= tem.getHour();
			o1[count][5]= tem.getGroup().getIdg();
			count++;
		}
		
		table_1=new JTable(o1,courseHeader);
		table_1.setEnabled(false);
		scrollPane_1.setViewportView(table_1);
		
		welcome.setText("Student: "+catalog.getStudents().get(ids).toString());
		welcome.setVisible(true);
	}
	public void update(Observable arg0, Object arg1) {
		String action=(String)arg1;
		if(action.equals("forced")|| action.equals("Added Grade") || action.equals("Added Course")||action.equals("Deleted Grade")){
		loadTables();
		}
		
	}
}
