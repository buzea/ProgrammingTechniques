package Bank;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import java.io.*;

public class BankWindow {

	private JFrame frmBank;
	private JTextField txtID1;
	private JTextField txtID2;
	private JTextField txtAmount;
	private Bank bank;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BankWindow window = new BankWindow();
					window.frmBank.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public BankWindow() {
		initialize();
	}
	
	public static double toDouble(String s){
		double balance=0.0;
		char[] chars=s.toCharArray();
		int i;
		double f,div;
		for(i=0;i<chars.length && chars[i]!='.';i++)
			if(Character.isDigit(chars[i]))
				{balance*=10; balance+= chars[i]-'0';}
		for(div=10;i<chars.length;i++)
			if(Character.isDigit(chars[i]))
				{f=(chars[i]-'0')/div; div*=10; balance+=f;}
		return balance;
	}
	
	private boolean checkID(String ID){
		if(bank.getAccount(ID)==null)
		{
			JOptionPane.showMessageDialog(frmBank, "Not a Valid ID!", "ID ERROR", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		return true;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		bank=new Bank("Accounts.txt");
		frmBank = new JFrame();
		frmBank.setTitle("Bank");
		frmBank.setBounds(100, 100, 457, 325);
		frmBank.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btnViewAccounts = new JButton("View All Accounts");
		btnViewAccounts.setToolTipText("Opens a new Window with all the accounts contained by the bank");
		btnViewAccounts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AccountsFrame frame=new AccountsFrame(bank);
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				frame.setVisible(true);
			}
		});
		
		JButton btnAddAccount = new JButton("Add Account");
		btnAddAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddAccountFrame frame=new AddAccountFrame(bank);
				frame.setVisible(true);
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		});
		btnAddAccount.setToolTipText("Opens a window to add a new Account");
		
		JButton btnRemoveAccount = new JButton("Remove Account");
		btnRemoveAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(checkID(txtID1.getText())){
					bank.removeAccount(txtID1.getText());
					JOptionPane.showMessageDialog(frmBank, "removed","Account info",JOptionPane.INFORMATION_MESSAGE);
				}
					
			
			}
		});
		btnRemoveAccount.setToolTipText("Removes Account with ID 1 from bank");
		
		txtID1 = new JTextField();
		txtID1.setColumns(10);
		
		JButton btnViewAccountLog = new JButton("View Account Log");
		btnViewAccountLog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checkID(txtID1.getText()))
					JOptionPane.showMessageDialog(frmBank, bank.getAccount(txtID1.getText()).getLog(),"Account info",JOptionPane.INFORMATION_MESSAGE);
			
			}
		});
		btnViewAccountLog.setToolTipText("Shows the log of Account with ID 1");
		
		JButton btnReadAccount = new JButton("Read Account");
		btnReadAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(checkID(txtID1.getText()))
					JOptionPane.showMessageDialog(frmBank, bank.getAccount(txtID1.getText()).toString(),"Account info",JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnReadAccount.setToolTipText("Displays info about Account with ID 1");
		
		JButton btnTransfer = new JButton("Transfer");
		btnTransfer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checkID(txtID1.getText()))
					if(checkID(txtID2.getText()))
						if(toDouble(txtAmount.getText())>0)
						{
							
							bank.transfer(txtID2.getText(), txtID1.getText(), toDouble(txtAmount.getText()));
							JOptionPane.showMessageDialog(frmBank, toDouble(txtAmount.getText())+"$ transfered","Transfer", JOptionPane.INFORMATION_MESSAGE);
						}
			}
		});
		btnTransfer.setToolTipText("Transfers specified amount from Account with ID 1 to Account with ID 2");
		
		JLabel lblAccountId = new JLabel("Account ID 1:");
		
		JLabel lblAccountId_1 = new JLabel("Account ID 2:");
		
		txtID2 = new JTextField();
		txtID2.setColumns(10);
		
		JLabel lblAmount = new JLabel("Amount:");
		
		txtAmount = new JTextField();
		txtAmount.setColumns(10);
		GroupLayout groupLayout = new GroupLayout(frmBank.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
									.addComponent(btnTransfer, GroupLayout.PREFERRED_SIZE, 215, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(lblAmount)
									.addGap(18)
									.addComponent(txtAmount))
								.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
									.addComponent(lblAccountId_1)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(txtID2))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblAccountId)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(txtID1, GroupLayout.PREFERRED_SIZE, 328, GroupLayout.PREFERRED_SIZE)))
							.addGap(178))
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(btnViewAccountLog, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnRemoveAccount, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnReadAccount, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnViewAccounts, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnAddAccount, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE))
							.addContainerGap())))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAccountId)
						.addComponent(txtID1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAccountId_1)
						.addComponent(txtID2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnViewAccounts)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnAddAccount)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnReadAccount)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnRemoveAccount)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnViewAccountLog)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnTransfer)
						.addComponent(txtAmount, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblAmount))
					.addGap(35))
		);
		frmBank.getContentPane().setLayout(groupLayout);
		
		JMenuBar menuBar = new JMenuBar();
		frmBank.setJMenuBar(menuBar);
		
		JMenu mnMenu = new JMenu("Menu");
		mnMenu.setMnemonic('n');
		menuBar.add(mnMenu);
		
		JMenuItem mntmSave = new JMenuItem("Save");
		mntmSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					BufferedWriter out = new BufferedWriter(new FileWriter("Accounts.txt"));
					out.write(bank.toString());
					out.close();
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, "ERROR", "ERROR", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
			}
		});
		mntmSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		mnMenu.add(mntmSave);
	}
}
