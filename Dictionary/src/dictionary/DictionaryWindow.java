package dictionary;

import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.UIManager.*;

import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;


public class DictionaryWindow {

	private JFrame frmDictionarDeSinonime;
	private Dictionary dictionary;
	private ControlPanel controlPan;
	private SearchPanel searchPan;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {
		    // If Nimbus is not available, you can set the GUI to another look and feel.
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DictionaryWindow window = new DictionaryWindow();
					window.frmDictionarDeSinonime.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public DictionaryWindow() {
		dictionary=new Dictionary();
		dictionary.populate();
		controlPan=new ControlPanel(dictionary);
		searchPan=new SearchPanel(dictionary);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmDictionarDeSinonime = new JFrame();
		frmDictionarDeSinonime.setIconImage(Toolkit.getDefaultToolkit().getImage(DictionaryWindow.class.getResource("/resource/books-icon.png")));
		frmDictionarDeSinonime.setTitle("Romanian synonyms dictionary");
		frmDictionarDeSinonime.setBounds(100, 100, 451, 398);
		frmDictionarDeSinonime.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmDictionarDeSinonime.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 434, 339);
		tabbedPane.add("Browse",controlPan);
		tabbedPane.add("Index",searchPan);
		//frame.setContentPane(tabbedPane);
		frmDictionarDeSinonime.getContentPane().add(tabbedPane);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setToolTipText("");
		frmDictionarDeSinonime.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		mnFile.setMnemonic('f');
		mnFile.setToolTipText("Alt+f");
		menuBar.add(mnFile);
		
		JMenuItem mntmSave = new JMenuItem("Save");
		mntmSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dictionary.save();
			}
		});
		mntmSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		mnFile.add(mntmSave);
		
		JMenuItem mntmPopulate = new JMenuItem("Restore");
		mntmPopulate.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_MASK));
		mntmPopulate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dictionary.populate();
				controlPan.refresh();
			}
		});
		mnFile.add(mntmPopulate);
		
		JMenuItem mntmAddWordsFrom = new JMenuItem("Add words from file");
		mntmAddWordsFrom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String absolutePath=JOptionPane.showInputDialog(frmDictionarDeSinonime, "File path :");
				if(absolutePath!=null)
					dictionary.addFromFile(absolutePath);
					controlPan.refresh();
			}
		});
		
		JMenuItem mntmClear = new JMenuItem("Clear");
		mntmClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dictionary.clear();
				controlPan.refresh();
			}
		});
		mnFile.add(mntmClear);
		mnFile.add(mntmAddWordsFrom);
		
		JMenuItem mntmExportDictionaryTo = new JMenuItem("Export dictionary to file");
		mntmExportDictionaryTo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String path=JOptionPane.showInputDialog("File path and name :");
				try {
					BufferedWriter out=new BufferedWriter(new FileWriter(path));
					out.write(dictionary.toString());
					out.close();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Error exporting dictionary", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		mnFile.add(mntmExportDictionaryTo);
		mnFile.addSeparator();
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		mntmExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.ALT_MASK));
		mnFile.add(mntmExit);
	}
}
