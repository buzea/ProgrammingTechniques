package OrderManagement;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Date;



@SuppressWarnings("serial")
public class OrderTableFrame extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private OPDept opd;
	private JButton btnMark;
	private JScrollPane scrollPane;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrderTableFrame frame = new OrderTableFrame();
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
	public OrderTableFrame() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 809, 520);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 68, 774, 406);
		contentPane.add(scrollPane);
		
		opd=new OPDept();
		final String[] header={"Delivered","Customer","Product","Data","Quantity","Value ($)"};
		
		table = new JTable(opd.orderTable(),header);
		//Object[][] fields=new Object[6][6]; 
		//table =new JTable(fields,header);
		scrollPane.setViewportView(table);
		
		btnMark = new JButton("Mark as Delivered");
		btnMark.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{int row=table.getSelectedRow();
				Product p=(Product)table.getValueAt(row, 2);
				Customer c=(Customer)table.getValueAt(row, 1);
				Date d=(Date)table.getValueAt(row, 3);
				Order o=new Order(0,p,c,d);
				opd.recievedOrder(o);
				table=new JTable(opd.orderTable(),header);
				scrollPane.setViewportView(table);
				scrollPane.repaint();
				}catch(Exception e){
					e.getStackTrace();
				}
				
			}
		});
		btnMark.setBounds(10, 11, 153, 23);
		contentPane.add(btnMark);
		setVisible(true);
	}
	
	public OrderTableFrame(Object[][] rows) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 809, 520);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 774, 463);
		contentPane.add(scrollPane);
		
		final String[] header={"Delivered","Customer","Product","Data","Quantity","Value ($)"};
		table = new JTable(rows,header);
		//table =new JTable();
		scrollPane.setViewportView(table);
		
		setVisible(true);
	}
	
	
}
