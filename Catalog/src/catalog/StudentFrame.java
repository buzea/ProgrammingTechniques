package catalog;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.CardLayout;
import java.awt.Toolkit;


public class StudentFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -960425864125388510L;
	private JPanel contentPane;
	private Catalog catalog;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentFrame frame = new StudentFrame();
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
	public StudentFrame() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(StudentFrame.class.getResource("/resource/png_open_book_by_lagrimadejarjayes-d5rnz2z.png")));
		setTitle("Student Application");
		catalog=Catalog.getInstance();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 360);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		LoginPanel login=new LoginPanel(contentPane,3);
		StudentPanel studPanel=new StudentPanel(login);
		catalog.addObserver(studPanel);
		contentPane.add(login,"Login");
		contentPane.add(studPanel,"Menu");
	}

	

}
