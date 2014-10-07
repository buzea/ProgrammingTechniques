package Queues;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JTextPane;
import javax.swing.UIManager;

public class GUI extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField minArr;
	private JTextField maxArr;
	private JTextField minServ;
	private JTextField maxServ;
	private JTextField start;
	private JTextField end;
	private JTextField queuesN;
	private JButton btnSubmit;
	private Simulation model;
	private JTextPane txtpnMaximumWaitingTime;
	private JTextPane txtpnMinimumWaitingTime;
	private JButton btnLog;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JOptionPane.showMessageDialog(null, "Please take into consideration the follwing rules for a successful simulation: \r\n"
							+ "-Max processing time must be < than Max waiting time \r\n"
							+ "-Min processing time must be < than Min waiting time \r\n"
							+ "-All inputs are expressed in minutes, so they must be pre-converted (i.e. 1h 20 min = 80) \r\n"
							+ "-Simulation Start < Simulation End  \r\n"
							+ "-Min Processing time < Max Processing Time \r\n"
							+ "-Min arrival time < Max Arrival Time \r\n"
							+ "-Only digits are accepted as input!\r\n"
							+ "-Generation of Logs is proportional with Simulation Time(100 minutes of simulation=1 second to generate) \r\n"
							+ "-When you press the \"Logs\" button, they are both opened and overlapping! \r\n "
							+ "-The \"Logs\" button is just a link to the logs and will not work unless they have the predefined path. In this case you may oppen them manualy "
							+ "in the project folder \r\n"
							+ "-Simulation time must contain the interval [120,180], which is the sample interval! ", "Input Details ", JOptionPane.INFORMATION_MESSAGE);
					GUI frame = new GUI();
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
	public GUI() {
		setTitle("Queue Simulation");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 452, 326);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Min time between customers");
		lblNewLabel.setBounds(10, 11, 179, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblMinArrivalTime = new JLabel("Max time between customers");
		lblMinArrivalTime.setBounds(10, 36, 179, 14);
		getContentPane().add(lblMinArrivalTime);
		
		JLabel lblMinServiceTime = new JLabel("Min service time");
		lblMinServiceTime.setBounds(10, 61, 179, 14);
		getContentPane().add(lblMinServiceTime);
		
		JLabel lblMaxServiceTime = new JLabel("Max service time");
		lblMaxServiceTime.setBounds(10, 86, 179, 14);
		getContentPane().add(lblMaxServiceTime);
		
		JLabel lblSimulationStartTime = new JLabel("Simulation Start time");
		lblSimulationStartTime.setBounds(10, 111, 179, 14);
		getContentPane().add(lblSimulationStartTime);
		
		JLabel lblSimulationEndTime = new JLabel("Simulation End time");
		lblSimulationEndTime.setBounds(10, 136, 179, 14);
		getContentPane().add(lblSimulationEndTime);
		
		JLabel lblNumberOfQueues = new JLabel("Number of queues");
		lblNumberOfQueues.setBounds(10, 161, 179, 14);
		getContentPane().add(lblNumberOfQueues);
		
		minArr = new JTextField();
		minArr.setBounds(199, 8, 86, 20);
		getContentPane().add(minArr);
		minArr.setColumns(10);
		
		maxArr = new JTextField();
		maxArr.setBounds(199, 33, 86, 20);
		getContentPane().add(maxArr);
		maxArr.setColumns(10);
		
		minServ = new JTextField();
		minServ.setBounds(199, 58, 86, 20);
		getContentPane().add(minServ);
		minServ.setColumns(10);
		
		maxServ = new JTextField();
		maxServ.setBounds(199, 83, 86, 20);
		getContentPane().add(maxServ);
		maxServ.setColumns(10);
		
		start = new JTextField();
		start.setBounds(199, 108, 86, 20);
		getContentPane().add(start);
		start.setColumns(10);
		
		end = new JTextField();
		end.setBounds(199, 133, 86, 20);
		getContentPane().add(end);
		end.setColumns(10);
		
		queuesN = new JTextField();
		queuesN.setBounds(199, 158, 86, 20);
		getContentPane().add(queuesN);
		queuesN.setColumns(10);
		
		btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int minC,maxC,minS,maxS,st,en,nb;
				try{
					minC=this.toInt(minArr.getText());
					maxC=this.toInt(maxArr.getText());
					minS=this.toInt(minServ.getText());
					maxS=this.toInt(maxServ.getText());
					st=this.toInt(start.getText());
					en=this.toInt(end.getText());
					nb=this.toInt(queuesN.getText());
					
					model=new Simulation(minC,maxC,nb,st,en,minS,maxS);
					model.start();
					JOptionPane.showMessageDialog(null, "Simulation started. Please check event log file", "Input message ", JOptionPane.INFORMATION_MESSAGE);
					
				}catch(Exception e1){
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Input message ", JOptionPane.INFORMATION_MESSAGE);
				}
			}
			private int toInt(String s) throws Exception{
				int nb=0,i;
				char[] c=s.toCharArray();
				for(i=0;i<c.length;i++)
					if(Character.isDigit(c[i]))
					{
						nb*=10;
						nb+= (c[i]-'0');
					}
					else
						throw new Exception("Bad Input. All fields must be integer!");
				
				return nb;
			}

			
		});
		btnSubmit.setBounds(199, 212, 89, 23);
		getContentPane().add(btnSubmit);
		
		JTextPane txtpnSasa = new JTextPane();
		txtpnSasa.setBackground(UIManager.getColor("Panel.background"));
		txtpnSasa.setEditable(false);
		txtpnSasa.setText(" All time inputs are expresed in MINUTES! ");
		txtpnSasa.setBounds(295, 11, 129, 64);
		getContentPane().add(txtpnSasa);
		
		txtpnMaximumWaitingTime = new JTextPane();
		txtpnMaximumWaitingTime.setEditable(false);
		txtpnMaximumWaitingTime.setBackground(UIManager.getColor("Panel.background"));
		txtpnMaximumWaitingTime.setText("Maximum waiting time: "+ Simulation.MAX_WAITING_TIME+ "." );
		txtpnMaximumWaitingTime.setBounds(295, 86, 129, 39);
		getContentPane().add(txtpnMaximumWaitingTime);
		
		txtpnMinimumWaitingTime = new JTextPane();
		txtpnMinimumWaitingTime.setBackground(UIManager.getColor("Panel.background"));
		txtpnMinimumWaitingTime.setEditable(false);
		txtpnMinimumWaitingTime.setText("Minimum waiting time: "+Simulation.MIN_WAITING_TIME);
		txtpnMinimumWaitingTime.setBounds(295, 136, 129, 39);
		getContentPane().add(txtpnMinimumWaitingTime);
		
		btnLog = new JButton("Logs");
		btnLog.addActionListener(new ActionListener() {
			@SuppressWarnings("unused")
			public void actionPerformed(ActionEvent e) {
				Runtime rt=Runtime.getRuntime();

				
				

				try {
					String file="C:\\Users\\Vlad\\Desktop\\An2 Sem 2\\PT\\Queues\\LOG.txt";
					String file2="C:\\Users\\Vlad\\Desktop\\An2 Sem 2\\PT\\Queues\\Queue Evolution.txt";
					Process p=rt.exec("notepad "+file);
					Process p1=rt.exec("notepad "+file2);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null,"Error opening file. Please try manual opening." , "File message ", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnLog.setBounds(298, 212, 89, 23);
		getContentPane().add(btnLog);
		
	}
}
