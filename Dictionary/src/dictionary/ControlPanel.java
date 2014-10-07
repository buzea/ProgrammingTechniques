package dictionary;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JList;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Color;


public class ControlPanel extends JPanel {

	private static final long serialVersionUID = -2770860132051885321L;
	private JList<String> list;
	private Dictionary d;
	private JScrollPane scrollPane;
	private JTextField txtWord;
	private JTextField txtSyn;
	private JPanel addPanel;

	/**
	 * Create the panel.
	 */
	public ControlPanel(Dictionary dictionary) {
		
		d=dictionary;
		scrollPane = new JScrollPane();
		
		JButton btnNewButton = new JButton("Remove selected word");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(list.getSelectedIndex()>=0){
				d.remove(list.getSelectedValue());
				list=new JList<String>(d.getWordArray());
				scrollPane.setViewportView(list);
				repaint();
				}
				else
					JOptionPane.showMessageDialog(scrollPane, "Please select a word","ERROR",JOptionPane.ERROR_MESSAGE);
			}
		});
		
		JButton btnRemoveWord = new JButton("Show synonyms");
		btnRemoveWord.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(list.getSelectedIndex()>=0)
					JOptionPane.showMessageDialog(scrollPane, d.getSynonymsArray(list.getSelectedValue()).toString(), "Synonyms", JOptionPane.INFORMATION_MESSAGE);
				else
					JOptionPane.showMessageDialog(scrollPane, "Please select a word","ERROR",JOptionPane.ERROR_MESSAGE);
			}
		});
		
		JButton btnAddWord = new JButton("Add new synonym");
		btnAddWord.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(list.getSelectedValue()==null)
					JOptionPane.showMessageDialog(scrollPane, "Please select a word","ERROR",JOptionPane.ERROR_MESSAGE);
				else{
				
				String synonym=JOptionPane.showInputDialog(scrollPane, "Give the the synonym");
				if(synonym!=null)
				if(synonym.equals(""))
					JOptionPane.showMessageDialog(scrollPane, "Please insert a synonym","ERROR",JOptionPane.ERROR_MESSAGE);
				else
					if(!list.getSelectedValue().equals(synonym))
						d.add(list.getSelectedValue(), synonym);
				
				}
			}
		});
		
		JButton btnAddNewWord = new JButton("Add new word");
		btnAddNewWord.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtWord.setText("");
				txtSyn.setText("");
				addPanel.setVisible(true);
				repaint();
			}
		});
		
		addPanel = new JPanel();
		addPanel.setVisible(false);
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refresh();
			}
		});
		btnRefresh.setBackground(Color.WHITE);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 194, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(btnAddNewWord, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnAddWord, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnRemoveWord, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnNewButton, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnRefresh, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addComponent(addPanel, GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 278, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnNewButton)
							.addGap(8)
							.addComponent(btnRemoveWord)
							.addGap(7)
							.addComponent(btnAddWord)
							.addGap(5)
							.addComponent(btnRefresh)
							.addGap(5)
							.addComponent(btnAddNewWord)
							.addPreferredGap(ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
							.addComponent(addPanel, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		JLabel lblWord = new JLabel("Word:");
		
		txtWord = new JTextField();
		txtWord.setColumns(10);
		
		JLabel lblSynonym = new JLabel("Synonym:");
		
		txtSyn = new JTextField();
		txtSyn.setColumns(10);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String w,s;
				w=txtWord.getText();
				s=txtSyn.getText();
				if(w==null || s==null)
					JOptionPane.showMessageDialog(scrollPane, "Please verify input","ERROR",JOptionPane.ERROR_MESSAGE);
				else
					if(w.equals("")||s.equals(""))
						JOptionPane.showMessageDialog(scrollPane, "Please verify input","ERROR",JOptionPane.ERROR_MESSAGE);
					else
						if( (!d.hasOnlyLetters(w)) || (!d.hasOnlyLetters(s)))
							JOptionPane.showMessageDialog(scrollPane, "Please verify input","ERROR",JOptionPane.ERROR_MESSAGE);
						else
							if(w.equals(s))
								JOptionPane.showMessageDialog(scrollPane, "Please verify input","ERROR",JOptionPane.ERROR_MESSAGE);
							else
								d.add(w, s);
				addPanel.setVisible(false);
				list=new JList<String>(d.getWordArray());
				scrollPane.setViewportView(list);
			}
		});
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addPanel.setVisible(false);
			}
		});
		GroupLayout gl_addPanel = new GroupLayout(addPanel);
		gl_addPanel.setHorizontalGroup(
			gl_addPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_addPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_addPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_addPanel.createSequentialGroup()
							.addComponent(btnAdd, GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(btnCancel))
						.addGroup(gl_addPanel.createSequentialGroup()
							.addGroup(gl_addPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblSynonym)
								.addComponent(lblWord))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_addPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(txtWord, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtSyn, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap())
		);
		gl_addPanel.setVerticalGroup(
			gl_addPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_addPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_addPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblWord)
						.addComponent(txtWord, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_addPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSynonym)
						.addComponent(txtSyn, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_addPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAdd)
						.addComponent(btnCancel))
					.addContainerGap(37, Short.MAX_VALUE))
		);
		addPanel.setLayout(gl_addPanel);
		
		list = new JList<String>(d.getWordArray());
		//list=new JList<String>();
		scrollPane.setViewportView(list);
		setLayout(groupLayout);

	}
	
	public void refresh(){
		list=new JList<String>(d.getWordArray());
		scrollPane.setViewportView(list);
		scrollPane.repaint();
		this.repaint();
	}
}
