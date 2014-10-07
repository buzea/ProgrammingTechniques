package catalog;


import java.awt.EventQueue;
import java.util.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableColumnModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;

import java.awt.Toolkit;
import java.util.Map;

import javax.swing.JTable;

public class ScheduleFrame extends JFrame implements Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3141192535821164605L;
	private JPanel contentPane;
	private JTable table;
	private Catalog catalog;
	private String[] header={"Name","Professor","Room","Day","Hour","Group"};
	private boolean rooms;
	private JScrollPane scrollPane;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ScheduleFrame frame = new ScheduleFrame(false);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ScheduleFrame(boolean rooms) {
		catalog=Catalog.getInstance();
		catalog.addObserver(this);
		setTitle("Schedule");
		setIconImage(Toolkit.getDefaultToolkit().getImage(ScheduleFrame.class.getResource("/resource/png_open_book_by_lagrimadejarjayes-d5rnz2z.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 610, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		scrollPane = new JScrollPane();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 570, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(20, Short.MAX_VALUE)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 221, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		this.rooms=rooms;
		if(!rooms){
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
		
		}
		else
		{
			String[] RoomsHeader={"Room","Address","Capacity"};
			table=new JTable(getRooms(),RoomsHeader);
			
		}
		
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
			
	}
	
		
		
		
	private Object[][] getTable(){
		//"Name","Professor","Room","Day","Hour","Group"
		Map<String,Course> courses=catalog.getCourses();
		Object[][] o=new Object[courses.size()][6];
		int count=0;
		for(Course temp:courses.values())
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
	
	private Object[][] getRooms(){
		//"Name","Professor","Room","Day","Hour","Group"
		Map<String,Room> rooms=catalog.getRooms();
		Object[][] o=new Object[rooms.size()][3];
		int count=0;
		for(Room temp:rooms.values())
		{
			o[count][0]= temp.getIdRoom();
			o[count][1]= temp.getAddress();
			o[count][2]= temp.getCapacity();
			count++;
		}
		return o;
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		
		if(rooms){
			String[] RoomsHeader={"Room","Address","Capacity"};
			table=new JTable(getRooms(),RoomsHeader);
		}
		else
		{
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
		}
		scrollPane.setViewportView(table);
	}

	
	
	
	
}
