package catalog;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JSpinner;

import java.awt.Font;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.TableColumnModel;
import javax.swing.*;

public class ProfessorMenuPanel extends JPanel implements Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5127251136264906775L;
	private JLabel lblWelcome;
	private Catalog catalog;
	private LoginPanel login;
	private Professor owner=null;
	private JTextField textField;
	private JTable table;
	private String[] header={"Name","Professor","Room","Day","Hour","Group"};
	private JScrollPane scrollPane;
	private JComboBox<Course> courseBox;
	private JComboBox<Student> studentBox;
	private JSpinner spinner;
	
	/**
	 * Create the panel.
	 */
	public ProfessorMenuPanel(LoginPanel login) {
		catalog=Catalog.getInstance();
		catalog.addObserver(this);
		lblWelcome = new JLabel("Welcome ");
		lblWelcome.setFont(new Font("Tahoma", Font.BOLD, 13));
		this.login=login;
		
		JLabel lblAddGrade = new JLabel("Add new Grade:");
		lblAddGrade.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JLabel lblCourse = new JLabel("Course:");
		
		JLabel lblStudent = new JLabel("Student:");
		
		JLabel lblDate = new JLabel("Date:");
		
		JLabel format = new JLabel("Date format : \"yyyy-MM-dd\"");
		
		courseBox = new JComboBox<Course>();
		courseBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setStudentBox();
			}
		});
		
		studentBox = new JComboBox<Student>();
		
		textField = new JTextField();
		textField.setColumns(10);
		spinner = new JSpinner((SpinnerModel) (new SpinnerNumberModel(5, 1, 10, 1)));
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(isValidDate(textField.getText()))
					{
					Student lucky=(Student)studentBox.getSelectedItem();
					Course  hard=(Course)courseBox.getSelectedItem();
					Calendar date=new GregorianCalendar();
					try {
						date.setTime(Catalog.format1.parse(textField.getText()));
						catalog.addGrade(lucky.getSSID(), hard.getIdCourse(), date, (int)spinner.getValue());
						JOptionPane.showMessageDialog(studentBox, "Grade added!", "Success", JOptionPane.INFORMATION_MESSAGE);
					} catch (ParseException  e1) {
						JOptionPane.showMessageDialog(studentBox, "Invalid Date format!", "ERROR", JOptionPane.WARNING_MESSAGE);
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(studentBox, "Invalid Input!", "ERROR", JOptionPane.WARNING_MESSAGE);
					}
					
					}
				else
					JOptionPane.showMessageDialog(studentBox, "Invalid Date format!", "ERROR", JOptionPane.WARNING_MESSAGE);
				
			}
		});
		
		JLabel lblMyCourses = new JLabel("My Courses:");
		lblMyCourses.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		scrollPane = new JScrollPane();
		
		JLabel lblMark = new JLabel("Mark:");
		
		JButton btnDeleteGrade = new JButton("Delete Grade");
		btnDeleteGrade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(isValidDate(textField.getText()))
				{
				Student lucky=(Student)studentBox.getSelectedItem();
				Course  hard=(Course)courseBox.getSelectedItem();
				Calendar date=new GregorianCalendar();
				try {
					date.setTime(Catalog.format1.parse(textField.getText()));
					catalog.deleteGrade(lucky.getSSID(), hard.getIdCourse(), date);
					JOptionPane.showMessageDialog(studentBox, "Grade deleted!", "Success", JOptionPane.INFORMATION_MESSAGE);
				} catch (ParseException | SQLException  e1) {
					JOptionPane.showMessageDialog(studentBox, "Invalid Input!", "ERROR", JOptionPane.WARNING_MESSAGE);
				}
				
				}
			else
				JOptionPane.showMessageDialog(studentBox, "Invalid Date format!", "ERROR", JOptionPane.WARNING_MESSAGE);
			
			}
		});
		
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblWelcome)
						.addComponent(lblAddGrade)
						.addComponent(format, GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblStudent)
								.addComponent(lblCourse)
								.addComponent(lblDate))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(textField)
								.addComponent(courseBox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(studentBox, 0, 118, Short.MAX_VALUE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnSubmit)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnDeleteGrade))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblMark)
							.addPreferredGap(ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
							.addComponent(spinner, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 335, Short.MAX_VALUE)
						.addComponent(lblMyCourses))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblWelcome)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAddGrade)
						.addComponent(lblMyCourses))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblCourse)
								.addComponent(courseBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblStudent)
								.addComponent(studentBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblDate)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(format)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblMark)
								.addComponent(spinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(28)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnSubmit)
								.addComponent(btnDeleteGrade)))
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE))
					.addContainerGap())
		);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		setLayout(groupLayout);

	}

	
	public void update(Observable arg0, Object arg1) {
		String action=(String)arg1;
		if(action.equals("forced")&&owner==null){
		String SSID=login.getOwnerId();
		owner=catalog.getProfessors().get(SSID);
		lblWelcome.setText("Welcome "+owner+"!");
		loadTable();
		setCoursesBox();
		courseBox.setSelectedIndex(0);
		setStudentBox();
		}
		
		
	}
	private Object[][] getTable(){
		//"Name","Professor","Room","Day","Hour","Group"
		Map<String,Course> courses=catalog.getCourses();
		Object[][] o=new Object[courses.size()][6];
		int count=0;
		for(Course temp:courses.values())
			if(temp.getProfessor().getSSID().equals(owner.getSSID()))
		{
			o[count][0]= temp.getName();
			o[count][1]= (temp.getProfessor().getFirstName()+" "+temp.getProfessor().getLastName());
			o[count][2]= temp.getRoom().getIdRoom();
			o[count][3]= temp.getDay();
			o[count][4]= temp.getHour();
			o[count][5]= temp.getGroup().getIdg();
			count++;
		}
		return o;
	}
	
	private void loadTable(){
		table = new JTable(getTable(),header);
		table.setEnabled(false);
		//Object[][] uateva=new Object[6][6];		
		//table =new JTable(uateva,header);
		TableColumnModel tcm = table.getColumnModel();
		tcm.getColumn(0).setPreferredWidth(120);     //Name
		tcm.getColumn(1).setPreferredWidth(100);    //Professor
		tcm.getColumn(2).setPreferredWidth(40);    //Room
		tcm.getColumn(3).setPreferredWidth(40);    //Day
		tcm.getColumn(4).setPreferredWidth(40);    //hour
		tcm.getColumn(5).setPreferredWidth(40);    //Group
		scrollPane.setViewportView(table);
	}
	
	private void setCoursesBox(){
		courseBox.removeAllItems();
		for(Course temp:catalog.getCourses().values())
			if(temp.getProfessor().getSSID().equals(owner.getSSID()))
				{
					courseBox.addItem(temp);
				}
		
	}
	
	private void setStudentBox(){
		studentBox.removeAllItems();
		Course c=(Course)courseBox.getSelectedItem();
		for(Student s:catalog.getStudents().values())
			if(s.getGroup().getIdg().equals(c.getGroup().getIdg()))
				studentBox.addItem(s);
		studentBox.setSelectedIndex(0);
	}
	public static boolean isValidDate(String date){
		if(date.length()>10)
			return false;
		if(date.length()<8)
			return false;
		String[] tokens=date.split("-");
		if(tokens.length!=3)
			return false;
		if(!Catalog.isNumeric(tokens[0]))
			return false;
		if(!Catalog.isNumeric(tokens[1]))
			return false;
		if(!Catalog.isNumeric(tokens[2]))
			return false;
		if(Integer.parseInt(tokens[1])>12)
			return false;
		if(Integer.parseInt(tokens[2])>31)
			return false;
		if(Integer.parseInt(tokens[1])==0)
			return false;
		if(Integer.parseInt(tokens[2])==0)
			return false;
		return true;
	}
}
