package Queues;

import java.io.*;
import java.util.*;

public class Simulation extends Thread {
	//minimum time between customer arrival, maximum time between customer arrival, maximum number of queues
	@SuppressWarnings("unused")
	private int minArrival,maxArrival,queuesNb,simulationStart,simulationEnd,openServers,minServiceTime,maxServiceTime;
	private Server[] servers;
	private CustomerGenerator cust;
	private Client transfer;
	public static final int MAX_WAITING_TIME=40,MIN_WAITING_TIME=2;
	private BufferedWriter output,serverLog;
	private int currentTime;
	private String pathLog,pathQ;
	private int[] hours;
	
	/**
	 * Builds simulation of a queues that process clients
	 * @param minArrival - minimum time between arrival of two customers
	 * @param maxArrival - maximum time between arrival of two customers
	 * @param queuesNb - maximum number of open Servers to process clients
	 * @param simulationStart - the time from which the simulation starts, expressed in minutes
	 * @param simulationEnd - the time at which the simulation end, expressed in minutes
	 * @param minServiceTime - the minimum amount of time that a customer may require processing
	 * @param maxServiceTime - the maximum amount of time that a customer may require processing
	 */
	public Simulation(int minArrival,int maxArrival,int queuesNb,int simulationStart,int simulationEnd,int minServiceTime,int maxServiceTime){
		this.minArrival=minArrival;
		this.maxArrival=maxArrival;
		this.queuesNb=queuesNb;
		this.simulationStart=simulationStart;
		this.simulationEnd=simulationEnd;
		this.openServers=1;
		servers=new Server[queuesNb];
		for(int i=0;i<queuesNb;i++)
			{servers[i]=new Server(simulationStart,simulationEnd,i);
			servers[i].start();
			}
		servers[0].openServer();
		this.minServiceTime=minServiceTime;
		this.maxServiceTime=maxServiceTime;
		cust=new CustomerGenerator( minArrival, maxArrival, minServiceTime, maxServiceTime, simulationStart,simulationEnd);
		hours=new int[25];
		
		
		try {
			File file = new File("Queue Evolution.txt");
			pathQ=file.getAbsolutePath();
			output = new BufferedWriter(new FileWriter(file));
			file = new File("LOG.txt");
			pathLog=file.getAbsolutePath();
			serverLog = new BufferedWriter(new FileWriter(file));
			//System.out.println(pathQ+"\r\n"+pathLog);
		} catch (IOException e) {
			System.err.println("FILE ERROR!!!");
			System.exit(1);
		}
	}
	/**Gets the path of the Queue Evolution file
	 * @return the pathQ in String form
	 */
	public String getPathQ() {
		return pathQ;
	}
	/**Gets the absolute path for the event Log file
	 * @return the pathLog in String form
	 */
	public String getPathLog() {
		return pathLog;
	}
	
	/**
	 * Computes the id of the open queue that has minimum waiting time
	 * @return id of queue with minuimum waiting time
	 */
	public synchronized int getMinQueNumber()
	{
		int min,i,nr=0;
		i=0;
		while((i<queuesNb)&& !servers[i].isOpen())
			{i++;}//find first open server
		if(i<queuesNb){
		min=servers[i].getMaxTime();
		for( ;i<queuesNb;i++)
			if(servers[i].isOpen())
			if(min>servers[i].getMaxTime())
				{min=servers[i].getMaxTime();
				nr=i;
				}
		return nr;
		}
		return -1;
	}
	/**
	 * Finds the id of the queue with maximum waiting time
	 * @return id of the queue with maximum waiting time
	 */
	public synchronized int getMaxQueNumber()
	{
		int max,i,nr=0;
		max=servers[0].getMaxTime();// doesn't matter if it's open or not, because at least one queue is open, an thus will have a greater queue time
		for(i=1;i<queuesNb;i++)
			if(servers[i].isOpen())
			if(max<servers[i].getMaxTime())
			{
				max=servers[i].getMaxTime();
				nr=i;
			}
		return nr;
	}
	
	/**
	 * Opens servers if the maximum waiting time is exceeded by any of the queues. Closes the servers that do not meet the minimum waiting time
	 * When a server is opened, all clients in queues are rearranged, in the order that they arrived.
	 */
	public synchronized void arrangeServers()
	{
		int i,minQ,maxQ;
		boolean ready=false;
		LinkedList<Client> r=new LinkedList<Client>();
		Client temp=null;
		
		while(!ready){
			
		ready=true;
		
		maxQ=this.getMaxQueNumber();
		if(servers[maxQ].getMaxTime()>MAX_WAITING_TIME)
			if(openServers<queuesNb)
				{
				//put all clients in a list
					for(i=0;i<queuesNb;i++)
						if(servers[i].isOpen())
						{	
							
							
							while(servers[i].getNbClients()>1)
							{
								temp=servers[i].extractLastClient();
								servers[i].setLog(servers[i].getLog()+ (Server.toTime(currentTime)+": Server "+i+ " Client "+temp.getID()+" left queue \r\n"));
								r.add(temp);
	
							}
						}
				//sort list with clients
					Collections.sort(r,new Comparator<Client>(){
	                     public int compare(Client s1,Client s2){
	                           return s1.getArrivalTime()-s2.getArrivalTime();
	                     }});
				//open new server
				i=0;
				while( (i<queuesNb) &&(servers[i].isOpen() ) )
				{
					i++;
				}
				
				if(i<queuesNb){
					
					servers[i].openServer();
					openServers++;
					//System.out.println("Opened Server "+i);
					try{
					output.write(Server.toTime(currentTime)+": Opened Server "+i);
					output.newLine();
					}catch(IOException e2){
						System.err.println("File Error");
					}
					temp=r.pollFirst();
					while(temp!=null)
							{
									minQ=this.getMinQueNumber();
									servers[minQ].addClient(temp);
									temp=r.pollFirst();
							}
				
				
				ready=false;
				}
				}
			
		minQ=this.getMinQueNumber();
		if(ready)
		if(servers[minQ].getMaxTime()<MIN_WAITING_TIME)
			if(openServers>1)
		{
				//we do not move any of the clients,
				// just don't allow any new ones,
				//by setting the open attribute to false
				try {
					servers[minQ].closeServer();openServers--;
					output.write(Server.toTime(currentTime)+": Closed Server "+minQ);
					output.newLine();
				} catch (IOException e) {
					System.err.println("FILE ERROR!!!");
				}
				//System.out.println("Closed Server "+minQ);
				
				
				ready=false;
		}
		 
		
		}
	}
	/**
	 *  Finds the value of the hour that had the most clients. The method returns a message with the peak hour
	 * @return a String containing a printable message that says the peak hour 
	 */
	public String peakHourMesage()
	{
		String s="The peak hour of the simulation was";
		int i,max=0;
		for(i=1;i<24;i++)
		{
			if(hours[i]>=hours[max])
				max=i;
		}
		
		s+=" from "+max+" to "+(max+1)+"\r\n";
		return s;
	}
	
	/**
	 * 
	 */
	public void run()
	{
		int minQ,h;
		cust.start();
		for(currentTime=simulationStart;currentTime<=simulationEnd;currentTime++)
		{
			if(cust.newClient!=null)
			{
				this.arrangeServers();
				transfer=cust.newClient.copy();
				h=transfer.getArrivalTime()/60;
				hours[h%24]++;
				minQ=this.getMinQueNumber();
				servers[minQ].addClient(transfer);
				cust.newClient=null;
			}
			try {
				sleep(1);
			} catch (InterruptedException e) {}
			
		}
		
		for(int i=0;i<queuesNb;i++)
		{
			try {
				serverLog.write(servers[i].getLog());
				serverLog.newLine();
				serverLog.newLine();
			} catch (IOException e) {
				System.err.println("LOG FILE ERROR!");
			}
		}
		 try {
			 serverLog.write("Average waiting time for hole simulation: "+ Server.getAverageWaitingTime()+" minutes");
			 serverLog.newLine();
			 serverLog.write("Average service time for hole simulation: "+ Server.getAverageServiceTime()+" minutes");
			 serverLog.newLine();
			 serverLog.newLine();
			 
			 for(int i=0;i<3;i++)
			 {
				 serverLog.write("Total empty queue time for server "+i+" = "+Server.toTime(servers[i].getIdleTime()));
				 serverLog.newLine(); 
			 }
			 serverLog.newLine(); 
			 serverLog.write(Server.intervalData());
			 serverLog.newLine(); 
			 serverLog.write(this.peakHourMesage());
			output.close();
			serverLog.close();
		} catch (IOException e) {}
		 
		 System.out.println("FINISHED");
	}
	
	
}
