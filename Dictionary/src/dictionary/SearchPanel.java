package dictionary;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JList;
import javax.swing.UIManager;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JLabel;



public class SearchPanel extends JPanel {

	private static final long serialVersionUID = 5916176605999495861L;
	private JTextField textField;
	private JTextField txtSearch;
	private Dictionary d;
	private JScrollPane scrollPane;
	private JList<String> list;
	
	public SearchPanel(Dictionary dictionary) {
		d=dictionary;
		txtSearch = new JTextField();
		txtSearch.setToolTipText("<html>Enter word to be searched<br>? replaces one character<br>* replaces a sequence of characters</html>");
		txtSearch.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s=txtSearch.getText();
				ArrayList<String> result;
				if(s.isEmpty()){
					list = new JList<String>(d.getWordArray());
					scrollPane.setViewportView(list);
					repaint();
				}
				if(d.preWord(s))
					{
					result=d.search(s);
					String[] r=result.toArray(new String[result.size()]);
					list=new JList<String>(r);
					scrollPane.setViewportView(list);
					repaint();
					}
				else
					JOptionPane.showMessageDialog(textField, "Please verify input","ERROR",JOptionPane.ERROR_MESSAGE);
				
			}
		});
		
		scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(null);
		scrollPane.setSize(100, 100);
		JButton btnViewSynonyms = new JButton("View synonyms");
		btnViewSynonyms.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(list.getSelectedIndex()>=0)
					{
					ArrayList<String> result=d.getSynonymsArray(list.getSelectedValue());
					list=new JList<String>(result.toArray(new String[result.size()]));
					scrollPane.setViewportView(list);
					repaint();
					}
				else
					JOptionPane.showMessageDialog(scrollPane, "Please select a word","ERROR",JOptionPane.ERROR_MESSAGE);
			}
		});
		
		JButton btnAddSynonym = new JButton("Add synonym");
		btnAddSynonym.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
		
		JLabel lblResults = new JLabel("Results:");
		
		JButton btnRemoveWord = new JButton("Remove word");
		btnRemoveWord.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(list.getSelectedIndex()>=0){
					d.remove(list.getSelectedValue());
					//list=new JList<String>(d.getWordArray());
					list=new JList<String>();
					scrollPane.setViewportView(list);
					repaint();
					}
					else
						JOptionPane.showMessageDialog(scrollPane, "Please select a word","ERROR",JOptionPane.ERROR_MESSAGE);
			}
		});
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(6)
							.addComponent(lblResults))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(txtSearch)
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(btnRemoveWord, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnSearch, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnAddSynonym, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnViewSynonyms, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
					.addGap(76))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(10)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtSearch, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSearch))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblResults)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE, false)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 228, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnAddSynonym)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnViewSynonyms)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnRemoveWord)))
					.addContainerGap())
		);
		
		list = new JList<String>();
		list.setBorder(null);
		list.setBackground(UIManager.getColor("Panel.background"));
		scrollPane.setViewportView(list);
		setLayout(groupLayout);
		
		textField = new JTextField();
		textField.setColumns(10);
		

		

	}
}
