package OrderManagement;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;

public class HomeWindow {

	private OPDept opd;
	private boolean openManager;
	
	private JFrame frmOrderManagement;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomeWindow window = new HomeWindow();
					window.frmOrderManagement.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public HomeWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmOrderManagement = new JFrame();
		frmOrderManagement.setTitle("Order Management");
		frmOrderManagement.setBounds(100, 100, 250, 250);
		frmOrderManagement.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmOrderManagement.getContentPane().setLayout(null);
		opd=new OPDept();
		openManager=false;
		
		JButton btnManagerLogIn = new JButton("Manager Log In");
		btnManagerLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!openManager)
					{new ManagerWindow(opd);}
				
			}
		});
		btnManagerLogIn.setBounds(10, 59, 214, 40);
		frmOrderManagement.getContentPane().add(btnManagerLogIn);
		
		JButton btnOrderProduct = new JButton("Order Product");
		btnOrderProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CustomerFrame f=new CustomerFrame();
				f.setVisible(true);
			}
		});
		btnOrderProduct.setBounds(10, 110, 214, 40);
		frmOrderManagement.getContentPane().add(btnOrderProduct);
		
		JButton btnWarehouse = new JButton("Warehouse");
		btnWarehouse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				WarehouseFrame fr=new WarehouseFrame();
				fr.setVisible(true);
			}
		});
		btnWarehouse.setBounds(10, 161, 214, 40);
		frmOrderManagement.getContentPane().add(btnWarehouse);
		
		JLabel lblPleaseSelectAction = DefaultComponentFactory.getInstance().createTitle("Please select action:");
		lblPleaseSelectAction.setBounds(10, 11, 214, 37);
		frmOrderManagement.getContentPane().add(lblPleaseSelectAction);
	}
}
