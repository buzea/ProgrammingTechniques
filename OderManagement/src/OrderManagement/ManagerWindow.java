package OrderManagement;

//import javax.swing.*;
import java.awt.*;

import javax.swing.JFrame;


import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JLabel;




import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import com.jgoodies.forms.factories.DefaultComponentFactory;

import java.awt.Font;


public class ManagerWindow {

	private JFrame frame;
	private JPasswordField passwordField;
	private static final String PASSWORD="pass";
	private static JPanel panel,panel2;
	private OPDept opd;
	private Warehouse warehouse=new Warehouse();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManagerWindow window = new ManagerWindow(new OPDept());
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ManagerWindow(OPDept opd) {
		this.opd=opd;
		initialize();
		this.frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Manager Window");
		
		panel2 = new JPanel();
		panel2.setBounds(0, 0, 444, 272);
		frame.getContentPane().add(panel2);
		panel2.setVisible(false);
		panel2.setLayout(null);
		
		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setVisible(true);
				panel2.setVisible(false);
			}
		});
		btnLogOut.setBounds(0, 36, 89, 23);
		panel2.add(btnLogOut);
		
		JButton btnViewAllOrders = new JButton("View All Orders");
		btnViewAllOrders.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OrderTableFrame fram=new OrderTableFrame();
				fram.setTitle("All orders");
				fram.setVisible(true);
				
			}
		});
		btnViewAllOrders.setBounds(0, 70, 185, 23);
		panel2.add(btnViewAllOrders);
		
		JButton btnViewDeliveredOrders = new JButton("View Delivered Orders");
		btnViewDeliveredOrders.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OrderTableFrame fr=new OrderTableFrame(opd.orderTable(true));
				fr.setTitle("Delivered Orders");
				fr.setVisible(true);
			}
		});
		btnViewDeliveredOrders.setBounds(0, 104, 185, 23);
		panel2.add(btnViewDeliveredOrders);
		
		JButton btnViewUndeliveredOrders = new JButton("View Undelivered Orders");
		btnViewUndeliveredOrders.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OrderTableFrame fr=new OrderTableFrame(opd.orderTable(false));
				fr.setTitle("Undelivered Orders");
				fr.setVisible(true);
			}
		});
		btnViewUndeliveredOrders.setBounds(0, 138, 185, 23);
		panel2.add(btnViewUndeliveredOrders);
		
		JButton btnClearAllOrders = new JButton("Clear all orders");
		btnClearAllOrders.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				opd.clear();
				JOptionPane.showMessageDialog(panel2, "ALL ORDERS HAVE BEEN DELETED!", "CLEAR ORDES",JOptionPane.ERROR_MESSAGE);
			}
		});
		btnClearAllOrders.setBounds(0, 211, 185, 23);
		panel2.add(btnClearAllOrders);
		//panel2.add(new JScrollPane(list));
		
		JLabel lblPanel = new JLabel("Options:");
		lblPanel.setBounds(0, 12, 112, 23);
		panel2.add(lblPanel);
		lblPanel.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JButton btnViewWarehouseStats = new JButton("View Warehouse Stats");
		btnViewWarehouseStats.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new WarehouseFrame(warehouse.statsArray());
			}
		});
		btnViewWarehouseStats.setBounds(0, 173, 185, 26);
		panel2.add(btnViewWarehouseStats);
		
		JButton btnAdd = new JButton("<html><center>"+"Add product"+"<br>"+"to Warehouse"+"</center></html>");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddProductFrame fr=new AddProductFrame();
				fr.setVisible(true);
			}
		});
		btnAdd.setBounds(300, 70, 144, 57);
		panel2.add(btnAdd);
		
		
		
		panel = new JPanel();
		panel.setBounds(126, 0, 192, 250);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(93, 86, 89, 23);
		panel.add(passwordField);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s=new String(passwordField.getPassword());
				if(s.equals(PASSWORD))
					{
						panel.setVisible(false);
						panel2.setVisible(true);
					}
				else
				{
					JOptionPane.showMessageDialog(panel, "Wrong password!\r\nPlease try again.", "Login", JOptionPane.ERROR_MESSAGE);
					passwordField.setText("");
				}
			}
		});
		btnSubmit.setBounds(47, 120, 89, 23);
		panel.add(btnSubmit);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(10, 90, 73, 14);
		panel.add(lblPassword);
		
		JLabel lblManagerLogin = DefaultComponentFactory.getInstance().createTitle("Manager Login");
		lblManagerLogin.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblManagerLogin.setBounds(10, 36, 172, 39);
		panel.add(lblManagerLogin);
		
		
		 
		
		
	}
	
}
