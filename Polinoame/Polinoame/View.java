package Polinoame;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import java.awt.Choice;

import javax.swing.JSeparator;
import javax.swing.JToggleButton;
import javax.swing.JTextPane;
import javax.swing.JTextArea;

import java.awt.Color;

import javax.swing.UIManager;

import java.awt.FlowLayout;

@SuppressWarnings({ "unused", "serial" })
public class View extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_x;
	private JButton btnLoadP;
	private JButton btnLoadP_1;
	private JButton adition;
	private JButton subtraction ;
	private JButton value;
	private JButton root;
	private JButton derivation;
	private JButton multiplication;
	private JButton equal;
	private JLabel lblResult;
	private JLabel lblNewLabel;
	private JPanel panel;
	private static Polynomial p1,p2,rez;
	

	/**
	 * Launch the application.
	 */
	
	  public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					View frame = new View();
					frame.setResizable(false);
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
	public View() {
		p1=new Polynomial();
		p2=new Polynomial();
		rez=new Polynomial();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 343, 304);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setTitle("Polynomial processing");
		
		JLabel lblP = new JLabel("P1:");
		lblP.setBounds(10, 11, 46, 14);
		contentPane.add(lblP);
		
		JLabel lblP_1 = new JLabel("P2:");
		lblP_1.setBounds(10, 36, 46, 14);
		contentPane.add(lblP_1);
		
		textField = new JTextField();
		textField.setToolTipText("Insert P(X). All coefficients must start with + !!!");
		textField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				panel.setVisible(true);
			}
			public void mouseExited(MouseEvent e)
			{
				panel.setVisible(false);
			}
		});
		textField.setBounds(50, 8, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(50, 33, 86, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		lblResult = new JLabel("Result:");
		lblResult.setBounds(10, 86, 46, 14);
		contentPane.add(lblResult);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(60, 86, 123, 14);
		contentPane.add(lblNewLabel);
		
		btnLoadP = new JButton("Load P1");
		btnLoadP.setToolTipText("Stores P1");
		btnLoadP.setMnemonic('1');
		btnLoadP.setBounds(224, 7, 89, 23);
		contentPane.add(btnLoadP);
		btnLoadP.addActionListener(this);
		
		btnLoadP_1 = new JButton("Load P2");
		btnLoadP_1.setToolTipText("Stores P2");
		btnLoadP_1.setMnemonic('2');
		btnLoadP_1.setBounds(224, 32, 89, 23);
		contentPane.add(btnLoadP_1);
		btnLoadP_1.addActionListener(this);
		
		adition = new JButton("P1+P2");
		adition.setToolTipText("Adds the Polynomials");
		adition.setBounds(224, 57, 89, 23);
		contentPane.add(adition);
		adition.addActionListener(this);
		
		subtraction = new JButton("P1-P2");
		subtraction.setBounds(224, 82, 89, 23);
		subtraction.addActionListener(this);
		contentPane.add(subtraction);
		
		value = new JButton("P1(x)");
		value.setBounds(224, 106, 89, 23);
		contentPane.add(value);
		value.addActionListener(this);
		
		JLabel lblX = new JLabel("x:");
		lblX.setBounds(10, 61, 46, 14);
		contentPane.add(lblX);
		
		textField_x = new JTextField();
		textField_x.setBounds(50, 58, 86, 20);
		contentPane.add(textField_x);
		textField_x.setColumns(10);
		
		root = new JButton("Roots P1");
		root.addActionListener(this);
		root.setBounds(224, 130, 89, 23);
		contentPane.add(root);
		root.setToolTipText("SLOW computation! Use only for integer roots.");
		root.addActionListener(this);
		
		derivation = new JButton("P1'");
		derivation.setBounds(224, 154, 89, 23);
		contentPane.add(derivation);
		derivation.addActionListener(this);
		
		multiplication = new JButton("P1*P2");
		multiplication.setBounds(224, 178, 89, 23);
		contentPane.add(multiplication);
		multiplication.addActionListener(this);
		
		equal = new JButton("P1=P2?");
		equal.setBounds(224, 202, 89, 23);
		contentPane.add(equal);
		equal.addActionListener(this);
		
		panel = new JPanel();
		panel.setVisible(false);
		panel.setBounds(20, 133, 189, 92);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JTextArea txtrSampleText = new JTextArea();
		txtrSampleText.setBounds(10, 36, 132, 57);
		txtrSampleText.setLineWrap(true);
		txtrSampleText.setWrapStyleWord(true);
		txtrSampleText.setBackground(UIManager.getColor("Panel.background"));
		txtrSampleText.setText("Example:  5X^4+(-3X^2)+X+2");
		panel.add(txtrSampleText);
		
		JLabel maxDeagree = new JLabel("Max Degree="+Polynomial.MAX_DEGREE);
		maxDeagree.setBounds(10, 11, 103, 14);
		panel.add(maxDeagree);
		
		
		
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()== btnLoadP) // load P1
		{
			if(Polynomial.checkInputString(textField.getText()))
				{
				try{p1=new Polynomial(textField.getText());
				lblNewLabel.setText(""+ p1.toString());}
				catch(Exception e1){lblNewLabel.setText("Bad input for P1(X)"); }
				}
			else
				lblNewLabel.setText("Bad input for P1(X)");
				this.repaint();
		}
		
		if(e.getSource()== btnLoadP_1) // load P2
		{
			if(Polynomial.checkInputString(textField_1.getText()))
				{
				try{p2=new Polynomial(textField_1.getText());
				lblNewLabel.setText(""+ p2.toString());}
				catch(ArrayIndexOutOfBoundsException e1){lblNewLabel.setText("Bad input for P2(X)"); }
				}
			else
				lblNewLabel.setText("Bad input for P2(X)");
				this.repaint();
		}
		
		if(e.getSource()== adition)
		{
			rez=p1.plus(p2);
			lblNewLabel.setText(rez.toString());
			this.repaint();
		}
		
		if(e.getSource()== subtraction)
		{
			rez=p1.minus(p2);
			lblNewLabel.setText(rez.toString());
			this.repaint();
		}
		if(e.getSource()== value)
		{
			int x;
			double r;
			String inp=new String();
			inp=textField_x.getText();
			try{x=Polynomial.convertX(inp);
			
			r=p1.valueAt(x);
			lblNewLabel.setText(""+r);
			this.repaint();
			}
			catch(Exception e1){
				lblNewLabel.setText(e1.getMessage());
			}
		}
		if(e.getSource()== root)
		{
			int[] r=p1.roots();
			lblNewLabel.setText(p1.showRoots(r));
			this.repaint();
		}
		
		if(e.getSource()== derivation)
		{
			rez=p1.derive();
			lblNewLabel.setText(rez.toString());
			this.repaint();
		}
		
		if(e.getSource()== multiplication)
		{
			rez=p1.times(p2);
			lblNewLabel.setText(rez.toString());
			this.repaint();
		}
		
		if(e.getSource()== equal)
		{
			boolean r=p1.equals(p2);
			lblNewLabel.setText(""+r);
			this.repaint();
		}
		
	}
}
