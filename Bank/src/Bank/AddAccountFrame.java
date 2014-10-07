package Bank;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JSeparator;


public class AddAccountFrame extends JFrame {

	private static final long serialVersionUID = -6930527974280428160L;
	private JPanel contentPane;
	private JTextField txtPhone;
	private JTextField txtAddr;
	private JTextField txtEmail;
	private JTextField txtLastName;
	private JTextField txtFirstName;
	private JTextField txtSSID;
	private JTextField txtBalance;
	private JRadioButton rdbtnSavings;
	private JRadioButton rdbtnSpending;
	private ButtonGroup group;
	private JLabel lblDueDate;
	private JTextField txtDueDate;
	private JLabel lblFormat;
	public static final SimpleDateFormat dt = new SimpleDateFormat("yyyy-mm-dd");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddAccountFrame frame = new AddAccountFrame(new Bank("Accounts.txt"));
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
	public AddAccountFrame(final Bank bank) {
		setTitle("Add Account");
		setType(Type.POPUP);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 546, 322);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblOwner = new JLabel("Owner:");
		lblOwner.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JLabel lblSsid = new JLabel("SSID:");
		
		JLabel lblFirstName = new JLabel("First Name:");
		
		JLabel lblLastName = new JLabel("Last Name:");
		
		JLabel lblEmail = new JLabel("Email:");
		
		JLabel lblAddress = new JLabel("Address:");
		
		JLabel lblPhoneNumber = new JLabel("Phone number:");
		
		txtPhone = new JTextField();
		txtPhone.setText("0");
		txtPhone.setColumns(10);
		
		txtAddr = new JTextField();
		txtAddr.setColumns(10);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		
		txtLastName = new JTextField();
		txtLastName.setColumns(10);
		
		txtFirstName = new JTextField();
		txtFirstName.setColumns(10);
		
		txtSSID = new JTextField();
		txtSSID.setColumns(10);
		
		JLabel lblAccount = new JLabel("Account:");
		lblAccount.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JLabel lblType = new JLabel("Type:");
		
		JLabel lblInitialBalance = new JLabel("Initial Balance:");
		
		rdbtnSpending = new JRadioButton("Spending");
		rdbtnSpending.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblDueDate.setVisible(false);
				txtDueDate.setVisible(false);
				lblFormat.setVisible(false);
			}
		});
		rdbtnSpending.setSelected(true);
		
		rdbtnSavings = new JRadioButton("Savings");
		rdbtnSavings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lblDueDate.setVisible(true);
				txtDueDate.setVisible(true);
				lblFormat.setVisible(true);
			}
		});
		group = new ButtonGroup();
	        group.add(rdbtnSavings);
	        group.add(rdbtnSpending);
		
		txtBalance = new JTextField();
		txtBalance.setText("0.0");
		txtBalance.setColumns(10);
		
		JButton btnAddAccount = new JButton("Add Account");
		btnAddAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(txtSSID.getText().equals("")||txtFirstName.getText().equals("")||txtLastName.getText().equals("")||txtAddr.getText().equals("")||txtEmail.getText().equals("")||txtPhone.getText().equals("")||txtBalance.getText().equals(""))
					{JOptionPane.showMessageDialog(contentPane, "Please fill all the fields", "ERROR", JOptionPane.ERROR_MESSAGE);}
				else{
				Account a;
				Person owner=new Person(txtSSID.getText(),txtFirstName.getText(),txtLastName.getText(),txtAddr.getText(),txtEmail.getText(),Long.parseLong(txtPhone.getText()));
				if(rdbtnSpending.isSelected())
					{a=new SpendingAccount(owner,BankWindow.toDouble(txtBalance.getText())); bank.addAccount(a);
					if(bank.getAccount(a.getID())==null)
						JOptionPane.showMessageDialog(contentPane, "ERROR", "ERROR", JOptionPane.ERROR_MESSAGE);
					else
						JOptionPane.showMessageDialog(contentPane, "Account Added", "Success!", JOptionPane.INFORMATION_MESSAGE);
					}
				else
				{
					Calendar dueDate=new GregorianCalendar();
					try {
						dueDate.setTime(dt.parse(txtDueDate.getText()));
						a=new SavingsAccount(owner,BankWindow.toDouble(txtBalance.getText()),dueDate);
						bank.addAccount(a);
						if(bank.getAccount(a.getID())==null)
							JOptionPane.showMessageDialog(contentPane, "ERROR", "ERROR", JOptionPane.ERROR_MESSAGE);
						else
							JOptionPane.showMessageDialog(contentPane, "Account Added", "Success!", JOptionPane.INFORMATION_MESSAGE);
					} catch (ParseException e) {
						JOptionPane.showMessageDialog(contentPane, "Bad Due Date!", "ERROR", JOptionPane.ERROR_MESSAGE);
					}
					catch(Exception e1){
						JOptionPane.showMessageDialog(contentPane, "ERROR", "ERROR", JOptionPane.ERROR_MESSAGE);
					}
					
				}
				
				}	
				
			}
		});
		
		JSeparator separator = new JSeparator();
		
		lblDueDate = new JLabel("Due Date:");
		lblDueDate.setVisible(false);
		
		txtDueDate = new JTextField();
		txtDueDate.setColumns(10);
		txtDueDate.setVisible(false);
		
		lblFormat = new JLabel("yyyy-mm-dd");
		lblFormat.setVisible(false);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblOwner))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblPhoneNumber)
								.addComponent(lblAddress)
								.addComponent(lblEmail)
								.addComponent(lblLastName)
								.addComponent(lblFirstName)
								.addComponent(lblSsid))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(txtSSID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtFirstName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtLastName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtAddr, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtPhone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addGap(44)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(34)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblType)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(rdbtnSpending)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(rdbtnSavings))
						.addComponent(lblAccount)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
							.addComponent(btnAddAccount, GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addComponent(lblInitialBalance)
									.addComponent(lblDueDate))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addComponent(txtDueDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addComponent(txtBalance, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblFormat)))))
					.addGap(119))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblOwner)
								.addComponent(lblAccount))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblSsid)
								.addComponent(txtSSID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblType)
								.addComponent(rdbtnSpending)
								.addComponent(rdbtnSavings))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblFirstName)
								.addComponent(txtFirstName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblInitialBalance)
								.addComponent(txtBalance, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblLastName)
										.addComponent(txtLastName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblEmail)
										.addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblAddress)
										.addComponent(txtAddr, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblDueDate)
										.addComponent(txtDueDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblFormat)))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblPhoneNumber)
								.addComponent(txtPhone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnAddAccount, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)))
						.addComponent(separator, GroupLayout.PREFERRED_SIZE, 280, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
