package OrderManagement;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;





@SuppressWarnings("serial")
public class WarehouseFrame extends JFrame {
	
	private JTable table;
	private Warehouse warehouse;
	private JTextField textField;
	private JTextField textField_1;
	private JScrollPane scrollPane;
	private JTextField maxPr;
	private JTextField minPr;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WarehouseFrame frame = new WarehouseFrame();
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
	public WarehouseFrame() {
		setTitle("Warehouse");
		warehouse=new Warehouse();
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 68, 414, 183);
		getContentPane().add(scrollPane);
		final String[] header={"ProductID","Name","Producer","Price ($)","Stock"};
		//Object[][]fields=new Object[100][100];
		table = new JTable(warehouse.productTable(),header);
		table.setEnabled(false);
		table.setFillsViewportHeight(true);
		table.setColumnSelectionAllowed(true);
		table.setCellSelectionEnabled(true);
		
		scrollPane.setViewportView(table);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(10, 11, 67, 14);
		getContentPane().add(lblName);
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				String name=textField.getText();
				String producer=textField_1.getText();
				table= new JTable(warehouse.productTable(name,producer),header);
				scrollPane.setViewportView(table);
				repaint();
			}
		});
		textField.setBounds(87, 8, 86, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblProducer = new JLabel("Producer:");
		lblProducer.setBounds(10, 43, 67, 14);
		getContentPane().add(lblProducer);
		
		textField_1 = new JTextField();
		textField_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String name=textField.getText();
				String producer=textField_1.getText();
				table= new JTable(warehouse.productTable(name,producer),header);
				scrollPane.setViewportView(table);
				repaint();
			}
		});
		textField_1.setBounds(87, 39, 86, 20);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblMaxPrice = new JLabel("Max Price:");
		lblMaxPrice.setBounds(183, 11, 67, 14);
		getContentPane().add(lblMaxPrice);
		
		JLabel lblMinPrice = new JLabel("Min Price:");
		lblMinPrice.setBounds(183, 43, 67, 14);
		getContentPane().add(lblMinPrice);
		
		maxPr = new JTextField();
		maxPr.setBounds(258, 8, 86, 20);
		getContentPane().add(maxPr);
		maxPr.setColumns(10);
		
		minPr = new JTextField();
		minPr.setBounds(258, 36, 86, 20);
		getContentPane().add(minPr);
		minPr.setColumns(10);
		
		JButton btnNewButton = new JButton("Price \r\nFilter");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String name=textField.getText();
				String producer=textField_1.getText();
				double mi=toDouble(minPr.getText());
				double ma=toDouble(maxPr.getText());
				table= new JTable(warehouse.productTable(name,producer,ma,mi),header);
				scrollPane.setViewportView(table);
				repaint();
				
			}
		});
		btnNewButton.setText("<html><center>"+"Price"+"<br>"+"filter"+"</center></html>");
		btnNewButton.setMnemonic('f');
		btnNewButton.setBounds(355, 5, 79, 52);
		getContentPane().add(btnNewButton);
		
		this.setResizable(false);
	}
	
	public WarehouseFrame(Object[][] rows){
		setTitle("Warehouse Stats");
		warehouse=new Warehouse();
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 570, 300);
		getContentPane().setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 68, 540, 183);
		getContentPane().add(scrollPane);
		final String[] header={"Product","Producer","Stock","Total Value","Stock balance"};
		//Object[][]fields=new Object[100][100];
		table = new JTable(rows,header);
		table.setEnabled(false);
		table.setFillsViewportHeight(true);
		table.setColumnSelectionAllowed(true);
		table.setCellSelectionEnabled(true);
		
		scrollPane.setViewportView(table);
		
		setVisible(true);
	}
	
	
	public static double toDouble(String nb){
		char[] nr=nb.toCharArray();
		int i;
		double f;
		double result=0;
		boolean fraction=false;
		if(nb==null||nb.equals(""))
			return 0;
		for(i=0;i<nr.length &&!fraction;i++)
			if(Character.isDigit(nr[i]))
				{result*=10;
				result+=(nr[i]-'0');
				}
			else
				{fraction=true;
				if(nr[i]!='.')
					return 0.0;
				}
		for(f=10;i<nr.length;i++,f*=10)
		{
			if(Character.isDigit(nr[i]))
				result+=(double)( (nr[i]-'0')/f);
			else
				return 0;
		}
		return result;
	}
	
}
