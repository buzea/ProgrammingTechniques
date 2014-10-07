package OrderManagement;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class AddProductFrame extends JFrame {

	private JPanel contentPane;
	private JTextField producerT;
	private JTextField priceT;
	private JTextField nameT;
	private JTextField qT;
	private Warehouse warehouse;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddProductFrame frame = new AddProductFrame();
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
	public AddProductFrame() {
		
		warehouse=new Warehouse();
		setTitle("Add Product");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 291, 235);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblProduct = new JLabel("Product:");
		lblProduct.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblProduct.setBounds(10, 11, 93, 14);
		contentPane.add(lblProduct);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(10, 36, 93, 14);
		contentPane.add(lblName);
		
		JLabel lblProducer = new JLabel("Producer:");
		lblProducer.setBounds(10, 61, 93, 14);
		contentPane.add(lblProducer);
		
		JLabel lblPrice = new JLabel("Price:");
		lblPrice.setBounds(10, 86, 93, 14);
		contentPane.add(lblPrice);
		
		producerT = new JTextField();
		producerT.setBounds(113, 58, 86, 20);
		contentPane.add(producerT);
		producerT.setColumns(10);
		
		priceT = new JTextField();
		priceT.setBounds(113, 83, 86, 20);
		contentPane.add(priceT);
		priceT.setColumns(10);
		
		nameT = new JTextField();
		nameT.setBounds(113, 33, 86, 20);
		contentPane.add(nameT);
		nameT.setColumns(10);
		
		JLabel lblQuantity = new JLabel("Quantity:");
		lblQuantity.setBounds(10, 111, 93, 14);
		contentPane.add(lblQuantity);
		
		qT = new JTextField();
		qT.setBounds(113, 108, 86, 20);
		contentPane.add(qT);
		qT.setColumns(10);
		
		JButton btnAddToWarehouse = new JButton("Add to Warehouse");
		btnAddToWarehouse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double price=WarehouseFrame.toDouble(priceT.getText());
				Product p=new Product(nameT.getText(),producerT.getText(),price);
				int q=(int)WarehouseFrame.toDouble(qT.getText());
				System.out.println(p+" "+q+"");
				warehouse.addProduct(p, q);
				//JOptionPane.showMessageDialog(contentPane, "Product added to warehouse");
				
			}
		});
		btnAddToWarehouse.setBounds(46, 139, 153, 47);
		contentPane.add(btnAddToWarehouse);
	}
}
