package catalog;


import java.awt.CardLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;

public class AdminFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -726568624601804825L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminFrame frame = new AdminFrame();
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
	public AdminFrame() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(AdminFrame.class.getResource("/resource/png_open_book_by_lagrimadejarjayes-d5rnz2z.png")));
		setTitle("Admin Application");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new CardLayout());
		LoginPanel lo=new LoginPanel(contentPane,0);
		AdminPanel admin=new AdminPanel();
		contentPane.add(lo,"Login");
		contentPane.add(admin,"Menu");
		setContentPane(contentPane);
	}

}
