package catalog;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
public class ProfessorWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5348342645686909947L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProfessorWindow frame = new ProfessorWindow();
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
	public ProfessorWindow() {
		setTitle("Professor Application");
		setIconImage(Toolkit.getDefaultToolkit().getImage(ProfessorWindow.class.getResource("/resource/png_open_book_by_lagrimadejarjayes-d5rnz2z.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 560, 315);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		//cl=(CardLayout) contentPane.getLayout();
		LoginPanel login=new LoginPanel(contentPane,1);
		ProfessorMenuPanel pr=new ProfessorMenuPanel(login);
		Catalog catalog=Catalog.getInstance();
		catalog.addObserver(pr);
		contentPane.add(login,"Login");
		contentPane.add(pr,"Menu");
		
		
		
	}

}
