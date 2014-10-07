package catalog;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import java.awt.CardLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LoginPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3302881326493085109L;
	private JTextField userTxt;
	private JPasswordField passwordField;
	private Catalog catalog;
	private String ownerId;

	/**
	 * Create the panel.
	 */
	public LoginPanel(final JPanel content,final int level) {
		catalog=Catalog.getInstance();
		ownerId="";
		JLabel lblUsername = new JLabel("Username:");
		
		userTxt = new JTextField();
		userTxt.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password:");
		
		passwordField = new JPasswordField();
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode()==10){
				String p=new String(passwordField.getPassword());
				String usr=userTxt.getText();
				if(catalog.login(usr, p, level)){
				CardLayout cl = (CardLayout)(content.getLayout());
			    ownerId=catalog.getOwnerId(usr, p);
			    catalog.forceNotify(); 
			    cl.show(content, "Menu");
				}
				else
					{JOptionPane.showMessageDialog(content, "Invalid Login Credentials!", "Invalid Login", JOptionPane.WARNING_MESSAGE);
					passwordField.setText("");
					}
				}
			}
		});
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String p=new String(passwordField.getPassword());
				String usr=userTxt.getText();
				if(catalog.login(usr, p, level)){
				CardLayout cl = (CardLayout)(content.getLayout());
			    ownerId=catalog.getOwnerId(usr, p);
			    catalog.forceNotify();
			    cl.show(content, "Menu");
				}
				else
					{JOptionPane.showMessageDialog(content, "Invalid Login Credentials!", "Invalid Login", JOptionPane.WARNING_MESSAGE);
					passwordField.setText("");
					}
			}
		});
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(111)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnLogin, GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblPassword, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
								.addComponent(lblUsername, GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(passwordField, GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
								.addComponent(userTxt, GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE))))
					.addGap(183))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(110)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(userTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblUsername, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(7)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPassword, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnLogin)
					.addGap(114))
		);
		setLayout(groupLayout);

	}
	
	String getOwnerId(){
		return ownerId;
	}
}
